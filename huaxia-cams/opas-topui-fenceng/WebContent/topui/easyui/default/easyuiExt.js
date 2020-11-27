/**
 * jQuery EasyUI 1.4.5.x 函数扩充
 * Author：2016.04.06.Tu.Huateng
 * 2016.04.18 重构 表单loadFilter,loader
 * 2016.04.19 $easyT包-FunctionAdd getPaneParams,dataGridLoad
 * 2016.04.20 $easyT包-FunctionAdd getWinParams,openWin
 * 2016.05.23 TABS 标签 带 tabs-icon 移动位置
*/
$(document).ready(function(e){
    var p =$cf.getStore("progress","load");
    if(p) {
        $cf.setStore("progress",null,"load");
        setTimeout(function(){
            top.$.messager.progress('close')
        },600);
    }
});
$.extend(
	$.fn.validatebox.defaults.rules,{
		notnull:{
			validator: function(value,param){
				return value.length>0;
			},
			message: '不能为空'
		},
		length: {
			validator: function(value, param){
				return value.length <= param[1]?(value.length >= param[0]?true:false):false;
			},
			message: '输入内容数量范围{0}-{1}'
		},
		email:{
			validator : function(value){
				return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
			},
			message : '请输入有效的电子邮件账号(例：***@***.com)'
		},
		CHS: {
			validator: function (value, param) {
				return /^[\u0391-\uFFE5]+$/.test(value);
			},
			message: '请输入汉字'
		},
		english : {// 验证英语
	        validator : function(value) {
	            return /^[A-Za-z]+$/i.test(value);
	        },
	        message : '请输入英文'
	    },
	    ip : {// 验证IP地址
	        validator : function(value) {
	            return /\d+\.\d+\.\d+\.\d+/.test(value);
	        },
	        message : 'IP地址格式不正确'
	    },
		ZIP: {
			validator: function (value, param) {
				return /^[0-9]\d{5}$/.test(value);
			},
			message: '邮政编码不存在'
		},
		QQ: {
			validator: function (value, param) {
				return /^[1-9]\d{4,10}$/.test(value);
			},
			message: 'QQ号码不正确'
		},
		mobile: {
			validator: function (value, param) {
				return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
			},
			message: '手机号码不正确'
		},
		tel:{
			validator:function(value,param){
				return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
			},
			message:'电话号码不正确'
		},
		mobileAndTel: {
			validator: function (value, param) {
				return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
			},
			message: '电话号码或手机号码不正确'
		},
		number: {
			validator: function (value, param) {
				return /^[0-9]+.?[0-9]*$/.test(value);
			},
			message: '请输入数字'
		},
		money:{
			validator: function (value, param) {
			 	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
			 },
			 message:'请输入正确的金额'

		},
		mone:{
			validator: function (value, param) {
			 	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
			 },
			 message:'请输入整数或小数'

		},
		integer:{
			validator:function(value,param){
				return /^[+]?[1-9]\d*$/.test(value);
			},
			message: '请输入最小为1的整数'
		},
		integ:{
			validator:function(value,param){
				return /^[+]?[0-9]\d*$/.test(value);
			},
			message: '请输入整数'
		},
		range:{
			validator:function(value,param){
				if(/^[1-9]\d*$/.test(value)){
					return value >= param[0] && value <= param[1];
				}else{
					return false;
				}
			},
			message:'输入的数字在{0}到{1}之间'
		},
		minLength:{
			validator:function(value,param){
				return value.length >=param[0];
			},
			message:'至少输入{0}个字'
		},
		maxLength:{
			validator:function(value,param){
				return value.length<=param[0];
			},
			message:'最多{0}个字'
		},
		//select即选择框的验证
		selectValid:{
			validator:function(value,param){
				if(value == param[0]){
					return false;
				}else{
					return true ;
				}
			},
			message:'请选择'
		},
		idCode:{
			validator:function(value,param){
				return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
			},
			message: '请输入正确的身份证号码'
		},
		loginName: {
			validator: function (value, param) {
				return /^[\u0391-\uFFE5\w]+$/.test(value);
			},
			message: '登录名称只允许汉字、英文字母、数字及下划线。'
		},
		equalTo: {
			validator: function (value, param) {
				return value == $(param[0]).val();
			},
			message: '两次输入的字符不一至'
		},
		englishOrNum : {// 只能输入英文和数字
	        validator : function(value) {
	            return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
	        },
	        message : '请输入英文、数字、下划线或者空格'
	    },
	    xiaoshu:{
			validator : function(value){
				return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
			},
			message : '最多保留两位小数！'
	    },
	    numberPec:{
			validator : function(value){
				return /^(([1-9]+)|([0-9]+\.[0-9]{1,1}))$/.test(value);
			},
			message : '最多保留一位小数！'
	    },
		ddPrice:{
			validator:function(value,param){
				if(/^[1-9]\d*$/.test(value)){
					return value >= param[0] && value <= param[1];
				}else{
					return false;
				}
			},
			message:'请输入1到100之间正整数'
		},
		jretailUpperLimit:{
			validator:function(value,param){
				if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
					return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
				}else{
					return false;
				}
			},
			message:'请输入0到100之间的最多俩位小数的数字'
		},
		rateCheck:{
			validator:function(value,param){
				if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
					return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
				}else{
					return false;
				}
			},
			message:'请输入0到1000之间的最多俩位小数的数字'
		},
		TZXM: {
			validator: function (value) {
				return /^[A-Z/\s]{1,}$/.test(value);
			},
			message: '请输入正确格式',
		},
		GJDM: {
			validator: function (value) {
				return /^(001|002|003|004|005|006|007|008|009|010|011|012|013|014|015|016|017|018|019|020|021|022|023|024|025|026|027|028|029|030|031|032|033|034|035|036|037|038|039|040|041|042|043|044|045|046|047|048|049|050|051|052|053|054|055|056|057|058|059|060|061|062|063|064|065|066|067|068|069|070|071|072|073|074|075|076|077|078|079|080|081|082|083|084|085|086|087|088|089|090|091|092|093|094|095|096|097|098|099|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|499|688001|002|003|004|005|006|007|008|009|010|011|012|013|014|015|016|017|018|019|020|021|022|023|024|025|026|027|028|029|030|031|032|033|034|035|036|037|038|039|040|041|042|043|044|045|046|047|048|049|050|051|052|053|054|055|056|057|058|059|060|061|062|063|064|065|066|067|068|069|070|071|072|073|074|075|076|077|078|079|080|081|082|083|084|085|086|087|088|089|090|091|092|093|094|095|096|097|098|099|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|499|688)$/.test(value);
			},
			message: '请输入正确代号',
		},
		GZZHYDM: {
			validator: function (value) {
				return /^(999|959|903|901|804|801|751|720|719|718|701|670|667|657|656|653|650|555|554|553|552|551|508|507|506|404|403|311|310|309|306|305|302|301|213|212|211|210|209|208|207|206|205|109|108|105|016|015|014|013|012|007|003|002)$/.test(value);
			},
			message: '请输入正确代码',
		},
		/*selectValueRequired: {  
			validator: function(value,param){  
			return $(param[0]).combobox('getValue') != '';  
			},  
			message: '该输入项为必输项'  
		}*/
		selectValueRequired: {  
			validator: function(value,param){  
				return value != '--请选择--';  
			},  
			message: '该输入项为必输项'  
		}
	},
    $.fn.tabs.defaults.tabHeight = 35,
    $.fn.datagrid.defaults.loadFilter = function(data) {
        return data;
    },
    $.fn.datagrid.defaults.loader = function(param, success, error){
        var opts = $(this).datagrid('options');
        if (!opts.url) return false;
        if (opts.pagination && opts.pageNumber == 0){
            opts.pageNumber = 1;
            param.page = 1;
        }
        if (param.page == 0) return false;
        $cf.ajax({
            type: opts.method,
            url: opts.url,
            data: param,
            dataType: 'json',
            onSuccess: function(e) {
                var bodyer = e["RSP_BODY"];
                /**
                 * bodyer["rows"]; 内容
                 * bodyer["total"]; 总数
                 */
                if (bodyer) {
                    success(bodyer);
                }
            },
            onError:function(e){
                success.apply(this, arguments);
                alert("onError:"+e);
            },
            onFail:function(e){
                error.apply(this, arguments);
                alert("onFail:"+e);
            }
        });
    },
    /**
     * 释放jquery对象，无返回值。此方法用以解决jquery的内存泄露问题
     */
    $.fn.del = function (selector, keepData) {
        if (!selector || $.filter(selector, [this]).length) {
            // 释放dom对象
            var item = $(this);
            var clearItem = $.lui.widget.__clean$;
            item.appendTo(clearItem);
            $('*', clearItem).each(function (i, e) {
                (events = $.data(this, 'events')) && $.each(events, function (i, e1) {
                    $(e).unbind(i + '.*');
                });
                $.event.remove(this);
                $.removeData(this);
            });
            clearItem[0].innerHTML = '';
            item = null;
        }
    },
    getParent = function(){
            return window.parent;
    }
);

$(function(){

});

////////////////////////
//自定义 渲染	////////
////////////////////////
var $easyT = {
    target:null,
    data:null,
    //渲染逐步请求数据
	doRender:function(id){
		var _r,_d;
		_r = _render();
		if(_r == null || _r.length<1){return;}
		//$.parser.parse();
		var _o;
		for(var r=0;r<_r.length;r++){
			_o = _r[r];
			if(_o['t']=='dictcombobox'){
				_d =  $easyT.getDictInfo(_o['p']);  //$.data(this,"dictcombobox-"+_o['p']);//取缓存
				//debugger;
                if(_d){
					$('#'+_o['o']).combobox("loadData",_d);
				}else{
					//$.ajax({
					//	url: "http://127.0.0.1",
					//	dataType: "json",
					//	data: {},
					//	success: function(){
							//alert(_o['p']+"-字典请求数据");
							//返回字典请求数据-Start
							var data = [{dictId:"0", dictName: "女性"},{dictId:"1", dictName: "男性"}];
							//var data = $.parseJSON(jsonstr);
                            //data.unshift({dictId: '', dictName: ''});
                            $easyT.setDictInfo(_o['p'],data);    //$.data(this,"dictcombobox-"+_o['p'],data);//存缓存
							//-End
							$('#'+_o['o']).combobox("loadData",data);
                    //	}
					//});
				}
                $('#'+_o['o']).combobox({ prompt:'请选择'});
			}
		}
	},
    //渲染一次性请求数据
	doRenderAll:function(){
		var _r,_d;
		_r = _render();
		if(_r == null || _r.length<1){return;}
		//$.parser.parse();
		var _o,_u=[];
		for(var r=0;r<_r.length;r++){
			_o = _r[r];
			if(_o['t']=='dictcombobox'){
				_d = $.data(this,"dictcombobox-"+_o['p']);//取缓存
				if(_d){
					$('#'+_o['o']).combobox("loadData",_d);
				}else{
					_u.push(_o['p']);
				}
			}
		}
	},
    /**
     * 获取表单DataGrid控件属性设置
     * @returns {{fitColumns: boolean, singleSelect: boolean, rownumbers: boolean, striped: boolean, pagination: boolean, pageList: number[], pageNumber: number}}
     */
    getPaneParams:function(){
        return {
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            striped: true,
            pagination: true,
            pageList: [10, 20, 50, 100],
            pageNumber: 1
        };
    },
    /**
     * 表单DataGrid请求数据
     * @param id 表单对象编号
     * @param url 表单请求路径
     * @param param 表单请求参数
     * @returns {*|jQuery|HTMLElement}
     */
    dataGridLoad:function(id,url,param,paneParams){
        var arm = $('#'+id);
        if(arm){
            var paramT = $.extend(this.getPaneParams(), paneParams || {});
            if(param){
                arm.datagrid('options').queryParams = param;
            }
            if(url){
                arm.datagrid('options').url = url;
            }
            arm.datagrid(paramT);
        }
        return arm;
    },
    /**
     * 获取窗体控件属性设置
     * @returns {{width: number, height: string, title: string, url: string, maximizable: boolean, minimizable: boolean, resizable: boolean, modal: boolean, onClosed: Function, onSuccess: Function}}
     */
    getWinParams:function(){
        return {
            width: 600,
            height: 'auto',
            title: 'New Window',
            url: '',
            top: 10,
            maximizable: false,
            minimizable: false,
            resizable: false,
            modal: true,
            onClosed:function(){},
            onSuccess:function(){},
            target:null,
            data: null,
            time: 1200,
            setData:function(value){this.data = value},
            getData:function(){return this.data}
        };
    },
    /**
     * 外弹窗体控件
     * @param paneParams
     * @returns {*|jQuery}
     */
    openWin: function(paneParams) {
        var paramT =  $.extend(this.getWinParams(), paneParams || {});
            var content = ''
                + '<iframe scrolling="yes" frameborder="0" style="width:100%;height:100%;"  src="'+ paramT.url +'"></iframe>'
                    //+ '<iframe scrolling="yes" frameborder="0" style="width:'+opts.width+'px;height:'+opts.height+'px;" src="'+ opts.url +'"></iframe>'
                + '<div style="clear:both;"/>';
            var win = $('<div class="messager-body"></div>').appendTo('body');
            win.append(content);
            //$.messager.progress();
            //$.messager.progress('close');
            win.window({
                top: paramT.top,
                title: paramT.title,
                width: paramT.width,
                height: paramT.height,
                left: paramT.left,
                modal: paramT.modal,
                collapsible: false,
                minimizable: paramT.minimizable,
                maximizable: paramT.maximizable,
                resizable: paramT.resizable,
                //Add------------
                //draggable: paramT.draggable,
                //Add------------
                inline: true,
                loader: function(a,b,c){
                    alert('loader');
                },
                onClose: function(){
                    var tar = this.firstChild.contentWindow;
                    setTimeout(function(){
                        win.window('destroy');
                        if(tar){
                            tar.$easyT.target = null;
                            tar.$easyT.data = null;
                        }
                        if(paramT.onClosed){paramT.onClosed();}
                    }, 100);
                },
                onOpen:function(){
                    var tar = this.firstChild.contentWindow;
                    setTimeout(function(){
                        if(tar){
                            tar.$easyT.target = paramT.target?paramT.target:this;
                            tar.$easyT.data = paramT.data;
                            if(tar.setData){tar.setData(paramT.data);}
                        }
                        if(paramT.onSuccess){paramT.onSuccess();}
                    }, paramT.time);
                },
                onLoadError:function(){
                },
                onResize: function(){
                    //opts.width = this.clientWidth;
                    //opts.height = this.clientHeight;
                },
                onMove: function(left,top){
                	var parentObj = $(this).panel('panel').parent();
                	if (left < 0) {
                		$(this).window('move', {
                			left : 1
                		});
                	}
                	if (top < 0) {
                		$(this).window('move', {
                			top : 1
                		});
                	}
                	var width = $(this).panel('options').width;
                	var height = $(this).panel('options').height;
                	var right = left + width;
                	var buttom = top + height;
                	var parentWidth = parentObj.width();
                	var parentHeight = parentObj.height();
                	if(parentObj.css("overflow")=="hidden"){
                		if(left > parentWidth-width){
                			$(this).window('move', {
                				"left":parentWidth-width
                			});
                		}
                		if(top > parentHeight-height){
                			$(this).window('move', {
                				"top":parentHeight-height
                			});
                		}
                	}
                }
            });
            return win;
    },
    /**
     * 获取 - 通过大类参数 获取小类数据
     * @param dictType
     * @returns {*}
     */
    getDictInfo:function(dictType){
        var dictInfo = $cf.getStore("dictInfo","index");
        //debugger;
        if(dictInfo==null || dictInfo[dictType]==null){
            return null;
        }
        return dictInfo[dictType];
    },
    /**
     * 保存 - 通过大类参数 存储小类数据
     * @param dictType
     * @param data
     */
    setDictInfo:function(dictType,data){
        /**存缓存 start*/
        var dictInfo = $cf.getStore("dictInfo","index");
        if(dictInfo==null){
            dictInfo = new Object();
        }
        dictInfo[dictType] = data;
        $cf.setStore("dictInfo",dictInfo,"index");
    }
};

//获取渲染对象集
var _render =function(){
	var _r,_o;
	var _n=0;
	$(".easyui-dictcombobox").each(function(){
		$(this).removeClass();
		$(this).addClass('easyui-combobox');
		if($(this).attr('dictTypeId')){
			if(_r == null){_r = [];}
			_o = $(this).attr('id');
			if(_o==null){
				_o = 'easyui-dictcomboboxTu'+_n;
				$(this).attr('id',_o);
				_n++;
			}
            //$(this).attr('value',"请选择");
			_r.push({'p':$(this).attr('dictTypeId'),'o':_o,'t':'dictcombobox'});
		}
	});
	return _r;
};


////////////////////////
//自定义 函数	////////
////////////////////////

//验证id对象内容是否有效 id对象class="easyui-validatebox"
function isValid(id){
	return $('#'+id)?$('#'+id).validatebox("isValid"):false;
}
//设置id对象为必输项
function required(id){
	var objer = $("#"+id);
	if(objer){
		if(objer.attr('class').indexOf('validatebox')){
			objer.validatebox({
				required: true,
				missingMessage: '必须输入'
			});
		}
	}
}

//表单发送检验 - 返回表单中控件（含id）
function isRequired(id,isNull){
    var result;
    var elemt = $('#'+id);
    if(elemt){
        var isValid = elemt.form('validate');
        if(isValid) {
            isValid = elemt['0'];
            if (isValid) {
                var objer;
                for (var i=0;i<isValid.length;i++) {
                    objer = isValid[i];
                    if (result == null) {
                        result = {};
                    }
                    if(objer['type'] =='radio' && objer.checked){
                    	result[objer.name] = objer['value'];
                    }else{
	                    if (objer['id']) {
	                        if (result == null) {
	                            result = {};
	                        }
	                        if (objer['value'] != '') {
	                            result[objer.id] = objer['value'];
	                        }else {
	                            var context = objer['className'];
	                            if(context.indexOf('combobox')>0){
	                                context = $('#'+objer.id).combobox("getValue");
	                                if(context != '' || isNull){
	                                    result[objer.id] = context;
	                                }
	                            }else{
	                                if(isNull){
	                                    result[objer.id] = objer['value'];
	                                }
	                            }
	                        }
	                    }
	                }
                }
            }
        }
    }
    return result;
}

//表单发送检验 - 返回表单中控件（含id）
function isAllRequired(id,isNull){
    var result;
    var elemt = $('#'+id);
    if(elemt){
        isValid = elemt['0'];
        if (isValid) {
            var objer;
            for (var i=0;i<isValid.length;i++) {
                objer = isValid[i];
                if (result == null) {
                    result = {};
                }
                if(objer['type'] =='radio' && objer.checked){
                	result[objer.name] = objer['value'];
                }else{
                    if (objer['id']) {
                        if (result == null) {
                            result = {};
                        }
                        if (objer['value'] != '') {
                            result[objer.id] = objer['value'];
                        }else {
                            var context = objer['className'];
                            if(context.indexOf('combobox')>0){
                                context = $('#'+objer.id).combobox("getValue");
                                if(context != '' || isNull){
                                    result[objer.id] = context;
                                }
                            }else{
                                if(isNull){
                                    result[objer.id] = objer['value'];
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return result;
}

/**
 * 改变关键字弹窗样式
 * create by jyz 2016-7-28
 */
function modifyStyle(){
	$("div[class*='messager-body']").attr("style","padding: 0px; margin: 0px;width: 890px; height: 612px;");
}

function baseValidationEx(base) {
	//判断身份证号码和性别是否匹配
	var appCertType = base.appCertType;
	var appCertNo = base.appCertNo;
	var applyerSex = base.applyerSex;
	if(appCertType == "01" && appCertNo.length == 18){
		appCertNo = appCertNo.substring(16,17);
		// modify by jyz 2016-5-19 优化判断规则
		if(applyerSex == "F" || applyerSex == "M"){
			if(compareNum(appCertNo) != applyerSex ){
				alert("身份证号码和性别不匹配，请修改后重新保存！");
				return 0;
			}
		}
	}else if(appCertType == "01" && appCertNo.length != 18){
		alert("身份证号码有误，请修改后重新保存！");
		return 0;
	}
	
	var applyerMobilePhone = base.applyerMobilePhone;
	var linkMan1MobilePhone = base.linkMan1MobilePhone;
	var linkMan2MobilePhone = base.linkMan2MobilePhone;
	if (applyerMobilePhone == linkMan1MobilePhone) {
		alert("联系人1手机不能与申请人手机相同！");
		return 1;
	} else if (applyerMobilePhone == linkMan2MobilePhone) {
		alert("联系人2手机不能与申请人手机相同！");
		return 1;
	}

	//申请人出生日期下发校验逻辑
	//18位身份证
	if(appCertType == "01" && base.appCertNo.length == 18){
		var appCertBirthDate18 = base.appCertNo.substring(6, 14);
		var applyerBirthDate18 = base.applyerBirthDate.replace(/-/g, '');
		if(appCertBirthDate18 != applyerBirthDate18){
			alert("申请人出生日期与身份证中的出生日期不一致！");
			return 0;
		}
	}
	//15位身份证
	if(appCertType == "02" && base.appCertNo.length == 15){
		var appCertBirthDate15 = base.appCertNo.substring(6, 12);
		var applyerBirthDate15 = base.applyerBirthDate.replace(/-/g, '').substring(2);
		if(appCertBirthDate15 != applyerBirthDate15){
			alert("申请人出生日期与身份证中的出生日期不一致！");
			return 0;
		}
	}
}
function compareNum(appCertNo){
	if(appCertNo%2==0){
		return "F";
	}else{
		return "M";
	}
}

//是否需要执行刷新函数
var refreshOf = false;
//父页面可以调用子页面此刷新函数 执行。
function refreshDo(){
	if(refreshOf){
		refreshData();
		refreshOf = false;
	}
}
//页面可以重构此函数。当标记refreshOf为TRUE的时候。 就执行refreshData函数
function refreshData(){}
//页面关闭触发的事件 - 清理页面中IFRAME控件
var unloadListernOf = true;
/**
 * 设置页面销毁开关
 * @param value
 */
function setunloadListern(value){
	unloadListernOf = value;
}
/**
 * 页面销毁元素监听
 * 默认为启动 见参数 unloadListernOf
 */
window.onunload = function(){
	if(unloadListernOf){
		cycleClear();
	}
};
function cycleClear() {
    try {
        var iframes = document.getElementsByTagName("iframe");
        var i = 0,length = iframes.length || 0;
        var objer = null, p = null;
        for(;i<length; i++){
            objer = frames[0].frameElement;
            if(objer != null) {
                objer.contentWindow.document.write('');
                objer.contentWindow.document.clear();
                objer.contentWindow.close();
                p = objer.parentNode;
                if (p != null) {
                    p.removeChild(objer);
                }
            }
        }
        iframes = null;
        i = NaN;
        length = NaN;
        objer = null;
        p = null;
        if($.browser.msie){
       	 CollectGarbage();
        }
        
        //this.document.write('');
        this.document.clear();
        for(obj in this){
        	if(typeof this[obj] == "object"){
        		this[obj] = null;	
        	}
        }
        /*
         p = this.parent.document;
         if (p != null) {
        	 p.removeChild(this);
         }
         */
        //window.close();
    } catch (e) {
        //setTimeout(cycleClear, 100);
    }
}