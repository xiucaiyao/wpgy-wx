<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>订单详情</title>
		<meta name="viewport" content="initial-scale=1, maximum-scale=1">
		<meta name="format-detection" content="telephone=no">
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
		<div id="order-detail" class="page order-detail">
			<header class="bar bar-nav">
				<a class="button button-link button-nav pull-left back external"  href="${pageContext.request.contextPath}/market!pageJump.action?pageName=orderList">
					<span class="icon icon-left"></span> 返回
				</a>
				<h1 class="title">订单详情</h1>
			</header>
			<nav class="bar bar-tab" style="background-color: transparent;">
				<div class="row">
 					<div class="col-50"><a id="tuidan" href="javascript:void(0);" class="button button-big button-fill button-danger external" style="top:initial">退单</a></div>
			      <div class="col-50"><a id="toPay"  href="javascript:void(0);" class="button button-big button-fill button-success external" style="top:initial">去支付</a></div>
			    </div>
			</nav>

			<div class="content">

				<div class="buttons-tab">
					<a href="#tab2" class="tab-link active button">订单详情</a>
					<a href="#tab1" class="tab-link  button">订单状态</a>
				</div>
				<div class="content-block">
					<div class="tabs">

					</div>
				</div>

			</div>

		</div>
		<script type="text/template" id="order_detail_tpl">
			<div id="tab2" class="tab active">
				<!--订单中商品-->
				<div class="list-block font75 detail-product-list">
					<ul>
						<li class="item-content detail-product-list-title">
							<div class="item-media"><i class="icon icon-menu"></i></div>
							<div class="item-inner">
								<div class="item-title"><strong>订单商品</strong></div>
							</div>
						</li>
						{@each orderDetailList as p}
						<li class="item-content">
							<div class="item-media">
								<img src="^{p.productBean.smallpic|imgServerUrl}" class="product-img" >
							</div>
							<div class="item-inner">
								<div class="item-title">^{p.productBean.name}</div>
								<div class="item-after">^{p.orderNum}</div>
								<div class="item-after">￥^{p.price}</div>
							</div>
						</li>
						{@/each}
					</ul>
				</div>
				<div class="list-block font75">
					<ul>
						<li class="item-content">
							<div class="item-inner">
								<div class="item-title" style="color:red;">订单一旦支付不能再修改，请核对后再支付！</div>
							</div>
						</li>
					</ul>
				</div>
				<!--合计-->
				<div class="list-block font75">
					<ul>
						<li class="item-content">
							<div class="item-inner">
								<div class="item-title">合计</div>
								<div class="item-after color-danger">￥^{orderBean.orderMoney}</div>
							</div>
						</li>
					</ul>
				</div>
				<!--订单详情 -->
				<div class="list-block font75 detail-info">
					<ul>
						<li class="item-content detail-info-title">
							<div class="item-media"><i class="icon icon-menu"></i></div>
							<div class="item-inner">
								<div class="item-title"><strong>订单详情</strong></div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner" style="padding-top: .4rem;">
									<div class="item-title label">订单号码:</div>
									<div class="item-input" id="orderNo" data-value="^{orderBean.orderNo}">
										^{orderBean.orderNo}
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title label">订单时间:</div>
									<div class="item-input">
										^{orderBean.orderDate|convertTime}
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title label">送货时间:</div>
									<div class="item-input">
										^{orderBean.sendDate,"yyyy-MM-dd"|convertTime}
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title label">收货人:</div>
									<div class="item-input">
										^{orderBean.receiveMan}
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title label">手机号码:</div>
									<div class="item-input">
										^{orderBean.phone}
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title label">收货地址:</div>
									<div class="item-input">
										^{orderBean.address}
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title label">订单备注:</div>
									<div class="item-input">
										^{orderBean.remark}
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>

			</div>

			<div id="tab1" class="tab ">
				<div id="order-status">
					<div class="timeline">
						<div class="timeline-item {@if orderBean.orderState=="未支付"}current{@/if}">
							<img class="timeline-logo" src="^{'/images/mobile/1.png'|imgServerUrl}">
							<div class="timeline-block">
								<i class="timline-block-arrow"></i>
								<p class="timeline-title">订单已提交^{orderBean.orderState}<span class="timeline-time"></span></p>
								<p class="timeline-sub">初始</p>
							</div>
						</div>
						
						<div class="timeline-connect timeline-connect-22"></div>
						<div class="timeline-item {@if orderBean.orderState=="汇总"}current{@/if}">
							<img class="timeline-logo" src="^{'/images/mobile/money.png'|imgServerUrl}">
							<div class="timeline-block">
								<i class="timline-block-arrow"></i>
								<p class="timeline-title">订单已经支付<span class="timeline-time"></span></p>
								<p class="timeline-sub">等待商品确认</p>
							</div>
						</div>
						
						<div class="timeline-connect timeline-connect-21"></div>
						<div class="timeline-item {@if orderBean.orderState=="送货"}current{@/if}">
							<img class="timeline-logo" src="^{'/images/mobile/2.png'|imgServerUrl}">
							<div class="timeline-block">
								<i class="timline-block-arrow"></i>
								<p class="timeline-title">送货中<span class="timeline-time"></span></p>
								<p class="timeline-sub">已在途中</p>
							</div>
						</div>

						<div class="timeline-connect timeline-connect-21"></div>
						<div class="timeline-item {@if orderBean.orderState=="收货"||orderBean.orderState=="审核"}current{@/if}">
							<img class="timeline-logo" src="^{'/images/mobile/3.png'|imgServerUrl}">
							<div class="timeline-block">
								<i class="timline-block-arrow"></i>
								<p class="timeline-title">订单完成<span class="timeline-time"></span></p>
								<p class="timeline-sub">已收货</p>
							</div>
						</div>

					</div>
				</div>
			</div>
			

		</script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm-extend.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/juicer-min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/app.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module-api.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module/order_detail.js"></script>
	</body>

</html>