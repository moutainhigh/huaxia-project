package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.ReviewDecisionDao;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;
import com.huaxia.opas.service.allot.ReviewDecisionService;
public class ReviewDecisionServiceImpl extends AbstractDAO implements ReviewDecisionService, Serializable {
	private static final Logger log = LoggerFactory.getLogger(ReviewDecisionServiceImpl.class);
	
	private static final long serialVersionUID = 203549783803228159L;
	@Resource(name = "reviewDecisionDao")
	private ReviewDecisionDao reviewDecisionDao;
	
	
	public ReviewDecisionDao getReviewDecisionDao() {
		return reviewDecisionDao;
	}

	public void setReviewDecisionDao(ReviewDecisionDao reviewDecisionDao) {
		this.reviewDecisionDao = reviewDecisionDao;
	}

	@Override
	public int countBatchCode(String appId) throws CoreException {
		return  reviewDecisionDao.countBatchCode(appId);
	}
	
	@Override
	public int saveReviewDecisionResult(OpasReviewDecisionResult ordr) throws CoreException {
		return reviewDecisionDao.insertBatchCode(ordr);
	}
	
	@Override
	public int updateReviewDecision(OpasReviewDecisionResult ordr) throws CoreException {
		return reviewDecisionDao.updateBatchCode(ordr);
	}

	@Override
	public int queryCount(String appId) throws CoreException {
		return  reviewDecisionDao.selectCount(appId);
	}
	
	@Override
	public int saveApprovaInfoSupp(Map map) throws CoreException {
		return reviewDecisionDao.insertApprovaInfoSupp(map);
	}
	
	@Override
	public int updateByAppId(Map map) throws CoreException {
		return reviewDecisionDao.updateByAppId(map);
	}
}
