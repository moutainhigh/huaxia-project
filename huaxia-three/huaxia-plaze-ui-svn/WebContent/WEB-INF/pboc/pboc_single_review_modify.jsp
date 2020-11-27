<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>人行征信查询</title>
</head>
<body>
	<form id="frmList" enctype="multipart/form-data" method="post"
		name="frmList" action="submit/query.do">
		<table cellpadding="10"
			style="border-collapse: separate; border-spacing: 10px;">
			<tr>
				<td align="right">姓名:</td>
				<td><input class="easyui-textbox" id="name" type="text"
					name="name" data-options="required:true" style="width: 165px"
					value="${records.name}"></input></td>
			</tr>
			<tr>
				<td align="right">证件号码:</td>
				<td><input class="easyui-textbox" id="cert_no" type="text"
					name="certNo" data-options="required:true,validType:'length[1,20]'"
					style="width: 165px" value="${records.certNo}"></input></td>
			</tr>
			<tr>
				<td align="right">查询方式:</td>
				<td>
					<div id="queryTypeDiv"></div>
				</td>
			</tr>
			<tr>
				<td align="right">证件类别:</td>
				<td>
					<section>
						<label><select id="cert_type" name="certType"
							class="easyui-combobox" data-options="panelHeight:'auto'"
							style="width: 120px;" groupCode="CERT_TYPE"></select></label>
					</section>
				</td>
			</tr>
			<tr>
				<td align="right">查询原因:</td>
				<td><textarea style="width: 300px; height: 100px;"
						id="query_reason" name="queryReason" maxlength="200">${records.queryReason}</textarea></td>
			</tr>
			<tr>
				<td align="right">影像信息是否匹配：</td>
				<td><input type="radio" value="0" name="imageMatch" id="isMatch">匹配&nbsp;&nbsp;
					<input type="radio" value="1" name="imageMatch" id="isNotMatch">不匹配</td>
			</tr>
			<input type="hidden" id="labelMatch" value="${records.imageMatch}"></input>
			<tr>
				<td align="right"><a href="javascript:enclosure.click();"
					class="easyui-linkbutton" iconCls="icon-add">PDF附件上传</a></td>
				<td><input id="enclosure" type="file" onchange="fileChange()"
					style="display: none" multiple="true" name="enclosure" />&nbsp; <textarea
						style="width: 300px; height: 100px;" id="enclosureName"
						name="enclosureName" readonly></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="button_region"
						style="height: 50px; text-align: center; margin-top: 10px">
						<a href="javascript:showView();" class="easyui-linkbutton"
							style="text-align: center; display: inline-block; width: 93px; height: 30px">影像调阅</a>&nbsp;
						<input
							style="text-align: center; display: inline-block; width: 93px; height: 30px"
							class="easyui-linkbutton" type="button" value="提交复核"
							onclick="onSubmit()" />

					</div> <input type="hidden" name="trnId" value="${records.trnId}">

				</td>
			</tr>
		</table>
	</form>
	<div id="showView" class="easyui-window" title="申请件列表"
		style="width: 400px; height: 300px"
		data-options="iconCls:'icon-save',modal:true,closed:true"></div>
</body>
<script type="text/javascript">
$(function() {
	//查询类型
	var key = "QUERY_TYPE";
	var checkQueryType = ${queryType};
	var check = "";
	if(checkQueryType == "03")
		check = "1";
	else if(checkQueryType == "01")
		check = '2'
	else if(checkQueryType == "19")
		check = '3'
	$.ajax({
				url : "${basePath}/system/loadDictByKeyAuth.do",
				type : 'post',
				data : {
					'groupCode' : key
				},
				dataType : "json",
				success : function(response, status) {
					//渲染到Easyui的下拉列表里
					var jsonList = response.json;
					var auth = response.auth;
					$("#queryTypeDiv").html("");
					var first = false;
					for (var i = 0; i < jsonList.length; i++) {
						if(auth != null && auth.indexOf(jsonList[i].key) != -1){
							if(check == jsonList[i].key){
							$("#queryTypeDiv").append(jsonList[i].value+"<input name='queryType' type='radio' checked='checked' value='"+jsonList[i].key+"'> &nbsp;&nbsp;");
							}else{
								$("#queryTypeDiv").append(jsonList[i].value+"<input name='queryType' type='radio' value='"+jsonList[i].key+"'> &nbsp;&nbsp;");
							}
						}else{
							if(check == jsonList[i].key){
							$("#queryTypeDiv").append(jsonList[i].value+"<input name='queryType' type='radio' checked='checked' value='"+jsonList[i].key+"' disabled='disabled'>&nbsp;&nbsp;");
							}
							else{
								$("#queryTypeDiv").append(jsonList[i].value+"<input name='queryType' type='radio' value='"+jsonList[i].key+"' disabled='disabled'>&nbsp;&nbsp;");
							}
						}
					}
				},
				error : function(error, status) {
					alert('交易请求异常,状态码[' + status + ']');
				}
			});
	//证件类型
	var certType = $('#cert_type');
	var key = certType.attr('groupCode');
	$.ajax({
		url : "${basePath}/system/loadDictByKey.do",
		type : 'post',
		data : {
			'groupCode' : key
		},
		dataType : "json",
		success : function(response, status) {
			$('#cert_type').combobox(
					{
						data : response.json,
						valueField : 'key',
						textField : 'value',
						editable : false,
						onLoadSuccess : function() {
							$('#cert_type').combobox('setValue',
									response.json[1].key)
						}
					});
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
	//影像是否匹配
		var obj = document.getElementsByName("imageMatch");
		if( $("#labelMatch").val() == 0){	
			$("#isNotMatch").removeAttr("checked");
			$("#isMatch").attr("checked","checked");
		}else if($("#labelMatch").val() == 1){
			$("#isMatch").removeAttr("checked");
			$("#isNotMatch").attr("checked","checked");
		}
});

	//影像调阅
	function showViewCreditControl(appId) {
		window.open("../../image_new/show.html?appId=" + appId, "newWindow");
	}

	function showView() {
		if (($("#cert_no").val() == '')) {
			alert("证件号码不能为空");
			return;
		}
		$
				.ajax({
					url : "${basePath}/pboc/image/appid.do",
					type : 'post',
					data : {
						'certNo' : $("#cert_no").val(),
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
	//提交
	function onSubmit() {
		var obj = document.getElementsByName("imageMatch");
		if ($("#enclosure").val() == "" && !obj[0].checked ) {
			alert("请选择附件,或者匹配影像");
			return;
		}
		//校验输入框是否有警示的
		var isValid = $("frmList").form('validate');
		if (!isValid) {
			return;
		} else {
			$('#frmList').form('submit', {
				success : function(data) {
					if (data == "提交成功") {
						window.opener.refresh();
						window.close();
					} else {
						$.messager.alert("提示", data, "warning");
					}
				}

			})

		}
	}
	//点击附件浏览之后触发的事件
	function fileChange() {
		//拼接的文件名
		var name = "";
		//文件表单属性
		var file = document.getElementById("enclosure");
		//显示文件名的textarea
		var enclosureName = document.getElementById("enclosureName");
		//文件列表
		var files = file.files;
		var patn = /\.jpg$|\.jpeg$|\.pdf$/i;
		for (var i = 0; i < files.length; i++) {
			if (!patn.test(files[i].name)) {
				alert("请选择正确的pdf格式或者图片格式的文件！");
				file.value = "";
				enclosureName.value = "";
				return;
			}
			name += i + 1 + "." + files[i].name + "\n";
		}
		//显示所有文件名到textarea
		enclosureName.value = name;

	}
</script>
</html>