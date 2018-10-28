<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0, minimal-ui" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1">
		<link rel="shortcut icon" href="/favicon.ico">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>我要买菜</title>
		<link href="${pageContext.request.contextPath}/css/mobile/sm.min.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/mobile/sm-extend.min.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/mobile/app.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/mobile/module.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/mobile/site.css" rel="stylesheet" />

	</head>

	<body>
		<div class="page ycc-login">
			<header class="bar bar-nav bar-back-color common-header">
				<a class="button button-link button-nav pull-left open-panel">
					<icon class="icon icon-back"></icon>
				</a>
				<h1 class="title">登录</h1>
			</header>

			<div class="content">
				<div class="content-block sliding">
					<div class="logo">HiApp</div>
				</div>
				<div class="login-input-content">
					<table id="table1" class="font8">
						<tbody>
							<tr>
								<th><i class="icon icon-user"></i></th>
								<td colspan="2">
									<input id="usernmae" type="text" maxlength="13" placeholder="请输入用户名">
								</td>
							</tr>
							<tr>
								<th class="bor_t"><i class="icon icon-password"></i></th>
								<td colspan="2" class="bor_t">
									<input id="password" type="password" placeholder="密码">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="logindiv" class="login_txline">
					<a href="#" id="zhuce">注册点餐用户</a>
					<a class="float-right color-a font6" href="#">忘记密码</a>
				</div>

				<div class="btnPanel">
					<div class="col-50"><a id="denglu" class="button button-big button-fill button-success disabled">登陆</a></div>

				</div>

			</div>
		</div>
		<script src='${pageContext.request.contextPath}/js/mobile/zepto.min.js'></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm-extend.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/juicer-min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/ymc.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/app.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module-api.js"></script>
		<script>
			 //关闭自动初始化页面的功能
			$(function() {
				var fun = {
					hasContent: function() {
						if ($('#usernmae').val() != "" && $('#password').val() != "") {
							return true;
						}
						return false;
					}
				};
				$('#usernmae').focus();
				$('#usernmae,#password').on('keyup', function() {
					if (fun.hasContent()) {
						$('#denglu').removeClass("disabled");
					} else {
						if (!$('#denglu').hasClass("disabled")) {
							$('#denglu').addClass("disabled");
						}
					}
				})
				$('#denglu').on('click', function() {
					if (!$('#denglu').hasClass("disabled")) {
						var data = {
							userName: $('#usernmae').val(),
							password: $('#password').val()
						}
						ymc.ajax({
							url: app.api.login,
							type: 'POST',
							data: data,
							success: function(jsonData) {
								if (jsonData.isSuccess == true) {
									window.location.href = app.api.marketIndexPage;
								} else {
									alert(jsonData.message)
								}
							}
						});
					}
				})
				$('#zhuce').on('click', function() {
					window.location.href = app.api.registPage;
				})
			})
		</script>
	</body>

</html>