<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> --%>
<%@ page language="java" import="com.mobile.constants.WxAppConstants" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1">
		<link rel="shortcut icon" href="/favicon.ico">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title><%= WxAppConstants.APP_NAME %></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/sm.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/sm-extend.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/app2.css">
   		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile/module.css">
		<script src="${pageContext.request.contextPath}/js/mobile/zepto.min.js"></script>
	</head>
	
	<body>
		<div id="tldl-index" class="page">
			<header class="bar bar-nav">
				<a class="button button-link button-nav pull-left external" href="javascript:location.reload()">
					<span class="icon icon-refresh"></span> 刷新
				</a>
				<h1 class="title"><%= WxAppConstants.APP_NAME %></h1>
			</header>
			
			<div class="bar bar-header-secondary">
				<div class="searchbar">
					<a class="searchbar-cancel">取消</a>
					<div class="search-input">
						<label class="icon icon-search" for="search"></label>
						<input type="search" id='search' placeholder='搜索...' />
					</div>
				</div>
			</div>
			<div id="zhezhao" class="searchbar-overlay "></div>
		 	<div class="bar catergory row no-gutter" style="top:4.4rem">
				<div class="catergory-all col-15">
					<div class="all active" data-id="all">
						全部
					</div>
				</div>
				<div class="catergory-list col-85">
					<div  class="catergory-wrap" >
						
					</div>
				</div>
			</div> 
		  	<nav class="bar bar-tab">
				<a class="tab-item active external" href="javascript:location.reload()">
					<span class="icon icon-home"></span>
					<span class="tab-label">首页</span>
				</a>
				<a class="tab-item external" href="${pageContext.request.contextPath}/market!pageJump.action?pageName=shopCar">
					<span class="icon icon-cart" id="toShopCar">
						<i id="shopcar-category-num">0</i>
					</span>
					<span class="tab-label">购物车</span>
				</a>
				<a class="tab-item external" href="${pageContext.request.contextPath}/market!pageJump.action?pageName=orderList">
					<span class="icon icon-me"></span>
					<span class="tab-label">我的订单</span>
				</a>
			</nav>
		  	<div onclick="$('.content').scrollTop(0);$('#indexToTop').hide()" class="bottom-to-top J_ping" id="indexToTop" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1); display: none;"> 
		  		<img src="${pageContext.request.contextPath}/images/mobile/scroll-to-top-icon.png" style="width: 100%;"> 
		  	</div>
		  	
			<div class="content infinite-scroll"   data-distance="50">
				
				
				<div class="list-block media-list" style="margin-top: 2.5rem;">
					<ul class="vagetables-ul content-inner" pageSize="1">
					</ul>
				</div>
				<div class="infinite-scroll-preloader">
			      <div class="preloader">
			      </div>
			      <div>玩命加载中...</div>
			    </div>
			</div>
		
		</div>
		<script type="text/template" id="catergory_list_tpl">
			<ul>
				{@each productTypeList as t}
				<li data-id="^{t.vcName}" >^{t.vcName}</li>
				{@/each}
			</ul>
		</script>
		<script type="text/template" id="vagetables_tpl">
			{@if productList.length>0}
				{@each productList as p}
					<li>
						<a href="${pageContext.request.contextPath}/market!pageJump.action?pageName=dishDetails&productQueryBean.serial=^{p.serial}&productQueryBean.productSpec=^{p.spec}" class="external item-link item-content">
							<div class="item-media"><img src="^{p.smallpic|imgServerUrl}" alt="图片加载失败" ></div>
							<div class="item-inner">
								<div class="item-title-row">
									<div class="item-title">^{p.name}</div>
									<div class="item-after">￥^{p.price}</div>
								</div>
								<div class="item-subtitle">^{p.spec}</div>
								<div class="item-subtitle">^{p.productSaleType}</div>
								<div class="product-shopope-box" data-obj="^{p|toJSON}">
									<div class="product-shopope-box-inner">
										<div class="product-shopope-add"></div>
									</div>
								</div>
							</div>
						</a>
					</li>
				{@/each}
				{@else}
				没有任何数据...
			{@/if}
			
		</script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm-extend.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/juicer-min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/fly.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/app.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module-api.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module/index.js"></script>
	</body>
</html>
