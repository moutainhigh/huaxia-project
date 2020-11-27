/**
 * Title: Bottle.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午2:21:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Bottle2020年9月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午2:21:16
 */
public class Bottle extends Treasure {

	/**
	 * Constructor
	 */
	public Bottle() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see JavaMonkey.Treasure#useMagic(JavaMonkey.Target)
	 */
	@Override
	public void useMagic(Target target) {
		// TODO Auto-generated method stub
		System.out.println("把"+target.getName()+"装进瓶里");
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午2:21:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
