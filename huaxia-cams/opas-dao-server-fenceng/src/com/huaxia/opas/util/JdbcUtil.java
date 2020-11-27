package com.huaxia.opas.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * JDBC实用类
 * @author pccc  wangdebin
 */
public class JdbcUtil {
	
	//wdb
	private static String url1=null;
	private static String username1=null;
	private static String password1=null;
	private static String driver1=null;
	static Connection  conn ;
	private Properties property;
	private static Map<String, String> cache = new HashMap<String, String>();
	
	public void init() {
		// 数据库配置
		String url1 = (String) property.get("url1");
		String username1 = (String) property.get("username1");
		String password1 = (String) property.get("password1");
		String driver1 = (String) property.get("driver1");
		String jedisUri = (String) property.get("jedisUri");
		if (url1 != null) {
			cache.put("url1", url1);
		}
		if (username1 != null) {
			cache.put("username1", username1);
		}
		if (password1 != null) {
			cache.put("password1", password1);
		}
		
		if (driver1 != null) {
			cache.put("driver1", driver1);
		}
		
		if (jedisUri != null&&jedisUri.contains(":")) {
			List<String> list=Arrays.asList(jedisUri.split(":"));
			cache.put("jedisHost", list.get(0));
			cache.put("jedisPort", list.get(1));
		}
	}
	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}

	public static Map<String, String> getParoperMap() {
		return cache;
	}

	public static void setParoperMap(Map<String, String> paroperMap) {
		JdbcUtil.cache = paroperMap;
	}
	
	/**
	 * 创建JDBC连接
	 * @param driver1 驱动
	 * @param url1 数据库URL
	 * @param user1 用户名
	 * @param password1 密码
	 * @param 连接对象方法
	 * @return conn
	 */
	public static Connection getConnection(){
		try{
			Map<String, String> streamMap = JdbcUtil.getParoperMap();
			url1=streamMap.get("url1").toString();
			username1=streamMap.get("username1").toString();
			password1=streamMap.get("password1").toString();
			PropertiesEncryptFactoryBean fb=new PropertiesEncryptFactoryBean();
			String password=fb.deEncryptString(password1);
			String username=fb.deEncryptString(username1);
			driver1=streamMap.get("driver1").toString();
			//注册驱动程序
			Class.forName(driver1);
			conn = DriverManager.getConnection(url1,username,password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return conn;
	}
	/**
	 * 创建JDBC连接，注意：催收批量尽量不要使用该方法
	 * @param driver 驱动
	 * @param url 数据库URL
	 * @param user 用户名
	 * @param password 密码
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection createConnection (String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
	
	/**
	 * 关闭数据库连接
	 * @param connection
	 * @throws SQLException
	 */
	public static void revertConnection (Connection connection) throws SQLException {
		if (connection!=null) {
			connection.close();
		}
	}
	
	
	/**
	 * 获取查询结果集合
	 * @param stat 语句对象
	 * @param sql 查询SQL
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet getResultSet(Statement stat, String sql) throws SQLException {
		return stat.executeQuery(sql);
	}
	
	/**
	 * 关闭结果集资源
	 * @param rs 结果集合
	 * @throws IOException
	 */
	public static void close(ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
	/**
	 * 关闭语句对象资源
	 * @param stat 语句对象
 	 * @throws IOException
	 */
	public static void close(Statement stat) {
		if (stat!=null) {
			try {
				stat.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
	/**
	 * 关闭数据库连接资源（如果是连接池的话，就是换回连接池）
	 * @param conn 数据库联机
	 * @throws IOException
	 */
	public static void close(Connection conn) {
		if (conn!=null) {
			try {
				conn.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
	/**
	 * 查询单个对象
	 * @param conn 数据库连接
	 * @param sql 查询语句
	 * @param clazz 需要转化为的对象类
	 * @return
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object queryBeanFetchFirstRowOnly (Connection conn, String sql, Class clazz) throws SQLException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Object obj = null;
		
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql+" fetch first 1 row only");
			
			if (rs.next()) {
				// 转化对象
				obj = createBeanFromResultSet(
					rs, 
					getORMappingList(rs), 
					clazz
				);
			}
		} finally {
			close(rs);
			close(stat);
		}
		return obj;
	}
	
	/**
	 * 创建语句对象
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public Statement createStatement(Connection conn) throws SQLException {
		return conn.createStatement();
	}
	
	/**
	 * 创建预编译的语句对象
	 * @param conn 数据库连接
	 * @param sql 
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement createPreparedStatement(Connection conn, String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
	
	/**
	 * 查询整型数字
	 * @param conn 数据库连接
	 * @param sql 查询SQL
	 * @return
	 * @throws SQLException
	 */
	public static int queryIntFromDB(Connection conn, String sql) throws SQLException {
		int returnValue = 0;
		
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				returnValue = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(stat);
		}
		
		return returnValue;
	}
	
	/**
	 * 查询金额数字
	 * @param conn 数据库连接
	 * @param sql 查询SQL
	 * @return
	 * @throws SQLException
	 */
	public static BigDecimal queryBigDecimalFromDB(Connection conn, String sql) throws SQLException {
		BigDecimal returnValue = null;
		
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				returnValue = rs.getBigDecimal(1);
			}
		} finally {
			close(rs);
			close(stat);
		}
		
		return returnValue;
	}
	
	/**
	 * 查询长整型数字
	 * @param conn 数据库连接
	 * @param sql 查询SQL
	 * @return
	 * @throws SQLException
	 */
	public static long queryLongFromDB(Connection conn, String sql) throws SQLException {
		long returnValue = 0;
		
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				returnValue = rs.getLong(1);
			}
		} finally {
			close(rs);
			close(stat);
		}
		
		return returnValue;
	}
	
	/**
	 * 获取结果集合的OR-MAPPING集合
	 * @param rs 结果集合
	 * @return
	 * @throws SQLException
	 */
	public static List<Mapping> getORMappingList(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		List<Mapping> orMappingList= new ArrayList<Mapping>(meta.getColumnCount());
		/*
		 * 循环迭代获取到结果集的数据类型
		 */
		Mapping mapping = null;
		for (int i=1; i<=meta.getColumnCount(); i++) {
			mapping = new Mapping();
				
			mapping.setColumnName(meta.getColumnName(i));													// 字段名称
			mapping.setColumnType(meta.getColumnType(i));													// 字段类型JDBC
			mapping.setPropertyName(StringUtil.getJavaPropertyName(mapping.getColumnName()));				// JAVA属性名称
			mapping.setSetMethodName("set"+StringUtil.getMethodPropertyName(mapping.getPropertyName()));	// 设置set方法的名称
			
			mapping.setJavaType(convertJdbcType2JavaType(mapping.getColumnType()));							// JAVA字段名称
			
			orMappingList.add(mapping);
		}
		
		return orMappingList;
	}
	
	public static List<Mapping> getORMappingListOracle(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		List<Mapping> orMappingList= new ArrayList<Mapping>(meta.getColumnCount());
		/*
		 * 循环迭代获取到结果集的数据类型
		 */
		Mapping mapping = null;
		for (int i=1; i<=meta.getColumnCount(); i++) {
			mapping = new Mapping();
				
			mapping.setColumnName(meta.getColumnName(i));													// 字段名称
			mapping.setColumnType(meta.getColumnType(i));													// 字段类型JDBC
			mapping.setPropertyName(StringUtil.getJavaPropertyName(mapping.getColumnName()));				// JAVA属性名称
			mapping.setSetMethodName("set"+StringUtil.getMethodPropertyName(mapping.getPropertyName()));	// 设置set方法的名称
			
			mapping.setJavaType(convertJdbcType2JavaTypeOracle(mapping.getColumnType(), meta.getPrecision(i), meta.getScale(i)));	// JAVA字段名称
			
			orMappingList.add(mapping);
		}
		
		return orMappingList;
	}
	
	/**
	 * 获取结果集合的OR-MAPPING集合
	 * K:字段名，V:字段映射关系
	 * @param rs 结果集合
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Mapping> getORMappingMap(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		Map<String, Mapping> orMappingMap = new HashMap<String, Mapping>(meta.getColumnCount());
		/*
		 * 循环迭代获取到结果集的数据类型
		 */
		Mapping mapping = null;
		for (int i=1; i<=meta.getColumnCount(); i++) {
			mapping = new Mapping();
				
			mapping.setColumnName(meta.getColumnName(i));													// 字段名称
			mapping.setColumnType(meta.getColumnType(i));													// 字段类型JDBC
			mapping.setPropertyName(StringUtil.getJavaPropertyName(mapping.getColumnName()));				// JAVA属性名称
			mapping.setSetMethodName("set"+StringUtil.getMethodPropertyName(mapping.getPropertyName()));	// 设置set方法的名称
			
			mapping.setJavaType(convertJdbcType2JavaType(mapping.getColumnType()));							// JAVA字段名称
			
			orMappingMap.put(mapping.getColumnName(), mapping);
		}
		
		return orMappingMap;
	}
	
	public static Map<String, Mapping> getORMappingMapOracle(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		Map<String, Mapping> orMappingMap = new HashMap<String, Mapping>(meta.getColumnCount());
		/*
		 * 循环迭代获取到结果集的数据类型
		 */
		Mapping mapping = null;
		for (int i=1; i<=meta.getColumnCount(); i++) {
			mapping = new Mapping();
				
			mapping.setColumnName(meta.getColumnName(i));													// 字段名称
			mapping.setColumnType(meta.getColumnType(i));													// 字段类型JDBC
			mapping.setPropertyName(StringUtil.getJavaPropertyName(mapping.getColumnName()));				// JAVA属性名称
			mapping.setSetMethodName("set"+StringUtil.getMethodPropertyName(mapping.getPropertyName()));	// 设置set方法的名称
			
			mapping.setJavaType(convertJdbcType2JavaTypeOracle(mapping.getColumnType(), meta.getPrecision(i), meta.getScale(i)));		// JAVA字段名称
			
			orMappingMap.put(mapping.getColumnName(), mapping);
		}
		
		return orMappingMap;
	}
	
	/**
	 * 创建数据实体对象
	 * @param rs 结果集合
	 * @param orMappingList 映射关系列表
	 * @param clazz 需要创建的对象类
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public static Object createBeanFromResultSet(ResultSet rs, List<Mapping> orMappingList, Class clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		if (rs==null) {
			throw new IllegalArgumentException("Parameter resultSet is null !");
		}
		if (orMappingList==null) {
			throw new IllegalArgumentException("Parameter orMappingList is null !");
		}
		if (clazz==null) {
			throw new IllegalArgumentException("Parameter class is null !");
		}
		// 先初始化对象
		Object obj = clazz.newInstance();
		Object value = null;
		for (Mapping mapping : orMappingList) {
			
			// 获取字段对应的数据
			value = getObjectFromResultSet(
				rs, 						// 结果集
				mapping.getColumnType(), 	// 字段类型JDBC
				mapping.getColumnName()		// 字段名称
			);
			
			if (value==null) {
				// 如果数据为空，则直接下一个字段
				continue;
			}
			
			// 调用方法
			ClassUtil.invokeMethod(
				obj, 								// bean对象
				mapping.getSetMethodName(), 		// 方法名称
				new Object[]{value}					// 方法所需要的参数
			);
			
		}
		return obj;
	}
	
	
	/**
	 * 从结果集合中获取对应的数据
	 * @param rs 结果结合
	 * @param jdbcType 数据类型
	 * @param columnLabel 字段名
	 * @return
	 * @throws SQLException
	 */
	public static Object getObjectFromResultSet (ResultSet rs, int jdbcType, String columnLabel) throws SQLException {
		
		Object returnObj = null;
		if (
			jdbcType == Types.CHAR
			|| jdbcType == Types.VARCHAR
			|| jdbcType == Types.CLOB
			|| jdbcType == Types.NCHAR
			|| jdbcType == Types.NVARCHAR
			|| jdbcType == Types.NCLOB
			|| jdbcType == Types.LONGVARCHAR
			|| jdbcType == Types.LONGNVARCHAR
		) {
			// 字符串类型
			returnObj = rs.getString(columnLabel);
		} else if (
			jdbcType == Types.DATE
			|| jdbcType == Types.TIMESTAMP
			|| jdbcType == Types.TIME
		) {
			// 时间类型
			returnObj = rs.getDate(columnLabel);
			
		} else if (
			jdbcType == Types.DECIMAL
			|| jdbcType == Types.DOUBLE
			|| jdbcType == Types.FLOAT
			|| jdbcType == Types.NUMERIC
		) {
			// 数字金额类型
			returnObj = rs.getBigDecimal(columnLabel);
			
		} else if (
			jdbcType == Types.INTEGER
			|| jdbcType == Types.SMALLINT
			|| jdbcType == Types.TINYINT
		) {
			// 整数类型
			returnObj = rs.getInt(columnLabel);
			
		} else if (
			jdbcType == Types.BIGINT
			|| jdbcType == Types.SMALLINT
			|| jdbcType == Types.TINYINT
		) {
			// 长整数类型
			returnObj = rs.getLong(columnLabel);			
		} else {
			throw new RuntimeException(String.format("jdbcType=%s, columnLabel=%s", jdbcType, columnLabel));
		}
		return returnObj;
	}
	
	/**
	 * 从结果集中获得指定的字符串
	 * @param rs 结果集
	 * @param columnName 字段名
	 * @param jdbcType 字段类型
	 * @param charset 字符集
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	public static String getString(ResultSet rs, String columnName, int jdbcType, String charset) throws UnsupportedEncodingException, SQLException {
		String returnValue = null;
		if (jdbcType==Types.BLOB) {
			Blob blob = rs.getBlob(columnName);
			if (blob!=null) {
				returnValue = new String(blob.getBytes(1, (int)blob.length()), charset);
			}
		} else {
			returnValue = rs.getString(columnName);
			if (returnValue!=null) {
				returnValue = new String(returnValue.getBytes(charset));
			}
		}
		return returnValue;
	}
	
	/**
	 * 从结果集中获得指定的字符串
	 * @param rs 结果集
	 * @param columnName 字段名
	 * @param jdbcType 字段类型
	 * @return
	 * @throws SQLException
	 */
	public static String getString(ResultSet rs, String columnName, int jdbcType) throws SQLException {
		String returnValue = null;
		if (jdbcType==Types.BLOB) {
			Blob blob = rs.getBlob(columnName);
			if (blob!=null) {
				returnValue = new String(blob.getBytes(1, (int)blob.length()));
			}
		} else {
			returnValue = rs.getString(columnName);
		}
		return returnValue;
	}
	
	/**
	 * 从结果集中获得指定的字符串
	 * @param rs 结果集
	 * @param column 字段索引
	 * @param charset 字符集
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	public static String getString(ResultSet rs, int column, String charset) throws UnsupportedEncodingException, SQLException {
		int jdbcType = rs.getMetaData().getColumnType(column);
		
		String returnValue = null;
		if (jdbcType==Types.BLOB) {
			// BLOB特殊处理一下
			Blob blob = rs.getBlob(column);
			if (blob!=null) {
				returnValue = new String(blob.getBytes(1, (int)blob.length()), charset);
			}
		} else {
			returnValue = rs.getString(column);
			if (returnValue!=null) {
				returnValue = new String(returnValue.getBytes(charset));
			}
		}
		return returnValue;
	}
	
	/**
	 * 从结果集中获得指定的字符串
	 * @param rs 结果集
	 * @param column 字段索引
	 * @return
	 * @throws SQLException
	 */
	public static String getString(ResultSet rs, int column) throws SQLException {
		int jdbcType = rs.getMetaData().getColumnType(column);
		
		String returnValue = null;
		if (jdbcType==Types.BLOB) {
			// BLOB特殊处理一下
			Blob blob = rs.getBlob(column);
			if (blob!=null) {
				returnValue = new String(blob.getBytes(1, (int)blob.length()));
			}
		} else {
			returnValue = rs.getString(column);
		}
		return returnValue;
	}
	
	/**
	 * 将数据库类型转化为JAVA类型
	 * @param jdbcType
	 * @return
	 */
	public static String convertJdbcType2JavaType(int jdbcType) {
//		java.sql.Types 
//		 public static final int ARRAY 2003 
//		 public static final int BINARY -2 
//		 public static final int BIT -7 
//		 public static final int BLOB 2004 
//		 public static final int BOOLEAN 16 
//		 public static final int DATALINK 70 
//		 public static final int DISTINCT 2001 
//		 public static final int JAVA_OBJECT 2000 
//		 public static final int LONGVARBINARY -4 
//		 public static final int NULL 0 
//		 public static final int OTHER 1111 
//		 public static final int REAL 7 
//		 public static final int REF 2006 
//		 public static final int ROWID -8 
//		 public static final int SQLXML 2009 
//		 public static final int STRUCT 2002 
//		 public static final int VARBINARY -3 
		
		/*
		 * 以上数据类型暂时不支持
		 */
		String returnType = null;
		if (
			jdbcType == Types.CHAR
			|| jdbcType == Types.VARCHAR
			|| jdbcType == Types.CLOB
			|| jdbcType == Types.NCHAR
			|| jdbcType == Types.NVARCHAR
			|| jdbcType == Types.NCLOB
			|| jdbcType == Types.LONGVARCHAR
			|| jdbcType == Types.LONGNVARCHAR
		) {
			// 字符串类型
			returnType = "String";
		} else if (
			jdbcType == Types.DATE
			|| jdbcType == Types.TIMESTAMP
			|| jdbcType == Types.TIME
		) {
			// 时间类型
			returnType = "Date";
			
		} else if (
			jdbcType == Types.DECIMAL
			|| jdbcType == Types.DOUBLE
			|| jdbcType == Types.FLOAT
			|| jdbcType == Types.NUMERIC
		) {
			// 数字金额类型
			returnType = "BigDecimal";
			
		} else if (
			jdbcType == Types.INTEGER
			|| jdbcType == Types.SMALLINT
			|| jdbcType == Types.TINYINT
		) {
			// 整数类型
			returnType = "Integer";
			
		} else if (
			jdbcType == Types.BIGINT
		) {
			// 长整数类型
			returnType = "Long";			
		} else {
			throw new RuntimeException("jdbcType="+jdbcType);
		}
		
		return returnType;
		
	}
	
	/**
	 * 将数据库类型转化为JAVA类型
	 * @param jdbcType
	 * @return
	 */
	public static String convertJdbcType2JavaTypeOracle(int jdbcType, int precision, int scale) {
//		java.sql.Types 
//		 public static final int ARRAY 2003 
//		 public static final int BINARY -2 
//		 public static final int BIT -7 
//		 public static final int BLOB 2004 
//		 public static final int BOOLEAN 16 
//		 public static final int DATALINK 70 
//		 public static final int DISTINCT 2001 
//		 public static final int JAVA_OBJECT 2000 
//		 public static final int LONGVARBINARY -4 
//		 public static final int NULL 0 
//		 public static final int OTHER 1111 
//		 public static final int REAL 7 
//		 public static final int REF 2006 
//		 public static final int ROWID -8 
//		 public static final int SQLXML 2009 
//		 public static final int STRUCT 2002 
//		 public static final int VARBINARY -3 
		
		/*
		 * 以上数据类型暂时不支持
		 */
		String returnType = null;
		if (
			jdbcType == Types.CHAR
			|| jdbcType == Types.VARCHAR
			|| jdbcType == Types.CLOB
			|| jdbcType == Types.NCHAR
			|| jdbcType == Types.NVARCHAR
			|| jdbcType == Types.NCLOB
			|| jdbcType == Types.LONGVARCHAR
			|| jdbcType == Types.LONGNVARCHAR
		) {
			// 字符串类型
			returnType = "String";
		} else if (
			jdbcType == Types.DATE
			|| jdbcType == Types.TIMESTAMP
			|| jdbcType == Types.TIME
		) {
			// 时间类型
			returnType = "Date";
			
		} else if (
			jdbcType == Types.DECIMAL
			|| jdbcType == Types.DOUBLE
			|| jdbcType == Types.FLOAT
			|| jdbcType == Types.NUMERIC
		) {
			
			if (scale==0) {
				// 精度是0
				if (precision<=8) {
					returnType = "Integer";
				} else {
					returnType = "Long";
				}
			} else {
				returnType = "BigDecimal";
				
			} 
				
		} else if (
			jdbcType == Types.INTEGER
			|| jdbcType == Types.SMALLINT
			|| jdbcType == Types.TINYINT
		) {
			// 整数类型
			returnType = "Integer";
			
		} else if (
			jdbcType == Types.BIGINT
		) {
			// 长整数类型
			returnType = "Long";			
		} else {
			throw new RuntimeException("jdbcType="+jdbcType);
		}
		
		return returnType;
		
	}
	
	
	public static String convertJdbcType2JavaTypeFull(int jdbcType) {
//		java.sql.Types 
//		 public static final int ARRAY 2003 
//		 public static final int BINARY -2 
//		 public static final int BIT -7 
//		 public static final int BLOB 2004 
//		 public static final int BOOLEAN 16 
//		 public static final int DATALINK 70 
//		 public static final int DISTINCT 2001 
//		 public static final int JAVA_OBJECT 2000 
//		 public static final int LONGVARBINARY -4 
//		 public static final int NULL 0 
//		 public static final int OTHER 1111 
//		 public static final int REAL 7 
//		 public static final int REF 2006 
//		 public static final int ROWID -8 
//		 public static final int SQLXML 2009 
//		 public static final int STRUCT 2002 
//		 public static final int VARBINARY -3 
		
		/*
		 * 以上数据类型暂时不支持
		 */
		String returnType = null;
		if (
			jdbcType == Types.CHAR
			|| jdbcType == Types.VARCHAR
			|| jdbcType == Types.CLOB
			|| jdbcType == Types.NCHAR
			|| jdbcType == Types.NVARCHAR
			|| jdbcType == Types.NCLOB
			|| jdbcType == Types.LONGVARCHAR
			|| jdbcType == Types.LONGNVARCHAR
		) {
			// 字符串类型
			returnType = "java.lang.String";
		} else if (
			jdbcType == Types.DATE
			|| jdbcType == Types.TIMESTAMP
			|| jdbcType == Types.TIME
		) {
			// 时间类型
			returnType = "java.util.Date";
			
		} else if (
			jdbcType == Types.DECIMAL
			|| jdbcType == Types.DOUBLE
			|| jdbcType == Types.FLOAT
			|| jdbcType == Types.NUMERIC
		) {
			// 数字金额类型
			returnType = "java.math.BigDecimal";
			
		} else if (
			jdbcType == Types.INTEGER
			|| jdbcType == Types.SMALLINT
			|| jdbcType == Types.TINYINT
		) {
			// 整数类型
			returnType = "java.lang.Integer";
			
		} else if (
			jdbcType == Types.BIGINT
		) {
			// 长整数类型
			returnType = "java.lang.Long";			
		} else {
			throw new RuntimeException("jdbcType="+jdbcType);
		}
		
		return returnType;
		
	}
	
	
	public static String convertJdbcType2SqlMapTypeOracle(int jdbcType) {
//		java.sql.Types 
//		 public static final int ARRAY 2003 
//		 public static final int BINARY -2 
//		 public static final int BIT -7 
//		 public static final int BLOB 2004 
//		 public static final int BOOLEAN 16 
//		 public static final int DATALINK 70 
//		 public static final int DISTINCT 2001 
//		 public static final int JAVA_OBJECT 2000 
//		 public static final int LONGVARBINARY -4 
//		 public static final int NULL 0 
//		 public static final int OTHER 1111 
//		 public static final int REAL 7 
//		 public static final int REF 2006 
//		 public static final int ROWID -8 
//		 public static final int SQLXML 2009 
//		 public static final int STRUCT 2002 
//		 public static final int VARBINARY -3 
		
		/*
		 * 以上数据类型暂时不支持
		 */
		String returnType = null;
		if (
			jdbcType == Types.CHAR
			|| jdbcType == Types.NCHAR
		) {
			// 字符串类型
			returnType = "CHAR";
		} else if (
			jdbcType == Types.VARCHAR
			|| jdbcType == Types.NVARCHAR
			|| jdbcType == Types.LONGVARCHAR
			|| jdbcType == Types.LONGNVARCHAR
		) {
			// 时间类型
			returnType = "VARCHAR";
		} else if (
			jdbcType == Types.CLOB
			|| jdbcType == Types.NCLOB
		) {
			// 字符串类型
			returnType = "CLOB";
		} else if (
			jdbcType == Types.DATE
		) {
			// 时间类型
			returnType = "DATE";
		} else if (
			jdbcType == Types.TIMESTAMP
		) {
			// 时间类型
			returnType = "TIMESTAMP";
			
		} else if (
			jdbcType == Types.TIME
		) {
			// 时间类型
			returnType = "TIME";
			
		} else if (
			jdbcType == Types.DECIMAL
			|| jdbcType == Types.DOUBLE
			|| jdbcType == Types.FLOAT
			|| jdbcType == Types.NUMERIC
		) {
			// 数字金额类型
			returnType = "NUMERIC";
			
		} else if (
			jdbcType == Types.INTEGER
			|| jdbcType == Types.SMALLINT
			|| jdbcType == Types.TINYINT
		) {
			// 整数类型
			returnType = "INTEGER";
			
		} else if (
			jdbcType == Types.BIGINT
		) {
			// 长整数类型
			returnType = "BIGINT";			
		} else {
			throw new RuntimeException("jdbcType="+jdbcType);
		}
		
		return returnType;
		
	}

}
