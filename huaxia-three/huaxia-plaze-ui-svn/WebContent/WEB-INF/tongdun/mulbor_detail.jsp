<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>同盾多头借贷查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<div>
		<table border="1" class="table-layout">
			<caption>同盾多头借贷全流程报告</caption>
			<tr title="个人基本信息">
				<td>

					<table border="1" class="table-layout" title="个人基本信息">
						<caption>多头借贷个人信息报告</caption>
						<tr>
							<th width="33.33%">借款人姓名</th>
							<th width="33.33%">借款人手机</th>
							<th width="33.33%">借款人身份证</th>
						</tr>
						<tr>
							<td style="text-align: center">${name}</td>
							<td style="text-align: center">${mulBorInfo.mulBorBase.mobile}</td>
							<td style="text-align: center">${mulBorInfo.mulBorBase.certNo}</td>
						</tr>
					</table>

				</td>
			</tr>
			<tr title="指标服务">
				<td>
					<table border="1" class="table-layout" title="指标服务">
						<caption>指标服务</caption>
						<tr>
							<th width="80%">指标名称</th>
							<th width="20%">指标结果</th>
						</tr>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0a4c82dd50b1 != null}">
							<tr>
								<th>手机号P2P网贷_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0a4c82dd50b1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8c5c6b85ba31 != null}">
							<tr>
								<th>手机号一般消费分期平台_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8c5c6b85ba31}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a5cfd2cc0821 != null}">
							<tr>
								<th>手机号一般消费分期平台_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a5cfd2cc0821}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7dc74be258f1 != null}">
							<tr>
								<th>手机号互联网金融门户_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7dc74be258f1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e4bef80ab191 != null}">
							<tr>
								<th>手机号互联网金融门户_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e4bef80ab191}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5f1cff4a6081 != null}">
							<tr>
								<th>手机号保险_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5f1cff4a6081}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d3c5b644d261 != null}">
							<tr>
								<th>手机号保险_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d3c5b644d261}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ac586c138441 != null}">
							<tr>
								<th>手机号厂商汽车金融_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ac586c138441}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d964cad56851 != null}">
							<tr>
								<th>手机号厂商汽车金融_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d964cad56851}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d0d879f929b1 != null}">
							<tr>
								<th>手机号大型消费金融公司_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d0d879f929b1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d7b369958461 != null}">
							<tr>
								<th>手机号小额贷款公司_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d7b369958461}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7decf0c10f91 != null}">
							<tr>
								<th>手机号房地产金融_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7decf0c10f91}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cd6ddb5b0231 != null}">
							<tr>
								<th>手机号担保_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cd6ddb5b0231}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e400cb476291 != null}">
							<tr>
								<th>手机号理财机构_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e400cb476291}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5edebc93e271 != null}">
							<tr>
								<th>手机号理财机构_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5edebc93e271}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_48e8c2d36f21 != null}">
							<tr>
								<th>手机号租赁_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_48e8c2d36f21}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_06b34db165e1 != null}">
							<tr>
								<th>手机号租赁_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_06b34db165e1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_69a5fc3dbc11 != null}">
							<tr>
								<th>手机号融资租赁_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_69a5fc3dbc11}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8fb406000650 != null}">
							<tr>
								<th>身份证P2P网贷_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8fb406000650}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_531b058e93f0 != null}">
							<tr>
								<th>身份证一般消费分期平台_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_531b058e93f0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4c244935aef0 != null}">
							<tr>
								<th>身份证互联网金融门户_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4c244935aef0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4faf84e71160 != null}">
							<tr>
								<th>身份证互联网金融门户_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4faf84e71160}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cd01aa772bd0 != null}">
							<tr>
								<th>身份证保险_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cd01aa772bd0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4b549901aea0 != null}">
							<tr>
								<th>身份证保险_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4b549901aea0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_bc72c9ccec40 != null}">
							<tr>
								<th>身份证厂商汽车金融_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_bc72c9ccec40}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_39497d9839d0 != null}">
							<tr>
								<th>身份证大型消费金融公司_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_39497d9839d0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ff7f8fc1f120 != null}">
							<tr>
								<th>身份证小额贷款公司_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ff7f8fc1f120}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_efcaeb13df30 != null}">
							<tr>
								<th>身份证房地产金融_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_efcaeb13df30}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a549cd137ad0 != null}">
							<tr>
								<th>身份证担保_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a549cd137ad0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c3437b89c780 != null}">
							<tr>
								<th>身份证理财机构_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c3437b89c780}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4f66e1f858e0 != null}">
							<tr>
								<th>身份证理财机构_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4f66e1f858e0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_85f885cc4f40 != null}">
							<tr>
								<th>身份证租赁_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_85f885cc4f40}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7de9144eca20 != null}">
							<tr>
								<th>身份证租赁_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7de9144eca20}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e3509aaf13d0 != null}">
							<tr>
								<th>身份证融资租赁_跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e3509aaf13d0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_161760eea3f1 != null}">
							<tr>
								<th>手机号P2P网贷_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_161760eea3f1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_871b4791b741 != null}">
							<tr>
								<th>手机号一般消费分期平台_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_871b4791b741}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_64343ca2cfa1 != null}">
							<tr>
								<th>手机号一般消费分期平台_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_64343ca2cfa1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cd166e53c9b1 != null}">
							<tr>
								<th>手机号互联网金融门户_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cd166e53c9b1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2ebb86a755d1 != null}">
							<tr>
								<th>手机号互联网金融门户_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2ebb86a755d1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0fd434e40bb1 != null}">
							<tr>
								<th>手机号保险_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0fd434e40bb1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_61aee6654c01 != null}">
							<tr>
								<th>手机号保险_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_61aee6654c01}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d5d1955ff171 != null}">
							<tr>
								<th>手机号厂商汽车金融_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d5d1955ff171}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d3445eae2341 != null}">
							<tr>
								<th>手机号厂商汽车金融_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d3445eae2341}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1904f9b71b31 != null}">
							<tr>
								<th>手机号大型消费金融公司_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1904f9b71b31}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ffcfc2b798a1 != null}">
							<tr>
								<th>手机号小额贷款公司_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ffcfc2b798a1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8401d0095da1 != null}">
							<tr>
								<th>手机号房地产金融_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8401d0095da1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_82b0f20981a1 != null}">
							<tr>
								<th>手机号担保_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_82b0f20981a1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2ec00f08de71 != null}">
							<tr>
								<th>手机号理财机构_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2ec00f08de71}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ac9e681b02a1 != null}">
							<tr>
								<th>手机号理财机构_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ac9e681b02a1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_acf713df3fd1 != null}">
							<tr>
								<th>手机号租赁_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_acf713df3fd1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ac4f01b59331 != null}">
							<tr>
								<th>手机号租赁_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ac4f01b59331}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f7215fe4b561 != null}">
							<tr>
								<th>手机号融资租赁_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f7215fe4b561}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dda895875860 != null}">
							<tr>
								<th>身份证P2P网贷_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dda895875860}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c66690712910 != null}">
							<tr>
								<th>身份证一般消费分期平台_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c66690712910}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_345211438ff0 != null}">
							<tr>
								<th>身份证互联网金融门户_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_345211438ff0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_17e6407b61a0 != null}">
							<tr>
								<th>身份证互联网金融门户_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_17e6407b61a0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_65d9389b8af0 != null}">
							<tr>
								<th>身份证保险_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_65d9389b8af0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_aab6d8fb91b0 != null}">
							<tr>
								<th>身份证保险_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_aab6d8fb91b0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_985b5ba42e40 != null}">
							<tr>
								<th>身份证厂商汽车金融_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_985b5ba42e40}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cf44bdae4ba0 != null}">
							<tr>
								<th>身份证大型消费金融公司_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cf44bdae4ba0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_aa7668222f80 != null}">
							<tr>
								<th>身份证小额贷款公司_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_aa7668222f80}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c50ff997ac10 != null}">
							<tr>
								<th>身份证房地产金融_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c50ff997ac10}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_423d47136df0 != null}">
							<tr>
								<th>身份证担保_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_423d47136df0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c961bdc158c0 != null}">
							<tr>
								<th>身份证理财机构_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c961bdc158c0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c7a2789758c0 != null}">
							<tr>
								<th>身份证理财机构_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c7a2789758c0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_12b48e2ad0d0 != null}">
							<tr>
								<th>身份证租赁_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_12b48e2ad0d0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_3e3266dff9a0 != null}">
							<tr>
								<th>身份证租赁_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_3e3266dff9a0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0dddf6531740 != null}">
							<tr>
								<th>身份证融资租赁_跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0dddf6531740}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5e8b36093271 != null}">
							<tr>
								<th>手机号P2P网贷_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5e8b36093271}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0f17340bb3d1 != null}">
							<tr>
								<th>手机号一般消费分期平台_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0f17340bb3d1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f50d9a1534f1 != null}">
							<tr>
								<th>手机号一般消费分期平台_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f50d9a1534f1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8b47333b60f1 != null}">
							<tr>
								<th>手机号互联网金融门户_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8b47333b60f1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1063a6592601 != null}">
							<tr>
								<th>手机号互联网金融门户_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1063a6592601}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_91931fc33a61 != null}">
							<tr>
								<th>手机号保险_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_91931fc33a61}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_de24c02f8481 != null}">
							<tr>
								<th>手机号保险_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_de24c02f8481}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ca08351cc511 != null}">
							<tr>
								<th>手机号厂商汽车金融_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ca08351cc511}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e221f4f1e761 != null}">
							<tr>
								<th>手机号厂商汽车金融_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e221f4f1e761}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ae4b0d59bdf1 != null}">
							<tr>
								<th>手机号大型消费金融公司_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ae4b0d59bdf1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dd029e7c9b51 != null}">
							<tr>
								<th>手机号小额贷款公司_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dd029e7c9b51}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c8914cf84a01 != null}">
							<tr>
								<th>手机号理财机构_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c8914cf84a01}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b9094b7f6931 != null}">
							<tr>
								<th>手机号理财机构_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b9094b7f6931}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_516bb59c3741 != null}">
							<tr>
								<th>手机号租赁_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_516bb59c3741}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_52a92783d1a1 != null}">
							<tr>
								<th>手机号租赁_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_52a92783d1a1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_81a4cb14dcc0 != null}">
							<tr>
								<th>身份证P2P网贷_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_81a4cb14dcc0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0b4c56afc4d0 != null}">
							<tr>
								<th>身份证一般消费分期平台_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0b4c56afc4d0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5ef845f75c30 != null}">
							<tr>
								<th>身份证互联网金融门户_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5ef845f75c30}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d86123679f20 != null}">
							<tr>
								<th>身份证互联网金融门户_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d86123679f20}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_62085131a0e0 != null}">
							<tr>
								<th>身份证保险_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_62085131a0e0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_02f0ce3c49b0 != null}">
							<tr>
								<th>身份证保险_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_02f0ce3c49b0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b2adaba137f0 != null}">
							<tr>
								<th>身份证大型消费金融公司_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b2adaba137f0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_3f24d3d821f0 != null}">
							<tr>
								<th>身份证小额贷款公司_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_3f24d3d821f0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2184d215c1d0 != null}">
							<tr>
								<th>身份证理财机构_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2184d215c1d0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_174f3daa7090 != null}">
							<tr>
								<th>身份证理财机构_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_174f3daa7090}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_840bea513490 != null}">
							<tr>
								<th>身份证租赁_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_840bea513490}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_250d6de8f150 != null}">
							<tr>
								<th>身份证租赁_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_250d6de8f150}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_726655fe0e41 != null}">
							<tr>
								<th>手机号P2P网贷_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_726655fe0e41}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e8699ab64f11 != null}">
							<tr>
								<th>手机号P2P网贷_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e8699ab64f11}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1a3acd9d8321 != null}">
							<tr>
								<th>手机号一般消费分期平台_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1a3acd9d8321}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ae6341359221 != null}">
							<tr>
								<th>手机号一般消费分期平台_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ae6341359221}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fcef6d5316c1 != null}">
							<tr>
								<th>手机号互联网金融门户_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fcef6d5316c1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_31ebeae71ac1 != null}">
							<tr>
								<th>手机号互联网金融门户_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_31ebeae71ac1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_59ec64955f81 != null}">
							<tr>
								<th>手机号保险_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_59ec64955f81}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_36e823180421 != null}">
							<tr>
								<th>手机号保险_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_36e823180421}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_565c88cef451 != null}">
							<tr>
								<th>手机号厂商汽车金融_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_565c88cef451}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a2ca08d0ad11 != null}">
							<tr>
								<th>手机号厂商汽车金融_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a2ca08d0ad11}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_aae68f6ed511 != null}">
							<tr>
								<th>手机号大型消费金融公司_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_aae68f6ed511}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_206dcd7f7031 != null}">
							<tr>
								<th>手机号大型消费金融公司_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_206dcd7f7031}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e12e97972ec1 != null}">
							<tr>
								<th>手机号小额贷款公司_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e12e97972ec1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_920763cb9dd1 != null}">
							<tr>
								<th>手机号小额贷款公司_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_920763cb9dd1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f7d48e7a7fd1 != null}">
							<tr>
								<th>手机号房地产金融_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f7d48e7a7fd1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_eaeeb101a711 != null}">
							<tr>
								<th>手机号担保_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_eaeeb101a711}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2cc6fa284591 != null}">
							<tr>
								<th>手机号理财机构_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2cc6fa284591}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d6c111317e71 != null}">
							<tr>
								<th>手机号理财机构_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d6c111317e71}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_34310d985721 != null}">
							<tr>
								<th>手机号租赁_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_34310d985721}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e5c06632e511 != null}">
							<tr>
								<th>手机号租赁_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e5c06632e511}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8518b24e6d01 != null}">
							<tr>
								<th>手机号融资租赁_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8518b24e6d01}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_70f6c6b1b550 != null}">
							<tr>
								<th>身份证P2P网贷_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_70f6c6b1b550}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_afb711bb1e80 != null}">
							<tr>
								<th>身份证P2P网贷_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_afb711bb1e80}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b9a7f3b1ba00 != null}">
							<tr>
								<th>身份证一般消费分期平台_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b9a7f3b1ba00}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0577468331b0 != null}">
							<tr>
								<th>身份证一般消费分期平台_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0577468331b0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_815d5462bb70 != null}">
							<tr>
								<th>身份证互联网金融门户_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_815d5462bb70}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e3033d42ae10 != null}">
							<tr>
								<th>身份证互联网金融门户_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e3033d42ae10}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_07fe4e9b86c0 != null}">
							<tr>
								<th>身份证保险_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_07fe4e9b86c0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5439378f49e0 != null}">
							<tr>
								<th>身份证保险_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5439378f49e0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_96df341d7ac0 != null}">
							<tr>
								<th>身份证厂商汽车金融_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_96df341d7ac0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_69efe2ed0480 != null}">
							<tr>
								<th>身份证大型消费金融公司_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_69efe2ed0480}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dad774186b10 != null}">
							<tr>
								<th>身份证大型消费金融公司_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dad774186b10}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c7c3de9ad0a0 != null}">
							<tr>
								<th>身份证小额贷款公司_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c7c3de9ad0a0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a55921ccfc30 != null}">
							<tr>
								<th>身份证小额贷款公司_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a55921ccfc30}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_9d43aeb5d4c0 != null}">
							<tr>
								<th>身份证房地产金融_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_9d43aeb5d4c0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_633d1f0f3960 != null}">
							<tr>
								<th>身份证担保_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_633d1f0f3960}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cc06e3fc9d80 != null}">
							<tr>
								<th>身份证理财机构_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_cc06e3fc9d80}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1d0a2bdbc700 != null}">
							<tr>
								<th>身份证理财机构_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1d0a2bdbc700}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_37d86624c840 != null}">
							<tr>
								<th>身份证租赁_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_37d86624c840}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_58463101eec0 != null}">
							<tr>
								<th>身份证租赁_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_58463101eec0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2f22f31df6c0 != null}">
							<tr>
								<th>身份证融资租赁_跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2f22f31df6c0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0efc481c3e11 != null}">
							<tr>
								<th>手机号P2P网贷_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_0efc481c3e11}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b7cd97a99b91 != null}">
							<tr>
								<th>手机号大型消费金融公司_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b7cd97a99b91}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e55e23c04261 != null}">
							<tr>
								<th>手机号小额贷款公司_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e55e23c04261}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_381be99bdf81 != null}">
							<tr>
								<th>手机号房地产金融_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_381be99bdf81}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4e28898eb2f1 != null}">
							<tr>
								<th>手机号担保_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4e28898eb2f1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e9c90d961b81 != null}">
							<tr>
								<th>手机号融资租赁_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e9c90d961b81}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_394a66802d50 != null}">
							<tr>
								<th>身份证P2P网贷_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_394a66802d50}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ee676d6542c0 != null}">
							<tr>
								<th>身份证一般消费分期平台_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ee676d6542c0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4b95f0143420 != null}">
							<tr>
								<th>身份证厂商汽车金融_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4b95f0143420}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fa43ee1ea310 != null}">
							<tr>
								<th>身份证大型消费金融公司_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fa43ee1ea310}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fd983032da40 != null}">
							<tr>
								<th>身份证小额贷款公司_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fd983032da40}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_326d3de30a90 != null}">
							<tr>
								<th>身份证房地产金融_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_326d3de30a90}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a2cd59853fa0 != null}">
							<tr>
								<th>身份证担保_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_a2cd59853fa0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8ed58d092710 != null}">
							<tr>
								<th>身份证融资租赁_申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8ed58d092710}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_afcbc297fc41 != null}">
							<tr>
								<th>手机号P2P网贷_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_afcbc297fc41}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_25091a670151 != null}">
							<tr>
								<th>手机号大型消费金融公司_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_25091a670151}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7816c976d701 != null}">
							<tr>
								<th>手机号小额贷款公司_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7816c976d701}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1f3c7162fe71 != null}">
							<tr>
								<th>手机号房地产金融_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1f3c7162fe71}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_743e0c2cad61 != null}">
							<tr>
								<th>手机号担保_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_743e0c2cad61}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c7af4177d721 != null}">
							<tr>
								<th>手机号融资租赁_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c7af4177d721}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2ee5a02ffe70 != null}">
							<tr>
								<th>身份证P2P网贷_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2ee5a02ffe70}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fa8229c60960 != null}">
							<tr>
								<th>身份证一般消费分期平台_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fa8229c60960}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dcc8ee56fd90 != null}">
							<tr>
								<th>身份证厂商汽车金融_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dcc8ee56fd90}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_66c0c946b530 != null}">
							<tr>
								<th>身份证大型消费金融公司_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_66c0c946b530}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c09e2cdbb0b0 != null}">
							<tr>
								<th>身份证小额贷款公司_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c09e2cdbb0b0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_769da8285e50 != null}">
							<tr>
								<th>身份证房地产金融_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_769da8285e50}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5404bf1f19f0 != null}">
							<tr>
								<th>身份证担保_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_5404bf1f19f0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_27da67cb7130 != null}">
							<tr>
								<th>身份证融资租赁_申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_27da67cb7130}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7330c7055f51 != null}">
							<tr>
								<th>手机号P2P网贷_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7330c7055f51}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4b3e0de98881 != null}">
							<tr>
								<th>手机号大型消费金融公司_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4b3e0de98881}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_af70e5cc27f1 != null}">
							<tr>
								<th>手机号小额贷款公司_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_af70e5cc27f1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f4be4b1bb551 != null}">
							<tr>
								<th>手机号房地产金融_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f4be4b1bb551}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d932dae554d1 != null}">
							<tr>
								<th>手机号房地产金融_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d932dae554d1}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ff55f67ed591 != null}">
							<tr>
								<th>手机号担保_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_ff55f67ed591}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_66c5c4c0de31 != null}">
							<tr>
								<th>手机号担保_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_66c5c4c0de31}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_23b3de194d81 != null}">
							<tr>
								<th>手机号融资租赁_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_23b3de194d81}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_677cd4b9af21 != null}">
							<tr>
								<th>手机号融资租赁_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_677cd4b9af21}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1db660e01470 != null}">
							<tr>
								<th>身份证P2P网贷_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1db660e01470}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_9160b72273f0 != null}">
							<tr>
								<th>身份证一般消费分期平台_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_9160b72273f0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7e292bb6f130 != null}">
							<tr>
								<th>身份证厂商汽车金融_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_7e292bb6f130}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_bd3985043c30 != null}">
							<tr>
								<th>身份证厂商汽车金融_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_bd3985043c30}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fa268e1ce520 != null}">
							<tr>
								<th>身份证大型消费金融公司_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fa268e1ce520}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1e56549b4d40 != null}">
							<tr>
								<th>身份证小额贷款公司_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1e56549b4d40}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b69d97c53570 != null}">
							<tr>
								<th>身份证房地产金融_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b69d97c53570}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fb7e7da3d670 != null}">
							<tr>
								<th>身份证房地产金融_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_fb7e7da3d670}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f1fc1d33c0f0 != null}">
							<tr>
								<th>身份证担保_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f1fc1d33c0f0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_9f8618f2d0e0 != null}">
							<tr>
								<th>身份证担保_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_9f8618f2d0e0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_681952a89ff0 != null}">
							<tr>
								<th>身份证融资租赁_申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_681952a89ff0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8d1df12ce690 != null}">
							<tr>
								<th>身份证融资租赁_跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_8d1df12ce690}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1a3a932f9b01 != null}">
							<tr>
								<th>手机号房地产金融_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_1a3a932f9b01}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e03aba035c61 != null}">
							<tr>
								<th>手机号担保_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e03aba035c61}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_3dea83e7b431 != null}">
							<tr>
								<th>手机号融资租赁_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_3dea83e7b431}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d0d0d3a1afe0 != null}">
							<tr>
								<th>身份证厂商汽车金融_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d0d0d3a1afe0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_727c2b9eef50 != null}">
							<tr>
								<th>身份证房地产金融_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_727c2b9eef50}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_06bf8ab91910 != null}">
							<tr>
								<th>身份证担保_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_06bf8ab91910}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b563fa4a8aa0 != null}">
							<tr>
								<th>身份证融资租赁_申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_b563fa4a8aa0}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_395dfd22f26d != null}">
							<tr>
								<th>手机号_总申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_395dfd22f26d}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2dbdf137424f != null}">
							<tr>
								<th>手机号_总跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2dbdf137424f}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_000b8adf3484 != null}">
							<tr>
								<th>身份证_总申请次数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_000b8adf3484}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f5b35b30910c != null}">
							<tr>
								<th>身份证_总跨平台数_30天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f5b35b30910c}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4daf6e6d17d9 != null}">
							<tr>
								<th>手机号_总申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_4daf6e6d17d9}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_09e30f820b20 != null}">
							<tr>
								<th>手机号_总跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_09e30f820b20}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_80a75fbf9e92 != null}">
							<tr>
								<th>身份证_总申请次数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_80a75fbf9e92}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c82d025af778 != null}">
							<tr>
								<th>身份证_总跨平台数_90天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_c82d025af778}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2bf1553fd109 != null}">
							<tr>
								<th>手机号_总申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2bf1553fd109}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f0745989f331 != null}">
							<tr>
								<th>手机号_总跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_f0745989f331}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_10848867ef88 != null}">
							<tr>
								<th>身份证_总申请次数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_10848867ef88}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d9d16344e7b6 != null}">
							<tr>
								<th>身份证_总跨平台数_180天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_d9d16344e7b6}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_809320c9ea49 != null}">
							<tr>
								<th>手机号_总申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_809320c9ea49}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dd5b8c368641 != null}">
							<tr>
								<th>手机号_总跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_dd5b8c368641}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2410e2596228 != null}">
							<tr>
								<th>身份证_总申请次数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_2410e2596228}</td>
							</tr>
						</c:if>
						<c:if
							test="${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e57dd0908fe5 != null}">
							<tr>
								<th>身份证_总跨平台数_365天</th>
								<td style="text-align: center">${mulBorInfo.mulBorAntifraudIndex.ANTIFRAUD_e57dd0908fe5}</td>
							</tr>
						</c:if>

					</table>

				</td>
			</tr>
			<tr title="贷前反欺诈报告">
				<td>

					<table border="1" class="table-layout" title="贷前反欺诈报告">
						<caption>贷前反欺诈报告-自定义指标</caption>
						<tr>
							<th>ext_id_court_close_risk_1</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.courtCloseRisk1}</td>
							<th>ext_id_overdue_risk_2</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.overdueRisk2}</td>
						</tr>
						<tr>
							<th>ext_id_court_risk_3</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.courtRisk3}</td>
							<th>ext_id_overdue_repay_risk_4</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.overdueRepayRisk4}</td>
						</tr>
						<tr>
							<th>ext_id_company_taxowing_risk_5</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.companyTaxowingRisk5}</td>
							<th>ext_id_court_zhixing_risk_6</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.courtZhixingRisk6}</td>
						</tr>
						<tr>
							<th>ext_id_car_loan_risk_7</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.carLoanRisk7}</td>
							<th>ext_id_tax_owing_risk_8</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.taxOwingRisk8}</td>
						</tr>
						<tr>
							<th>ext_id_crime_risk_9</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.crimeRisk9}</td>
							<th>ext_id_student_loan_risk_10</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.studentLoanRisk10}</td>
						</tr>
						<tr>
							<th>ext_id_vehicle_lease_default_risk_11</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.vehicleLeaseDefaultRisk11}</td>
							<th>ext_id_company_paying_risk_12</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.companyPayingRisk12}</td>
						</tr>
						<tr>
							<th>ext_id_deliberate_violation_risk_13</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.deliberateViolationRisk13}</td>
							<th>ext_m_false_mobile_risk_14</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.falseMobileRisk14}</td>
						</tr>
						<tr>
							<th>ext_m_alimobile_risk_15</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.alimobileRisk15}</td>
							<th>ext_m_overdue_risk_16</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.overdueRisk16}</td>
						</tr>
						<tr>
							<th>ext_m_vehicle_lease_default_risk_17</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.vehicleLeaseDefaultRisk17}</td>
							<th>ext_m_company_paying_risk_18</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.companyPayingRisk18}</td>
						</tr>
						<tr>
							<th>ext_m_overdue_repay_risk_19</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.overdueRepayRisk19}</td>
							<th>ext_id_follow_high_20</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.followHigh20}</td>
						</tr>
						<tr>
							<th>ext_id_follow_medium_21</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.followMedium21}</td>
							<th>ext_id_follow_low_22</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.followLow22}</td>
						</tr>
						<tr>
							<th>ext_m_follow_high_23</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.followHigh23}</td>
							<th>ext_m_follow_medium_24</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.followMedium24}</td>
						</tr>
						<tr>
							<th>ext_m_follow_low_25</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.followLow25}</td>
							<th>ext_search_result</th>
							<td style="text-align: center">${mulBorInfo.mulBorBase.extSearchResult}</td>
						</tr>
					</table>

				</td>
			</tr>
			<tr title="风险名单">
				<td>

					<table border="1" class="table-layout" title="风险名单">
						<caption>风险名单</caption>
						<tr>
							<th width="30%">规则名称</th>
							<th width="30%">规则得分</th>
							<th width="40%">规则详情</th>
						</tr>
						<c:forEach items="${mulBorRiskList}" var="list"
							varStatus="sequence">
							<tr>

								<td style="text-align: center">${list.riskName}</td>
								<td style="text-align: center">${list.score}</td>
								<td style="text-align: left">规则描述 ${list.description} <br />
									风险类型： ${list.fraudTypeDisplayName} <br> 匹配字段：${ list.hitTypeDisplayName}
									<br> <%-- <c:when test="${list.value != null}">
							附件：法院详情
						</c:when> --%> <c:choose>
										<c:when test="${list.value != null}">
							附件：法院详情
							</c:when>

										<c:otherwise>

										</c:otherwise>
									</c:choose>
								</td>

							</tr>

						</c:forEach>
						<c:forEach items="${mulBorDiscreditCountMain}" var="list"
							varStatus="sequence">
							<tr>

								<td style="text-align: center">${list.riskName}</td>
								<td style="text-align: center">${list.score}</td>
								<td style="text-align: left">规则描述 ${list.description} <br />
									逾期次数： ${list.discreditTimes} <br> <c:forEach
										items="${mulBorInfo.mulBorDescreditCountList}" var="list2"
										varStatus="sequence">

										<c:choose>
											<c:when test="${list2.refUuid == list.pkUuid}">
										 逾期金额:${list2.overdueAmountRange} 逾期笔数: ${list2.overdueCount} 逾期天数: ${list2.overdueDayRange} 逾期入库时间:${list2.overdueTime}
										<br>
											</c:when>

											<c:otherwise>

											</c:otherwise>
										</c:choose>


									</c:forEach>
								</td>

							</tr>
						</c:forEach>
					</table>

				</td>
			</tr>
			<tr title="关注名单">
				<td>
					<table border="1" class="table-layout" title="关注名单">
						<caption>关注名单</caption>
						<tr>
							<th width="30%">规则名称</th>
							<th width="30%">规则得分</th>
							<th width="40%">规则详情</th>
						</tr>
						<c:forEach items="${mulBorAttentionList}" var="list"
							varStatus="sequence">
							<tr>
								<td style="text-align: center">${list.riskName}</td>
								<td style="text-align: center">${list.score}</td>
								<td style="text-align: left">规则描述 ${list.description} <br />
									风险类型： ${list.fraudTypeDisplayName} <br> 匹配字段：
									${list.hitTypeDisplayName}

								</td>

							</tr>
						</c:forEach>

					</table>
				</td>
			</tr>
			<tr title="法院详情">
				<td>
					<table border="1" class="table-layout" title="法院详情">
						<caption>法院详情</caption>

						<c:forEach items="${mulBorInfo.mulBorBlackListList}" var="list"
							varStatus="sequence">
							<c:choose>
								<c:when test="${list.value != null}">
									<table border="1" class="table-layout">
										<%-- <c:choose>
											<c:when test="${list.executedName != null}">
												<tr>
													<th>被执行人姓名</th>
													<td style="text-align: center">${list.executedName}</td>
												</tr>
											</c:when>
											<c:when test="${list.gender != null}">
												<tr>
													<th>性别</th>
													<td style="text-align: center">${list.gender}</td>
												</tr>
											</c:when>
											<c:when test="${list.age != null}">
												<tr>
													<th>年龄</th>
													<td style="text-align: center">${list.age}</td>
												</tr>
											</c:when>
											<c:when test="${list.fraudType != null}">
												<tr>
													<th>风险类型</th>
													<td style="text-align: center">${list.fraudType}</td>
												</tr>
											</c:when>
											<c:when test="${list.value != null}">
												<tr>
													<th>命中的属性值</th>
													<td style="text-align: center">${list.value}</td>
												</tr>
											</c:when>
											<c:when test="${list.evidenceCoutt != null}">
												<tr>
													<th>执行法院</th>
													<td style="text-align: center">${list.evidenceCoutt}</td>
												</tr>
											</c:when>
											<c:when test="${list.province != null}">
												<tr>
													<th>省份</th>
													<td style="text-align: center">${list.province}</td>
												</tr>
											</c:when>
											<c:when test="${list.executeCode != null}">
												<tr>
													<th>执行依据文号</th>
													<td style="text-align: center">${list.executeCode}</td>
												</tr>
											</c:when>
											<c:when test="${list.caseDate != null}">
												<tr>
													<th>立案时间</th>
													<td style="text-align: center">${list.caseDate}</td>
												</tr>
											</c:when>
											<c:when test="${list.caseCode != null}">
												<tr>
													<th>案号</th>
													<td style="text-align: center">${list.caseCode}</td>
												</tr>
											</c:when>
											<c:when test="${list.executeSubjec != null}">
												<tr>
													<th>执行标的</th>
													<td style="text-align: center">${list.executeSubjec}</td>
												</tr>
											</c:when>
											<c:when test="${list.executeStatus != null}">
												<tr>
													<th>执行状态</th>
													<td style="text-align: center">${list.executeStatus}</td>
												</tr>
											</c:when>
											<c:when test="${list.evidenceCoutt != null}">
												<tr>
													<th>做出依据执行法院</th>
													<td style="text-align: center">${list.evidenceCoutt}</td>
												</tr>
											</c:when>
											<c:when test="${list.termDuty != null}">
												<tr>
													<th>生效法律文书确定的任务</th>
													<td style="text-align: center">${list.termDuty}</td>
												</tr>
											</c:when>
											<c:when test="${list.carryOut != null}">
												<tr>
													<th>被执行人履行情况</th>
													<td style="text-align: center">${list.carryOut}</td>
												</tr>
											</c:when>
											<c:when test="${list.specificCircumstances != null}">
												<tr>
													<th>信贷逾期被执行人行为具体情形</th>
													<td style="text-align: center">${list.specificCircumstances}</td>
												</tr>
											</c:when>
											<c:when test="${list.evidenceTime != null}">
												<tr>
													<th>证据时间戳形式</th>
													<td style="text-align: center">${list.evidenceTime}</td>
												</tr>
											</c:when>
											<c:when test="${list.fraudTypeDisplayName != null}">
												<tr>
													<th>风险类型显示名</th>
													<td style="text-align: center">${list.fraudTypeDisplayName}</td>
												</tr>
											</c:when>
											<c:otherwise>

											</c:otherwise>
										</c:choose> --%>
										<tr>
											<th style="width: 50%"></th>
											<td style="width: 50%; text-align: center"></td>
										</tr>
										<c:if test="${list.executedName != null}">
											<tr>
												<th>被执行人姓名</th>
												<td style="text-align: center">${list.executedName}</td>
											</tr>
										</c:if>
										<c:if test="${list.gender != null}">
											<tr>
												<th>性别</th>
												<td style="text-align: center">${list.gender}</td>
											</tr>
										</c:if>
										<c:if test="${list.age != null}">
											<tr>
												<th>年龄</th>
												<td style="text-align: center">${list.age}</td>
											</tr>
										</c:if>
										<c:if test="${list.fraudType != null}">
											<tr>
												<th>风险类型</th>
												<td style="text-align: center">${list.fraudType}</td>
											</tr>
										</c:if>
										<c:if test="${list.value != null}">
											<tr>
												<th>命中的属性值</th>
												<td style="text-align: center">${list.value}</td>
											</tr>
										</c:if>
										<c:if test="${list.evidenceCoutt != null}">
											<tr>
												<th>执行法院</th>
												<td style="text-align: center">${list.evidenceCoutt}</td>
											</tr>
										</c:if>
										<c:if test="${list.province != null}">
											<tr>
												<th>省份</th>
												<td style="text-align: center">${list.province}</td>
											</tr>
										</c:if>
										<c:if test="${list.executeCode != null}">
											<tr>
												<th>执行依据文号</th>
												<td style="text-align: center">${list.executeCode}</td>
											</tr>
										</c:if>
										<c:if test="${list.caseDate != null}">
											<tr>
												<th>立案时间</th>
												<td style="text-align: center">${list.caseDate}</td>
											</tr>
										</c:if>
										<c:if test="${list.caseCode != null}">
											<tr>
												<th>案号</th>
												<td style="text-align: center">${list.caseCode}</td>
											</tr>
										</c:if>
										<c:if test="${list.executeSubjec != null}">
											<tr>
												<th>执行标的</th>
												<td style="text-align: center">${list.executeSubjec}</td>
											</tr>
										</c:if>
										<c:if test="${list.executeStatus != null}">
											<tr>
												<th>执行状态</th>
												<td style="text-align: center">${list.executeStatus}</td>
											</tr>
										</c:if>
										<c:if test="${list.evidenceCoutt != null}">
											<tr>
												<th>做出依据执行法院</th>
												<td style="text-align: center">${list.evidenceCoutt}</td>
											</tr>
										</c:if>
										<c:if test="${list.termDuty != null}">
											<tr>
												<th>生效法律文书确定的任务</th>
												<td style="text-align: center">${list.termDuty}</td>
											</tr>
										</c:if>
										<c:if test="${list.carryOut != null}">
											<tr>
												<th>被执行人履行情况</th>
												<td style="text-align: center">${list.carryOut}</td>
											</tr>
										</c:if>
										<c:if test="${list.specificCircumstances != null}">
											<tr>
												<th>信贷逾期被执行人行为具体情形</th>
												<td style="text-align: center">${list.specificCircumstances}</td>
											</tr>
										</c:if>
										<c:if test="${list.evidenceTime != null}">
											<tr>
												<th>证据时间戳形式</th>
												<td style="text-align: center">${list.evidenceTime}</td>
											</tr>
										</c:if>
										<c:if test="${list.fraudTypeDisplayName != null}">
											<tr>
												<th>风险类型显示名</th>
												<td style="text-align: center">${list.fraudTypeDisplayName}</td>
											</tr>
										</c:if>

									</table>
									<br>

								</c:when>

								<c:otherwise>

								</c:otherwise>
							</c:choose>

						</c:forEach>
				</td>
			</tr>
		</table>



	</div>
</body>
<script type="text/javascript">
	
</script>
</html>