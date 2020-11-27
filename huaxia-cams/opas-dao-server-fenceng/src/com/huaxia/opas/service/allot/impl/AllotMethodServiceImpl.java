package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.huateng.neofp.core.CoreException;
import com.huateng.neofp.core.CoreRuntimeException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.dao.allot.AllotApplyDao;
import com.huaxia.opas.dao.allot.AllotCommonDao;
import com.huaxia.opas.dao.allot.AllotQueueDao;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.apply.ApplyRemarkDao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.common.sqlmap.SqlExecutor;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.allot.AllotAppId;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.service.allot.AllotMethodService;
import com.huaxia.opas.service.workflow.TopbpmService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.JdbcUtil;
import com.huaxia.opas.util.RedisLock;
import com.huaxia.opas.util.RedisPool;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.UUIDGenerator;

import redis.clients.jedis.Jedis;
import sun.rmi.runtime.Log;
/**
 * 分件 
 *
 * @author wangdebin
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-16 修复标准卡回收到组生命周期记录不全问题
 *  2017-10-27 修复自动分件组到人批量记录历史报错问题
 *  2017-10-27 添加jdbc批量记录历史
 *  2017-11-15 添加自动分件标准卡征信环节池到风险队列、组描述
 *  2017-12-20 征审合一件审批环节分件和转移问题
 * ------------------------------------------------
 */
public class AllotMethodServiceImpl extends AbstractDAO implements AllotMethodService, Serializable {

	private static final long serialVersionUID = 3834360024753600901L;

	private static final Logger logger = LoggerFactory.getLogger(AllotMethodServiceImpl.class);
	
	@Resource(name = "allotCommonDao")
	private AllotCommonDao allotCommonDao;
	
	@Resource(name = "allotApplyDao")
	private AllotApplyDao allotApplyDao;
	
	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDao;
	
	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyHisDao;
	
	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;
	
	@Resource(name = "allotQueueDao")
	private AllotQueueDao allotQueueDao;
	
	@Resource(name = "applyRemarkDao")
	private ApplyRemarkDao applyRemarkDao;
	
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;
	
	@Resource(name = "sqlExecutor")
	private SqlExecutor sqlExecutor;
	
	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;
	
	public AllotCommonDao getAllotCommonDao() {
		return allotCommonDao;
	}
	public void setAllotCommonDao(AllotCommonDao allotCommonDao) {
		this.allotCommonDao = allotCommonDao;
	}
	
	public AllotApplyDao getAllotApplyDao() {
		return allotApplyDao;
	}
	public void setAllotApplyDao(AllotApplyDao allotApplyDao) {
		this.allotApplyDao = allotApplyDao;
	}
	
	/**
	 * @Description:  申请件单件转移或回收
	 * flag生命周期记录标识  1 挂起  2 释放  3 备注  4 转移  5 回收  6 手动分件  7批量调工作流   8 自动分件池到组   9 自动分件组到人  10 自动分件征信池到征信队列
	 * @author 王德彬
	 * @version V1.1
	 * @param Map
	 */
	@Override
	public int updateMethod(Map map)  {
		int result=0;
		String appId="";
		//Client client=null;
		try{
			AllotApply app=new AllotApply();
			String flag=(String) map.get("flag")!=null?(String) map.get("flag"):"";
			List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
			if("8".equals(flag)){
				allotApplyList=(List<AllotApply>) map.get("allotApplyList");
			}else{
				app=(AllotApply) map.get("app");
			}
			List<String> lifeList=new ArrayList<String>();
			lifeList=(List<String>) map.get("lifeList");
			appId=lifeList.get(0);
			String diaoliu=StringUtils.trimToEmpty((String)map.get("diaoliu"));
			//当前节点
			String secondNode=StringUtils.trimToEmpty((String)map.get("secondNode"));
			//当前环节
			String currNode=StringUtils.trimToEmpty((String)map.get("currNode"));
			//当前易达金标识
			String ydjFlag=StringUtils.trimToEmpty((String)map.get("ydjFlag"));
			//转移方式(组员之间转移)
			String wayId=StringUtils.trimToEmpty((String)map.get("wayId"));
			//回收节点位置 池  队列  组
			String recoveryNode=StringUtils.trimToEmpty((String)map.get("recoveryNode"));
			int ap=0;
			//审批环节征审合一件分件或转移到普通审批员  调工作流跑流程
			if("03".equals(currNode)&&"3".equals(secondNode)&&(("4".equals(flag)&&"2".equals(wayId))||"6".equals(flag)||"9".equals(flag))&&(!"".equals(ydjFlag))){
					//若当前审批人不是征审合一角色
					ap = apUserDao.queryCurrUserCode(app.getCurrUser());
					if("2".equals(app.getCheck_meuoFlag())&&(ap==0)){
						//client=getClient(client);
						app.setCheck_meuoFlag("1");
						app.setZshyInnerCheck("0");
						if("yes".equals(diaoliu)){
							String isBack = "12";
							//lineNewUrlClientZSHY(client,app.getAppId(), isBack, ydjFlag);
							lineNewUrlClientZSHY(app.getAppId(), isBack, ydjFlag);
						}
					}
			}
			//单件插入标识
	        map.put("batchInFlag", "2");
			//记录历史
//			allotApplyHisDao.insertHisBatch(lifeList);
			//存储过程记录历史
			Map<String,Object> param = new HashMap<String,Object>();
			List<AllotAppId> list=new ArrayList<AllotAppId>();
			for(int i=0;i<lifeList.size();i++){
				//不重复new对象 最后记录的是最后一个对象的记录(多条)
				AllotAppId apply=new AllotAppId();
				apply.setAppId(lifeList.get(i));
				list.add(apply);
			}
			param.put("list", list);
			param.put("v_ydjFlag", ydjFlag);
			param.put("v_currNode", currNode);
			param.put("v_flag", flag);
			param.put("v_recoverNode", recoveryNode);
			param.put("v_result", 0);
			allotApplyHisDao.insertAutoApply(param);
			logger.info("AllotMethodServiceImpl.updateMethod 申请件appId="+appId+"记录历史成功");
			//转移  或回收
			if("4".equals(flag)||"5".equals(flag)){
				result=allotApplyDao.updateByPrimaryKey(app);
			}else if("6".equals(flag)){//手动分件
				result=allotApplyDao.updateAllotApply(app);
			}else if("8".equals(flag)||"10".equals(flag)){//自动分件
				for(AllotApply allotApply:allotApplyList){
					result=allotApplyDao.updateAllotApply(allotApply);
				}
			}
			if(result!=0){
				if("4".equals(flag)){
					logger.info("AllotMethodServiceImpl.updateMethod 申请件appId="+appId+"转移成功");
				}else if("5".equals(flag)){
					logger.info("AllotMethodServiceImpl.updateMethod 申请件appId="+appId+"回收成功");
				}else if("6".equals(flag)){
					logger.info("AllotMethodServiceImpl.updateMethod 申请件appId="+appId+"手动分件成功");
				}else if("8".equals(flag)){
					logger.info("AllotMethodServiceImpl.updateMethod 申请件appId="+appId+"自动分件成功");
				}
			}
			//生命周期更新
			saveAppLifeCicle(map);
			logger.info("AllotMethodServiceImpl.updateMethod 申请件appId="+appId+"记录生命周期成功");
		}catch(Exception e){
			logger.info("AllotMethodServiceImpl.updateMethod 申请件appId="+appId+"记录生命周期失败");
			throw new RuntimeException(e);
		}finally{
			/*if(client!=null){
				client.close();
				client=null;
			}*/
		}
		return result;
	}
	
	/**
	 * @Description:  申请件批量 手动分件   转移  回收 挂起 释放   
	 *  flag生命周期记录标识  1 挂起  2 释放  3 备注  4 转移  5 回收  6 手动分件  7批量调工作流   8 自动分件池到组   9 自动分件组到人  10 自动分件征信池到征信队列
	 * @author 王德彬
	 * @version V1.1
	 * @param Map
	 */
	@Override
	public int updateBatchMethod(Map map)  {
		//初始   
		int result=0;
		//Client client=null;
		try{
			ArrayList<AllotApply> allotApplyList=(ArrayList<AllotApply>) map.get("allotApplyList");
			ArrayList<String> lifeList=(ArrayList<String>) map.get("lifeList");
			String flag=(String) map.get("flag")!=null?(String) map.get("flag"):"";
			//当前节点
			String secondNode=StringUtils.trimToEmpty((String)map.get("secondNode"));
			//当前环节
			String currNode=StringUtils.trimToEmpty((String)map.get("currNode"));
			//当前易达金标识
			String ydjFlag=StringUtils.trimToEmpty((String)map.get("ydjFlag"));
			//转移方式(组员之间转移)
			String wayId=StringUtils.trimToEmpty((String)map.get("wayId"));
			//回收节点位置 池  队列  组
			String recoveryNode=StringUtils.trimToEmpty((String)map.get("recoveryNode"));
			AllotApply allotApply;
			int ap=0;
			List<String> workList=new ArrayList<String>();
			//审批环节征审合一件分件或转移到普通审批员  调工作流跑流程
			if("03".equals(currNode)&&"3".equals(secondNode)&&(("4".equals(flag)&&"1".equals(wayId))||"6".equals(flag)||"9".equals(flag))&&(!"".equals(ydjFlag))){
				for(int i=0;i<allotApplyList.size();i++){
					allotApply=allotApplyList.get(i);
					//若当前审批人不是征审合一角色
					ap = apUserDao.queryCurrUserCode(allotApply.getCurrUser());
					if("2".equals(allotApply.getCheck_meuoFlag())&&(ap==0)){
						allotApply.setCheck_meuoFlag("1");
						allotApply.setZshyInnerCheck("0");
						String appId2=allotApply.getAppId().substring(0, 15);
						if(!workList.contains(appId2+"1")&&!workList.contains(appId2+"2")){
							workList.add(allotApply.getAppId());
						}
					}
				}
			}
			if("1".equals(flag)||"2".equals(flag)){//单件插入标识2019.10.30
		        map.put("batchInFlag", "2");
			}else{//批量插入生命周期标识 jdbc
				map.put("batchInFlag", "1");
			}
			//批量记录历史(分批插入 不能分批放入一个集合里插入  在一个事务中  都是最后才批量提交  导致分多少次  同一个申请件就记录几遍)
			//allotApplyHisDao.insertHisBatch(lifeList);
			//存储过程记录历史
			Map<String,Object> param = new HashMap<String,Object>();
			List<AllotAppId> list=new ArrayList<AllotAppId>();
			if(!"9".equals(flag)){
				for(int i=0;i<lifeList.size();i++){
					//不重复new对象 最后记录的是最后一个对象的记录(多条)
					AllotAppId app=new AllotAppId();
					app.setAppId(lifeList.get(i));
					list.add(app);
				}
			}else if("9".equals(flag)){
				for(int i=0;i<lifeList.size();i++){
					AllotAppId app=new AllotAppId();
					String[] longStr=lifeList.get(i).split("-");
					app.setAppId(longStr[0]);
					list.add(app);
				}
			}
			param.put("list", list);
			param.put("v_ydjFlag", ydjFlag);
			param.put("v_currNode", currNode);
			param.put("v_flag", flag);
			param.put("v_recoverNode", recoveryNode);
			param.put("v_result", 0);
			allotApplyHisDao.insertAutoApply(param);
			//allotApplyHisDao.executeOpasPoAllot(param);
			//jdbc记录历史
			//saveBatchHis(lifeList);
			// 批量记录历史 
			logger.info("AllotMethodServiceImpl.updateBatchMethod  存储过程批量记录历史成功!");
			if("1".equals(flag)||"2".equals(flag)){//挂起、释放 2019.10.30
				result=allotApplyDao.updateBatchApply(allotApplyList);
			}else{//其它
				result=selfBatchApply(allotApplyList);
				if("05".equals(currNode)&&"5".equals(flag)){//欺诈回收功能，变更欺诈提报表处理状态字段-wenyh-20201019
					allotApplyDao.updateBatchSubmitFraud(allotApplyList);
				}
			}
			if("1".equals(flag)){
				ArrayList<ApplyRemark> remarkList=(ArrayList<ApplyRemark>) map.get("remarkList");
				if(remarkList!=null&&remarkList.size()>0){
					applyRemarkDao.insertBatch(remarkList);
				}
				logger.info("AllotMethodServiceImpl.updateBatchMethod  批量挂起成功!");
			}else if("2".equals(flag)){
				logger.info("AllotMethodServiceImpl.updateBatchMethod  批量释放成功!");
			}else if("4".equals(flag)){
				logger.info("AllotMethodServiceImpl.updateBatchMethod  批量转移成功!");
			}else if("5".equals(flag)){
				logger.info("AllotMethodServiceImpl.updateBatchMethod  批量回收成功!");
			}else if("6".equals(flag)){
				logger.info("AllotMethodServiceImpl.updateBatchMethod  手动分件成功!");
			}else if("8".equals(flag)){
				logger.info("AllotMethodServiceImpl.updateBatchMethod  自动分件池到组分件成功!");
			}else if("9".equals(flag)){
				logger.info("AllotMethodServiceImpl.updateBatchMethod  自动分件组到人分件成功!");
			}
			//生命周期插入
			saveAppLifeCicle(map);
			logger.info("AllotMethodServiceImpl.updateBatchMethod  批量插入生命周期成功!");
			//调征审合一件分件或转移到普通审批员 调工作流
			if(workList.size()>0){
				//client=getClient(client);
				for(int j=0;j<workList.size();j++){
					//lineNewUrlClientZSHY(client,workList.get(j), "12", ydjFlag);
					lineNewUrlClientZSHY(workList.get(j), "12", ydjFlag);
				}
			}
		}catch(Exception e){
			logger.error("批量异常：{}", new Object[] { e.getMessage() }, e);
			throw new RuntimeException(e);
		}finally{
			/*if(client!=null){
				client.close();
				client=null;
			}*/
		}
		return result;
	}
	@Override
	public int saveRemark(Map map) throws Exception {
		//初始时间   
		int result=0;
		List<ApplyRemark> remarkList=new ArrayList<ApplyRemark>();
		remarkList=(List<ApplyRemark>) map.get("remarkList");
		List<String> lifeList=new ArrayList<String>();
		lifeList=(List<String>) map.get("lifeList");
		//单件插入标识
        map.put("batchInFlag", "2");
        result=applyRemarkDao.insertBatch(remarkList);
		logger.info("AllotMethodServiceImpl.saveRemark  批量插入全局备注成功!");
		//生命周期更新
		saveAppLifeCicle(map);
		logger.info("AllotMethodServiceImpl.saveRemark  批量插入生命周期成功!");
		return result;
	}
	//申请件批量记录生命周期
	public void saveAppLifeCicle(Map lifeMap) throws Exception {
		logger.info("AllotMethodServiceImpl.saveAppLifeCicle 开始记录生命周期");
		//当前功能标识
		String flag=StringUtils.trimToEmpty((String)lifeMap.get("flag"));
		//当前用户
		String userCode=StringUtils.trimToEmpty((String)lifeMap.get("userCode"));
		//按特定件方式转移到当前风险队列、组、组员
		String currCode=StringUtils.trimToEmpty((String)lifeMap.get("currCode"));
		//转移 回收时使用 转移或回收方式
		String id=StringUtils.trimToEmpty((String)lifeMap.get("wayId"));
		//当前节点
		String node=StringUtils.trimToEmpty((String)lifeMap.get("currNode"));
		//生命周期记录方式
		String batchInFlag=StringUtils.trimToEmpty((String)lifeMap.get("batchInFlag"));
		String secondNode="",ydjFlag="",code="",recoveryNode="",code2="",userName2="";
		//获取当前位置、易达金标识及申请件转移（按件数）或分配到的风险队列、组或人
		if("4".equals(flag)||"6".equals(flag)||"8".equals(flag)||"9".equals(flag)){
			secondNode=StringUtils.trimToEmpty((String)lifeMap.get("secondNode"));
			ydjFlag=StringUtils.trimToEmpty((String)lifeMap.get("ydjFlag"));
			code=StringUtils.trimToEmpty((String)lifeMap.get("code"));
			if("8".equals(flag)&&"02".equals(node)){
				code2=StringUtils.trimToEmpty((String)lifeMap.get("code2"));
			}
		}else if("5".equals(flag)){//回收到的位置
			recoveryNode=StringUtils.trimToEmpty((String)lifeMap.get("recoveryNode"));
		}
		List<String> lifeList=new ArrayList<String>();
		lifeList=(List<String>) lifeMap.get("lifeList");
		if(lifeList.size()>0){
			List<ApplyLifeCicle> cicleList=new ArrayList<ApplyLifeCicle>();
			Map userMap=new HashMap();
			userMap.put("userCode", userCode);
			AllotCommon acmon=new AllotCommon();
			if(userCode!=null&&!"".equals(userCode)&&!"system".equals(userCode)){
				acmon=allotCommonDao.selectUser(userMap);
			}
			//操作者(原件管理岗 或组长)中文
			if(acmon!=null&&acmon.getUserName()!=null){
				userName2=acmon.getUserName();
			}else{
				userName2="系统";
			}
			for(String appId:lifeList){
				ApplyLifeCicle a=new ApplyLifeCicle();
				String orgName="",userName="",queueName="";
				if(!"9".equals(flag)){
					a.setAppId(appId);
				}else if("9".equals(flag)){//自动分件组到人从action直接传过组员中文名称
					String[] longStr=appId.split("-");
					a.setAppId(longStr[0]);
					code=longStr[1];
					userName=longStr[2];
				}
				AllotCommon acm=new AllotCommon();
			//获取申请件到达的风险队列、组或人中文名称
			 if("4".equals(flag)||"6".equals(flag)||"8".equals(flag)){
					if("1".equals(ydjFlag)||"".equals(ydjFlag)){//易达金或反欺诈
						if("1".equals(secondNode)){
							if("1".equals(id)||"6".equals(flag)||"8".equals(flag)){
								acm=allotCommonDao.selectGroupByOrgNo(code);
							}else if("2".equals(id)){
								acm=allotCommonDao.selectGroupByOrgNo(currCode);
							}
							if(acm!=null){
								//组名
								orgName=acm.getOrgName();
							}
						}else if("3".equals(secondNode)){
							if("1".equals(id)||"6".equals(flag)){
								userMap.put("userCode", code);
								acm=allotCommonDao.selectUser(userMap);
								if(acm!=null){
									//组员
									userName=acm.getUserName();
								}
							}else if("2".equals(id)){
								userMap.put("userCode", currCode);
								acm=allotCommonDao.selectUser(userMap);
								if(acm!=null){
									//组员
									userName=acm.getUserName();
								}
							}else if("3".equals(id)){
								acm=allotCommonDao.selectGroupByOrgNo(code);
								if(acm!=null){
									orgName=acm.getOrgName();
								}
							}else if("4".equals(id)){
								acm=allotCommonDao.selectGroupByOrgNo(currCode);
								if(acm!=null){
									orgName=acm.getOrgName();
								}
							}
						}
					}else if("2".equals(ydjFlag)){//标准卡
						if(("01".equals(node)||"03".equals(node))&&"1".equals(secondNode)){//审查和审批
							if("1".equals(id)||"3".equals(id)||"6".equals(flag)||"8".equals(flag)){
								acm=allotCommonDao.selectGroupByOrgNo(code);
							}else if("2".equals(id)||"4".equals(id)){
								acm=allotCommonDao.selectGroupByOrgNo(currCode);
							}
							if(acm!=null){
								orgName=acm.getOrgName();
							}
						}else if("02".equals(node)&&"1".equals(secondNode)){//征信
							//二期更改  1、标准卡征信环节手动需要分到风险队列，自动分件自动分到风险队列 2、 只分到征信风险队列
							if("1".equals(id)||"6".equals(flag)){
								queueName=getQueueName(code);
							}else if("2".equals(id)){
								queueName=getQueueName(currCode);
							}else if("8".equals(flag)){//自动分件
								acm=allotCommonDao.selectGroupByOrgNo(code);
								if(acm!=null){
									orgName=acm.getOrgName();
								}
								if(!"".equals(code2)){
									queueName=getQueueName(code2);
								}
							}else if("10".equals(flag)){//只分到征信风险队列
								queueName=getQueueName(code);
							}
						}else if("2".equals(secondNode)){//征信队列
							if("1".equals(id)||"3".equals(id)||"6".equals(flag)||"8".equals(flag)){
								acm=allotCommonDao.selectGroupByOrgNo(code);
							}else if("2".equals(id)||"4".equals(id)){
								acm=allotCommonDao.selectGroupByOrgNo(currCode);
							}
							if(acm!=null){
								orgName=acm.getOrgName();
							}
						}else if("3".equals(secondNode)){
							if("1".equals(id)||"6".equals(flag)){
								userMap.put("userCode", code);
								acm=allotCommonDao.selectUser(userMap);
								if(acm!=null){
									//组员
									userName=acm.getUserName();
								}
							}else if("2".equals(id)){
								userMap.put("userCode", currCode);
								acm=allotCommonDao.selectUser(userMap);
								if(acm!=null){
									//组员
									userName=acm.getUserName();
								}
							}else if("3".equals(id)){
								acm=allotCommonDao.selectGroupByOrgNo(code);
								if(acm!=null){
									orgName=acm.getOrgName();
								}
							}else if("4".equals(id)){
								acm=allotCommonDao.selectGroupByOrgNo(currCode);
								if(acm!=null){
									orgName=acm.getOrgName();
								}
							}
						}
					}
				}
			 //节点描述
				 if("1".equals(flag)||"2".equals(flag)||"3".equals(flag)){
					 if("1".equals(flag)){
							a.setOperateDesc(userName2+"将该申请件挂起");
						}else if("2".equals(flag)){
							a.setOperateDesc(userName2+"将该申请件解挂");
						}else if("3".equals(flag)){
							a.setOperateDesc(userName2+"将该申请件备注");
						}
				 }else if("4".equals(flag)){
					if("1".equals(ydjFlag)||"".equals(ydjFlag)){
						if("1".equals(secondNode)&&"1".equals(id)){
							a.setOperateDesc(userName2+"将申请件转移到"+orgName+"组");
						}else if("1".equals(secondNode)&&"2".equals(id)){
							a.setOperateDesc(userName2+"将该申请件转移到"+orgName+"组");
						}else if("3".equals(secondNode)&&("1".equals(id)||"2".equals(id))){
							a.setOperateDesc(userName2+"将该申请件转移到"+userName+"队列");
						}else if("3".equals(secondNode)&&("3".equals(id)||"4".equals(id))){
							a.setOperateDesc(userName2+"将该申请件转移到"+orgName+"组");
						}
					}else if("2".equals(ydjFlag)){
						if("01".equals(node)||"03".equals(node)){
							if("1".equals(secondNode)){
								a.setOperateDesc(userName2+"将申请件转移到"+orgName+"组");
							}else if("3".equals(secondNode)){
								if("1".equals(id)||"2".equals(id)){
									a.setOperateDesc(userName2+"将该申请件转移到"+userName+"队列");
								}else if("3".equals(id)||"4".equals(id)){
									a.setOperateDesc(userName2+"将该申请件转移到"+orgName+"组");
								}
							}
						}else if("02".equals(node)){
							if("1".equals(secondNode)){
								a.setOperateDesc(userName2+"将该申请件转移到"+queueName);
							}else if("2".equals(secondNode)){
								a.setOperateDesc(userName2+"将该申请件转移到"+orgName+"组");
							}else if("3".equals(secondNode)&&("1".equals(id)||"2".equals(id))){
								a.setOperateDesc(userName2+"将该申请件转移到"+userName+"队列");
							}else if("3".equals(secondNode)&&("3".equals(id)||"4".equals(id))){
								a.setOperateDesc(userName2+"将该申请件转移到"+orgName+"组");
							}
						}
					}
				}else if("5".equals(flag)){
					if("1".equals(recoveryNode)){
						if("01".equals(node)){
							a.setOperateDesc(userName2+"将该申请件回收到审查池");
						}else if("02".equals(node)){
							a.setOperateDesc(userName2+"将该申请件回收到征信池");
						}else if("03".equals(node)){
							a.setOperateDesc(userName2+"将该申请件回收到审批池");
						}else if("05".equals(node)){
							a.setOperateDesc(userName2+"将该申请件回收到反欺诈池");
						}
					}else if("2".equals(recoveryNode)){
						a.setOperateDesc(userName2+"将该申请件回收到征信风险队列");
					}else if("3".equals(recoveryNode)){//按件数或特定件
						if(!"".equals(userCode)){
							acm=allotCommonDao.selectGroupByUserCode(userCode);
						}
						if(acm!=null&&acm.getOrgName()!=null&&!"".equals(acm.getOrgName())){
							orgName=acm.getOrgName();
							a.setOperateDesc(userName2+"将该申请件回收到组--"+orgName);
						}else{
							a.setOperateDesc(userName2+"将该申请件回收到组");
						}
					}
				}else if("6".equals(flag)){//手动分件
					if("1".equals(ydjFlag)){
						if("1".equals(secondNode)){
							if("02".equals(node)){
								a.setOperateDesc("进入易达金征信环节"+orgName);
							}else if("03".equals(node)){
								a.setOperateDesc("进入易达金审批环节"+orgName);
							}
						}else if("3".equals(secondNode)){
							if("02".equals(node)){
								a.setOperateDesc("进入易达金征信员"+userName+"队列");
							}else if("03".equals(node)){
								a.setOperateDesc("进入易达金审批员"+userName+"队列");
							}
						}
					}else if("2".equals(ydjFlag)){
						if(("01".equals(node)||"03".equals(node))&&"1".equals(secondNode)){
							if("01".equals(node)){
								a.setOperateDesc("进入标准卡录入环节"+orgName+"组");
							}else if("03".equals(node)){
								a.setOperateDesc("进入标准卡审批环节"+orgName+"组");
							}
						}else if(("01".equals(node)||"03".equals(node))&&"3".equals(secondNode)){
							if("01".equals(node)){
								a.setOperateDesc("进入标准卡录入员"+userName+"队列");
							}else if("03".equals(node)){
								a.setOperateDesc("进入标准卡审批员"+userName+"队列");
							}
						}else if("02".equals(node)&&"1".equals(secondNode)){
							a.setOperateDesc("进入标准卡征信环节"+queueName);
						}else if("02".equals(node)&&"2".equals(secondNode)){
							a.setOperateDesc("进入征信环节"+orgName+"组");
						}else if("02".equals(node)&&"3".equals(secondNode)){
							a.setOperateDesc("进入征信员"+userName+"队列");
						}
					}else if("".equals(ydjFlag)){
						if("1".equals(secondNode)){
							a.setOperateDesc("进入反欺诈"+orgName+"组");
						}else if("3".equals(secondNode)){
							a.setOperateDesc("进入反欺诈员"+userName+"队列");
						}
					}
					
				}else if("8".equals(flag)||"9".equals(flag)){//自动分件到组 、组到人
					if("1".equals(ydjFlag)){
						if("1".equals(secondNode)){
							if("02".equals(node)){
								a.setOperateDesc("自动分件到易达金征信环节"+orgName);
							}else if("03".equals(node)){
								a.setOperateDesc("自动分件到易达金审批环节"+orgName);
							}
						}else if("3".equals(secondNode)){
							if("02".equals(node)){
								a.setOperateDesc("自动分件到易达金征信员"+userName+"队列");
							}else if("03".equals(node)){
								a.setOperateDesc("自动分件到易达金审批员"+userName+"队列");
							}
						}
					}else if("2".equals(ydjFlag)){
						if(("01".equals(node)||"03".equals(node))&&"1".equals(secondNode)){
							if("01".equals(node)){
								a.setOperateDesc("自动分件到标准卡录入环节"+orgName+"组");
							}else if("03".equals(node)){
								a.setOperateDesc("自动分件到标准卡审批环节"+orgName+"组");
							}
						}else if(("01".equals(node)||"03".equals(node))&&"3".equals(secondNode)){
							if("01".equals(node)){
								a.setOperateDesc("自动分件到标准卡录入员"+userName+"队列");
							}else if("03".equals(node)){
								a.setOperateDesc("自动分件到标准卡审批员"+userName+"队列");
							}
						}else if("02".equals(node)&&"1".equals(secondNode)){
							//二期  风险队列和组同时到达
							if(!"".equals(queueName)){
								a.setOperateDesc("自动分件到征信环节"+queueName+"风险队列和"+orgName+"组");
							}else{
								a.setOperateDesc("自动分件到征信环节"+orgName+"组");
							}
						}else if("02".equals(node)&&"2".equals(secondNode)){
							a.setOperateDesc("自动分件到征信环节"+orgName+"组");
						}else if("02".equals(node)&&"3".equals(secondNode)){
							a.setOperateDesc("自动分件到征信员"+userName+"队列");
						}
					}else if("".equals(ydjFlag)){
						if("1".equals(secondNode)){
							a.setOperateDesc("自动分件到反欺诈"+orgName+"组");
						}else if("3".equals(secondNode)){
							a.setOperateDesc("自动分件到反欺诈员"+userName+"队列");
						}
					}
				}else if("10".equals(flag)){//只分到征信风险队列
					a.setOperateDesc("自动分件到征信环节"+queueName+"风险队列");
				}
				if(!"".equals(userCode)){
					a.setOperater(userName2+"-"+userCode);
					a.setCrtUser(userCode);
				}else{
					a.setOperater("system");
					a.setCrtUser("system");
				}
				a.setOperateResult("完成");
				a.setCrtDate(new Date());
				a.setUuid(StringUtil.randomUUIDPlainString());
				cicleList.add(a);
			}
			if("1".equals(batchInFlag)){//分批批量插入
				selfBatch(cicleList);
			}else if("2".equals(batchInFlag)){//单件插入标识
				for(int i=0;i<cicleList.size();i++){
					ApplyLifeCicle ac=cicleList.get(i);
					applyLifeCicleDao.addApplyLifeCicle(ac);
				}
			}
			cicleList.clear();
			logger.info("AllotMethodServiceImpl.saveAppLifeCicle 生命周期批量记录成功");
		}
	}
	//申请件生命周期分批处理
	public void selfBatch(List<ApplyLifeCicle> cicleList) throws Exception{
		//数据源获取数据库连接
        Connection conn = sqlExecutor.getConnection();
		if (conn == null) {
			return;
		}
		//设置为不自动提交
		conn.setAutoCommit(false);
		String sql="insert into OPAS_APPLY_LIFE_CICLE (UUID, APP_ID, OPERATER, OPERATE_DESC, OPERATE_RESULT, CRT_DATE, CRT_USER, LST_UPD_DATE, LST_UPD_USER) values (?,?,?,?,?,systimestamp,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		//每次提交的记录 一次50条
		int commitCountEveryTime=50;
		//记录命令执行失败数
		long failNum=0;
		//成功执行次数
		long commitNum=0;
		//需要提交的次数  
		int	commitCount=(int)Math.ceil(cicleList.size()/(double)commitCountEveryTime);
		//开始 和结束下标
		int start ,stop;
		try {
			//循环提交的次数
			for(int i=0;i<commitCount;i++){
				//开始标
				start=i*commitCountEveryTime;
				//每批最后一个下标
				stop=Math.min(i*commitCountEveryTime+commitCountEveryTime-1,cicleList.size()-1);
				//循环将数据放进
				for(int j=start;j<=stop;j++){
					ApplyLifeCicle ac=new ApplyLifeCicle();
					ac=cicleList.get(j);
					ps.setString(1, ac.getUuid());
					ps.setString(2, ac.getAppId());
					ps.setString(3, ac.getOperater());
					ps.setString(4, ac.getOperateDesc());
					ps.setString(5,ac.getOperateResult());
					ps.setString(6, ac.getCrtUser());
					ps.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
					ps.setString(8, ac.getCrtUser());
					ps.addBatch();
				}
				ps.executeBatch();
				conn.commit();
				ps.clearBatch();
				commitNum++;
				
			}
		} catch (SQLException e) {
			failNum++;
			if(conn!=null&&(!conn.isClosed())){
				conn.rollback();
			}
			//throw new RuntimeException(e);
		}finally {
			if(ps!=null){
				try{
					ps.close();
				}catch(SQLException e){
				}
			}
			if(conn!=null){
				try{
					conn.setAutoCommit(true);
					//conn.close();
				}catch(SQLException e){
				}
			}
		}
	}
	//批量记录历史
	public void selfBatchHis(List<String> lifeList) throws Exception{
		//数据源获取数据库连接
		Connection conn = sqlExecutor.getConnection();
		if (conn == null) {
			return;
		}
		//设置为不自动提交
		conn.setAutoCommit(false);
		StringBuffer sbf=new 	StringBuffer();
		sbf.append("insert into OPAS_ALLOT_APPLY_ALLOT_HIS (ID, APP_ID, CURR_OPT_QUEUE, LAST_OPT_QUEUE, ");
		sbf.append(" CURR_OPT_GROUP, LAST_OPT_GROUP,CURR_OPT_USER,LAST_OPT_USER, CURR_STATUS, DEL_STATUS, ");
		sbf.append(" CURR_NODE, CRT_TEAM_DATE, QUEUE_DATE, GROUP_DATE, SUBMIT_STATUS, SUBMIT_TYPE,");
		sbf.append(" LST_TEAM_DATE, SUBMIT_MEMO, YDJ_FLAG, REVIEW_STATUS,PROCESS_ID,USER_DATE, MATCHING_CARD_FLAG, ");
		sbf.append(" RECORD_TEAM_DATE, BACK_FLAG,SYN_FLAG,LAOJIANFLAG,CURR_USER_NAME, GROUP_TEAM_DATE,FRAUD_DATE )");
		sbf.append(" select (select sys_guid() from dual), APP_ID, CURR_OPT_QUEUE, LAST_OPT_QUEUE,");
		sbf.append(" CURR_OPT_GROUP, LAST_OPT_GROUP, CURR_OPT_USER,LAST_OPT_USER, CURR_STATUS, DEL_STATUS,");
		sbf.append("CURR_NODE, CRT_TEAM_DATE, QUEUE_DATE, GROUP_DATE, SUBMIT_STATUS, SUBMIT_TYPE,");
		sbf.append("LST_TEAM_DATE, SUBMIT_MEMO, YDJ_FLAG, REVIEW_STATUS,PROCESS_ID,USER_DATE,MATCHING_CARD_FLAG, ");
		sbf.append(" sysdate, BACK_FLAG,SYN_FLAG,LAOJIANFLAG, CURR_USER_NAME,GROUP_TEAM_DATE,FRAUD_DATE");
		sbf.append(" from OPAS_ALLOT_APPLY_ALLOT where APP_ID=?");
		String sql=sbf.toString();
		PreparedStatement ps = conn.prepareStatement(sql);
		//每次提交的记录 一次50条
		int commitCountEveryTime=50;
		//记录命令执行失败数
		long failNum=0;
		//成功执行次数
		long commitNum=0;
		//需要提交的次数  
		int	commitCount=(int)Math.ceil(lifeList.size()/(double)commitCountEveryTime);
		//开始 和结束下标
		int start ,stop;
		try {
			//循环提交的次数
			for(int i=0;i<commitCount;i++){
				//开始标
				start=i*commitCountEveryTime;
				//每批最后一个下标
				stop=Math.min(i*commitCountEveryTime+commitCountEveryTime-1,lifeList.size()-1);
				//循环将数据放进
				for(int j=start;j<=stop;j++){
					String appId=lifeList.get(j);
					ps.setString(1, appId);
					ps.addBatch();
				}
				ps.executeBatch();
				conn.commit();
				ps.clearBatch();
				commitNum++;
				
			}
		} catch (SQLException e) {
			failNum++;
			if(conn!=null&&(!conn.isClosed())){
				conn.rollback();
			}
			throw new RuntimeException(e);
		}finally {
			try{
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.setAutoCommit(true);
					conn.close();
				}
			}catch(SQLException e){
			}
		}
		logger.info("AllotMethodServiceImpl.saveBatchHis 批量记录分件历史记录提交次数："+commitNum);
		logger.info("AllotMethodServiceImpl.saveBatchHis 批量记录分件历史记录失败次数："+failNum);
	}
	//维护队列名称
	public String getQueueName(String queueCode){
		String queueName="";
		if("L".equals(queueCode)){
			queueName="低风险人工征信L";
		}else if("L1".equals(queueCode)){
			queueName="低风险自动征信L1";
		}else if("L2".equals(queueCode)){
			queueName="低风险自动征信L2";
		}else if("L3-1".equals(queueCode)){
			queueName="W低风险自动征信L3.1";
		}else if("L3-2".equals(queueCode)){
			queueName="W低风险免电核(人工排查)L3.2";
		}else if("LV".equals(queueCode)){
			queueName="低风险人工征信LV";
		}else if("M".equals(queueCode)){
			queueName="中风险人工征信M";
		}else if("H".equals(queueCode)){
			queueName="高风险人工征信H";
		}else if("H1".equals(queueCode)){
			queueName="高风险自动征信H1";
		}else if("S100".equals(queueCode)){
			queueName="系统征信自动通过";
		}else if("L1-1".equals(queueCode)){
			queueName="低风险征信L1.1";
		}else if("WL1-1".equals(queueCode)){
			queueName="W低风险征信L1.1";
		}else if("LR1-1".equals(queueCode)){
			queueName="低风险征信LR1.1";
		}else if("WLR1-1".equals(queueCode)){
			queueName="W低风险征信LR1.1";
		}else if("LR1-2".equals(queueCode)){
			queueName="低风险征信LR1.2";
		}else if("WLR1-2".equals(queueCode)){
			queueName="W低风险征信LR1.2";
		}else if("LR1-3".equals(queueCode)){
			queueName="低风险人工侧核LR1.3";
		}else if("WLR1-3".equals(queueCode)){
			queueName="W低风险人工侧核LR1.3";
		}else if("LR3".equals(queueCode)){
			queueName="低风险征信LR3";
		}else if("WLR3".equals(queueCode)){
			queueName="W低风险征信LR3";
		}else if("LR2-1".equals(queueCode)){
			queueName="低风险征信LR2.1";
		}else if("WLR2-1".equals(queueCode)){
			queueName="W低风险征信LR2.1";
		}else if("LR2-2".equals(queueCode)){
			queueName="低风险征信LR2.2";
		}else if("WLR2-2".equals(queueCode)){
			queueName="W低风险征信LR2.2";
		}else if("LR2-3".equals(queueCode)){
			queueName="低风险人工侧核LR2.3";
		}else if("WLR2-3".equals(queueCode)){
			queueName="W低风险人工侧核LR2.3";
		}else if("MR2-1".equals(queueCode)){
			queueName="中风险人工审核MR2.1";
		}else if("WMR2-1".equals(queueCode)){
			queueName="W中风险人工审核MR2.1";
		}else if("MR2-2".equals(queueCode)){
			queueName="中风险人工征信-关注正核MR2.2";
		}else if("WMR2-2".equals(queueCode)){
			queueName="W中风险人工征信-关注正核MR2.2";
		}else if("HR".equals(queueCode)){
			queueName="高风险人工征信HR";
		}else if("HR2".equals(queueCode)){
			queueName="高风险人工征信HR-关注正核";
		}else if("RGZX".equals(queueCode)){
			queueName="人工征信";
		}
		return queueName;
	}
	  //批量更改分配表
		public int selfBatchApply(List<AllotApply> allotApplyList) throws Exception{
			int result=0;
			//数据源获取数据库连接
			Connection conn = sqlExecutor.getConnection();
			if (conn == null) {
				return result;
			}
			//设置为不自动提交
			conn.setAutoCommit(false);
			StringBuffer sbf=new 	StringBuffer();
			sbf.append("update OPAS_ALLOT_APPLY_ALLOT set CURR_OPT_QUEUE=?, LAST_OPT_QUEUE=?, ");
			sbf.append(" CURR_OPT_GROUP=?, LAST_OPT_GROUP=?, CURR_OPT_USER=?,CURR_USER_NAME=?,LAST_OPT_USER=?, ");
			sbf.append(" CURR_STATUS=?, DEL_STATUS=?, CURR_NODE=?,CRT_TEAM_DATE=?,LST_TEAM_DATE=systimestamp, ");
			sbf.append(" QUEUE_DATE=?, GROUP_DATE=?, USER_DATE=?, BACK_FLAG=?, ");
			sbf.append(" BATCH_OPERATE_FLAG=?,BATCH_CREDIT_FLAG=?, BATCH_APPROVAL_FLAG=?, ");
			sbf.append(" GROUP_TEAM_DATE=?, FRAUD_DATE=?,CHECK_MEUOFLAG=?,ZSHY_INNER_CHECK=?,ALLOT_VERSION=?,REVIEW_STATUS=? where APP_ID =? AND ALLOT_VERSION=? AND CURR_NODE=?");
			String sql=sbf.toString();
			PreparedStatement ps = conn.prepareStatement(sql);
			//每次提交的记录 一次50条
			int commitCountEveryTime=50;
			//记录命令执行失败数
			long failNum=0;
			//成功执行次数
			long commitNum=0;
			//需要提交的次数  
			int	commitCount=(int)Math.ceil(allotApplyList.size()/(double)commitCountEveryTime);
			//开始 和结束下标
			int start ,stop;
			try {
				//循环提交的次数
				for(int i=0;i<commitCount;i++){
					//开始标
					start=i*commitCountEveryTime;
					//每批最后一个下标
					stop=Math.min(i*commitCountEveryTime+commitCountEveryTime-1,allotApplyList.size()-1);
					//循环将数据放进
					for(int j=start;j<=stop;j++){
						AllotApply allotApply=allotApplyList.get(j);
						String appId=allotApply.getAppId();
						ps.setString(1, allotApply.getCurrQueue());
						ps.setString(2, allotApply.getLastQueue());
						ps.setString(3, allotApply.getCurrGroup());
						ps.setString(4,allotApply.getLastGroup());
						ps.setString(5,allotApply.getCurrUser());
						ps.setString(6, allotApply.getCurrUserName());
						ps.setString(7,allotApply.getLastUser());
						ps.setString(8,allotApply.getCurrStatus());
						ps.setString(9, allotApply.getDelStatus());
						ps.setString(10,allotApply.getCurrNode());
						if(allotApply.getCrtTeamDate()==null){
							ps.setTimestamp(11, null);
						}else if(allotApply.getCrtTeamDate()!=null){
							ps.setTimestamp(11, new java.sql.Timestamp(allotApply.getCrtTeamDate().getTime()));
						}
						if(allotApply.getQueueDate()==null){
							ps.setTimestamp(12, null);
						}else if(allotApply.getQueueDate()!=null){
							ps.setTimestamp(12, new java.sql.Timestamp(allotApply.getQueueDate().getTime()));
						}
						if(allotApply.getGroupDate()==null){
							ps.setTimestamp(13, null);
						}else if(allotApply.getGroupDate()!=null){
							ps.setTimestamp(13, new java.sql.Timestamp(allotApply.getGroupDate().getTime()));
						}
						if(allotApply.getUserDate()==null){
							ps.setTimestamp(14, null);
						}else if(allotApply.getUserDate()!=null){
							ps.setTimestamp(14, new java.sql.Timestamp(allotApply.getUserDate().getTime()));
						}
						ps.setString(15, allotApply.getBackFlag());
						ps.setString(16, allotApply.getBatchApprovalFlag());
						ps.setString(17,allotApply.getBatchCreditFlag());
						ps.setString(18,allotApply.getBatchApprovalFlag());
						if(allotApply.getGroupTeamDate()==null){
							ps.setTimestamp(19, null);
						}else if(allotApply.getGroupTeamDate()!=null){
							ps.setTimestamp(19, new java.sql.Timestamp(allotApply.getGroupTeamDate().getTime()));
						}
						if(allotApply.getFraudDate()==null){
							ps.setTimestamp(20, null);
						}else if(allotApply.getFraudDate()!=null){
							ps.setTimestamp(20, new java.sql.Timestamp(allotApply.getFraudDate().getTime()));
						}
						if("02".equals(allotApply.getCurrNode())){
							ps.setString(21, "1");
						}else{
							ps.setString(21, allotApply.getCheck_meuoFlag());
						}
						ps.setString(22,allotApply.getZshyInnerCheck());
						ps.setInt(23, (allotApply.getAllotVersion()+1));
						ps.setString(24,null);
						ps.setString(25,appId);
						ps.setInt(26, allotApply.getAllotVersion());
						ps.setString(27, allotApply.getCurrNode());
						ps.addBatch();
					}
					ps.executeBatch();
					conn.commit();
					ps.clearBatch();
					commitNum++;
					
				}
			
			} catch (SQLException e) {
				failNum++;
				if(conn!=null&&(!conn.isClosed())){
					conn.rollback();
				}
				logger.error("批量更改分配表异常：{}", new Object[] { e.getMessage() }, e);
//				throw new RuntimeException(e);
			}finally {
				if(ps!=null){
					try {
						ps.close();
					} catch(SQLException e){
					}
				}
				if(conn!=null){
					try{
						conn.setAutoCommit(true);
						//conn.close();
					}catch(SQLException e){
					}
				}
			}
			return result=(int)commitNum;
		}
		/**
		 * 征审合一件审批环节调用工作流从跑流程
		 * @param appId
		 * @param isBack
		 * @param resMap
		 * @throws Exception
		 */
		//public void lineNewUrlClientZSHY(Client client,String appId, String isBack, String ydjFlag) throws Exception {
		public void lineNewUrlClientZSHY(String appId, String isBack, String ydjFlag) throws Exception {
			// isBack = 0;为提交，4为审批退回,2征信，3征审
			Map paramMap = new HashMap();
			paramMap.put("appId", appId);
			Map<String, Object> needMap = bizInpApplDao.selectProcessIdByAppId(paramMap);
			String processId = needMap.get("PROCESSID").toString();
			Map<String, String> streamMap = CommonProperties.getParoperMap();
			String nodeId="";
			if("1".equals(ydjFlag)){
				nodeId=streamMap.get("nodeId_sc_ydj").toString();//流节点
			}else if("2".equals(ydjFlag)){
				nodeId=streamMap.get("nodeId_sc_bzk").toString();//流节点
			}
			logger.info("调用流程>>>>>>>参数信息:" + processId + ",nodeId2=" + nodeId+",process_IP="+streamMap.get("process_IP"));
			/*Client client = new Client(new URL(
					"http://" + streamMap.get("process_IP") + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));*/
			String intputXml = "";
			intputXml = "{'processId':'" + processId + "','nodeId':'" + nodeId + "','isBack':'" + isBack
					+ "'}";
			logger.info(">>>>>>>参数信息:" + intputXml);
			/*client.setTimeout(60);
			logger.info(">>>>>>>设置超时时间:" + 60+"s");*/
			/*Object[] obj = new Object[] { intputXml.toString() };
			Object[] result = null;
			result = client.invoke("signal", obj);*/
			String result = topbpmService.signal(intputXml);
			String res = "";
			if(result != null && result != ""){
				res = result;
			}
			logger.info("res=" + res);
			if("1".equals(res)){
				logger.info("===========调用流程接口【成功】============");
			}else{
				logger.error("===========调用流程接口【出错】============res="+res);
				throw new Exception("工作流繁忙,请稍后再试。res="+res);
			}
		}
		 //批量隐藏申请件
		@Override
		public int updateSynFlag(List<String> allotApplyList,String currNode) throws Exception{
			int result=0;
			//数据源获取数据库连接
			Connection conn = sqlExecutor.getConnection();
			if (conn == null) {
				return result;
			}
			//设置为不自动提交
			conn.setAutoCommit(false);
			StringBuffer sbf=new 	StringBuffer();
			if("02".equals(currNode)){
				sbf.append("update OPAS_ALLOT_APPLY_ALLOT set SYN_FLAG=?,batch_credit_flag=?,LST_TEAM_DATE=systimestamp where APP_ID =? and curr_node=? and syn_flag=? ");
			}else if("03".equals(currNode)){
				sbf.append("update OPAS_ALLOT_APPLY_ALLOT set SYN_FLAG=?,batch_approval_flag=?,LST_TEAM_DATE=systimestamp  where APP_ID =? and curr_node=? and syn_flag=? ");
			}
			String sql=sbf.toString();
			PreparedStatement ps = conn.prepareStatement(sql);
			//每次提交的记录 一次200条
			int commitCountEveryTime=200;
			//记录命令执行失败数
			long failNum=0;
			//成功执行次数
			long commitNum=0;
			//需要提交的次数  
			int	commitCount=(int)Math.ceil(allotApplyList.size()/(double)commitCountEveryTime);
			//开始 和结束下标
			int start ,stop;
			try {
				//循环提交的次数
				for(int i=0;i<commitCount;i++){
					//开始标
					start=i*commitCountEveryTime;
					//每批最后一个下标
					stop=Math.min(i*commitCountEveryTime+commitCountEveryTime-1,allotApplyList.size()-1);
					//循环将数据放进
					for(int j=start;j<=stop;j++){
						String appId=allotApplyList.get(j);
						ps.setString(1, "1");
						ps.setString(2, "1");
						ps.setString(3, appId);
						ps.setString(4, currNode);
						ps.setString(5, "0");
						ps.addBatch();
					}
					ps.executeBatch();
					conn.commit();
					ps.clearBatch();
					commitNum++;
					
				}
			
			} catch (SQLException e) {
				failNum++;
				if(conn!=null&&(!conn.isClosed())){
					conn.rollback();
				}
			}finally {
				if(ps!=null){
					try {
						ps.close();
					} catch(SQLException e){
					}
				}
				if(conn!=null){
					try{
						conn.setAutoCommit(true);
						//conn.close();
					}catch(SQLException e){
					}
				}
			}
			return result=(int)commitNum;
		}
		//获取Client连接
		public Client getClient(Client client){
			try {
				if(client==null){
					Map<String, String> streamMap = CommonProperties.getParoperMap();
					String processIp= streamMap.get("process_IP").toString();//服务ip地址
					client = new Client(new URL("http://" + processIp+ "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
				}
			}  catch (Exception e) {
				logger.info("连接工作流失败，失败原因为"+e.getMessage());
				throw new RuntimeException("连接工作流失败"+e.getMessage());
			}
			return client;
		}
}