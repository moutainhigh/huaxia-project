/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日下午3:26:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.enumExample;

/**
 * @class_name:Test2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日下午3:26:48
 */
public class Test {
	private Object b;
	
	public Object getB() {
		return b;
	}

	public void setB(Object b) {
		this.b = b;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日下午3:26:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
		t.setB(new Boolean(true));
		System.out.println(t.getB());
		t.setB(new Float(12.3));
		Float f = (Float)(t.getB());
		System.out.println(f);
	}

}
