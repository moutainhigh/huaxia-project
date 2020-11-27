package com.huaxia.opas.action.sysparm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.InCreditTel;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.BatchfileLowRiskInfo;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.sysparm.BatchFileLowRiskInfoService;
import com.huaxia.opas.service.system.KeyValueConfigureService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class UploadLowRiskCustomersServlet extends ServletProxy {
	private final static Logger logger = LoggerFactory.getLogger(UploadLowRiskCustomersServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -6892490892409199290L;
	private KeyValueConfigureService  keyValueConfigureService;
	private BatchFileLowRiskInfoService batchFileLowRiskInfoService;
//	ComboPooledDataSource comboPooledDataSource;
	public  String uploadPath;
	public void init() {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());  
	       keyValueConfigureService  = (KeyValueConfigureService) wac.getBean("keyValueConfigureService");
	        batchFileLowRiskInfoService   = (BatchFileLowRiskInfoService) wac.getBean("batchFileLowRiskInfoService");
		uploadPath = keyValueConfigureService.getValueContentByKeyName("NEWLOWRISKPATH");   
//		comboPooledDataSource = (ComboPooledDataSource) wac.getBean("dataSource");
	}
	public BatchFileLowRiskInfoService getBatchFileLowRiskInfoService() {
		return batchFileLowRiskInfoService;
	}

	public void setBatchFileLowRiskInfoService(BatchFileLowRiskInfoService batchFileLowRiskInfoService) {
		this.batchFileLowRiskInfoService = batchFileLowRiskInfoService;
	}


	public UploadLowRiskCustomersServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CoreException {
		FileInputStream in = null; 
		PrintWriter out = null;
		File file = null;
		//主键Id
		String id = SequenceUtil.getUUID();
		String userName ="";
		String userCode= "";
		String fileType = "";
		StringBuffer fileNameBuffer =new StringBuffer("");
		List<CreditTelcheckList> list = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			//读取请求Body
			byte[] body = readBody(request,fileNameBuffer);
			//取得所有Body内容的字符串表示
			String textBody = new String(body, "ISO-8859-1");
			//取得上传的文件名称
//			fileName = new String(getFileName(textBody).getBytes("ISO-8859-1"), "UTF-8");
			//取得文件开始与结束位置
//			Position p = getFilePosition(request, textBody);
			//输出至文件
//			writeTo(fileName, body, p);
			
			Map<String,String> map = getOperatorInfo(textBody);
//			userName = request.getParameter("userName");
//			userName = request.getParameter("userCode");
			userName = new String(map.get("userName").getBytes("ISO-8859-1"),"UTF-8");
			userCode = map.get("userCode");
			BatchfileLowRiskInfo batchfileLowRiskInfo = new BatchfileLowRiskInfo(id, fileNameBuffer.toString(), new Date(), null, userCode, userName, null, "1");
			
			try{
				batchFileLowRiskInfoService.insert(batchfileLowRiskInfo);
			}catch(Exception e){
				logger.error("异常信息[ {} ]。",new Object[] { e.getMessage() });
			}
//			file = new File(uploadPath.concat(fileName));
//			in = new FileInputStream(file);
//			list = readExcel(in ,textBody,userName);
//			int counts = creditTelcheckService.insertCreditTelcheckList(list, batchfileInfo);
			response.setContentType("text/html");
	        out = response.getWriter();
//	        out.print("success_" + counts);
	        out.print("success_");
		} catch (Exception e) {
			out = response.getWriter();
			String message = e.getMessage();
			System.out.println("false_"+message);
			out.print("false_"+message);
		}finally {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
//			//数据加载完成，删除磁盘上的临时文件。---不删除文件
//			if(null != file){
//				file.delete();
//			}
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
	private byte[] readBody(HttpServletRequest request,	StringBuffer fileName) throws Exception, CoreException {
	//获取请求文本字节长度
	int formDataLength = request.getContentLength();
	if(-1 == formDataLength){
		throw new CoreException("文件监听事件并非异常，可忽略");
	}
	
	//文件路径不存在就自动创建
			File fd = new File(uploadPath);
			if(!fd.exists()){
				fd.mkdirs();
			}
			//清空文件夹下所有文件,然后写入文件
			File[] files = fd.listFiles();
			if(files != null){
				//文件被占用无法删除，调用垃圾回收，回收内存占用文件
				System.gc();
				for(File f : files){
					//文件删除失败，被占用解析，给页面提示
					if(!f.delete()){
						throw new Exception("正在解析入库文件，请稍后再试");
					};
				}
			}
	//取得ServletInputStream输入流对象
	DataInputStream dataStream = new DataInputStream(request.getInputStream());
	byte body[] = new byte[1024];

	int length = dataStream.read(body);	
	//第一次读取输入流，文件头获取文件名
	String textBody = new String(body, "ISO-8859-1");
	fileName.append(new String(getFileName(textBody).getBytes("ISO-8859-1"), "UTF-8"));
	FileOutputStream fileOutputStream = new FileOutputStream(uploadPath + fileName);
	DataOutputStream dataOutStream = new DataOutputStream(fileOutputStream);
	 dataOutStream.write(body,0,length);
	if(length != -1){
		while ((length = dataStream.read(body)) != -1) {
			 dataOutStream.write(body,0,length);
			}
	}
	dataOutStream.flush();
	dataOutStream.close();
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
	private void writeTo(String fileName, byte[] body, Position p) throws Exception {
	//文件路径不存在就自动创建
	File fd = new File(uploadPath);
	if(!fd.exists()){
		fd.mkdirs();
	}
	//清空文件夹下所有文件,然后写入文件
	File[] files = fd.listFiles();
	if(files != null){
		//文件被占用无法删除，调用垃圾回收，回收内存占用文件
		System.gc();
		for(File f : files){
			//文件删除失败，被占用解析，给页面提示
			if(!f.delete()){
				throw new Exception("正在解析入库文件，请稍后再试");
			};
		}
	}
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
	}// </editor-fold>
	
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
	
	
	public List<CreditTelcheckList> readExcel(InputStream in,String textBody,String userName) throws Exception {
		// 部门名称Map
		Map<String,String> companyDeptMap = new HashMap<String,String>();
		companyDeptMap.put("人力", "01");
		companyDeptMap.put("财务", "02");
		companyDeptMap.put("行政", "03");
		companyDeptMap.put("办公室", "04");
		companyDeptMap.put("总机", "05");
		companyDeptMap.put("其它", "06");
		
		// 当前状态Map
		Map<String,String> currStatusMap = new HashMap<String,String>();
		currStatusMap.put("启用","1");
		currStatusMap.put("停用","0");
		
		
		List<CreditTelcheckList> list=new ArrayList<CreditTelcheckList>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		try{
			Workbook workbook = WorkbookFactory.create(in);
			@SuppressWarnings("unused")
			int sheetCount = workbook.getNumberOfSheets();
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			// 遍历每一行 第一行为标题行
			for (int r = 1; r < rowCount; r++) {
				CreditTelcheckList creditTelcheckList = new CreditTelcheckList();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells();//获取总列数
				//遍历,获取每一列
				for (int c = 0; c < cellCount; c++) {
					Cell cell = row.getCell(c);//获得列值
					if(cell != null){
						switch(c){
						case InCreditTel.APP_ID://申请件编号	
							String appId = cell.toString();
							creditTelcheckList.setAppId(appId);
							break;
						case InCreditTel.COMPANY_NAME://单位名称
							String companyName = cell.toString();
							creditTelcheckList.setCompanyName(companyName);
							break;
						case InCreditTel.COMPANY_TEL://单位电话
							String companyTel = cell.toString();
							creditTelcheckList.setCompanyTel(companyTel);
							break;
						case InCreditTel.COMPANY_DEPT://部门名称
							String companyDept = companyDeptMap.get(cell.toString());
							creditTelcheckList.setCompanyDept(companyDept);
							break;
						case InCreditTel.TEL_SOURCE://电话来源
							String telSource = cell.toString();
							creditTelcheckList.setTelSource(telSource);
							break;
						case InCreditTel.MEMO://备注
							String memo = cell.toString();
							creditTelcheckList.setMemo(memo);
							break;	
						case InCreditTel.CURR_STATUS://当前状态
							String currStatus = currStatusMap.get(cell.toString());
							creditTelcheckList.setCurrStatus(currStatus);
							break;
						}
					}
				}
				//生成UUID
				UUID uuid = UUID.randomUUID();
				creditTelcheckList.setAutoId(uuid.toString().replace("-", ""));
				creditTelcheckList.setCreateTime(new Date());
				creditTelcheckList.setOperator(userName);
				list.add(creditTelcheckList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}	
	/**
	 * 测试删除文件夹文件的方法
	 * @param args
	 */
	public static void main(String args[]){
		File fd = new File("/app/opas/lowristlist/");
		//清空文件夹下所有文件,然后写入文件
		File[] files = fd.listFiles();
		if(files != null){
			for(File f : files){
				f.delete();
			}
		}
	}
}
