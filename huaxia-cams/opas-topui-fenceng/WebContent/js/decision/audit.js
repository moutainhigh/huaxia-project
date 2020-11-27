function opWind(appId) {
		var opts = $.extend({
			title : '查看备注',
			showType : 'show',
			showSpeed : 400,
			top : 100,
			modal : false,
			onOpen : function() {
				$.messager.progress('close');
			},
			onClose : function() {
				processInsNo = "";
				$(this).dialog('destroy');
				$(this).appendTo($("body"));
			}
		}, {});
		opts.content = '<iframe id="" src="../common/comment.html?appId='+appId+'" allowTransparency="true" scrolling="auto" width="560px" height="500px" frameBorder="0" name=""></iframe>';
		$('#dd').dialog(opts);
		var src = $('div>iframe').attr("src");
		$('#dd').dialog('refresh');
	}
function formatremark(val, row, index) {
	var stopFlag = row.stopFlag == null ? "" : row.stopFlag;
	var appId = row.appId;
	if (val == 0) {
		if (stopFlag == '0' || stopFlag == "") {// 既没有全流程备注，也没有质检
			return "";
		}
		if (stopFlag == '1') {// 没有全流程备注，但质检状态为提交
			return "<a href='javascript:selectQualityRecord(\"" + appId
					+ "\")'; >质检</a>";
		}
		if (stopFlag == '2') {
			return "<a href='javascript:breakQualityStatus(\"" + appId
					+ "\")'; >中断</a>";
		}
	} else {
		if (stopFlag == '0' || stopFlag == "") {// 有全流程备注，没有质检
			return "<a href='javascript:opWind(\""+appId+"\")'>查看</a>";
		}
		if (stopFlag == '1') {// 有全流程备注，但质检状态为提交
			return "<a href='javascript:opWind(\""+appId+"\")'>查看</a>&nbsp;<a href='javascript:selectQualityRecord(\""
					+ appId + "\")'; >质检</a>";
		}
		if (stopFlag == '2') {
			return "<a href='javascript:opWind(\""+appId+"\")'>查看</a>&nbsp;<a href='javascript:breakQualityStatus(\""
					+ appId + "\")'; >中断</a>";
		}
		// return "<a href=javascript:opWind();>查看</a>";
	}
}
// 点击质检查看质检结果
function selectQualityRecord(appId) {
	$cf.ajax({
		url : "/opas-naps/select_QualityChecking1.json",
		data : {
			'appId' : appId
		},
		onSuccess : function(data) {
			if (data.RSP_BODY.isSuccess) {
				$("#winCheckingSelect").window({
					closed : false,
					collapsible : false,
					minimizable : false,
					onBeforeOpen : setDataSelect(data.RSP_BODY.qualityChecking)
				});
				$("#crtUser1").textbox('setValue',data.RSP_BODY.userName);
			} else {
				$.messager
						.alert("操作提示", "操作异常，原因:后台运行异常" + data.exMsg, "error");
			}
		}
	});
}
// 点击中断查看质检结果
function breakQualityStatus(appId) {
	$cf.ajax({
		url : "/opas-naps/select_QualityChecking.json",
		data : {
			'appId' : appId
		},
		onSuccess : function(data) {
			if (data.RSP_BODY.isSuccess) {
				$("#winCheckingBreak").window({
					closed : false,
					collapsible : false,
					minimizable : false,
					onBeforeOpen : setDataBreak(data.RSP_BODY.qualityChecking)
				});
				$("#crtUser3").textbox('setValue',data.RSP_BODY.userName);
			} else {
				$.messager
						.alert("操作提示", "操作异常，原因:后台运行异常" + data.exMsg, "error");
			}
		}
	});
}
function setDataSelect(data) {
	$("#checkingFormSelect").form("load", data);
	$("#crtDate").datetimebox('setValue', formatDate1(data.crtDate));
}
function setDataBreak(data) {
	$("#checkingFormBreak").form("load", data);
	$("#crtDate1").datetimebox('setValue', formatDate1(data.crtDate));
}
// 日期格式转换
function formatDate1(value) {
	var str = getTaskTime(value);
	if (str.indexOf('1970') >= 0) {
		return '';
	} else {
		return str;
	}
}
// 工具方法：格式化CST类型时间
function getTaskTime(strDate) {
	if (null == strDate || "" == strDate) {
		return "";
	}
	var dateStr = strDate.trim().split(" ");
	var strGMT = dateStr[0] + " " + dateStr[1] + " " + dateStr[2] + " "
			+ dateStr[5] + " " + dateStr[3] + " GMT+0800";
	var date = new Date(Date.parse(strGMT));
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? ('0' + m) : m;
	var d = date.getDate();
	d = d < 10 ? ('0' + d) : d;
	var h = date.getHours();
	h = h < 10 ? ('0' + h) : h;
	var minute = date.getMinutes();
	minute = minute < 10 ? ('0' + minute) : minute;
	var second = date.getSeconds();
	second = second < 10 ? ('0' + second) : second;

	return y + "-" + m + "-" + d + " " + h + ":" + minute + ":" + second;
};