package codeQuerstion;
/**
 * 拍讯算法
 * @author 刘伟
 *
 */
public class SortArray {
	//冒泡排序
	public void sort(int[] arr){
		int temp ;
		for(int i=0;i<arr.length;i++){
			 for(int j =  (i+1) ;j<arr.length;j++){
				 if(arr[i] > arr[j]){
					 temp = arr[i];
					 arr[i]  = arr[j];
					 arr[j] = temp;
				 }
			 }
		}
	}
public static void main(String args[]){
	int[] arr={9,8,7,100,5,4,3,2,1};
	SortArray so = new SortArray();
	so.sort(arr);
	for(int i=0;i<arr.length;i++){
		System.out.print(arr[i]+ "  ");
	}
}
}
