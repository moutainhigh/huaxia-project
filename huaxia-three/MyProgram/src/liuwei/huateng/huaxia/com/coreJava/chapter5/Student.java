/**
 * Title: Student.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��30������9:59:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:Student2019��12��30��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��30������9:59:51
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
