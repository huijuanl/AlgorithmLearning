package Search;
//二分查找非递归算法，注意边界
public class binarySearch {
 public static int BinarySearch(int []arr,int low,int high,int target){
	 //在[low,high]区间内进行查找
	 while(low<=high){
		 int mid = (low+high)/2;
		 if(arr[mid] == target)
			 return mid;
		 if(arr[mid] < target)
			 low = mid +1;
		 else high = mid -1;
		 
	 }
	 return -1;
	 
 }
 //二分查找递归算法，可以根据非递归算法的边界来照着修改，比较容易
 public static int BinarySearchRecursion(int[]arr,int low,int high,int target){
	 if(low>high)
		 return -1;
	 int mid = (low+high)/2;
	 if(arr[mid] == target)
		 return mid;
	 if(arr[mid]<target)
		 return BinarySearchRecursion(arr,mid+1,high,target);
	 else 
		 return BinarySearchRecursion(arr,low,mid-1,target);
 }
 //test
 public static void main(String[]args){
	 int[] arr={1,3,5,6,7};
	 int target =2;
	 System.out.println(BinarySearch(arr,0,arr.length-1,target));
	 System.out.println(BinarySearchRecursion(arr,0,arr.length-1,target));
 }
 
}
