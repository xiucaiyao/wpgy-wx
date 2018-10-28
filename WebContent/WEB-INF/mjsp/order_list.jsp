<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> --%>
<%@ page language="java" import="com.mobile.constants.WxAppConstants" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title><%=WxAppConstants.APP_NAME%></title>
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
		<div id="tldl-order-list" class="page tldl-order-list">
			<header class="bar bar-nav">
				<a class="button button-link button-nav pull-left external" href="${pageContext.request.contextPath}/market!pageJump.action?pageName=marketIndex">
					<span class="icon icon-left"></span> 返回
				</a>
				<h1 class="title">我的订单</h1>
			</header>
			<!--返回顶部-->
			<div onclick="$('.content').scrollTop(0);$('#indexToTop').hide()" class="bottom-to-top J_ping" id="indexToTop" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1); display: none;"> 
		  		<img src="${pageContext.request.contextPath}/images/mobile/scroll-to-top-icon.png" style="width: 100%;"> 
		  	</div>
			<div class="content infinite-scroll">
				  <div class="buttons-tab">
				    <a href="#order-all" class="tab-link active button">已支付</a>
				    <a href="#order-waiting-pay" class="tab-link button">未支付</a>
				    <a href="#order-waiting-delivery" class="tab-link button">收货</a>
				  </div>
				  <div class="content-block">
				    <div class="tabs">
		    			  <!--全部-->
				      <div id="order-all" data-status="已支付" class="tab active">
				          <div class="list-block media-list">
						    <ul class="order-list-ul">
						    </ul>
						  </div>
				      </div>
				      <!--待付款-->
				      <div id="order-waiting-pay" data-status="未支付"  class="tab">
				        <div class="list-block media-list">
						    <ul class="order-list-ul">
						      
						    </ul>
						  </div>
				      </div>
				      <!--待发货-->
				      <div id="order-waiting-delivery" data-status="收货"  class="tab">
				        <div class="list-block media-list">
						    <ul class="order-list-ul">
						      
						    </ul>
						  </div>
				      </div>
				    </div>
				  </div>
				   <!-- 加载提示符 -->
				    <div class="infinite-scroll-preloader">
				        <div class="preloader"></div>
				        <div>玩命加载中...</div>
				    </div>
			</div>
		</div>
		<script type="text/template" id="order_list_tpl">
			{@if orderList.length>0}
			{@each orderList as order}
			<li>
				<a href="${pageContext.request.contextPath}/market!pageJump.action?pageName=orderDetail&orderId=^{order.orderId}" class="item-link item-content external">
					<div class="item-inner">
						<div class="item-title-row">
							<div class="item-title">^{order.orderNo}</div>
							<!-- <div class="item-after color-red">^{order.orderState}</div> -->
						</div>
						<div class="item-subtitle">订单来源：^{order.orderSource}</div>
						<div class="item-subtitle">创建时间：^{order.createTime|convertTime}</div>
						<div class="item-subtitle">合计<strong class="color-red">￥^{order.orderMoney}</strong></div>
						{@if order.orderState=='收货'}
						<div class="item-title-row">
          					<div class="item-title"></div>
          					<div class="item-after">
								<button data-id="^{order.orderId}" data-options="refundApply" class="button button-fill button-danger">申请退款</button>
							</div>
        				</div>
						{@/if}
					</div>
				</a>
			</li>
			{@/each}
			{@else}
			<li class="text-center">
			未查询到任何数据...
			</li>
			{@/if}
		</script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm-extend.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/juicer-min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/app.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module-api.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module/order_list.js"></script>
	</body>

</html>