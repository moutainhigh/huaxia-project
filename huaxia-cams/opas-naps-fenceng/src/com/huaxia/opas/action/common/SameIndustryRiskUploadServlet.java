package com.huaxia.opas.action.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.SameIndustryRisk;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.sysparm.SameIndustryRiskService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;

/**
 * 同业关注风险名单的批量导入
 * @author 汪涛
 *
 */
public class SameIndustryRiskUploadServlet extends ServletProxy {
       
	private static final long serialVersionUID = 4972560425364005799L;
	
	@Value("${upload.newpath}")
	public  String uploadPath;
	
//	ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"config/naps-config.xml","config/dubbo-reference.xml"});
//	SameIndustryRiskService sameIndustryRiskService=(SameIndustryRiskService) ac.getBean("sameIndustryRiskService");
//	BatchFileInfoService batchFileInfoService = (BatchFileInfoService) ac.getBean("batchFileInfoService");
	@Autowired
	private SameIndustryRiskService sameIndustryRiskService;
	@Autowired
	private ApDictService apDictService ;
	@Autowired
	private ApDictDetailService apDictDetailService ;
	
    public SameIndustryRiskService getSameIndustryRiskService() {
		return sameIndustryRiskService;
	}

	public void setSameIndustryRiskService(SameIndustryRiskService sameIndustryRiskService) {
		this.sameIndustryRiskService = sameIndustryRiskService;
	}

	public ApDictService getApDictService() {
		return apDictService;
	}

	public void setApDictService(ApDictService apDictService) {
		this.apDictService = apDictService;
	}

	public ApDictDetailService getApDictDetailService() {
		return apDictDetailService;
	}

	public void setApDictDetailService(ApDictDetailService apDictDetailService) {
		this.apDictDetailService = apDictDetailService;
	}

	public SameIndustryRiskUploadServlet() {
        super();
    }
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CoreException, ParseException {
		FileInputStream in = null; 
		PrintWriter out = null;
		File file = null;
		//主键Id
		String id = SequenceUtil.getUUID();
		String userName ="";
		String userCode= "";
		String fileName ="";
		String fileType ="";
		List<SameIndustryRisk> sameIndustryRiskList = null;
		try{
			response.setContentType("text/html;charset=UTF-8");
			//读取请求Body
			byte[] body = readBody(request);
			//取得所有Body内容的字符串表示
			String textBody = new String(body, "ISO-8859-1");
			//取得上传的文件名称
			fileName = new String(getFileName(textBody).getBytes("ISO-8859-1"), "UTF-8");
			//取得文件开始与结束位置
			Position p = getFilePosition(request, textBody);
			//输出至文件
			writeTo(fileName, body, p);
			
			Map<String,String> map = getOperatorInfo(textBody);
			userName = new String(map.get("userName").getBytes("ISO-8859-1"),"UTF-8");
			userCode = map.get("userCode");
			//字典表WJLB里面的数据
			Map<String, String> dictDetail = getDictDetail("WJLB");
			fileType = dictDetail.get("同业关注风险名单");

			BatchfileInfo batchfileInfo = new BatchfileInfo(id, fileName, new Date(), null,fileType, userCode, userName, "导入成功", "1");
			
			file = new File(uploadPath.concat(fileName));
			in = new FileInputStream(file);
			sameIndustryRiskList= readExcel(in, textBody ,userCode);
			int counts = sameIndustryRiskService.insertSIRiskImportFile(sameIndustryRiskList, batchfileInfo);
			response.setContentType("text/html");
	        out = response.getWriter();
	        out.print("success_" + counts);
		} catch (Exception e) {
			out = response.getWriter();
			String message = e.getMessage();
			out.print(message);
		}finally {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
			//数据加载完成，删除磁盘上的临时文件。
			if(null != file){
				file.delete();
			}
		}
	}
			//构造类
	class Position {
		int begin;
		int end;
		public Position(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
	}
	private byte[] readBody(HttpServletRequest request) throws IOException, CoreException {
		//获取请求文本字节长度
		int formDataLength = request.getContentLength();
		if(-1 == formDataLength){
			throw new CoreException("文件监听事件并非异常，可忽略");
		}
		//取得ServletInputStream输入流对象
		DataInputStream dataStream = new DataInputStream(request.getInputStream());
		byte body[] = new byte[formDataLength];
		int totalBytes = 0;
		while (totalBytes < formDataLength) {
		int bytes = dataStream.read(body, totalBytes, formDataLength);
		totalBytes += bytes;
		}
		return body;
	}
	private Position getFilePosition(HttpServletRequest request, String textBody) throws IOException {
		//取得文件区段边界信息
		String contentType = request.getContentType();
		String boundaryText = contentType.substring(contentType.lastIndexOf("=") + 1, contentType.length());
		//取得实际上传文件的气势与结束位置
		int pos = textBody.indexOf("filename=\"");
		pos = textBody.indexOf("\n", pos) + 1;
		pos = textBody.indexOf("\n", pos) + 1;
		pos = textBody.indexOf("\n", pos) + 1;
		int boundaryLoc = textBody.indexOf(boundaryText, pos) - 4;
		int begin = ((textBody.substring(0, pos)).getBytes("ISO-8859-1")).length;
		int end = ((textBody.substring(0, boundaryLoc)).getBytes("ISO-8859-1")).length;
		return new Position(begin, end);
	}
	
	private String getFileName(String requestBody) {
		String fileName = requestBody.substring(requestBody.indexOf("filename=\"") + 10);
		fileName = fileName.substring(0, fileName.indexOf("\n"));
		fileName = fileName.substring(fileName.indexOf("\n") + 1, fileName.indexOf("\""));
		return fileName;
	}
	/**
	 * 获取上传操作用户名(userName)和用户代码(userCode)的MAP集合方法
	 * @param String requestBody 请求体
	 * @return Map<String,String> 上传的文件名
	 * */	
	private Map<String,String> getOperatorInfo(String requestBody) {
		Map<String,String> hashMap = new HashMap<>();
		//获取用户名
		String userName = requestBody.substring(requestBody.indexOf("userName\"")+9 );
		String[] split = userName.split("-");
		userName = split[0].trim();
		//获取用户名
		String userCode = requestBody.substring(requestBody.indexOf("userCode\"")+9 );
		split = userCode.split("-");
		userCode = split[0].trim();
		
		hashMap.put("userName", userName);
		hashMap.put("userCode", userCode);
		return hashMap;
	}	
	
	//获取字典表相关数据
	public Map<String, String> getDictDetail(String dictCode) throws CoreException{
		Map<String, String> map = new HashMap<>();
//		ApDictService apDictService = (ApDictService)ac.getBean("apDictService");
//		ApDictDetailService apDictDetailService = (ApDictDetailService)ac.getBean("apDictDetailService");
		ApDict apDict = apDictService.queryApDictByDictCode(dictCode);
		List<ApDictDetail> list = apDictDetailService.queryDictDetailListByDictId(apDict.getDictId());
		for (ApDictDetail apDictDetail : list) {
			map.put(apDictDetail.getDictDetailName(), apDictDetail.getDictDetailCode());
		}
		return map;
	}

	private void writeTo(String fileName, byte[] body, Position p) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(uploadPath + fileName);
		fileOutputStream.write(body, p.begin, (p.end - p.begin));
		fileOutputStream.flush();
		fileOutputStream.close();
	}
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	* Handles the HTTP
	* <code>GET</code> method.
	*
	* @param request servlet request
	* @param response servlet response
	* @throws ServletException if a servlet-specific error occurs
	* @throws IOException if an I/O error occurs
	*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
	}
	/**
	* Handles the HTTP
	* <code>POST</code> method.
	*
	* @param request servlet request
	* @param response servlet response
	* @throws ServletException if a servlet-specific error occurs
	* @throws IOException if an I/O error occurs
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
	}
	/**
	* Returns a short description of the servlet.
	*
	* @return a String containing servlet description
	*/
	@Override
	public String getServletInfo() {
		return "Short description";
	}
	
	/**
	 * 循环遍历表格获取数据封装成实体对象
	 * @throws Exception 
	 */
	public List<SameIndustryRisk> readExcel(InputStream in,String textBody,String userCode) throws Exception {
		
		List<SameIndustryRisk> sameIndustryRiskList=new ArrayList<SameIndustryRisk>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, String> dictMap = getDictDetail("ZDFXZJLX");
		int r = 1;
		int colum=0;
		try {
			// 同时支持Excel 2003、2007
			Workbook workbook = WorkbookFactory.create(in); // 这种方式 Excel
															// 2003/2007/2010
															// 都是可以处理的
			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			Date date = new Date();
			// 遍历每一行 第一行为标题行
			for (; r < rowCount; r++) {
				SameIndustryRisk sameIndustryRisk = new SameIndustryRisk();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
				// 遍历每一列
				for (int c = 0; c <10 ; c++,colum=c) {
					Cell cell = row.getCell(c);
					int cellType = 0;
					if(cell!=null&&!"".equals(cell.toString())){
						cellType = cell.getCellType();
					}else if(c ==9){
						cellType=3;
					}else{
						continue;
					}
					String cellValue = null;
					switch (cellType) {
						case Cell.CELL_TYPE_STRING: // 文本
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC: // 数字、日期
							if (DateUtil.isCellDateFormatted(cell)) {
								cellValue = sdf.format(cell.getDateCellValue()); // 日期型
							} else {
								cellValue = String.valueOf((int)cell.getNumericCellValue()); // 数字
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN: // 布尔型
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_BLANK: // 空白
							cellValue ="启用";
							break;
						case Cell.CELL_TYPE_ERROR: // 错误
							cellValue = "错误";
							break;
						case Cell.CELL_TYPE_FORMULA: // 公式
							cellValue = "错误";
							break;
						default:
							cellValue = "错误";
					}
				switch (c) {
					case 0: 
						sameIndustryRisk.setName(cellValue);
						break;
					case 1:
						sameIndustryRisk.setCertifiType(dictMap.get(cellValue));
						break;
					case 2:
						sameIndustryRisk.setCertifiNo(cellValue);
						break;
					case 3: 
						sameIndustryRisk.setSubmitBank(cellValue);
						break;
					case 4: 
						sameIndustryRisk.setStarLevle(cellValue);
						break;
					case 5: 
						if("司法机关".equals(cellValue)){
							sameIndustryRisk.setInfoChannel("1");
						}else if("监管机构".equals(cellValue)){
							sameIndustryRisk.setInfoChannel("2");
						}else if("人行内部".equals(cellValue)){
							sameIndustryRisk.setInfoChannel("3");
						}else if("审核系统".equals(cellValue)){
							sameIndustryRisk.setInfoChannel("4");
						}else if("同业".equals(cellValue)){
							sameIndustryRisk.setInfoChannel("5");
						}else if("其他".equals(cellValue)){
							sameIndustryRisk.setInfoChannel("6");
						}else{
							sameIndustryRisk.setInfoChannel("6");
						}
						break;
					case 6: 
						if("欺诈申请".equals(cellValue)){
							sameIndustryRisk.setFraudType("1");
						}else if("中介信息".equals(cellValue)){
							sameIndustryRisk.setFraudType("2");
						}else if("交易欺诈".equals(cellValue)){
							sameIndustryRisk.setFraudType("3");
						}else if("其他".equals(cellValue)){
							sameIndustryRisk.setFraudType("4");
						}else{
							sameIndustryRisk.setFraudType("4");
						}
						break;
					case 7: 
						sameIndustryRisk.setMemo(cellValue);
						break;
					case 8: 
						if(cellValue!=null&&"".equals(cellValue)==false){
							sameIndustryRisk.setInvalidTime(sdf.parse(cellValue));
						}
						break;
					case 9: 
						if ("停用".equals(cellValue)) {
							sameIndustryRisk.setCurrStatus("0");
						}else{
							sameIndustryRisk.setCurrStatus("1");
						}
						break;
					default:
				 }
			 }
			//设置id,auto_id,操作人，操作时间
				sameIndustryRisk.setId(UUID.randomUUID().toString().replace("-", ""));
				sameIndustryRisk.setAuto_id(UUID.randomUUID().toString().replace("-", ""));
				sameIndustryRisk.setOperator(userCode);
				sameIndustryRisk.setCreateTime(date);
				sameIndustryRisk.setOperatType("导入");
				sameIndustryRisk.setOperatTime(date);
				if(sameIndustryRisk.getCurrStatus()==null){
					sameIndustryRisk.setCurrStatus("1");
				}
				sameIndustryRiskList.add(sameIndustryRisk);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("false_"+(r+1) + "_" + (colum+1));
		}
		return sameIndustryRiskList;
	}

}
