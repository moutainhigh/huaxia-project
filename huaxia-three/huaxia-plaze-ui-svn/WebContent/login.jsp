<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第三方查询模块</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap/bootstrap.min.css">
<style type="text/css">
body {
	background: url(${basePath}/images/login/bg.jpg) no-repeat center top #050f47;
	padding: 0;
	margin: 0;
}

h1 {
	font: normal normal bold 30px/1.2em "黑体";
	text-align: center;
	margin-bottom: 20px;
}

.btn {
	padding: 10px 30px;
}

.wrapper-login {
	border: 1px solid #fff;
}

.box {
	color: #FFFFFF;
	background-color: rgba(0, 0, 0, 0.3);
	filter: alpha(opacity = 30);
	position: static;
	width: 600px;
	height: 360px;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: auto;
	padding: 30px;;
	border-color: #FFFFFF;
	border-style: solid;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	border-radius: 20px;
	-moz-box-shadow: 5px 5px 10px #333333;
	-webkit-box-shadow: 5px 5px 10px #333333;
	box-shadow: 5px 5px 10px #333333;
	width: 600px;
}

.msg-box {
	text-align: center;
}

.tips {
	color: #FF0000;
}

.form-control {
	display: inline;
	width: 270px;
}
</style>
<script type="text/javascript" src="${basePath}/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/base64.js"></script>
<script type="text/javascript" src="${basePath}/js/sha256.js"></script>
</head>
<body>
	<div id="wrapper" class="wrapper-login">
		<div class="box">
			<h1>第三方查询模块</h1>
			<form name="frmLogin" id="frmLogin" class="form-horizontal"
				method="post">
				<div class="form-group">
					<label for="account" class="col-md-2 control-label">账号</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="account"
							name="account" placeholder="请输入账号" style="width:410px;" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-2 control-label">密码</label>
					<div class="col-md-10">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="请输入密码" style="width:410px;"	 />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-2 control-label">验证码</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="validCode" name="validCode" placeholder="请输入校验码" style="width:270px;"/>&nbsp;
						<input id="btnValidCode" type="button" class="btn btn-default" value="获取验证码" style="width:132px;height:34px;line-height:15px;" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button id="btnReset" type="button" class="btn btn-default">重置</button>
						&nbsp;&nbsp;
						<button id="btnLogin" type="button" class="btn btn-primary">登录</button>
					</div>
				</div>
				<div class="form-group">
					<div id="msgbox" class="col-md-12 msg-box">
						<span id="tips" class="tips"></span>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	function fnTips(msg) {
		$('#tips').html(msg);
		setTimeout(function() {
			fnClearTips();
		}, 10000);
	}
	
	function fnClearTips() {
		$('#tips').html('');
	}
	
	function fnReset() {
		$('#account').val('');
		$('#password').val('');
	}

	$(function() {
		// 默认焦点&密码输入框
		$('#account').focus();
		
		window.history.pushState(null, null, document.URL);
		window.addEventListener('popstate', function() {
			window.history.pushState(null, null, document.URL);
		});
		
		fnReset();
		
		//获取验证码
		$("#btnValidCode").click(function() {
			var account = $('#account').val();
			if (account == '' || account == undefined) {
				$('#account').focus();
				fnTips('请输入登录账号!');
				return;
			} else {
				fnClearTips();
			}
			$('#validCode').focus();
			
			$.ajax({
				url : "${basePath}/verify.do",
				type : 'post',
				data : {
					'account' : account,
				},
				dataType : "json",
				success : function(response, status) {
					// 短信验证码
					if (!response.success) {
						fnTips(response.message);
					}
				},
				error : function(error, status) {
					alert(error + '=' + status);
				}
			});
			reckon();
		});	
        // 验证码计时器
		function reckon() {
    	   var intervalObj;
    	   var count=60;
    	   var curCount;
    	   curCount=count;
			$("#btnValidCode").attr("disabled",true).val(curCount+'s重新获取');
			intervalObj=window.setInterval(setRemainTime,1000);
			function setRemainTime(){
				if(curCount==1){
					window.clearInterval(intervalObj);
					$("#btnValidCode").attr("disabled",false);
					$("#btnValidCode").val('获取验证码')
				}else{
					curCount--;
					$("#btnValidCode").val(curCount+'s重新获取')
				}
			}
		}
	
		$("#btnLogin").click(function() {
			var account = $('#account').val();
			var password = $('#password').val();
			var validCode = $('#validCode').val();
			if (account == '' || account == undefined) {
				$('#account').focus();
				fnTips('请输入登录账号!');
				return;
			} else {
				fnClearTips();
			}
			
			if (password == '' || password == undefined) {
				$('#password').focus();
				fnTips('请输入登录密码!');
				return;
			} else {
				fnClearTips();
			}
			
			if (validCode == '' || validCode == undefined) {
				$('#validCode').focus();
				fnTips('请输入验证码!');
				return;
			} else {
				fnClearTips();
			}
			
			$.ajax({
				url : "${basePath}/login.do",
				type : 'post',
				data : {
					'account' : account,
					'password' : sha256_digest(password),
					'validCode' : validCode
				},
				dataType : "json",
				success : function(response, status) {
					if (response.result) {
						if (response.target == undefined) {
							window.location.href = '${basePath}/default/index.do?account=' + account;
						} else if (response.target == 'change') {
							window.location.href = '${basePath}/' + response.target + '.do?account=' + account;	
						} else {
							window.location.href = "${basePath}/login.jsp";
						}
					} else {
						fnTips(response.message);
						if (response.code == '010111') { // 已登录
							$('#account').attr('readonly', 'readonly');
							$('#password').attr('readonly', 'readonly');
							$('#btnLogin').attr('disabled', 'disabled');
							$('#btnReset').attr('disabled', 'disabled');
							setTimeout(function() {
								window.location.href = "${basePath}/default/index.do?account=" + account;
							}, 3000);
						}
					}
				},
				error : function(error, status) {
					alert(error + '=' + status);
				}
			});
			
			$('#btnLogin').attr('disabled', true);
			setTimeout(function() {
				$('#btnLogin').attr('disabled', false);		
			}, 3000);
		});
		
		$('#btnReset').on('click', function() {
			fnReset();
			$('#account').focus();
		});

		$('body').keydown(function(e) {
			if (e.which == 13) {
				$('#btnLogin').click();
			}
		});
	});
</script>
</html>