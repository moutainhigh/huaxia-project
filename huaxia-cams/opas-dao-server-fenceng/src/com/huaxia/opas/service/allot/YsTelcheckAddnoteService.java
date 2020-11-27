package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.allot.YsPatchboltInfo;
import com.huaxia.opas.domain.allot.YsTelcheckAddnote;

/**
 * 系统决策页面反显预审环节调查注记、补件、资料审查
 * @author  wangdebin
 */
public interface YsTelcheckAddnoteService {
	
	 int selectCount(Map map) throws Exception;

	 List<YsTelcheckAddnote> selectByExample(Map map) throws Exception;
	 
	 YsTelcheckAddnote selectYsResultInfo(Map map) throws Exception;
	 
	 YsPatchboltInfo selectYsPatchbolt(Map map) throws Exception;
}
