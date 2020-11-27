package com.huaxia.opas.action.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.service.ITokenService;
import com.huaxia.opas.domain.MenuObj;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.FileRoleRelation;
import com.huaxia.opas.domain.system.FileUpload;
import com.huaxia.opas.domain.system.FileUploadLog;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.service.system.FileUploadLogService;
import com.huaxia.opas.service.system.FileUploadService;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.FileUploadProperties;
import com.huaxia.opas.util.PDF2SWFUtil;
import com.huaxia.opas.util.StringUtil;

/**
 * 文件上传
 * 
 * @author zhiguo.xu
 */
public class FileUploadAction extends HttpServlet implements Action {

	private static final long serialVersionUID = -2054428917058617066L;

	private static Logger logger = LoggerFactory.getLogger(FileUploadAction.class);

	@Resource(name = "fileUploadService")
	private FileUploadService fileUploadService;

	@Resource(name = "fileUploadLogService")
	private FileUploadLogService fileUploadLogService;

	@Resource(name = "tokenService")
	private ITokenService tokenService;

	@Resource(name = "apUserService")
	private ApUserService apUserService;

	/**
	 * 获得pdf文件的上传路径
	 */
	public String savePdfPath() {
		Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
		String path = paroperMap.get("SAVE_PATH");
		if (path == null || "".equals(path)) {
			return "D:" + File.separator + "documentFile" + File.separator + "upload" + File.separator;
		} else {
			return path;
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void fileUpload(Context context) throws CoreException {
		// 上传文件路径
		String fileUploadPath = StringUtils.trimToEmpty((String) context.getDataMap().get("fileUploadPath"));
		System.out.println("fileUploadPath=" + fileUploadPath);
		String bigCategory = StringUtils.trimToEmpty((String) context.getDataMap().get("bigCategory"));
		String smallCategory = StringUtils.trimToEmpty((String) context.getDataMap().get("smallCategory"));
		String fileDescribe = StringUtils.trimToEmpty((String) context.getDataMap().get("fileDescribe"));
		FileUpload fileUp = null;
		try {
			String fileSeq = "\\";
			int fileStartWeizhi = fileUploadPath.lastIndexOf(fileSeq) + 1;
			// 获得文件名
			String fileName = fileUploadPath.substring(fileStartWeizhi);
			// 校验文档类型
			/*if (fileName.toLowerCase().indexOf(".pdf") == -1) {
				context.setData("msg", "文档类型不正确，只允许上传PDF文档!");
				return;
			}
			File file = new File("/"+fileName);
			File filePath = new File(savePdfPath());
			if(file.length()/1024/1024>3){
				context.setData("msg", "上传文档大小不能超过3M!");
				return;
			}
			if (!filePath.exists() && !filePath.isDirectory()) {
				filePath.mkdirs();
			}
			File newfile = new File(savePdfPath() + File.separator + fileName);
			// 流
			FileInputStream in = new FileInputStream(file);
			FileOutputStream out = new FileOutputStream(newfile);
			byte by[] = new byte[521];
			int len = 0;
			while ((len = in.read(by)) > 0) {
				out.write(by);
			}
			out.close();
			in.close();
			logger.debug("文档上传完成");*/
			//============================
			FileUpload oldFile = fileUploadService.selectByFileName(fileName);
			if(oldFile!=null){
				context.setData("msg", "该文件已经存在，不可重复上传!");
				return;
			}
			fileUp = new FileUpload();
			String fileId = StringUtil.randomUUIDPlainString();
			// 获得当前登陆用户的userId
			String userCode = String.valueOf(context.getDataMap().get("userId"));
			// 用户信息
			ApUser userInfo = apUserService.queryApUserByUserCode(userCode);
			//获取文档大类
			String bigCate = userInfo.getFileUploadControl();
			if(bigCate==null || "".equals(bigCate)){
				context.setData("msg", "请为该用户配置所属文档大类权限!");
				return;
			}
			fileUp.setFileId(fileId);
			fileUp.setFileName(fileName);
			fileUp.setFilePath(savePdfPath() + fileName);
			fileUp.setBigCategory(bigCate.toUpperCase());
			fileUp.setSmallCategory(smallCategory);
			fileUp.setFileDescribe(fileDescribe);
			fileUp.setUploadDate(new Date());
			fileUp.setRightControl(smallCategory);
			fileUp.setOperatCode(userCode);
			fileUp.setOperatPerson(userInfo.getUserName());
			// 流水日志记录
			FileUploadLog filelog = new FileUploadLog();
			filelog.setId(StringUtil.randomUUIDPlainString());
			filelog.setFileId(fileId);
			filelog.setFileName(fileName);
			filelog.setOperatCode(userCode);
			filelog.setOperatDate(new Date());
			filelog.setOperator(userInfo.getUserName());
			filelog.setOperatType("1");
			// 上传文档
			fileUploadService.addFileUpload(fileUp,filelog);
			//fileUploadLogService.insert(filelog);
			//upload(fileUploadPath, fileName);
			//fileView(fileUploadPath);
			context.setData("msg", "文档上传成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户认证异常..", e);
			context.setData("msg", "系统异常!");
		}
	}
	/**
	 * 文档管理队列：文件初始化链表显示及根据条件查询
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void fileUploadSelect(Context context) throws CoreException {

		String fileName = StringUtils.trimToEmpty((String) context.getDataMap().get("fileName"));
		String smallCategory = StringUtils.trimToEmpty((String) context.getDataMap().get("smallCategory"));
		String uploadDateStr = StringUtils.trimToEmpty((String) context.getDataMap().get("uploadDate"));
		// 获得当前登陆用户的userId
		String userCode = String.valueOf(context.getDataMap().get("userId"));
		// 用户信息
		ApUser userInfo = apUserService.queryApUserByUserCode(userCode);
		/**
		 * 查询，获得文档列表
		 */
		int curNum = 0;
		int curPage = Integer.parseInt(
				String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		int pageNum = Integer.parseInt(
				String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//条件查询项
		map.put("bigCategory", userInfo.getFileUploadControl());
		map.put("smallCategory", smallCategory);
		map.put("fileName", fileName);
		map.put("uploadDateStr", uploadDateStr);
		//map.put("userCode", userCode);
		int count = fileUploadService.queryFileCount(map);

		List<FileUpload> list = new ArrayList<FileUpload>();;

		if (count > 0) {
			list = fileUploadService.queryFileList(map, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		context.setDataMap(dataMap);
	}
	/**
	 * 文档查看队列：文件初始化链表显示及根据条件查询
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void fileViewSelect(Context context) throws CoreException {

		String fileName = StringUtils.trimToEmpty((String) context.getDataMap().get("fileName"));
		String smallCategory = StringUtils.trimToEmpty((String) context.getDataMap().get("smallCategory"));
		String uploadDateStr = StringUtils.trimToEmpty((String) context.getDataMap().get("uploadDate"));
		// 获得当前登陆用户的userId
		String userCode = String.valueOf(context.getDataMap().get("userId"));
		// 用户信息
		ApUser userInfo = apUserService.queryApUserByUserCode(userCode);
		/**
		 * 查询，获得文档列表
		 */
		int curNum = 0;
		int curPage = Integer.parseInt(
				String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		int pageNum = Integer.parseInt(
				String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 获取授予文档权限
		String fileRightControl = userInfo.getFileRightControl();
		/** 判断用户是否已经单独配置个人文档浏览的权限，若已经配置，则以这个为主，若没有配置，则以角色文档类型配置为主*/
		StringBuilder mubiao = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		/*1.个人文档浏览权限查询*/
		if(fileRightControl!= null && !"".equals(fileRightControl.trim())){
			String rightControl[] = fileRightControl.trim().split("[,]");
			for (int i = 0; i < rightControl.length; i++) {
				if (!"".equals(rightControl[i])) {
					/*if(rightControl[i].length()==1){
						if(big_mubiao.length()>0){
							if (i < (rightControl.length - 1)) {
								big_mubiao.append(",");
							}
						}
						big_mubiao.append("'");
						big_mubiao.append(rightControl[i]);
						big_mubiao.append("'");
					}else{*/
						mubiao.append("'");
						mubiao.append(rightControl[i]);
						mubiao.append("'");
						if (i < (rightControl.length - 1)) {
							mubiao.append(",");
						}
					/*}*/
				}
			}
			//map.put("bigCategory", big_mubiao.length()==0?null:big_mubiao);
			//map.put("fileRightControl", mubiao.length()==0?null:mubiao);
			//map.put("flag", "1");//文档权限配置方法标示：1。个人文档浏览权限查询；2.角色文档浏览配置查询
		}else{/*2.角色文档浏览配置查询*/
			//获取当前用户角色所对应的可浏览的文档类型
			List<String> fileTypes = fileUploadService.selectSmallCategoryByUserCode(userCode);
			for (int i = 0; i < fileTypes.size(); i++) {
				mubiao.append("'");
				mubiao.append(fileTypes.get(i));
				mubiao.append("'");
				if (i < (fileTypes.size() - 1)) {
					mubiao.append(",");
				}
			}
			//map.put("fileRightControl", mubiao.length()==0?null:mubiao);
			//map.put("flag", "2");//文档权限配置方法标示：1。个人文档浏览权限查询；2.角色文档浏览配置查询
		}
		//条件查询项
		map.put("fileRightControl", mubiao.length()==0?null:mubiao);
		map.put("smallCategory", smallCategory);
		map.put("fileName", fileName);
		map.put("uploadDateStr", uploadDateStr);
		map.put("userCode", userCode);
		int count = 0;
		
		if(!"".equals(map.get("fileName"))||!"".equals(map.get("smallCategory"))
				||!"".equals(map.get("uploadDateStr"))||(map.get("fileRightControl"))!=null){
			count = fileUploadService.queryFileCount(map);
		} 
		List<FileUpload> list = new ArrayList<FileUpload>();

		if (count > 0) {
			list = fileUploadService.queryFileList(map, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		context.setDataMap(dataMap);
	}
	/**
	 * 查询文件角色配置列表
	 * @param context
	 * @throws Exception
	 */
	public void fileRoleSelect(Context context) throws Exception {
		// 获得当前登陆用户的userCode
		String userCode = String.valueOf(context.getDataMap().get("userId"));
		String roleName = String.valueOf(context.getDataMap().get("roleName"));
		String fileType = String.valueOf(context.getDataMap().get("fileType"));
		int curNum = 0;
		int curPage = Integer.parseInt(
				String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		int pageNum = Integer.parseInt(
				String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCode", userCode);
		map.put("roleName", roleName);
		map.put("smallCategory", fileType);
		int count = fileUploadService.queryFileRoleCount(map);
		List<FileRoleRelation> list = new ArrayList<FileRoleRelation>();;
		if (count > 0) {
			list = fileUploadService.queryFileRoleList(map, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		context.setDataMap(dataMap);
	} 
	/**
	 * 修改角色、个人与文档类型的配置关系
	 * @param context
	 * @throws Exception
	 */
	public void updateFileRoleConf(Context context) throws Exception {
		//获得数据
		String flag = String.valueOf(context.getDataMap().get("flag"));
		String oldFileTypes = String.valueOf(context.getDataMap().get("oldFileTypes"));
		String newFileTypes = String.valueOf(context.getDataMap().get("newFileTypes"));
		String meBigCategory = String.valueOf(context.getDataMap().get("meBigCategory"));
		String roleCode = String.valueOf(context.getDataMap().get("roleCode"));
		//新旧数据进行比对，进行数据的插入与删除
		FileRoleRelation frr = null;
		if("1".equals(flag)){//角色与文档类型关系配置修改
			//对删除的判断
			if(oldFileTypes!=null && !"".equals(oldFileTypes)){
				String oldFileTypesArray[] = oldFileTypes.substring(0, oldFileTypes.length()-1).split("\\,");
				for (int i = 0; i < oldFileTypesArray.length; i++) {
					if(newFileTypes.indexOf(oldFileTypesArray[i])>-1){
						continue;
					}else{
						frr = new FileRoleRelation();
						frr.setBigCategory(meBigCategory);
						frr.setSmallCategory(meBigCategory+oldFileTypesArray[i]);
						frr.setRoleCode(roleCode);
						fileUploadService.deleteByCondition(frr);
					}
				}
			}
			//新增
			if(newFileTypes!=null && !"".equals(newFileTypes)){
				String newFileTypesArray[] = newFileTypes.substring(0, newFileTypes.length()-1).split("\\,");
				for (int i = 0; i < newFileTypesArray.length; i++) {
					if(oldFileTypes.indexOf(newFileTypesArray[i])>-1){
						continue;
					}else{
						frr = new FileRoleRelation();
						frr.setId(StringUtil.randomUUIDPlainString());
						frr.setBigCategory(meBigCategory);
						frr.setSmallCategory(meBigCategory+newFileTypesArray[i]);
						frr.setRoleCode(roleCode);
						fileUploadService.insertSelective(frr);
					}
				}
			}
		}else if("2".equals(flag)){//特殊个人与文件类型配置
			String userCode = String.valueOf(context.getDataMap().get("userCode"));
			ApUser apUser = apUserService.queryApUserByUserCode(userCode);
			String newFileRightControl = "";
			String fileRightControl = "";
			if(apUser.getFileRightControl()!=null){
				String arrays[] = apUser.getFileRightControl().split("\\,");
				for (int i = 0; i < arrays.length; i++) {
					if(arrays[i].contains(meBigCategory)){
						continue;
					}else{
						fileRightControl += arrays[i] + ",";
					}
				}
				if(fileRightControl.length()>0 && ",".equals(fileRightControl.substring(fileRightControl.length()-1))){
					fileRightControl = fileRightControl.substring(0, fileRightControl.length()-1);
				}
			}
			if(newFileTypes!=null && !"".equals(newFileTypes)){
				String newFileTypesArray[] = newFileTypes.substring(0, newFileTypes.length()-1).split("\\,");
				for (int i = 0; i < newFileTypesArray.length; i++) {
					newFileRightControl = newFileRightControl + meBigCategory + newFileTypesArray[i] + ",";
				}
			}
			String res = "";
			if(fileRightControl!=null && !"".equals(fileRightControl)){
				res = fileRightControl+","+newFileRightControl;
			}else{
				res = newFileRightControl;
			}
			apUser.setFileRightControl(CommonUtil.sortStr(res, ","));
			apUserService.updateApUser(apUser);
		}
		
	}
	/**
	 * 查询文件个人配置列表
	 * @param context
	 * @throws Exception
	 */
	public void personFilePowerSelect(Context context) throws Exception {
		// 获得当前登陆用户的userCode
		String userName = String.valueOf(context.getDataMap().get("userName"));
		String fileType = String.valueOf(context.getDataMap().get("fileType"));
		int curNum = 0;
		int curPage = Integer.parseInt(
				String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		int pageNum = Integer.parseInt(
				String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("userCode", userCode);
		map.put("userName", userName);
		map.put("fileRightControl", fileType);
		int count = apUserService.queryConfUserCodeCount(map);
		List<ApUser> list = new ArrayList<ApUser>();;
		if (count > 0) {
			list = apUserService.queryConfApUserByUserCode(map, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		context.setDataMap(dataMap);
	} 
	/**
	 * 获得当前人的大类权限
	 * @param fileUploadPath
	 * @param fileName
	 * @throws Exception
	 */
	public void currPersonBigCategoryControl(Context context) throws Exception {
		String userCode = String.valueOf(context.getDataMap().get("userId"));
		// 用户信息
		ApUser userInfo = apUserService.queryApUserByUserCode(userCode);
		context.setData("powerCate", userInfo.getFileUploadControl());
	}
	public void upload(String fileUploadPath, String fileName) throws Exception {
		File file = new File(fileUploadPath);
		File filePath = new File(savePdfPath());

		if (!filePath.exists() && !filePath.isDirectory()) {
			filePath.mkdirs();
		}
		File newfile = new File(savePdfPath() + File.separator + fileName);
		// 流
		InputStream in = new FileInputStream(file);
		OutputStream out = new FileOutputStream(newfile);
		byte by[] = new byte[1024];
		int len = 0;
		while ((len = in.read(by)) > 0) {
			out.write(by);
		}
		out.close();
		in.close();
		logger.debug("文档上传完成");
	}

	public FileUploadService getFileUploadService() {
		return fileUploadService;
	}

	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	/**
	 * 文件修改
	 * 
	 * @return
	 */
	public void updateFile(Context context) throws CoreException {
		// 上传文件路径
		String fileId = StringUtils.trimToEmpty((String) context.getDataMap().get("fileId"));
		String fileName = StringUtils.trimToEmpty((String) context.getDataMap().get("fileName"));
		String smallCategory = StringUtils.trimToEmpty((String) context.getDataMap().get("smallCategory"));
		String fileDescribe = StringUtils.trimToEmpty((String) context.getDataMap().get("fileDescribe"));
		FileUpload file = null;
		try {
			file = fileUploadService.selectByPrimaryKey(fileId);
			file.setFileName(fileName);
			file.setSmallCategory(smallCategory);
			file.setFileDescribe(fileDescribe);
			fileUploadService.updateByPrimaryKey(file);
			// 插入流水日志
			insertFileUploadLog(context, fileId, fileName, "3");
			context.setData("msg", "更新成功!");
		} catch (Exception e) {
			logger.error("用户认证异常..", e);
			context.setData("msg", "系统异常!");
		}
	}

	/**
	 * 插入流水日志
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void insertFileUploadLog(Context context, String fileId, String fileName, String operatType)
			throws CoreException {
		// 查询当前登陆人信息
		String userCode = String.valueOf(context.getDataMap().get("userId"));
		ApUser userInfo = apUserService.queryApUserByUserCode(userCode);
		// 流水日志记录
		FileUploadLog filelog = new FileUploadLog();
		filelog.setId(StringUtil.randomUUIDPlainString());
		filelog.setFileId(fileId);
		filelog.setFileName(fileName);
		filelog.setOperatCode(userCode);
		filelog.setOperatDate(new Date());
		filelog.setOperator(userInfo.getUserName());
		filelog.setOperatType(operatType);
		fileUploadLogService.insert(filelog);
	}

	/**
	 * 文件删除
	 * 
	 * @return
	 */
	public void deleteFile(Context context) throws CoreException {
		// 上传文件路径
		String fileId = StringUtils.trimToEmpty((String) context.getDataMap().get("fileId"));
		try {
			FileUpload file = fileUploadService.selectByPrimaryKey(fileId);
			File pdfFile = new File(file.getFilePath());
			pdfFile.delete();
			// 查询当前登陆人信息
			String userCode = String.valueOf(context.getDataMap().get("userId"));
			ApUser userInfo = apUserService.queryApUserByUserCode(userCode);
			// 流水日志记录
			FileUploadLog filelog = new FileUploadLog();
			filelog.setId(StringUtil.randomUUIDPlainString());
			filelog.setFileId(fileId);
			filelog.setFileName(file.getFileName());
			filelog.setOperatCode(userCode);
			filelog.setOperatDate(new Date());
			filelog.setOperator(userInfo.getUserName());
			filelog.setOperatType("2");
			fileUploadService.deleteFileUpload(filelog,fileId);
			context.setData("msg", "删除成功!");
		} catch (Exception e) {
			logger.error("用户认证异常..", e);
			context.setData("msg", "系统异常!");
		}
	}

	/**
	 * 文档阅读权限配置
	 * 
	 * @return
	 */
	public void fileRightControl(Context context) throws CoreException {
		// 获得fileID
		String fileId = StringUtils.trimToEmpty((String) context.getDataMap().get("fileId"));

	}

	/**
	 * 查询当前所有管理员
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void allUser(Context context) throws CoreException {
		Map dataMap = new HashMap();
		List<ApUser> listUser = apUserService.queryAllApUser();
		List<MenuObj> finalTreeMenu = new ArrayList<MenuObj>();
		MenuObj obj = null;
		for (ApUser apUser : listUser) {
			obj = new MenuObj(apUser.getUserId(), apUser.getUserName(), "1", "1", "1");
			finalTreeMenu.add(obj);
		}
		dataMap.put("listUser", finalTreeMenu);
		context.setDataMap(dataMap);
	}

	/**
	 * 用户文档权限配置保存
	 * 
	 * @param context
	 */
	public void userRightControlSave(Context context) {
		try {
			Gson gson = new Gson();
			// String smallCategory =
			// gson.toJson(context.getDataMap().get("smallCategory"));
			String jsonData = gson.toJson(context.getDataMap().get("menuInfo"));
			List<ApUser> list = gson.fromJson(jsonData, new TypeToken<List<ApUser>>() {
			}.getType());
			apUserService.updateFileRightControlBatch(list);
			context.setData("msg", "用户文档权限授予成功！!");
		} catch (Exception e) {
			logger.error("用户认证异常..", e);
			context.setData("msg", "系统异常!");
		}

	}

	/**
	 * 文件浏览
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void fileView(String converfilename) throws IOException, InterruptedException {
		// 转换器安装路径cmd
		Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
		String exePath = paroperMap.get("PDF2SWF_PATH");// "D:\\swf\\pdf2swf.exe";
		String firstSwfPath = paroperMap.get("FIRST_SWF_PATH");
		String projWorkSpace = paroperMap.get("PROJ_WORK_SPACE");
		PDF2SWFUtil.pdf2swf(converfilename, exePath, firstSwfPath, projWorkSpace);
	}

	/**
	 * 文档流水信息
	 * 
	 * @param context
	 */
	public void fileUploadLog(Context context) {
		String fileId = StringUtils.trimToEmpty((String) context.getDataMap().get("fileId"));
		logger.error("文档流水信息fileId=" + fileId);
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileId", fileId);
			int count = fileUploadLogService.queryFileCount(map);

			List<FileUpload> list = new ArrayList<FileUpload>();

			if (count > 0) {
				list = fileUploadLogService.queryFileList(map);
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("total", count);
			dataMap.put("rows", list);

			context.setDataMap(dataMap);
			context.setData("userOrgList", list);
			context.setData("isSuccess", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ITokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(ITokenService tokenService) {
		this.tokenService = tokenService;
	}

	public ApUserService getApUserService() {
		return apUserService;
	}

	public FileUploadLogService getFileUploadLogService() {
		return fileUploadLogService;
	}

	public void setFileUploadLogService(FileUploadLogService fileUploadLogService) {
		this.fileUploadLogService = fileUploadLogService;
	}

	public void setApUserService(ApUserService apUserService) {
		this.apUserService = apUserService;
	}

}
