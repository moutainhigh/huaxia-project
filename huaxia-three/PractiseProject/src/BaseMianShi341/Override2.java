/**
 * Title: Override.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日下午5:24:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:Override2020年9月22日
 * @comments: 继承的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午5:24:23
 */
public class Override2 {

	/**
	 * Constructor
	 */
	public Override2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午5:24:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person person= new Woman();
		person.foot();
		person.talk();
	}

}
/**
 * 
 * @class_name:Person2020年9月22日
 * @comments:父类Person
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午5:25:36
 */
class Person{
	/**
	 * @Title: talk
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午5:25:27
	 */
	public void talk(){
		System.out.println("Person.talk()");
	}
	/**
	 * @Title: foot
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午5:25:31
	 */
	public void foot(){
		System.out.println("Person.foot");
	}
}
/**
 * 
 * @class_name:Woman2020年9月22日
 * @comments:子类方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午5:27:19
 */
class Woman extends Person{
	/**
	 * 重写的talk方法
	 */
	public void talk(){
		System.out.println("Woman.talk()");
	}
	/**
	 * @Title: baby
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午5:26:55
	 */
	public void baby(){
		System.out.println("Woman.baby()");
	}
}