/**
 * Title: Treasure.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午2:20:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Treasure2020年9月15日
 * @comments: 抽象类和接口的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午2:20:03
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
	 *@Date: 2020年9月15日下午2:20:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bottle bottle = new Bottle();
		Gourd gourd = new Gourd();
		Rope rope = new Rope();
		Monkey monkey = new Monkey("孙悟空");
		bottle.useMagic(monkey);
		gourd.useMagic(monkey);
		rope.useMagic(monkey);
	}
}
