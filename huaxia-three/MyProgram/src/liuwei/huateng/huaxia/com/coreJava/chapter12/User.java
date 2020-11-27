/**
 * Title: User.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月15日上午11:20:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

/**
 * @class_name:User2020年1月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月15日上午11:20:09
 */
public class User {
	private String username;
	private String password;
	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String username,String password	) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
