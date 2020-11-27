package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.util.concurrent.AbstractService;
import com.huaxia.opas.dao.report.DifferentGrainStatisticsDao;
import com.huaxia.opas.service.report.DifferentGrainStatisticsService;

/**
 * @author gaohui (不同粒度进件情况统计表)
 *
 */
public class DifferentGrainStatisticsServiceImpl extends AbstractService implements DifferentGrainStatisticsService, Serializable {

	private static final long serialVersionUID = 5629495342872930704L;
	@Resource(name = "differentGrainStatisticsDao")
	private DifferentGrainStatisticsDao differentGrainStatisticsDao;
	public DifferentGrainStatisticsDao getDifferentGrainStatisticsDao() {
		return differentGrainStatisticsDao;
	}
	public void setDifferentGrainStatisticsDao(DifferentGrainStatisticsDao differentGrainStatisticsDao) {
		this.differentGrainStatisticsDao = differentGrainStatisticsDao;
	}
	@Override
	protected void doStart() {
	}
	@Override
	protected void doStop() {
	}
	@Override
	public Map<String, Object> findDataDifferentGrainStatisticsByDateDimension(Map<String, Object> paraMap) {
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
		paramsMap.put("choseDimensionColumnGroup",choseDimensionColumnGroup.toString());
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
		List<Map<String, Object>> listMap =differentGrainStatisticsDao.selectDifferentGrainStatisticsDataByDateDimension(paramsMap);
		Map<String, Object> hj=new HashMap<String, Object>();
		Map<String, Object> pfhj=new HashMap<String, Object>();//配发卡合计
		String ydjFlag = (String) paraMap.get("ydjFlag");
		double hjAppcnt=0.0;//进件数量  APPCNT
		double hjPass=0.0;//核准 PASS
		double hjRefuse=0.0;//拒绝 REFUSE
		double hjCancel=0.0;//取消 CANCEL
		double hjNoFinish=0.0;//未完成 NOFINISH
		double hjApproveSum=0.0;//审批量 APPROVESUM
		String hjPassRate="0.0";//核准率% PASSRATE
		String hjRefuseRate="0.0";//拒绝率% REFUSERATE
		String hjWaitDealRate="0.0";//待处理率% WAITDEALRATE
		
		double hjAppcnt1=0.0;//进件数量  APPCNT
		double hjPass1=0.0;//核准 PASS
		double hjRefuse1=0.0;//拒绝 REFUSE
		double hjCancel1=0.0;//取消 CANCEL
		double hjNoFinish1=0.0;//未完成 NOFINISH
		double hjApproveSum1=0.0;//审批量 APPROVESUM
		String hjPassRate1="0.0";//核准率% PASSRATE
		String hjRefuseRate1="0.0";//拒绝率% REFUSERATE
		String hjWaitDealRate1="0.0";//待处理率% WAITDEALRATE
		boolean flag = true;
		for (Map<String, Object> map : listMap) {
			 if ("1".equals(ydjFlag)&&("0069".equals(map.get("APP_PROD"))||"0041".equals(map.get("APP_PROD")))) {
				hjAppcnt1+=Double.parseDouble(map.get("APPCNT").toString());
				hjPass1+=Double.parseDouble(map.get("PASS").toString());
				hjRefuse1+=Double.parseDouble(map.get("REFUSE").toString());
				hjCancel1+=Double.parseDouble(map.get("CANCEL").toString());
				hjNoFinish1+=Double.parseDouble(map.get("NOFINISH").toString());
				hjApproveSum1+=Double.parseDouble(map.get("APPROVESUM").toString());
				if(flag){
					flag=false;
				}
			} else {
				hjAppcnt+=Double.parseDouble(map.get("APPCNT").toString());
				hjPass+=Double.parseDouble(map.get("PASS").toString());
				hjRefuse+=Double.parseDouble(map.get("REFUSE").toString());
				hjCancel+=Double.parseDouble(map.get("CANCEL").toString());
				hjNoFinish+=Double.parseDouble(map.get("NOFINISH").toString());
				hjApproveSum+=Double.parseDouble(map.get("APPROVESUM").toString());
			}
		}
		if (!flag&&"1".equals(ydjFlag)) {
			pfhj.put("TYPE", "合计(配发卡)");
			pfhj.put("APPCNT",hjAppcnt1);
			pfhj.put("PASS", hjPass1);
			pfhj.put("REFUSE", hjRefuse1);
			pfhj.put("CANCEL", hjCancel1);
			pfhj.put("NOFINISH",hjNoFinish1);
			pfhj.put("APPROVESUM", hjApproveSum1);
			if (hjApproveSum1!=0){
				hjPassRate1=String.format("%.2f",(hjPass1/hjApproveSum1)*100);
				hjRefuseRate1=String.format("%.2f",(hjRefuse1/hjApproveSum1)*100);
			} 
			if (hjAppcnt1!=0){
				hjWaitDealRate1=String.format("%.2f",(hjNoFinish1/hjAppcnt1)*100);
			} 
			pfhj.put("PASSRATE", Double.parseDouble(hjPassRate1));
			pfhj.put("REFUSERATE",  Double.parseDouble(hjRefuseRate1));
			pfhj.put("WAITDEALRATE", Double.parseDouble(hjWaitDealRate1));
		} 
		hj.put("TYPE", "合计");
		hj.put("APPCNT",hjAppcnt);
		hj.put("PASS", hjPass);
		hj.put("REFUSE", hjRefuse);
		hj.put("CANCEL", hjCancel);
		hj.put("NOFINISH",hjNoFinish);
		hj.put("APPROVESUM", hjApproveSum);
		if (hjApproveSum!=0){
			hjPassRate=String.format("%.2f",(hjPass/hjApproveSum)*100);
			hjRefuseRate=String.format("%.2f",(hjRefuse/hjApproveSum)*100);
		} 
		if (hjAppcnt!=0){
			hjWaitDealRate=String.format("%.2f",(hjNoFinish/hjAppcnt)*100);
		} 
		hj.put("PASSRATE", Double.parseDouble(hjPassRate));
		hj.put("REFUSERATE",  Double.parseDouble(hjRefuseRate));
		hj.put("WAITDEALRATE", Double.parseDouble(hjWaitDealRate));
		//易达金维度为申请卡种 （配发卡合计） 
		List<Map<String, Object>> listMapUse=new ArrayList<Map<String,Object>>();
		listMapUse.add(hj);
		if(!flag){
			listMapUse.add(pfhj);
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
			double othersAppcnt=0.0;//进件数量  APPCNT
			double othersPass=0.0;//核准 PASS
			double othersRefuse=0.0;//拒绝 REFUSE
			double othersCancel=0.0;//取消 CANCEL
			double othersNoFinish=0.0;//未完成 NOFINISH
			double othersApproveSum=0.0;//审批量 APPROVESUM
			String othersPassRate="0.0";//核准率% PASSRATE
			String othersRefuseRate="0.0";//拒绝率% REFUSERATE
			String othersWaitDealRate="0.0";//待处理率% WAITDEALRATE
			for (Map<String, Object> map :Otherlist) {
				othersAppcnt+=Double.parseDouble(map.get("APPCNT").toString());
				othersPass+=Double.parseDouble(map.get("PASS").toString());
				othersRefuse+=Double.parseDouble(map.get("REFUSE").toString());
				othersCancel+=Double.parseDouble(map.get("CANCEL").toString());
				othersNoFinish+=Double.parseDouble(map.get("NOFINISH").toString());
				othersApproveSum+=Double.parseDouble(map.get("APPROVESUM").toString());
			}
			others.put("TYPE", "其他");
			others.put("APPCNT",othersAppcnt);
			others.put("PASS", othersPass);
			others.put("REFUSE",othersRefuse);
			others.put("CANCEL", othersCancel);
			others.put("NOFINISH",othersNoFinish);
			others.put("APPROVESUM", othersApproveSum);
			if (othersApproveSum!=0){
				othersPassRate=String.format("%.2f",(othersPass/othersApproveSum)*100);
				othersRefuseRate=String.format("%.2f",(othersRefuse/othersApproveSum)*100);
			} 
			if (othersAppcnt!=0){
				othersWaitDealRate=String.format("%.2f",(othersNoFinish/othersAppcnt)*100);
			} 
			others.put("PASSRATE", Double.parseDouble(othersPassRate));
			others.put("REFUSERATE",  Double.parseDouble(othersRefuseRate));
			others.put("WAITDEALRATE", Double.parseDouble(othersWaitDealRate));
			realUselist.add(others);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		if ("7".equals(choseDimensionColumnGroup)){
		dataMap.put("rows", realUselist);
		}else{
			dataMap.put("rows", listMapUse);
		}
		return dataMap;
	}
	@Override
	public List<Map<String, Object>> queryApplicationCardProducts() {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> cardMap = new HashMap<String, Object>();
		cardMap.put("dictDetailCode", "");
		cardMap.put("dictDetailName", "--请选择--");
		listMap.add(cardMap);
		List<Map<String, Object>> searchlistMap =differentGrainStatisticsDao.selectApplicationCardProducts();
		if(!searchlistMap.isEmpty()){
			listMap.addAll(searchlistMap);
		}
		return listMap;
	}
}
