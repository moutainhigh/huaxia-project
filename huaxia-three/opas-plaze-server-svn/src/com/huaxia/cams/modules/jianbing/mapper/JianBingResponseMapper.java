package com.huaxia.cams.modules.jianbing.mapper;


import com.huaxia.cams.modules.jianbing.domain.JianBingComAnalData;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjBrief;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjBriefAnal;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjDetail;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjDetailAnal;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjLoanBrief;
import com.huaxia.cams.modules.jianbing.domain.JianBingGjjLoanDetail;


public interface JianBingResponseMapper {

	int insertBrief(JianBingGjjBrief brief);

	int insertBriefAnal(JianBingGjjBriefAnal briefAnal);

	void insertGjjDetail(JianBingGjjDetail detail);

	void insertDetailAnal(JianBingGjjDetailAnal detailAnal);

	void insertGjjLoan(JianBingGjjLoanBrief loan);

	void insertGjjLoanDetail(JianBingGjjLoanDetail detailLoan);

	void insertComAnal(JianBingComAnalData company);
}
