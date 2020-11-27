package com.huaxia.cams.modules.bairong.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.cams.modules.bairong.domain.ApplyLoanStr;
import com.huaxia.cams.modules.bairong.mapper.ApplyLoanStrMapper;
import com.huaxia.cams.modules.bairong.service.ApplyLoanStrService;

@Service("applyLoanStrService")
public class ApplyLoanStrServiceImpl implements ApplyLoanStrService {

	@Resource
	private ApplyLoanStrMapper applyLoanStrMapper;

	@Override
	public int saveApplyLoanStr(ApplyLoanStr applyLoanStr) {
		int result = 0;
		if (applyLoanStr != null) {
			if (applyLoanStr.getApplyLoanStrBase() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrBase(applyLoanStr.getApplyLoanStrBase());
			}
			if (applyLoanStr.getApplyLoanStrD7() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrD7(applyLoanStr.getApplyLoanStrD7());
			}
			if (applyLoanStr.getApplyLoanStrD15() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrD15(applyLoanStr.getApplyLoanStrD15());
			}
			if (applyLoanStr.getApplyLoanStrM1() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrM1(applyLoanStr.getApplyLoanStrM1());
			}
			if (applyLoanStr.getApplyLoanStrM3() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrM3(applyLoanStr.getApplyLoanStrM3());
			}
			if (applyLoanStr.getApplyLoanStrM6() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrM6(applyLoanStr.getApplyLoanStrM6());
			}
			if (applyLoanStr.getApplyLoanStrM12() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrM12(applyLoanStr.getApplyLoanStrM12());
			}
			if (applyLoanStr.getApplyLoanStrFst() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrFst(applyLoanStr.getApplyLoanStrFst());
			}
			if (applyLoanStr.getApplyLoanStrLst() != null) {
				result += applyLoanStrMapper.insertApplyLoanStrLst(applyLoanStr.getApplyLoanStrLst());
			}
		}
		return result;
	}
}
