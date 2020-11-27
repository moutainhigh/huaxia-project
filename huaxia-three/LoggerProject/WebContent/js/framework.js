var ALL_DICT_KEY = "ALL_DICT"; 
var DICT_TYPE_KEY = "dict_type";
var INDEX_KEY = "index";
/**************获取UUID********************/
//优化后的全局流水号生成规则，防止流水号重复
function GUID(splitType) {
	this.Mt = [];
	this.index = 0;
	this.splitType = splitType;
	GUID.prototype.getGUID = function() {
		this.initialize_generator();
		var lastStr = "";
		var uuidStr = "";
		var newMathUUID = "";
		var dateStr = this.getDateStr(this.splitType);
		var mathUUID = Math.abs(this.Mt[Math.ceil(Math.random() * 624)])
				.toString(32).toUpperCase();

		uuidStr = dateStr + mathUUID;
		if (mathUUID.length > 16) {
			newMathUUID = mathUUID.substring(0, 16);
			uuidStr = dateStr + newMathUUID;
		} else if (mathUUID.length < 16) {
			var lastLen = 16 - mathUUID.length;
			lastStr = this.guuid(lastLen);
			uuidStr = dateStr + mathUUID + lastStr;
		}
		uuidArr = uuidStr.split("");
		uuidArr[23] = '-';
		return uuidArr.join('');
	};
	GUID.prototype.initialize_generator = function() {
		this.Mt[0] = new Date().getTime();
		for (var i = 1; i < 624; i++) {
			this.Mt[i] = 4294967295 & (1812433253 * (this.Mt[i - 1] ^ (this.Mt[i - 1] >> 30)) + i);
		}
		this.generate_numbers();
	};
	GUID.prototype.mt_rand = function(min, max) {
		return this.extract_number() % (max - min + 1) + min;
	};
	GUID.prototype.generate_numbers = function() {
		for (var i = 0; i < 624; i++) {
			var y = (this.Mt[i] & 2147483648)
					+ (this.Mt[(i + 1) % 624] & 2147483647);
			this.Mt[i] = this.Mt[(i + 397) % 624] ^ (y >> 1);
			if (y % 2 != 0) {
				this.Mt[i] ^= 2567483615;
			}
		}
	};
	GUID.prototype.extract_number = function() {
		if (this.index == 0) {
			this.generate_numbers();
		}
		var y = this.Mt[this.index];
		y ^= (y >> 11);
		y ^= ((y << 7) & 2636928640);
		y ^= ((y << 15) & 4022730752);
		y ^= (y >> 18);
		this.index = (this.index + 1) % 624;
		return y;
	};
	GUID.prototype.getDateStr = function(splitType) {
		var returnValue = "";
		var returnArr = [];
		var rule = {
			getFullYear : {
				rule : "<10",
				result : "0"
			},
			getMonth : {
				rule : "<10",
				result : "0"
			},
			getDate : {
				rule : "<10",
				result : "0"
			},
			getHours : {
				rule : "<10",
				result : "0"
			},
			getMinutes : {
				rule : "<10",
				result : "0"
			},
			getSeconds : {
				rule : "<10",
				result : "0"
			},
			getMilliseconds : function(value) {
				if (value < 10) {
					return "00" + value;
				} else {
					if (value < 100) {
						return "0" + value;
					} else {
						return value;
					}
				}
			}
		};
		var dateValue = "";
		var date = new Date();
		for ( var m in rule) {
			var mRuleSourceValue = eval("date." + m + "()");
			if (m === "getMonth") {
				mRuleSourceValue += 1;
			}
			var ruleObject = rule[m];
			var ruleStr, ruleResult, mRuleEndValue;
			if (typeof ruleObject != "function") {
				ruleStr = ruleObject.rule;
				ruleResult = ruleObject.result;
				mRuleEndValue = eval(mRuleSourceValue + ruleStr + "?'"
						+ ruleResult + "'+" + mRuleSourceValue + ":"
						+ mRuleSourceValue);
			} else {
				mRuleEndValue = ruleObject(mRuleSourceValue);
			}
			dateValue += mRuleEndValue;
		}
		returnValue = dateValue;
		if (splitType) {
			returnArr = returnValue.split('');
			returnArr.splice(8, 0, '-');
			returnArr.splice(13, 0, '-');
			returnArr.splice(18, 0, '-');
			returnValue = returnArr.join('');
		}
		return returnValue;
	};
	GUID.prototype.guuid = function(num) {
		var temp;
		var b = [], c = "0123456789ABCDEF".split("");
		for (var a = 0; a < num; a++) {
			b[a] = Math.floor(Math.random() * 16)
		}
		for (var a = 0; a < num; a++) {
			b[a] = c[b[a]]
		}
		temp = b.join("");
		return temp;
	}
}
function UUID() {
	var guids = new GUID(true);
	return guids.getGUID();
}
/** *****************定义$cf************** */
(function($) {
	if (!$) {
		return;
	}
	var $cf = {
		version : 1.0
	};
	// store中需要缓存的数据个数，超过的，将按照先进先出的情况被移除，数值小于等于0时，总长度将失效
	var STORE_LENGTH = 0;
	// 页面的标识，该设置将可用于授权、数据存取
	var PAGE_ID = window['CF_PAGE_ID'];
	var globalName = 'ContextFramework';
	window['$cf'] = window[globalName] = $cf;
	// 缓存数据
	var storeData = {
		map : {},
		list : [],
		hasKey : function(key) {
			return (',' + storeData.list.join(',') + ',').indexOf(',' + key + ',') > 0;
		}
	};
	$.extend($cf, {
		/**
		 * 显示消息
		 * @param msg: 消息
		 * @param title: 窗口标题
		 */
		showMsg : function(msg, title) {
			$.messager.show({
				title:title||'消息',
				msg:msg,
				timeout:1500
			});
		},
		alertMsg : function(msg, title) {
			$.messager.alert(title||"提示", msg);
		},
		/**
		 * 合并多个object的方法,本方法只将o中不存在（即值为undefined）的属性,将c中对应的属性进行覆盖
		 * @param o: 目标对象
		 * @param c: 源对象，注意：源对象可以为多个，分多个参数传递
		 */
		applyIf : function(o, c) {
			if (!o || !c || typeof (c) != 'object') {
				return;
			}
			for ( var p in c) {
				if (o[p] == undefined) {
					o[p] = c[p];
				}
			}
			for (var i = 2; i < arguments.length; i++) {
				arguments.callee(o, arguments[i]);
			}
			return o;
		},
		//获取最顶层的非跨域的top
		getTopWindow : function(check) {
			var win = window;
			while (true) {
				if (win.parent != win) {
					try {
						var alert = win.parent.alert;
						if (!win.parent[globalName]) {
							break;
						}
					} catch (e) {
						break;
					}
				} else {
					break;
				}
				win = win.parent;
			}
			return win;
		},
		//获取顶层框架
		getTopSC : function() {
			var topWin = $cf.getTopWindow();
			return topWin[globalName];
		},
		/**
		 * 获取store中存储的数据
		 * @param key 用于存储数据的key值
		 * @param winName 页面的名称，用于区分不同的页面
		 */
		getStore : function(key, winName) {
			if ($cf.isTopWindow()) {
				var data = storeData.map;
				winName = winName || PAGE_ID;
				if (winName) {
					data = data[winName];

					if (!data) {
						return null;
					}
				}
				return data[key];
			} else {
				winName = winName || PAGE_ID;
				return $cf.getTopSC().getStore(key, winName);
			}
		},
		/**
		 * 设置store中存储的数据
		 * @param key 用于存储数据的key值
		 * @param value 需要存储的数据，请存储成string，如果是对象，请存储成json字符串
		 * @param winName 页面的名称，用于区分不同的页面
		 */
		setStore : function(key, value, winName) {
			if ($cf.isTopWindow()) {
				var data = storeData.map;
				var list = storeData.list;
				winName = winName || PAGE_ID;
				var _key = [];
				if (winName) {
					data[winName] = data[winName] || {};
					data = data[winName];
					_key.push(winName);
				}
				data[key] = value;
				_key.push(key);
				_key = _key.join('.');

				if (!storeData.hasKey(_key)) {
					list.push(_key);
					if (STORE_LENGTH > 0 && list.length > STORE_LENGTH) {// 此处控制总个数，超过的，第一个将被移除，如果总长度控制为小于等于0，则长度控制失效
						$cf.delStoreByIndex(0);
					}
				}
			} else {
				winName = winName || PAGE_ID;// 设置数据时，默认添加PAGE_ID
				$cf.getTopSC().setStore(key, value, winName);
			}
		},
		/**
		 * 删除数据
		 * @param key key
		 * @param winName 页面名称
		 */
		delStore : function(key, winName) {
			if ($cf.isTopWindow()) {
				var map = storeData.map;
				var list = storeData.list;
				var len = list.length;
				// map数据移除
				var _key = [];
				if (winName) {
					if (map[winName]) {
						delete map[winName][key];
					}
					_key.push(winName);
				} else {
					delete map[key];
				}
				_key.push(key);
				_key = _key.join('.');
				// list数据移除
				for (var i = 0; i < len; i++) {
					if (list[i] == _key) {
						list.splice(i, 1);
						break;
					}
				}
			} else {
				$cf.getTopSC().delStore(key, winName);
			}
		},
		/**
		 * 删除当前页面的缓存数据
		 * @param winName 窗口名称
		 */
		delStoreByWinName : function(winName) {
			if ($cf.isTopWindow()) {
				var data = storeData.map;

				if (winName) {
					var winData = data[winName];
					if (winData) {
						for ( var key in winData) {
							$cf.delStore(key, winName);
						}
					}
				}
			} else {
				winName = winName || PAGE_ID;// 设置数据时，默认添加PAGE_ID
				$cf.getTopSC().delStoreByWinName(winName);
			}
		},
		// 清理页面缓存
		clearPageStore : function() {
			if ($cf.isTopWindow()) {
				$cf.delStoreByWinName(PAGE_ID);
			} else {
				$cf.getTopSC().delStoreByWinName(PAGE_ID);
			}
		},
		/**
		 * 根据序号删除
		 * @param index 需要删除的序号
		 */
		delStoreByIndex : function(index) {
			if ($cf.isTopWindow()) {
				var key = storeData.list[index];
				var list = storeData.list;
				var map = storeData.map;

				if (index < 0 || index >= list.length) {
					return;
				}

				key = key.split('.');
				if (key.length == 2) {
					var _winName = key[0];
					var _key = key[1];

					if (map[_winName]) {
						delete map[_winName][_key];
					}
				} else {
					delete map[key[0]];
				}

				list.splice(index, 1);
			} else {
				$cf.getTopSC().delStore(index);
			}
		},
		//判断,分割的字符串里面，是否包括指定的字符串
		contains : function(str, c) {
			return (',' + str + ',').indexOf(',' + c + ',') != -1;
		}
	});
	//获取最顶层的非跨域的top
	$cf.getTopWindow = function(check) {
		var win = window;
		while (true) {
			if (win.parent != win) {
				try {
					var alert = win.parent.alert;
					if (!win.parent[globalName]) {
						break;
					}
				} catch (e) {
					break;
				}
			} else {
				break;
			}
			win = win.parent;
		}
		return win;
	};
	//解析JSON报文
	$cf.decode = function(jsonStr) {
		return $.parseJSON(jsonStr);
	};
	$cf.encode = function(object) {
		return JSON.stringify(object);
	};
	//判断当前窗体是否是topWindow
	$cf.isTopWindow = function(win) {
		var topWin = $cf.getTopWindow();
		win = win || window;
		return win == topWin;
	};
	// 重新封装的ajax
	$cf.ajax = function(config, transType) {
		var code;
		if (!config.url) {
			return;
		}
		// var mask=nui.loading("正在加载,请稍后....", "加载");
		try {
			config.complete = function(e) {
				var sessionstatus = e.getResponseHeader("sessionstatus");
				if(sessionstatus == null || sessionstatus == ""){
					
				}else{
					if(sessionstatus == "303"){
	    				var win = window;
	    				while(win != win.top){
	    					win = win.top;
	    				}
	    				$.messager.alert('我的消息','您已被踢下线','confirm');
	    				win.location.href = e.getResponseHeader("CONTENTPATH");
	    			}
				}
    			
				// var dpsysteminfo = e.getResponseHeader('dpsysteminfo');
				// if(dpsysteminfo==null||dpsysteminfo==""){
				// }else{
				// if(dpsysteminfo!="haveNoDpTokenCheck"&&($cf.getStore('dpsysteminfo','index')=="haveNoDpTokenCheck")){
				// $cf.setStore('dpsysteminfo',dpsysteminfo,'index');
				// }
				// }
				// nui.hideMessageBox(mask);
			}
			// 获取需要传入后端的报文
			var messageHead = $cf.getMessageHead();
			// 获取url
			if (config.url.lastIndexOf("?") > 0) {
				code = config.url.substring(config.url.lastIndexOf("/") + 1,
						config.url.lastIndexOf("?"));
			} else {
				code = config.url.substring(config.url.lastIndexOf("/") + 1);
			}
			$cf.applyIf(config, {
				type : 'POST',
				dataType : 'json',
				//request.setRequestHeader("tokenStr",token); //token
				onSuccess : function() {},
				onFail : function(text, textStatus, xhr) {
					try {
						if (text.RSP_HEAD.ERROR_CODE) {
							//alert(text.RSP_HEAD.ERROR_MESSAGE);
						}
					} catch (e) {
						alert(this.url+",ajax onFail,"+$cf.encode(e));
					}
				},
				onError : function() {},
				afterDo : function(){"after"},
				onComplete : function(){"complete"},
				beforeDo : function() {}
			});
			messageHead.transCode = code;
			if (transType != null) {
				messageHead.transType = transType;
			}
			var data = {
				REQ_HEAD : messageHead,
				REQ_BODY : config.data
			};
			config.data = {
				REQ_MESSAGE : JSON.stringify(data)
			};
			config.success = function(ret) {
				try {
					this.onSuccess(ret);
					if (ret == null) {
						// this.onSuccess(ret);
					} else {
						// if(ret.RSP_HEAD.TRAN_SUCCESS!='1'){
						// this.onFail(ret);
						// }else{
						// this.onSuccess(ret);
						// }
					}
				} catch (e) {
					// alert(this.url+",ajax请求返回数据格式有误,"+JSON.stringify(ret));
				}
			};
			config.error = function(e) {
				var url = this.url;
				var _this = this;
				if (this.dataType == 'json') {
					try {
						var ret = eval('(' + e.responseText + ')');

						if (ret.RSP_HEAD.ResultCode == '-1' || ret.RSP_BODY.Errors) {
							this.onFail(ret);
						} else {
							this.onSuccess(ret);
						}
					} catch (error) {
						_this.onError();
					}
				} else {
					_this.onError();
				}
			};
			return $.ajax(config);
		} catch (e) {
			// nui.hideMessageBox(mask);
			throw e;
		}
	};
	// 获取业务字典翻译
	$cf.getDictDetailName = function(dictType, dictDetailCode){
		if (!dictType || !dictDetailCode ) {
			return dictDetailCode;
		}
		var v_dictData = $cf.getDictDetail();
		var v_dictList = v_dictData[dictType];
		var returnValue = dictDetailCode;
		if (v_dictList) {
			for (var i = 0; i < v_dictList.length; i++) {
				if (v_dictList[i].dictDetailCode == dictDetailCode) {
					returnValue = v_dictList[i].dictDetailName;
					break;
				}
			}
		}
		return returnValue;
	}
	// 缓存业务字典
	$cf.cacheDict = function(dictType, dictData) {
		if (!dictType && !dictData) {
			return;
		}
		var v_allDictCache = $cf.getDictCache();
		v_allDictCache[dictType] = dictData;
	};
	// 获取页面业务字典缓存
	$cf.getDictCache = function() {
		var v_allDictCache = $cf.getStore(ALL_DICT_KEY, INDEX_KEY);
		if (!v_allDictCache) {
			v_allDictCache = {};
			$cf.setStore(ALL_DICT_KEY, v_allDictCache, INDEX_KEY);
		}
		return v_allDictCache;
	};
	// 填充页面业务字典数据
	$cf.fillDictData = function(dictList, calls) {
		try {
			$(".easyui-combobox").each(function() {
				var dictType = $(this).attr(DICT_TYPE_KEY);
				if (dictType) {
					try {
						var defaultData = [ {
							"dictDetailCode" : "",
							"dictDetailName" : "--请选择--",
							"selected" : true
						} ];
						$(this).combobox({
							data : $.merge(defaultData, dictList[dictType])
						});
					} catch (e) {
						//TODO IE11，这里有异常，需要研究
					}
				}
			});
			try {
				if(calls){
					calls();
				}
			} catch (e) {
				alert("ERROR 加载业务字典后的回调函数 异常原因 =" + $cf.encode(e));
			}
		} catch (e) {
			alert("ERROR fillDictData =" + $cf.encode(e));
		}
	};
	// 判断是否存在字典缓存
	$cf.containsDictCache = function(dictType) {
		var flag = false;
		if (!dictType) {
			return flag;
		}		
		var allDictCache = $cf.getDictCache();
		if (allDictCache && allDictCache[dictType]) {
			flag = true;
		}
		return flag;
	};
	$cf.loadAllDict_local = function(dictData, keys) {
		try {
			if (keys.length>0) {
				// 加载数据大类code
				$cf.ajax({
					url : "/opas-naps/dict_load.json",
					data : {"dictData" : dictData},
					async: false,
					onSuccess : function(data) {
						try {
							if (data) {
								var allDictCache = $cf.getDictCache();
								var dictList = data.RSP_BODY;
								if (dictList) {
									var v_key = null;
									for (var i=0; i<keys.length; i++) {
										v_key = keys[i];
										allDictCache[v_key] = dictList[v_key];
									}
								}
							}
						} catch (e) {
							alert("ERROR loadAllDict AJAX " + $cf.encode(e));
						}
					}
				});
			}
		} catch (e) {
			alert("ERROR loadAllDict_local =" + $cf.encode(e));
		}
	};
	// 只加载业务字典，不渲染控件
	$cf.loadAllDictOnly = function(dictTypes) {
		if (dictTypes) {
			var keys = dictTypes.split(",");
			$cf.getTopSC().loadAllDict_local(dictTypes, keys);
		}
	};
	// 业务字典加载公共js
	$cf.loadAllDict = function(calls) {
		var dictData = "";
		var keys = [];
		try {
			$(".easyui-combotree,.easyui-combobox").each(function() {
				var v_dictData = $(this).attr(DICT_TYPE_KEY);
				if (v_dictData && !$cf.containsDictCache(v_dictData)) {
					dictData += v_dictData + ",";
					keys[keys.length] = v_dictData;
				}
			});
		} catch (e) {
			alert("ERROR loadAllDict 1 =" + $cf.encode(e));
		}
		if (keys.length>0) {
			try {
				$cf.getTopSC().loadAllDict_local(dictData, keys);
			} catch (e) {
				alert("ERROR loadAllDict 2 =" + $cf.encode(e));
			}
		}
		$cf.fillDictData($cf.getDictCache(), calls);
	};
	$cf.getDictDetail = function() {
		return $cf.getDictCache();
	};
	// 处理报文头
	$cf.getMessageHead = function() {
		var v_userRelInfo = $cf.getStore("userRelInfo",INDEX_KEY);
		var v_userCode = "";
		var v_orgCode = "";
		try {
			v_userCode = v_userRelInfo.userInfo.userCode;
		}catch(e) {}
		try {
			v_orgCode = v_userRelInfo.orgInfo.orgNo;
		}catch(e) {}
		var v_now = new Date();
		var v_nowStr = v_now.getFullYear()+"-"+(v_now.getMonth()+1)+"-"+v_now.getDate()+" "+v_now.getHours()+":"+v_now.getMinutes()+":"+v_now.getSeconds()+"."+v_now.getMilliseconds();
		var messageHead = {
			transId : new GUID().getGUID(),
			channel : "UI",
			sourceSys : "OPAS",
			transCode : "",
			userId : v_userCode,
			bsCode : v_orgCode,
			transTime : v_nowStr
		};
		return messageHead;
	};
	//获取表单数据
	$cf.getFormData = function(formId) {
		var dataObject = $("#"+formId).serializeArray();
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
	};
	//定位页面
	$cf.showUI = function(id) {
		$(document).scrollTop($("#"+id).offset().top);
	};
	
	/**
	 * 获取业务字典指定编码对应的名称
	 * @param type 字典类型
	 * @param code 字典编码
	 * @return 该字典编码对应的字典名称
	 * @since 1.0
	 * @author zhiguo.li
	 */
	$cf.getDictName = function(type, code) {
		if (!type || !code) return;
		var d = $cf.getDictDetail()[type];
		for (var i = 0; i < d.length; i++) {
			if (d[i].dictDetailCode == code) return d[i].dictDetailName;
		}
		return;
	};
})(jQuery);
/** ************资源权限管控******************* */
var __authsLoadUrl = '/opas/getComponentConfInfobyRoleQueue.json';
var __authData = $cf.getStore('compData', INDEX_KEY) || {}; // 上下文中的缓存
//加载资源配置信息至前端缓存
function __loadAuths(queueNo) {
	var roleId = $cf.getStore("roleId", INDEX_KEY);
	var data = {
		roleId : roleId,
		queueId : queueNo
	};
	var authData = {};
	$cf.ajax({
		url : __authsLoadUrl,
		data : data,
		async : false,
		onSuccess : function(ret) {
			// 添加异常处理，防止在高版本IE（版本9及更高）上运行出错，因为高版本IE中，关闭iframe窗口，会对iframe资源进行回收,导致指针无法访问而产生异常
			try {
				authData = ret.RSP_BODY.componentConf;
				__authData[queueNo] = authData;
			} catch (e) {
			}
		}
	}, "20");
}
//获取当前角色和队列的权限缓存信息
function __getAuthData(queueNo) {
	var authData = __authData[queueNo];
	if (authData == null || authData == undefined) {
		__loadAuths(queueNo);
	}
	return __authData[queueNo];

}
function doRender(parQueueNo) {
	var queueNo;
	// 若传参存在则使用传参，否则取上下文中的队列号
	if (parQueueNo == undefined || parQueueNo == "" || parQueueNo == null) {
		// 从上下文中获取当前存入的队列号
		queueNo = $cf.getStore('opas_queueNo', INDEX_KEY);
	} else {
		queueNo = parQueueNo;
	}
	var authData = __getAuthData(queueNo);
	// 若无该队列，则直接返回
	if (authData == null || authData == undefined) {
		return;
	} else {
		// 输入框和按钮控制
		$('.topui-auth').each(function() {
			var el = $(this);
			var authName = el.attr('authName'); // 获取资源ID
			if (authName) {
				var auth = authData[authName];
				// easyui或非easyui的输入框和按钮（适用于table、td、tr），可以实现不可编辑和隐藏
				if (auth.componentType == "1" || auth.componentType == "2" ) {
					if (auth.authType == 'R') {
						el.attr('disabled', 'true'); // 设置只读
					} else if (auth.authType == 'N') {
						el.css("display", "none"); // 隐藏该控件
					}
				}
				// easyui的tab页，只能实现隐藏
				if (auth.componentType == "3") {
					if (auth.authType == 'N') {
						// 隐藏tab
						var tabsId = this.parentNode.parentNode.parentNode.id;
						var tab_option = $("#" + tabsId).tabs('getTab',$.trim(this.innerText)).panel('options').tab;
						if (tab_option) {
							tab_option.hide();
						}
					}
				}
				// easyui的选择框和日期框，可以实现不可编辑和隐藏
				if (auth.componentType == "4") {
					var id = el.attr('id');
					if (auth.authType == 'R') {
						$("#" + id).combobox("disable");
					} else if (auth.authType == 'N') {
						$("#" + id).next(".combo").hide();
					}
				}
			}
		});
	}
}
/** ***********动态配置申请件主界面模板******************* */
var __templateLoadUrl = '/opas/getTemplateConfInfobyRoleQueue.json';
var __templateData = $cf.getStore('tempData', 'index') || {}; // 上下文中的缓存
//获取当前角色和队列的模板缓存信息
function __loadtemplate(queueNo) {
	var roleId = $cf.getStore("roleId", INDEX_KEY);
	var data = {
		roleId : roleId,
		queueId : queueNo
	};
	$cf.ajax({
		url : __templateLoadUrl,
		data : data,
		async : false,
		onSuccess : function(ret) {
			// 添加异常处理，防止在高版本IE（版本9及更高）上运行出错，因为高版本IE中，关闭iframe窗口，会对iframe资源进行回收,导致指针无法访问而产生异常
			try {
				__templateData[queueNo] = ret.RSP_BODY.url;
			} catch (e) {
			}
		}
	}, "20");
}
function getTemplateUrl(parQueueNo) {
	var queueNo;
	// 若传参存在则使用传参，否则取上下文中的队列号
	if (parQueueNo == undefined || parQueueNo == "" || parQueueNo == null) {
		// 从上下文中获取当前存入的队列号
		queueNo = $cf.getStore('opas_queueNo', INDEX_KEY);
	} else {
		queueNo = parQueueNo;
	}
	var url = __templateData[queueNo];
	// 若无该队列，则直接返回
	if (url == null || url == undefined || url == "") {
		// 查询后台
		__loadtemplate(queueNo);
		url = __templateData[queueNo];
		if (url == null || url == undefined || url == "") {
			// 前台设置返回默认页面
			return "/opas/html/appMainFrame2.html";
		} else {
			return url;
		}
	} else {
		return url;
	}
}
function gridDateFormat2(value, row, index) {
	if (value == null || value == "") {
		return value;
	}
	var v_date = null;
	try {
		var dd2 = null;
		dd2 = new Date(value);
		dd2.setMinutes(dd2.getMinutes() - 14 * 60);
		v_date = formatDateTime(dd2);
	} catch (e) {
		v_date = value;
	}
	return v_date;
}
function gridDateFormatYMD(value, row, index) {
	if (value == null || value == "") {
		return value;
	}
	var v_date = null;
	try {
		var dd2 = null;
		dd2 = new Date(value);
		dd2.setMinutes(dd2.getMinutes() - 14 * 60);
		v_date = formatDate2Time(dd2);
	} catch (e) {
		v_date = value;
	}
	return v_date;
}
function gridDateFormat(value, row, index) {
	if (value == null || value == "") {
		return value;
	}
	var v_date = null;
	try {
		v_date = formatDateTime(new Date(value));
	} catch (e) {
		v_date = value;
	}
	return v_date;
}
var formatDateTime = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? ('0' + m) : m;
	var d = date.getDate();
	d = d < 10 ? ('0' + d) : d;
	var h = date.getHours();
	h = h < 10 ? ('0' + h) : h;
	var minute = date.getMinutes();
	minute = minute < 10 ? ('0' + minute) : minute;
	var s = date.getSeconds();
	s = s < 10 ? ('0' + s) : s;
	return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ":" + s;
};
//日期显示年月日 2016-06-16 bu machao
var formatDate2Time = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? ('0' + m) : m;
	var d = date.getDate();
	d = d < 10 ? ('0' + d) : d;
	return y + '-' + m + '-' + d + ' ';
};
//日期显示年月日 2016-06-16 bu machao
function gridDateFormat3(value, row, index) {
	if (value == null || value == "") {
		return value;
	}
	var v_date = null;
	try {
		v_date = formatDate2Time(new Date(value));
	} catch (e) {
		v_date = value;
	}
	return v_date;
}

//日期显示年月 2016-06-16 bu machao
var formatDate4Time = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? ('0' + m) : m;
	return y + '-' + m;
};
//日期显示年月2016-06-16 bu machao
function gridDateFormat4(value, row, index) {
	if (value == null || value == "") {
		return value;
	}
	var v_date = null;
	try {
		v_date = formatDate4Time(new Date(value));
	} catch (e) {
		v_date = value;
	}
	return v_date;
}
// 获取地址栏url ？后面的参数
function getURLParam() {
	var addr = window.location.search;
	var dat = addr.split("?");
	var list = dat[1].split("&");
	var result = {};
	for ( var key in list) {
		var ll = list[key].split("=");
		result[ll[0]] = ll[1];
	}
	return result;
}

//校验结束时间不能大于开始时间
function checkTimes(startTime, endTime){
	if(startTime !="" && endTime != ""){
		var timeCount = new Date(endTime.replace(/\-/g, "\/")) - new Date(startTime.replace(/\-/g, "\/"));
		if(timeCount<0){
			$cf.alertMsg("开始时间不能大于结束时间");
			return false;
		}
		return true;
	}
	return true;
}

//校验结束时间不能大于开始时间
function checkTimesByFiled(startTime, endTime, msg){
	if(startTime !="" && endTime != ""){
		var sDate = new Date(startTime);
		var eDate = new Date(endTime);
		var fen=(eDate.getTime()-sDate.getTime())/1000/60;
		var distance = parseInt(fen/(24*60));
		if(distance<0){
			$cf.alertMsg("【" + msg + "】开始时间不能大于结束时间");
			return false;
		}
		return true;
	}
	return true;
}

//校验结束时间不能大于开始时间
function checkNodeTimesByFiled(startTime, endTime, msg){
	if(startTime !="" && endTime != ""){
		var sDate = new Date(startTime);
		var eDate = new Date(endTime);
		var fen=(eDate.getTime()-sDate.getTime())/1000/60;
		var distance = parseInt(fen/(24*60));
		if(distance<0){
			$cf.alertMsg("【" + msg + "】开始时间不能大于结束时间");
			return false;
		}
		distance=distance+1;
		if(distance>14){
			$cf.alertMsg("【" + msg + "】 时间跨度超过14天");
			return false;
		}	
		return true;
	}
	return true;
}
//校验结束时间不能大于开始时间
function checkNodeTimesByFiled2(startTime, endTime, msg){
	if(startTime !="" && endTime != ""){
		var sDate = new Date(startTime);
		var eDate = new Date(endTime);
		var fen=(eDate.getTime()-sDate.getTime())/1000/60;
		var distance = parseInt(fen/(24*60));
		if(distance<0){
			$cf.alertMsg("【" + msg + "】.");
			return false;
		}
		return true;
	}
	return true;
}
//格式化日期 yyyy-MM-dd
function getFormatDate(value, row, index) { 
	if (value==null||value=="") {
		return value;
	}
	var date = new Date(value);
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;

}
//格式化日期 yyyy-MM-dd hh:mi:ss
function getFormatTime(value, row, index) { 
	if (value==null||value=="") {
		return value;
	}
	var date = "";
	if(value.indexOf("CST")>0){
		
		date = new Date(new Date(value)-14*3600*1000);//GMT时间快14个小时
	}else if(value.indexOf("-")>0){
		date = new Date(value.replace(/-/g, "/"));
	}else{
		date = new Date(value);
	}
	var seperator1 = "-";
	var seperator2 = ":";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var h = date.getHours();
	h = h < 10 ? ('0' + h) : h;
	var minute = date.getMinutes();
	minute = minute < 10 ? ('0' + minute) : minute;
	var s = date.getSeconds();
	s = s < 10 ? ('0' + s) : s;
	var currentdate = year + seperator1 + month + seperator1 + strDate + ' ' + h + seperator2 + minute + seperator2 + s;
	return currentdate;
}