package com.huaxia.opas.action.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import com.huaxia.opas.domain.sysparm.TeamName;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.sysparm.TeamService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;

public class TeamNameServlet extends ServletProxy {
	
	private static final long serialVersionUID = 1653272306851521255L;
	
	@Value("${upload.newpath}")
	public  String uploadPath;
	
//	ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"config/naps-config.xml","config/dubbo-reference.xml"});
	@Autowired
	private TeamService teamService;
	@Autowired
	private ApDictService apDictService ;
	@Autowired
	private ApDictDetailService apDictDetailService ;
	
	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
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

	public TeamNameServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			FileInputStream in = null; 
			PrintWriter out = null;
			File file = null;
			//主键Id
			String id = SequenceUtil.getUUID();
			String userName ="";
			String userCode= "";
			String fileType = "";
			String fileName ="";
			List<TeamName> list = null;
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
			fileType = dictDetail.get("团办名单");
			BatchfileInfo batchfileInfo = new BatchfileInfo(id, fileName, new Date(), null, fileType, userCode, userName, null, "1");
			
			file = new File(uploadPath.concat(fileName));
			in = new FileInputStream(file);
			Map<String,Object>	teamNameMap = readExcel(in, textBody ,userCode);
			list = (List<TeamName>) teamNameMap.get("teamNameMap");
//			TeamService teamService=(TeamService) ac.getBean("teamService");
			int counts = teamService.insertTeamNameFromFile(list, batchfileInfo);
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
	} catch (Exception e) {
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
	} catch (Exception e) {
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
//		ApDictService apDictService = (ApDictService)ac.getBean("apDictService");
//		ApDictDetailService apDictDetailService = (ApDictDetailService)ac.getBean("apDictDetailService");
		ApDict apDict = apDictService.queryApDictByDictCode(dictCode);
		List<ApDictDetail> list = apDictDetailService.queryDictDetailListByDictId(apDict.getDictId());
		for (ApDictDetail apDictDetail : list) {
			map.put(apDictDetail.getDictDetailName(), apDictDetail.getDictDetailCode());
		}
		return map;
	}
	
	public Map<String,Object> readExcel(InputStream in,String textBody,String userCode) throws Exception {
		List<TeamName> teamNameList=new ArrayList<TeamName>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
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
			int m = 0;
			// 遍历每一行 第一行为标题行
			for (; r < rowCount; r++) {
				m++;
				TeamName teamName = new TeamName();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
				// 遍历每一列
				for (int c = 0; c < 9; c++,colum = c) {
					Cell cell = row.getCell(c);
					int cellType = 0;
					if(cell!=null&&!"".equals(cell.toString())){
						cellType = cell.getCellType();
					}else if(c==7){
						cellType = 3;
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
							cellValue = fmt.format(cell.getDateCellValue()); // 日期型
						} else {
//							cellValue = String.valueOf(cell.getNumericCellValue()); // 数字
//							if(cellValue.contains(".")){
//								int indexOf = cellValue.indexOf(".");
//								cellValue = cellValue.substring(0, indexOf);
//							}
							BigDecimal bd = new BigDecimal(String.valueOf(cell.getNumericCellValue()));
							cellValue = bd.toPlainString();
							if(cellValue.contains(".")){
								cellValue = cellValue.split(".")[0];
							}
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN: // 布尔型
						cellValue = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_BLANK: // 空白
						cellValue = "启用";
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
					System.out.print(cellValue + " ");
					switch (c) {
						case 0: 
							teamName.setTeamId(cellValue);
							break;
						case 1:
							teamName.setCompany(cellValue);
							break;
						case 2:
							teamName.setApproveContent(cellValue);
							break;
						case 3: 
							teamName.setAuthCriterion(cellValue);
							break;
						case 4: 
							if(cellValue!=null&&!("".equals(cellValue))){
								teamName.setApproveTime(new SimpleDateFormat("yyyy-MM-dd").parse(cellValue));
							}
							break;
						case 5:
						    if(cellValue!=null&&!("".equals(cellValue))){
						    	teamName.setApproveVailudDate(new SimpleDateFormat("yyyy-MM-dd").parse(cellValue));
						    }
							break;
						case 6:  
							if("停用".equals(cellValue)){
								teamName.setStatus("0");
							}else{
								teamName.setStatus("1");
							}
							break;
						case 7: 
							teamName.setRemark(cellValue);
							break;
						default:
					}
				}
				String autoId = UUID.randomUUID().toString().replace("-", "");
				teamName.setOrderByNo(String.valueOf(m));
				teamName.setAutoId(autoId);
				teamName.setCrtDate(date);
				teamName.setCrtTime(date);
				teamName.setCrtUser(userCode);
				teamNameList.add(teamName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("false_"+(r+1) + "_" + (colum+1));
		}
		Map<String,Object> teamNameMap = new HashMap<>();
		teamNameMap.put("teamNameMap",teamNameList);
		return teamNameMap;
	}
}
