package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huaxia.opas.dao.sysparm.HospitalDao;
import com.huaxia.opas.domain.sysparm.Hospital;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.HospitalService;

public class HospitalServiceImpl extends AbstractService implements HospitalService,Serializable{

	private static final long serialVersionUID = -6124007793514527837L;

	@Resource
	private HospitalDao hospitalDao;
	
	
	@Override
	public int queryHospitalCount(Hospital hospital) {
		return hospitalDao.queryHospitalCount(hospital);
	}

	@Override
	public List<Hospital> queryHospitalList(Hospital hospital, int curNum, int pageNum) {
		return hospitalDao.queryHospitalList(hospital,curNum,pageNum);
	}

	@Override
	public void deleteHospital(Hospital hospital) {
		hospitalDao.deleteHospital(hospital);
	}

	@Override
	public List<Hospital> checkIsExistHospital(Hospital hospital) {
		return hospitalDao.checkIsExistHospital(hospital);
	}

	@Override
	public void insertHospital(Hospital hospital) {
		hospitalDao.insertHospital(hospital);
	}

	@Override
	public void updateHospital(Hospital hospital) {
		hospitalDao.updateHospital(hospital);
	}

	@Override
	public int insertHospitalUpload(List<Hospital> list) {
		return hospitalDao.insertHospitalUpload(list);
	}


}
