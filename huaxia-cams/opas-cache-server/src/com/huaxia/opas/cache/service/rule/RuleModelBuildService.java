package com.huaxia.opas.cache.service.rule;

import java.util.List;

import com.huateng.huaxia.xom.ApplyMainCardInfo;
import com.huateng.huaxia.xom.EnterpriseNetwork;
import com.huateng.huaxia.xom.HaveCardInfo;
import com.huateng.huaxia.xom.PadInfor;
import com.huateng.huaxia.xom.QuyushujuInfo;
import com.huateng.huaxia.xom.RepInfor;
import com.huateng.huaxia.xom.RepInforCheck;
import com.huateng.huaxia.xom.StatisInfo;
import com.huateng.huaxia.xom.TYDataAnalyze;
import com.huateng.huaxia.xom.WebInforCheck;
import com.huateng.huaxia.xom.YuShenInfo;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;

public interface RuleModelBuildService {
	
	public List<Opasbizinpapplc1> selectByPrimaryKey(String appId) throws CoreException;
	
	public ApplyMainCardInfo creatApplyMainCardInfo(Opasbizinpapplc1 on1) throws CoreException;

	public HaveCardInfo creatHaveCardInfo(Opasbizinpapplc1 on1,String appIdThd) throws CoreException;

	public RepInfor creatRepInfor(Opasbizinpapplc1 on1,String appIdThd) throws CoreException;

	public RepInforCheck creatRepInforCheck(Opasbizinpapplc1 on1,String appIdThd) throws CoreException;

	public StatisInfo creatStatisInfo(Opasbizinpapplc1 on1) throws CoreException;

	public WebInforCheck creatWebInforCheck(Opasbizinpapplc1 on1,String appIdThd) throws CoreException;

	public TYDataAnalyze createTYDataAnalyze(Opasbizinpapplc1 on1,String appIdThd) throws CoreException;
	
	//add by yuanquan 2018.10.25
	public EnterpriseNetwork createEnterpriseNetwork(Opasbizinpapplc1 on1,String appIdThd)throws CoreException;

	//add by wjf 2019.04.17
	public YuShenInfo createYuShenModel(Opasbizinpapplc1 on1,String appIdThd)throws CoreException;

	//add by wjf 2019.09.20 设备指纹
	public PadInfor creatPadInforModel(Opasbizinpapplc1 on1) throws CoreException;

	public QuyushujuInfo createQysjInfoModel(Opasbizinpapplc1 on1, String appIdThd) throws CoreException;

}
