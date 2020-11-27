package com.huaxia.opas.domain.pboc.pcr;

import java.io.Serializable;
/**
 * 相关还款责任信息单元
 * @author gaoh
 *
 */
public class PD03 implements Serializable {
	
	private static final long serialVersionUID = -5487584234711432667L;

	private PD03A PD03A;//相关还款责任信息段    [1..1] 
	
	private PD03Z PD03Z;//标注及声明信息段    [0..1]

	public PD03A getPD03A() {
		return PD03A;
	}

	public void setPD03A(PD03A pD03A) {
		PD03A = pD03A;
	}

	public PD03Z getPD03Z() {
		return PD03Z;
	}

	public void setPD03Z(PD03Z pD03Z) {
		PD03Z = pD03Z;
	}
	
}
