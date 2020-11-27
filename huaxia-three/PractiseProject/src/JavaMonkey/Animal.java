/**
 * Title: Animal.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午3:31:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Animal2020年9月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午3:31:13
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
