/**
 * Title: Monkey2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午3:32:40
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Monkey22020年9月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午3:32:40
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
		System.out.println(getName()+"痛的火冒金星");
	}

}
