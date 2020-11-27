package com.huaxia.opas.cache.service.address.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.service.address.AddresMachLevService;
import com.huaxia.opas.domain.address.OpasArea;
import com.huaxia.opas.domain.address.OpasCity;
import com.huaxia.opas.domain.address.OpasProvince;
import com.huaxia.opas.domain.address.OpasTown;
import com.huaxia.opas.service.address.QueryAddressListService;
import com.huaxia.opas.util.StringUtil;

public class AddresMachLevImpl implements AddresMachLevService {

	protected static Logger logger = Logger.getLogger(AddresMachLevImpl.class);

	private static Map<String, OpasProvince> mapprovince = new HashMap<String, OpasProvince>();
	private static Map<String, List<OpasCity>> mapcity = new HashMap<String, List<OpasCity>>();
	private static List<OpasArea> listarea = new ArrayList<OpasArea>();
	private static List<OpasTown> listtown = new ArrayList<OpasTown>();

	@Resource(name = "addressListService")
	private QueryAddressListService addressListService;

	@Override
	public String getMachResult(String WorkType, String Address, String CompanyName) throws CoreException {
		String lev = "5";
		Address = StringUtil.trimToEmpty(Address);
		CompanyName = StringUtil.trimToEmpty(CompanyName);
		if (!"".equals(Address) && !"".equals(CompanyName)) {
			if (mapprovince.size() <= 0) {
				List<OpasProvince> provinces = getAddressListService().queryProvinceDetail();
				if (provinces != null && provinces.size() > 0) {
					this.do_province(provinces);
				}
			}
			if (mapcity.size() <= 0) {
				List<OpasCity> citys = getAddressListService().queryCityDetail();
				if (citys != null && citys.size() > 0) {
					this.do_city(citys);
				}
			}
			if (listarea.size() <= 0) {
				listarea = getAddressListService().queryAreaDetail();
			}
			if (listtown.size() <= 0) {
				listtown = getAddressListService().queryTownDetail();
			}

			if ("1".equals(WorkType)) {
				lev = this.is_jiguan(WorkType, Address, CompanyName);
			} else {// 非机关事业单位
				lev = this.no_jiguan(WorkType, Address, CompanyName);
			}
		}
		return lev;
	}

	public Map<String, OpasProvince> do_province(List<OpasProvince> provinces) {
		for (int i = 0; i < provinces.size(); i++) {
			OpasProvince protmp = provinces.get(i);
			mapprovince.put(protmp.getProId(), protmp);
		}
		return mapprovince;

	}

	public Map<String, List<OpasCity>> do_city(List<OpasCity> citys) {
		List<OpasCity> listcity = null;
		for (int i = 0; i < citys.size(); i++) {
			OpasCity city = citys.get(i);
			if (mapcity.get(city.getProId()) == null) {
				listcity = new ArrayList<OpasCity>();
				listcity.add(city);
				mapcity.put(city.getProId(), listcity);
			} else {
				listcity.add(city);
				mapcity.put(city.getProId(), listcity);
			}
		}
		return mapcity;

	}

	// 机关事业单位判断逻辑
	private String is_jiguan(String WorkType, String Address, String CompanyName) {
		String lev = "5";
		String provinceid = "";
		String cityid = "";
		// 匹配省，直辖市
		if (mapprovince != null && mapprovince.size() > 0) {
			for (Map.Entry<String, OpasProvince> entry : mapprovince.entrySet()) {
				if (CompanyName.contains(entry.getValue().getProName())
						|| Address.contains(entry.getValue().getProName())) {
					provinceid = entry.getKey();
					lev = entry.getValue().getLev();
					break;
				}
			}
		}
		logger.info("匹配到的省份ID" + provinceid);
		// 匹配市
		List<OpasCity> listcity = mapcity.get(provinceid);
		if (listcity == null || listcity.size() <= 0) {
			return lev;
		}
		for (int i = 0; i < listcity.size(); i++) {
			if (CompanyName.contains(listcity.get(i).getCityName())
					|| Address.contains(listcity.get(i).getCityName())) {
				lev = listcity.get(i).getLev();
				cityid = listcity.get(i).getCityId();
				break;
			}
		}
		// 匹配区和县
		if (listarea != null && listarea.size() > 0) {
			for (int i = 0; i < listarea.size(); i++) {
				if (cityid.equals(listarea.get(i).getCityId())) {
					if (CompanyName.contains(listarea.get(i).getAreaName())
							|| Address.contains(listarea.get(i).getAreaName())) {
						lev = listarea.get(i).getLev();
						break;
					}
				}
			}
		}
		// List<OpasTown> listtown=Opas001_Dao.getListtown();
		if (listtown != null && listtown.size() > 0) {
			for (int i = 0; i < listtown.size(); i++) {
				if (Address.contains(listtown.get(i).getTownName())) {
					lev = listtown.get(i).getLev();
					break;
				}
			}
		}
		return lev;
	}

	// 非机关事业单位判断逻辑
	public String no_jiguan(String WorkType, String Address, String CompanyName) {

		String lev = "5";
		String provinceid = "";
		String cityid = "";
		// 匹配省，直辖市
		if (mapprovince != null && mapprovince.size() > 0) {
			for (Map.Entry<String, OpasProvince> entry : mapprovince.entrySet()) {
				if (Address.contains(entry.getValue().getProName())) {
					provinceid = entry.getKey();
					lev = entry.getValue().getLev();
					break;
				}
			}
		}
		if ("".equals(provinceid))
			return lev;

		logger.info("匹配到的省份ID" + provinceid);
		// 匹配市
		if (mapcity != null && mapcity.size() > 0) {
			List<OpasCity> listcity = mapcity.get(provinceid);
			if (listcity != null && listcity.size() > 0) {
				for (int i = 0; i < listcity.size(); i++) {
					if (Address.contains(listcity.get(i).getCityName())) {
						lev = listcity.get(i).getLev();
						cityid = listcity.get(i).getCityId();
						break;
					}
				}
			}
		}
		logger.info("匹配到的市ID" + cityid);
		// 匹配区和县
		// List<OpasArea> listarea=Opas001_Dao.getListarea();
		if (listarea != null && listarea.size() > 0) {
			for (int i = 0; i < listarea.size(); i++) {
				if (cityid.equals(listarea.get(i).getCityId())) {
					if (Address.contains(listarea.get(i).getAreaName())) {
						lev = listarea.get(i).getLev();
						break;
					}
				}
			}
		}
		// 匹配街道
		// List<OpasTown> listtown=Opas001_Dao.getListtown();
		if (listtown != null && listtown.size() > 0) {
			for (int i = 0; i < listtown.size(); i++) {
				if (Address.contains(listtown.get(i).getTownName())) {
					lev = listtown.get(i).getLev();
					break;
				}
			}
		}
		return lev;
	}

	public QueryAddressListService getAddressListService() {
		return addressListService;
	}

	public void setAddressListService(QueryAddressListService addressListService) {
		this.addressListService = addressListService;
	}
}
