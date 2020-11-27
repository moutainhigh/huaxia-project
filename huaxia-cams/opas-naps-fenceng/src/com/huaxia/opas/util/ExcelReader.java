package com.huaxia.opas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.huaxia.opas.domain.excel.TestObj;

public class ExcelReader {

	public String readExcel(InputStream in) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 同时支持Excel 2003、2007
			Workbook workbook = WorkbookFactory.create(in); // 这种方式 Excel
															// 2003/2007/2010
															// 都是可以处理的
			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			// 遍历每个Sheet
			for (int s = 0; s < sheetCount; s++) {
				Sheet sheet = workbook.getSheetAt(s);
				int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
				// 遍历每一行
				for (int r = 0; r < rowCount; r++) {
					Row row = sheet.getRow(r);
					int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
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
								cellValue = String.valueOf(cell
										.getNumericCellValue()); // 数字
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN: // 布尔型
							cellValue = String.valueOf(cell
									.getBooleanCellValue());
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
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public List<TestObj> readExcel2(InputStream in) {
		List<TestObj> list = new ArrayList<TestObj>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 同时支持Excel 2003、2007
			Workbook workbook = WorkbookFactory.create(in); // 这种方式 Excel
															// 2003/2007/2010
															// 都是可以处理的
			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			// 遍历每一行
			for (int r = 0; r < rowCount; r++) {
				TestObj t = new TestObj();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
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
							cellValue = String.valueOf(cell
									.getNumericCellValue()); // 数字
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
						t.setNum(cellValue);
						break;
					case 1:
						t.setName(cellValue);
						break;
					case 2:
						t.setAge(cellValue);
						break;
					case 3:
						t.setScore(cellValue);
						break;
					default:
					}

				}
				list.add(t);
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		File file = new File("D:/123.xlsx");
		InputStream in = new FileInputStream(file);

		ExcelReader excelReader = new ExcelReader();

		List<TestObj> list = excelReader.readExcel2(in);
		System.out.println(list);
	}
}
