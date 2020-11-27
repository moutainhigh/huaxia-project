package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huaxia.opas.domain.sysparm.QRCode;

/**
 * 二维码名单库维护
 * @author zhangjunwen
 *
 */
public interface QRCodeDao {

	int queryQRCodeCount(QRCode qrCode);

	List<QRCode> queryQRCode(QRCode qrCode, int curNum, int pageNum);

	int insertQRCodeStart(QRCode qrCode);
	int insertQRCodeEnd(QRCode qrCode);

	int deleteQRCode(QRCode qrCode);

	int setQRCodeStatusOK(QRCode qrCode);

	int setQRCodeStatusNO(QRCode qrCode);

    List<QRCode> checkIsExistQR(QRCode qrCode);

	String queryQRCodeStatus(String uuId);

	int updateQRCodeWithoutStatus(QRCode qrCode);

	int updateQRCodeAndStatus(QRCode qrCode);

	int insertQRCodeUpload(List<QRCode> list);

}
