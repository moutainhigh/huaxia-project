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
</head>
<body>
	<form id="frmList" enctype="multipart/form-data" method="post"
		name="frmList" action="review/submit.do">
		<table cellpadding="10"
			style="border-collapse: separate; border-spacing: 10px;">
			<tr>
				<td align="right">
				<a href="javascript:files.click();"
					class="easyui-linkbutton" iconCls="icon-add">批量导入数据文件</a>
				</td>
				<td>
				<input id="files" type="file" onchange="filesDataChange()"
					style="display: none" multiple="true" name="files" /> 
					<textarea
						style="width: 300px; height: 100px;" id="filesName"
						name="filesName" readonly>
					</textarea>
				</td>
			</tr>

			<tr>
				<td align="right">查询方式:</td>
				<td>
					<div id="queryTypeDiv"></div>
				</td>
			</tr>
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
					<div id="button_submit"
						style="height: 50px; text-align: center; margin-top: 10px">
						<!-- 隐藏域 旧的batch号 -->
						<input type="hidden" name="oldBatchNo" value="${batchId} ">
						<input
							style="text-align: center; display: inline-block; width: 93px; height: 30px"
							class="easyui-linkbutton" type="button" value="提交复核"
							onclick="onSubmit()" />
					</div>
				</td>
			</tr>
		</table>
	</form>
    <div id="modelLoading"
		style="position: fixed; z-index: 150; width: 100%; height: 100%; top: 0; left: 0; background: #fff; filter: alpha(opacity = 50); -moz-opacity: 0.5; -khtml-opacity: 0.5; opacity: 0.5; padding-top: 25%; display: none;">
		<div align='center'>
			<img src='/huaxia-plaze-ui/images/index/loading.gif' border='0' />
		</div>
	</div>
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
			check = '2';
		else if(checkQueryType == "19")
			check = '3';
			$.ajax({
				url : "${basePath}/system/loadDictByKeyAuth.do",
				type : 'post',
				data : {
					'groupCode' : key
				},
				dataType : "json",
				success : function(response, status) {
					//渲染到Easyui的下拉列表里
								var jsonList =response.json;
								var auth = response.auth;
								$("#queryTypeDiv").html("");
								for(var i = 0; i < jsonList.length; i++){
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
	})
	//提交表单
	function onSubmit() {
		
		if($("#files").val() ==""){
			alert("请选择数据文件");
			return;
		}
		if($("#enclosure").val() ==""){
			alert("请选择附件");
			return;
		}
		$("#modelLoading").show();
			$('#frmList').form('submit', {
				success : function(data) {
			   if(data.indexOf("提交成功") != -1){
				$("#modelLoading").hide();
				window.opener.refresh();
				window.close();
				}else{
					$("#modelLoading").hide();
					$.messager.alert("提示", data, "warning");
				}
				}
			})
		}
	/* 选择为数据文件后触发的事件 */
	function filesDataChange(){
		 //需要显示的文件名
		 var name="";
		 //获取导入数据文件属性
		 var files =document.getElementById("files");
		//显示文件名的textarea
		 var filesName =document.getElementById("filesName");
		 //文件列表
		 var fileList =files.files;
		 if(fileList.length >10){
			 alert("最多不能导入超过10个文件");
			 return;
		 }
		 for(var i=0;i<fileList.length; i++){
			 if(fileList[i].name.indexOf("txt")==-1 && fileList[i].name.indexOf("xls") == -1){
				 alert("请选择txt文件或者Excel文件");
				 files.value="";
				 filesName.value="";
				 return;
			 }
			 name+=i+1+"."+fileList[i].name+"\n";
		 }
		//显示文件名的textarea
		 filesName.value =name;
		
	}	
	/* 选择为附件后触发的事件 */
	function fileChange(){
		 var name="";
		 var enclosure =document.getElementById("enclosure");
		 var enclosureName =document.getElementById("enclosureName");
		 var files =  enclosure.files;
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
		 enclosureName.value =name;
	}
</script>
</html>