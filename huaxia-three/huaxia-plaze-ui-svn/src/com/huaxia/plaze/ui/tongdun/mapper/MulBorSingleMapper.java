package com.huaxia.plaze.ui.tongdun.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.tongdun.domain.MulBorSingle;

public interface MulBorSingleMapper {

	List<Map<String,Object>> selectFormularyNumTasksByType(Map<String,Object> params);
	
	int insertObject(MulBorSingle mulBorTrnSingle);
	
	int updateStatusById(Map<String, Object> params);
	
}
