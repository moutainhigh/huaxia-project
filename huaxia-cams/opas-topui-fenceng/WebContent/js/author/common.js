var common = new Object();
// 表单数据转json对象
common.formToJson = function(dataObject) {
	var jsonObject = '{';
	var k = 0;
	$.each(dataObject, function() {
		k++;
		var objName = this.name;
		var objValue = this.value;
		jsonObject += '"' + objName + '":';
		jsonObject += '"' + objValue + '"';
		if (k < dataObject.length) {
			jsonObject += ",";
		}
	});
	jsonObject += '}';
	return eval("(" + jsonObject + ")");
}
// 表格数据删除并刷新
function operatePromtRsh(data, tblObj) {
	if (data.isSuccess == "true") {
		$.messager.alert("操作提示", data.msg);
		if (tblObj) {
			tblObj.datagrid("reload");
		}
	} else {
		$.messager.alert("操作提示", data.msg);
	}
}
// 表格添加或修改并关闭弹出框
function operatePromtCls(data, winObj) {
	if (data.isSuccess == "true") {
		$.messager.alert("操作提示", data.msg);
		if (winObj) {
			winObj.window("close");
		}
	} else {
		$.messager.alert("操作提示", data.msg);
	}
}
// 控件授权
function author(testAuthor) {
	var component = $(".author");// 获取授权组件
	component.each(function() {
		var authorName = this.getAttribute("authorName");
		for (var i = 0; i < testAuthor.length; i++) {
			var obj = testAuthor[i];
			if (obj.authorName == authorName) {
				var compontObj = $(".author[authorName=" + authorName + "]");
				if (obj.authorType == "1") {// 组件只读
					compontObj.textbox({
						'readonly' : true
					});
					break;
				} else if (obj.authorType == "2") {// 组件不可用
					break;
				} else if (obj.authorType == "0") {// 组件隐藏
					compontObj.css("display", "none");
					break;
				} else {// 销毁
					$(this).remove();
					break;
				}
			}
		}
	});
}

/**
 * 弹出框关闭
 * @param winName 窗体id
 * @param formName 表单id
 */
common.closeWin = function(winName, formName) {
	var winObj = $("#" + winName + "");
	var formObj = $("#" + formName + "");
	formObj.form("clear");
	/*var row = $("#tblDictType").datagrid("getSelected");
	var id = row.dictId;
	var paramObj = document.getElementsByName("dictId");
	for (var i = 0; i < paramObj.length; i++) {
		paramObj[i].value = id;
	}*/
	winObj.window("close");
}

/**
 * 弹出框打开
 * @param winName 窗体名称
 */
common.openWin = function(winName) {
	winObj = $("#" + winName + "");
	winObj.window({
		closed : false
	});
}
/**
 * 业务字典大类.小类.表单提交（新增、修改）
 * @param tblName 表格id
 * @param formName 表单id
 * @param url
 */
common.formSub = function(tblName, winName, formName, url) {
	var formObj = $("#" + formName + "");
	var tblObj = $("#" + tblName + "");
	var jsondata = common.getFormData(formObj.serializeArray());
	if(formName=="dictTypeForm"){//大类新增
		if (jsondata.dictName == null||jsondata.dictName==""|| jsondata.dictName.trim().length <=0 ) {
			$.messager.alert("操作提示","失败:大类名称不能为空!", "error");
			return true;
		}
		if (jsondata.dictCode == null || jsondata.dictCode.trim().length <=0 ) {
			$.messager.alert("操作提示", "失败:大类编码不能为空!", "error");
			return true;
		}
	}
	if(formName=="dictTypeEditForm"){//大类修改
		if (jsondata.dictName == null || jsondata.dictName.trim().length <=0 ) {
			$.messager.alert("操作提示","失败:大类名称不能为空!", "error");
			return true;
		}
		if (jsondata.dictCode == null || jsondata.dictCode.trim().length <=0 ) {
			$.messager.alert("操作提示", "失败:大类编码不能为空!", "error");
			return true;
		}
	}
	if(formName=="dictForm"){//小类新增
		if (jsondata.dictDetailName == null || jsondata.dictDetailName.trim().length <=0 ) {
			$.messager.alert("操作提示","失败:小类名称不能为空!", "error");
			return true;
		}
		if (jsondata.dictDetailCode == null || jsondata.dictDetailCode.trim().length <=0 ) {
			$.messager.alert("操作提示", "失败:小类编码不能为空!", "error");
			return true;
		}
	}
	if(formName=="dictEditForm"){//小类修改
		if (jsondata.dictDetailName == null || jsondata.dictDetailName.trim().length <=0 ) {
			$.messager.alert("操作提示","失败:小类名称不能为空!", "error");
			return true;
		}
		if (jsondata.dictDetailCode == null || jsondata.dictDetailCode.trim().length <=0 ) {
			$.messager.alert("操作提示", "失败:小类编码不能为空!", "error");
			return true;
		}
	}
	$cf.ajax({
		type : "post",
		url : url,
		data : jsondata,
		onSuccess : function(data) {
			tblObj.datagrid("reload");
			if (data.RSP_BODY.isSuccess) {
				$.messager.alert("操作提示", "操作成功！");
			}else if(data.RSP_BODY.isCode){
				$.messager.alert("操作提示", "业务字典编码重复！");
				return;
			}else if(data.RSP_BODY.isName){
				$.messager.alert("操作提示", "业务字典名称重复！");
				return;
			}
			common.closeWin(winName, formName);
		}
	});
}

/**
 * 表格数据编辑
 * 
 * @param tblName 表格id
 * @param winName 窗体id
 * @param formName 表单id
 * 
 */
common.editOperate = function(tblName, winName, formName) {
	var winObj = $("#" + winName + "");
	var tblObj = $("#" + tblName + "");
	var formObj = $("#" + formName + "");
	var dataObj = tblObj.datagrid("getSelected");
	winObj.window({
		closed : false,
		onBeforeOpen : setData(formObj, dataObj)
	});
}
// 表单赋值
function setData(formObj, data) {
	formObj.form("load", data);
}

/**
 * 弹出框打开并给参数赋值
 * @param winName 窗体名称
 * @param tblName 表格名称
 * @param tarTblName 子表格名称
 * @param tarUrl 请求url
 * @param tarParam 隐藏域
 */
common.openWinTbl = function(winName, tblName, tarTblName, tarUrl, tarParam) {
	var tblObj = $("#" + tblName + "");
	var tblDict = $("#" + tarTblName + "");
	winObj = $("#" + winName + "");
	var row = tblObj.datagrid("getSelected");
	var id = row.dictId;
	var paramObj = document.getElementsByName(tarParam);
	for (var i = 0; i < paramObj.length; i++) {
		paramObj[i].value = id;
	}
	winObj.window({
		closed : false
	});
	var quryParam = {};
	quryParam[tarParam] = id;
	tblDict.datagrid({
		url : tarUrl,
		queryParams : quryParam
	});
}

/**
 * @param formName 表单名称
 * @param tblName 表格名称 表格条件查询
 */
common.searchTbl = function(formName, tblName) {
	var tblObj = $("#" + tblName + "");
	var formObj = $("#" + formName + "");
	var data = formObj.serializeArray();
	var jsonData = common.formToJson(data);
	tblObj.datagrid("reload", jsonData);
}

/**
 * @param formName 表单名称
 * @param tblName 表格名称 表格条件查询
 */
common.searchTblAndOpenWin = function(formName, tblName,winName) {
	if("tb_fileRoleConf"==tblName){
		$("#roleName_1").textbox('setValue','');
		$("#fileType_1").textbox('setValue','');
	}
	if("tb_personFilePowerConf"==tblName){
		$("#userName_2").textbox('setValue','');
		$("#fileType_2").textbox('setValue','');
	}
	var tblObj = $("#" + tblName + "");
	var formObj = $("#" + formName + "");
	var data = formObj.serializeArray();
	var jsonData = common.formToJson(data);
	tblObj.datagrid("reload", jsonData);
	
	winObj = $("#" + winName + "");
	winObj.window({
		closed : false
	});
} 
/**
 * 表格单条数据删除
 * @param tblName 表格名称
 * @param url
 */
common.deleteTbl = function(tblName, url) {
	var tblObj = $("#" + tblName + "");
	var dataObj = tblObj.datagrid("getSelected");
	if ($.messager.confirm("确认提示", "确定要删除吗?", function(r) {
		if (r) {
			$cf.ajax({
				type : "post",
				url : url,
				data : dataObj,
				onSuccess : function(data) {
					if (data.RSP_BODY.isSuccess) {
						$.messager.alert("操作提示", "删除成功！");
					}else {
						$.messager.alert("操作提示", "删除失败！");
						return;
					}
					tblObj.datagrid("reload");
				}
			});
		}
	}))
		;
}
//表单数据封装
common.getFormData = function getFormData(dataObject) {
	var jsonObject = '{';
	var k = 0;
	$.each(dataObject, function() {
		k++;
		var objName = this.name;
		var objValue = this.value;
		jsonObject += '"' + objName + '":';
		jsonObject += '"' + objValue + '"';
		if (k < dataObject.length) {
			jsonObject += ",";
		}
	});
	jsonObject += '}';
	return eval("(" + jsonObject + ")");
}
//获取字符串长度
function getByteLen(val) {
	var len = 0;
	if (val != null) {
		for (var i = 0; i < val.length; i++) {
			var a = val.charAt(i);
			if (a.match(/[^\x00-\xff]/ig) != null) {
				len += 2;
			} else {
				len += 1;
			}
		}
	}
	return len;
}
//监听窗口变化,调节datagrid高度  wdb  录入、征信队列列表
function initTableHeight(obj,val){
	if(obj!=null){
		$(window).on("resize",function(){
			var w_height; 
			if(val=='1'){//录入 征信 审批等操作队列列表
				w_height=screen.availHeight-400;
			}else if(val=='2'){//参数管理、风险名单库列表
				w_height=screen.availHeight-300;
			}else if(val=='3'){//征审审批已完成、已挂起
				w_height=screen.availHeight-450;
			}
			$(obj).datagrid("resize",{
				height:w_height
			})
		}).trigger("resize");
	}
}
//监听窗口变化,调节大页面高度  wdb  
function initYMHeight(obj){
	if(obj!=null){
		$(window).on("resize",function(){
			var w_height=screen.availHeight-120;
			$(".tab").css("height",w_height);
		}).trigger("resize");
	}
}