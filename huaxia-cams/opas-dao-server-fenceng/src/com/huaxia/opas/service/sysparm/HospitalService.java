package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huaxia.opas.domain.sysparm.Hospital;

public interface HospitalService {

	int queryHospitalCount(Hospital hospital);

	List<com.huaxia.opas.domain.sysparm.Hospital> queryHospitalList(Hospital hospital, int curNum, int pageNum);

	void deleteHospital(Hospital hospital);

	List<Hospital> checkIsExistHospital(Hospital hospital);

	void insertHospital(Hospital hospital);

	void updateHospital(Hospital hospital);

	int insertHospitalUpload(List<Hospital> list);

}
