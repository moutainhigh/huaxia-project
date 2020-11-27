package com.huaxia.cams.modules.jianbing.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaxia.cams.modules.jianbing.domain.JianBingComAnalData;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjBrief;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjBriefAnal;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjDetail;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjDetailAnal;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjLoanBrief;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjLoanDetail;
import com.huaxia.cams.modules.jianbing.mapper.JianBingResponseMapper;
import com.huaxia.cams.modules.jianbing.service.JianBingResponseService;

@Service("jianBingResponseService")
public class JianBingResponseServiceImpl implements JianBingResponseService {

	@Resource
	JianBingResponseMapper jianBingResponseMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(Map<String, Object> info) throws Exception {
		String bodyStr = info.get("bodyStr").toString();
		String trnId = info.get("trnId").toString();
		String crtUser = info.get("crtUser").toString();
		String appId = info.get("appId").toString();
		
		JSONObject json = JSONObject.parseObject(bodyStr);
		JSONObject data = (JSONObject) json.get("gjj_data");
		//获取基表信息
		JianBingGjjBrief brief = (JianBingGjjBrief) JSON.parseObject(data.get("gjj_brief").toString(),
				JianBingGjjBrief.class);
		//获取其他七张业务表信息
		JianBingGjjBriefAnal briefAnal = (JianBingGjjBriefAnal) JSON.parseObject(data.get("gjj_brief_analysis").toString(), JianBingGjjBriefAnal.class);
		List<JianBingGjjDetail> detailList = JSON.parseArray(data.getString("gjj_detail"), JianBingGjjDetail.class);
		JianBingGjjDetailAnal detailAnal = (JianBingGjjDetailAnal) JSON.parseObject(data.get("gjj_detail_analysis").toString(), JianBingGjjDetailAnal.class);
		List<JianBingComAnalData> comAnal = JSON.parseArray(detailAnal.getCompany_analyzed_data(),JianBingComAnalData.class);
		List<JianBingGjjLoanBrief> loanBrief = JSON.parseArray(data.getString("gjj_loan_brief"),JianBingGjjLoanBrief.class);
		JSONArray loanDetail = JSON.parseArray(data.getString("gjj_loan_detail"));
		brief.setCrt_user(crtUser);
		brief.setTrn_id(trnId);
		brief.setApp_id(appId);
		//先插入基表
		int result = jianBingResponseMapper.insertBrief(brief);
		//插入其他数据表
		if(result > 0){
			if(briefAnal != null){
				briefAnal.setTrn_id(trnId);
				briefAnal.setCrt_user(crtUser);
				briefAnal.setApp_id(appId);
			    jianBingResponseMapper.insertBriefAnal(briefAnal);
			}
			if(detailList != null && detailList.size() > 0){
				for(JianBingGjjDetail detail : detailList){
					detail.setTrn_id(trnId);
					detail.setCrt_user(crtUser);
					detail.setApp_id(appId);
					jianBingResponseMapper.insertGjjDetail(detail);
				}
			}
			if(detailAnal != null){
				detailAnal.setTrn_id(trnId);
				detailAnal.setCrt_user(crtUser);
				detailAnal.setApp_id(appId);
			    jianBingResponseMapper.insertDetailAnal(detailAnal);
			}
			if(comAnal != null && comAnal.size() > 0){
				for(JianBingComAnalData company : comAnal){
					company.setTrn_id(trnId);
					company.setCrt_user(crtUser);
					company.setApp_id(appId);
					jianBingResponseMapper.insertComAnal(company);
				}
			}
			if(loanBrief != null && loanBrief.size() > 0){
				for(JianBingGjjLoanBrief loan : loanBrief){
					loan.setTrn_id(trnId);
					loan.setCrt_user(crtUser);
					loan.setApp_id(appId);
					jianBingResponseMapper.insertGjjLoan(loan);
				}
			}
			if(loanDetail != null && loanDetail.size() > 0){
				for(int i = 0; i < loanDetail.size(); i++){
					JSONArray detail = loanDetail.getJSONArray(i);
					for(int k = 0; k < detail.size(); k++){
						JianBingGjjLoanDetail detailLoan = (JianBingGjjLoanDetail) JSON.parseObject(detail.getJSONObject(k).toString(), JianBingGjjLoanDetail.class);
						detailLoan.setTrn_id(trnId);
						detailLoan.setCrt_user(crtUser);
						detailLoan.setApp_id(appId);
						jianBingResponseMapper.insertGjjLoanDetail(detailLoan);

					}
				}
			}
			
		}

		return result;

	}

	
}
