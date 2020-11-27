package com.huaxia.opas.service.archive;

import java.util.List;

import com.huaxia.opas.domain.archive.SuppArchive;

public interface SuppArService {
	public List<SuppArchive> querySuppArList(SuppArchive suppArchive, int curNum, int pageNum);
	
	public int querySuppArCount(SuppArchive suppArchive);
	
	public int updateSuppArFlag(SuppArchive suppArchive);
	
	public List<SuppArchive> querySuppArAllList(SuppArchive suppArchive, int curNum, int pageNum);
	
	public int querySuppArAllCount(SuppArchive suppArchive);
	
	public int updateSuppToAr(SuppArchive suppArchive);
	
	public int updateSuppToArAll(SuppArchive suppArchive);
	
	public int updateSuppToDel(SuppArchive suppArchive);
	
	public List<SuppArchive> querySuccessList(SuppArchive suppArchive);
	
	// 一次补件或二次补件后对补件结果下结论   shihuan 2017-03-27
	public int insertFilePatch(SuppArchive suppArchive);
}
