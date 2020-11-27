package com.huaxia.opas.action.sysparm;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.huaxia.opas.domain.sysparm.VvipList;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.sysparm.VvipListService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;

/**
 * VVIP名单批量导入
 * 
 * @author liudi
 * @since 2017-03-28
 * @version 1.0
 */
public class VvipListServlet extends ServletProxy {



	/**
	 * 
	 */
	private static final long serialVersionUID = 5427057339704881325L;
	
	@Value("${upload.newpath}")
	public  String uploadPath;


	@Autowired
	private VvipListService vvipListService ;
	@Autowired
	private ApDictService apDictService  ;
	@Autowired
	private ApDictDetailService apDictDetailService ;
	
	public VvipListService getVvipListService() {
		return vvipListService;
	}

	public void setVvipListService(VvipListService vvipListService) {
		this.vvipListService = vvipListService;
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

	public VvipListServlet() {
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
		try {
		response.setContentType("text/html;charset=UTF-8");
		// 读取请求Body
		byte[] body = readBody(request);
		// 取得所有Body内容的字符串表示
		String textBody = new String(body, "ISO-8859-1");
		// 取得上传的文件名称
		String fileName = new String (getFileName(textBody).getBytes("ISO-8859-1"),"UTF-8");
		// 取得文件开始与结束位置
		Position p = getFilePosition(request, textBody);
		// 输出至文件
		writeTo(fileName, body, p);

		//名单批量上传文件信息实体类相关的参数
		Map<String, String> map = getOperatorInfo(textBody);
		String userName = map.get("userName");
		String userCode = map.get("userCode");
		//主键Id
		String id = SequenceUtil.getUUID();
		//字典表WJLB里面的数据
		Map<String, String> dictDetail = getDictDetail("WJLB");
		String fileType = dictDetail.get("VVIP名单信息");
		BatchfileInfo batchfileInfo = new BatchfileInfo(id, fileName, new Date(), null, fileType, userCode, userName, null, "1");
		
		file = new File(uploadPath.concat(fileName));
		in = new FileInputStream(file);
		List<VvipList> list = readExcel2(in, textBody ,userCode);
		System.out.println(list);
		int counts = vvipListService.saveVvipInfoList(list, batchfileInfo);
		
		response.setContentType("text/html");
		out = response.getWriter();
		/*out.println("  <script type='text/javascript'> alert('导入成功');window.history.back(-1);</script>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
		out.print("success_" + counts);
		} catch (CoreException e){
			out = response.getWriter();
			String message = e.getMessage();
			out.print(message);
		} finally {
			if(null != in){
				in.close();
			}
			if(null != out){
				out.close();
			}
			if(null != file){
				//数据加载完成，删除磁盘上的临时文件。
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
			throw new CoreException("页面刷新实时监听，并非错误");
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
		// 取得实际上传文件的气势与结束位置
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
	

	private void writeTo(String fileName, byte[] body, Position p) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(uploadPath + fileName);
		fileOutputStream.write(body, p.begin, (p.end - p.begin));
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
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

	public List<VvipList> readExcel2(InputStream in ,String textBody ,String userCode) throws CoreException {
		List<VvipList> list = new ArrayList<VvipList>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		int r = 1 , colum = 0;
		try {
			// 同时支持Excel 2003、2007
			Workbook workbook = WorkbookFactory.create(in); // 这种方式 Excel
															// 2003/2007/2010
															// 都是可以处理的
			/*Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			// 遍历每一行
			for (int r = 1; r < rowCount; r++) {
				VvipList t = new VvipList();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数

				String operator = getOperator(textBody);*/
			@SuppressWarnings("unused")
			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
				Sheet sheet = workbook.getSheetAt(0);
				int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
				// 遍历每一行
				for (; r < rowCount; r++) {
					Row row = sheet.getRow(r);
//					int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
					short cellCount = row.getLastCellNum();
					
					VvipList t = new VvipList();

					
					t.setAutoId(SequenceUtil.getUUID());
					t.setStatus("1");
					t.setCrtDate(date);
					t.setLstUpdDate(date);
					t.setCrtUser(userCode);
					t.setLstUpdUser(userCode);
					t.setStartDate(date);
					t.setStopDate(null);

				// 遍历每一列
				for (int c = 0; c < cellCount; c++) {
					Cell cell = row.getCell(c);
					int cellType = cell.getCellType();
					String cellValue = null;
					switch (cellType) {
					case Cell.CELL_TYPE_STRING: // 文本
						cellValue = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC: // 数字、日期
						if (DateUtil.isCellDateFormatted(cell)) {
							cellValue = fmt.format(cell.getDateCellValue()); // 日期型
						} else {
							DecimalFormat df = new DecimalFormat("#");
							cellValue = df.format(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN: // 布尔型
						cellValue = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_BLANK: // 空白
						cellValue = cell.getStringCellValue();
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
						t.setAppName(cellValue);
					case 1:
						t.setIdNo(cellValue);
						break;
					default:
					}
				}

				list.add(t);
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("第"+(r+1)+"行，第"+(colum+1)+"列数据导入错误");
			throw new CoreException("false_"+(r+1) + "_" + (colum+1));
		}
		return list;
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
}
