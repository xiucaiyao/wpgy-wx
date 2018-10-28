<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String massage = (String) request.getAttribute("massage");
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
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
	</head>

	<body style="background: #fff;">
		<div class="page ycc-success">
			<header class="bar bar-nav bar-back-color common-header">
				<a class="button button-link button-nav pull-left open-panel">
					<icon class="icon icon-back"></icon>
				</a>
				<h1 class="title">报错啦</h1>
			</header>
			<div class="content">
				<div id="main">
					<div class="fail">
						<p>
							<b>${massage}</b> 
						</p>
					</div>
				</div>
			</div>
		</div>
		<script src='${pageContext.request.contextPath}/js/mobile/zepto.min.js'></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm-extend.min.js"></script>
	</body>

</html>