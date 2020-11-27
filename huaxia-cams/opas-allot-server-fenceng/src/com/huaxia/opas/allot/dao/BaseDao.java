/**
 * 
 */
/**
 * @author uatjl990299
 *
 */
package com.huaxia.opas.allot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.huaxia.opas.common.util.EncrypAES;

public class BaseDao {

	private String driver;
	private String url;
	private String username;
	private String password;
	
	private String driver2;
	private String url2;
	private String username2;
	private String password2;
	
	
	
	public Connection getConnection() throws Exception{
		EncrypAES aes = new EncrypAES();
		String result = aes.decryptor(password, "huateng2spdbcccc");
		Class.forName(driver);
		return DriverManager.getConnection(url, username, result);
		
	}
	
	public Connection getConnectionBig() throws Exception{
		EncrypAES aes = new EncrypAES();
		String result = aes.decryptor(password2, "huateng2spdbcccc");
		Class.forName(driver2);
		return DriverManager.getConnection(url2, username2, result);
		
	}

	public static void closeConnection(Connection conn, Statement stat ,ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
		if (stat!=null) {
			try {
				stat.close();
			} catch (Exception e) {
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver2() {
		return driver2;
	}

	public void setDriver2(String driver2) {
		this.driver2 = driver2;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	
	
}