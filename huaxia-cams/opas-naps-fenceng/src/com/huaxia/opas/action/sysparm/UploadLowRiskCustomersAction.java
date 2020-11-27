package com.huaxia.opas.action.sysparm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import javax.annotation.Resource;
import java.util.concurrent.Executors;
import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotAppId;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.sysparm.LowRiskCustomers;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.BatchFileLowRiskInfoService;
import com.huaxia.opas.service.sysparm.LowRiskCustomersService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.service.system.KeyValueConfigureService;
import com.huaxia.opas.util.MapTools;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 定时解析文件入库
 * @author liuwei
 *
 */
public class UploadLowRiskCustomersAction  implements Action{
	private final static Logger logger = LoggerFactory.getLogger(UploadLowRiskCustomersAction.class);
	
//	@Value("${upload.newlowriskpath}")
//	public  String uploadPath="/app/opas/lowristlist/";
	@Resource(name = "keyValueConfigureService")
	private KeyValueConfigureService  keyValueConfigureService;
	@Autowired
	private BatchFileLowRiskInfoService batchFileLowRiskInfoService;
	public  String uploadPath;
	public  String uploadPathTemp;
	@Autowired
	private LowRiskCustomersService lowRiskCustomersService ;
//	@Resource(name = "approveReasonCodeService")
//	private ApproveReasonCodeService approveReasonCodeService;
	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	
	
	/**
	 * 低风险名单库, 定时每天执行该方法.每分钟调用改方法判断时间和数据库是否一致
	 */
	public void executeCheckTime() {
		String timeReq = this.getNowTimeHHmm();
		String times = keyValueConfigureService.getValueContentByKeyName("LOWRISKQUARTZTIME");   
		if (null != times && times.indexOf(timeReq) != -1) {
			parseFileInDB();
		}
	}
	/**
	 * 返回当前时间12:22
	 */
	public static String getNowTimeHHmm() {
		long t = System.currentTimeMillis();
		Date date = new Date(t);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String timeReq = sdf.format(date);
		return timeReq;
	}

	/**
	 * 定时解析文件入库
	 */
	public void parseFileInDB() {
		// 当前状态Map
		Map<String,String> currStatusMap = new HashMap<String,String>();
		currStatusMap.put("启用","1");
		currStatusMap.put("停用","0");
		//名单类型
		Map<String,String> riskType = new HashMap<String,String>();
		riskType.put("信用","2");
		riskType.put("欺诈","1");
		uploadPath = keyValueConfigureService.getValueContentByKeyName("NEWLOWRISKPATH");  
		//创建固定大小的3个线程的线程池
		int corePoolSize = 3;
		int maximumPoolSize = 3;
		int keepAliveTime = 5;
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
		ExecutorService es = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
				TimeUnit.SECONDS, workQueue, handler);
		
		File file = new File(uploadPath);
		File[] array = file.listFiles();
		//获取目录下的每个文件
		for(int i =0;i<array.length;i++){
//			if(array[i].isFile()){
//				System.out.println(array[i].getName());
//				System.out.println(array[i]);
//				System.out.println(array[i].getPath());
//			}
			String fileName = array[i].getName();
			String userName = batchFileLowRiskInfoService.selectUser();
			StringBuffer errorMessage = new StringBuffer("");
			//清除临时表数据，再进行解析文件插入数据
			try{
				lowRiskCustomersService.deleteLowRiskCustomersTempAll(new String(""));
			}catch(Exception e){
				logger.error("删除异常，异常信息[ {} ]。",new Object[] { e.getMessage() });
			}
			int rows = 0;
			int total = 0;
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("total", total);
			args.put("errorMessage", "success");
			try {
				FileInputStream fileInputStream = new FileInputStream(array[i]);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
				BufferedReader reader = new BufferedReader(inputStreamReader);
				try {
					String record;
					//把ArrayList转化成线程安全的，使用Collections.synchronizedList
					List<LowRiskCustomers> list =new ArrayList<LowRiskCustomers>();
					/**前四行是request的内容，不用处理，跳过
					 * ------WebKitFormBoundaryg95v1Y5T1sAYodoO
						Content-Disposition: form-data; name="file1"; filename="上传文件练习4.txt"
						Content-Type: text/plain
					 */
						record = reader.readLine();
						record = reader.readLine();
						record = reader.readLine();
						record = reader.readLine();
					while (StringUtils.isNotBlank(record = reader.readLine())) {
						total++;
					/*	Thread.sleep(1);*/
//						if (rows > 20000) {
//							throw new Exception("文件[ " + fileName + " ]数量超过最大限制");
//						}
						LowRiskCustomers lowRiskCustomer = new LowRiskCustomers();
						String uuid = SequenceUtil.getUUID();
						lowRiskCustomer.setUuid(uuid);
						lowRiskCustomer.setCrtUser(userName);
						lowRiskCustomer.setCrtTime(new Timestamp(new Date().getTime()));
					try{
						// 数据记录校验
						String[] columns = record.split("\\^\\|");
						if (columns.length != 6) {
							 if(columns.length != 5){
								 //读到文件末尾，进行跳出循环，文件解析完成
								 if(record.startsWith("------WebKitForm")){
										break;
									}
								 errorMessage.append("文件" + fileName + "第" + (total) + "行格式校验失败");
								 continue;
								}
						}
						if (columns[0].getBytes("gbk").length > 30) {
							 errorMessage.append("文件" + fileName + "第" + (total) + "行长度校验失败");
							 continue;
						}else{
							lowRiskCustomer.setCusName(columns[0]);
						}
						if (columns[1].getBytes("gbk").length > 19) {
							 errorMessage.append("文件" + fileName + "第" + (total) + "行长度校验失败");
							 continue;
						}else{
							lowRiskCustomer.setCredNo(columns[1]);
						}
						if (columns[2].getBytes("gbk").length > 12) {
							 errorMessage.append("文件" + fileName + "第" + (total) + "行长度校验失败");
							 continue;
						}else{
							lowRiskCustomer.setMobile(columns[2]);
						}
						if (columns[3].getBytes("gbk").length > 150) {
							errorMessage.append("文件" + fileName + "第" + (total) + "行长度校验失败");
							 continue;
					    }else{
							lowRiskCustomer.setCompanyName(columns[3]);
						}
						if (columns[4].getBytes("gbk").length > 1) {
							errorMessage.append("文件" + fileName + "第" + (total) + "行长度校验失败");
							 continue;
						}else{
//							String listType = riskType.get(columns[4]);
							lowRiskCustomer.setListType(columns[4]);
						}
						if(columns.length == 5){
							//不输入用户状态，默认是启用
							lowRiskCustomer.setSTATUS("1");
						}else{
							if (columns[5].getBytes("gbk").length > 4) {
								errorMessage.append("文件" + fileName + "第" + (total) + "行长度校验失败");
								 continue;
							}else{
								String sTATUS = currStatusMap.get(columns[5]);
								lowRiskCustomer.setSTATUS(sTATUS);
							}
						}
					}catch(Exception e){
						logger.error("文件[ {} ]解析失败，异常信息[ {} ]。",new Object[] { fileName, e.getMessage() });
						errorMessage.append("文件" + fileName + "第" + (total) + "行解析失败"+e.getMessage());
					}
						// 数据记录信息持久化
						rows ++;
//						logger.info("lowRiskCustomer，的值[ {} ]。",new Object[] { fileName, lowRiskCustomer.toString() });
						list.add(lowRiskCustomer);
						if(rows > 30000){
							//多线程，30000条提交一次
							List<LowRiskCustomers> listTemp = list;
							 list =  new ArrayList<LowRiskCustomers>(); 
							es.submit(new LowRiskThread(listTemp,errorMessage,total));
							rows = 0;
						}
					}
					logger.info("现在行数："+rows+",list="+list.size()+"，总共输入行数"+total);
//					lowRiskCustomersService.insertLowRiskCustomersList(list);
					Map<String,Object> param2 = new HashMap<String,Object>();
					param2.put("list", list);
					lowRiskCustomersService.insertLowRiskCustomersListCall(param2);
					Map param = new HashMap<>();
					//判断线程池执行完毕，执行下一步
					es.shutdown();
					while(true){
						if(es.isTerminated()){
							break;
						}
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//把主表的listtype为1的数据插入临时表，然后把临时表和主表更换表名，存储过程实现
					lowRiskCustomersService.executeProceLowRiskRename(param);
					//数据库字段为4000
					args.put("total", total);
					if(StringUtil.isNotBlank(errorMessage.toString())){
						args.put("errorMessage", errorMessage.toString().getBytes("gbk").length>=4000?errorMessage.toString().substring(0,2000):errorMessage.toString()+"");
					}else{
						args.put("errorMessage", "success");
					}
					batchFileLowRiskInfoService.updateTotalCount(args);
					batchFileLowRiskInfoService.updateStatus("2");
					//文件写入临时目录，删除
					writetoTempFold(array[i]);
				} catch (UnsupportedEncodingException e) {
					logger.error("文件[ {} ]处理失败，异常信息[ {} ]。",new Object[] { fileName, e.getMessage() });
					errorMessage.append("文件[ {"+fileName+"} ]处理失败，异常信息[ {"+e.getMessage()+"} ]。");
					args.put("total", total);
					if(StringUtil.isNotBlank(errorMessage.toString())){
						args.put("errorMessage", errorMessage.toString().getBytes("gbk").length>=4000?errorMessage.toString().substring(0,2000):errorMessage.toString()+"");
					}else{
						args.put("errorMessage", "success");
					}
					batchFileLowRiskInfoService.updateTotalCount(args);
					batchFileLowRiskInfoService.updateStatus("3");
					return;
				}finally{
					reader.close();
					inputStreamReader.close();
					fileInputStream.close();
				}
			} catch (FileNotFoundException e) {
				logger.error("文件[ {} ]处理失败，异常信息[ {} ]。",new Object[] { fileName, e.getMessage() });
				errorMessage.append("文件[ {"+fileName+"} ]处理失败，异常信息[ {"+e.getMessage()+"} ]。");
				args.put("total", total);
				if(StringUtil.isNotBlank(errorMessage.toString())){
					try {
						args.put("errorMessage", errorMessage.toString().getBytes("gbk").length>=4000?errorMessage.toString().substring(0,2000):errorMessage.toString()+"");
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						logger.error("文件[ {} ]处理失败，异常信息[ {} ]。",new Object[] { fileName, e.getMessage() });
					}
				}else{
					args.put("errorMessage", "success");
				}
				batchFileLowRiskInfoService.updateTotalCount(args);
				batchFileLowRiskInfoService.updateStatus("3");
				return;
			}catch(Exception e){
				logger.error("文件[ {} ]处理失败，异常信息[ {} ]。",new Object[] { fileName, e.getMessage() });
				errorMessage.append("文件[ {"+fileName+"} ]处理失败，异常信息[ {"+e.getMessage()+"} ]。");
				args.put("total", total);
				if(StringUtil.isNotBlank(errorMessage.toString())){
					try {
						args.put("errorMessage", errorMessage.toString().getBytes("gbk").length>=4000?errorMessage.toString().substring(0,2000):errorMessage.toString()+"");
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						logger.error("文件[ {} ]处理失败，异常信息[ {} ]。",new Object[] { fileName, e.getMessage() });
					}
				}else{
					args.put("errorMessage", "success");
				}
				batchFileLowRiskInfoService.updateTotalCount(args);
				batchFileLowRiskInfoService.updateStatus("3");
				return;
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("异常信息[ {} ]。",new Object[] { e.getMessage() });
		}
		//上传成功后把文件删除
		File[] files = file.listFiles();
		if(files != null){
			//文件被占用无法删除，调用垃圾回收，回收内存占用文件
			System.gc();
			for(File f : files){
				f.delete();
			}
		}
	}
	/**
	 * 多线程实体类
	 * @author liuwei
	 *
	 */
	class LowRiskThread implements Runnable{
		List<LowRiskCustomers> list;
		StringBuffer errorMessage;
		int total;
		public LowRiskThread(List<LowRiskCustomers> list,StringBuffer errorMessage,int total){
			this.list =list;
			this.errorMessage = errorMessage;
			this.total = total;
		}
		@Override
		public void run() {
			try{
//				lowRiskCustomersService.insertLowRiskCustomersList(list);
				//有异常就捕获记录异常，程序继续处理文件
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("list", list);
				lowRiskCustomersService.insertLowRiskCustomersListCall(param);
				list.clear();
			}catch(Exception e){
				logger.error("文件处理失败，异常信息[ {} ]。",new Object[] {  e.getMessage() });
				errorMessage.append("文件入库异常，异常信息[ {"+e.getMessage()+"} ]");
			}
			logger.info(Thread.currentThread().getName()+",现在解析listSize="+list.size()+",文件入库"+total);
		}
	}
	/**
	 * 把文件写到临时目录
	 * @param file
	 */
	public void writetoTempFold(File file){
		uploadPathTemp = keyValueConfigureService.getValueContentByKeyName("NEWLOWRISKTEMPPATH");   
		try {
			//文件路径不存在就自动创建
			File fd = new File(uploadPathTemp);
			if(!fd.exists()){
				fd.mkdirs();
			}
			//清空文件夹下所有文件,然后写入文件
			File[] files = fd.listFiles();
			if(files != null){
				//文件被占用无法删除，调用垃圾回收，回收内存占用文件
				System.gc();
				for(File f : files){
					//文件删除失败，被占用解析，给页面提示
					if(!f.delete()){
						logger.info("文件[ {} ]移动到临时目录处理失败。",new Object[] { file.getName()});
					};
				}
			}
			String uploadPath2 = uploadPathTemp + file.getName();
			FileOutputStream fileOutputStream = new FileOutputStream(uploadPath2);
			DataOutputStream outputStream  = new DataOutputStream(fileOutputStream);
			int length = 0;
			byte[] buffer = new byte[1024];
			File file2 = new File(uploadPath+file.getName());
			FileInputStream inputStream = new FileInputStream(file2);
			DataInputStream datainputStream = new DataInputStream(inputStream);
		    while((length  = datainputStream.read(buffer))!=-1){
		    	outputStream.write(buffer,0,length);
		    }
		    outputStream.flush();
		    outputStream.close();
		    
		    datainputStream.close();
		    inputStream.close();
		    outputStream.close();
		    fileOutputStream.close();
		} catch (Exception e) {
			logger.error("文件[ {} ]移动到临时目录处理失败，异常信息[ {} ]。",new Object[] { file.getName(), e.getMessage() });
		}
	}
	/**
	 * 分页查询征信电话核查白名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	public void queryCreditTelcheckHistoryList(Context ctx)throws CoreException{
		CreditTelcheckList creditTelcheckList = getCreditTelcheck(ctx,"autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = lowRiskCustomersService.queryCreditTelcheckHistoryList(creditTelcheckList, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 分页查询低风险客户名单库列表
	 * 
	 * @author luzhen.ou
	 * */	
	public void queryLowRiskCustomerList(Context ctx) throws CoreException{
		LowRiskCustomers lowRiskCustomer = getLowRiskCustomer(ctx, "createTime");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		try {
			Map<String, Object> args = MapTools.objectToMap(lowRiskCustomer);
			args.put("pageNo", curPage);
			args.put("pageSize", pageNum);
			Map<String, Object> dataMap = lowRiskCustomersService.queryLowRiskDomainList(lowRiskCustomer,args);
			ctx.setDataMap(dataMap);	
		} catch (IllegalAccessException e) {
			logger.error("UploadLowRiskCustomersAction处理失败，异常信息[ {} ]。",new Object[] { e.getMessage() });
		}
	}

	/**
	 * 单条新增低风险客户名单实例
	 * 
	 * @author liuwei
	 * */
	public void insertLowRiskCustomer(Context ctx) throws CoreException{
		try{
			LowRiskCustomers lowRiskCustomers = getLowRiskCustomer(ctx);
			checkData(lowRiskCustomers);
			lowRiskCustomers.setUuid(SequenceUtil.getUUID());
			lowRiskCustomersService.insertLowRiskCustomer(lowRiskCustomers);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 修改低风险客户名单实例
	 *  
	 * @author luzhen.ou
	 */
	public void updateLowRiskCustomer(Context ctx) throws CoreException{
		try{
			LowRiskCustomers lowRiskCustomers = getLowRiskCustomer(ctx);
//			checkData(creditTelcheckList);
			lowRiskCustomersService.updateLowRiskCustomers(lowRiskCustomers);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/**
	 * 删除低风险名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	public void deleteRiskCustomers(Context ctx) throws CoreException{
		try {
			LowRiskCustomers lowRiskCustomer  = getLowRiskCustomer(ctx, "ids");
			lowRiskCustomersService.deleteLowRiskCustomers(lowRiskCustomer);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	/**
	 * 全部低风险名单实例
	 *  
	 * @author luzhen.ou
	 */	
	public void deleteRiskCustomersAll(Context ctx) throws CoreException{
		try {
			lowRiskCustomersService.deleteLowRiskCustomersAll(null);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	/**
	 * 批量修改征信电话核查白名单实例启用状态
	 *  
	 * @author luzhen.ou
	 */	
	public void updateCreditTelcheckActive(Context ctx) throws CoreException{
		try {
			LowRiskCustomers lowRiskCustomers = getLowRiskCustomer(ctx,"ids","status");
			lowRiskCustomersService.updateLowRiskCustomersActive(lowRiskCustomers);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/*
	*//**
	 * 批量导入征信电话核查白名单实例Excel
	 * 
	 * @author luzhen.ou
	 * *//*
	public void insertCreditTelcheckFileUpload (Context ctx) throws CoreException{
		try {
			InputStream in = null;
			Map<String,Object> map = ctx.getDataMap();
			String fileName = (String) map.get("fileName");
			in = new FileInputStream(fileName);
			ExcelReader excelReader = new ExcelReader();
			List<CreditTelcheckList> list = readExcel2(in,ctx);
			lowRiskCustomersService.insertCreditTelcheckList(list);
			ctx.setData("isSuccess", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	*/

	
	/**
	 * 身征信电话核查白名单实例新增和修改数据校验
	 *  
	 * @author luzhen.ou
	 */	
	public void checkData(LowRiskCustomers lowRiskCustomers) throws CoreException{
		
		String txt= "[^_][\\u4e00-\\u9fa5[^\\x00-\\xff][\\w]]*";	
		String code = "[a-zA-Z0-9]{1,16}";
		String tel1 ="\\d{3}-\\d{8}";
		String tel2 ="\\d{4}-\\d{7}";
		
//		String tmp = creditTelcheckList.getAppId();
//		if ( null != tmp && !tmp.trim().isEmpty() ) {
//			if (tmp.trim().length() > 16) {
//				throw new CoreException("申请件编号长度不能超过16个字符!");
//			}
//			if (!tmp.trim().matches(code) ) {
//				throw new CoreException("申请件编号应为英文、数字组成!");
//			}
//		}
//		
		String tmp = lowRiskCustomers.getCompanyName();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 50) {
				throw new CoreException("单位名称长度不能超过50个字符!");
			}
			if (!tmp.trim().matches(txt) ) {
				throw new CoreException("单位名称应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			}
		}

//		tmp = creditTelcheckList.getCompanyTel();
//		if ( null != tmp && !tmp.trim().isEmpty() ) {
//			if (tmp.trim().length() >13) {
//				throw new CoreException("单位电话长度不能超过13个字符!");
//			}
//		}
//		
//		
//		tmp = creditTelcheckList.getMemo();
//		if (tmp != null &&!tmp.trim().isEmpty()) {
//			if (tmp.trim().length() > 200) {
//				throw new CoreException("备注长度不能超过200个字符!");
//			}
//		}
		
	}	
	
	/**
	 * 抽取前台页面获取数据的公共方法,并对传入数据进行后台校验
	 *  
	 * @author luzhen.ou
	 */	   
	public LowRiskCustomers getLowRiskCustomer(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
	
		Gson gson = new Gson();
		LowRiskCustomers lowRiskCustomer = gson.fromJson(gson.toJson(map), LowRiskCustomers.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		ApUser apUser = apUserService.queryApUserByUserCode(operator);
		lowRiskCustomer.setOperator(apUser.getUserName());
		lowRiskCustomer.setCrtUser(apUser.getUserName());
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
//				creditTelcheckList.setIds(ids);
				break;
				
			case "status":
				String currStatus = lowRiskCustomer.getSTATUS();
				if( null == currStatus || (!currStatus.trim().equals("1") && !currStatus.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值currStatus非法");
					throw e;
				}
				break;
				
			case "uuid":
				String autoId = lowRiskCustomer.getUuid();
				if( null == autoId || autoId.trim().isEmpty()){
					CoreException e = new CoreException("查询历史操作记录的uuid值为空");
					throw e;
				}
				break;
				
			case "createTime":
//			    creditTelcheckList.setCreateTime(createTime);
				break;
				
			default:
				break;
			}
		}
		return lowRiskCustomer;
	}
	/**
	 * 抽取前台页面获取数据的公共方法,并对传入数据进行后台校验
	 *  
	 * @author luzhen.ou
	 */	   
	public CreditTelcheckList getCreditTelcheck(Context ctx,String ...strings) throws CoreException{
	
		return null;
	}
	public static void main(String args[]){
		try {
			new UploadLowRiskCustomersAction().parseFileInDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
