/**
 * Title: User.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��9������9:33:43
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern;

/**
 * @class_name:User2020��1��9��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��9������9:33:43
 */
public class User {
	//����
	private String name;
	//����
	private int age;
	/**
	 * 
	 */
	public User(String name,int age) {
		// TODO Auto-generated constructor stub
		this.name  = name;
		this.age = age;
	}
	/**
	 * @Title: show
	 *@Description: TODO ��ʾ��Ϣ
	 *@author: LiuWei
	 *@Date: 2020��1��9������9:35:43
	 */
	public void show(){
		System.out.println("�û�����"+name+"\t���䣺"+age);
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
}
