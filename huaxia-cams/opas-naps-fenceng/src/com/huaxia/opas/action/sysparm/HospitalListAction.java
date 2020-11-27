package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Hospital;
import com.huaxia.opas.service.sysparm.HospitalService;
import com.huaxia.opas.util.SequenceUtil;
/**
 * 营销员白名单
 * @author hxb
 *
 */
public class HospitalListAction implements Action{

	@Resource
	private HospitalService hospitalService;
	
	/**
	 * 查询功能实现
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryHospitalList(Context ctx) throws CoreException{
		Gson gson = new Gson();
		Hospital Hospital = gson.fromJson(gson.toJson(ctx.getDataMap()), Hospital.class);
		int curNum = 0;
		Object pageObj = ctx.getDataMap().get("page");
		int curPage = Integer.parseInt(String.valueOf(pageObj == null ? 0 : pageObj));
		Object rowsObj = ctx.getDataMap().get("rows");
		int pageNum = Integer.parseInt(String.valueOf(rowsObj == null ? 0 : rowsObj));
		if(curPage > 1){
			curNum = (curPage-1)*pageNum;
		}
		List<Hospital> list = new ArrayList<Hospital>();
		int count = hospitalService.queryHospitalCount(Hospital);
		if(count > 0){
			list = hospitalService.queryHospitalList(Hospital,curNum,pageNum);
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	
	//删除方法
	public void hospital_remove(Context ctx) {
		try {
			Hospital  Hospital = getHospital(ctx,"ids");
			hospitalService.deleteHospital(Hospital);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
		}
	}
	
	
	//保存
	public void hospital_save(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		Hospital Hospital = json.fromJson(json.toJson(map), Hospital.class);
		Hospital.setUuid(SequenceUtil.getUUID());
		List<Hospital> hospitalList=new ArrayList<Hospital>();
		hospitalList = hospitalService.checkIsExistHospital(Hospital);
		if(hospitalList != null && hospitalList.size()!=0){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", "数据库已存在该条纪录！");
		} else {
			hospitalService.insertHospital(Hospital);
		} 
		ctx.setData("isSuccess", true);
	}
	//更新
	public void hospital_update(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		Hospital Hospital = json.fromJson(json.toJson(map), Hospital.class);
		hospitalService.updateHospital(Hospital);
		ctx.setData("isSuccess", true);
		
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public Hospital getHospital(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("crtTime")) {
			String createDateString = (String)map.get("crtTime");
			if(null!=createDateString && !createDateString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime  = sdf.parse(createDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("crtTime");
		}
		Gson gson = new Gson();
		Hospital hospital = gson.fromJson(gson.toJson(map), Hospital.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		//Hospital.setUserName(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				hospital.setIds(ids);
				break;
				
			case "crtTime":
				hospital.setCrtTime(createTime);
				break;
				
			default:
				break;
			}
		}
		return hospital;
	}
}
