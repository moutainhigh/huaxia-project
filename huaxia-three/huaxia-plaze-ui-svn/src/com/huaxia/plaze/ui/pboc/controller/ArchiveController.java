package com.huaxia.plaze.ui.pboc.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;
import com.huaxia.plaze.ui.pboc.service.FileStorageService;
import com.huaxia.plaze.ui.setting.service.PbocQueryService;

@Controller
@RequestMapping("pboc/archive") 
public class ArchiveController {

	private final static Logger logger = LoggerFactory.getLogger(ArchiveController.class);

	// 人行查询设置
	@Resource
	private PbocQueryService pbocQueryService;

	// 交易复核信息 处理业务层
	@Resource
	private FileStorageService fileStorageService;

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Value("${file_store_root}")
	private String fileStoreRoot;

	// 预览附件
	@RequestMapping("preview/pdf")
	public void previewPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf;charset=UTF-8");
		String fid = request.getParameter("id");
		if (StringUtils.isNotBlank(fid)) {
			String filePath = fileStorageService.getFilePathById(fid);
			File file = new File(fileStoreRoot + filePath);
			if (!file.exists() || file.isDirectory()) {
				logger.warn("文件对象[{}]不存在!", file.getPath());
				return;
			}

			flushFileObject(response, file);
		}
	}

	// 预览附件
	@RequestMapping("preview/image")
	public void previewImage(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		String fid = request.getParameter("id");
		if (StringUtils.isNotBlank(fid)) {
			String filePath = fileStorageService.getFilePathById(fid);
			File file = new File(fileStoreRoot + filePath);
			if (!file.exists() || file.isDirectory()) {
				logger.warn("文件对象[{}]不存在!", file.getPath());
				return;
			}

			flushFileObject(response, file);
		}
	}

	/**
	 * 以流的形式输出文件对象
	 * 
	 * @param response
	 * @param file
	 *            文件对象
	 * @author zhiguo.li
	 */
	private void flushFileObject(HttpServletResponse response, File file) {
		OutputStream output = null;
		try {
			FileInputStream input = new FileInputStream(file);
			byte[] buffer = readStream(input);
			output = response.getOutputStream();
			output.write(buffer);
			output.flush();
		} catch (Exception e) {
			logger.error("文件对象读取异常[{}]", new Object[] { e.getMessage() });
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
				}
			}
		}
	}

	// 获取附件列表信息
	@RequestMapping("filelist")
	@ResponseBody
	public Map<String, Object> getListFileName(String sourceId) {
		List<FileStorageInfo> list = fileStorageService.queryById(sourceId, "");
		Map<String, Object> response = new HashMap<String, Object>();
		if (list.size() == 0) {
			response.put("result", false);
			response.put("message", "没有附件");
			return response;
		}
		response.put("result", true);
		response.put("message", list);
		return response;
	}

	// 读取文件流
	private byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (bops != null) {
				try {
					bops.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
