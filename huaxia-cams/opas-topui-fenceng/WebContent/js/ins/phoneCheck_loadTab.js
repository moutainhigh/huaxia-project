$(function(){
	$('#logwindow_curr').dialog({  
	    modal:true
	}); 
	$('#logwindow_curr').dialog("close");
	
	$('#logwindow_copa').dialog({  
	    modal:true
	}); 
	$('#logwindow_copa').dialog("close");
	
	$('#infoupdateWindow').dialog({  
	    modal:true
	}); 
    $('#infoupdateWindow').dialog("close");
    
	$('#imgcreInfoTabs').tabs({
		onSelect:function(title,index){
			if(title=='征信信息'){
				var src=$("#creditIframe").attr("src");
				if(src==''){
					$("#creditIframe").attr("src","../../html/common/creditTab.html");
				}
			}
		}
	});
	$('#juniorCheckTabs').tabs({
		onSelect:function(title,index){
			if(title=='初审核实选项'){
				var src=$("#checkOptionIframe").attr("src");
				if(src==""){
					$("#checkOptionIframe").attr("src","../../html/common/checkOption.html");
				}
			}
			else if(title=='电话环节'){
				var src=$("#phoneTabIframe").attr("src");
				if(src==""){
					$("#phoneTabIframe").attr("src","../../html/common/phoneTab.html?phoneType=0");
				}
			}
			else if(title=='电核拨叫明细'){
				var src=$("#phoneDetailIframe_phoneCheck").attr("src");
				if(src==""){
					$("#phoneDetailIframe_phoneCheck").attr("src","../../html/common/phoneDetail.html?phoneType=0,2,4");
				}else{
					document.getElementById("phoneDetailIframe_phoneCheck").contentWindow.reloadData();
				}
				
			} 
			else if (title=='公司白名单对比件'){
				var v_compWhiteSimIframe = $("#compWhiteSimIframe");
				if(v_compWhiteSimIframe.attr("src")==''){
					v_compWhiteSimIframe.attr("src","../../html/workFlow/bizCompWhiteSim.html");
				}
			}
		}
	});
	$('#multiCheatTabs').tabs({
		onSelect:function(title,index){
			if(title=='App申请信息'){
				var src=$("#conApp").attr("src");
				if(src==''){
					document.getElementById("conApp").src = "../../html/common/cheatTab.html?cheat=1";
				}else{
					document.getElementById("conApp").contentWindow.refreshDo();
				}
			}else 
			if(title=='Applicant主附申请人'){
				var src=$("#cheatTab2Iframe").attr("src");
				if(src==''){
					document.getElementById("cheatTab2Iframe").src = "../../html/common/cheatTab.html?cheat=2";
				}else{
					document.getElementById("cheatTab2Iframe").contentWindow.refreshDo();
				}
			}else 
			if(title=='Contact联系人'){
				var src=$("#cheatTab3Iframe").attr("src");
				if(src==''){
					document.getElementById("cheatTab3Iframe").src = "../../html/common/cheatTab.html?cheat=3";
				}else{
					document.getElementById("cheatTab3Iframe").contentWindow.refreshDo();
				}
			}else 
			if(title=='DR/Spouse直系亲属/配偶'){
				var src=$("#cheatTab4Iframe").attr("src");
				if(src==''){
					document.getElementById("cheatTab4Iframe").src = "../../html/common/cheatTab.html?cheat=4";
				}else{
					document.getElementById("cheatTab4Iframe").contentWindow.refreshDo();
				}
			}else 
			if(title=='Sales推广/推荐人'){
				var src=$("#cheatTab5Iframe").attr("src");
				if(src==''){
					document.getElementById("cheatTab5Iframe").src = "../../html/common/cheatTab.html?cheat=5";
				}else{
					document.getElementById("cheatTab5Iframe").contentWindow.refreshDo();
				}
			}else 
			if(title=='移动联名卡'){
				var src=$("#cheatTab6Iframe").attr("src");
				if(src==''){
					document.getElementById("cheatTab6Iframe").src = "../../html/common/cheatTab.html?cheat=6";
				}else{
					document.getElementById("cheatTab6Iframe").contentWindow.refreshDo();
				}
			}
		}
	});
});

function nodeNoTransTitle(nodeNo){
	var title="";
	if(nodeNo=="n13020_N13020RUQ015"){
		title="方案式批量申请";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ016"){
		title="员工卡申请";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ001"){
		title="TT类申请";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ002"){
		title="TMXC";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ003"){
		title="经销商卡和私营业主卡";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ004"){
		title="私行卡";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ005"){
		title="网贷项目";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ006"){
		title="手工提报";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ007"){
		title="大额分期";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ008"){
		title="移动员工卡";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ009"){
		title="B27规则";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ010"){
		title="批量申请";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ011"){
		title="重要客户";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ012"){
		title="B07规则";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ013"){
		title="H流程";
		return title;
	}
	if(nodeNo=="n13020_N13020RUQ014"){
		title="一般申请";
		return title;
	}
}
