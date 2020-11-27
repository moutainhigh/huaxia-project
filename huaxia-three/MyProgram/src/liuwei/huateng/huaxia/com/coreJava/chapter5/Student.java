/**
 * Title: Student.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日上午9:59:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:Student2019年12月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日上午9:59:51
 */
public class Student extends Person {
	private String major;
	/**
	 * @param name
	 */
	public Student(String name,String major) {
		super(name);
		// TODO Auto-generated constructor stub
		this.major = major;
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.coreJava.chapter5.Person#getDescripton()
	 */
	@Override
	public String getDescripton() {
		// TODO Auto-generated method stub
		return "a student majoring in "+major;
	}

}
