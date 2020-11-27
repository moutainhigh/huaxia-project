/**
 * Title: OverClass.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日下午3:40:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.enumExample;

/**
 * @class_name:OverClass2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日下午3:40:54
 */
public class OverClass<T> {
	private  T over;
	
	public T getOver() {
		return over;
	}

	public void setOver(T over) {
		this.over = over;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日下午3:40:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OverClass<Boolean> over1 = new OverClass<Boolean>();
		OverClass<Float> over2 = new OverClass<Float>();
		over1.setOver(true);
		over2.setOver(12.3f);
		Boolean b = over1.getOver();
		Float f = over2.getOver();
		System.out.println(b);
		System.out.println(f);
	}
}
