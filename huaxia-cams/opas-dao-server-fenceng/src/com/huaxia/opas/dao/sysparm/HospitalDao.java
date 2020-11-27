package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huaxia.opas.domain.sysparm.Hospital;

public interface HospitalDao {

	int queryHospitalCount(Hospital hospital);

	List<Hospital> queryHospitalList(Hospital hospital, int curNum, int pageNum);

	void deleteHospital(Hospital hospital);

	List<Hospital> checkIsExistHospital(Hospital hospital);

	void insertHospital(Hospital hospital);

	void updateHospital(Hospital hospital);

	int insertHospitalUpload(List<Hospital> list);

}
