package com.huaxia.opas.action.archive;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.service.archive.SuppArService;
import com.huaxia.opas.util.DownloadExcel;
import com.huaxia.opas.util.ServletProxy;
/**
 * 补件归档下载
 * 
 * @author gaohui 
 *
 */
public class SuppArActionDownLoadServlet extends ServletProxy {
	private static final long serialVersionUID = 1827117737551022582L;
	@Autowired
	private  SuppArService suppArService;
	 
	
	public SuppArService getSuppArService() {
		return suppArService;
	}

	public void setSuppArService(SuppArService suppArService) {
		this.suppArService = suppArService;
	}

	public SuppArActionDownLoadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operatTimeStr = (String) request.getParameter("operatTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date operatTime = null;
		List<SuppArchive> list = new ArrayList<SuppArchive>();
		try {
			if(operatTimeStr.equals("")||operatTimeStr == null){
				operatTimeStr = sdf.format(new Date());
			}
			operatTime = sdf.parse(operatTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SuppArchive suppArchive = new SuppArchive();
		suppArchive.setOperatTime(operatTime);
		list = suppArService.querySuccessList(suppArchive);

		String fileName = "成功归档的补件信息"+operatTimeStr+".xls";
		int rowNum = list.size();
		int columnNum = 4;
		 //标题行
        String title[]={"客户姓名","补件码","申请流水号","证件号码"};
    	HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		//设置列宽 
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5200);
		//设置字体 
        HSSFFont headFont=workbook.createFont();
        headFont.setFontName("黑体");
        headFont.setFontHeightInPoints((short)10);//字体大小
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        //单元格样式
        HSSFCellStyle headStyle=workbook.createCellStyle();
        headStyle.setFont(headFont);
		//产生表格标题行
		HSSFRow row = sheet.createRow(0);
        SuppArchive sa = null;
        //写入标题
        for(int i=0;i<columnNum;i++){
        	HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(title[i]);
			cell.setCellValue(text);
			cell.setCellStyle(headStyle);
        }
        int rownum = 1;
        //写入内容
        for(int j=0;j<rowNum;j++){//行
        	row = sheet.createRow(rownum++);
            sa = (SuppArchive) list.get(j);
            row.createCell(0).setCellValue(new HSSFRichTextString(sa.getCustName()));
            row.createCell(1).setCellValue(new HSSFRichTextString(sa.getPatchCode()));
            row.createCell(2).setCellValue(new HSSFRichTextString(sa.getAppId()));
            row.createCell(3).setCellValue(new HSSFRichTextString(sa.getCredNo()));
        }
      //文件存到流中
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      workbook.write(os);
      os.flush();
      os.close();
      // 下载
      DownloadExcel downloadUtil = new DownloadExcel();
      downloadUtil.download(os,request,response, fileName);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
