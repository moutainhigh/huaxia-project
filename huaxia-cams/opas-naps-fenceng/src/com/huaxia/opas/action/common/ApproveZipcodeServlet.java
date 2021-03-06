package com.huaxia.opas.action.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.InApproveZipcode;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.sysparm.ApproveZipcodeService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;

/**
 * 邮编区号维护上传文件
 * @author lipengfei
 *
 */
public class ApproveZipcodeServlet extends ServletProxy{
	private static final long serialVersionUID = -8994189620502525787L;

	@Value("${upload.newpath}")
	public  String uploadPath;
	
	/**
	 * 业务字典取值
	 */
	@Autowired
	private ApDictService apDictService;
	@Autowired
	private ApDictDetailService apDictDetailService ;

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

	/**
	 * 操作数据库
	 */
	@Autowired
	private ApproveZipcodeService approveZipcodeService;

	public ApproveZipcodeService getApproveZipcodeService() {
		return approveZipcodeService;
	}

	public void setApproveZipcodeService(ApproveZipcodeService approveZipcodeService) {
		this.approveZipcodeService = approveZipcodeService;
	}
	
	public ApproveZipcodeServlet(){
		super();
	}
	
	public void destroy() {
		super.destroy();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CoreException, ParseException {
		FileInputStream in = null;
		PrintWriter out = null;
		File file = null;
		// 主键Id
		String id = SequenceUtil.getUUID();
		String userName = "";
		String userCode = "";
		String fileType = "";
		String fileName = "";
		List<ApproveZipcode> list = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			// 读取请求Body
			byte[] body = readBody(request);
			// 取得所有Body内容的字符串表示
			String textBody = new String(body, "ISO-8859-1");
			// 取得上传的文件名称
			fileName = new String(getFileName(textBody).getBytes("ISO-8859-1"), "UTF-8");
			// 取得文件开始与结束位置
			Position p = getFilePosition(request, textBody);
			// 输出至文件
			writeTo(fileName, body, p);
			// 批量上传文件信息实体类相关的参数
			Map<String, String> map = getOperatorInfo(textBody);
			userName = new String(map.get("userName").getBytes("ISO-8859-1"), "UTF-8");
			userCode = map.get("userCode");
			// 主键Id
			id = SequenceUtil.getUUID();
			//业务字典取出导入提示
			Map<String, String> dictDetail = getDictDetail("WJLB");
			fileType = dictDetail.get("征信问题信息名单");
			BatchfileInfo batchfileInfo = new BatchfileInfo(id, fileName, new Date(), null, fileType, userName,
					userName, "导入成功", "1");
			file = new File(uploadPath.concat(fileName));
			in = new FileInputStream(file);
			list = readExcel(in, textBody, userName);
			int counts = approveZipcodeService.insertApproveZipcodeFromFile(list, batchfileInfo);
			// 数据加载完成,清除磁盘上的临时文件
			response.setContentType("text/html");
			out = response.getWriter();
			out.print("success_" + counts);
		} catch (CoreException e) {
			out = response.getWriter();
			String message = e.getMessage();
			out.print(message);
		} finally {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
			if (null != file) {
				file.delete();
			}
		}
	}

	// 构造类
	class Position {
		int begin;
		int end;

		public Position(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
	}

	private byte[] readBody(HttpServletRequest request) throws IOException, CoreException {
		// 获取请求文本字节长度
		int formDataLength = request.getContentLength();
		if(-1 == formDataLength){
			throw new CoreException("文件监听事件并非异常，可忽略");
		}
		// 取得ServletInputStream输入流对象
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
		// 取得文件区段边界信息
		String contentType = request.getContentType();
		String boundaryText = contentType.substring(contentType.lastIndexOf("=") + 1, contentType.length());
		// 取得实际上传文件的起始与结束位置
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

	
	//获取上传的操作用户名
	private Map<String,String> getOperatorInfo(String requestBody) {
		Map<String,String> hashMap = new HashMap<>();
		//获取用户名
		String userName = requestBody.substring(requestBody.indexOf("userName\"")+9 );
		String[] split = userName.split("-");
		userName = split[0].trim();
		//获取用户
		String userCode = requestBody.substring(requestBody.indexOf("userCode\"")+9 );
		split = userCode.split("-");
		userCode = split[0].trim();
		
		hashMap.put("userName", userName);
		hashMap.put("userCode", userCode);
		return hashMap;
	}
	
	private void writeTo(String fileName, byte[] body, Position p) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(uploadPath + fileName);
		fileOutputStream.write(body, p.begin, (p.end - p.begin));
		fileOutputStream.flush();
		fileOutputStream.close();
	}


	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
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
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
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

	
	public  List<ApproveZipcode> readExcel(FileInputStream in,String textBody ,String userName)throws CoreException {
		//用户类型Map
		Map<String,String> userFlagMap = new HashMap<String,String>();
		userFlagMap.put("标准卡账户", "1");
		userFlagMap.put("易达金账户", "2");
		//区号渠道Map
		Map<String,String> areaChannelMap = new HashMap<String,String>();
		areaChannelMap.put("线上", "0");
		areaChannelMap.put("线下", "1");
		areaChannelMap.put("均可", "2");
		//是否异地营销
		Map<String,String> remoteMarketMap = new HashMap<String,String>();
		remoteMarketMap.put("是", "1");
		remoteMarketMap.put("否", "0");		
		//异地营销城市分类
		Map<String,String> remoteMarketCityMap = new HashMap<String,String>();
		remoteMarketCityMap.put("城市分类一", "1");
		remoteMarketCityMap.put("城市分类二", "2");
		remoteMarketCityMap.put("城市分类三", "3");
		remoteMarketCityMap.put("城市分类四", "4");
		//异地营销城市状态
		String remoteMarket = null;
		Map<String,String> remoteMarketStatusMap = new HashMap<String,String>();
		remoteMarketStatusMap.put("启用","1");
		remoteMarketStatusMap.put("停用","0");
		
		List<ApproveZipcode> approveZipcodeList = new ArrayList<ApproveZipcode>();
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
				ApproveZipcode approveZipcode = new ApproveZipcode();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells();//获取总列数
				//将启停状态默认为启动
				approveZipcode.setStatus("1");
				//遍历,获取每一列
				for (int c = 0; c < cellCount; c++) {
					Cell cell = row.getCell(c);//获得列值
					if(cell != null){
						switch(c){
						case InApproveZipcode.ZIP_CODE://邮编代码	
							String zipCode = cell.toString();
							approveZipcode.setZipCode(zipCode);
							break;
						case InApproveZipcode.ACCT_TYPE://账户类型
							String acctType = userFlagMap.get(cell.toString());
							approveZipcode.setAcctType(acctType);
							break;
						case InApproveZipcode.PROVINCE://省份
							String province = cell.toString();
							approveZipcode.setProvince(province);
							break;
						case InApproveZipcode.CITY://城市
							String city = cell.toString();
							approveZipcode.setCity(city);
							break;
						case InApproveZipcode.TEL_NO://电话区号
							String telNo = cell.toString();
							approveZipcode.setTelNo(telNo);
							break;
						case InApproveZipcode.AREA_CHANNEL://区号渠道
							String areaChannel = areaChannelMap.get(cell.toString());
							approveZipcode.setAreaChannel(areaChannel);
							break;	
						case InApproveZipcode.REMOTE_MARKET://是否异地营销
							remoteMarket = remoteMarketMap.get(cell.toString());
							approveZipcode.setRemoteMarket(remoteMarket);
							break;	
						case InApproveZipcode.REMOTE_MARKETCITY://异地营销城市分类
							String remoteMarketCity = remoteMarketCityMap.get(cell.toString());
							if("1".equals(remoteMarketCity)){
								approveZipcode.setRemoteMarketCity(remoteMarketCity);
							}else{
								approveZipcode.setRemoteMarketCity("");								
							}
							break;	
						case InApproveZipcode.REMOTE_MARKETSTATUS://异地营销城市状态
							String remoteMarketStatus = remoteMarketStatusMap.get(cell.toString());
							if("1".equals(remoteMarketStatus)){
								approveZipcode.setRemoteMarketStatus(remoteMarketStatus);
							}else{
								approveZipcode.setRemoteMarketStatus("");								
							}
							break;	
						}
					}
				}
				UUID uuid = UUID.randomUUID();
				approveZipcode.setAutoId(uuid.toString().replace("-", ""));//uuid
				if("1".equals(remoteMarket)){
					approveZipcode.setRemoteStartDate(new Date());//异地营销城市启用时间	
				}
				approveZipcode.setCrtUser(userName);
				approveZipcode.setLstUpdUser(userName);
				approveZipcodeList.add(approveZipcode);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return approveZipcodeList;
	}
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	//业务字典取值的方法
	public Map<String, String> getDictDetail(String dictCode) throws CoreException{
		Map<String, String> map = new HashMap<>();
		ApDict apDict = apDictService.queryApDictByDictCode(dictCode);
		List<ApDictDetail> list = apDictDetailService.queryDictDetailListByDictId(apDict.getDictId());
		for (ApDictDetail apDictDetail : list) {
			map.put(apDictDetail.getDictDetailName(), apDictDetail.getDictDetailCode());
		}
		return map;
	}
}
