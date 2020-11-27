$cf.loadAllDict();//加载数据字典
function GetQueryString(name){
	 var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
	 var r = window.location.search.substr(1).match(reg);
	 if(r!=null) return unescape(r[2]);
	 return null;
}
//校验页面数据是否有修改
function checkPageData(){
	var collnput = document.getElementsByTagName("input");
	var change="0";
	for(var i=0;i<collnput.length;i++){
		/*alert("new"+collnput[i].value);
		alert("old"+collnput[i].defaultValue);*/
		if(collnput[i].value!=collnput[i].defaultValue){
			alert("页面被修改过");
			change = "1";
			break;
		}
	}
	return change;
}