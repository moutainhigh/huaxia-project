package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Marketer;

public interface MarketerService {

	int queryMarketersCount(Marketer marketer);

	List<Marketer> queryMarketersList(Marketer marketer, int curNum, int pageNum);

	List<Marketer> checkIsExistMarketer(Marketer marketer);

	int insertMarketer(Marketer marketer);

	int updateMarketer(Marketer marketer);

	int deleteMarketer(Marketer marketer);

	int marketerStatusOK(Marketer marketer);

	int marketerStatusStop(Marketer marketer);

	int insertMarketerUpload(List<Marketer> list)  throws CoreException ;

}
