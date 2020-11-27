/**
 * Title: Singleton.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��15������2:24:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:Singleton2020��10��15��
 * @comments: ����ģʽ������һ��ģʽ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��15������2:24:41
 */
public class Singleton {
	private static Singleton instance = new Singleton();
	/**
	 * Constructor
	 */
	public Singleton() {
		// TODO Auto-generated constructor stub
		System.out.println("Singleton is create");
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��10��15������2:26:22
	 */
	public static Singleton getInstance(){
		return instance;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��10��15������2:24:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10000;i++){
			System.out.println(Singleton.getInstance().hashCode());
		}
	}

}
