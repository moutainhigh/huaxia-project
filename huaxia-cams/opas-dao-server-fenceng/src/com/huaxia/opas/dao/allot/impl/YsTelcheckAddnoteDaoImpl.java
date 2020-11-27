package com.huaxia.opas.dao.allot.impl;

import java.util.List;
import java.util.Map;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.YsTelcheckAddnoteDao;
import com.huaxia.opas.domain.allot.YsPatchboltInfo;
import com.huaxia.opas.domain.allot.YsTelcheckAddnote;
/**
 * 预审环节信息
 * @author  wangdebin 
 */
public  class YsTelcheckAddnoteDaoImpl  extends AbstractDAO implements YsTelcheckAddnoteDao  {
	
	private static final long serialVersionUID = -1923363099060668724L;
	private static final String NAMESPACES = "YsTelcheckAddnote.";
	
	@Override
	public int selectCount(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCount", map);
	}

	@Override
	public List<YsTelcheckAddnote> selectByExample(Map map) {
		return getSqlMap().queryForList(NAMESPACES + "selectByExample", map);
	}

	@Override
	public YsTelcheckAddnote selectYsResultInfo(Map map) {
		return getSqlMap().queryForObject(NAMESPACES+"selectYsResultInfo",map);
	}

	@Override
	public YsPatchboltInfo selectYsPatchbolt(Map map) {
		return getSqlMap().queryForObject(NAMESPACES+"selectYsPatchbolt",map);
	}
}
