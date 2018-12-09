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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/sm.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/sm-extend.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/app2.css?version=1">
   		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/module.css">
		<script src="${pageContext.request.contextPath}/js/mobile/zepto.min.js"></script>
	</head>

	<body>
		<div id="tldl-shop-car" class="page tldl-shop-car">
			<header class="bar bar-nav">
				<a class="button button-link button-nav pull-left external" href="javascript:history.go(-2)">
					<span class="icon icon-left"></span> 返回
				</a>
				<h1 class="title">购物车</h1>
			</header>
			<div class="content">
			</div>
		</div>
		<script type="text/template" id="shop_car_tpl">
			<!--购物车为空-->
			<div id="emptyCart" {@if shoppingCartProductList.length>0} style="display:none" {@/if}>
				<div class="shp-cart-empty">
					<div class="empty-sign cart-empty-pic"></div>
					<div class="empty-warning-text">
						购物车空空如也，快去购物吧
						<br> 或者登录查看您的购物车
					</div>
					<div class="empty-btn-wrapper">
						<a href="${pageContext.request.contextPath}/market!pageJump.action?pageName=marketIndex" class="external btn-jd-darkred btn-large">去逛逛</a>
						<a href="${pageContext.request.contextPath}/market!pageJump.action?pageName=orderList" class="btn-jd-gray btn-large external">我的订单</a>
					</div>
				</div>
			</div>
			<!--购物车为空end-->
			{@if shoppingCartProductList.length>0}
 			{@if !sendDate} <div class="content-block-title" style="color: red; font-weight: 700;">亲，当前时间是不能下单的哦！</div> {@/if}
			<div class="list-block shopcar-list">
				<ul id="shop_car_list">
					<li class="item-content">
						<div class="item-inner">
							<div class="item-title font75">收货人:</div>
							<div class="item-after">
								<input type="text" placeholder="收货人" id="receiveMan" {@if data} value="^{data.receiveMan}" {@/if} />
							</div>
						</div>
					</li>
					<li class="item-content">
						<div class="item-inner">
							<div class="item-title font75">联系电话:</div>
							<div class="item-after">
								<input type="tel" placeholder="联系电话" id="phone" {@if data} value="^{data.phone}" {@/if} />
							</div>
						</div>
					</li>
					<li class="align-top">
						<div class="item-content">
							<div class="item-inner">
								<div class="item-title label font75">收货地址:</div>
								<div class="item-input">
									<textarea id="address" placeholder="收货地址" readonly >{@if data} ^{data.address} {@/if}</textarea>
								</div>
							</div>
						</div>
					</li>
					<li class="item-content">
						<div class="item-inner">
							<div class="item-title font75">配送时间:</div>
							<div class="item-after">
								<input type="text" placeholder="闪电送，及时达" readonly id='datetime-picker' {@if sendDate} value="^{sendDate}" {@/if}/>
							</div>
						</div>
					</li>
					<li class="align-top">
						<div class="item-content">
							<div class="item-inner">
								<div class="item-title label font75">备注:</div>
								<div class="item-input">
									<textarea id="remark" placeholder="可输入特殊要求"></textarea>
								</div>
							</div>
						</div>
					</li>
					{@each shoppingCartProductList as p}
					<li class="item-content product-list {@if !p.valid} valid {@/if}" style="position: relative;">
						{@if !p.valid} 
						<img src="^{'/images/mobile/yishixiao.png'|imgServerUrl}" class="valid-image">
						{@/if}
						<img src="^{'/images/mobile/shanchu.png'|imgServerUrl}"  class="del-image" data-id="^{p.productSerial}" data-productSpec="^{p.productSpec}" data-productName="^{p.productName}" >
						<div class="item-media"><img src="^{p.smallpic|imgServerUrl}" style="width: 1.5rem;height: 1.5rem;"></div>
						<div class="item-inner">
							<div class="stroy-name">^{p.productName} 
							<div style="font-size:.5rem">^{p.productSpec}</div>
							</div>
							<div class="item-after stroy-price">
								￥^{p.productPrice}
							</div>
							<div class="item-after text-right">
								<div class="mui-numbox" data-price="^{p.productPrice}" data-id="^{p.productSerial}" data-productSpec="^{p.productSpec}" data-count="^{p.number}">
									<button class="mui-btn mui-numbox-btn-minus" type="button">-</button>
									<input class="mui-numbox-input" type="tel" value="^{p.number}">
									<button class="mui-btn mui-numbox-btn-plus" type="button">+</button>
								</div>
							</div>
						</div>
					</li>
					{@/each}
					<li class="item-content">
						<div class="item-inner">
							<div class="item-title ">
								<span class="font75" >共</span> 
								<span class="color-red font85" style="line-height: 1.3rem;font-weight:500">￥<span id="count-price">^{price}</span>  </span>
							</div>
							<div class="item-after">
								<button id="choice-ok" class="button button-fill button-danger">立即下单</button>
							</div>
						</div>
					</li>
					<li>
						<div><span>&nbsp;&nbsp;</span></div>
						<div><span>{@if data} ^{data.priceLimitTips}{@/if}</span></div>
						<div style="color:red;">
							<span>订单一旦支付不能再修改，请核对后再支付！</span>
						</div>
					</li>
				</ul>
			</div>
			{@/if}
		</script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm-extend.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/juicer-min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/app.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module-api.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module/shopcar.js"></script>
	</body>

</html>