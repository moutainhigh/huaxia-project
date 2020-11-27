document.write('<script type="text/javascript" src="../../topui/jquery/default/jquery.min.js"></script>');
function ishow(url,json) {
    if (url != null) {  
		var feature = 'top=0,left=0,titlebar=no,menubar=no,location=no,toolbar=no,z-look=yes,status=no,channelmode=yes,fullscreen=yes';
        try {  
			var width = 800;
			var height = 600;
            if (window.screen) {
            	width = screen.availWidth;
				height = screen.availHeight;
	            feature += ',width=' + width + ',height=' + height;
            }
            var win = window.open(url,"_blank","win-image",feature);
            if (win != null) {  
            	win.moveTo(0, 0);
            	win.resizeTo(width, height);
                win.focus();// 新页面获得焦点
            } else {
            	alert('未获取到影像信息!');
            }
        } catch (e) {
        	
        }  
    }  
};

function ishowImage(url,json) {
    if (url != null) {  
		var feature = 'top=0,left=0,titlebar=yes,menubar=yes,location=yes,toolbar=yes,z-look=yes,status=no,channelmode=yes,fullscreen=yes';
        try {  
			var width = 600;
			var height = 400;
            if (window.screen) {
            	width = screen.availWidth;
				height = screen.availHeight;
	            feature += ',width=' + width + ',height=' + height;
            }
            var win = window.open(url,"_blank","win-image",feature);
            if (win != null) {  
            	win.moveTo(0, 0);
            	win.resizeTo(width, height);
                win.focus();// 新页面获得焦点
            } else {
            	alert('未获取到影像信息!');
            }
        } catch (e) {
        }  
    }  
};
/*******************************
 *@describe:获取表单数据Json形式
 *@author:xiaoJianFeng
 *@date:2017-03-05
 ******************************/
function getFormDataJson(dataObject) {
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
/*******************************
 *@describe:弹框显示影像
 *@author:xiaoJianFeng
 *@date:2017-03-28
 ******************************/
function showView(){
	window.open("../win/image/sign.HTML","_blank");	
}
/*******************************
 *@describe:征信页面提交
 *@author:xiaoJianFeng
 *@date:2017-03-31
 ******************************/
function creditSubmit(){
	alert();
	var jsondata = {};
	jsondata.check_delStatus='0'; 
	jsondata.check_submitType='';
	jsondata.check_SubmitStatus='';
	jsondata.check_ydjFlag=ydjFlag;
	jsondata.check_currNode='03';
	jsondata.check_number=appId; 
	$cf.ajax({
		url : "/opas-naps/credit_submit.json",
		data : jsondata,
		onSuccess : function(data) {
			if (data.RSP_BODY.success) {
				alert("征信提交成功");
				window.close();
				window.opener.reload(); 
			} else {
				alert("征信提交失败");
				return;
			}
		}
	});
}
/*******************************
 *@describe:征信页面提报
 *@author:xiaoJianFeng
 *@date:2017-03-28
 ******************************/
function creditSubmitNewspaper(){
	var jsondata = {};
		jsondata.check_number=appId; 
		jsondata.check_submitType=submitType;
		jsondata.check_SubmitStatus="1"
		jsondata.check_ydjFlag=ydjFlag;
	$cf.ajax({
		url : "/opas-naps/creditSubmit_newspaper.json",
		data : jsondata,
		onSuccess : function(data) {
			if (data.RSP_BODY.success) {
				alert("提报成功");
				window.close();
				window.opener.reload(); 
			} else {
				alert("提报失败");
				return;
			}
		}
	});
}
//关闭窗体
function onConcle(obj) {
	var winObj = $("#" + obj);
	winObj.window("close");
}
