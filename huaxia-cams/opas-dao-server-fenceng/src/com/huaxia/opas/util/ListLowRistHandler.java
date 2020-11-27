package com.huaxia.opas.util;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.huaxia.opas.domain.allot.AllotAppId;
import com.huaxia.opas.domain.sysparm.LowRiskCustomers;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
public class ListLowRistHandler extends BaseTypeHandler{
	private final static Logger logger = LoggerFactory.getLogger(ListLowRistHandler.class);

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
					List<LowRiskCustomers> list=(ArrayList<LowRiskCustomers>) object;
					conn = JdbcUtil.getConnection();
//				    Array array=getArray(conn,"opas_low_risk_List_type","opas_low_risk_list_t",list);
				    Array array=getArray(conn,"OPAS_LOW_RISK_LIST_TYPE","OPAS_LOW_RISK_LIST_T",list);
				    ps.setArray(i, array);
				}
			} catch (Exception e) {
				logger.error("ListLowRistHandler [{}]", e.getMessage());
			}finally{
				if(null!=conn){
					conn.close();
				}
			}
	}
	
	private ARRAY getArray(Connection conn,String OracleObject,String OracleList,List<LowRiskCustomers> listData)throws Exception{
		ARRAY array=null;
		ArrayDescriptor desc=ArrayDescriptor.createDescriptor(OracleList,conn);
		STRUCT[] structs=new STRUCT[listData.size()];
		if(listData!=null&&listData.size()>0){
			StructDescriptor structDesc= new StructDescriptor(OracleObject,conn);
			for(int i=0;i<listData.size();i++){
				Object[] result={
								 listData.get(i).getCrtUser(),
								 listData.get(i).getCusName(),
								 listData.get(i).getCredNo(),
								 listData.get(i).getMobile(),
								 listData.get(i).getCompanyName(),
								 listData.get(i).getListType(),
								 listData.get(i).getSTATUS()
								};
				structs[i]=new STRUCT(structDesc, conn, result);
			}
			array=new ARRAY(desc,conn,structs);
		}else{
			array=new ARRAY(desc,conn,structs);
		}
		return array;
	}
}
