package com.huaxia.plaze.ui.tongdun.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.tongdun.domain.MulBorAttentionList;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatch;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchDetail;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchFile;
import com.huaxia.plaze.ui.tongdun.domain.MulBorDiscreditCountMain;
import com.huaxia.plaze.ui.tongdun.domain.MulBorInfo;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;
import com.huaxia.plaze.ui.tongdun.domain.MulBorRiskList;
import com.huaxia.plaze.ui.tongdun.service.MulBorBatchDetailService;
import com.huaxia.plaze.ui.tongdun.service.MulBorBatchFileService;
import com.huaxia.plaze.ui.tongdun.service.MulBorBatchService;
import com.huaxia.plaze.ui.tongdun.service.MulBorConfigurateService;
import com.huaxia.plaze.ui.tongdun.service.MulBorHistoryService;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/** 多头借贷查询（批量） */
@Controller
@RequestMapping("tongdun/mulbor/batch")
public class MulBorBatchController {

	private final static Logger logger = LoggerFactory.getLogger(MulBorBatchController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private MulBorBatchService mulBorBatchService;

	@Resource
	private MulBorBatchFileService mulBorBatchFileService;

	@Resource
	private MulBorBatchDetailService mulBorBatchDetailService;

	@Resource
	private MulBorHistoryService mulBorHistoryQueryService;

	@Resource
	private MulBorConfigurateService mulBorConfigurateService;

	/** 多头借贷查询--批量实时查询页面 */
	@RequestMapping("index")
	public String index() {
		return "tongdun/mulbor_batch_index";
	}

	@RequestMapping("submit")
	@ResponseBody
	public String batchSubmit(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
		User loginUser = null;
		String batchNo = BatchNoGenerator.generateNextNumber();
		try {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			loginUser = JacksonUtil.jsonToObject(body, User.class);

			String result = saveObject(files, batchNo, loginUser);

			if (result.equals("success")) {
				return "提交成功    生成批次号为" + batchNo;
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			if (e instanceof ApplicationException) {
				return e.getMessage();
			}
			return "提交失败";
		}
	}

	/**
	 * 多头借贷查询--批次查询主页面
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping("pagelist/batch")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);
		page.addParameter("team", loginUser.getTeam());

		if (logger.isDebugEnabled()) {
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		int totalCount = mulBorBatchService.queryBatchListCountByPage(page);
		List<MulBorQueryReview> list = mulBorBatchService.queryBatchListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);

		return "tongdun/mulbor_batch_list";
	}

	/** 多头借贷--查询文件明细 */
	@RequestMapping("pagelist/batchfile")
	public String getFileDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchId = request.getParameter("batchId");
		List<MulBorBatchFile> list = mulBorBatchFileService.queryDetailListByPage(batchId);
		modelMap.put("records", list);
		return "tongdun/mulbor_batch_file_list";
	}

	/** 多头借贷--查询文件记录明细 */
	@RequestMapping("pagelist/filedetail")
	public String getFileDtail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchFileId = request.getParameter("batchFileId");
		List<MulBorQueryReview> list = mulBorBatchDetailService.queryDetailListByPage(batchFileId);
		modelMap.put("records", list);
		return "tongdun/mulbor_batch_detail_list";
	}

	/** 多头借贷--根据身份证号进行查询数据展示 */
	@RequestMapping("view/detail")
	public String getRecordDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String trnId = request.getParameter("trnId");
		String name = mulBorHistoryQueryService.queryName(trnId);
		MulBorInfo mulBorInfo = mulBorHistoryQueryService.queryMulbor(trnId);
		List<MulBorRiskList> mulBorRiskList = mulBorHistoryQueryService.queryMulBorRiskList(trnId);

		List<MulBorDiscreditCountMain> mulBorDiscreditCountMain = mulBorHistoryQueryService
				.queryMulBorDiscreditCountMain(trnId);
		List<MulBorAttentionList> mulBorAttentionList = mulBorHistoryQueryService.queryMulBorAttentionList(trnId);

		modelMap.put("name", name);
		modelMap.put("mulBorInfo", mulBorInfo);
		modelMap.put("mulBorRiskList", mulBorRiskList);
		modelMap.put("mulBorDiscreditCountMain", mulBorDiscreditCountMain);
		modelMap.put("mulBorAttentionList", mulBorAttentionList);
		return "tongdun/mulbor_batch_detail_show";
	}

	public void printMulBorInfo(MulBorInfo mulBorInfo) {
		if (mulBorInfo.getMulBorBase() != null) {
			System.out.println(mulBorInfo.getMulBorBase());
		}
		if (mulBorInfo.getMulBorRiskItemList() != null) {
			for (int i = 0; i < mulBorInfo.getMulBorRiskItemList().size(); i++) {
				System.out.println(mulBorInfo.getMulBorRiskItemList().get(i));
			}
		}
		if (mulBorInfo.getMulBorBlackListList() != null) {
			for (int i = 0; i < mulBorInfo.getMulBorBlackListList().size(); i++) {
				System.out.println(mulBorInfo.getMulBorBlackListList().get(i));
			}
		}
		if (mulBorInfo.getMulBorDescreditCountList() != null) {
			for (int i = 0; i < mulBorInfo.getMulBorDescreditCountList().size(); i++) {
				System.out.println(mulBorInfo.getMulBorDescreditCountList().get(i));
			}
		}
		if (mulBorInfo.getMulBorGreyListList() != null) {
			for (int i = 0; i < mulBorInfo.getMulBorGreyListList().size(); i++) {
				System.out.println(mulBorInfo.getMulBorGreyListList().get(i));
			}
		}
		if (mulBorInfo.getMulBorAntifraudIndex() != null) {
			System.out.println(mulBorInfo.getMulBorAntifraudIndex());
		}
	}

	/* 批量实时查询--解析批次文件数据入库 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveObject(MultipartFile[] files, String batchNo, User loginUser) throws Exception {
		// 定义文件输入流
		BufferedReader br = null;
		// 读取txt文件每一行记录
		String record = null;
		// 文件名
		String name = null;
		// 记录文件的总行数
		int i = 0;
		// 记录所有文件的总行数
		int allLine = 0;
		// 文件表
		try {
			// 查询证件类型
			for (MultipartFile file : files) {
				MulBorBatchFile fileDetail = new MulBorBatchFile();
				// 获取一个文件的uuid
				String fileId = SequenceUtil.getUUID();
				i = 0;
				// 文件名
				name = file.getOriginalFilename();
				if (name.indexOf("txt") != -1) {
					br = null;
					br = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
					record = br.readLine();
					while (StringUtils.isNotBlank(record)) {
						MulBorBatchDetail detail = new MulBorBatchDetail();
						i++;
						String[] strArray = record.split("\\|");
						if (strArray.length != 3) {
							throw new ApplicationException("文件" + name + "第" + i + "行格式不对");
						}
						// 批次文件中的每个查询入库
						detail.setCrtUser(loginUser.getAccount());
						detail.setBatchFileId(fileId);
						detail.setCertType("00");
						detail.setName(strArray[0]);
						detail.setCertNo(strArray[1]);
						detail.setMobile(strArray[2]);
						detail.setQueryStatus("0");
						mulBorBatchDetailService.save(detail);
						record = br.readLine();
					}

					if (i > 2000) {
						throw new ApplicationException("文件" + name + "导入数量不能大于2000");
					}
				} else {
					Workbook workbook = WorkbookFactory.create(file.getInputStream());

					Sheet sheet = workbook.getSheetAt(0);
					// 获取总行数
					int rowCount = sheet.getPhysicalNumberOfRows();
					i = rowCount;
					if (i > 2000) {
						throw new ApplicationException("文件" + name + "导入数量不能大于2000");
					}
					String noType;
					// 遍历每一行 第一行为标题行
					for (int r = 0; r < rowCount; r++) {
						MulBorBatchDetail detail = new MulBorBatchDetail();
						Row row = sheet.getRow(r);
						int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
						if (cellCount != 3) {
							throw new ApplicationException("文件" + name + "第" + (r + 1) + "行超过最大列数3");
						}
						// 遍历每一列
						for (int c = 0; c < cellCount; c++) {
							Cell cell = row.getCell(c);// 获得列值
							if (cell != null) {
								switch (c) {
								case 0: // 证件号
									detail.setName(cell.toString());
									break;
								case 1:
									detail.setCertNo(cell.toString());
									break;
								case 2:
									detail.setMobile(cell.toString());
									break;
								}
							}
						}
						// 批次文件中的每个查询入库
						detail.setCrtUser(loginUser.getAccount());
						detail.setBatchFileId(fileId);
						detail.setCertType("00");
						detail.setQueryStatus("0");
						mulBorBatchDetailService.save(detail);
					}
				}
				if (i == 0) {
					throw new ApplicationException("文件" + name + "是空文件");
				}
				allLine += i;

				// 各个批次文件入库
				fileDetail.setCrtUser(loginUser.getAccount());
				fileDetail.setBatchId(batchNo);
				fileDetail.setBatchFileId(fileId);
				fileDetail.setBatchFileName(name);
				fileDetail.setBatchFileRecordSize(i);
				mulBorBatchFileService.save(fileDetail);
			}

			// 创建这个批次的存储数据，创建批次表，批次表入库
			MulBorBatch mulBorBatch = new MulBorBatch();
			mulBorBatch.setCrtUser(loginUser.getAccount());
			mulBorBatch.setBatchId(batchNo);
			mulBorBatch.setBatchRecordSize(allLine);
			mulBorBatchService.save(mulBorBatch);
			// 查询接口设置
			int resultInterface = mulBorConfigurateService.isAllowSingleQuery("1");
			if (resultInterface != 1) {
				if (resultInterface == -1) {
					throw new ApplicationException("当前时间不允许查询");
				} else if (resultInterface == -2) {
					throw new ApplicationException("查询数量达到上限");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof ApplicationException) {
				throw e;
			}
			throw new Exception("请求异常");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return "success";
	}
}
