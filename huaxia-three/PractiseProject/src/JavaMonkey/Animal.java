/**
 * Title: Animal.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������3:31:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Animal2020��9��15��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������3:31:13
 */
public abstract class Animal {
	private String name;
	/**
	 * Constructor
	 */
	public Animal(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
