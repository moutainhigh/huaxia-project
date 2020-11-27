function createFrame(url) {
	
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function addTab(subtitle, url, queueNo, tabsName, icon) {
	var tur=url+"?t="+new Date().getTime()+"&queueNo="+queueNo;
	var jq = top.jQuery;
	if (!jq("#"+tabsName).tabs('exists', subtitle)) {
		jq("#"+tabsName).tabs('add', {
			title : subtitle,
			content : createFrame(tur),
			closable : true,
			icon : icon
		});
	} else {
		jq("#"+tabsName).tabs('select', subtitle);
	}
	//添加close事件
	tabClose(tabsName);
}

function tabClose(tabsName) {
	/* 双击关闭TAB选项卡 */
	var jq = top.jQuery;
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		jq("#"+tabsName).tabs('close', subtitle);
	});
}