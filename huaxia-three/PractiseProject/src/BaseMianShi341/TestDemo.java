/**
 * Title: TestDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������11:01:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:TestDemo2020��9��23��
 * @comments:Safe1
Safe3
Safe4
û���쳣��catch��䲻��ִ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������11:01:48
 */
public class TestDemo {

	/**
	 * Constructor
	 */
	public TestDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��23������11:01:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestDemo().demo();
	}
	/**
	 * @Title: demo
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��23������11:07:26
	 */
	public void demo(){
		try{
			test();
			System.out.println("Safe1");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Safe2");
		}finally{
			System.out.println("Safe3");
		}
		System.out.println("Safe4");
	}
	public void test(){
	}
}
