/**
 * Title: ArrayClass.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日下午4:11:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.enumExample;

/**
 * @class_name:ArrayClass2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日下午4:11:41
 */
public class ArrayClass<T> {
	private T[] array;
	
	public T[] getT() {
		return array;
	}

	public void setT(T[] array) {
		this.array = array;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日下午4:11:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayClass<String> a =new ArrayClass<String>();
		String[] array = {"成员1","成员2","成员3","成员4","成员5"};
		a.setT(array);
		for(int i=0;i<a.getT().length;i++){
			System.out.println(a.getT()[i]);
		}
	}

}
