/**
 * Title: LiftBook.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������2:42:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @class_name:LiftBook2020��9��15��
 * @comments:
 * @param: ���������ܱ��̳У�ֻ����һ����
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������2:42:21
 */
public final class LiftBook {
	//������е�������Ϣ  ������
	private Map<Integer,Creature> creatures = new  HashMap<Integer,Creature>();
	/**
	 * Constructor
	 */
	public LiftBook() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��15������2:42:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Creature creature = new Creature(1000,"����","�Ƕ���",300,new Date(),"����ɽ");
		LiftBook lifeBook = new LiftBook();
		lifeBook.add(creature);
		creature = lifeBook.get(1000);
		System.out.println("�Ƕ��ǵ�������"+creature.getLifetime());
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param creature ���������������
	 *@author: LiuWei
	 *@Date: 2020��9��15������2:44:47
	 */
	public void add(Creature creature){
		creatures.put(creature.getNo(), creature);
	}
	/**
	 * @Title: get
	 *@Description: TODO
	 *@param no ��ȡ�������Ϣ
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��15������2:45:26
	 */
	public Creature get(int no){
		return creatures.get(no);
	}
}
