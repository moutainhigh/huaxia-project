/**
 * Title: ConsCodeBlock.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������5:05:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

/**
 * @class_name:ConsCodeBlock2020��9��11��
 * @comments: ���ִ��˳�򣬾�̬��ֻ�ڼ��ص�ʱ��ִ��һ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������5:05:39
 */
public class ConsCodeBlock {

	/**
	 * Constructor
	 */
	public ConsCodeBlock() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������5:05:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 =new Person();
		System.out.println("-----------------------");
		Person p2 = new Person("Zhang");
	}
}
class Person
{
	{
		System.out.println("���췽���飬��̬�����ִ��....");
		x = 100;
	}
	Person()
	{
		System.out.println("���췽��ִ��....");
		name = "Guangzi";
		show();
	}
	Person(String name)
	{
		System.out.println("���췽��ִ��....");
		this.name = name;
		show();
	}
	/**
	 * @Title: show
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��11������5:09:16
	 */
	void show(){
		System.out.println("Welcome!"+name);
		System.out.println("x="+x);
	}
	private String name;
	private int x;
}
