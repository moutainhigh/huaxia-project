// JavaScript Document .T.20150511
	/**
	 * jQuery类插件,扩展jQuery类方法
	 * 
	 */
	$.extend({
	/**
	 * 获取UUID
	 * eg: var uuid = $.getUUID();
	 * @param
	 */
	getUUID : function(){
		return UUID().replace(/-/g,"");
	},
	/**
	 * 判断变量是否为空
	 * eg: var object = null;
	 *     var boolean = $.isEmpty(object);
	 * @param val
	 */
	isEmpty:function(val){
		if(val == undefined || val == null || (val.trim && val.trim().length == 0)){
			return true;
		}else{
			return false
		}
	},
	/**
	 * 判断变量是否不为空
	 * eg: var object = null;
	 *     var boolean = $.isNotEmpty(object);
	 * @param val
	 */
	isNotEmpty:function(val){
		return !this.isEmpty(val)?true:false;
	},
	/**
	 * 判断变量类型是否是string
	 * eg: var object = "123";
	 *     var boolean = $.isString(object);
	 * @param val
	 */
	isString:function(object){
		  return object && typeof object == 'string' && Object.prototype.toString.call(object) === '[object String]';
	},
	jsonTtrim:function(object){
		
		for(var key in object){
			var val = object[key];
			if($.isNotEmpty(val) && $.isString(val)){
				val=val.trim();
				object[key]=val;
			}
		}
	},
	/**
	 * 前台通过获取get方式参数
	 * @param key 
	 * @param loc 
	 * var username = $.getParameter('username');
	 */
	getParameter:function(key,loc) {
		var query = {}, loc = loc || 0;
		var _init = function() {
			var queryString = location.search.substring(1), arrays = queryString
					.split("&");
			for (var i = 0; i < arrays.length; i++) {
				var index = arrays[i].indexOf('=');
				var key = arrays[i].substring(0, index);
				var val = arrays[i].substring(index + 1);
				if (!query[key]) {
					var parr = [];
					parr.push(val);
					query[key] = parr;
				} else {
					query[key].push(val);
				}
			}
		};
		_init();
		if (!query[key]) return '';   
		return !query[key][loc]?'':query[key][loc]; 
	}
});