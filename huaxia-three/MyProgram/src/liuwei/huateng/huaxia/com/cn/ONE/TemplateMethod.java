/**
 * Title: TemplateMethod.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��5������3:00:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:TemplateMethod2019��12��5��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��5������3:00:01
 */
public class TemplateMethod {

	/**
	 * 
	 */
	public TemplateMethod() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��5������3:00:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TemplateMethod t1 = new TemplateMethod(){
			@Override
			protected void warpPrint(String message){
				System.out.println("+"+message+"+");
			}
		};
		t1.print("Hello Thread!");
		TemplateMethod t2 = new TemplateMethod(){
			@Override
			protected void warpPrint(String message){
				System.out.println("*"+message+"*");
			}
		};
		t2.print("Hello Thread!");
	}
	/**
	 * @Title: print
	 *@Description: TODO
	 *@param message
	 *@author: LiuWei
	 *@Date: 2019��12��5������3:05:12
	 */
	public final void print(String message){
		System.out.println(".............");
		warpPrint(message);
		System.out.println(".............");
	}
	/**
	 * @Title: warpPrint
	 *@Description: TODO
	 *@param message
	 *@author: LiuWei
	 *@Date: 2019��12��5������3:05:15
	 */
	protected void warpPrint(String message){
	}
}
