package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.YsTelcheckAddnoteDao;
import com.huaxia.opas.domain.allot.YsPatchboltInfo;
import com.huaxia.opas.domain.allot.YsTelcheckAddnote;
import com.huaxia.opas.service.allot.YsTelcheckAddnoteService;
/**
 * 预审环节信息查询
 * @author wangdebin
 * @version v1.1(初始v1.0)
 */
public class YsTelcheckAddnoteServiceImpl extends AbstractDAO implements YsTelcheckAddnoteService, Serializable {

	private static final long serialVersionUID = -8725698635390007714L;

	private static final Logger logger = LoggerFactory.getLogger(YsTelcheckAddnoteServiceImpl.class);

	@Resource(name = "ysTelcheckAddnoteDao")
	private YsTelcheckAddnoteDao ysTelcheckAddnoteDao;

	@Override
	public int selectCount(Map map) throws Exception {
		return ysTelcheckAddnoteDao.selectCount(map);
	}

	@Override
	public List<YsTelcheckAddnote> selectByExample(Map map) throws Exception {
		return ysTelcheckAddnoteDao.selectByExample(map);
	}

	@Override
	public YsTelcheckAddnote selectYsResultInfo(Map map) throws Exception {
		return ysTelcheckAddnoteDao.selectYsResultInfo(map);
	}

	@Override
	public YsPatchboltInfo selectYsPatchbolt(Map map) throws Exception {
		return ysTelcheckAddnoteDao.selectYsPatchbolt(map);
	}
	
}
