/**
 * Title: Treasure.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������2:20:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Treasure2020��9��15��
 * @comments: ������ͽӿڵ�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������2:20:03
 */
public abstract class Treasure {
	public abstract void useMagic(Target target);
	/**
	 * Constructor
	 */
	public Treasure() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��15������2:20:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bottle bottle = new Bottle();
		Gourd gourd = new Gourd();
		Rope rope = new Rope();
		Monkey monkey = new Monkey("�����");
		bottle.useMagic(monkey);
		gourd.useMagic(monkey);
		rope.useMagic(monkey);
	}
}
