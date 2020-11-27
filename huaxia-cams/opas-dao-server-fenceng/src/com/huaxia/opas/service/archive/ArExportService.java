package com.huaxia.opas.service.archive;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.archive.ArExport;

public interface ArExportService {
	
	public List queryAppIdList(ArExport arExport);

	public List queryNoSendList(String appIds);
	//补全导出文件的字段
	public List<Map<String, Object>> querySendCardUpdateColunm(String appId);
	//查询进件渠道
	public String queryinputChannel(String appId);

}
