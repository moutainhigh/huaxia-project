package com.huaxia.plaze.common;

import java.io.File;
import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileHelper {

	// 附件上传根目录
	@Value("${file_store_root}")
	private String fileStoreRoot;

	// 附件上传子目录
	private String fileStoreDirectory;

	public String getFileStoreRoot() {
		return fileStoreRoot;
	}

	public void setFileStoreDirectory(String fileStoreDirectory) {
		this.fileStoreDirectory = fileStoreDirectory;
	}

	public String getFileStoreDirectory() {
		return fileStoreDirectory;
	}

	public String getDateStorePath(boolean absolute) {
		return getDateStorePath(absolute, null);
	}

	/**
	 * 获取日期存储路径
	 * 
	 * @param absolute
	 *            是否绝对路径
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public String getDateStorePath(boolean absolute, String fileName) {
		StringBuilder builder = new StringBuilder();
		if (absolute) {
			builder.append(fileStoreRoot);
		}
		builder.append(fileStoreDirectory);
		builder.append(File.separator);
		builder.append(DateUtil.formatDate(new Date(), "yyyyMMdd"));
		builder.append(File.separator);
		if (StringUtils.isNotBlank(fileName)) {
			builder.append(fileName);
		}
		return builder.toString();
	}

	public String build(String separator, String... strings) {
		StringBuilder builder = new StringBuilder();
		if (strings != null && strings.length > 0) {
			for (int i = 0; i < strings.length; i++) {
				builder.append(strings[i]);
				if (i < strings.length) {
					builder.append(separator);
				}
			}
		}
		return builder.toString();
	}

}
