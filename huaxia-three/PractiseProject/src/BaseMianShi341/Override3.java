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
 * @comments: �̳е�ʹ�� �̳кͶ�̬
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������5:24:23
 */
public class Override3 {

	/**
	 * Constructor
	 */
	public Override3() {
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
		Person2 person= new Woman2();
		person.foot();
		person.talk();
		Person2 person2= new Man();
		person2.foot();
		person2.talk();
		Woman woman = new Woman();
		woman.foot();
		woman.talk();
		woman.baby();
		Man man = new Man();
		man.foot();
		man.talk();
		man.baby();
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
class Person2{
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
 * .
 * 
 * @createtime:2020��9��22������5:27:19
 */
class Woman2 extends Person2{
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
/**
 * 
 * @class_name:Woman2020��9��22��
 * @comments:���෽��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������5:27:19
 */
class Man extends Person2{
	/**
	 * ��д��talk����
	 */
	public void talk(){
		System.out.println("Man.talk()");
	}
	/**
	 * @Title: baby
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��22������5:26:55
	 */
	public void baby(){
		System.out.println("Man.baby()");
	}
}