<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> --%>
<%@ page language="java" import="com.mobile.constants.WxAppConstants" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title><%= WxAppConstants.APP_NAME %></title>
		<meta name="viewport" content="initial-scale=1, maximum-scale=1">
		<link rel="shortcut icon" href="/favicon.ico">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="css/sm.css">
		<link rel="stylesheet" href="css/sm-extend.css">
		<link rel="stylesheet" href="css/app.css">
    	<link rel="stylesheet" href="css/module.css">
		<script src="js/zepto.js"></script>
	</head>

	<body>
		<div id="tldl-shop-car" class="page tldl-shop-car">
			<header class="bar bar-nav">
				<a class="button button-link button-nav pull-left external" href="index.html">
					<span class="icon icon-left"></span> 返回
				</a>
				<h1 class="title">购物车</h1>
			</header>
			
			<div class="content">
				
				<div id="emptyCart" style="display:block">
				    <div class="shp-cart-empty">
				        <div class="empty-sign cart-empty-pic"></div>
				        <div class="empty-warning-text">
				            购物车空空如也，快去购物吧<br>
				            或者登录查看您的购物车
				        </div>
				        <div class="empty-btn-wrapper">
				            <a href="index.html" class="external btn-jd-darkred btn-large">去逛逛</a>
                            <a href="javascript:void(0)" class="btn-jd-gray btn-large">去登录</a>
	                    </div>
				    </div>
				</div>
				
				
			</div>
		</div>
		<script src="js/sm.js"></script>
		<script src="js/sm-extend.js"></script>
		<script src="js/module/shopcar.js"></script>
	</body>

</html>