package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.MarktQRCodeDao;
import com.huaxia.opas.domain.sysparm.MarktQRCode;
/**
 *  二维码名单库维护
 *
 */
public class MarktQRCodeDaoimpl extends AbstractDAO implements MarktQRCodeDao{

	private static final long serialVersionUID = -2308449203826742558L;

	private static final String NAMESPACES = "MarktQRCode.";
	/**
	 * 数量统计
	 */
	@Override
	public int queryQRCodeCount(MarktQRCode qrCode) {
		return sqlMap.queryForObject(NAMESPACES+"queryQRCodeCount", qrCode);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<MarktQRCode> queryQRCode(MarktQRCode qrCode, int curNum, int pageNum) {
		List<MarktQRCode> list = new ArrayList<MarktQRCode>();
		list = sqlMap.queryForList(NAMESPACES+"queryQRCode", qrCode, curNum,pageNum);
		return list;
	}
	/**
	 * 添加   启用
	 */
	@Override
	public int insertQRCodeStart(MarktQRCode qrCode) {
		return sqlMap.insert(NAMESPACES+"insertQRCodeStart",qrCode);
	}
	/**
	 * 添加   停用
	 */
	@Override
	public int insertQRCodeEnd(MarktQRCode qrCode) {
		return sqlMap.insert(NAMESPACES+"insertQRCodeEnd",qrCode);
	}
	/**
	 * 批量删除
	 */
	@Override
	public int deleteQRCode(MarktQRCode qrCode) {
		List ids = qrCode.getIds();
		return sqlMap.delete(NAMESPACES+"deleteQRCode", ids);
	}
	/**
	 * 批量启用
	 */
	@Override
	public int setQRCodeStatusOK(MarktQRCode qrCode) {
		return sqlMap.update(NAMESPACES+"setQRCodeStatusOK",qrCode);
	}
	/**
	 * 批量禁用
	 */
	@Override
	public int setQRCodeStatusNO(MarktQRCode qrCode) {
		return sqlMap.update(NAMESPACES+"setQRCodeStatusNO",qrCode);
	}
	/**
	 * 查询数据是否已存在
	 */
	@Override
	public List<MarktQRCode> checkIsExistQR(MarktQRCode qrCode) {
		List<MarktQRCode> listQRCode = new ArrayList<MarktQRCode>();
		listQRCode=sqlMap.queryForList(NAMESPACES+"checkIsExistQR", qrCode);
		return listQRCode;
	}
	@Override
	public String queryQRCodeStatus(String uuId) {
		return sqlMap.queryForObject(NAMESPACES+"queryQRCodeStatus", uuId);
	}
	@Override
	public int updateQRCodeWithoutStatus(MarktQRCode qrCode) {
		return sqlMap.update(NAMESPACES+"updateQRCodeWithoutStatus", qrCode);
	}
	@Override
	public int updateQRCodeAndStatus(MarktQRCode qrCode) {
		return sqlMap.update(NAMESPACES+"updateQRCodeAndStatus", qrCode);
	}
	@Override
	public int insertQRCodeUpload(List<MarktQRCode> list) {
		return sqlMap.update(NAMESPACES+"insertQRCodeUpload", list);
	}

}
