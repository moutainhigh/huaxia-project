package com.huaxia.plaze.ui.pboc.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;

public interface SingleQueryMapper<T> {

	int insertObject(T t);

	int selectListCountByPage(Map<String, Object> args);

	List<SingleQueryReview> selectListByPage(Map<String, Object> args);

	int updateBatchAgree(Map<String, Object> args);

	int updateBatchReject(Map<String, Object> args);

	int updateSubmitAgree(Map<String, Object> args);

	int updateSubmitReject(Map<String, Object> args);

	int selectFileDetailCount(Map<String, Object> args);

	List<SingleQueryReview> selectFileDetailListByPage(Map<String, Object> args);

	SingleQueryReview selectObjectById(String id);

	int updateObject(SingleQueryReview queryReview);

	int selectAllowCount(Map<String, Object> args);

	int updateSubmitAgreeBatch(Map<String, Object> args);

	int updateSubmitRejectBatch(Map<String, Object> args);

	int deleteReviewBybatchId(String oldBatchNo);

	int selectUserStatusByTrnId(Map<String, Object> map);

	int selectCountByReferTime(Map<String, Object> args);

}
