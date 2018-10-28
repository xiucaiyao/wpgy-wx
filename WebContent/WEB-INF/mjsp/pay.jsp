<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String appId = (String)request.getAttribute("appid");
String timeStamp = (String)request.getAttribute("timeStamp");
String nonceStr = (String)request.getAttribute("nonceStr");
String packageValue = (String)request.getAttribute("package");
String paySign = (String)request.getAttribute("sign");
String payAmount = (String)request.getAttribute("payAmount");//用户支付金额
String payOrderNo = (String)request.getAttribute("payOrderNo");
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<title>微信支付</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
	</body>
	<script src='${pageContext.request.contextPath}/js/mobile/zepto.min.js' charset='utf-8'></script>
	<script src="${pageContext.request.contextPath}/js/mobile/app.js"></script>
	<script src="${pageContext.request.contextPath}/js/mobile/module-api.js"></script>
	<script type="text/javascript">
		$(function() {
			document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
				var data = {
					"appId": "<%=appId%>",
					"timeStamp": "<%=timeStamp%>",
					"nonceStr": "<%=nonceStr%>",
					"package": "<%=packageValue%>",
					"signType": "MD5",
					"paySign": "<%=paySign%>"
				}
				WeixinJSBridge.invoke('getBrandWCPayRequest', data, function(res) {
					WeixinJSBridge.log(res.err_msg);
					if (res.err_msg == "get_brand_wcpay_request:ok") {
						//alert("支付成功!");  
						window.location.href = app.api.paySuccessPage + "&payAmount=" + "<%=payAmount%>" + "&payOrderNo=" + "<%=payOrderNo%>";
					} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
						//alert("支付取消!");  
						window.location.href = app.api.orderListPage;
					} else if (res.err_msg == "get_brand_wcpay_request:fail") {
						//alert("支付失败!");  
						window.location.href = app.api.payFail;
					} else {
						alert('错误：' + res.err_msg);
						window.location.href = app.api.orderListPage;
					}
				});
			}, false);
		})
	</script>

</html>
