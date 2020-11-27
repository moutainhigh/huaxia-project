/**
 * Title: ConsCodeBlock.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午5:05:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

/**
 * @class_name:ConsCodeBlock2020年9月11日
 * @comments: 类的执行顺序，静态块只在加载的时候执行一次
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午5:05:39
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
	 *@Date: 2020年9月11日下午5:05:39
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
		System.out.println("构造方法块，静态程序块执行....");
		x = 100;
	}
	Person()
	{
		System.out.println("构造方法执行....");
		name = "Guangzi";
		show();
	}
	Person(String name)
	{
		System.out.println("构造方法执行....");
		this.name = name;
		show();
	}
	/**
	 * @Title: show
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午5:09:16
	 */
	void show(){
		System.out.println("Welcome!"+name);
		System.out.println("x="+x);
	}
	private String name;
	private int x;
}
