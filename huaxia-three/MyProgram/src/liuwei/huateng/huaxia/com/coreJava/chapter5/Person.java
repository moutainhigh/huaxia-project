/**
 * Title: Person.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��30������9:53:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:Person2019��12��30��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��30������9:53:05
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
