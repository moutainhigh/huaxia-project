/**
 * Title: ArrayMaxMin.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日上午9:34:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:ArrayMaxMin2020年9月18日
 * @comments:求数组的最大值和最小值
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日上午9:34:50
 */
public class ArrayMaxMin {

	/**
	 * Constructor
	 */
	public ArrayMaxMin() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午9:34:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i ,min,max;
		int A[] = {25,65,98,36,45};
		min = max = A[0] ;
		System.out.println("数组A的元素包括：");
		for(i=0;i<A.length;i++){
			System.out.println(A[i] +" ");
			if(A[i]>max)
				max = A[i];
			if(A[i]<min)
				min = A[i];
		}
		System.out.println("数组的最大值是"+max);
		System.out.println("数组的最小值是"+min);
	}

}
