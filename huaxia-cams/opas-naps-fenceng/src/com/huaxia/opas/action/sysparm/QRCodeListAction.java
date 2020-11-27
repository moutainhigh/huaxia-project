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
import com.huaxia.opas.domain.sysparm.QRCode;
import com.huaxia.opas.service.sysparm.QRCodeService;
import com.huaxia.opas.util.SequenceUtil;
/**
 *  二维码名单维护参数表 action
 * @author zhangjunwen
 *
 */
public class QRCodeListAction implements Action{
	
	@Resource
	private QRCodeService qrCodeService;
	/**
	 * 查询功能实现
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryQRCode(Context ctx) throws CoreException{
		Gson gson = new Gson();
		QRCode qrCode = gson.fromJson(gson.toJson(ctx.getDataMap()), QRCode.class);
		int curNum = 0;
		Object pageObj = ctx.getDataMap().get("page");
		int curPage = Integer.parseInt(String.valueOf(pageObj == null ? 0 : pageObj));
		Object rowsObj = ctx.getDataMap().get("rows");
		int pageNum = Integer.parseInt(String.valueOf(rowsObj == null ? 0 : rowsObj));
		if(curPage > 1){
			curNum = (curPage-1)*pageNum;
		}
		List<QRCode> list = new ArrayList<QRCode>();
		int count = qrCodeService.queryQRCodeCount(qrCode);
		if(count > 0){
			list = qrCodeService.queryQRCodeList(qrCode,curNum,pageNum);
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	/**
	 * 添加 二维码名单维护
	 * @param ctx
	 * @throws CoreException
	 */
	public void addQRCode(Context ctx) throws CoreException{
		Gson json = new Gson();
		String operator = ctx.getData("userId");
		QRCode qrCode = json.fromJson(json.toJson(ctx.getDataMap()), QRCode.class);
		qrCode.setOperator(operator);
		qrCode.setLstUpdUser(operator);
		qrCode.setUuId(SequenceUtil.getUUID());
		List<QRCode> qrCodeList=new ArrayList<QRCode>();
		qrCodeList = qrCodeService.checkIsExistQR(qrCode);
		if(qrCodeList != null && qrCodeList.size()!=0){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", "数据库已存在该条纪录！");
		}
		else{
		try {
			if("1".equals(qrCode.getStatus())){
				qrCodeService.insertQRCodeStart(qrCode);
			}else if ("0".equals(qrCode.getStatus())){
				qrCodeService.insertQRCodeEnd(qrCode);
			}
			else if ("2".equals(qrCode.getStatus())){
				qrCodeService.insertQRCodeEnd(qrCode);
			}
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
		}
	}
	/**
	 * 修改
	 * @param ctx
	 * @throws CoreException
	 */
	public void updateQRCode(Context ctx) throws CoreException{
		Gson json = new Gson();
		QRCode qrCode = json.fromJson(json.toJson(ctx.getDataMap()), QRCode.class);
		String userId = ctx.getData("userId");
		String uuId = ctx.getData("uuId");
		qrCode.setLstUpdUser(userId);
		List<QRCode> qrCodeList=new ArrayList<QRCode>();
		qrCodeList = qrCodeService.checkIsExistQR(qrCode);
		if(qrCodeList != null && qrCodeList.size()!=0){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", "数据库已存在该条纪录！");
		}
		else{
		try {
			String status = qrCodeService.queryQRCodeStatus(uuId);
			if(qrCode.getStatus().equals(status)){
				qrCodeService.updateQRCodeWithoutStatus(qrCode);
			}else{
				qrCodeService.updateQRCodeAndStatus(qrCode);
			}
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		}
		ctx.setData("isSuccess", true);
	}
	/**
	 * 批量删除
	 * @param ctx
	 * @throws CoreException
	 */
	public void deleteQRCode(Context ctx) throws CoreException{
		try {
			QRCode  qrCode = getQRCode(ctx,"ids");
			qrCodeService.deleteQRCode(qrCode);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	/**
	 * 批量启用
	 * @param ctx
	 * @throws CoreException
	 */
	public void setStatusOK(Context ctx) throws CoreException{
		try {
			QRCode qrCode = getQRCode(ctx,"ids","Status");
			String userId = ctx.getData("userId");
			qrCode.setLstUpdUser(userId);
			qrCodeService.setQRCodeStatusOK(qrCode);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
			throw e;
		}
	}
	/**
	 *批量停用
	 * @param ctx
	 * @throws CoreException
	 */
	public void setStatusNO(Context ctx) throws CoreException{
		try {
			QRCode qrCode = getQRCode(ctx,"ids","Status");
			String userId = ctx.getData("userId");
			qrCode.setLstUpdUser(userId);
			qrCodeService.setQRCodeStatusNO(qrCode);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
			throw e;
		}
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public QRCode getQRCode(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("crtDate")) {
			String createDateString = (String)map.get("crtDate");
			if(null!=createDateString && !createDateString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime  = sdf.parse(createDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("crtDate");
		}
		Gson gson = new Gson();
		QRCode qrCode = gson.fromJson(gson.toJson(map), QRCode.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		qrCode.setOperator(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				qrCode.setIds(ids);
				break;
				
			case "Status":
				String Status = (String)map.get("Status");
				if( null == Status || (!Status.trim().equals("1") && !Status.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值Status非法");
					throw e;
				}
				break;
				
			case "crtDate":
				qrCode.setCrtDate(createTime);
				break;
				
			default:
				break;
			}
		}
		return qrCode;
	}

}
