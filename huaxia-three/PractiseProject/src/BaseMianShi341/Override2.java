/**
 * Title: Override.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������5:24:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:Override2020��9��22��
 * @comments: �̳е�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������5:24:23
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
	 *@Date: 2020��9��22������5:24:23
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
 * @class_name:Person2020��9��22��
 * @comments:����Person
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������5:25:36
 */
class Person{
	/**
	 * @Title: talk
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��22������5:25:27
	 */
	public void talk(){
		System.out.println("Person.talk()");
	}
	/**
	 * @Title: foot
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��22������5:25:31
	 */
	public void foot(){
		System.out.println("Person.foot");
	}
}
/**
 * 
 * @class_name:Woman2020��9��22��
 * @comments:���෽��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������5:27:19
 */
class Woman extends Person{
	/**
	 * ��д��talk����
	 */
	public void talk(){
		System.out.println("Woman.talk()");
	}
	/**
	 * @Title: baby
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��22������5:26:55
	 */
	public void baby(){
		System.out.println("Woman.baby()");
	}
}