package com.huaxia.opas.service.avaya;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.avaya.AvayaLog;

public interface AvayaLogService {

	// 添加自动拨号添加日志（也是入sas库的表）
	public int addAvayaLog(AvayaLog avayaLog) throws CoreException;

}
