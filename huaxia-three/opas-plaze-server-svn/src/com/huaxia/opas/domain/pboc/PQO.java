package com.huaxia.opas.domain.pboc;

import java.io.Serializable;
import com.huaxia.opas.domain.pboc.pqo.PC05;
/**
 * 查询记录概要
 * @author gaoh
 *
 */
public class PQO implements Serializable {
	private static final long serialVersionUID = -1083904844345554430L;
	private PC05 PC05;//查询记录概要信息单元    [1..1] 

	public PC05 getPC05() {
		return PC05;
	}

	public void setPC05(PC05 pC05) {
		PC05 = pC05;
	}
	
}