package Hash;

import java.util.HashMap;


public class RandomPool {
	public static class Pool {
		HashMap<String,Integer> hashmap1 ;
		HashMap<Integer,String> hashmap2 ;
		int size;//0-(size-1)Ϊ�Ѵ洢������
		public Pool(){
			 this.hashmap1 = new HashMap<String,Integer>();
			 this.hashmap2 = new HashMap<Integer,String>();
			 this.size = 0;
		}
		public void insert(String key){
			if(!this.hashmap1.containsKey(key)){
				this.hashmap1.put(key, size);
				this.hashmap2.put(size, key);
				this.size++;
			}
			
		}
		public void delete(String key) {
			if(!this.hashmap1.containsKey(key))
				return;
			int size_key = this.hashmap1.get(key);
			String key_sizemax = this.hashmap2.get(this.size-1);
			this.hashmap1.put(key_sizemax, size_key);
			this.hashmap2.put(size_key,key_sizemax);
			this.hashmap1.remove(key);
			this.hashmap2.remove(this.size-1);
			this.size--;
		}
		public String getRandom(){
			if(this.size== 0)
				return null;
			int k = (int) (Math.random() * this.size); // 0 ~ size -1
			return this.hashmap2.get(k);
			
			
		}
	}
	public static void main(String[] args) {
		Pool pool = new Pool();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}
}
