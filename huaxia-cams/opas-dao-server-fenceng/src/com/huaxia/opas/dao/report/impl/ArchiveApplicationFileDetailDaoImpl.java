/**
 * 
 */
package com.huaxia.opas.dao.report.impl;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.ArchiveApplicationFileDetailDao;


/**
 * @author gaohui(归档申请件明细表)
 *
 */
public class ArchiveApplicationFileDetailDaoImpl extends AbstractDAO implements ArchiveApplicationFileDetailDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4604427074102715465L;
	private static final String NAMESPACES = "ArchiveApplicationFileDetail.";
	@Override
	public List<Map<String, Object>> selectDataArchiveApplicationFileDetailByDateDimension(Map<String, Object> paramsMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectDataArchiveApplicationFileDetailByDateDimension", paramsMap);
	}


}
