package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.HospitalDao;
import com.huaxia.opas.domain.sysparm.Hospital;

public class HospitalDaoImpl extends AbstractDAO implements HospitalDao {

	
	private static final long serialVersionUID = -5308649215143068343L;
	
	private static final String NAMESPACES = "Hospital.";

	@Override
	public int queryHospitalCount(Hospital hospital) {
		return sqlMap.queryForObject(NAMESPACES+"queryHospitalCount", hospital);
	}

	@Override
	public List<Hospital> queryHospitalList(Hospital hospital, int curNum, int pageNum) {
		return sqlMap.queryForList(NAMESPACES+"queryHospitalList", hospital, curNum,pageNum);
	}

	@Override
	public List<Hospital> checkIsExistHospital(Hospital hospital) {
		return sqlMap.queryForList(NAMESPACES+"checkIsExistHospital", hospital);
	}
	
	@Override
	public void deleteHospital(Hospital hospital) {
		sqlMap.delete(NAMESPACES+"deleteHospital", hospital);
	}

	@Override
	public void insertHospital(Hospital hospital) {
		sqlMap.insert(NAMESPACES+"insertHospital", hospital);
	}

	@Override
	public void updateHospital(Hospital hospital) {
		sqlMap.update(NAMESPACES+"updateHospital", hospital);
	}

	@Override
	public int insertHospitalUpload(List<Hospital> list) {
		return sqlMap.insert(NAMESPACES+"insertHospitalUpload", list);
	}

}
