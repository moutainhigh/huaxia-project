<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>人行征信查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<div>
		<form id="frmList" method="post">
			<table border="1" class="table-layout">
				<caption>
					<span style="float: left"> <a href="javascript:void();"
						onclick='batchAgree()'>批量通过</a> <a href="javascript:void();"
						onclick='batchReject()'>批量退回</a>
					</span>单条查询复核列表
				</caption>
				<tr>
					<th width="3%"><input type="checkbox" name="ALl_CHECKED"
						id="ALL_CHECKED" onclick="checkAll()"></th>
					<th width="5%">用户工号</th>
					<th width="5%">查询人姓名</th>
					<th width="10%">查询人ID</th>
					<th width="10%">提交复核申请时间</th>
					<th width="7%">被查询人姓名</th>
					<th width="10%">被查询人ID</th>
					<th width="8%">查询原因</th>
					<th width="5%">信息是否匹配</th>
					<th width="15%">操作</th>
				</tr>
				<c:forEach items="${records}" var="list" varStatus="sequence">
					<tr>
						<td><input type="checkbox" name="trnId" value="${list.trnId}"></td>
						<td>${list.staffId}</td>
						<td>${list.staffName}</td>
						<td>${list.staffCert}</td>
						<td>${list.crtTime}</td>
						<td>${list.name}</td>
						<td>${list.certNo}</td>
						<td>
						<c:if test="${list.queryType=='03'}">
					贷前查询
					</c:if> <c:if test="${list.queryType=='01'}">
					贷后查询
					</c:if> <c:if test="${list.queryType=='19'}">
					特约商户查询
					</c:if>
						</td>
						<td><c:if test="${list.imageMatch=='0'}">
					匹配
					</c:if> <c:if test="${list.imageMatch=='1'}">
					不匹配
					</c:if></td>
						<td><a href="javascript:void();"
							onclick='fnSubmitQuery("${list.trnId}")'>提交查询</a>&nbsp;|&nbsp; <a
							href="javascript:showView('${list.queryType}','${list.certNo}');">调阅影像</a>&nbsp;|&nbsp;
							<a href="javascript:show('${list.sourceId}') ">预览附件</a>&nbsp;|&nbsp;
							<a href="javascript:void();"
							onclick='fnSubmitReject("${list.trnId}")'>退回</a></td>
					</tr>
				</c:forEach>
			</table>
			<!-- 分页代码 & 开始 -->
			<c:if test="${page.totalCount > 0}">
				<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}"
					totalCount="${page.totalCount}" />
			</c:if>
			<input type="hidden" id="pageNo" name="pageNo" /> <input
				type="hidden" id="pageSize" name="pageSize" />
			<!-- 分页代码 & 结束 -->
		</form>

	</div>
	<!-- 附件预览 -->
	<div id="preview" class="easyui-window" title="附件列表"
		style="width: 400px; height: 300px"
		data-options="iconCls:'icon-save',modal:true,closed:true"></div>
	<div id="showView" class="easyui-window" title="申请件列表"
		style="width: 400px; height: 300px"
		data-options="iconCls:'icon-save',modal:true,closed:true"></div>
</body>
<script type="text/javascript">
	//全选
	function checkAll() {
		if ($('#ALL_CHECKED').prop('checked')) {
			$("input[name='trnId']").prop("checked", true);
		} else {
			$("input[name='trnId']").prop("checked", false);
		}
	}
	//批量提交
	function batchAgree() {
		$.messager.confirm('请确认', '是否提交查询?', function(r) {
			if (r) {
				var ids = "";
				$("input[name='trnId']").each(function() {
					if ($(this).prop("checked")) {
						ids += $(this).val() + ",";
					}
				})
				if (ids == "") {
					$.messager.alert("提示", "请选择一条记录", "warning");
					return;
				}
				ids = ids.substring(0, ids.length - 1);
				$.ajax({
					url : "${basePath}/pboc/single/agree/batch.do",
					type : 'post',
					data : {
						'ids' : ids,
					},
					dataType : "text",
					success : function(data) {
						if (data == "提交成功") {
							$.messager.alert("提示", data, "info", function() {
								refresh();
							});
						} else {
							$.messager.alert("提示", data, "warning");
						}

					},
					error : function(error, status) {
						$.messager.alert("提示", "请求失败", "warning");
					}
				})

			}
		})

	}
	//单个提交查询
	function fnSubmitQuery(id) {
		$.messager.confirm('请确认', '是否提交查询?', function(r) {
			if (r) {

				$.ajax({
					url : "${basePath}/pboc/single/agree/single.do",
					type : 'post',
					data : {
						'id' : id,
					},
					dataType : "text",
					success : function(data) {
						if (data == "提交成功") {
							$.messager.alert("提示", data, "info", function() {
								refresh();
							});
						} else {
							$.messager.alert("提示", data, "warning");
						}
					},
					error : function() {
						$.messager.alert("提示", "请求失败", "warning");
					}
				})
			}
		})
	}
	//批量退回
	function batchReject() {
		$.messager.confirm('请确认', '是否退回所选项?', function(r) {
			if (r) {
				var ids = "";
				$("input[name='trnId']").each(function() {
					if ($(this).prop("checked")) {
						ids += $(this).val() + ",";
					}
				})
				if (ids == "") {
					$.messager.alert("提示", "请选择一条记录", "warning");
					return;
				}
				ids = ids.substring(0, ids.length - 1);
				$.messager.prompt('退回', '请填写退回理由', function(data) {
					if (data) {
						$.ajax({
							url : "${basePath}/pboc/single/reject/batch.do",
							type : 'post',
							data : {
								'ids' : ids,
								'refuseReason' : data
							},
							dataType : "text",
							success : function(data) {
								$.messager.alert("提示", data, "info",
										function() {
											refresh();
										});
							},
							error : function(error, status) {
								$.messager.alert("提示", error.responseText,
										"warning");
							}
						})
					}
				})
			}
		})

	}
	//单个提交退回
	function fnSubmitReject(id) {
		$.messager.confirm('请确认', '是否退回所选项?', function(r) {
			if (r) {
				$.messager.prompt('退回', '请填写退回理由', function(data) {
					if (data) {
						$.ajax({
							url : "${basePath}/pboc/single/reject/single.do",
							type : 'post',
							data : {
								'id' : id,
								'refuseReason' : data
							},
							dataType : "text",
							success : function(data) {
								$.messager.alert("提示", data, "info",
										function() {
											refresh();
										});
							},
							error : function(error, status) {
								$.messager.alert("提示", error.responseText,
										"warning");
							}
						})
					}
				})
			}
		})

	}
	//操作成功刷新纪录
	function refresh() {
		var f = $('#frmList');
		f.action = "${basePath}/pboc/single/pagelist.do";
		f.submit();
	}

	//影像调阅
	function showViewCreditControl(appId) {
		window.open("../../../image_new/show.html?appId=" + appId, "newWindow");
	}

	//翻页显示
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/pboc/single/reviewQuery.do";
		f.submit();
	}
	//显示附件预览
	function show(sourceId) {
		$
				.ajax({
					url : "${basePath}/pboc/archive/filelist.do",
					type : 'post',
					data : {
						'sourceId' : sourceId
					},
					dataType : "json",
					success : function(data) {
						if (data.result == false) {
							$.messager.alert("提示", data.message, "warning");
						} else {
							var url = location.href.split("/huaxia-plaze-ui")[0];
							$("#preview").html("");
							for (var i = 0; i < data.message.length; i++) {
								if (data.message[i].sourceType == "pdf") {
									if(data.message[i].sourceName == null){
										alert("无附件");
									}else{
									$("#preview")
											.append(
													"<br><a href='"
															+ url
															+ "/huaxia-plaze-ui/generic/web/viewer.html?file2=${basePath}/pboc/archive/preview/pdf.do?id="
															+ data.message[i].uuid
															+ "'  target='_Blank' style='display:block'>"
															+data.message[i].sourceName
															+ "</a> <br>");
									$('#preview').window('open');
									}
								} else {
									if(data.message[i].sourceName == null){
										alert("无附件");
									}else{
									$("#preview")
											.append(
													"<br><a href='"
															+ url
															+ "${basePath}/pboc/archive/preview/image.do?id="
															+ data.message[i].uuid
															+ "'  target='_Blank' style='display:block'>"
															+ data.message[i].sourceName
															+ "</a> <br>");
									$('#preview').window('open');
									}
								}
							}
						}

					},
					error : function(error, status) {
						$.messager.alert("提示", "请求失败", "warning");
					}
				})
	}
	//调阅影像
	function showView(queryType, certNo) {

		$
				.ajax({
					url : "${basePath}/pboc/image/appid.do",
					type : 'post',
					data : {
						'certNo' : certNo,
					},
					dataType : "json",
					success : function(data) {
						if (data.msg == "success") {
							$("#showView").html("");
							var appids = data.data.appids;
							if (appids.length > 0) {
								var appid = "";
								for (var i = 0; i < appids.length; i++) {
									appid = appids[i];
									$("#showView")
											.append(
													"<br><a href='javascript:void(0)' onclick=\"showViewCreditControl('"
															+ appid
															+ "')\" target='_Blank' style='display:block'>"
															+ appid
															+ "</a> <br>");
								}
								$('#showView').window('open');
							} else {
								$.messager.alert("提示", "无可供调阅的影像");
							}
						} else {
							$.messager.alert("提示", "请求失败", "warning");
						}
					},
					error : function(error, status) {
						$.messager.alert("提示", "请求失败", "warning");
					}
				})
	}
</script>
</html>