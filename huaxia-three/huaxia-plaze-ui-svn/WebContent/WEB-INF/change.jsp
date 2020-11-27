<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第三方查询模块</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/bootstrap/bootstrap.min.css">
<style type="text/css">
body {
	background: url(${basePath}/images/login/bg.jpg) no-repeat center top #050f47;
	padding: 0;
	margin: 0;
}

h1 {
	font: normal normal bold 30px/1.2em "黑体";
	text-align: center;
	margin-top: 0px;
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
	height: 405px;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: auto;
	padding: 50px;;
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
</style>
<script type="text/javascript" src="${basePath}/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/base64.js"></script>
</head>
<body>
	<div id="wrapper" class="wrapper-change">
		<div class="box">
			<form name="frmChange" id="frmChange" class="form-horizontal"
				method="post">
				<h1>第三方平台登录密码修改</h1>
				<div class="form-group">
					<label for="account" class="col-md-2 control-label">账户</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="account"
							name="account" value="${account}" readonly="readonly" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-2 control-label">旧密码</label>
					<div class="col-md-10">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="请输入密码" />
					</div>
				</div>
				<div class="form-group">
					<label for="newpassword" class="col-md-2 control-label">新密码</label>
					<div class="col-md-10">
						<input type="password" class="form-control" id="newpassword"
							name="newpassword" placeholder="请输入密码" />
					</div>
				</div>
				<div class="form-group">
					<label for="confirmpassword" class="col-md-2 control-label">确认密码</label>
					<div class="col-md-10">
						<input type="password" class="form-control" id="confirmpassword"
							name="confirmpassword" placeholder="请输入密码" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button id="btnReset" type="button" class="btn btn-default">重置</button>
						&nbsp;&nbsp;
						<button id="btnModify" type="button" class="btn btn-primary">修改</button>
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
		}, 3000);
	}
	
	function fnClearTips() {
		$('#tips').html('');
	}
	
	function fnReset() {
		$('#password').val('');
		$('#newpassword').val('');
		$('#confirmpassword').val('');
	}

	$(function() {
		// 默认焦点&密码输入框
		$('#password').focus();
		
		$("#btnModify").click(function() {
			var account = $("#account").val();
			var password1 = $("#password").val();
			var newpassword = $("#newpassword").val();
			var confirmpassword = $("#confirmpassword").val();
			if (password1 == '' || password1 == undefined) {
				$('#password1').focus();
				fnTips('请输入旧密码');
				return;
			}

			if (newpassword == '' || newpassword == undefined) {
				$('#newpassword').focus();
				fnTips('请输入新密码');
				return;
			}
			
			if (confirmpassword == '' || confirmpassword == undefined) {
				$('#confirmpassword').focus();
				fnTips('请输入确认密码');
				return;
			}

			if (newpassword != confirmpassword) {
				$('#newpassword').val('').focus();
				$('#confirmpassword').val('');
				fnTips('新密码与确认密码不一致');
				return;
			}
			//密码格式校验
			var regex = /^.{8,20}$/;
			if(!regex.test(newpassword)){
				$('#newpassword').val('').focus();
				$('#confirmpassword').val('');
				fnTips('密码位数应当在8-20位。');
				return;
            }
			var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,20}$/;
			if(!regex.test(newpassword)){
				$('#newpassword').val('').focus();
				$('#confirmpassword').val('');
				fnTips('密码应当包含大小写字母、数字、特殊字符。');
				return;
            }
			$.ajax({
				url : "${basePath}/modify.do",
				type : 'post',
				timeout : 120000,
				data : {
					'account' : account,
					'password' : sha256_digest(password1),
					'newPassword' : sha256_digest(newpassword),
					'confirmPassword' : sha256_digest(confirmpassword)
				},
				dataType : "json",
				success : function(response, status) {
					if (response.result) {
						fnTips('用户密码' + response.message + '， 重新登录后进行操作。');
						setTimeout(function() {
							fnClearTips();
							window.location.href = "${basePath}/login.jsp";
						}, 3000)
					} else {
						fnTips(response.message);
					}
				},
				error : function(error, status) {
					alert(error + '=' + status);
				}
			});

			$('#newpassword').on('blur', function() {
				var oldpassword = $('#password').val();
				var newpassword = $('#newpassword').val();
				if (oldpassword == newpassword) {
					$('#newpassword').val('').focus();
					fnTips('新密码不能与旧密码相同');
					setTimeout(function() {
						fnClearTips();
					}, 3000);
				}
			});
		});
		
		$('#btnReset').on('click', function() {
			fnReset();
			$('#account').focus();
		});

		$('body').keydown(function(e) {
			if (e.which == 13) {
				$('#btnModify').click();
			}
		});
	});
</script>
</html>