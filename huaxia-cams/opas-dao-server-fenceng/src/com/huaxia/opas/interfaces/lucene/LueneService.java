package com.huaxia.opas.interfaces.lucene;

import java.util.List;

import com.huateng.neofp.core.CoreException;

/**
 * 全文检索服务
 * @author Administrator
 */
public interface LueneService {
	
	/**
	 * 获取相似度评分
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return
	 * @throws CoreException
	 */
	public double getSimScore(String str1,String str2) throws CoreException;
	
	/**
	 * 获取相似度评分列表
	 * @param strs1
	 * @param strs2
	 * @return
	 * @throws CoreException
	 */
	public List<Double> getSimScoreList(List<String> strs1, List<String> strs2) throws CoreException;
	
}
