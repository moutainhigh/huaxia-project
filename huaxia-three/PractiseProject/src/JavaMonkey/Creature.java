/**
 * Title: Creature.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午2:36:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

import java.util.Date;

/**
 * @class_name:Creature2020年9月15日
 * @comments: 生死簿中的生灵 ，生灵的出生都是固定的。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午2:36:22
 */
public class Creature {
	private final int no;//编号
	private final String category;//类别
	private final String name;//姓名
	private final int lifetime;//寿命
	private final Date birthday;//出生日期
	private final String birthplace;//出生地
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
	 *@Date: 2020年9月15日下午2:36:22
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
