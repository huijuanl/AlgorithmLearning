// Databricks notebook source
// MAGIC %md ## Speed up the creation of clusters using external metastore by pre-downloading metastore client libraries.
// MAGIC 
// MAGIC When setting up the connection to an external metastore, setting `spark.sql.hive.metastore.jars` to `maven` allows the Spark driver to automatically download all of needed jars based on the metastore version set by `spark.sql.hive.metastore.version`. However, with `spark.sql.hive.metastore.jars` set to `maven`, Spark will need to connect to Maven Central to download all of jars, and this external service can sometimes be flaky (e.g. transient slow connection issues). In order to reduce the cluster startup time and make setting up metastore jars robust, it will be good to pre-download all of needed jars from Maven to DBFS and then use those jars in DBFS.
// MAGIC 
// MAGIC The rest of this notebook will show you how to do it.
// MAGIC 
// MAGIC **Note: If you are experiencing cluster startup issue caused by the slowness of downloading metastore client libraries, please run this notebook using a cluster that does not have any custom Spark configurations.**

// COMMAND ----------

// MAGIC %md ## Specify the metastore version and specify the location in DBFS to store metastore clients

// COMMAND ----------

// MAGIC %md Please set `metastoreVersion` to the version that matches the external metastore version. Then, please specify the location of downloaded jars in DBFS.

// COMMAND ----------

// Please set these values.
val metastoreVersion = "3.1.0"
// metastoreLibDirInDbfs should start with dbfs:/, and looks like dbfs:/foo/bar
val metastoreLibDirInDbfs = "dbfs:/ella/hive_metastore_jars/"
// initScriptPath is a location in DBFS, should look like dbfs:/foo/bar/baz.sh
val initScriptPath = "dbfs:/ella/hive_metastore_jars/setup_metastore.sh"

// COMMAND ----------

// MAGIC %md ## Pre-flight checks

// COMMAND ----------

val path = new org.apache.hadoop.fs.Path(metastoreLibDirInDbfs)
val fs = path.getFileSystem(sc.hadoopConfiguration)
assert(!fs.exists(path), s"$metastoreLibDirInDbfs exists, please use a new path to DBFS for storing downloaded jars")

// COMMAND ----------

// MAGIC %md ## Download metastore jars

// COMMAND ----------

package org.apache.spark.sql.hive.client

import java.io.File
import java.lang.reflect.InvocationTargetException
import java.net.{URL, URLClassLoader}
import java.util

import scala.util.Try

import org.apache.commons.io.{FileUtils, IOUtils}
import org.apache.hadoop.conf.Configuration

import org.apache.spark.SparkConf
import org.apache.spark.deploy.SparkSubmitUtils
import org.apache.spark.internal.Logging
import org.apache.spark.sql.catalyst.util.quietly
import org.apache.spark.sql.hive.HiveUtils
import org.apache.spark.sql.internal.NonClosableMutableURLClassLoader
import org.apache.spark.util.{MutableURLClassLoader, Utils}

object HiveMetastoreJarDownloadUtil {
  
  def hiveVersion(version: String): HiveVersion = {
    IsolatedClientLoader.hiveVersion(version)
  }
  
  def downloadVersion(
      version: HiveVersion,
      hadoopVersion: String = "2.7.3",
      ivyPath: Option[String] = None): String = {
    val hiveArtifacts = version.extraDeps ++
      Seq("hive-metastore", "hive-exec", "hive-common", "hive-serde")
        .map(a => s"org.apache.hive:$a:${version.fullVersion}") ++
      Seq("com.google.guava:guava:14.0.1",
        s"org.apache.hadoop:hadoop-client:$hadoopVersion")

    val classpath = SparkSubmitUtils.resolveMavenCoordinates(
        hiveArtifacts.mkString(","),
        SparkSubmitUtils.buildIvySettings(
          Some("http://www.datanucleus.org/downloads/maven2"),
          ivyPath),
        exclusions = version.exclusions)
    val allFiles = classpath.split(",").map(new File(_)).toSet

    val tempDir = Utils.createTempDir(namePrefix = s"hive-${version}")
    allFiles.foreach(f => FileUtils.copyFileToDirectory(f, tempDir))
    println(s"Downloaded metastore jars to ${tempDir.getCanonicalPath}")
    tempDir.listFiles().map(_.toURI.toURL)
    return tempDir.getCanonicalPath
  }
}

// COMMAND ----------

def downloadMetastoreJarstoDbfs(metastoreVersion: String, metastoreLibDirInDbfs: String): Unit = {
  val hiveVersion = org.apache.spark.sql.hive.client.HiveMetastoreJarDownloadUtil.hiveVersion(metastoreVersion)
  // Download jars.
  val downloadedDir = org.apache.spark.sql.hive.client.HiveMetastoreJarDownloadUtil.downloadVersion(hiveVersion)
  // Get downloaded jars to dbfs
  dbutils.fs.cp(s"file://$downloadedDir", metastoreLibDirInDbfs, true)
  println(s"Metastore libraries for version $metastoreVersion have been downloaded to $metastoreLibDirInDbfs")
}

// COMMAND ----------

// metastoreLibDirInDbfs needs to be a absolute DBFS path, like dbfs:/foo/bar or /foo/bar.
def generateInitScript(metastoreVersion: String, metastoreLibDirInDbfs: String, initScriptPath: String): String = {
  val dbfsPathInDriver = "/dbfs" + metastoreLibDirInDbfs.stripPrefix("dbfs:")
  val dbfsInitScriptPath = "/dbfs" + initScriptPath.stripPrefix("dbfs:")
  
  s"""
You may use the example init script to setup the external metastore connection with "spark.sql.hive.metastore.jars" set to "maven":


#!/bin/bash 

set -e 

sleep 10
cp -R $dbfsPathInDriver /databricks/hive_metastore_jars

cat >/databricks/driver/conf/00-spark-metastore.conf << 'EOL'
[driver] {
  # Spark specific configuration options
  "spark.sql.hive.metastore.version" = "$metastoreVersion"
  "spark.sql.hive.metastore.jars" = "/databricks/hive_metastore_jars/*"
}

Alternatively, if you are not using init scripts and instead are setting external metastore configurations via the Databricks cluster creation UI (or REST API) you can change the Spark configuration above in your cluster configuration.
  """
}

// COMMAND ----------

// Download jars from maven. This cell could takes a few minutes to finish.
downloadMetastoreJarstoDbfs(metastoreVersion, metastoreLibDirInDbfs)

// COMMAND ----------

// MAGIC %md ## Generate the instruction

// COMMAND ----------

generateInitScript(metastoreVersion, metastoreLibDirInDbfs, initScriptPath)

// COMMAND ----------

// MAGIC %md ## Test 
// MAGIC 
// MAGIC Once set up, test using the following.

// COMMAND ----------

// MAGIC %scala
// MAGIC  
// MAGIC {
// MAGIC   import com.databricks.logging.{AttributionContext, BaseTagDefinitions}
// MAGIC    
// MAGIC   val sparkConfigurations = Seq(
// MAGIC     "spark.sql.hive.metastore.version",
// MAGIC     "spark.sql.hive.metastore.jars",
// MAGIC     "spark.sql.hive.hmshandler.retry.attempts",
// MAGIC     "spark.databricks.clusterSource",
// MAGIC     "spark.databricks.hive.metastore.client.pool.enabled"
// MAGIC   )
// MAGIC  
// MAGIC   val hadoopConfigurations = Seq(
// MAGIC     "datanucleus.autoCreateSchema",
// MAGIC     "datanucleus.fixedDatastore",
// MAGIC     "datanucleus.connectionPool.initialPoolSize",
// MAGIC     "datanucleus.connectionPool.minPoolSize",
// MAGIC     "datanucleus.connectionPool.maxPoolSize",
// MAGIC     "datanucleus.connectionPoolingType",
// MAGIC     "javax.jdo.option.ConnectionDriverName",
// MAGIC     "hive.hmshandler.retry.attempts"
// MAGIC   )
// MAGIC  
// MAGIC   val dbrImageLabel = AttributionContext.current.tags.get(BaseTagDefinitions.TAG_SPARK_VERSION)
// MAGIC    
// MAGIC   val universeGitHash = com.databricks.BuildInfo.gitHash
// MAGIC    
// MAGIC   println(s"Spark version: ${sc.version}")
// MAGIC   println(s"""DBR Image Label: ${dbrImageLabel.getOrElse("UNKNOWN")}""")
// MAGIC   println(s"""Universe git commit: $universeGitHash""")
// MAGIC   println()
// MAGIC    
// MAGIC   println("Spark configurations:")
// MAGIC   for (conf <- sparkConfigurations.sorted) {
// MAGIC     val value = Option(sc.getConf.get(conf, null)).getOrElse("[not set]")
// MAGIC     println(s"    $conf: $value")
// MAGIC   }
// MAGIC   println()
// MAGIC    
// MAGIC   println("Hadoop / Hive configurations:")
// MAGIC   for (conf <- hadoopConfigurations.sorted) {
// MAGIC     val value = Option(sc.hadoopConfiguration.get(conf, null)).getOrElse("[not set]")
// MAGIC     println(s"    $conf: $value")
// MAGIC   }
// MAGIC   println()
// MAGIC    
// MAGIC   println("Sources of Hive classes:")
// MAGIC   try {
// MAGIC     val externalCatalog = spark.sharedState.externalCatalog
// MAGIC     val client = externalCatalog match {
// MAGIC       case hasUnwrapped: { def unwrapped: Any }
// MAGIC           if hasUnwrapped.getClass.getName.contains("ExternalCatalogProxy")
// MAGIC           || hasUnwrapped.getClass.getName.contains("ExternalCatalogWithListener") =>
// MAGIC         hasUnwrapped.unwrapped match {
// MAGIC             case hasClientField: { def client: Any } => Some(hasClientField.client)
// MAGIC             case _ => None
// MAGIC         }
// MAGIC       case hasClientField: { def client: Any } => Some(hasClientField.client)
// MAGIC       case _ => None
// MAGIC     }
// MAGIC  
// MAGIC     val isolatedClientLoader = client.flatMap {
// MAGIC       case hasClientLoaderField: { def clientLoader: Any } =>
// MAGIC         hasClientLoaderField.clientLoader match {
// MAGIC           case hasLoader: { def classLoader: java.lang.ClassLoader } => Some(hasLoader.classLoader)
// MAGIC           case _ => None
// MAGIC         }
// MAGIC     }
// MAGIC      
// MAGIC     val classes = Seq(
// MAGIC       "org/apache/hadoop/hive/ql/metadata/Hive.class"
// MAGIC     )
// MAGIC      
// MAGIC     classes.foreach { cls =>
// MAGIC       val source =  isolatedClientLoader.map(_.getResource(cls)).getOrElse("[unknown]")
// MAGIC       println(s"    $cls: $source")
// MAGIC     }
// MAGIC  
// MAGIC   } catch {
// MAGIC     case scala.util.control.NonFatal(t) =>
// MAGIC       println("Error while trying to determine source of Hive classes:")
// MAGIC       println(t)
// MAGIC   }