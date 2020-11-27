package com.huaxia.opas.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author uatym990190
 */
public class ZipUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);

	/**
	 * @param args
	 * @throws IOException 
	 */
//	public static void main(String[] args) throws IOException {
//		System.out.println(unZipFiles("D:\\HTIN2016061301.zip", "D:"));
//	}

	/**
	 * 压缩文件-由于out要在递归调用外,所以封装一个方法用来 调用ZipFiles(ZipOutputStream out,String
	 * path,File... srcFiles)
	 * @param zip
	 * @param path
	 * @param srcFiles
	 * @throws IOException
	 */
	public static void zipFiles(File zip, String path, File... srcFiles)
	throws IOException {
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zip));
			ZipUtil.zipFiles(out, path, srcFiles);
		} finally {
			close(out);
		}
	}

	/**
	 * 压缩文件-File
	 * @param zipFile zip文件
	 * @param srcFiles 被压缩源文件
	 * @throws IOException 
	 */
	public static void zipFiles(ZipOutputStream out, String path, File... srcFiles) throws IOException {
		path = path.replaceAll("\\*", "/");
		if (!path.endsWith("/")) {
			path += "/";
		}
		byte[] buf = new byte[1024];

		for (int i = 0; i < srcFiles.length; i++) {
			if (srcFiles[i].isDirectory()) {
				File[] files = srcFiles[i].listFiles();
				String srcPath = srcFiles[i].getName();
				srcPath = srcPath.replaceAll("\\*", "/");
				if (!srcPath.endsWith("/")) {
					srcPath += "/";
				}
				out.putNextEntry(new ZipEntry(path + srcPath));
				zipFiles(out, path + srcPath, files);
			} else {
				FileInputStream in = null;
				try {
					in = new FileInputStream(srcFiles[i]);
//				System.out.println(path + srcFiles[i].getName());
					out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					out.flush();
				} finally {
					out.closeEntry();
					close(in);
				}
			}
		}

	}

	/**
	 * 解压到指定目录
	 * 
	 * @param zipPath
	 * @param descDir
	 * @author isea533
	 */
	public static List<String> unZipFiles(String zipPath, String descDir)
	throws IOException {
		
		return unZipFiles(new File(zipPath), descDir);
	}

	/**
	 * 解压文件到指定目录
	 * 
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> unZipFiles(File zipFile, String descDir)
	throws IOException {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		Enumeration entries = zip.entries();

		List<String> fileNames = new ArrayList<String>();	// 存放包内文件名
		while (entries.hasMoreElements()) {
			InputStream in = null;
			OutputStream out = null;
			
			try {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				fileNames.add(zipEntryName);
				
//				System.out.println("zipEntryName="+zipEntryName);
				
				in = zip.getInputStream(entry);
				String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");
				
//				System.out.println("outPath="+outPath);
				
				// 判断路径是否存在,不存在则创建文件路径
				File file = new File(outPath).getParentFile();
				if (!file.exists()) {
					file.mkdirs();
				}
				// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
				if (new File(outPath).isDirectory()) {
					continue;
				}
				// 输出文件路径信息
//				System.out.println(outPath);

				out = new FileOutputStream(outPath);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				out.flush();
			} finally {
				close(out);
				close(in);
			}
		}
		
		return fileNames;
	}
	
	/**
	 * 关闭资源
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		if (closeable!=null) {
			try {
				closeable.close();
			} catch (Exception e) {
			}
		}
	}
}
