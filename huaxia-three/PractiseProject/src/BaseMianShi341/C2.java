/**
 * Title: C2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午9:59:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;
/**
 * 
 * @class_name:A22020年9月23日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午10:01:24
 */
class A2{
	String x,y;
	public A2(String a,String b){
		x = a;
		y = b;
	}
	/**
	 * @Title: write
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午10:01:11
	 */
	public void write(){
		System.out.println(x);
	}
}
/**
 * @class_name:C22020年9月23日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午9:59:33
 */
public class C2 extends A2{

	/**
	 * Constructor
	 */
	public C2(String a,String b) {
		// TODO Auto-generated constructor stub
		super(a,b);
	}
	/**
	 * @Title: write
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午10:01:11
	 */
	public void write(){
		System.out.println(x+" to"+y);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午9:59:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A2 p = new A2("beijing","shanghai");
		A2 t = new C2("guangdong","jilin");
		p.write();
		t.write();
	}

}
