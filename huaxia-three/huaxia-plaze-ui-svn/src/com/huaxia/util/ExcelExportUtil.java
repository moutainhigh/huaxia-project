package com.huaxia.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.plaze.common.ExportSource;

public class ExcelExportUtil {

	private final static Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);

	// 标题行样式
	private static CellStyle titleStyle;

	// 标题行字体
	private static Font titleFont;

	// 表头行样式
	private static CellStyle headStyle;

	// 表头行字体
	private static Font headFont;

	// 内容行样式
	private static CellStyle contentStyle;

	// 内容行字体
	private static Font contentFont;

	private static HSSFWorkbook init() {
		HSSFWorkbook wb = new HSSFWorkbook();

		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();

		initTitleCellStyle();
		initTitleFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();

		return wb;
	}

	public static void download(HttpServletRequest request, HttpServletResponse response, String reportTitle, List<Map<String, String>> reportHeader,
			ExportSource reportSource) throws Exception {
		HSSFWorkbook wb = init();
		HSSFSheet sheet = wb.createSheet();
		doGenerateExportTitle(sheet, reportTitle, reportHeader.size());
		doGenerateExportHeader(sheet.createRow((int) 1), reportHeader);
		doGenerateExportDataRow(sheet, reportSource, reportHeader);
		adjustColumnSize(sheet, reportHeader.size());
		output(response, wb);
	}

	private static void output(HttpServletResponse response, HSSFWorkbook wb) throws IOException {
		OutputStream output = null;
		try {
			output = response.getOutputStream();
			wb.write(output);
			output.flush();
		} catch (IOException e) {
			logger.error("写入数据流异常:{}", e.getMessage());
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	private static void doGenerateExportTitle(HSSFSheet sheet, String fileName, int columnSize) {
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, columnSize - 1);
		sheet.addMergedRegion(titleRange);
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(titleStyle);
		cell.setCellValue(fileName);
	}

	private static void doGenerateExportHeader(HSSFRow row, List<Map<String, String>> reportHeader) {
		row.setHeight((short) 400);
		for (int i = 0; i < reportHeader.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(reportHeader.get(i).get("NAME"));
			cell.setCellStyle(headStyle);
		}
	}

	private static void doGenerateExportDataRow(HSSFSheet sheet, ExportSource source, List<Map<String, String>> reportHeader) {
		List<Map<String, Object>> list = source.getDataSource();
		HSSFRow row = null;
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			row.setHeight((short) 350);
			HSSFCell cell = null;
			Map<String, Object> dataSource = list.get(i);
			for (int j = 0; j < reportHeader.size(); j++) {
				cell = row.createCell(j);
				if (j == 0) {
					cell.setCellValue(String.valueOf(i + 1));
				} else {
					cell.setCellValue(String.valueOf(dataSource.get(reportHeader.get(j).get("CODE"))));
				}
				cell.setCellStyle(contentStyle);
			}
		}
	}

	private static void adjustColumnSize(HSSFSheet sheet, int columnSize) {
		for (int i = 0; i < columnSize; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	private static void initTitleCellStyle() {
		titleStyle.setAlignment((short) HorizontalAlignment.CENTER_SELECTION.ordinal());
		titleStyle.setVerticalAlignment((short) VerticalAlignment.CENTER.ordinal());
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	}

	private static void initHeadCellStyle() {
		headStyle.setAlignment((short) HorizontalAlignment.CENTER_SELECTION.ordinal());
		headStyle.setVerticalAlignment((short) VerticalAlignment.CENTER.ordinal());
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		headStyle.setBorderTop((short) BorderStyle.THIN.ordinal());
		headStyle.setBorderBottom((short) BorderStyle.THIN.ordinal());
		headStyle.setBorderLeft((short) BorderStyle.THIN.ordinal());
		headStyle.setBorderRight((short) BorderStyle.THIN.ordinal());
		headStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	}

	private static void initContentCellStyle() {
		contentStyle.setAlignment((short) HorizontalAlignment.CENTER_SELECTION.ordinal());
		contentStyle.setVerticalAlignment((short) VerticalAlignment.CENTER.ordinal());
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop((short) BorderStyle.THIN.ordinal());
		contentStyle.setBorderBottom((short) BorderStyle.THIN.ordinal());
		contentStyle.setBorderLeft((short) BorderStyle.THIN.ordinal());
		contentStyle.setBorderRight((short) BorderStyle.THIN.ordinal());
		contentStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		contentStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		contentStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		contentStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		contentStyle.setWrapText(true); // 字段换行
	}

	private static void initTitleFont() {
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short) 10);
		titleFont.setBoldweight((short) 3);
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLACK.getIndex());
	}

	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBoldweight((short) 2);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.BLACK.getIndex());
	}

	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBoldweight((short) 1);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLACK.getIndex());
	}

}
