package com.huaxia.opas.action.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.excel.TestObj;
import com.huaxia.opas.service.compare.TestObjService;
import com.huaxia.opas.util.ExcelReader;

/**
 * excel到数据库
 * 
 * @author xiebinxu 2017年3月9日
 */
public class ExcelUploadAction implements Action {

	private static Logger logger = LoggerFactory
			.getLogger(ExcelUploadAction.class);

	@Resource(name = "testObjService")
	private TestObjService testObjService;

	public void addFileUpload(Context ctx) throws CoreException {

		try {
			InputStream in = null;
			Map map = ctx.getDataMap();
			String fileName = (String) map.get("fileName");
			File file = new File(fileName);
			in = new FileInputStream(file);
			ExcelReader excelReader = new ExcelReader();
			List<TestObj> list = excelReader.readExcel2(in);
			testObjService.insertTestObjList(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ctx.setData("isSuccess", true);

	}

}
