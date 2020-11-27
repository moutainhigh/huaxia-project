package com.huaxia.opas.action.sysparm;

import java.io.DataInputStream;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.InCreditInfo;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.sysparm.CreditInfoService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;


public class CreditInfoServlet extends ServletProxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6892490892409199290L;
	
	@Value("${upload.newpath}")
	public  String uploadPath;
	
	
	@Autowired
	private CreditInfoService creditInfoService ;
	@Autowired
	private ApDictService apDictService  ;
	@Autowired
	private ApDictDetailService apDictDetailService ;
	
	
	public CreditInfoService getCreditInfoService() {
		return creditInfoService;
	}

	public void setCreditInfoService(CreditInfoService creditInfoService) {
		this.creditInfoService = creditInfoService;
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

	public CreditInfoServlet() {
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
			String fileName ="";
			List<CreditWhiteList> list = null;
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
				fileType = dictDetail.get("标准卡征信信息名单");
				BatchfileInfo batchfileInfo = new BatchfileInfo(id, fileName, new Date(), null, fileType, userCode, userName, null, "1");
				file = new File(uploadPath.concat(fileName));
				in = new FileInputStream(file);
				list = readExcel(in ,textBody,userName);
				
				int counts = creditInfoService.insertCreditInfoList(list, batchfileInfo);
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
	
	//获取字典表相关数据
	public Map<String, String> getDictDetail(String dictCode) throws CoreException{
		Map<String, String> map = new HashMap<>();
		ApDict apDict = apDictService.queryApDictByDictCode(dictCode);
		List<ApDictDetail> list = apDictDetailService.queryDictDetailListByDictId(apDict.getDictId());
		for (ApDictDetail apDictDetail : list) {
			map.put(apDictDetail.getDictDetailName(), apDictDetail.getDictDetailCode());
		}
		return map;
	}
	
	
	
	public List<CreditWhiteList> readExcel(InputStream in ,String textBody,String userName) throws Exception {
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
		
		List<CreditWhiteList> creditWhiteListList = new ArrayList<CreditWhiteList>();
		//格式化时间
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
				CreditWhiteList creditWhiteList = new CreditWhiteList();
				Row row = sheet.getRow(r);
//				int cellCount = row.getPhysicalNumberOfCells();//获取不为空的总列数，中间有空则不符合业务要求
				int cellCount = row.getLastCellNum();//获取最后一个不为空的是第几列
				//遍历,获取每一列
				for (int c = 0; c < cellCount; c++) {
					Cell cell = row.getCell(c);//获得列值
					if(cell != null){
						switch(c){
						case InCreditInfo.APP_ID://申请件编号	
							String appId = cell.toString();
							creditWhiteList.setAppId(appId);
							break;
						case InCreditInfo.COMPANY_NAME://单位名称
							String companyName = cell.toString();
							creditWhiteList.setCompanyName(companyName);
							break;
						case InCreditInfo.COMPANY_TEL://单位电话
							String companyTel = cell.toString();
							creditWhiteList.setCompanyTel(companyTel);
							break;
						case InCreditInfo.COMPANY_DEPT://部门名称
							String companyDept = companyDeptMap.get(cell.toString());
							creditWhiteList.setCompanyDept(companyDept);
							break;
						case InCreditInfo.COMPANY_ADDR://单位地址
							String companyAddr = cell.toString();
							creditWhiteList.setCompanyAddr(companyAddr);
							break;
						case InCreditInfo.LEGALER://法人
							String legaler = cell.toString();
							creditWhiteList.setLegaler(legaler);
							break;	
						case InCreditInfo.REGISTER_FUND://注册资金
							String registerFund = cell.toString();
							creditWhiteList.setRegisterFund(registerFund);
							break;	
						case InCreditInfo.REGISTER_ADDR://注册地址
							String registerAddr = cell.toString();
							creditWhiteList.setRegisterAddr(registerAddr);
							break;	
						case InCreditInfo.MANAGE_STATUS://经营状态
							String manageStatus = cell.toString();
							creditWhiteList.setManageStatus(manageStatus);
							break;	
						case InCreditInfo.PARTNER_INFO://股东信息
							String partnerInfo = cell.toString();
							creditWhiteList.setPartnerInfo(partnerInfo);
							break;	
						case InCreditInfo.MANAGEXCEPTION_INFO://经营异常信息
							String manageExceptionInfo = cell.toString();
							creditWhiteList.setManageExceptionInfo(manageExceptionInfo);
							break;
						case InCreditInfo.CURR_STATUS://当前状态
							String currStatus = currStatusMap.get(cell.toString());
							creditWhiteList.setCurrStatus(currStatus);
							break;
						}
					}
				}
				//生成UUID
				UUID uuid = UUID.randomUUID();
				creditWhiteList.setAutoId(uuid.toString().replace("-", ""));
				creditWhiteList.setCreateTime(new Date());
				creditWhiteList.setOperator(userName);
				creditWhiteListList.add(creditWhiteList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return creditWhiteListList;
	}	
	
	
}
