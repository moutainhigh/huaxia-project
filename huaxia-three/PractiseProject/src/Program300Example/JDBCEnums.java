/**
 * Title: JDBCEnums.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������10:56:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:JDBCEnums2020��9��18��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������10:56:29
 */
public enum JDBCEnums {
	DRIVER,URL,USERNAME,PASSWORD;
	/**
	 * @Title: getJDBCEnums
	 *@Description: TODO
	 *@param JDBC
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��18������11:07:30
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
	 *@param args ����ö������
	 *@author: LiuWei
	 *@Date: 2020��9��18������11:09:13
	 */
	public static void main(String args[]){
		for(JDBCEnums JDBC:JDBCEnums.values()){
			System.out.println(JDBC+"��"+JDBC.getJDBCEnums(JDBC));
		}
	}
}
