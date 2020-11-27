package DataStructuresAndAlgorithm;
/**
 * 测试排序算法
 * @author liuwei
 *
 */
public class SortAlgorithm {
	public static boolean compareTo(int a,int b){
		int temp = 0;
		temp = a-b;
		if(temp >= 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @Title: insertionSort
	 *@Description: 插入排序
	 *@param a
	 *@author: LiuWei
	 *@Date: 2019年7月5日上午9:52:10
	 */
	public static void insertionSort(int[] a){
		int j;
		for(int p=1;p<a.length;p++){
			int tmp = a[p];
			for(j = p;j>0&&(compareTo(a[p],a[j]));j--)
				a[j] = a[j-1];
			a[j] = tmp;
		}
	}
	/**
	 * @Title: paoSort
	 *@Description: 冒泡排序
	 *@param a
	 *@author: LiuWei
	 *@Date: 2019年7月8日下午10:46:07
	 */
	public static void paoSort(int[] a){
		int temp;
		for(int i=0;i<a.length;i++){
			for(int j=i;j<a.length;j++){
				if(a[j]<a[i]){
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
	}
	 public static void main(String args[]){
		 int[] a = new int[101];
		 int temp = 101;
		 for(int i=0;i<a.length;i++){
			 a[i] = temp;
			 temp--;
		 }
		 System.out.println("排序前的数据：");
		 for(int i=0;i<a.length;i++){
			 System.out.print(a[i]+ " ");
		 }
		 System.out.println();
//		 System.out.println(a.toString());
//		 insertionSort(a);
		 System.out.println("排序后的数据：");
//		 System.out.println(a.toString());
		 for(int i=0;i<a.length;i++){
			 System.out.print(a[i]+ " ");
		 }
		 System.out.println();
		 paoSort(a);
		 System.out.println("冒泡排序后的数据：");
//		 System.out.println(a.toString());
		 for(int i=0;i<a.length;i++){
			 System.out.print(a[i]+ " ");
		 }
		 System.out.println();
	 }
}
