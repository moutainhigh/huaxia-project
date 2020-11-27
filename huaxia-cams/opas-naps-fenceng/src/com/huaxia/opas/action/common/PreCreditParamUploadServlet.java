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
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PreCreditParam;
import com.huaxia.opas.service.sysparm.PreCreditParamService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;
/**
 * 预授信批量导入 
 * @author 汪涛
 *
 */
public class PreCreditParamUploadServlet extends ServletProxy {
       
	private static final long serialVersionUID = 4972560425364005799L;
	
	@Value("${upload.newpath}")
	public  String uploadPath;
	
//	ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"config/naps-config.xml","config/dubbo-reference.xml"});
//	PreCreditParamService preCreditParamService=(PreCreditParamService) ac.getBean("preCreditParamService");
	@Autowired
	private PreCreditParamService preCreditParamService;
	
	public PreCreditParamService getPreCreditParamService() {
		return preCreditParamService;
	}
	public void setPreCreditParamService(PreCreditParamService preCreditParamService) {
		this.preCreditParamService = preCreditParamService;
	}
	public PreCreditParamUploadServlet() {
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
		List<PreCreditParam> preCreditParamList = null;
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
			

			BatchfileInfo batchfileInfo = new BatchfileInfo(id, fileName, new Date(), null,"31", userCode, userName, "导入成功", "1");
			
			file = new File(uploadPath.concat(fileName));
			in = new FileInputStream(file);
			preCreditParamList= readExcel(in, textBody ,userCode);
			int counts = preCreditParamService.insertPreCreditParamImportFile(preCreditParamList, batchfileInfo);
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
	

	//循环遍历导入的表格，读取数据手动封装成预授信对象
	public List<PreCreditParam> readExcel(InputStream in,String textbody,String userCode) throws Exception {
		List<PreCreditParam> preCreditParamList = new ArrayList<PreCreditParam>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
				PreCreditParam preCreditParam = new PreCreditParam();
				Row row = sheet.getRow(r);
//				int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
				// 遍历每一列
				for (int c = 0; c < 10; c++,colum = c) {
					Cell cell = row.getCell(c);
					int cellType = 0;
					if(cell!=null&&!"".equals(cell.toString())){
						cellType = cell.getCellType();
					}else if(c==9){
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
							cellValue = String.valueOf((int) cell
									.getNumericCellValue()); // 数字
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
					switch (c) {
					case 0:
						preCreditParam.setBatchNo(cellValue);
						break;
					case 1:
						preCreditParam.setCustID(cellValue);
						break;
					case 2:
						preCreditParam.setPreSellLimit(Integer
								.parseInt(cellValue));
						break;
					case 3:
						preCreditParam.setStartDate(sdf.parse(cellValue));
						break;
					case 4:
						preCreditParam.setEndDate(sdf.parse(cellValue));
						break;
					case 5:
						preCreditParam.setCustName(cellValue);
						break;
					case 6:
						preCreditParam.setCertifiID(cellValue);
						break;
					case 7:
						preCreditParam.setPhoneNum(cellValue);
						break;
					case 8:
						if ("标准卡".equals(cellValue)) {
							preCreditParam.setAcctType("0");
						} else {
							preCreditParam.setAcctType("1");
						}
						break;
					case 9:
						if ("停用".equals(cellValue)) {
							preCreditParam.setStatus("0");
							preCreditParam.setStopDate(date);
						}else {
							preCreditParam.setStatus("1");
							preCreditParam.setBeginDate(date);
						}
						break;
					default:
					}
				}
				// 设置autoID,最后操作人，操作时间,创建时间，创建人,导入日期
				preCreditParam.setAutoID(UUID.randomUUID().toString()
						.replace("-", ""));
				preCreditParam.setImportDate(date);
				preCreditParam.setCrtUser(userCode);
				preCreditParam.setCrtDate(date);
				preCreditParam.setLstUpdDate(date);
				preCreditParam.setLstUpdUser(userCode);
				if(preCreditParam.getStatus()==null){
					preCreditParam.setStatus("1");
					preCreditParam.setBeginDate(date);
				}
				preCreditParamList.add(preCreditParam);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("false_"+(r+1) + "_" + (colum+1));
		}
		return preCreditParamList;
	}
	
}
