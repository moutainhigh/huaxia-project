package com.huaxia.opas.util;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.huaxia.opas.domain.allot.AllotAppId;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
public class ListHandler extends BaseTypeHandler{

	@Override
	public Object getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object object, JdbcType jdbcType) throws SQLException {
		Connection conn=null;
		
			try {
				if(null!=object){
					List<AllotAppId> list=(ArrayList<AllotAppId>) object;
					conn = JdbcUtil.getConnection();
				    Array array=getArray(conn,"ALLOTLIST","ALLOT_LIST",list);
				    ps.setArray(i, array);
				}
			} catch (Exception e) {
			}finally{
				if(null!=conn){
					conn.close();
				}
			}
	}
	
	private ARRAY getArray(Connection conn,String OracleObject,String OracleList,List<AllotAppId> listData)throws Exception{
		ARRAY array=null;
		ArrayDescriptor desc=ArrayDescriptor.createDescriptor(OracleList,conn);
		STRUCT[] structs=new STRUCT[listData.size()];
		if(listData!=null&&listData.size()>0){
			StructDescriptor structDesc= new StructDescriptor(OracleObject,conn);
			for(int i=0;i<listData.size();i++){
				Object[] result={listData.get(i).getAppId()};
				structs[i]=new STRUCT(structDesc, conn, result);
			}
			array=new ARRAY(desc,conn,structs);
		}else{
			array=new ARRAY(desc,conn,structs);
		}
		return array;
	}

}
