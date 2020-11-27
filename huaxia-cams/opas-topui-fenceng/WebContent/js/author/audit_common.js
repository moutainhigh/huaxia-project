var appId="";
var param;
/***********页面初始化***********/
$(function() {
	var url = location.href;
	appId = url.split("=")[1];
})
/***********保存按钮*************/
function save(){
	$cf.ajax({
		url : "/opas-naps/saveOrSubmitButtonCommon.json",
		data : {
			"appId" : appId,
			"button" : "save"
		},
		onSuccess : function(resData) {
			var msg = resData.RSP_BODY.msg;
			alert(msg);
		}
	});
}
/***********提交按钮***********/
function submit(){
	$cf.ajax({
		url : "/opas-naps/saveOrSubmitButtonCommon.json",
		data : {
			"appId" : appId,
			"button" : "submit"
		},
		onSuccess : function(resData) {
			var isSuccess = resData.RSP_BODY.isSuccess;
			if(isSuccess==true){
				//自动推送下一个任务
				submitPush();
			}else{
				var msg = resData.RSP_BODY.msg;
				alert(msg);
			}
		}
	});
}
/***********自动获得下一个推送的业务件***********/
function submitPush(){
	alert("submitPush");
	$cf.ajax({
		url : "/opas-naps/submitPush.json",
		onSuccess : function(resData) {
			var res = resData.RSP_BODY.applyAllot;
			appId = res.appId;
			var msg = resData.RSP_BODY.msg;
			alert(msg+",点击可获取下一个待办任务");
		}
	});
	$("iframe").each(function(){
		var iframe = $(this).prop("src");
		var resalert = $(this).prop("src",iframe+"?appId="+appId);
	});
}
/*//校验页面数据是否有修改
function checkPageData(){
	var collnput = document.getElementsByTagName("input");
	for(var i=0;i<collnput.length;i++){
		alert("new"+collnput[i].value);
		alert("old"+collnput[i].defaultValue);
		if(collnput[i].value!=collnput[i].defaultValue){
			alert("页面被修改过");
			//触发业务逻辑保存功能
			
		}else{
			alert("页面没有被修改过");
		}
	}
}*/
/***********选择员工树公方法***********/
function openUserTree(obj) {
	param = obj;
	$cf.ajax({
		url : "/opas-naps/queryThreeApRole.json",
		onSuccess : function(data) {
			var menuTree = $("#menuTree");
			var node = data.RSP_BODY.listUser;
			menuTree.tree({
				cascadeCheck: true,
				data : node,
				onClick : function(node) {
					
				}
			});
			showWin("userRelationMenu");
			selectOne(node);
		}
	})
}
function selectOne(node){
	$('#menuTree').tree({
        cascadeCheck: false,
        //onlyLeafCheck: true,
        checkbox: true,
        data: node,
        width: 160,
        height: 32,
        panelHeight: 400,
        onSelect: function (node) {
            var cknodes = $('#menuTree').tree("getChecked");
            for (var i = 0; i < cknodes.length; i++) {
                if (cknodes[i].id != node.id) {
                    $('#menuTree').tree("uncheck", cknodes[i].target);
                }
            }
            if (node.checked) {
                $('#menuTree').tree('uncheck', node.target);

            } else {
                $('#menuTree').tree('check', node.target);

            }

        },
        onLoadSuccess: function (node, data) {
            $(this).find('span.tree-checkbox').unbind().click(function () {
                $('#menuTree').tree('select', $(this).parent());
                return false;
            });
        }
    })
}
/**********关联管理员**********/
function zhuanShangJi() {
	$cf.ajax({
		url : "/opas-naps/queryThreeApRole.json",
		onSuccess : function(data) {
			var menuTree = $("#menuTree");
			var node = data.RSP_BODY.listUser;
			menuTree.tree({
				data : node,
				onClick : function(node) {

				}
			});
			showWin("userRelationMenu");
		}
	})
}
/**********保存用户授权**********/
function userSaveCommon() {
	var menuTree = $("#menuTree");
	var checkeNode = menuTree.tree('getChecked');
	var userMenus = [];
	for (var i = 0; i < checkeNode.length; i++) {
		var roleObj = {};
		roleObj.userId = checkeNode[i].id;
		userMenus[i] = roleObj;
	}
	var data = {
		"menuInfo" : userMenus,
		"appId":appId
	};
	var urlparam;
	if(param=='1'){
		urlparam = "/opas-naps/outHui.json";//退回（审批）
	}else if(param=='2'){
		urlparam = "/opas-naps/zhuanShangJi.json";//转上级
	}else if(param=='3'){
		urlparam = "/opas-naps/outJian.json";//退件（退回征信）
	}
	$cf.ajax({
		url : urlparam,
		data : data,
		onSuccess : function(data) {
			alert(data.RSP_BODY.msg);
			onConcle("userRelationMenu");
		}
	});
}
/***********申请表信息修改查看页面***********/
function openApplyDealView(){
	$cf.ajax({
		url : "/opas-naps/applyDealView.json",
		data : {
			"appId" : appId
		},
		onSuccess : function(dataRes) {
			var dataMap = {
				"rows" : dataRes.RSP_BODY.rows,
				"total" : dataRes.RSP_BODY.total
			};
			$("#tb_applyDealView").datagrid("loadData", dataMap);
			$("#tb_applyDealView").datagrid('getRows');
		}
	}); 
	showWin('applyDealView');
}
function formatKey(value) {
	if(value=='1'){
		return "是";
	}else if(value=='0'){
		return "否";
	}
}
function formatDate(value) {
	var str = getTaskTime(value);
	if(str.indexOf('1970')>=0){
		return '';
	}else{
		return str;
	}
}
//工具方法：格式化CST类型时间
function getTaskTime(strDate) {   
	if(null==strDate || ""==strDate){  
	    return "";  
	}  
	var dateStr=strDate.trim().split(" ");  
	var strGMT = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";  
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
	  
	return y+"-"+m+"-"+d+" "+h+":"+minute+":"+second;  
};
function showWin(obj) {
	var winObj = $("#"+obj);
	winObj.window({
		closed : false
	});
}
//关闭窗体
function onConcle(obj) {
	var winObj = $("#" + obj);
	winObj.window({
		closed : true
	});
}