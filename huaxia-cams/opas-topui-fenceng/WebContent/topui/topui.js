__CreateJSPath = function(js) {
	var scripts = document.getElementsByTagName("script");
	var path = "";
	for ( var i = 0, l = scripts.length; i < l; i++) {
		var src = scripts[i].src;
		if (src.indexOf(js) != -1) {
			var ss = src.split(js);
			path = ss[0];
			break;
		}
	}
	var href = location.href;
	href = href.split("#")[0];
	href = href.split("?")[0];
	var ss = href.split("/");
	ss.length = ss.length - 1;
	href = ss.join("/");
	if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
		path = href + "/" + path;
	}
	return path;
}
//bootPath
var bootPATH = __CreateJSPath("topui.js");
//业务字典缓存key
var SYS_CACHE_DICT = "_btp_sys_cache_dict";
var SYS_CACHE_DICT_KEY = "_dictStore";
//debugger,此变量用来区别ajax请求是否弹出alert来提示交互错误
topui_debugger = false;
//window['nui_model']=window['nui_model']||'min';
document.write('<link href="'+ bootPATH+ 'easyui/default/theme/default/easyui.16.3.6.css" rel="stylesheet" type="text/css" />');
document.write('<link href="'+ bootPATH+ 'easyui/default/theme/color.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH+ 'easyui/default/theme/icon.css" rel="stylesheet" type="text/css" />');
document.write('<script src="' + bootPATH+ 'jquery/default/jquery.min.js" type="text/javascript"></sc'+ 'ript>');
document.write('<script src="' + bootPATH+ 'easyui/default/jquery.easyui.min.js" type="text/javascript"></sc'+ 'ript>');
document.write('<script src="'+ bootPATH+ 'easyui/default/locale/easyui-lang-zh_CN.js" type="text/javascript" ></sc'+ 'ript>');
document.write('<script src="' + bootPATH+ 'easyui/default/jquery.query.js" type="text/javascript" ></sc'+ 'ript>');
document.write('<script src="' + bootPATH+ 'framework/framework.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH+ 'easyui/default/easyuiExt.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH+ 'easyui/default/constant.js" type="text/javascript"></sc' + 'ript>');
////////////////////////////////////////////////////////////////////////////////////////
function getCookie(sName) {
	var aCookie = document.cookie.split("; ");
	var lastMatch = null;
	for ( var i = 0; i < aCookie.length; i++) {
		var aCrumb = aCookie[i].split("=");
		if (sName == aCrumb[0]) {
			lastMatch = aCrumb;
		}
	}
	if (lastMatch) {
		var v = lastMatch[1];
		if (v === undefined)
			return v;
		return unescape(v);
	}
	return null;
}
function getBasePath() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName + '/');
}
var basePath=getBasePath();
//为单选按钮赋值
function setRadioValue(fieldValue,radioName){
	var radios=document.getElementsByName(radioName);
	for ( var i = 0,len = radios.length; i < len; i++) {
		if (fieldValue == radios[i].value) {
			radios[i].setAttribute('checked', 'checked');
		}
	}
}
//将单选按钮清空
function clearRadioValue(radioName){
	var radios=document.getElementsByName(radioName);
	for ( var i = 0,len = radios.length; i < len; i++) {
		if (radios[i].checked) {
			radios[i].checked=false;
		}
	}
}
//表单name属性转换为formData对象
function getFormData(form){
	var arr=$(form).serializeArray();
	var formData ={};
	for(var i=0;i<arr.length;i++){
		formData[arr[i].name]=arr[i].value;
	}
	return formData;
}

function lengthLimit(elem, max){ 
	//var elem = elem.id;
	//var elem = document.getElementById(elem); 
	var max = max || 50;// 最大限度字符，汉字按两个字符计算 
	
	function getTextLength(str){// 获取字符串的长度 一个汉字为2个字符 
		return str.replace(/[^\x00-\xff]/g,"xx").length; 
	}; 
	
	// 监听text的内容变化 
	if(/msie (\d+\.\d)/i.test(navigator.userAgent) == true) {// 区分IE 
		elem.onpropertychange = textChange; 
	}else{ 
		elem.addEventListener("input", textChange, false); 
	} 

	function textChange(){// 内容变化时的处理 
		var text = elem.value; 
		var count = getTextLength(text); 
		if(count > max){// 文字超出截断 
		for(var i=0; i<text.length; i++){ 
			if(getTextLength(text.substr(0, i)) >= max){ 
				elem.value = text.substr(0, i); 
				break; 
			}; 
		} 
	  }; 
	}; 
};
//禁用右键
document.oncontextmenu=function(){
	return false;
};
document.onkeydown=function(e){
	var blnEevntCancel = false;
	//禁用回退键（文本框回退时不禁用）
    if (window.event.keyCode == 8) {
        if (window.event.srcElement.tagName.toUpperCase() == "TEXTAREA") {
            if (window.event.srcElement.readOnly == true) {
                blnEevntCancel = true;
            }else if(window.event.srcElement.value.length==0){
            	blnEevntCancel = true;
            }
        }
        else if (window.event.srcElement.tagName.toUpperCase() == "INPUT" && (event.srcElement.type.toUpperCase() == 'TEXT' || event.srcElement.type.toUpperCase() == 'PASSWORD')) {
            if (window.event.srcElement.readOnly == true) {
                blnEevntCancel = true;
            }else if(window.event.srcElement.value.length==0){
            	blnEevntCancel = true;
            }
        } else {
            blnEevntCancel = true;
        }
    }
   	if (blnEevntCancel == true) {
        window.event.cancelBubble = true;
        window.event.returnValue = false;
        return false;
    }
   	//禁用F5
	var ev = window.event || e;
	var code = ev.keyCode || ev.which;
	if (code == 116) {
		ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
		cancelBubble = true;
		return false;
	}
	if (event.altKey && event.keyCode == 49) {// 按 Alt + 1
		parent.imgTab();
	}
	if (event.altKey && event.keyCode == 50) {// 按 Alt + 2
		parent.creTab();
	}
	if (event.altKey && event.keyCode == 75) {// 按 Alt + K
		parent.openKeyWord();
	}
	if (event.altKey && event.keyCode == 67) {// 按 Alt + C
		parent.sub();
	}
	if (event.altKey && event.keyCode == 83) {// 按 Alt + S
		parent.save();
	}
	if (event.altKey && event.keyCode == 53) {// 按 Alt + 5
		parent.basePhoneTab();
	}
	if (event.altKey && event.keyCode == 54) {// 按 Alt + 6
		parent.approvalCall1Tab();
	}
	if (event.altKey && event.keyCode == 51) {// 按 Alt + 3
		parent.approvalCall2Tab();
	}
};
