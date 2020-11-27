package codeQuerstion;
/**
 * 计算排列数目 例如123 排列为123 321 123 等等 6种
 * 分析
 * 123456
 * 12
 * 123
 * @author liuwei
 *
 */
public class Permutation {
	static int[] array ;//用于排列的临时数组
	static int count = 0;//统计排列的个数
	// 使用递归进行解决
	public void mySort(int[] arr){
		
		if(arr.length == 1){
			array[0]= arr[0];
			printArr2(array);
			count++;
//			System.out.println(arr[0]+"");
//			System.out.print("arr "+arr[0]);
//			System.out.println("Hello World");
		}else{
			int[] tempArr = copyArr(arr);
//			System.out.println(arr.length);
			for(int i=0;i<tempArr.length;i++){
//				System.out.print(tempArr[i]);
				array[tempArr.length-1]=tempArr[i];
				arr = remove(tempArr,i);
//				   printArr(arr);
				mySort(arr);
			}
		}	
	}
	//删除数组中的制定元素
	public int[] remove(int[] arr,int i){
		
		int[] arr2 = new int[arr.length-1];
		int len = 0;
		for(int k= 0;k<arr.length;k++){
			if( k == i){
				continue;
			}
			arr2[len]=arr[k];
			len++;
		}
		return arr2;
	}
	//copy数组
	public int[] copyArr(int[] arr){
		int[] arr2 = new int[arr.length];
		for(int i = 0;i<arr.length;i++){
			arr2[i] = arr[i];
		}
		return arr2;
	}
	public static void main(String args[]) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		array = arr;
		Permutation p = new Permutation();
		p.mySort(arr);
		System.out.println("排列总数:"+count);
//		p.printArr(arr);
//		arr = p.remove(arr, 0);
//		p.printArr(arr);
	}
	public void printArr(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	//倒序输出
	public void printArr2(int[] arr){
		for(int i=arr.length-1;i>=0;i--){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
}
