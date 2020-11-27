package com.huaxia.opas.domain.retrieve;

import java.io.Serializable;

public class FicoBigResponse implements Serializable{

	private static final long serialVersionUID = 2882451377166479662L;
	private int spgjs;//审批过件数
	private int spjjs;//审批拒件数
	private int ycfqzjjs;//一次反欺诈拒件数
	private int ecfqzjjs;//二次反欺诈拒件数
	private int ycjcjjs;//一次决策拒件数
	private int ycjcgjs;//一次决策过件数
	private int ecjcgjs;//二次决策拒件数
	private int ecjcjjs;//二次决策过件数
	private int sqjzs;//申請件总数
	public int getSpgjs() {
		return spgjs;
	}
	public void setSpgjs(int spgjs) {
		this.spgjs = spgjs;
	}
	public int getSpjjs() {
		return spjjs;
	}
	public void setSpjjs(int spjjs) {
		this.spjjs = spjjs;
	}
	public int getYcfqzjjs() {
		return ycfqzjjs;
	}
	public void setYcfqzjjs(int ycfqzjjs) {
		this.ycfqzjjs = ycfqzjjs;
	}
	public int getEcfqzjjs() {
		return ecfqzjjs;
	}
	public void setEcfqzjjs(int ecfqzjjs) {
		this.ecfqzjjs = ecfqzjjs;
	}
	public int getYcjcjjs() {
		return ycjcjjs;
	}
	public void setYcjcjjs(int ycjcjjs) {
		this.ycjcjjs = ycjcjjs;
	}
	public int getYcjcgjs() {
		return ycjcgjs;
	}
	public void setYcjcgjs(int ycjcgjs) {
		this.ycjcgjs = ycjcgjs;
	}
	public int getEcjcgjs() {
		return ecjcgjs;
	}
	public void setEcjcgjs(int ecjcgjs) {
		this.ecjcgjs = ecjcgjs;
	}
	public int getEcjcjjs() {
		return ecjcjjs;
	}
	public void setEcjcjjs(int ecjcjjs) {
		this.ecjcjjs = ecjcjjs;
	}
	public int getSqjzs() {
		return sqjzs;
	}
	public void setSqjzs(int sqjzs) {
		this.sqjzs = sqjzs;
	}
	
}
