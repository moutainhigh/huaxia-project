/**
 * Title: C1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午10:21:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:C12020年9月23日
 * @comments: 方法的重载，重写方法，和覆盖不同
 * runResult:public Man（String n)
我是：张三，今年：0岁
public Man（int a)
我是：null，今年：12岁
public Man（String n,int a)
我是：李四，今年：56岁
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午10:21:12
 */
public class C1 {

	/**
	 * Constructor
	 */
	public C1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午10:21:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Man2 man = new Man2("张三");
		System.out.println(man.talk());
		System.out.println(new Man2(12).talk());
		System.out.println(new Man2("李四",56).talk());
	}
}
class Man2{
	private String name;
	private int age;
	/**
	 * 
	 * @param n
	 * @param aConstructor
	 */
	public Man2(String n,int a){
		name = n;
		age = a;
		System.out.println("public Man（String n,int a)");
	}
	/**
	 * 
	 * @param nConstructor
	 */
	public Man2(String n){
		name = n;
		System.out.println("public Man（String n)");
	}
	/**
	 * 
	 * @param aConstructor
	 */
	public Man2(int a){
		age = a;
		System.out.println("public Man（int a)");
	}
	/**
	 * @Title: talk
	 *@Description: TODO
	 *@return 说话方法
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午10:25:23
	 */
	public String talk(){
		return "我是："+name+"，今年："+age+"岁";
	}
}
