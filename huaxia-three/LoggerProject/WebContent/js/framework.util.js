(function($) {
	if (!$) return undefined;
	var $u = {
		version: 1.0
	}
	window['$u'] = $u;
	$.extend({
		/**
		 * 功能：获取地址栏申请件编号
		 * 格式：http://106.129.1.61:9093/opas-topui/web/audit/bzk_credit_approve_main.html?appId=180223****P07191
		 * 说明：参数p必须为?后第一个参数
		 * @param p 参数
		 */
		fnGetAppId : function(p) {
			var regex = new RegExp("(^|&)" + p + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(regex);
			return r != null ? unescape(r[2]) : undefined;
		},
		/**
		 * 功能：打开浏览器窗口
		 * @version 1.0
		 */
		fnOpenWindow : function(url) {
			return window.open(url, '', 'resizable=no,scrollbars=no,titlebar=yes,status=no,menubar=no,toolbar=no,location=no,left=0,top=0,width='+screen.availWidth+',height='+screen.availHeight, true);
		}
	});
	$.extend($u, {
		/**
		 *  功能：JSON操作（添加）
		 *  @param o JSON对象
		 *  @param s JSON字符串
		 */
		push : function(o, s) {
			if (!o) return undefined;
			if (o instanceof Array) {
				o.push($.parseJSON(s));
			} else {
				var object = $.parseJSON(s);
				$.each(object, function(k, v) {
					o[k] = v;
				});
			}
		},
		/**
		 * 功能：JSON操作（删除）
		 * @param o 存储器
		 * @param key JSON键
		 */
		remove : function (o, key) {
			if (!o) return undefined;
			$this = o.json;
			var idx = 0;
			$.each($this, function(i, x) {
				if (x.key == key) {
					idx = i;
				}
			});
			return $this.splice(idx, 1);
		},
		/**
		 *  功能：JSON操作（串行化）
		 *  @param o JSON对象
		 */
		stringify : function(o) {
			if (!o) return undefined;
			return JSON.stringify(o);
		}
	});
	$.fn.extend({
		/**
		 * 功能：加载组件结果集
		 * 样例：
		 * <ul>
		 *   <li>加载结果集：{"json":[{"key":0,"value":"内容0"}]}</li>
		 *   <li>加载结果集并选中指定项：{"json":[{"key":0,"value":"内容0"},{"key":1,"value":"内容1"}],"selected":false,"key":"1","ignore":false}</li>
		 * <ul>
		 * @param settings 组件参数集
		 * @version 1.1
		 */
		fnLoadComponent : function(settings) {
			if (!settings)
				return undefined;
			var defaults = {
				selected : false, // 下拉框默认不选中
				ignore : false // 下拉框默认带"请选择"项
			}
			$.extend(defaults, settings);
			var $this = $(this), key = settings.key, selected = settings.selected, ignore = settings.ignore;
			$this.empty();
			var options = '';
			if (!ignore) {
				options += '<option value="">请选择</options>';
			}
			$.each(settings.json, function(i, o) {
				var k = o.key, v = o.value;
				if (selected && key == k) {
					options += '<option value="' + k + '" selected="selected">' + v + '</option>';
				} else {
					options += '<option value="' + k + '">' + v + '</option>';
				}
			});
			$this.append(options);
		}
	});
})(jQuery);