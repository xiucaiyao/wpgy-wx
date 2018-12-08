<%@ page  pageEncoding="UTF-8"%>
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
		<title>王胖果业</title>
		<link href="${pageContext.request.contextPath}/css/mobile/sm.min.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/mobile/sm-extend.min.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/mobile/app.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/mobile/module.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/mobile/site.css" rel="stylesheet" />
	</head>

	<body>
		<div class="page ycc-reg">
			<header class="bar bar-nav bar-back-color common-header">
				<a class="button button-link button-nav pull-left open-panel">
					<icon class="icon icon-back"></icon>
				</a>
				<h1 class="title">注册</h1>
			</header>

			<div class="content">
				<div class="list-block">
					<ul>
						<!-- Text inputs -->
						<li>
							<div class="item-content">
								<div class="item-media"><i class="icon icon-form-name"></i></div>
								<div class="item-inner">
									<div class="item-title label">姓名</div>
									<div class="item-input">
										<input type="text" data-name="verify" id="name" placeholder="">
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media"><i class="icon icon-form-gender"></i></div>
								<div class="item-inner">
									<div class="item-title label">性别</div>
									<div class="item-input">
										<select id="sex" data-name="verify">
											<option>男</option>
											<option>女</option>
										</select>
									</div>
								</div>
							</div>
						</li>

						<!--icon icon-phone-->
						<li>
							<div class="item-content">
								<div class="item-media"><i class="icon icon-form-name"></i></div>
								<div class="item-inner">
									<div class="item-title label">电话</div>
									<div class="item-input">
										<input type="tel" data-name="verify" id="phone" placeholder="">
									</div>
								</div>
							</div>
						</li>

						<li>
							<div class="item-content">
								<div class="item-media"><i class="icon icon-form-name"></i></div>
								<div class="item-inner">
									<div class="item-title label">用户名</div>
									<div class="item-input">
										<input type="text" data-name="verify" id="usernmae" placeholder="">
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media"><i class="icon icon-form-password"></i></div>
								<div class="item-inner">
									<div class="item-title label">密码</div>
									<div class="item-input">
										<input type="password" data-name="verify" id="password" placeholder="" class="">
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media"><i class="icon icon-form-password"></i></div>
								<div class="item-inner">
									<div class="item-title label">确认密码</div>
									<div class="item-input">
										<input type="password" data-name="verify" id="password2" placeholder="" class="">
									</div>
								</div>
							</div>
						</li>
						
						<li>
							<div class="item-content">
								<div class="item-media"><i class="icon icon-form-name"></i></div>
								<div class="item-inner">
									<div class="item-title label">收货地址</div>
									<div class="item-input">
										<input type="text" data-name="verify" id="address" placeholder="填写街道门牌地址即可" class="">
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="content-block">
					<div class="row">
						<div class="col-100"><a href="#" id="zhuce" class="button button-big button-fill button-success disabled">注册</a></div>
					</div>
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
			var fun = {
				hasContent: function() {
					var result = true;
					$('input[data-name=verify]').each(function() {
						console.info($(this).val(), $(this).val() == "")
						if ($(this).val() == "") {
							result = false;
							return false;
						}
					})
					return result;
				}
			};
			$('input[data-name=verify]').eq(0).focus();
			$('input[data-name=verify]').on('keyup', function() {
				var result = fun.hasContent();
				console.info(result)
				if (result) {
					$('#zhuce').removeClass("disabled");
				} else {
					if (!$('#zhuce').hasClass("disabled")) {
						$('#zhuce').addClass("disabled");
					}
				}
			});
			$('#zhuce').on('click', function() {
				if (!$('#zhuce').hasClass("disabled")) {
					if ($('#password').val() == $('#password2').val()) {
						var validateNameIsExist = {
							loginName: $('#usernmae').val()
						}
						ymc.ajax({
							url: app.api.validateNameIsExist,
							type: 'POST',
							data: validateNameIsExist,
							success: function(jsonData) {
								if (jsonData.isSuccess == true) {
									var data = {
										"registFansBean.name": $('#name').val(),
										"registFansBean.gender": $('#password').val(),
										"registFansBean.phone": $('#phone').val(),
										"registFansBean.loginName": $('#usernmae').val(),
										"registFansBean.password": $('#password').val(),
										"registFansBean.address": $('#address').val()
									}
									ymc.ajax({
										url: app.api.regist,
										type: 'POST',
										data: data,
										success: function(
											jsonData) {
											if (jsonData.isSuccess == true) {
												window.location.href = app.api.registSuccessMessagePage;
											} else {
												alert(jsonData.message)
											}
										}
									});
								} else {
									alert(jsonData.message)
								}
							}
						});
					} else {
						$.alert('两次密码输入不一致', '提示');
					}
				}
			});
		</script>
	</body>

</html>