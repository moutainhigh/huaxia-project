package com.huaxia.opas.domain.pboc.ppo;

import java.io.Serializable;
import java.util.List;
/**
 * 公共信息概要信息单元
 * @author gaoh
 *
 */
public class PC04 implements Serializable {

	private static final long serialVersionUID = 8008407246619637481L;
	private PC04data PC04data;//公共信息概要信息单元数据
    private List<PC040H> PC040HList;//公共信息概要信息
	public PC04data getPC04data() {
		return PC04data;
	}
	public void setPC04data(PC04data pC04data) {
		PC04data = pC04data;
	}
	public List<PC040H> getPC040HList() {
		return PC040HList;
	}
	public void setPC040HList(List<PC040H> pC040HList) {
		PC040HList = pC040HList;
	}
    
}