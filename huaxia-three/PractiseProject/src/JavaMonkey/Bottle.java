/**
 * Title: Bottle.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������2:21:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Bottle2020��9��15��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������2:21:16
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
		System.out.println("��"+target.getName()+"װ��ƿ��");
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��15������2:21:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
