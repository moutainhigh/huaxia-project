/**
 * Title: Person.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日上午9:53:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:Person2019年12月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日上午9:53:05
 */
public abstract class Person {
	public abstract String getDescripton();
	private String name;
	/**
	 * 
	 */
	public Person(String name) {
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
