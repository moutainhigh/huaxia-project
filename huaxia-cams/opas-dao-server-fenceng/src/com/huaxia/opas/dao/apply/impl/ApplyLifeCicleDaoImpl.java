package com.huaxia.opas.dao.apply.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.compare.RevCompInfo;

public class ApplyLifeCicleDaoImpl extends AbstractDAO implements ApplyLifeCicleDao{
	private static final long serialVersionUID = -6740782036190523028L;
	private static final String NAMESPACES = "ApplyLifeCicle.";
	
	private static final Logger log = LoggerFactory.getLogger(ApplyLifeCicleDaoImpl.class);
	
	@Override
	public int addApplyLifeCicle(ApplyLifeCicle a) throws Exception{
		try{
			return getSqlMap().insert(NAMESPACES + "insert", a);
		}catch (Exception e){
			throw e;
		}
		
	}
	
	@Override
	public int insertApplyLifeBatch(List<ApplyLifeCicle> cicleList) throws Exception{
		return getSqlMap().insert(NAMESPACES + "insertApplyLifeBatch", cicleList);
	}
	
	@Override
	public int selfInsert(List<ApplyLifeCicle> cicleList) throws Exception{
		int result=0;
		//配置文件名称
		String resource="config/dao-config.xml";
		//通过mybatis包中的resources对象轻松获取到配置文件
		Reader reader=Resources.getResourceAsReader(resource);
		//通过SqlSessionFactoryBuilder创建
		SqlSessionFactory sqlSessionFactroy=new SqlSessionFactoryBuilder().build(reader);
		//xml中ID
		String mybatisSQLId="insertApplyLifeBatch";
		SqlSession sqlSession=null;
		//新获取一个模式为BATCH 自动提交为false 的sqlSession
		//如果自动提交设置为true 将无法控制提交的条数 变成最后统一提交  可能导致内存溢出(获取实例)  
		sqlSession=sqlSessionFactroy.openSession(ExecutorType.BATCH,false);
		try {
			//从hostproperties获取提交件数 
			//Map<String, String> streamMap = CommonProperties.getParoperMap();
			//每次提交的记录 一次1000条
			int commitCountEveryTime=1000;
			//int commitCountEveryTime=Integer.parseInt(streamMap.get("commitCountEveryTime").toString());
			//需要提交的次数  
			int	commitCount=(int)Math.ceil(cicleList.size()/(double)commitCountEveryTime);
			//临时数据集合  
			List<ApplyLifeCicle> tempList=new ArrayList<ApplyLifeCicle>(commitCountEveryTime);
			int start ,stop;
			Long startTime=System.currentTimeMillis();
			//循环提交的次数
			for(int i=0;i<commitCount;i++){
				//清空
				tempList.clear();
				//开始标
				start=i*commitCountEveryTime;
				//每批最后一个下标
				stop=Math.min(i*commitCountEveryTime+commitCountEveryTime-1,cicleList.size()-1);
				//循环将数据放进临时集合
				for(int j=start;j<=stop;j++){
					tempList.add(cicleList.get(j));
				}
				//插入生命周期表
				sqlSession.insert(NAMESPACES+mybatisSQLId,tempList);
				//手动提交   提交后无法回滚  
				sqlSession.commit();
				//清理缓存 ,防止溢出
				sqlSession.clearCache();
				result=1;
				log.info("已经批量分批插入生命周期表"+(stop+1)+"条");
			}
			Long endTime=System.currentTimeMillis();
			log.debug("selfInsert插入完成耗时"+(endTime-startTime)+"毫秒");
			//result=applyLifeCicleDao.selfInsert(cicleList);
		} catch (Exception e) {
			log.error("selfInsert--error"+e);
			//没有提交的数据可以回滚
			sqlSession.rollback();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return  result;
	}
	
	@Override
	public String queryOperater(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "queryOperater",map);
	}
	
}
