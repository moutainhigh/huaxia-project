package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.QRCodeDao;
import com.huaxia.opas.domain.sysparm.QRCode;
/**
 *  二维码名单库维护
 * @author zjw
 *
 */
public class QRCodeDaoimpl extends AbstractDAO implements QRCodeDao{

	private static final long serialVersionUID = -2308449203826742558L;

	private static final String NAMESPACES = "QRCode.";
	/**
	 * 数量统计
	 */
	@Override
	public int queryQRCodeCount(QRCode qrCode) {
		return sqlMap.queryForObject(NAMESPACES+"queryQRCodeCount", qrCode);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<QRCode> queryQRCode(QRCode qrCode, int curNum, int pageNum) {
		List<QRCode> list = new ArrayList<QRCode>();
		list = sqlMap.queryForList(NAMESPACES+"queryQRCode", qrCode, curNum,pageNum);
		return list;
	}
	/**
	 * 添加   启用
	 */
	@Override
	public int insertQRCodeStart(QRCode qrCode) {
		return sqlMap.insert(NAMESPACES+"insertQRCodeStart",qrCode);
	}
	/**
	 * 添加   停用
	 */
	@Override
	public int insertQRCodeEnd(QRCode qrCode) {
		return sqlMap.insert(NAMESPACES+"insertQRCodeEnd",qrCode);
	}
	/**
	 * 批量删除
	 */
	@Override
	public int deleteQRCode(QRCode qrCode) {
		List ids = qrCode.getIds();
		return sqlMap.delete(NAMESPACES+"deleteQRCode", ids);
	}
	/**
	 * 批量启用
	 */
	@Override
	public int setQRCodeStatusOK(QRCode qrCode) {
		return sqlMap.update(NAMESPACES+"setQRCodeStatusOK",qrCode);
	}
	/**
	 * 批量禁用
	 */
	@Override
	public int setQRCodeStatusNO(QRCode qrCode) {
		return sqlMap.update(NAMESPACES+"setQRCodeStatusNO",qrCode);
	}
	/**
	 * 查询数据是否已存在
	 */
	@Override
	public List<QRCode> checkIsExistQR(QRCode qrCode) {
		List<QRCode> listQRCode = new ArrayList<QRCode>();
		listQRCode=sqlMap.queryForList(NAMESPACES+"checkIsExistQR", qrCode);
		return listQRCode;
	}
	@Override
	public String queryQRCodeStatus(String uuId) {
		return sqlMap.queryForObject(NAMESPACES+"queryQRCodeStatus", uuId);
	}
	@Override
	public int updateQRCodeWithoutStatus(QRCode qrCode) {
		return sqlMap.update(NAMESPACES+"updateQRCodeWithoutStatus", qrCode);
	}
	@Override
	public int updateQRCodeAndStatus(QRCode qrCode) {
		return sqlMap.update(NAMESPACES+"updateQRCodeAndStatus", qrCode);
	}
	@Override
	public int insertQRCodeUpload(List<QRCode> list) {
		return sqlMap.update(NAMESPACES+"insertQRCodeUpload", list);
	}

}
