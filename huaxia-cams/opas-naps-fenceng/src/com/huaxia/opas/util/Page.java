package com.huaxia.opas.util;

/**
 * 分页计算工具类
 * 
 * @author Administrator
 *
 */
public class Page {

	/**
	 * 每页条目数
	 */
	private int pageNum;

	/**
	 * 当前页码
	 */
	private int curpage;

	/**
	 * 开始页码
	 */
	private int begPageNum;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public int getBegPageNum() {
		if (curpage > 1) {
			begPageNum = (curpage - 1) * pageNum;
		}
		return begPageNum;
	}

	public void setBegPageNum(int begPageNum) {
		this.begPageNum = begPageNum;
	}

}
