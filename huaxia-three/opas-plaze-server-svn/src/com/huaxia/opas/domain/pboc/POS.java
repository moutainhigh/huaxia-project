package com.huaxia.opas.domain.pboc;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.domain.pboc.pos.PG01;
/**
 * 其他标注及声明信息
 * @author gaoh
 *
 */
public class POS implements Serializable {
	 
	private static final long serialVersionUID = 2203027309333738142L;
	private List<PG01> PG01List;//标注及声明信息单元   [0..*]  

	public List<PG01> getPG01List() {
		return PG01List;
	}

	public void setPG01List(List<PG01> pG01List) {
		PG01List = pG01List;
	}
	
}
