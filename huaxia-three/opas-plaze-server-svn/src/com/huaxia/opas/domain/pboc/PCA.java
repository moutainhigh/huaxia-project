package com.huaxia.opas.domain.pboc;

import java.io.Serializable;
import java.util.List;
import com.huaxia.opas.domain.pboc.pca.PD02;
/**
 * 授信协议信息 
 * @author gaoh
 *
 */
public class PCA implements Serializable {
	 
	private static final long serialVersionUID = -940941816891056194L;
	private List<PD02> PD02List;//授信协议信息单元   [0..*] 

	public List<PD02> getPD02List() {
		return PD02List;
	}

	public void setPD02List(List<PD02> pD02List) {
		PD02List = pD02List;
	}
	
}
