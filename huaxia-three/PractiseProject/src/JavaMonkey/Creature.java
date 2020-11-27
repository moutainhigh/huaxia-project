/**
 * Title: Creature.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������2:36:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

import java.util.Date;

/**
 * @class_name:Creature2020��9��15��
 * @comments: �������е����� ������ĳ������ǹ̶��ġ�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������2:36:22
 */
public class Creature {
	private final int no;//���
	private final String category;//���
	private final String name;//����
	private final int lifetime;//����
	private final Date birthday;//��������
	private final String birthplace;//������
	/**
	 * Constructor
	 */
	public Creature(int no,String category,String name,int lifetime,Date birthday,String birthplace) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.category = category;
		this.name = name;
		this.lifetime = lifetime;
		this.birthday = birthday;
		this.birthplace = birthplace;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��15������2:36:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getNo() {
		return no;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public int getLifetime() {
		return lifetime;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getBirthplace() {
		return birthplace;
	}

}
