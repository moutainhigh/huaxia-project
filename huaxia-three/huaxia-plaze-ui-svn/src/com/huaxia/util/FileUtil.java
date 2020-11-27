package com.huaxia.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

public class FileUtil {

	static final int BUFFER_SIZE = 1024;

	static File file = null;

	static boolean flag = false;

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		flag = false;
		file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 如果文件存在返回 true, 否则返回 false.
	 */
	public static boolean exists(String filePath) {
		if (StringUtils.isBlank(filePath)) {
			return false;
		}
		return new File(filePath).exists();
	}

	/**
	 * 将文件存储至指定位置（拷贝方式）
	 * 
	 * @param file
	 *            文件
	 * @param directory
	 *            目标目录
	 * @return
	 */
	public static boolean store(File file, String directory) {
		if (StringUtils.isBlank(directory)) {
			return false;
		}

		if (file != null) {
			byte[] buffer = new byte[BUFFER_SIZE];
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			String filePath = directory + File.separator + file.getName();
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				bos = new BufferedOutputStream(new FileOutputStream(filePath));
				while (bis.read(buffer) >= 0) {
					bos.write(buffer);
				}
				bos.flush();
			} catch (FileNotFoundException e) {
				System.err.println(String.format("指定文件[%s]不存在:%s", filePath, e.getMessage()));
			} catch (IOException e) {
				System.err.println(String.format("流写入异常:%s", e.getMessage()));
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
					}
				}
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return false;
	}

	public static boolean store(String filePath) {
		if (StringUtils.isBlank(filePath)) {
			return false;
		}
		return store(new File(filePath), filePath.substring(0, filePath.lastIndexOf(".")));
	}
	
	public static boolean store(InputStream in, String directory) {
		byte[] buffer = new byte[BUFFER_SIZE];
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String filePath = directory + File.separator + getBatchFileId();
		try {
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
			while (bis.read(buffer) >= 0) {
				bos.write(buffer);
			}
			bos.flush();
			return true;
		} catch (FileNotFoundException e) {
			System.err.println(String.format("指定文件[%s]不存在:%s", filePath, e.getMessage()));
		} catch (IOException e) {
			System.err.println(String.format("流写入异常:%s", e.getMessage()));
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}
	
	private static String getBatchFileId() {
		return BatchNoGenerator.generateNextNumber();
	}

}
