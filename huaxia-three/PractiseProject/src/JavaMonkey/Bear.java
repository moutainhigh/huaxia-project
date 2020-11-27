/**
 * Title: Bear.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午3:34:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Bear2020年9月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午3:34:06
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
		System.out.println(getName()+"痛的嗷嗷叫");
	}

}
