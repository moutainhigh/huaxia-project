/**
 * 不查询字典，查询业务维护表，下拉框显示
 * 降级产品下拉框显示。针对标准卡的，易达金没有。
 * @param cardlist
 * @param obj
 * @param lean
 */
function dictList(cardlist, obj, lean,noselect,cardFace,yearFree) {
	if(cardFace==null || cardFace ==""){
		cardFace = c4VerCode;
	}
	if(yearFree==null || yearFree ==""){
		yearFree = appAcctyp;
	}
	var dictData = "[";
	for (var i = 0; i < cardlist.length; i++) {
		var code = cardlist[i].productCode;
		var name = cardlist[i].productName;
		if (lean && cardlist.length > 1) {
			if (i == 0) {
				if(noselect==null || noselect=="" || noselect==code){
					dictData = dictData + "{dictDetailCode:'" + code + "',"
					+ "dictDetailName:'" + name + "',selected:" + true
					+ "},";
				}else{
					dictData = dictData + "{dictDetailCode:'" + code + "',"
					+ "dictDetailName:'" + name + "'},";
				}
			} else if (i == cardlist.length - 1) {
				if(noselect!=null && noselect!="" && noselect==code){
					dictData = dictData + "{dictDetailCode:'" + code + "',"
					+ "dictDetailName:'" + name + "',selected:" + true
					+ "}]";
				}else{
					dictData = dictData + "{dictDetailCode:'" + code + "',"
					+ "dictDetailName:'" + name + "'}]";
				}
			} else {
				if(noselect!=null && noselect!="" && noselect==code){
					dictData = dictData + "{dictDetailCode:'" + code + "',"
					+ "dictDetailName:'" + name + "',selected:" + true
					+ "},";
				}else{
					dictData = dictData + "{dictDetailCode:'" + code + "',"
					+ "dictDetailName:'" + name + "'},";
				}
			}
		} else if (lean && cardlist.length == 1) {
			dictData = dictData + "{dictDetailCode:'" + code + "',"
					+ "dictDetailName:'" + name + "',selected:" + true + "}]";
		} else {
			dictData = dictData + "{dictDetailCode:'" + code + "',"
					+ "dictDetailName:'" + name + "',selected:" + true + "}]";
		}
	}
	if (cardlist.length > 0) {
		// 自定义下拉框
		dictData = eval('(' + dictData + ')');
		$("#" + obj).combobox(
				{
					valueField : 'dictDetailCode',
					textField : 'dictDetailName',
					data : dictData,
					onSelect : function(row) {
						for (var i = 0; i < cardlist.length; i++) {
							if (cardlist[i].productCode == row.dictDetailCode) {
								if (lean) {
									cardFaceAndYear(cardlist[i].productFace,
											cardlist[i].productFaceName,
											"cardFaceCode",i,"1",cardFace,yearFree,cardlist[i].productCode,cardlist[i].productFaceDefault);
									cardFaceAndYear(
											cardlist[i].yearFeeDerateType,
											cardlist[i].yearFeeDerateTypeName,
											"yearFeeDerateType",i,"2",cardFace,yearFree,cardlist[i].productCode,cardlist[i].yearFeeDerateTypeDefault);
								} else {
									cardFaceAndYear(cardlist[i].productFace,
											cardlist[i].productFaceName,
											"cardFaceCode_fk",i,"1",cardFace,yearFree,cardlist[i].productCode,cardlist[i].productFaceDefault);
									cardFaceAndYear(
											cardlist[i].yearFeeDerateType,
											cardlist[i].yearFeeDerateTypeName,
											"yearFeeDerateType_fk",i,"2",cardFace,yearFree,cardlist[i].productCode,cardlist[i].yearFeeDerateTypeDefault);
								}
							}
						}
					}
				});
	}
}
/**
 * 卡板面和年费类型特殊下拉框生成代码
 * @param productFace
 */
function cardFaceAndYear(productFace,productFaceName,obj,num,flag,cardFace,yearFree,finalCardProduct,_default){
	var cardlist = productFace.split(",");
	var cardlistName = productFaceName.split(",");
	var selectedFlag = false;
	var dictData="[";
	for(var i = 0;i<cardlist.length;i++){
		var code = cardlist[i];
		var name = cardlistName[i];
		if(num == 0 && ((flag=="1" && cardFace!=null && cardFace!="")||
				(flag=="2" && yearFree!=null && yearFree!=""))){
			if(i!=cardlist.length-1 && ((flag=="1" && cardFace == code)||(flag=="2" && yearFree==code))){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"',selected:"+true+"},";
				selectedFlag = true;
			}else if(i==cardlist.length-1 && ((flag=="1" && cardFace == code)||(flag=="2" && yearFree==code))){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"',selected:"+true+"}]";
			}else if(i==cardlist.length-1){
				if(selectedFlag){
					dictData = dictData+"{dictDetailCode:'"+code+"',"+
					"dictDetailName:'"+name+"'}]";
				}else{
					dictData = dictData+"{dictDetailCode:'"+code+"',"+
					"dictDetailName:'"+name+"',selected:"+true+"}]";
				}
			}else{
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"'},";
			}
		}else{
			if(i < cardlist.length-1){
				if(_default != null && _default != ""){
					if(code == _default){
						dictData = dictData+"{dictDetailCode:'"+code+"',"+
						"dictDetailName:'"+name+"',selected:"+true+"},";
						selectedFlag = true;
					}else{
						dictData = dictData+"{dictDetailCode:'"+code+"',"+
						"dictDetailName:'"+name+"'},";
					}
				}else{
					dictData = dictData+"{dictDetailCode:'"+code+"',"+
					"dictDetailName:'"+name+"',selected:"+true+"},";
					selectedFlag = true;
				}
			}else if(i==cardlist.length-1){
				if(selectedFlag){
					dictData = dictData+"{dictDetailCode:'"+code+"',"+
					"dictDetailName:'"+name+"'}]";
				}else{
					dictData = dictData+"{dictDetailCode:'"+code+"',"+
					"dictDetailName:'"+name+"',selected:"+true+"}]";
				}
			}	
		}
	}
	//自定义下拉框
	if(cardlist.length>0){
		dictData = eval('('+dictData+')');
		$("#"+obj).combobox({    
		    valueField:'dictDetailCode',    
		    textField:'dictDetailName',
		    data:dictData
		});
	}
	if(isAgreeDegrade=="1"){
		var appCard =  $("#applyCard").val();
		//若当前卡产品为0020或银联普卡（）并且是可以降级的标准卡，则其默认的年费类型为14
		if(((appCard=="0025" && finalCardProduct=="0020")||(appCard=="0041" && finalCardProduct=="0040")) && flag=="2"){
			if(obj=="yearFeeDerateType_fk"){
				$("#yearFeeDerateType_fk").combobox('setValue',"14");
			}else{
				$("#yearFeeDerateType").combobox('setValue',"14");
			}
		}
		if(flag=="1"){
			if(appCard=="0088" && finalCardProduct=="0087"){
				if(obj=="cardFaceCode_fk"){
					$("#cardFaceCode_fk").combobox('setValue',cardFace);
				}else{
					$("#cardFaceCode").combobox('setValue',cardFace);
				}
			}
			//若当前卡产品为0073并且是 可以降级的标准卡，则其默认的卡版面为SM01
			if(appCard=="0092" && finalCardProduct=="0073"){
				if(obj=="cardFaceCode_fk"){
					$("#cardFaceCode_fk").combobox('setValue',"SM01");
				}else{
					$("#cardFaceCode").combobox('setValue',"SM01");
				}
			}
		}
	}
}
/**
 * 不查询字典，查询业务维护表，下拉框显示
 * @param cardlist
 * @param obj
 * @param lean
 */
function dictListPolicyList(cardlist,obj,noselect){
	var dictData="[{dictDetailCode:'',dictDetailName:'---请选择---'},";
	for(var i = 0;i<cardlist.length;i++){
		var code = cardlist[i].policyCode;
		var name = code +"~"+cardlist[i].policyName;
		if(noselect!=null&&noselect==code){
			if(i < cardlist.length-1){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"',selected:"+true+"},";
			}else if(i==cardlist.length-1){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"',selected:"+true+"}]";
			}	
		}else{
			if(i < cardlist.length-1){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"'},";
			}else if(i==cardlist.length-1){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"'}]";
			}	
		}
	}
	//自定义下拉框
	if(cardlist.length>0){
		dictData = eval('('+dictData+')');
		$("#"+obj).combobox({    
		    valueField:'dictDetailCode',    
		    textField:'dictDetailName',
		    data:dictData
		});
	}
}
function dictListCommon(cardlist,obj,noselect){
	var dictData="[{dictDetailCode:'',dictDetailName:'---请选择---'},";
	for(var i = 0;i<cardlist.length;i++){
		var code = cardlist[i].reasonCode;
		var name = code +"~"+cardlist[i].reasonDesc;
		if(noselect!=null&&noselect==code){
			if(i < cardlist.length-1){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"',selected:"+true+"},";
			}else if(i==cardlist.length-1){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"',selected:"+true+"}]";
			}	
		}else{
			if(i < cardlist.length-1){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"'},";
			}else if(i==cardlist.length-1){
				dictData = dictData+"{dictDetailCode:'"+code+"',"+
				"dictDetailName:'"+name+"'}]";
			}	
		}
	}
	//自定义下拉框
	if(cardlist.length>0){
		dictData = eval('('+dictData+')');
		$("#"+obj).combobox({    
		    valueField:'dictDetailCode',    
		    textField:'dictDetailName',
		    data:dictData
		});
	}
}

function showDiv(obj){
		if(obj=='radio_pizhun'){
			$("#radio_approveResult").val("1");
			$("#pizhun").show();
			$("#jujue").hide();
		}else if(obj=='radio_jujue'){
			$("#radio_approveResult").val("0");
			$("#jujue").show();
			$("#pizhun").hide();
		}
}
function showDiv_fk(obj){
	if(obj=='radio_pizhun_fk'){
		$("#radio_approveResult_fk").val("1");
		$("#pizhun_fk").attr("style","display:true");
		$("#jujue_fk").attr("style","display:none");
	}else if(obj=='radio_jujue_fk'){
		$("#radio_approveResult_fk").val("0");
		$("#jujue_fk").attr("style","display:true");
		$("#pizhun_fk").attr("style","display:none");
	}
}
//展示窗口
function showWin(obj) {
	var winObj = $("#" + obj);
	winObj.window({
		closed : false
	});
}
function GetQueryString(name){
	 var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
	 var r = window.location.search.substr(1).match(reg);
	 if(r!=null) return unescape(r[2]);
	 return null;
}