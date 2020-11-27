package com.huaxia.opas.domain.pboc;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.domain.pboc.pda.PD01;
/**
 * 借贷账户信息
 * @author gaoh
 *
 */
public class PDA implements Serializable {

	private static final long serialVersionUID = 1553350588068170040L;
	private List<PD01> PD01List;//借贷账户信息单元
	public List<PD01> getPD01List() {
		return PD01List;
	}
	public void setPD01List(List<PD01> pD01List) {
		PD01List = pD01List;
	}

}