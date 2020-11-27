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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.InconfQuestion;
import com.huaxia.opas.dao.sysparm.ConfQuestionDao;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.ConfQuestion;
import com.huaxia.opas.domain.sysparm.IdentityRisk;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.sysparm.ConfQuestionService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;

/**
 * 征信问题库上传Servlet
 * 
 * @author lipengfei
 *
 */
public class ConfQuestionUploadServlet extends ServletProxy {
	
//	public final static String uploadPath="/app/opas/upload/";


	private static final long serialVersionUID = 5657645870447683607L;
	@Autowired
	private ConfQuestionService confQuestionService;
	@Autowired
	private ApDictService apDictService;
	@Autowired
	private ApDictDetailService apDictDetailService ;
	
	@Value("${upload.newpath}")
	public  String uploadPath;
	
	public ConfQuestionService getConfQuestionService() {
		return confQuestionService;
	}

	public void setConfQuestionService(ConfQuestionService confQuestionService) {
		this.confQuestionService = confQuestionService;
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

	public ConfQuestionUploadServlet() {
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
		List<ConfQuestion> list = null;
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
//			ConfQuestionService confQuestionService = (ConfQuestionService) ac.getBean("confQuestionService");s
			list = readExcel(in, textBody, userName);
			int counts = confQuestionService.insertConfQuestionFromFile(list, batchfileInfo);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
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

	
	public  List<ConfQuestion> readExcel(FileInputStream in,String textBody ,String userName)throws CoreException {
//		ConfQuestionService confQuestionService = (ConfQuestionService) ac.getBean("confQuestionService");
		//用户类型Map
		Map<String,String> userFlagMap = new HashMap<String,String>();
		userFlagMap.put("标准卡", "1");
		userFlagMap.put("易达金卡", "2");
		//问题等级Map
		Map<String,String> questionLevelMap = new HashMap<String,String>();
		questionLevelMap.put("初级问题", "1");
		questionLevelMap.put("中级问题", "2");
		questionLevelMap.put("高级问题", "3");
		//启停状态Map
		Map<String,String> statusMap = new HashMap<String,String>();
		statusMap.put("启用","1");
		statusMap.put("禁用","0");
		
		List<ConfQuestion> qurstionList = new ArrayList<ConfQuestion>();
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
				ConfQuestion question = new ConfQuestion();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells();//获取总列数
				//将启停状态默认为启动
				question.setStatus("1");
				//遍历,获取每一列
				for (int c = 0; c < cellCount; c++) {
					Cell cell = row.getCell(c);//获得列值
					if(cell != null){
						switch(c){
						case InconfQuestion.USERFLAG_INDEX://用户类型	
							String userFlag = userFlagMap.get(cell.toString());
							question.setUserFlag(userFlag);
							break;
						case InconfQuestion.QUESTINOLEVEL_INDEX://问题等级
							String questionLevel = questionLevelMap.get(cell.toString());
							question.setQuestionLevel(questionLevel);
							break;
						case InconfQuestion.QUESTIONDESC_INDEX://问题描述
							String questionDesc = cell.toString();
							question.setQuestionDesc(questionDesc);
							break;
						case InconfQuestion.FROMTBLNO_INDEX://答案来源渠道
							String questionTbldesc = cell.toString();
							question.setFromTableDesc(questionTbldesc);
							break;
						case InconfQuestion.FROMFIELDNO_INDEX://答案来源域
							String questionFeldesc = cell.toString();
							question.setFromFelDesc(questionFeldesc);
							break;
						case InconfQuestion.STATUS_INDEX://启停状态
							String questionStatus = statusMap.get(cell.toString());
							if(questionStatus != null && !"".equals(questionStatus)){
							question.setStatus(questionStatus);
							}
							break;	
						}
					}
				}
				//生成UUID
				UUID uuid = UUID.randomUUID();
				question.setAutoId(uuid.toString().replace("-", ""));
				question.setCrtDate(new Date());
				question.setOperator(userName);
				qurstionList.add(question);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return qurstionList;
	}
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	//业务字典取值的方法
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
}
