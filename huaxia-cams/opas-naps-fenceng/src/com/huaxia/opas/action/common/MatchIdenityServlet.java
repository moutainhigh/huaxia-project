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
import com.huaxia.opas.common.InMatchIdenity;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MatchIdenity;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.sysparm.MatchIdenityService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;

/**
 * 简项公安参数配置 批量导入
 * @author lipengfei
 *
 */
public class MatchIdenityServlet extends ServletProxy{
	private static final long serialVersionUID = 6194935717569580311L;
	
	@Value("${upload.newpath}")
	public  String uploadPath;
	
	@Autowired
	private MatchIdenityService matchIdenityService;
	@Autowired
	private ApDictService apDictService;
	@Autowired
	private ApDictDetailService apDictDetailService;
	public MatchIdenityService getMatchIdenityService() {
		return matchIdenityService;
	}
	public void setMatchIdenityService(MatchIdenityService matchIdenityService) {
		this.matchIdenityService = matchIdenityService;
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
	
	public MatchIdenityServlet(){
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
		List<MatchIdenity> list = null;
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
			// 业务字典取出导入提示
			Map<String, String> dictDetail = getDictDetail("WJLB");
			fileType = dictDetail.get("简项公安参数配置");
			BatchfileInfo batchfileInfo = new BatchfileInfo(id, fileName, new Date(), null, fileType, userName,
					userCode, "导入成功", "1");
			file = new File(uploadPath.concat(fileName));
			in = new FileInputStream(file);
			list = readExcel(in, textBody, userName);			
			int counts = matchIdenityService.insertRosterLibraryFromFile(list, batchfileInfo);					
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
		if (-1 == formDataLength) {
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

	// 获取上传的操作用户名
	private Map<String, String> getOperatorInfo(String requestBody) {
		Map<String, String> hashMap = new HashMap<>();
		// 获取用户名
		String userName = requestBody.substring(requestBody.indexOf("userName\"") + 9);
		String[] split = userName.split("-");
		userName = split[0].trim();
		// 获取用户
		String userCode = requestBody.substring(requestBody.indexOf("userCode\"") + 9);
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
	}// </editor-fold>

	public List<MatchIdenity> readExcel(FileInputStream in, String textBody, String userName)
			throws CoreException {
		long starTime = System.currentTimeMillis();
		List<MatchIdenity> rosterList = new ArrayList<MatchIdenity>();
		// 格式化时间
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		try {
			Workbook workbook = WorkbookFactory.create(in);
			@SuppressWarnings("unused")
			int sheetCount = workbook.getNumberOfSheets();
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			// 遍历每一行 第一行为标题行
			for (int r = 1; r < rowCount; r++) {
				MatchIdenity roster = new MatchIdenity();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells();// 获取总列数
				// 遍历,获取每一列
				for (int c = 0; c < cellCount; c++) {
					Cell cell = row.getCell(c);// 获得列值
					if (cell != null) {
						switch (c) {
						case InMatchIdenity.CITY_NAME:// 城市名称
							String cityName = cell.toString();
							roster.setCityName(cityName);
							break;
						case InMatchIdenity.ID_NO:// 证件号
							String idNo = cell.toString();
							roster.setIdNo(idNo);
							break;
						case InMatchIdenity.TEL_AREA:// 电话区号
							String telArea = cell.toString();
							roster.setTelArea(telArea);
							break;
						case InMatchIdenity.INPUT_CHANNEL:// 进件渠道
							String inputChannel = cell.toString();
							roster.setInputChannel(inputChannel);
							break;
						case InMatchIdenity.EXTENSION_AGENCY:// 推广渠道
							String extensionAgency = cell.toString();
							roster.setExtensionAgency(extensionAgency);
							break;
						}
					}
				}
				UUID uuid = UUID.randomUUID();
				roster.setId(uuid.toString().replace("-", ""));
				roster.setStartTime(new Date());
				roster.setLstUpdUser(userName);
				rosterList.add(roster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		long time = endTime - starTime;
		System.out.println("导入功能解析文件耗时" + time);
		return rosterList;
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	// 业务字典取值的方法
	public Map<String, String> getDictDetail(String dictCode) throws CoreException {
		Map<String, String> map = new HashMap<>();
		ApDict apDict = apDictService.queryApDictByDictCode(dictCode);
		List<ApDictDetail> list = apDictDetailService.queryDictDetailListByDictId(apDict.getDictId());
		for (ApDictDetail apDictDetail : list) {
			map.put(apDictDetail.getDictDetailName(), apDictDetail.getDictDetailCode());
		}
		return map;
	}

	
}
