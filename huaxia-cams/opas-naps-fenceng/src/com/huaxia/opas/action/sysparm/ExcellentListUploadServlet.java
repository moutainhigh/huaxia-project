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
import com.huaxia.opas.domain.decision.ExcellentCompanyList;
import com.huaxia.opas.service.sysparm.ExcellentService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.ServletProxy;

/**
 * 导入优质单位准入白名单
 * 
 * @author ad
 *
 */
public class ExcellentListUploadServlet extends ServletProxy {

	private static final long serialVersionUID = -163059699146540767L;
	@Value("${upload.newpath}")
	public String uploadPath;

	@Autowired
	private ExcellentService excellentService;

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
	 * 处理请求响应
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws CoreException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CoreException {
		FileInputStream in = null;
		PrintWriter out = null;
		File file = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			// 读取请求body
			byte[] body = readBody(request);
			// 获取所有body内容的字符串表示
			String textBody = new String(body, "ISO-8859-1");
			// 获取上传文件的名称
			String fileName = new String(getFileName(textBody).getBytes("ISO-8859-1"), "UTF-8");
			// 获得文件的开始与结束位置
			Position p = getFilePosition(request, textBody);
			// 输出至文件
			writeTo(fileName, body, p);

			// 名单批量上传文件信息实体类相关的参数
			Map<String, String> map = getOperatorInfo(textBody);
			String userCode = map.get("userCode");

			file = new File(uploadPath.concat(fileName));
			in = new FileInputStream(file);
			List<ExcellentCompanyList> list = readExcel2(in, textBody, userCode);
			int counts = excellentService.insertExcellentUpload(list);
			out = response.getWriter();
			out.print("导入成功_" + counts + "条数据");
		} catch (CoreException e) {
			if (!e.getCode().equals("") || !e.getCode().equals(null)) {
				out = response.getWriter();
				out.print(e.getCode());
				throw new CoreException(e.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public List<ExcellentCompanyList> readExcel2(InputStream in, String textBody, String userCode) throws CoreException {
		List<ExcellentCompanyList> list = new ArrayList<ExcellentCompanyList>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("#.##");

		Date date = new Date();
		int r = 1, colum = 0;
		try {
			// 同时支持Excel 2003、2007
			Workbook workbook = WorkbookFactory.create(in); // 这种方式 Excel
															// 2003/2007/2010
			@SuppressWarnings("unused") // 都是可以处理的
			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			// 遍历每一行
			int m = 0;
			for (; r < rowCount; r++) {
				m++;
				ExcellentCompanyList t = new ExcellentCompanyList();
				Row row = sheet.getRow(r);
				// int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
				short cellCount = row.getLastCellNum();
				// 遍历每一列
				for (int c = 0; c < 6; c++, colum = c) {
					Cell cell = row.getCell(c);
					int cellType = 99;
					String cellValue = null;

					if (null != cell && !"".equals(cell.toString())) {
						cellType = cell.getCellType();
					} else if (c == 0 || c == 2 ) {
						throw new CoreException("导入失败_第" + (r + 1) + "行_第" + (c + 1) + "列_数据不能为空");
					}
					switch (cellType) {
					case Cell.CELL_TYPE_STRING: // 文本
						cellValue = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC: // 数字、日期
						if (DateUtil.isCellDateFormatted(cell)) {
							cellValue = fmt.format(cell.getDateCellValue()); // 日期型
						} else {
							cellValue = df.format(cell.getNumericCellValue()).toString();
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN: // 布尔型
						cellValue = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_BLANK: // 空白
						cellValue = "";
						break;
					case Cell.CELL_TYPE_ERROR: // 错误
						cellValue = "错误";
						break;
					case Cell.CELL_TYPE_FORMULA: // 公式
						cellValue = "错误";
						break;
					case 99: // 文件中数据为空，允许插入数据为空值
						cellValue = "";
						break;
					default:
						cellValue = "错误";
					}

					switch (c) {
					case 0:
						t.setName(cellValue);
						break;
					case 1:
						t.setCompanyName(cellValue);
						break;
					case 2:
						t.setCertifiNo(cellValue);
						break;
					case 3:
						t.setPhone(cellValue);
						break;
					case 4:
						if("".equals(cellValue)){
							break;
						}else {
							t.setStartTime(fmt.parse(cellValue));
							break;
						}
					case 5:
						if("".equals(cellValue)){
							break;
						}else {
							t.setStopTime(fmt.parse(cellValue));
							break;
						}
					default:
					}
				}
				String id = SequenceUtil.getUUID();
				t.setAutoId(id);
				
				list.add(t);
			}
		} catch (CoreException e) {
			if (!e.getMessage().equals("") || !e.getMessage().equals(null)) {
				throw new CoreException(e.getCode());
			}
			else{
				e.printStackTrace();
				throw new CoreException("导入失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取上传操作用户名(userName)和用户代码(userCode)的MAP集合方法
	 * 
	 * @param String
	 *            requestBody 请求体
	 * @return Map<String,String> 上传的文件名
	 */
	private Map<String, String> getOperatorInfo(String requestBody) {
		Map<String, String> hashMap = new HashMap<>();
		// 获取用户名
		String userName = requestBody.substring(requestBody.indexOf("userName\"") + 9);
		String[] split = userName.split("-");
		userName = split[0].trim();
		// 获取用户名
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

	/**
	 * 获取文件的开始与结束位置
	 * 
	 * @param request
	 * @param textBody
	 * @return
	 */
	private Position getFilePosition(HttpServletRequest request, String textBody) throws IOException {
		String contentType = request.getContentType();
		String boundaryText = contentType.substring(contentType.lastIndexOf("=") + 1, contentType.length());
		// 取得实际上传文件的开始与结束位置
		int pos = textBody.indexOf("filename=\"");
		pos = textBody.indexOf("\n", pos) + 1;
		pos = textBody.indexOf("\n", pos) + 1;
		pos = textBody.indexOf("\n", pos) + 1;
		int boundaryLoc = textBody.indexOf(boundaryText, pos) - 4;
		int begin = ((textBody.substring(0, pos)).getBytes("ISO-8859-1")).length;
		int end = ((textBody.substring(0, boundaryLoc)).getBytes("ISO-8859-1")).length;
		return new Position(begin, end);
	}

	/**
	 * 获取上传文件的名称
	 * 
	 * @param textBody
	 * @return
	 */
	private String getFileName(String textBody) {
		String fileName = textBody.substring(textBody.indexOf("filename=\"") + 10);
		fileName = fileName.substring(0, fileName.indexOf("\n"));
		fileName = fileName.substring(fileName.indexOf("\n") + 1, fileName.indexOf("\""));
		return fileName;
	}

	/**
	 * 读取请求体body
	 * 
	 * @param request
	 * @return
	 */
	private byte[] readBody(HttpServletRequest request) throws IOException, CoreException {
		int formDataLength = request.getContentLength();
		if (-1 == formDataLength) {
			throw new CoreException("文件监听事件并非异常，可忽略");
		}
		// 获取ServletInputStream输入流对象
		DataInputStream dataStream = new DataInputStream(request.getInputStream());
		byte[] body = new byte[formDataLength];
		int totalBytes = 0;
		while (totalBytes < formDataLength) {
			int bytes = dataStream.read(body, totalBytes, formDataLength);
			totalBytes += bytes;
		}
		return body;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (CoreException e) {
			e.printStackTrace();
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
}
