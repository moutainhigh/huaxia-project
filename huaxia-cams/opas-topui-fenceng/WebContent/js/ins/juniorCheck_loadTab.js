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
				var src=$("#universeCheatIframe").attr("src");
				var readOnly=$.query.get("readOnly");
				if(src==''){
					if(readOnly=="true"){
						$("#universeCheatIframe").attr("src","../../html/common/universeCheatTab.html?pageName=junior&isReview=false&readOnly="+readOnly+"");	
					}else{
						$("#universeCheatIframe").attr("src","../../html/common/universeCheatTab.html?pageName=junior&isReview=false");
					}
					
				}
			} else if (title=='公司白名单对比件'){
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
	if(nodeNo=="n13010_N13010RUQ001"){
		title="TT类申请";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ002"){
		title="TMXC";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ003"){
		title="经销商卡和私营业主卡";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ004"){
		title="私行卡";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ005"){
		title="网贷项目";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ006"){
		title="外部手工提报";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ007"){
		title="大额分期";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ008"){
		title="移动员工卡";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ009"){
		title="B27规则";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ010"){
		title="批量申请";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ011"){
		title="重要客户";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ012"){
		title="B07规则";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ013"){
		title="H流程";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ014"){
		title="报警申请";
		return title;
	}
	if(nodeNo=="n13010_N13010RUQ015"){
		title="未报警申请";
		return title;
	}
}