package com.huaxia.opas.domain.pboc;

import java.io.Serializable;
import com.huaxia.opas.domain.pboc.pmm.PB02;
/**
 * 婚姻信息
 * @author gaoh
 *
 */
public class PMM implements Serializable {
	
	private static final long serialVersionUID = 4856365173349783192L;
	private PB02 PB02;//婚姻信息单元

	public PB02 getPB02() {
		return PB02;
	}

	public void setPB02(PB02 pB02) {
		PB02 = pB02;
	}
	
}
