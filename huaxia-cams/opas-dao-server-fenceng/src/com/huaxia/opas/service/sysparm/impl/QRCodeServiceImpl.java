package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.QRCodeDao;
import com.huaxia.opas.domain.sysparm.QRCode;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.QRCodeService;
/**
 * 二维码名单维护 serviceImpl
 * @author zjw
 *
 */
public class QRCodeServiceImpl extends AbstractService implements QRCodeService,Serializable{

	private static final long serialVersionUID = -6001811659170101139L;

	@Resource
	private QRCodeDao qrCodeDao;
	/**
	 * 查询符合条件的数量
	 */
	@Override
	public int queryQRCodeCount(QRCode qrCode) throws CoreException {
		return qrCodeDao.queryQRCodeCount(qrCode);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<QRCode> queryQRCodeList(QRCode qrCode, int curNum, int pageNum) throws CoreException {
		return qrCodeDao.queryQRCode(qrCode,curNum,pageNum);
	}
	/**
	 * 添加   启用状态
	 */
	@Override
	public int insertQRCodeStart(QRCode qrCode) throws CoreException {
		return qrCodeDao.insertQRCodeStart(qrCode);
	}
	/**
	 * 添加 停用状态
	 */
	@Override
	public int insertQRCodeEnd(QRCode qrCode) throws CoreException {
		return qrCodeDao.insertQRCodeEnd(qrCode);
	}
	/**
	 * 删除 
	 */
	@Override
	public int deleteQRCode(QRCode qrCode) throws CoreException {
		return qrCodeDao.deleteQRCode(qrCode);
	}
	/**
	 * 批量启用
	 */
	@Override
	public int setQRCodeStatusOK(QRCode qrCode) throws CoreException {
		return qrCodeDao.setQRCodeStatusOK(qrCode);
	}
	/**
	 * 批量禁用
	 */
	@Override
	public int setQRCodeStatusNO(QRCode qrCode) throws CoreException {
		return qrCodeDao.setQRCodeStatusNO(qrCode);
	}
	/**
	 * 查看是否已存在
	 */
	@Override
	public List<QRCode> checkIsExistQR(QRCode qrCode) throws CoreException {
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
	public int updateQRCodeWithoutStatus(QRCode qrCode) throws CoreException {
		return qrCodeDao.updateQRCodeWithoutStatus(qrCode);
	}
	@Override
	public int updateQRCodeAndStatus(QRCode qrCode) throws CoreException {
		return  qrCodeDao.updateQRCodeAndStatus(qrCode);
	}
	/**
	 * 批量导入 
	 */
	@Override
	public int insertQRCodeUpload(List<QRCode> list) throws CoreException {
		return qrCodeDao.insertQRCodeUpload(list);
	}

}
