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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/sm.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/sm-extend.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/app2.css">
   		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/module.css">
		<script src="${pageContext.request.contextPath}/js/mobile/zepto.min.js"></script>
	</head>

	<body>
		<div id="tldl-dish-detail" class="page tldl-dish-detail">
			<header class="bar bar-nav">
				<a class="button button-link button-nav pull-left external" href="${pageContext.request.contextPath}/market!pageJump.action?pageName=marketIndex">
					<span class="icon icon-left"></span> 返回
				</a>
				<h1 class="title">商品详情</h1>
			</header>

			<nav class="bar bar-tab">
				<span class="tab-item">
		  			<a class="button button-fill button-warning external" id="directorder" href="javascript:void(0)">立即购买</a>
		  		</span>
				<span class="tab-item">
		  			<a class="button button-fill button-danger external" id="add_cart" href="javascript:void(0);">加入购物车</a>
		  		</span>
				<span class="tab-item">
					<a href="${pageContext.request.contextPath}/market!pageJump.action?pageName=shopCar" class="icon icon-cart color-danger external">
						<i id="shopcar-category-num">0</i>
					</a>
				</span>
			</nav>

			<div class="cart-pop" id="cart-success" style=" display: none; ">
				<div class="ico-succ">
					<span class="att-succ">添加成功！</span>
					<span class="cart-succ">商品已成功加入购物车</span>
				</div>
			</div>

			<div class="content">

			</div>
		</div>
		<script type="text/template" id="dish_detail_tpl">
			{@if productBean}
			<div class="goods-detail">
				<div class="new-p-re">
					<div class="detail-img">
						<div class="imgbox">
							<img src="^{productBean.bigPic|imgServerUrl}" alt="图片加载失败..." style="width: 320px;height: 320px;" />
						</div>
						<div class="detail-price"> <span id="price" class="p-price">¥^{productBean.price}/斤  </span>
							<a id="attention" class="btn-sc J_ping"></a>
						</div>
					</div>
				</div>
				<div class="goodsinfo">
					<h1 id="title" class="detail-title"> ^{productBean.name} </h1>
					<p id="promotionInfo" class="subtitle">^^{productBean.memoCpjs} </p>
				</div>
			</div>

			<div class="list-block">
				<ul>
					{@if productBean.brand}
					<li>
						<div class="item-content">
							<div class="item-inner">
								<div class="item-title label font75">品牌:</div>
								<div class="item-input font7 color-gray2 text-right">
									^{productBean.brand}
								</div>
							</div>
						</div>
					</li>
					{@/if}
					{@if productBean.shelfLife}
					<li>
						<div class="item-content">
							<div class="item-inner">
								<div class="item-title label font75">保质期:</div>
								<div class="item-input font7 color-gray2 text-right">
									^{productBean.shelfLife}
								</div>
							</div>
						</div>
					</li>
					{@/if}
					{@if productBean.spec}
					<li>
						<div class="item-content">
							<div class="item-inner">
								<div class="item-title label font75">规格:</div>
								<div class="item-input font7 color-gray2 text-right">
									^{productBean.spec}
								</div>
							</div>
						</div>
					</li>
					{@/if}
				</ul>
			</div>
			{@/if}
		</script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm-extend.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/juicer-min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/app.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module-api.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module/details.js"></script>
	</body>

</html>