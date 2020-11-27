/**
 * Title: Gourd.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午2:22:40
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Gourd2020年9月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午2:22:40
 */
public class Gourd extends Treasure {

	/**
	 * Constructor
	 */
	public Gourd() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see JavaMonkey.Treasure#useMagic(JavaMonkey.Target)
	 */
	@Override
	public void useMagic(Target target) {
		// TODO Auto-generated method stub
		System.out.println("把"+target.getName()+"装进葫芦里");
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午2:22:40
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
