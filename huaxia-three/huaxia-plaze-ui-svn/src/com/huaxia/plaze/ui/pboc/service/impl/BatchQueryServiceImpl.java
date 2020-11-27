package com.huaxia.plaze.ui.pboc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;
import com.huaxia.plaze.ui.pboc.domain.BatchQueryReview;
import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;
import com.huaxia.plaze.ui.pboc.mapper.FileStorageMapper;
import com.huaxia.plaze.ui.pboc.mapper.BatchQueryMapper;
import com.huaxia.plaze.ui.pboc.mapper.SingleQueryMapper;
import com.huaxia.plaze.ui.pboc.service.BatchQueryService;
import com.huaxia.plaze.ui.pboc.service.SingleQueryService;

@Service("batchQueryService")
public class BatchQueryServiceImpl implements BatchQueryService {

	// 批量导入的Mapper层
	@Resource
	private BatchQueryMapper<BatchQueryReview> batchQueryMapper;

	@Resource
	private SingleQueryMapper<SingleQueryReview> singleQueryMapper;
	
	@Resource
	private SingleQueryService singleQueryService;

	@Resource
	private FileStorageMapper<FileStorageInfo> fileStorageMapper;

	/* 查询批量分页总数 */
	@Override
	public int queryBatchIdListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return batchQueryMapper.selectBatchIdListCountByPage(args);
	}

	/* 查询批量分页记录 */
	@Override
	public List<BatchQueryReview> queryBatchIdListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		List<BatchQueryReview> list = batchQueryMapper.selectBatchIdListByPage(args);
		String crtTime = null;
		String batchId = null;
		// 批次号转换为时间
		for (BatchQueryReview batch : list) {
			batchId = batch.getBatchId();
			crtTime = batchId.substring(0, 4) + "-" + batchId.substring(4, 6) + "-" + batchId.substring(6, 8) + " "
					+ batchId.substring(8, 10) + ":" + batchId.substring(10, 12) + ":" + batchId.substring(12, 14);
			batch.setCrtTime(crtTime);
		}
		return list;
	}

	/* 查询批量退回分页记录 */
	@Override
	public List<BatchQueryReview> queryBatchIdRefuseListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		List<BatchQueryReview> list = batchQueryMapper.selectBatchIdRefuseListByPage(args);
		String crtTime = null;
		String batchId = null;
		// 批次号转换为时间
		for (BatchQueryReview batch : list) {
			batchId = batch.getBatchId();
			crtTime = batchId.substring(0, 4) + "-" + batchId.substring(4, 6) + "-" + batchId.substring(6, 8) + " "
					+ batchId.substring(8, 10) + ":" + batchId.substring(10, 12) + ":" + batchId.substring(12, 14);
			batch.setCrtTime(crtTime);
		}
		return list;
	}

	/* 批次查询--导入记录 分页总数量 */
	@Override
	public int queryBatchListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return batchQueryMapper.selectBatchListCountByPage(args);
	}

	/* 批次查询--导入记录 分页记录 */
	@Override
	public List<BatchQueryReview> queryBatchListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return batchQueryMapper.selectBatchListByPage(args);
	}

	/* 批次查询--查询导入文件明细总数 */
	@Override
	public int queryFileDetailCount(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return singleQueryMapper.selectFileDetailCount(args);
	}

	/* 批次查询--导入文件分件明细 */
	@Override
	public List<SingleQueryReview> queryFileDetailListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return singleQueryMapper.selectFileDetailListByPage(args);
	}

	/* 批量查询复核--提交查询 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateSubmitAgreeBatch(Map<String, Object> args) {
		// 更新明细
		int count = singleQueryMapper.updateSubmitAgreeBatch(args);
		if (count > 0) {
			// 更新导入记录状态
			return batchQueryMapper.updateStatus(args);
		}
		return count;
	}

	/* 批量查询复核--退回 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateSubmitRejectBatch(Map<String, Object> args) {
		int count = singleQueryMapper.updateSubmitRejectBatch(args);
		if (count > 0) {
			return batchQueryMapper.updateStatus(args);
		}
		return count;
	}

	/* 批量复核退回--修改提交 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int batchUpdate(List<FileStorageInfo> infoList, SingleQueryReview queryReview, MultipartFile[] files,
			String batchNo, String oldBatchNo) throws Exception {
		int count = 0;
		for (FileStorageInfo info : infoList) {
			count = fileStorageMapper.insert(info);
		}
		// 删除明细
		count = singleQueryMapper.deleteReviewBybatchId(oldBatchNo);
		if (count > 0) {
			// 删除导入记录
			count = batchQueryMapper.deleteBatchBybatchId(oldBatchNo);
			if (count > 0) {
				// 在插入新的记录
				count = singleQueryService.saveObjectList(queryReview, files, batchNo);
			}
		}
		return count;
	}

}
