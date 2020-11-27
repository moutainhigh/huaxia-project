package com.huaxia.opas.dao.common.sqlmap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.web.servlet.ResultHandler;

import com.huateng.neofp.service.sqlmap.SqlMap;
import com.huateng.neofp.service.sqlmap.SqlMapSession;

/**
 * SQL语句执行方式
 * 
 * @Author wang.jf
 * @Version 1.0 Created at 2016-4-18 下午4:36:42
 */
public class SqlExecutor implements SqlMap {

	private static final Logger log = LoggerFactory.getLogger(SqlExecutor.class);

	@Resource(name = "sqlMap")
	private SqlMap sqlMap;

	@Resource(name = "dataSource")
	private DataSource dataSource;

	public SqlMap getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(SqlMap sqlMap) {
		this.sqlMap = sqlMap;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return DataSourceUtils.doGetConnection(dataSource);
	}

	/**
	 * 关闭资源
	 * 
	 * @param rs
	 */
	protected void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				DataSourceUtils.doReleaseConnection(connection, dataSource);
			} catch (SQLException e) {
				log.error("", e);
			}
		}
	}

	/**
	 * @param sqls
	 *            oracle.sql.Clob类型转换成String类型
	 * @return
	 * @throws SQLException
	 */
	public String ClobToString(Clob clob) throws SQLException, IOException {
		Reader is = null;
		BufferedReader br = null;
		String reString = "";
		try {
			is = clob.getCharacterStream();// 得到流
			br = new BufferedReader(is);
			String s = br.readLine();
			StringBuilder sb = new StringBuilder();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();
		} catch (Exception e) {
			log.error("", e);
		} finally {
			if (br != null)
				br.close();

		}
		return reString;
	}

	/**
	 * 关闭资源
	 * 
	 * @param rs
	 */
	protected void closeResult(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

	/**
	 * 关闭资源
	 * 
	 * @param rs
	 */
	protected void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

	/**
	 * @param sqls
	 *            多条将要被执行的SQL
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public Map<String, LinkedList<Object>> queryForMap(List<String> sqls) throws SQLException, IOException {
		Map<String, LinkedList<Object>> result = new HashMap<String, LinkedList<Object>>();

		java.sql.Connection conn = null;

		LinkedList<Object> values = null;

		if (sqls == null || sqls.size() == 0) {
			return result;
		}

		try {
			conn = getConnection();

			PreparedStatement ps = null;
			ResultSet rs = null;
			for (String sql : sqls) {
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					int num = rsmd.getColumnCount();
					String key = null;
					Object value = null;
					boolean flag = false;
					while (rs.next()) {
						flag = true;
						for (int i = 1; i <= num; i++) {
							key = rsmd.getColumnName(i);
							value = rs.getObject(key);
							if (result.keySet().contains(key)) {
								if (value instanceof String) {
									for (String s : ("△☆" + value.toString() + "☆△").split("☆")) {
										if (!s.equals("△")) {
											result.get(key).add(s);
										}
									}
								} else if (value instanceof oracle.sql.CLOB) {
									result.get(key).add(ClobToString((Clob) value));
								} else if (value instanceof oracle.sql.TIMESTAMP) {
									result.get(key).add((java.util.Date) (((oracle.sql.TIMESTAMP) value).dateValue()));
								} else {
									result.get(key).add(value);
								}
							} else {
								values = new LinkedList<Object>();
								if (value instanceof String) {
									for (String s : ("△☆" + value.toString() + "☆△").split("☆")) {
										if (!s.equals("△")) {
											values.add(s);
										}
									}
								} else if (value instanceof oracle.sql.CLOB) {
									values.add(ClobToString((Clob) value));
								} else if (value instanceof oracle.sql.TIMESTAMP) {
									values.add((java.util.Date) (((oracle.sql.TIMESTAMP) value).dateValue()));
								} else {
									values.add(value);
								}
								result.put(key, values);
							}
						}
					}
					if (!flag) {
						for (int i = 1; i <= num; i++) {
							key = rsmd.getColumnName(i);
							result.put(key, new LinkedList<Object>());
						}
					}
				} finally {
					closeResult(rs);
					closeStatement(ps);
				}
			}
		} finally {
			closeConnection(conn);
		}
		return result;
	}

	/**
	 * 批量开卡数据抽取
	 * 
	 * @param sqls
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public LinkedList<Map<String, Object>> queryBatch(String sql) throws SQLException, IOException {
		LinkedList<Map<String, Object>> rows = new LinkedList<Map<String, Object>>();
		Map<String, Object> row = null;
		java.sql.Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (sql == null || sql.trim().length() == 0) {
			return rows;
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			conn = getConnection();

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			int num = rs.getMetaData().getColumnCount();
			String key = null;
			Object value = null;
			while (rs.next()) {
				row = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= num; i++) {
					key = rs.getMetaData().getColumnName(i);
					value = rs.getObject(key);
					if (value != null) {
						if (value instanceof String) {
							if (value != null && value.toString().trim().length() != 0) {
								row.put(key, value.toString().trim());
							} else {
								row.put(key, "");
							}
						} else if (value instanceof oracle.sql.CLOB) {
							row.put(key, ClobToString((Clob) value));
						} else if (value instanceof oracle.sql.DATE) {
							String dateStr = sdf.format((java.util.Date) (((oracle.sql.DATE) value).dateValue()));
							if (("00021130").equals(dateStr)) {
								dateStr = "00000000";
							}
							row.put(key, dateStr);
						} else if (value instanceof java.sql.Timestamp) {
							String dateStr = sdf.format((java.sql.Timestamp) value);
							if (("00021130").equals(dateStr)) {
								dateStr = "00000000";
							}
							row.put(key, dateStr);
						} else if (value instanceof oracle.sql.TIMESTAMP) {
							String dateStr = sdf.format((java.util.Date) (((oracle.sql.TIMESTAMP) value).dateValue()));
							if (("00021130").equals(dateStr)) {
								dateStr = "00000000";
							}
							row.put(key, dateStr);
						} else {
							row.put(key, value.toString().trim());
						}
					} else {
						row.put(key, "");
					}
				}
				rows.add(row);
			}
		} finally {
			closeResult(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return rows;
	}

	/**
	 * 执行无参数insert语句
	 * 
	 * @param sqls1
	 *            含有Clob字段的insert语句
	 * @param sqls2
	 *            不含Clob字段的insert语句
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public void batch(List<LinkedList<String>> sqls1, List<String> sqls2) throws SQLException, IOException {
		java.sql.Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 不控制数据库事务
			conn.setAutoCommit(false);

			if (sqls1 != null && sqls1.size() != 0) {
				for (LinkedList<String> sqls : sqls1) {

					try {
						ps = conn.prepareStatement(sqls.get(0));
						ps.executeUpdate();
					} catch (Exception e) {
						log.error(sqls.get(0), e);
						// 忽略异常
					} finally {
						closeStatement(ps);
					}

					try {
						ps = conn.prepareStatement(sqls.get(1));
						ps.executeUpdate();
					} catch (Exception e) {
						log.error(sqls.get(1), e);
						// 忽略异常
					} finally {
						closeStatement(ps);
					}

					try {
						ps = conn.prepareStatement(sqls.get(2));
						rs = ps.executeQuery();
						while (rs.next()) {
							oracle.sql.CLOB clob = null;
							BufferedWriter out = null;
							for (int i = 1; i < sqls.size() - 2; i++) {
								clob = (oracle.sql.CLOB) rs.getClob(i);
								out = new BufferedWriter(clob.getCharacterOutputStream());
								String content = sqls.get(2 + i);
								out.write(content, 0, content.length());
								out.close();
							}
						}
					} catch (Exception e) {
						log.error(sqls.get(2), e);
						// 忽略异常
					} finally {
						closeResult(rs);
						closeStatement(ps);
					}
				}
			}
			if (sqls2 != null && sqls2.size() != 0) {
				for (String sql : sqls2) {
					try {
						ps = conn.prepareStatement(sql);
						ps.execute();
					} catch (Exception e) {
						log.error(sql, e);
					} finally {
						closeStatement(ps);
					}
				}
			}
			conn.commit();
		} catch (Exception e1) {
			conn.rollback();
			throw new RuntimeException(e1);
		} finally {
			closeConnection(conn);
		}
	}

	/**
	 * 执行多条SQL
	 * 
	 * @throws SQLException
	 */
	public boolean execSQL(LinkedList<String> sqls) throws SQLException {

		java.sql.Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = true;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			for (String sql : sqls) {
				try {
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
				} catch (SQLException e1) {
					flag = false;
					log.error("", e1);
					throw e1;
				} finally {
					closeStatement(ps);
				}
			}
			conn.commit();
		} catch (SQLException e1) {
			conn.rollback();
			throw e1;
		} catch (Exception e3) {
			conn.rollback();
			throw new RuntimeException(e3);
		} finally {
			closeConnection(conn);
		}
		return flag;
	}

	/**
	 * 执行批量SQL
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int[] excelBatch(String sql, Object[][] obj) throws SQLException {
		int[] returnInt = null;

		java.sql.Connection conn = null;

		try {
			conn = getConnection();

			returnInt = excelBatch(conn, sql, obj);

			conn.commit();
		} catch (SQLException e1) {
			conn.rollback();
			throw e1;
		} catch (Exception e3) {
			conn.rollback();
			throw new RuntimeException(e3);
		} finally {
			closeConnection(conn);
		}

		return returnInt;
	}

	public int[] excelBatch(String insertSql, String deleteSql, Object[][] params, int col) throws SQLException {

		java.sql.Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			if (params != null && params.length != 0) {
				ps = connection.prepareStatement(deleteSql);
				for (int i = 0; i < params.length; i++) {
					doExceBatch(ps, params[i][col]);
					ps.addBatch();
				}
				ps.executeBatch();
				connection.commit();
				ps.close();
			}

			if (params != null && params.length != 0) {
				ps = connection.prepareStatement(insertSql);
				for (int i = 0; i < params.length; i++) {
					doExceBatch(ps, params[i]);
					ps.addBatch();
				}
				ps.executeBatch();
				connection.commit();
				ps.close();
			}

		} catch (SQLException sqlexception) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("", e);
			}
			throw sqlexception;
		} catch (Exception e3) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("", e);
			}
			throw new RuntimeException(e3);
		} finally {
			closeStatement(ps);
			closeConnection(connection);
		}
		return null;
	}

	public int[] excelBatch(String insertSql, String deleteSql, Object[][] params, int key1, int key2)
			throws SQLException {

		java.sql.Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			if (params != null && params.length != 0) {
				ps = connection.prepareStatement(deleteSql);
				for (int i = 0; i < params.length; i++) {
					doExceBatch(ps, params[i][key1], params[i][key2]);
					ps.addBatch();
				}
				ps.executeBatch();
				connection.commit();
				ps.close();
			}

			if (params != null && params.length != 0) {
				ps = connection.prepareStatement(insertSql);
				for (int i = 0; i < params.length; i++) {
					doExceBatch(ps, params[i]);
					ps.addBatch();
				}
				ps.executeBatch();
				connection.commit();
				ps.close();
			}

		} catch (SQLException sqlexception) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("", e);
			}
			throw sqlexception;
		} catch (Exception e3) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("", e);
			}
			throw new RuntimeException(e3);
		} finally {
			closeStatement(ps);
			closeConnection(connection);
		}
		return null;
	}

	private int[] excelBatch(Connection connection, String sql, Object[][] params) throws SQLException {
		PreparedStatement preparedstatement = null;
		try {
			preparedstatement = connection.prepareStatement(sql);
			if (params != null && params.length != 0) {
				for (int i = 0; i < params.length; i++) {
					doExceBatch(preparedstatement, params[i]);
					preparedstatement.addBatch();
				}
			}

			return preparedstatement.executeBatch();

		} finally {
			closeStatement(preparedstatement);
		}

	}

	protected void doExceBatch(PreparedStatement preparedstatement, Object objects[]) throws SQLException {
		if (objects == null)
			return;
		for (int i = 0; i < objects.length; i++)
			if (objects[i] != null) {
				if ((objects[i] instanceof java.lang.String)) {

					preparedstatement.setCharacterStream(i + 1,
							new StringReader(((String) objects[i]).replaceAll(" $", " ")),
							((String) objects[i]).length());

				} else if (objects[i] instanceof java.sql.Date) {

					preparedstatement.setDate(i + 1, (java.sql.Date) objects[i]);

				} else if (objects[i] instanceof java.util.Date) {

					preparedstatement.setDate(i + 1, new java.sql.Date(((java.util.Date) objects[i]).getTime()));

				} else {

					preparedstatement.setObject(i + 1, objects[i]);
				}
			} else {
				preparedstatement.setNull(i + 1, Types.NULL);
			}

	}

	@SuppressWarnings("unused")
	protected void doExceBatch(PreparedStatement preparedstatement, Object objects) throws SQLException {
		if (objects == null)
			return;

		if (objects != null) {
			if ((objects instanceof String) && ((String) objects).length() > 1024) {

				preparedstatement.setCharacterStream(1, new StringReader((String) objects),
						((String) objects).length());
			} else if (objects instanceof java.sql.Date) {

				preparedstatement.setDate(1, (java.sql.Date) objects);

			} else if (objects instanceof java.util.Date) {

				preparedstatement.setDate(1, new java.sql.Date(((java.util.Date) objects).getTime()));

			} else {

				preparedstatement.setObject(1, objects);
			}
		} else {
			preparedstatement.setNull(1, Types.REF);
		}

	}

	protected void doExceBatch(PreparedStatement preparedstatement, Object object1, Object object2)
			throws SQLException {
		if (object1 == null) {
			return;
		} else {
			if ((object1 instanceof String) && ((String) object1).length() > 1024) {

				preparedstatement.setCharacterStream(1, new StringReader((String) object1),
						((String) object1).length());
			} else if (object1 instanceof java.sql.Date) {

				preparedstatement.setDate(1, (java.sql.Date) object1);

			} else if (object1 instanceof java.util.Date) {

				preparedstatement.setDate(1, new java.sql.Date(((java.util.Date) object1).getTime()));

			} else {

				preparedstatement.setObject(1, object1);
			}
		}
		if (object2 == null) {
			return;
		} else {
			if ((object2 instanceof String) && ((String) object2).length() > 1024) {

				preparedstatement.setCharacterStream(2, new StringReader((String) object2),
						((String) object2).length());
			} else if (object2 instanceof java.sql.Date) {

				preparedstatement.setDate(2, (java.sql.Date) object2);

			} else if (object2 instanceof java.util.Date) {

				preparedstatement.setDate(2, new java.sql.Date(((java.util.Date) object2).getTime()));

			} else {

				preparedstatement.setObject(2, object2);
			}
		}

	}

	public int updateTableStatue(String sql) throws Exception {
		int returnInt = 0;
		java.sql.Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement(sql);

			returnInt = ps.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return returnInt;

	}

	public List<LinkedHashMap<String, Object>> getTbDataToList(String sql) throws SQLException {
		List<LinkedHashMap<String, Object>> objList = new ArrayList<LinkedHashMap<String, Object>>();
		LinkedHashMap<String, Object> mapRow = null;

		java.sql.Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				mapRow = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnNum; i++) {
					mapRow.put(rsmd.getColumnName(i), rs.getString(i));
				}
				objList.add(mapRow);
				// mapRow.clear();
			}
		} finally {
			closeResult(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return objList;
	}

	public List<Map<String, Object>> getTbDataToListByMap(String sql) throws SQLException {
		List<Map<String, Object>> objList = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapRow = null;

		java.sql.Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				mapRow = new HashMap<String, Object>();
				for (int i = 1; i <= columnNum; i++) {
					mapRow.put(rsmd.getColumnName(i), rs.getString(i));
				}
				objList.add(mapRow);
				// mapRow.clear();
			}
		} finally {
			closeResult(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return objList;
	}

	public Map<String, Map<String, Object>> getTableInfo(String sql) throws SQLException {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		Map<String, Object> colInfo = null;

		java.sql.Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				colInfo = new HashMap<String, Object>();
				colInfo.put("ColumnCount", rsmd.getColumnCount());
				colInfo.put("ColumnType", rsmd.getColumnType(i));
				colInfo.put("ColumnClassName", rsmd.getColumnClassName(i));
				colInfo.put("ColumnDisplaySize", rsmd.getColumnDisplaySize(i));
				colInfo.put("Precision", rsmd.getPrecision(i));
				colInfo.put("Scale", rsmd.getScale(i));
				colInfo.put("ColumnName", rsmd.getColumnName(i));
				map.put(rsmd.getColumnName(i).toUpperCase(), colInfo);
			}
		} finally {
			closeResult(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return map;
	}

	public LinkedList<Map<String, Object>> getTableInfoByList(String sql) throws SQLException {
		LinkedList<Map<String, Object>> linkList = new LinkedList<Map<String, Object>>();
		Map<String, Object> colInfo = null;

		java.sql.Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				colInfo = new HashMap<String, Object>();
				colInfo.put("ColumnCount", rsmd.getColumnCount());
				colInfo.put("ColumnType", rsmd.getColumnType(i));
				colInfo.put("ColumnClassName", rsmd.getColumnClassName(i));
				colInfo.put("ColumnDisplaySize", rsmd.getColumnDisplaySize(i));
				colInfo.put("Precision", rsmd.getPrecision(i));
				colInfo.put("Scale", rsmd.getScale(i));
				colInfo.put("ColumnName", rsmd.getColumnName(i));
				linkList.add(colInfo);
			}
		} finally {
			closeResult(rs);
			closeStatement(ps);
			closeConnection(conn);
		}

		return linkList;
	}

	public int[] batch(String s, Object aobj[][]) throws SQLException {
		java.sql.Connection connection = null;
		try {
			connection = getConnection();

			int[] returnInt = batch(connection, s, aobj);
			connection.commit();
			return returnInt;

		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} catch (Exception e) {
			connection.rollback();
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
	}

	private int[] batch(Connection connection, String sql, Object[][] params) throws SQLException {
		PreparedStatement preparedstatement = null;
		try {
			preparedstatement = connection.prepareStatement(sql);
			if (params != null && params.length != 0) {
				for (int i = 0; i < params.length; i++) {
					doBatch(preparedstatement, params[i]);
					preparedstatement.addBatch();
				}
			}

			return preparedstatement.executeBatch();
		} finally {
			closeStatement(preparedstatement);
		}

	}

	protected void doBatch(PreparedStatement preparedstatement, Object objects[]) throws SQLException {
		if (objects == null)
			return;
		for (int i = 0; i < objects.length; i++)
			if (objects[i] != null) {
				if ((objects[i] instanceof String) && ((String) objects[i]).length() > 1024)
					preparedstatement.setCharacterStream(i + 1, new StringReader((String) objects[i]),
							((String) objects[i]).length());
				else
					preparedstatement.setObject(i + 1, objects[i]);
			} else {
				preparedstatement.setNull(i + 1, Types.REF);
			}

	}

	/**
	 * 批处理金融制裁名单
	 * 
	 * @param insertSql
	 * @param deleteSql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int[] excelBatch(String insertSql, String deleteSql, Object[][] params, boolean endFlag)
			throws SQLException {

		java.sql.Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			if (endFlag) {
				// 删除表数据
				ps = connection.prepareStatement(deleteSql);
				ps.execute();
				ps.close();
			}

			String strId = "";
			// 插入表数据
			if (params != null && params.length != 0) {
				oracle.sql.CLOB clob = null;
				BufferedWriter out = null;
				for (int i = 0; i < params.length; i++) {
					rs = null;
					ps = connection.prepareStatement(insertSql);
					// 插入主见
					UUID uuid = UUID.randomUUID();
					strId = uuid.toString();
					doExceBatchUUID(strId, ps, params[i]);
					// ps.addBatch();
					ps.executeUpdate();
					closeStatement(ps);

					ps = connection.prepareStatement(
							"update OPAS_BIZ_BLACK_LIST set FINA_USER_NAME=EMPTY_CLOB()  where FINA_USER_ID = '" + strId
									+ "'");
					ps.executeUpdate();
					closeStatement(ps);

					ps = connection
							.prepareStatement("select FINA_USER_NAME from OPAS_BIZ_BLACK_LIST where FINA_USER_ID= '"
									+ strId + "' for update");
					rs = ps.executeQuery();
					while (rs.next()) {
						clob = null;
						out = null;
						clob = (oracle.sql.CLOB) rs.getClob("FINA_USER_NAME");
						out = new BufferedWriter(clob.getCharacterOutputStream());
						if (params[i][4] != null) {
							String content = ((String) (params[i][4]));
							out.write(content, 0, content.length());
						}

						out.close();
					}
					closeResult(rs);
					closeStatement(ps);
				}
				closeStatement(ps);
				connection.commit();
				// ps.close();
			}
			closeStatement(ps);
		} catch (SQLException sqlexception) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("", e);
			}
			throw sqlexception;
		} catch (Exception e3) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("", e);
			}
			throw new RuntimeException(e3);
		} finally {
			closeResult(rs);
			closeStatement(ps);
			closeConnection(connection);
		}
		return null;
	}

	protected void doExceBatchUUID(String strId, PreparedStatement preparedstatement, Object objects[])
			throws SQLException {
		if (objects == null)
			return;
		// 插入主见
		// UUID uuid = UUID.randomUUID();
		// String strId = uuid.toString();

		preparedstatement.setCharacterStream(1, new StringReader(strId.replaceAll(" $", " ")), strId.length());
		// 插入内容
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] != null) {
				if (i == 4) {
					continue;
				}

				if ((objects[i] instanceof java.lang.String)) {

					preparedstatement.setCharacterStream(i + ((i > 4) ? 1 : 2),
							new StringReader(((String) objects[i]).replaceAll(" $", " ")),
							((String) objects[i]).length());

				} else if (objects[i] instanceof java.sql.Date) {

					preparedstatement.setDate(i + ((i > 4) ? 1 : 2), (java.sql.Date) objects[i]);

				} else if (objects[i] instanceof java.util.Date) {

					preparedstatement.setDate(i + ((i > 4) ? 1 : 2),
							new java.sql.Date(((java.util.Date) objects[i]).getTime()));

				} else {

					preparedstatement.setObject(i + ((i > 4) ? 1 : 2), objects[i]);
				}
			} else {
				preparedstatement.setNull(i + ((i > 4) ? 1 : 2), Types.NULL);
			}

		}
	}

	public int delete(String s) throws DataAccessException {
		assertDelegate();
		return sqlMap.delete(s);
	}

	public int delete(String s, Object obj) throws DataAccessException {
		assertDelegate();
		return sqlMap.delete(s, obj);
	}

	public void delete(String s, Object obj, int i) throws DataAccessException {
		assertDelegate();
		sqlMap.delete(s, obj, i);
	}

	public int insert(String s) throws DataAccessException {
		assertDelegate();
		return sqlMap.insert(s);
	}

	public int insert(String s, Object obj) throws DataAccessException {
		assertDelegate();
		return sqlMap.insert(s, obj);
	}

	public <T> List<T> queryForList(String s) throws DataAccessException {
		assertDelegate();
		return sqlMap.queryForList(s);
	}

	public <T> List<T> queryForList(String s, Object obj) throws DataAccessException {
		assertDelegate();
		return sqlMap.queryForList(s, obj);
	}

	public <T> List<T> queryForList(String s, Object obj, int i, int j) throws DataAccessException {
		assertDelegate();
		return sqlMap.queryForList(s, obj, i, j);
	}

	public <T> T queryForObject(String s) throws DataAccessException {
		assertDelegate();
		return sqlMap.queryForObject(s);
	}

	public <T> T queryForObject(String s, Object obj) throws DataAccessException {
		assertDelegate();
		return sqlMap.queryForObject(s, obj);
	}

	public void queryWithRowHandler(String s, Object obj, ResultHandler resulthandler) throws DataAccessException {
		assertDelegate();
		sqlMap.queryWithRowHandler(s, obj, (org.apache.ibatis.session.ResultHandler) resulthandler);
	}

	public void queryWithRowHandler(String s, Object obj, int i, int j, ResultHandler resulthandler)
			throws DataAccessException {
		assertDelegate();
		sqlMap.queryWithRowHandler(s, obj, i, j, (org.apache.ibatis.session.ResultHandler) resulthandler);
	}

	public SqlMapSession startSession(boolean flag) throws DataAccessException {
		assertDelegate();
		return sqlMap.startSession(flag);
	}

	public int update(String s) throws DataAccessException {
		assertDelegate();
		return sqlMap.update(s);
	}

	public int update(String s, Object obj) throws DataAccessException {
		assertDelegate();
		return sqlMap.update(s, obj);
	}

	public void update(String s, Object obj, int i) throws DataAccessException {
		assertDelegate();
		sqlMap.update(s, obj);
	}

	private void assertDelegate() {
		if (sqlMap == null)
			throw new UnsupportedOperationException("because of absence of delegate sqlmap object");
	}

	@Override
	public void queryWithRowHandler(String arg0, Object arg1, org.apache.ibatis.session.ResultHandler arg2)
			throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void queryWithRowHandler(String arg0, Object arg1, int arg2, int arg3,
			org.apache.ibatis.session.ResultHandler arg4) throws DataAccessException {
		// TODO Auto-generated method stub

	}
}
