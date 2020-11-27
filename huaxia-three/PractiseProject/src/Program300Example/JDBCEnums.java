/**
 * Title: JDBCEnums.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日上午10:56:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:JDBCEnums2020年9月18日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日上午10:56:29
 */
public enum JDBCEnums {
	DRIVER,URL,USERNAME,PASSWORD;
	/**
	 * @Title: getJDBCEnums
	 *@Description: TODO
	 *@param JDBC
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午11:07:30
	 */
	public String getJDBCEnums(JDBCEnums JDBC){
		switch(JDBC){
		case DRIVER:
			return "com.mysql.jdbc.Driver";
		case URL:
			return "jdbc:mysql://localhost:3306/mysqltest";
		case USERNAME:
			return "root";
		case PASSWORD:
			return "123456";
		default: 
			return null;
		}
	}
	/**
	 * @Title: main
	 *@Description: TODO
	 *@param args 遍历枚举类型
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午11:09:13
	 */
	public static void main(String args[]){
		for(JDBCEnums JDBC:JDBCEnums.values()){
			System.out.println(JDBC+"："+JDBC.getJDBCEnums(JDBC));
		}
	}
}
