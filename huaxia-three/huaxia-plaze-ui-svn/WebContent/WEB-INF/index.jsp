<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>第三方查询模块</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/index/index.css"></link>
<link rel="stylesheet" type="text/css" href="${basePath}/css/index/menu.css"></link> 
</head>
<body style="background-color:#FFFFFF;">
	<div id="huaxia" class="easyui-layout" data-options="fit:true" style="margin:0px;padding:0px;">
		<!-- NORTH -->
		<div data-options="region:'north'" style="width:100%;height:62px;">
			<div id="header">
				<h1>第三方查询模块</h1>
				<div class="global-user-area">登录账户&nbsp;[&nbsp;${loginUser.account}&nbsp;]</div>
				<div class="global-link-area">
					<ul>
						<li><a href=javascript:doShowModify();>修改密码 </a></li>
					</ul>
				</div>
				<form id="frmLogout" method="post" action="${basePath}/logout.do">
					<input type="hidden" name="account" value="${loginUser.account}" />
					<input type="submit" id="btnExit" class="btn-exit" title="退出系统" />
				</form>
			</div>
		</div>
		<!-- WEST -->
		<div data-options="region:'west',title:'&nbsp;'" style="width:240px;">
			<div id="menu_area" class="menu-tree"></div>
		</div>
		<!-- CENTER -->
		<div data-options="region:'center',title:'&nbsp;',split:false,noheader:true,border:false">
			<div class="easyui-tabs" id="contents" style="height:100%"></div>
		</div>
	</div>
	
	<input type="hidden" id="basePath" value="${basePath}" />

	<!-- [区域] 修改密码窗口 -->
	<div id="win_change_password" class="easyui-window" title="修改密码"
		style="width: 600px; height: 400px; padding: 70px;"
		data-options="iconCls:'icon-save',modal:true,closed:true">
		<form id="frmWindow" class="form-horizontal">
			<div class="form-group">
				<label for="password1" class="col-md-2 control-label">旧密码</label>
				<div class="col-md-10">
					<input type="password" class="form-control" id="password1"
						name="password1" placeholder="请输入旧密码" />
				</div>
			</div>
			<div class="form-group">
				<label for="password2" class="col-md-2 control-label">新密码</label>
				<div class="col-md-10">
					<input type="password" class="form-control" id="password2"
						name="password2" placeholder="请输入新密码" />
				</div>
			</div>
			<div class="form-group">
				<label for="password3" class="col-md-2 control-label">确认密码</label>
				<div class="col-md-10">
					<input type="password" class="form-control" id="password3"
						name="password3" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-10">
					<button type="button" class="btn btn-default"
						onclick="fnCloseWindow('win_change_password','frmWindow');">关闭窗口</button>
					&nbsp;&nbsp;
					<button type="button" id="btnModifySave" class="btn btn-primary">修改提交</button>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		// 显示密码修改窗体
		function doShowModify() {
			$("#win_change_password").window({
				closed : false,
				collapsible : false,
				maximizable : false,
				minimizable : false,
				draggable : false
			});
		}
		// 关闭密码修改窗体
		function fnCloseWindow(oid, wid) {
			$("#" + oid).window("close");
			$("#" + wid).form("clear");
		}
		// 系统界面UI事件
		function fnUIEvent() {
			// [功能] 退出登录
			$('#btnExit').click(function() {
				if (confirm('确定退出系统?')) {
					$('#frmLogout').submit();
				}
				return false;
			});
			
			// [功能] 更新密码
			$('#btnModifySave').on('click', function() {
				var password1 = $('#password1').val();
				var password2 = $("#password2").val();
				var password3 = $("#password3").val();
				if (password1 == '') {
					$.messager.alert("操作提示", '请输入"旧密码"!', "error");
					return;
				}
				var password = '';
				if (password2 == '') {
					$.messager.alert("操作提示", '请输入"新密码"!', "error");
					return;
				}
				if (password1 == password2) {
					$.messager.alert("操作提示", '"新密码"不能与"旧密码"相同!', "error");
					return;
				}
				if (password3 == '') {
					$.messager.alert("操作提示", '请输入"确认密码", 与"新密码"保持一致!', "error");
					return;
				}
				if (password2 != password3) {
					$.messager.alert("操作提示", '"确认密码"与"新密码"不一致, 请重新输入!', "error");
					return;
				}
				if (password2 != undefined && password2 != '') {
					password = password2;
				}
				var regex = /^.{8,20}$/;
				if(!regex.test(password2)){
					$.messager.alert("操作提示", '密码位数应当在8-20位。', "error");
					return;
	            }
				var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,20}$/;
				if(!regex.test(password2)){
					$.messager.alert("操作提示", '密码应当包含大小写字母、数字、特殊字符。', "error");
					return;
	            }
		
				$.ajax({
					type : "post",
					url : "${basePath}/default/password/update.do",
					contentType : "application/json",
					data : JSON.stringify({
						'account' : "${loginUser.account}",
						'password1' : sha256_digest(password1),
						'password2' : sha256_digest(password2)
					}),
					datatype : "json",
					success : function(response, status) {
						response = JSON.parse(response);
						alert(response.message);
						if (response.result) {
							fnCloseWindow('win_change_password','frmWindow');
						}
					}
				});
			});
		}
		
		var relationInfo = {
			"loginInfo" : {
				"token" : "",
				"tokenDate" : ""
			},
			"roleInfo" : "",
			"orgInfo" : ""
		};
		var params = this.window.location.search;
		var uuid = params.substr(6, this.params.length + 1);
	
		$(function() {
			window.history.pushState(null, null, document.URL);
			window.addEventListener('popstate', function() {
				window.history.pushState(null, null, document.URL);
			});
			
			$.ajax({
				url : "${basePath}/default/usermenu/query.do",
				type : 'post',
				data : {
					'account' : "${loginUser.account}"
				},
				dataType : 'json',
				success : function(reponse) {
					fillData(reponse);
				}
			});
			
			fnUIEvent();
		});
	</script>
	<script type="text/javascript" src="${basePath}/js/menu.js"></script>
</body>
</html>