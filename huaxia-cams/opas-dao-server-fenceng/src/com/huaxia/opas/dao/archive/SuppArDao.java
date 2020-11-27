package com.huaxia.opas.dao.archive;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.archive.SuppArchive;

public interface SuppArDao {
	
	public int querySuppArCount(SuppArchive suppArchive);
	
	public List<SuppArchive> querySuppArList(SuppArchive suppArchive, int curNum, int pageNum);
	
	public int updateSuppArFlag(SuppArchive suppArchive);
	
	public int querySuppArAllCount(SuppArchive suppArchive);
	
	public List<SuppArchive> querySuppArAllList(SuppArchive suppArchive, int curNum, int pageNum);
	
	public int updateSuppToAr(SuppArchive suppArchive);
	
	public int updateSuppToArAll(SuppArchive suppArchive);
	
	public int updateSuppToDel(SuppArchive suppArchive);

	public List<SuppArchive> querySuccessList(SuppArchive suppArchive);
	
	// 一次补件或二次补件后对补件结果下结论   shihuan 2017-03-27
	public int insertFilePatch(SuppArchive suppArchive);
	//更新待归档申请表  wangtao  2017-06-07
	public int updateFilePatch(SuppArchive supparchive);
	//根据appId查询归档补件表是否已存在该条记录
	public int queryFilePatchCodeCount(String appId);

	public int queryCountByPatchCode(Map<String, Object> dataMap);

	public int insertFilePatchHis(SuppArchive supparchive);
}
