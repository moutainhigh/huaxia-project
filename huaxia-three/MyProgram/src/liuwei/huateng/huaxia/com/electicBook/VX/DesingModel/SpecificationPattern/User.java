/**
 * Title: User.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月9日上午9:33:43
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern;

/**
 * @class_name:User2020年1月9日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月9日上午9:33:43
 */
public class User {
	//姓名
	private String name;
	//年龄
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
	 *@Description: TODO 显示信息
	 *@author: LiuWei
	 *@Date: 2020年1月9日上午9:35:43
	 */
	public void show(){
		System.out.println("用户名："+name+"\t年龄："+age);
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
