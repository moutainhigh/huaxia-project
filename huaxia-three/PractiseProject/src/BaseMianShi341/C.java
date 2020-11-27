/**
 * Title: C.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午9:51:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;
interface D{
}
class B extends A implements D{
}
class A{
}
/**
 * @class_name:C2020年9月23日
 * @comments: instanceof 关键字，属于哪个类？
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午9:51:55
 */
public class C extends B{

	/**
	 * Constructor
	 */
	public C() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午9:51:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a = new A();
		B b = new B();
		C c = new C();
		if(a instanceof B){
			System.out.println("Hello vivi");
		}
		if( b instanceof A){
			System.out.println("Hello cici");
		}
		if(c instanceof C){
			System.out.println("Hello wiwi");
		}
		if(c instanceof D){
			System.out.println("Hello");
		}
	}

}
