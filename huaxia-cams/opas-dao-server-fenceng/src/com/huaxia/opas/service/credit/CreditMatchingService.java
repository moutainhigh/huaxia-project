package com.huaxia.opas.service.credit;
import java.util.Map;

import com.huaxia.opas.domain.credit.CreditMatching;
/*******************************
 *@describe:人行征信查询匹配设置 service
 *@author：xiaoJianFeng
 *@date:2017-03-19
 ********************************/
public interface CreditMatchingService {
	/********************************
	 *@describe:人行征信查询匹配设置 分布查询
	 *@param creditMatching
	 *@param begNum
	 *@param pageNum
	 *@author：xiaoJianFeng
	 *@date:2017-03-19
	 *@return
	 *********************************/
	Map<String, Object> selectCreditMatchPage(CreditMatching creditMatching, int begNum, int pageNum);
	/*************************************
	 *@describe:添加 人行征信查询匹配设置
	 *@param creditMatching
	 *@author：xiaoJianFeng
	 *@date:2017-03-19
	 *************************************/
	void addCreditMatch(CreditMatching creditMatching);
	/*************************************
	 *@describe:修改 人行征信查询匹配设置
	 *@param creditMatching
	 *@author：xiaoJianFeng
	 *@date:2017-03-19
	 *************************************/	
	void updateCreditMatch(CreditMatching creditMatching);
	/*************************************
	 *@describe:删除 人行征信查询匹配设置
	 *@param autoId
	 *@author：xiaoJianFeng
	 *@date:2017-03-19
	 *************************************/	
	void deleteCreditMatch(String autoId);

}
