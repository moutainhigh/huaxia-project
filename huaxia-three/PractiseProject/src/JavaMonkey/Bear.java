/**
 * Title: Bear.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������3:34:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Bear2020��9��15��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������3:34:06
 */
public class Bear extends Animal implements Trainee {

	/**
	 * @param nameConstructor
	 */
	public Bear(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see JavaMonkey.Trainee#haveHeadache()
	 */
	@Override
	public void haveHeadache() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"ʹ���໽�");
	}

}
