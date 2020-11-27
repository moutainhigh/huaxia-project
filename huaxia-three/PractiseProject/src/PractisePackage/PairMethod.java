/**
 * Title: PairMethod.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午3:30:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

/**
 * @class_name:PairMethod2020年8月6日
 * @comments: 泛型方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午3:30:14
 */
public class PairMethod {

	/**
	 * Constructor
	 */
	public PairMethod() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午3:30:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(indexOf(new Integer[]{1,3,5},10));
		System.out.println(indexOf(new String[]{"hello","老马","编程"},"老马"));
	}
	/**
	 * @Title: indexOf
	 *@Description: TODO 泛型查询方法
	 *@param arr
	 *@param elm
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午3:32:19
	 */
	public static <T> int indexOf(T[] arr,T elm){
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals(elm)){
				return i;
			}
		}
		return -1;
	}
}
