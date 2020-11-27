package com.huaxia.opas.dao.apply;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.apply.ApplyLifeCicle;

public interface ApplyLifeCicleDao {
	
	public int addApplyLifeCicle(ApplyLifeCicle a) throws Exception;

	public int insertApplyLifeBatch(List<ApplyLifeCicle> cicleList) throws Exception;
	
	public int selfInsert(List<ApplyLifeCicle> cicleList) throws Exception;
	
	String queryOperater(Map map) throws Exception;
	
}
