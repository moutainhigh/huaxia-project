package com.huaxia.opas.domain.pboc.pce;

import java.io.Serializable;
/**
 * 强制执行记录信息单元
 * @author gaoh
 *
 */
public class PF03 implements Serializable {
	
	private static final long serialVersionUID = -5074742760299291322L;
	private PF03A PF03A;//强制执行记录信息段    [1..1]
	private PF03Z PF03Z;//标注及声明信息段    [0..1]  
	public PF03A getPF03A() {
		return PF03A;
	}
	public void setPF03A(PF03A pF03A) {
		PF03A = pF03A;
	}
	public PF03Z getPF03Z() {
		return PF03Z;
	}
	public void setPF03Z(PF03Z pF03Z) {
		PF03Z = pF03Z;
	}
	
}
