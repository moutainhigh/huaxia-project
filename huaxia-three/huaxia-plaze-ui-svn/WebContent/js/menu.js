var _menutree = $("#contents");

/** 初始化菜单树 */
function fnLoadMenuTree(menus) {
	var items = "<ul id=\"menu_nav\">";
	for (var i = 0; i < menus.length; i++) {
		var data = menus[i];
		var children = data.children;
		var item = buildlines(data, children);
		items = items + item;
	}
	items = items + "</ul>";
	$('#menu_area').html(items);

	// 初始化菜单树样式
	menuStyle();

	// 显示默认TAB页
	fnShowDefaultTab();
}
// 导航列表
function buildlines(data, children) {
	var a = "";
	var begli = "<li>";
	var endli = "</li>";
	var begh4 = "<h4 class=\"menu-item\">";
	var span = "<span class=\"collapsible\"></span>" + data.name;
	var endh4 = "</h4>";
	var begdiv = "<div class=\"menu-list none\">";
	var enddiv = "</div>";
	if (children) {
		var basePath = $('#basePath').val();
		for (var j = 0; j < children.length; j++) {
			var o = children[j];
			a += "<a id='" + o.id + "' name='" + o.name
					+ "' href=\"javascript:addTab('" + o.name + "', '" + basePath
					+ (o.link == undefined ? '/404.html' : o.link) + "','"
					+ o.id + "')\">" + o.name + "</a>";
		}
	}
	return begli + begh4 + span + endh4 + begdiv + a + enddiv + endli;
}
// 添加tab页
function addTab(name, url, id) {
	var tab = fnGetTabById(id);
	if (tab) {
		tab.panel('open');
		fnRefreshTab(tab);
	} else {
		_menutree.tabs('add', {
			id : id,
			title : name,
			selected : true,
			closable : true,
			content : '<iframe name="'
					+ name
					+ '" src="' +url+ '" width="100%" height="682" frameborder="0" scrolling="auto" style="height:682px;"></iframe>'
		});
	}
};

// tab与菜单联动
function tabCase(obj) {
	var $div = obj.siblings(".menu-list");
	if ($div.is(":hidden")) {
		$("#menu_nav li").find(".menu-list").slideUp(600);
		$("#menu_nav li").removeClass("menu-active");
		obj.parent().addClass("menu-active");
		$div.slideDown(600);
	}
}
// 菜单click事件
function menuHandler(item) {
	var tabs = _menutree.tabs("tabs");
	var data = $.extend([], tabs);
	var index = $("#tabsMenu").data("tabIndex");
	if (item.id == 0 && index != 0) {
		_menutree.tabs("close", index);
	} else if (item.id == 1) {
		for (var i = data.length; i >= 0; i--) {
			if (i == index) {
				continue;
			}
			if (i != 0) {
				_menutree.tabs("close", i);
			}
		}
		_menutree.tabs("select", 1);
	} else if (item.id == 2) {
		for (var i = data.length; i >= 0; i--) {
			if (i != 0) {
				_menutree.tabs("close", i);
			}
		}
	} else if (item.id == 3) {
		// var currTab = _menutree.tabs("getSelected");//获取当前选中的tab
		// var url =$(currTab.panel('options').content).attr('src'); //该tab
		// iframe的src属性
		// _menutree.tabs('update',{
		// tab:currTab,
		// options:{
		// href: url
		// }
		// });
	}
}

// 填充数据
function fillData(json) {
	fnLoadMenuTree(json.USERMENU);
	relationInfo.menuInfo = json.USERMENU;
	relationInfo.userInfo = json.userInfo;
	relationInfo.roleInfo = json.roleInfo;
	relationInfo.loginInfo.token = json.token;
	relationInfo.loginInfo.tokenDate = new Date();
}

// -- 首页菜单事件及样式设置 --
// 菜单排版
function menuStyle() {
	var $obj = $("#menu_nav");
	$obj.find("h4").hover(function() {
		$(this).addClass("hover");
		/*
		 * $("#threeMenu").css({ 'left' : '242px', 'top' : top + 'px', 'display' :
		 * 'none', 'border-top' : '1px solid #95b8e7' });
		 */
	}, function() {
		$(this).removeClass("hover");
	});

	$obj.find("h4").click(function() {
		var $div = $(this).siblings(".menu-list");
		if ($(this).parent().hasClass("active")) {
			$div.slideUp(600);
			$(this).parent().removeClass("active");
		}
		if ($div.is(":hidden")) {
			$("#menu_area ul li").find(".menu-list").slideUp(600); // 滑动隐藏
			$("#menu_area ul li").removeClass("active");
			$(this).parent().addClass("active");
			$div.slideDown(600); // 滑动展开
		}
	});

	// 菜单锁定
	$obj.find("a").click(function() {
		$obj.find("a").each(function(i, e) {
			e.className = '';
		});

		if ($(this).hasClass("menu-active")) {
			$(this).removeClass("menu-active");
		} else {
			$(this).addClass("menu-active");
		}
	});
}

// 过虑菜单对象
function filterMenu(menus, menuName) {
	var istrue = false;
	for (var i = 0; i < menus.length; i++) {
		menuObj = menus[i];
		if (menuObj.children) {
			for (var j = 0; j < menuObj.children.length; j++) {
				var menu = menuObj.children[j];
				if (menu.name == menuName) {
					istrue = true;
					break;
				}
			}
		}
		if (istrue) {
			break;
		}
	}
	return menus[i];
}

// 显示三级菜单
function loadThreeMenu(obj, menuName) {
	var menulist = "";
	var menuObj;
	if (obj.children) {
		for (var i = 0; i < obj.children.length; i++) {
			menuObj = obj.children[i];
			if (menuObj.name == menuName && menuObj.children) {
				for (var j = 0; j < menuObj.children.length; j++) {
					var menu = menuObj.children[j];
					var url = menu.link;
					if (!url) {
						url = "javascript:void(0);"
					}
					menulist += "<a href='"
							+ url
							+ "' class=\"three-a\" style=\"border-top:1px solid #95b8e7;\">"
							+ menu.name + "</a>";
				}
			}
		}
	}
	return menulist;
}

// -- 首页统计信息区域 --

/** 首页 */
function fnShowDefaultTab() {
	_menutree.tabs('add', {
		title : "首页",
		selected : true,
		closable : false,
		content : '<iframe name="首页" src="" width="100%" height="682" frameborder="0" scrolling="auto"></iframe>'
	});
}

// 关闭tabsjavascript:void(0)
function closeTab(tabName) {
	var obj = getTabProperty();
	_menutree.tabs('close', obj.title);
}

// 获取当前选中的tab页面相关属性
function getTabProperty() {
	return _menutree.tabs("getSelected").panel("options");
}

/**
 * 获取指定编号的TAB
 * 
 * @param id
 *            TAB编号
 */
function fnGetTabById(id) {
	var tabs = _menutree.tabs("tabs");
	var data = $.extend([], tabs);
	for (var i = 0; i < data.length; i++) {
		var tab = data[i];
		if (tab.panel("options").id == id) {
			return tab;
		}
	}
	return null
}

/**
 * 重新加载当前TAB内容
 * 
 * @param tab
 *            当前TAB对象
 */
function fnRefreshTab(tab) {
	var url = $(tab.panel('options')).attr('href');
	_menutree.tabs('update', {
		tab : tab,
		options : {
			href : url
		}
	});
}