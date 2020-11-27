package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.QRCode;

/**
 * 二维码名单维护 service
 * @author zjw
 *
 */
public interface QRCodeService {
	/**
	 * 查询符合条件的数据条数
	 * @param qrCode
	 * @return
	 * @throws CoreException
	 */
	public int queryQRCodeCount(QRCode qrCode) throws CoreException;
	/**
	 * 分页查询
	 * @param qrCode
	 * @param curNum
	 * @param pageNum
	 * @return
	 * @throws CoreException 
	 */
	public List<QRCode> queryQRCodeList(QRCode qrCode, int curNum, int pageNum) throws CoreException;
	/**
	 * 添加二维码名单维护  启用状态
	 * @param qrCode
	 */
	public int insertQRCodeStart(QRCode qrCode) throws CoreException;
	/**
	 * 添加 二维码名单维护 停用状态
	 * @param qrCode
	 */
	public int insertQRCodeEnd(QRCode qrCode) throws CoreException;
	/**
	 * 删除
	 * @param qrCode
	 * @return
	 */
	public int deleteQRCode(QRCode qrCode) throws CoreException;
	/**
	 * 批量启用
	 * @param qrCode
	 * @return
	 */
	public int setQRCodeStatusOK(QRCode qrCode) throws CoreException;
	/**
	 * 批量禁用
	 * @param qrCode
	 * @return int
	 */
	public int setQRCodeStatusNO(QRCode qrCode) throws CoreException;
	/**
	 * 查看是否已存在
	 * @param qrCode
	 * @return List<QRCode>
	 */
	public List<QRCode> checkIsExistQR(QRCode qrCode) throws CoreException;
	/**
	 * 查询数据启用状态
	 * @param qrCode
	 * @return
	 */
	public String queryQRCodeStatus(String uuid) throws CoreException;
	/**
	 * 修改 状态不变
	 * @param qrCode
	 * @return
	 * @throws CoreException
	 */
	public int updateQRCodeWithoutStatus(QRCode qrCode) throws CoreException;
	public int updateQRCodeAndStatus(QRCode qrCode) throws CoreException;
	/**
	 * 批量导入 插入
	 * @param list
	 * @return
	 * @throws CoreException
	 */
	public int insertQRCodeUpload(List<QRCode> list) throws CoreException;
}
