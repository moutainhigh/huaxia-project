package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huaxia.opas.domain.sysparm.MarktQRCode;

/**
 * 二维码名单库维护
 *
 */
public interface MarktQRCodeDao {

	int queryQRCodeCount(MarktQRCode qrCode);

	List<MarktQRCode> queryQRCode(MarktQRCode qrCode, int curNum, int pageNum);

	int insertQRCodeStart(MarktQRCode qrCode);
	int insertQRCodeEnd(MarktQRCode qrCode);

	int deleteQRCode(MarktQRCode qrCode);

	int setQRCodeStatusOK(MarktQRCode qrCode);

	int setQRCodeStatusNO(MarktQRCode qrCode);

    List<MarktQRCode> checkIsExistQR(MarktQRCode qrCode);

	String queryQRCodeStatus(String uuId);

	int updateQRCodeWithoutStatus(MarktQRCode qrCode);

	int updateQRCodeAndStatus(MarktQRCode qrCode);

	int insertQRCodeUpload(List<MarktQRCode> list);

}
