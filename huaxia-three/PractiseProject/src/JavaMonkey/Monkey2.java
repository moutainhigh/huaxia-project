/**
 * Title: Monkey2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������3:32:40
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Monkey22020��9��15��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������3:32:40
 */
public class Monkey2 extends Animal implements Trainee {

	/**
	 * @param nameConstructor
	 */
	public Monkey2(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see JavaMonkey.Trainee#haveHeadache()
	 */
	@Override
	public void haveHeadache() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"ʹ�Ļ�ð����");
	}

}
