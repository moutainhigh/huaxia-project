package com.huaxia.opas.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.PoliceGatherBlDao;
import com.huaxia.opas.dao.PoliceGatherDao;
import com.huaxia.opas.domain.Police;
import com.huaxia.opas.domain.Police.Output;
import com.huaxia.opas.domain.Police.Rts;
import com.huaxia.opas.domain.Police.Rts.Row;
import com.huaxia.opas.domain.Police.Rts.Row.RowOutput;
import com.huaxia.opas.service.PoliceService;

@Service("policeService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PoliceServiceImpl implements PoliceService {

	@Autowired
	private PoliceGatherDao policeGatherDao;

	@Autowired
	private PoliceGatherBlDao policeGatherBlDao;

	@Override
	public int save(Police police) {
		int result = 0;
		Map<String, Object> entity = new HashMap<String, Object>();
		entity.put("appId", police.getAppId());

		Output output = police.getOutput();
		if (output.getErrorMessage() != null) {
			entity.put("errorMessage", output.getErrorMessage());
			entity.put("errorMessageCol", output.getErrorMessageCol());
			
			result = getPoliceGatherDao().insertFailure(entity);
		} else {
			// 身份核查信息
			entity.put("gmsfhm", output.getGmsfhm());
			entity.put("result_gmsfhm", output.getResult_gmsfhm());
			entity.put("xm", output.getXm());
			entity.put("result_xm", output.getResult_xm());
			entity.put("cym", output.getCym());
			entity.put("result_cym", output.getResult_cym());
			entity.put("xb", output.getXb());
			entity.put("result_xb", output.getResult_xb());
			entity.put("mz", output.getMz());
			entity.put("result_mz", output.getResult_mz());
			entity.put("csrq", output.getCsrq());
			entity.put("result_csrq", output.getResult_csrq());
			entity.put("ssssxq", output.getSsssxq());
			entity.put("result_ssssxq", output.getResult_ssssxq());
			entity.put("jgssx", output.getJgssx());
			entity.put("result_jgssx", output.getResult_jgssx());
			entity.put("csdssx", output.getCsdssx());
			entity.put("result_csdssx", output.getResult_csdssx());
			entity.put("zz", output.getZz());
			entity.put("result_zz", output.getResult_zz());
			entity.put("pcsmc", output.getPcsmc());
			entity.put("result_pcsmc", output.getResult_pcsmc());
			entity.put("fwcs", output.getFwcs());
			entity.put("result_fwcs", output.getResult_fwcs());
			entity.put("hyzk", output.getHyzk());
			entity.put("result_hyzk", output.getResult_hyzk());
			entity.put("whcd", output.getWhcd());
			entity.put("result_whcd", output.getResult_whcd());
			entity.put("xp", output.getXp());
			
			result += getPoliceGatherDao().insert(entity);
			
			// 失信信息
			Rts rts = police.getRts();
			if (rts != null) {
				String dn = rts.getDn();
				List<Row> rowList = rts.getRowList();
				if (rowList != null && rowList.size() > 0) {
					Map<String, Object> parameters = new HashMap<String, Object>();
					List<Map<String, Object>> rowMapList = new ArrayList<Map<String, Object>>();
					for (Row row : rowList) {
						Map<String, Object> rowMap = new HashMap<String, Object>();
						RowOutput rowOutput = row.getRowOutput();
						rowMap.put("appId", police.getAppId());
						rowMap.put("dn", dn);
						rowMap.put("result_name", rowOutput.getResult_name());
						rowMap.put("result_cardnum", rowOutput.getResult_cardnum());
						rowMap.put("result_case_code", rowOutput.getResult_case_code());
						rowMap.put("result_gist_unit", rowOutput.getResult_gist_unit());
						rowMap.put("result_area_name", rowOutput.getResult_area_name());
						rowMap.put("result_performance", rowOutput.getResult_performance());
						rowMap.put("result_disreput_type_name", rowOutput.getResult_disreput_type_name());
						rowMap.put("result_publish_date", rowOutput.getResult_publish_date());
						rowMapList.add(rowMap);
					}
					parameters.put("list", rowMapList);
					
					result += getPoliceGatherBlDao().insertBatch(parameters);
					rowMapList.clear();
					rowMapList = null;
				}
			}
		}

		return result;
	}

	@Override
	public int saveBatch(List<Police> policeList) {
		int result = 0;
		if (policeList != null && policeList.size() > 0) {
			for (int i = 0; i < policeList.size(); i++) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("appId", policeList.get(i).getAppId());
				parameters.put("list", policeList.get(i).getRts().getRowList());
				result += getPoliceGatherBlDao().insertBatch(parameters);
			}
		}
		return result;
	}
	
	@Override
	public int getCountByAppId(String appId) {
		return getPoliceGatherDao().selectCountByAppId(appId);
	}
	
	@Override
	public int getCountByCertNo(String certNo) {
		return getPoliceGatherDao().selectCountByCertNo(certNo);
	}

	public PoliceGatherDao getPoliceGatherDao() {
		return policeGatherDao;
	}

	public void setPoliceGatherDao(PoliceGatherDao policeGatherDao) {
		this.policeGatherDao = policeGatherDao;
	}

	public PoliceGatherBlDao getPoliceGatherBlDao() {
		return policeGatherBlDao;
	}

	public void setPoliceGatherBlDao(PoliceGatherBlDao policeGatherBlDao) {
		this.policeGatherBlDao = policeGatherBlDao;
	}

}
