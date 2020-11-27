package com.huaxia.opas.dao.avaya;

import com.huaxia.opas.domain.avaya.AvayaLog;

public interface AvayaLogDao {

	// 自动拨号添加日志（也是入sas库的表）
	public int add(AvayaLog avayaLog);

}
