package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.BRZXConsumptionDao;
import com.huaxia.opas.dao.BRZXCreditPointDao;
import com.huaxia.opas.dao.BRZXLocationDao;
import com.huaxia.opas.dao.BRZXSpecialListForCellDao;
import com.huaxia.opas.dao.BRZXSpecialListForIdDao;
import com.huaxia.opas.dao.BRZXSpecialListForLmCellDao;
import com.huaxia.opas.dao.brzx.SpecialListForGidDao;
import com.huaxia.opas.domain.BRZX;
import com.huaxia.opas.domain.BRZXConsumption;
import com.huaxia.opas.domain.BRZXCreditPoint;
import com.huaxia.opas.domain.BRZXLocation;
import com.huaxia.opas.domain.BRZXSpecialList;
import com.huaxia.opas.domain.BRZXSpecialListForCell;
import com.huaxia.opas.domain.BRZXSpecialListForId;
import com.huaxia.opas.domain.BRZXSpecialListForLmCell;
import com.huaxia.opas.domain.brzx.SpecialListForGid;
import com.huaxia.opas.service.BRZXService;
import com.huaxia.opas.util.TaskStatusUtil;

@Service("brzxService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BRZXServiceImpl implements BRZXService {
	private final static Logger logger = LoggerFactory.getLogger(BRZXServiceImpl.class);
	@Autowired
	private BRZXSpecialListForIdDao specialListForIdDao;

	@Autowired
	private BRZXSpecialListForCellDao specialListForCellDao;

	@Autowired
	private BRZXSpecialListForLmCellDao specialListForLmCellDao;

	@Autowired
	private SpecialListForGidDao specialListForGidDao;

	@Autowired
	private BRZXLocationDao locationDao;

	@Autowired
	private BRZXConsumptionDao consumptionDao;

	@Autowired
	private BRZXCreditPointDao creditPointDao;

	public BRZXSpecialListForIdDao getSpecialListForIdDao() {
		return specialListForIdDao;
	}

	public void setSpecialListForIdDao(BRZXSpecialListForIdDao specialListForIdDao) {
		this.specialListForIdDao = specialListForIdDao;
	}

	public BRZXSpecialListForCellDao getSpecialListForCellDao() {
		return specialListForCellDao;
	}

	public void setSpecialListForCellDao(BRZXSpecialListForCellDao specialListForCellDao) {
		this.specialListForCellDao = specialListForCellDao;
	}

	public BRZXLocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(BRZXLocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public BRZXConsumptionDao getConsumptionDao() {
		return consumptionDao;
	}

	public void setConsumptionDao(BRZXConsumptionDao consumptionDao) {
		this.consumptionDao = consumptionDao;
	}

	public BRZXCreditPointDao getCreditPointDao() {
		return creditPointDao;
	}

	public void setCreditPointDao(BRZXCreditPointDao creditPointDao) {
		this.creditPointDao = creditPointDao;
	}

	public BRZXSpecialListForLmCellDao getSpecialListForLmCellDao() {
		return specialListForLmCellDao;
	}

	public void setSpecialListForLmCellDao(BRZXSpecialListForLmCellDao specialListForLmCellDao) {
		this.specialListForLmCellDao = specialListForLmCellDao;
	}

	public SpecialListForGidDao getSpecialListForGidDao() {
		return specialListForGidDao;
	}

	public void setSpecialListForGidDao(SpecialListForGidDao specialListForGidDao) {
		this.specialListForGidDao = specialListForGidDao;
	}
//	@Autowired
//	private TaskCallStatusDao taskCallStatusDao;
	
//	@Autowired
//	private TaskRequestInfoDao taskRequestInfoDao;
	
	@Override
	public int save(BRZX brzx) {
		int result = 0;

		// 通过身份证查询
		if (brzx.getSpecialListForId() != null) {
			BRZXSpecialListForId specialListForId = brzx.getSpecialListForId();
			specialListForId.setAppId(brzx.getAppId());
			specialListForId.setSwiftNumber(brzx.getSwiftNumber());
			result += saveSpecialListForId(specialListForId);
		}

		// 通过手机号查询
		if (brzx.getSpecialListForCell() != null) {
			BRZXSpecialListForCell specialListForCell = brzx.getSpecialListForCell();
			specialListForCell.setAppId(brzx.getAppId());
			specialListForCell.setSwiftNumber(brzx.getSwiftNumber());
			result += saveSpecialListForCell(specialListForCell);
		}

		// 通过联系人手机号查询
		if (brzx.getSpecialListForLmCell() != null) {
			BRZXSpecialListForLmCell specialListForLmCell = brzx.getSpecialListForLmCell();
			specialListForLmCell.setAppId(brzx.getAppId());
			specialListForLmCell.setSwiftNumber(brzx.getSwiftNumber());
			result += saveSpecialListForLmCell(specialListForLmCell);
		}

		// 通过GID查询
		if (brzx.getSpecialListForGid() != null) {
			SpecialListForGid specialListForGid = brzx.getSpecialListForGid();
			specialListForGid.setAppId(brzx.getAppId());
//			specialListForGid.setSwiftNumber(brzx.getSwiftNumber());
			result += saveSpecialListForGid(specialListForGid);
		}

		// 地址信息核对
		if (brzx.getLocation() != null) {
			BRZXLocation location = brzx.getLocation();
			location.setAppId(brzx.getAppId());
			location.setSwiftNumber(brzx.getSwiftNumber());
			result += saveLocation(location);
		}

		// 商品消费评估
		if (brzx.getConsumption() != null) {
			BRZXConsumption consumption = brzx.getConsumption();
			consumption.setAppId(brzx.getAppId());
			consumption.setSwiftNumber(brzx.getSwiftNumber());
			result += saveConsumption(consumption);
		}

		// 百融通用评分
		if (brzx.getCreditPoint() != null) {
			BRZXCreditPoint creditPoint = brzx.getCreditPoint();
			creditPoint.setAppId(brzx.getAppId());
			creditPoint.setSwiftNumber(brzx.getSwiftNumber());
			result += saveCreditPoint(creditPoint);
		}
		return result;
	}

	@Override
	public int saveSpecialListForId(BRZXSpecialList specialList) {
		return getSpecialListForIdDao().insert(specialList);
	}

	@Override
	public int saveSpecialListForCell(BRZXSpecialList specialList) {
		return getSpecialListForCellDao().insert(specialList);
	}

	@Override
	public int saveSpecialListForLmCell(BRZXSpecialList specialList) {
		return getSpecialListForLmCellDao().insert(specialList);
	}

	@Override
	public int saveLocation(BRZXLocation location) {
		return getLocationDao().insert(location);
	}

	@Override
	public int saveConsumption(BRZXConsumption consumption) {
		return getConsumptionDao().insert(consumption);
	}

	@Override
	public int saveCreditPoint(BRZXCreditPoint creditPoint) {
		return getCreditPointDao().insert(creditPoint);
	}

	@Override
	public int selectAllTaskCountByAppId(String appId) {
		return getSpecialListForIdDao().selectAllTaskCountByAppId(appId);
	}

	@Override
	public int saveSpecialListForGid(BRZXSpecialList specialList) {
		return getSpecialListForGidDao().insert(specialList);
	}

	@Override
	public void saveBrzxUpdateDataAdapterAction(BRZX brzx, String appId, String taskType) {
		save(brzx);
		Map<String, String> paramsUpdate = new HashMap<String, String>();
	    paramsUpdate.put("appId", appId);
	    paramsUpdate.put("status", TaskStatusUtil.SUCCESS);
	    paramsUpdate.put("taskType", taskType);
	    paramsUpdate.put("errorCode", null);
	    paramsUpdate.put("lstOptUser", TaskStatusUtil.CURR_USER);
	    paramsUpdate.put("requestAddNum", null);
	    paramsUpdate.put("failureAddNum", null);
//		getTaskCallStatusDao().updateSingleTask(paramsUpdate);
	}

}
