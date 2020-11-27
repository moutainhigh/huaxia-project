var ALL_DICT_KEY = "ALL_DICT"; 
var DICT_TYPE_KEY = "dict_type";
var INDEX_KEY = "index";

// 优化后的全局流水号生成规则，防止流水号重复
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
	
	/**
	 * 获取业务字典指定类型对应的JSON字符串
	 * @param type 字典类型
	 * @return 该字典类型对应的JSON字符串
	 * @since 1.0
	 * @author zhiguo.li
	 */
	$cf.getDictJSON = function(type) {
		if (!type || type == '') return undefined;
		var d = $cf.getDictDetail()[type], json = '[';
		for (var i = 0; i < d.length; i++) {
			json += '{"key":"' + d[i].dictDetailCode + '","value":"' + d[i].dictDetailName + '"}';
			if (i < d.length - 1) {
				json += ',';
			}
		}
		json += ']';
		return json;
	};
})(jQuery);