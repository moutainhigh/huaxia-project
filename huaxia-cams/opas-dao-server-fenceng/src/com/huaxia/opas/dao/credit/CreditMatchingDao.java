package com.huaxia.opas.dao.credit;
import java.util.List;
import com.huaxia.opas.domain.credit.CreditMatching;
/*******************************
 *@describe:人行征信查询匹配设置 接口
 *@author：xiaoJianFeng
 *@date:2017-03-02
 ********************************/
public interface CreditMatchingDao {
	/********************************
	 *@describe:人行征信查询匹配设置 分布查询
	 *@param CreditMatching
	 *@param begNum
	 *@param pageNum
	 *@author：xiaoJianFeng
	 *@date:2017-03-19
	 *@return
	 *********************************/
	List<CreditMatching> selectCreditMatchPage(CreditMatching creditMatching, int begNum, int pageNum);
	 /**********************
	 *@describe:人行征信查询匹配设置条数
	  *@param creditMatching
	  *@return String
	  *@author：xiaoJianFeng
	  *@date:2017-03-19
	  ***********************/
	 String selectCreditMatchCount(CreditMatching creditMatching);
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
