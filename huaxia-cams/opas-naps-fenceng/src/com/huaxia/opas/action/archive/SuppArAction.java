package com.huaxia.opas.action.archive;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.service.archive.SuppArService;
import com.ibm.icu.text.SimpleDateFormat;

import jxl.Workbook;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class SuppArAction implements Action{
	private static Logger logger = LoggerFactory.getLogger(SuppArAction.class);

	@Resource(name = "suppArService")
	private SuppArService suppArService;
	
	/**
	 * 查出补件信息
	 * @throws ParseException 
	 */
	public void listSuppAr(Context ctx){
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		SuppArchive suppArchive = gson.fromJson(gson.toJson(map), SuppArchive.class);
		
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		List<SuppArchive> list = new ArrayList<SuppArchive>();
		int count = 0;
		count = suppArService.querySuppArCount(suppArchive);
		if (count > 0) {
			list = suppArService.querySuppArList(suppArchive, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
		
	}
	
	
	/**
	 * 修改待归档补件信息状态并查出该补件信息
	 * @throws ParseException 
	 */
	public void updateAndSearchSupp(Context ctx){
		int sign = updateSuppArFlag(ctx);
		if(sign>0){
			ctx.setData("isSuccess", true);
		}else if(sign==0){
			ctx.setData("isNull", true);
		}else{
			System.out.println("更改补件码标识状态出现问题");
			logger.error("更改补件码标识状态出现问题.....");
		}
		
	}
	
	/**
	 * 修改待归档补件信息状态
	 * @throws ParseException 
	 */
	public int updateSuppArFlag(Context ctx){
		/*Gson gson = new Gson();*/
		Map map = ctx.getDataMap();
		SuppArchive suppArchive = new SuppArchive();
		/*SuppArchive suppArchive = gson.fromJson(gson.toJson(map), SuppArchive.class);*/
		suppArchive.setPatchCode((String) map.get("patchCode_input"));
		return suppArService.updateSuppArFlag(suppArchive);
	}
	
	/**
	 * 修改待归档补件信息为已归档
	 * @throws CoreException 
	 *
	 */
	public void updateSuppToAr(Context ctx) throws CoreException{
		Map map = ctx.getDataMap();
		try {
			List patchCodeList = (ArrayList) map.get("patchCode");
			if (patchCodeList == null || patchCodeList.isEmpty()) {
				throw new CoreException("请选择补件信息!");
			}
			for (int i = 0; i < patchCodeList.size(); i++) {
				SuppArchive suppArchive = new SuppArchive();
				suppArchive.setPatchCode((String) patchCodeList.get(i));
				suppArService.updateSuppToAr(suppArchive);
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			throw e;
		}
	}
	
	/**
	 * 修改列表中全部待归档补件信息为已归档
	 * @throws Exception 
	 * @throws CoreException 
	 *
	 */
	public void updateSuppToArAll(Context ctx) throws Exception{
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		SuppArchive suppArchive = gson.fromJson(gson.toJson(map), SuppArchive.class);
		try {
			suppArService.updateSuppToArAll(suppArchive);
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 放弃该补件信息
	 * @throws Exception 
	 * @throws CoreException 
	 *
	 */
	public void giveUpSupp(Context ctx) throws CoreException{
		Map map = ctx.getDataMap();
		try {
			List patchCodeList = (ArrayList) map.get("patchCode");
			if (patchCodeList == null || patchCodeList.isEmpty()) {
				throw new CoreException("请选择补件信息!");
			}
			for (int i = 0; i < patchCodeList.size(); i++) {
				SuppArchive suppArchive = new SuppArchive();
				suppArchive.setPatchCode((String) patchCodeList.get(i));
				suppArService.updateSuppToDel(suppArchive);
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			throw e;
		}
	}
	
	/**
	 * 导出指定时间的成功归档补件信息生成excle文件
	 */
	public void expToExcel(Context ctx){
		Map map = ctx.getDataMap();
		String pathUrl =(String) map.get("path");
		String operatTimeStr = (String)map.get("operatTime");
		String sign = (String) map.get("sign");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date operatTime = null;
		List<SuppArchive> list = new ArrayList<SuppArchive>();
		try {
			if(operatTimeStr.equals("")||operatTimeStr == null){
				operatTimeStr = sdf.format(new Date());
			}
			operatTime = sdf.parse(operatTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(sign.equals("0") ||sign == "0" ){
		SuppArchive suppArchive = new SuppArchive();
		suppArchive.setOperatTime(operatTime);
		list = suppArService.querySuccessList(suppArchive);
		}else if(sign.equals("1") ||sign == "1"){
				List patchCodeList = (ArrayList) map.get("patchCode");
				for (int i = 0; i < patchCodeList.size()-1; i++) {
					SuppArchive suppArchive = new SuppArchive();
					suppArchive.setPatchCode((String) patchCodeList.get(i));
					suppArchive.setOperatTime(operatTime);
					List targetlist = suppArService.querySuccessList(suppArchive);
					list.addAll(targetlist);
				}
		}else if(sign.equals("2") ||sign == "2"){
			SuppArchive suppArchive = new SuppArchive();
			suppArchive.setOperatTime(operatTime);
			String[] conditions = ((String) map.get("conditions")).split("@");
			if(conditions.length == 1){
				suppArchive.setPatchCode(conditions[0]);
			}else if(conditions.length == 2){
				suppArchive.setPatchCode(conditions[0]);
				suppArchive.setCustName(conditions[1]);
			}else if(conditions.length == 3){
				suppArchive.setPatchCode(conditions[0]);
				suppArchive.setCustName(conditions[1]);
				suppArchive.setCredNo(conditions[2]);
			}
			list = suppArService.querySuccessList(suppArchive);
		}
		if(list.size() == 0){
			ctx.setData("isNone", true);
			return;
		}
		int flag = createExlFile(pathUrl,operatTimeStr,list,sign);
		if(flag == 1){
			ctx.setData("isSucc", true);
		}else if(flag == 2){
			ctx.setData("isExist", true);
		}
		
	}
	
	/**创建excle文件
	 * */
	public int createExlFile(String path,String operatTimeStr,List contextList,String sign){
		String dateStr = operatTimeStr.replace("-", "");
		File url = new File(path);
		if(!url.exists()&& !url.isDirectory()){
			url.mkdirs();
		}
		String fileName = "";
		if(sign.equals("0")||sign == "0"){
			fileName = "succSupApp_"+dateStr+".xls";
		}else if(sign.equals("1")||sign == "1"){
			fileName = "selectSupApp_"+dateStr+".xls";
		}else if(sign.equals("2")||sign == "2"){
			fileName = "querySupApp_"+dateStr+".xls";
		}
		String fileStr = path+fileName;
		int rowNum = contextList.size();
		int columnNum = 4;
		SuppArchive sa = null;
		 //标题行
        String title[]={"客户姓名","补件码","申请流水号","证件号码"};
        //操作执行
        File targetFile = new File(fileStr);
        if(targetFile.exists()){
        	return 2;
        }
        try {
        	//设置标题字体为黑体，背景颜色为灰色
        	WritableFont boldFont = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
        	WritableCellFormat greyBackGround = new WritableCellFormat(boldFont);
        	greyBackGround.setBackground(Colour.GRAY_25);
            WritableWorkbook book= Workbook.createWorkbook(new File(fileStr));
            //生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet=book.createSheet("Sheet",0);
             
            //写入标题
            for(int i=0;i<columnNum;i++)    //title
                sheet.addCell(new Label(i,0,title[i],greyBackGround));
            //写入内容
                for(int j=0;j<rowNum;j++)//行
                {
                	sa = (SuppArchive) contextList.get(j);
                    sheet.addCell(new Label(0,j+1,sa.getCustName()));
                    sheet.addCell(new Label(1,j+1,sa.getPatchCode()));
                    sheet.addCell(new Label(2,j+1,sa.getAppId()));
                    sheet.addCell(new Label(3,j+1,sa.getCredNo()));
                }
           
             
            /*合并单元格.合并既可以是横向的，也可以是纵向的
             *WritableSheet.mergeCells(int m,int n,int p,int q);   表示由(m,n)到(p,q)的单元格组成的矩形区域合并
             * */
           /* sheet.mergeCells(0,1,0,2);
            sheet.mergeCells(0,3,0,4);*/
             
            //写入数据
            book.write();
            //关闭文件
            book.close();
        }
        catch(Exception e) {
        	logger.error("异常："+e.getMessage());
        	e.printStackTrace();
        }
        return 1;
	}
}
