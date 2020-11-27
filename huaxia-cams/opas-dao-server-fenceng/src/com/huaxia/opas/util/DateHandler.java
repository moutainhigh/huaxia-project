package com.huaxia.opas.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
/**
 * mybatis date日期取出来少一天
 * @author andong
 *
 */
public class DateHandler extends BaseTypeHandler{

	@Override
	public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
		java.sql.Date date = rs.getDate(columnName, Calendar.getInstance());
	    if (date != null) {
	      return new java.util.Date(date.getTime());
	    }
	    return null;
	}

	@Override
	public Object getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
