package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.MarktQRCodeDao;
import com.huaxia.opas.domain.sysparm.MarktQRCode;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.MarktQRCodeService;
/**
 * 二维码名单维护 serviceImpl
 *
 */
public class MarktQRCodeServiceImpl extends AbstractService implements MarktQRCodeService,Serializable{

	private static final long serialVersionUID = -6001811659170101139L;

	@Resource
	private MarktQRCodeDao qrCodeDao;
	/**
	 * 查询符合条件的数量
	 */
	@Override
	public int queryQRCodeCount(MarktQRCode qrCode) throws CoreException {
		return qrCodeDao.queryQRCodeCount(qrCode);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<MarktQRCode> queryQRCodeList(MarktQRCode qrCode, int curNum, int pageNum) throws CoreException {
		return qrCodeDao.queryQRCode(qrCode,curNum,pageNum);
	}
	/**
	 * 添加   启用状态
	 */
	@Override
	public int insertQRCodeStart(MarktQRCode qrCode) throws CoreException {
		return qrCodeDao.insertQRCodeStart(qrCode);
	}
	/**
	 * 添加 停用状态
	 */
	@Override
	public int insertQRCodeEnd(MarktQRCode qrCode) throws CoreException {
		return qrCodeDao.insertQRCodeEnd(qrCode);
	}
	/**
	 * 删除 
	 */
	@Override
	public int deleteQRCode(MarktQRCode qrCode) throws CoreException {
		return qrCodeDao.deleteQRCode(qrCode);
	}
	/**
	 * 批量启用
	 */
	@Override
	public int setQRCodeStatusOK(MarktQRCode qrCode) throws CoreException {
		return qrCodeDao.setQRCodeStatusOK(qrCode);
	}
	/**
	 * 批量禁用
	 */
	@Override
	public int setQRCodeStatusNO(MarktQRCode qrCode) throws CoreException {
		return qrCodeDao.setQRCodeStatusNO(qrCode);
	}
	/**
	 * 查看是否已存在
	 */
	@Override
	public List<MarktQRCode> checkIsExistQR(MarktQRCode qrCode) throws CoreException {
		return qrCodeDao.checkIsExistQR(qrCode);
	}
	/**
	 * 查询状态
	 */
	@Override
	public String queryQRCodeStatus(String uuId) throws CoreException {
		return qrCodeDao.queryQRCodeStatus(uuId);
	}
	/**
	 * 修改  状态不变
	 */
	@Override
	public int updateQRCodeWithoutStatus(MarktQRCode qrCode) throws CoreException {
		return qrCodeDao.updateQRCodeWithoutStatus(qrCode);
	}
	@Override
	public int updateQRCodeAndStatus(MarktQRCode qrCode) throws CoreException {
		return  qrCodeDao.updateQRCodeAndStatus(qrCode);
	}
	/**
	 * 批量导入 
	 */
	@Override
	public int insertQRCodeUpload(List<MarktQRCode> list) throws CoreException {
		return qrCodeDao.insertQRCodeUpload(list);
	}

}
