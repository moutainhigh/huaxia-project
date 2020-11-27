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
	<form id="frmList" method="post">
		<table border="1" class="table-layout">
			<caption>批量查询复核列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="12%">批次号</th>
				<th width="8%">导入文件列表</th>
				<th width="15%">导入时间</th>
				<th width="8%">导入总条数</th>
				<th width="10%">操作人员工号</th>
				<th width="10%">操作人姓名</th>
				<th width="12%">操作人ID</th>
				<th width="15%">操作</th>
			</tr>
			<c:forEach items="${records}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td>${list.batchId}</td>
					<td><a href="javascript:void();"
						onclick='showDetail("${list.fileName}")'>文件列表</a></td>
					<td>${list.crtTime}</td>
					<td>${list.totalRecord}</td>
					<td>${list.staffId}</td>
					<td>${list.staffName}</td>
					<td>${list.certNo}</td>
					<td><a href="javascript:void();"
						onclick='fnSubmitQuery("${list.batchId}")'>提交查询</a>&nbsp;|&nbsp; <a
						href="javascript:show('${list.batchId}') ">预览附件</a>&nbsp;|&nbsp; <a
						href="javascript:void();"
						onclick='fnSubmitReject("${list.batchId}")'>退回</a></td>
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
	<!-- 附件预览 -->
	<div id="preview" class="easyui-window" title="附件列表"
		style="width: 400px; height: 300px"
		data-options="iconCls:'icon-save',modal:true,closed:true"></div>
	<div id="previewFile" class="easyui-window" title="文件列表"
		style="width: 400px; height: 300px"
		data-options="iconCls:'icon-save',modal:true,closed:true"></div>
</body>
<script type="text/javascript">
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		/* f.action = "${basePath}/pboc/batch/batchResult.do"; */
		f.submit();
	}

	function fileDetail(fileId) {
		window
				.open(
						"${basePath}/pboc/batch/getFileDetail.do?fileId="
								+ fileId,
						'_blank',
						'toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
	}

	function fnSubmitQuery(batchId) {
		$.messager.confirm('请确认', '是否提交查询?', function(r) {
			if (r) {

				$.ajax({
					url : "${basePath}/pboc/batch/submit/query.do",
					type : 'post',
					data : {
						'batchId' : batchId,
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

	function fnSubmitReject(batchId) {
		$.messager.confirm('请确认', '是否退回所选项?', function(r) {
			if (r) {
				$.messager.prompt('退回', '请填写退回理由', function(data) {
					if (data) {
						$.ajax({
							url : "${basePath}/pboc/batch/reject/single.do",
							type : 'post',
							data : {
								'batchId' : batchId,
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
	
	// 操作成功刷新纪录
	function refresh() {
		var f = $('#frmList');
		f.action = "${basePath}/pboc/batch/review/pagelist.do";
		f.submit();
	}
	// 显示附件预览
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
							var filePath;
							$("#preview").html("");
							for (var i = 0; i < data.message.length; i++) {
								if (data.message[i].sourceType == "pdf") {
									var url = location.href
									.split("/huaxia-plaze-ui")[0];
									$("#preview")
											.append(
													"<br><a href='"
															+ url
															+ "/huaxia-plaze-ui/generic/web/viewer.html?file2=${basePath}/pboc/archive/preview/pdf.do?id="
															+ data.message[i].uuid
															+ "'  target='_Blank' style='display:block'>"
															+ data.message[i].sourceName
															+ "</a> <br>");

								} else {
									var url = location.href
									.split("/huaxia-plaze-ui")[0];
									$("#preview")
											.append(
													"<br><a href='"
															+ url
															+ "${basePath}/pboc/archive/preview/image.do?id="
															+ data.message[i].uuid
															+ "'  target='_Blank' style='display:block'>"
															+ data.message[i].sourceName
															+ "</a> <br>");

								}
							}
							$('#preview').window('open');

						}

					},
					error : function(error, status) {
						$.messager.alert("提示", "请求失败", "warning");
					}
				})
	}
	// 显示附件预览
	function showDetail(files) {
		var fileList = files.split(",")
		$("#previewFile").html("");
		for (var i = 0; i < fileList.length; i++) {
			$("#previewFile").append("<br>" + fileList[i] + "<br>");
		}
		$('#previewFile').window('open');
	}
</script>
</html>