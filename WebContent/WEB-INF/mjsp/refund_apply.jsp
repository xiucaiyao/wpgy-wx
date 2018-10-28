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
  		<input type="hidden" name="orderId" value="${request.orderId}">
    	<div id="tldl-refund-apply" class="page tldl-refund-apply">
			<header class="bar bar-nav">
				<a class="button button-link button-nav pull-left external" href="${pageContext.request.contextPath}/market!pageJump.action?pageName=orderList">
					<span class="icon icon-left"></span> 返回
				</a>
				<h1 class="title">退款申请</h1>
			</header>
			<div class="content">
				<div class="list-block">
					<ul>
      					<li>
							<div class="item-content">
					       		<div class="item-inner">
            						<div class="item-title label">订单编号</div>
            						<div class="item-input">
              							<input type="text" name="orderNo" readonly="readonly">
            						</div>
          						</div>
					        </div>
						</li>
						<li>
							<div class="item-content">
					       		<div class="item-inner">
            						<div class="item-title label">退款金额(￥)</div>
            						<div class="item-input">
              							<input type="text" name="orderMoney" max="10">
            						</div>
          						</div>
					        </div>
						</li>
						<li>
							<div class="item-content">
					       		<div class="item-inner">
            						<div class="item-title label">申请原因</div>
            						<div class="item-input">
              							<textarea maxlength="200"></textarea>
            						</div>
          						</div>
					        </div>
						</li>
					</ul>
				</div>
				<div class="content-block">
    				<div class="row">
      					<div class="col-50"><a href="${pageContext.request.contextPath}/market!pageJump.action?pageName=orderList" class="button button-big button-fill button-danger external">取消</a></div>
      					<div class="col-50"><a href="javascript:void(0);" class="button button-big button-fill button-success external">提交</a></div>
    				</div>
  				</div>
			</div>
		</div>
		<script src="${pageContext.request.contextPath}/js/mobile/sm.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/sm-extend.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/juicer-min.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/app.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module-api.js"></script>
		<script src="${pageContext.request.contextPath}/js/mobile/module/refund_apply.js"></script>
  	</body>
</html>
