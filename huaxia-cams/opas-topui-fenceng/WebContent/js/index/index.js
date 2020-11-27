$(function(){
//	 $(".tabs li").on('contextmenu',function(e){
//	        var title =$(this).text();
//	        $('#addtab').tabs('select',title);
//	        
//	        if(title!='首页'){
//	        	//显示自定义菜单
//		        $('#menu').menu('show', {
//		            left: e.pageX,
//		            top: e.pageY
//		        });	
//	        }
//	        return false;//屏蔽浏览器右键
//	    });
	$('#addtab').tabs({
		onContextMenu:function(e,title,index){
			e.preventDefault();
	        var title =$(this).text();
	        $('#addtab').tabs('select',title);
	        
	        if(title!='首页'){
		        $('#menu').menu('show', {
		            left: e.pageX,
		            top: e.pageY
		        });	
	        }
		}
	});

	  // 刷新
	    $("#m-refresh").click(function(){
	        var currTab = $('#addtab').tabs('getSelected');    			//获取当前选中的tab
	        var text =  currTab.panel('options').title;  	   			//标题
	        var url  =$(currTab.panel('options').content).attr('src'); //该tab iframe的src属性

	        $('#addtab').tabs('update',{
	            tab:currTab,
	            options:{
	                content: addTab(text,url)
	            }
	        })
	    });
	    //关闭所有
	    $("#m-closeall").click(function(){
	        $(".tabs li").each(function(i, n){
	            var title = $(n).text();
	            if(title!='首页'){
	            	$('#addtab').tabs('close',title);  
	            }
	        });
	    });
	    //关闭其它
	    $("#m-closeother").click(function(){
	        var currTab = $('#addtab').tabs('getSelected');
	        var currtabTitle = currTab.panel('options').title;    
	        
	        $(".tabs li").each(function(i, n){
	            var title = $(n).text();
	            if(currtabTitle != title){
		            if(title!='首页'){
		            	$('#addtab').tabs('close',title);  
		            }
	            }
	        });
	    });
	    //关闭
	    $("#m-close").click(function(){
	        var currTab = $('#addtab').tabs('getSelected');
	        var currtabTitle = currTab.panel('options').title;  
	        
	        $('#addtab').tabs('close', currtabTitle);
	    });
})

function closeTab(tabName){
	$('#addtab').tabs('close', tabName);
}

function addTab(text, url) {
	if ($("#addtab").tabs('exists', text)) {
		$("#addtab").tabs('select', text);// 有则选中
	} else {// 没有则添加
		if (url != null) {
			$("#addtab")
					.tabs(
							'add',
							{
								closable : true,
								title : text,
								content : '<iframe src="/opas-topui/html/'+ url+ '" style="border:0;width:100%;height:100%;"></iframe>'
							});
		}
	}
}
/*
 * 	<div id="menu" class="easyui-menu" style="width:120px;">
	
 	    <div id="m-refresh">刷新</div>
	    <div class="menu-sep"></div> 
	    
	    <div id="m-close">关闭</div>
	    <div class="menu-sep"></div>
	    
	    <div id="m-closeother">关闭其它</div>
	    <div class="menu-sep"></div>
	    
	    <div id="m-closeall">全部关闭</div>
	    <div class="menu-sep"></div>
	</div>
 */
function exit(){
	window.location="/opas-topui";
}