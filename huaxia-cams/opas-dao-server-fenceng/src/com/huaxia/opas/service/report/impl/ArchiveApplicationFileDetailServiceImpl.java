package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.report.ArchiveApplicationFileDetailDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.report.ArchiveApplicationFileDetailService;

/**
 * @author gaohui (归档申请件明细表)
 *
 */
public class ArchiveApplicationFileDetailServiceImpl extends AbstractService implements ArchiveApplicationFileDetailService, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6002889548691181170L;
	@Resource(name = "archiveApplicationFileDetailDao")
	private ArchiveApplicationFileDetailDao archiveApplicationFileDetailDao;
	public ArchiveApplicationFileDetailDao getArchiveApplicationFileDetailDao() {
		return archiveApplicationFileDetailDao;
	}
	public void setArchiveApplicationFileDetailDao(ArchiveApplicationFileDetailDao archiveApplicationFileDetailDao) {
		this.archiveApplicationFileDetailDao = archiveApplicationFileDetailDao;
	}

	@Override
	public Map<String, Object> findArchiveApplicationFileDetailDataByDateDimension(Map<String, Object> paraMap) {
		Map<String, Object> paramsMap=  new HashMap<String, Object>();
		paramsMap.put("beginDate",paraMap.get("beginDate"));
		paramsMap.put("endDate",paraMap.get("endDate"));
		paramsMap.put("ydjFlag",paraMap.get("ydjFlag"));//易达金标识
		Object choseChannel=paraMap.get("choseChannel");
		Object choseArea=paraMap.get("choseArea");
		Object choseallApplicationCard=paraMap.get("allApplicationCard");
		Object choseApplicationCard=paraMap.get("choseApplicationCard");
		Object choseOperator=paraMap.get("choseOperator");
		Object choseSerialWaterNum=paraMap.get("choseSerialWaterNum");
		paramsMap.put("choseChannel",choseChannel);//渠道 8
		paramsMap.put("choseArea",choseArea);//地区 9
		if(choseallApplicationCard.toString().equals("allCard")){
		paramsMap.put("allApplicationCard","allCard");//申请卡产品(全部)
		paramsMap.put("choseApplicationCard","");//申请卡产品卡
		}else{
		paramsMap.put("allApplicationCard","");
		paramsMap.put("choseApplicationCard",choseApplicationCard.toString());//申请卡产品卡
		}
		paramsMap.put("choseOperator",choseOperator);//录入商 7
		paramsMap.put("choseSerialWaterNum",choseSerialWaterNum);//流水号字母 11
		//获取  维度 中的 数据
		String[] groupByColum=paraMap.get("choseDimension").toString().split("-");
		String choseDimensionColumnGroup=groupByColum[0];//7,8,9,11,CARD
		String dictCodeStyle=groupByColum[1];//ALLOT_APPCHANNEL,ALLOT_APPREGION,ALLOT_APPPROD,ALLOT_APPINPUT...
		if(choseDimensionColumnGroup.equals("CARD")){//如果选择维度是 申请卡产品
			
		paramsMap.put("choseDimensionColumnGroup","");// 以  l.APPLY_CARD 分组
		if(choseallApplicationCard.toString().equals("allCard")){//代表 选择了全部卡产品
		paramsMap.put("dictDetailCodeValue","");
		}else{//卡产品中的某一个
		paramsMap.put("dictDetailCodeValue",choseApplicationCard.toString());//dictDetailCode字段的值
		}
		
		}else{//7,8,9,11几个数字的其中一个
		if(!choseDimensionColumnGroup.equals("")){
		paramsMap.put("choseDimensionColumnGroup",Integer.parseInt(choseDimensionColumnGroup));
		}else{
		paramsMap.put("choseDimensionColumnGroup","");
		}
		if(choseDimensionColumnGroup.equals("7")){
		paramsMap.put("dictDetailCodeValue",choseOperator.toString());//dictDetailCode字段的值
		}else if(choseDimensionColumnGroup.equals("8")){
		paramsMap.put("dictDetailCodeValue",choseChannel.toString());//dictDetailCode字段的值
		}else if(choseDimensionColumnGroup.equals("9")){
		paramsMap.put("dictDetailCodeValue",choseArea.toString());//dictDetailCode字段的值
		}else{//11
		paramsMap.put("dictDetailCodeValue",choseSerialWaterNum.toString());//dictDetailCode字段的值
		}
		}
		paramsMap.put("dictCodeStyle",dictCodeStyle);//ALLOT_APPCHANNEL,ALLOT_APPREGION,ALLOT_APPPROD,ALLOT_APPINPUT...
		if(paraMap.get("choseApplyResult").toString().equals("-1")){
			paramsMap.put("choseApplyResult","");//申请件结果
		}else{
			paramsMap.put("choseApplyResult",paraMap.get("choseApplyResult").toString());//申请件结果
		}
		if(paraMap.get("choseAprroveWay").toString().equals("-1")){
			paramsMap.put("choseAprroveWay","");//审批方式
		}else{
			paramsMap.put("choseAprroveWay",paraMap.get("choseAprroveWay").toString());//审批方式
		}
		List<Map<String, Object>> listMap=archiveApplicationFileDetailDao.selectDataArchiveApplicationFileDetailByDateDimension(paramsMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> hj=new HashMap<String, Object>();
		Map<String, Object> pf=new HashMap<String, Object>();//配发卡合计
		double hjArch=0.0;//进件数量  arch
		double hjPass=0.0;//核准 PASS
		double hjRefuse=0.0;//拒绝 REFUSE
		String hjPassRate="0.0";//核准率% PASSRATE
		String hjRefuseRate="0.0";//拒绝率% REFUSERATE
		
		//配发卡
		double pfArch=0.0;//进件数量  arch
		double pfPass=0.0;//核准 PASS
		double pfRefuse=0.0;//拒绝 REFUSE
		String pfPassRate="0.0";//核准率% PASSRATE
		String pfRefuseRate="0.0";//拒绝率% REFUSERATE
		
		boolean flag = true;
		String ydjFlag = (String) paraMap.get("ydjFlag");
		for (Map<String, Object> map : listMap) {
			if ("1".equals(ydjFlag)&&("0069".equals(map.get("APP_PROD"))||"0041".equals(map.get("APP_PROD")))) {
				pfArch+=Double.parseDouble(map.get("ARCH").toString());
				pfPass+=Double.parseDouble(map.get("PASS").toString());
				pfRefuse+=Double.parseDouble(map.get("REFUSE").toString());
				if(flag){
					flag=false;
				}
			} else {
				hjArch+=Double.parseDouble(map.get("ARCH").toString());
				hjPass+=Double.parseDouble(map.get("PASS").toString());
				hjRefuse+=Double.parseDouble(map.get("REFUSE").toString());
			}
		}
		if (!flag&&"1".equals(ydjFlag)) {
			double pfApproveSum = pfPass+pfRefuse;
			pf.put("TYPE", "合计(配发卡)");
			pf.put("ARCH",pfArch);
			pf.put("PASS", pfPass);
			pf.put("REFUSE", pfRefuse);
			pf.put("APPROVESUM", pfApproveSum);
			if (pfApproveSum!=0){
				pfPassRate=String.format("%.2f",(pfPass/pfApproveSum)*100);
				pfRefuseRate=String.format("%.2f",(pfRefuse/pfApproveSum)*100);
			} 
			pf.put("PASSRATE", Double.parseDouble(pfPassRate));
			pf.put("REFUSERATE",  Double.parseDouble(pfRefuseRate));
		}
		double hjApproveSum = hjPass+hjRefuse;
		hj.put("TYPE", "合计");
		hj.put("ARCH",hjArch);
		hj.put("PASS", hjPass);
		hj.put("REFUSE", hjRefuse);
		hj.put("APPROVESUM", hjApproveSum);
		if (hjApproveSum!=0){
			hjPassRate=String.format("%.2f",(hjPass/hjApproveSum)*100);
			hjRefuseRate=String.format("%.2f",(hjRefuse/hjApproveSum)*100);
		} 
		hj.put("PASSRATE", Double.parseDouble(hjPassRate));
		hj.put("REFUSERATE",  Double.parseDouble(hjRefuseRate));
		List<Map<String, Object>> listMapUse=new ArrayList<Map<String,Object>>();
		listMapUse.add(hj);
		if(!flag){
			listMapUse.add(pf);
		}
		listMapUse.addAll(listMap);
		
		List<Map<String, Object>> realUselist=new ArrayList<Map<String,Object>>();
		if ("7".equals(choseDimensionColumnGroup)){
			List<Map<String, Object>> Otherlist=new ArrayList<Map<String,Object>>();
			
			for (Map<String, Object> map :listMapUse) {
				if ("其他".equals(map.get("TYPE").toString())){
					Otherlist.add(map);
				} else {
					realUselist.add(map);
				}
			}
			Map<String,Object> others=new HashMap<String,Object>();
			double otherArch=0.0;//进件数量  arch
			double otherPass=0.0;//核准 PASS
			double otherRefuse=0.0;//拒绝 REFUSE
			String otherPassRate="0.0";//核准率% PASSRATE
			String otherRefuseRate="0.0";//拒绝率% REFUSERATE
			for (Map<String, Object> map :Otherlist) {
				otherArch+=Double.parseDouble(map.get("ARCH").toString());
				otherPass+=Double.parseDouble(map.get("PASS").toString());
				otherRefuse+=Double.parseDouble(map.get("REFUSE").toString());
			}
			double otherApproveSum = otherPass+otherRefuse;
			others.put("TYPE", "其他");
			others.put("ARCH",otherArch);
			others.put("PASS", otherPass);
			others.put("REFUSE", otherRefuse);
			others.put("APPROVESUM", otherApproveSum);
			if (otherApproveSum!=0){
				otherPassRate=String.format("%.2f",(otherPass/otherApproveSum)*100);
				otherRefuseRate=String.format("%.2f",(otherRefuse/otherApproveSum)*100);
			} 
			others.put("PASSRATE", Double.parseDouble(otherPassRate));
			others.put("REFUSERATE",  Double.parseDouble(otherRefuseRate));
			realUselist.add(others);
		}
		
		if ("7".equals(choseDimensionColumnGroup)){
			dataMap.put("rows", realUselist);
		}else{
			dataMap.put("rows", listMapUse);
		}
		return dataMap; //fileApplicationDetailDao.findDataLineFileApplicationDetailByDateDimension(paramsMap);
	}
}
