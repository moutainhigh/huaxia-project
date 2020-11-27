package com.huaxia.plaze.ui.fico.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.fico.domain.FicoBatch;
import com.huaxia.plaze.ui.fico.domain.FicoBatchDetail;
import com.huaxia.plaze.ui.fico.domain.FicoBatchFile;
import com.huaxia.plaze.ui.fico.mapper.FicoBatchDetailMapper;
import com.huaxia.plaze.ui.fico.mapper.FicoBatchFileMapper;
import com.huaxia.plaze.ui.fico.mapper.FicoBatchMapper;
import com.huaxia.plaze.ui.fico.service.FicoBatchService;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchFile;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.SHAUtil;
@Service("ficoBatchService")
public class FicoBatchServiceImpl implements FicoBatchService {

	private final static Logger logger = LoggerFactory.getLogger(FicoBatchServiceImpl.class);

	@Resource
	private FicoBatchMapper ficoBatchMapper;
	@Resource
	private FicoBatchFileMapper ficoBatchFileMapper;
	@Resource
	private FicoBatchDetailMapper ficoBatchDetailMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public int saveUploadRecord(MultipartFile file, String batchNo, String account) throws Exception {
		FicoBatchFile batchFile = new FicoBatchFile();
		String fileId = SequenceUtil.getUUID();
		String fileName = file.getOriginalFilename();
		//判断文件名是否重复，文件名重复禁止查询
		int isDuplicate = ficoBatchFileMapper.isTheFileNameDuplicate(fileName);
		if(isDuplicate > 0){
			throw new Exception("文件[ " + fileName + " ]，文件名重复，文件已上传");
		}
		if (fileName.endsWith(".txt")) {
			int rows = 1;
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
			String record = reader.readLine();
			while (StringUtils.isNotBlank(record)) {
				if (rows > 20000) {
					throw new Exception("文件[ " + fileName + " ]数量超过最大限制");
				}
				FicoBatchDetail detail = new FicoBatchDetail();
				detail.setCrtUser(account);
				detail.setBatchFileId(fileId);
				detail.setTrnId(GuidUtil.getGuid());
				detail.setQueryStatus("0");

				// 数据记录校验
				String[] columns = record.split("\\|");
				if (columns.length != 5) {
					throw new ApplicationException("文件" + fileName + "第" + rows + "行格式不对");
				}
				if (columns[0].length() > 64) {
					throw new Exception("文件" + fileName + "第" + rows + "行 [ 证件号码 ] 长度校验失败");
				}
				if (columns[1].length() > 32) {
					throw new Exception("文件" + fileName + "第" + rows + "行 [ 手机号码 ] 长度校验失败");
				}
				if (columns[4].length() > 16) {
					throw new Exception("文件" + fileName + "第" + rows + "行 [ 唯一编号 ] 长度校验失败");
				}
				
				detail.setCertNo(columns[0]);
				detail.setMobile(columns[1]);
				detail.setTopThreeMobile(columns[2]);
				detail.setInputSysDate(columns[3]);
				detail.setUniqueID(columns[4]);
				detail.setSequenceNumber(rows+"");
				detail.setEncryptionCertNO(SHAUtil.sha256(columns[0]));
				detail.setEncryptionMobile(SHAUtil.sha256(columns[1]));
				// 数据记录信息持久化
				rows += ficoBatchDetailMapper.insertObject(detail);
				record = reader.readLine();
			}
			
			// 恢复预读取多读的一行即为最终记录总数
			rows--;
			
			// 数据文件信息持久化
			batchFile.setCrtUser(account);
			batchFile.setBatchId(batchNo);
			batchFile.setBatchFileId(fileId);
			batchFile.setBatchFileName(fileName);
			batchFile.setBatchFileRecordSize(rows);
			ficoBatchFileMapper.insertObject(batchFile);
			return rows;
		}
		return 0;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveTrnBatch(FicoBatch ficoBatch) {
		return ficoBatchMapper.insertObject(ficoBatch);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveTrnBatchFile(FicoBatchFile ficoBatchFile) {
		return ficoBatchFileMapper.insertObject(ficoBatchFile);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveTrnBatchDetail(FicoBatchDetail ficoBatchDetail) {
		return ficoBatchDetailMapper.insertObject(ficoBatchDetail);
	}
	/**
	 * 查询批次表
	 * @param args
	 * @return
	 */
	@Override
	public List<FicoBatch> queryBatchListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return ficoBatchMapper.selectBatchListByPage(args);
	}
	/**
	 * 查询总记录数目
	 * @param args
	 * @return
	 */
	@Override
	public int queryBatchListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return ficoBatchMapper.selectBatchListCountByPage(args);
	}
	/**
	 * 查询数目
	 * @param args
	 * @return
	 */
	@Override
	public int queryDetailListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return ficoBatchDetailMapper.selectBatchListCountByPage(args);
	}	
	
	@Override
	public List<FicoBatchFile> queryDetailListByPage(String batchId) {
		return ficoBatchFileMapper.selectDetailListByPage(batchId);
	}
	@Override
	public List<FicoBatchDetail> queryFileDetailListByPage(String batchFileId) {
		return ficoBatchDetailMapper.selectDetailListByPage(batchFileId);
	}
	/**
	 * 查询批次表
	 * @param args
	 * @return
	 */
	@Override
	public List<FicoBatchDetail> queryFileDetailListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return ficoBatchDetailMapper.selectBatchListByPage(args);
	}
	/**
	 * 统计BatchDetail数量
	 * @param args
	 * @return
	 */
	public int countBatchDetailNumber(Map<String, Object> args){
		return ficoBatchDetailMapper.countNumber(args);
	}
	/**
	 * 统计BatchFile数量
	 * @param args
	 * @return
	 */
	public int countBatchFileNumber(Map<String, Object> args){
		return ficoBatchFileMapper.countNumber(args);
	}
}
