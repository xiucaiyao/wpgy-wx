var app = app || {};
(function(constant) {
	app.api = {};
	if(app.Config.isDebug){
		//前端调试模式
		app.api.query = app.Config.contextPath + "*.json"
		app.api={
				"productQuery":"/api/productQuery.json",
				"queryProductType":"/api/queryProductType.json",
				"productDetail":"/api/productDetail.json",
				"orderList":"/api/orderList.json",
				"orderDetail":"/api/orderDetail.json"
			}
	} else {
		//与后端对接模式
		app.api.weixinsdk = app.Config.contextPath + "weixinSdk.action"; //微信JS-SDK 配置信息获取
		
		/***
		 * POST
		 * 用户名：userName
		 * 登录密码：password
		 * 菜场用户信息登录
		 */
		app.api.login = app.Config.contextPath + "/fans.action";
		
		/***
		 * POST
		 * 用户名：loginName
		 * 注册时判断登录的用户名是否存在
		 */
		app.api.validateNameIsExist = app.Config.contextPath + "/fans!validateNameIsExist.action";
		
		/***
		 * POST
		 * 用户姓名：registFansBean.name（必须）
		 * 性别：registFansBean.gender（必须）
		 * 登录名：registFansBean.loginName（必须）
		 * 登录密码：registFansBean.password（必须）
		 * 电话：registFansBean.phone（必须）
		 * 注册用户信息
		 */
		app.api.regist = app.Config.contextPath + "/fans!regist.action";
		
		/***
		 * POST
		 * 搜索内容：productQueryBean.searchKeywords
		 * 当前页数：productQueryBean.currentPage
		 * 产品类型：productQueryBean.productType
		 * 分页查询菜场用户产品信息列表
		 */
		app.api.queryMarketProduct = app.Config.contextPath + "/market!queryMarketProduct.action"; 
		
		/**
		 * 查询菜场用户产品分类信息列表
		 */
		app.api.queryMarketProductType = app.Config.contextPath + "/market!queryMarketProductType.action"; 
		
		/***
		 * POST
		 * 编码：productQueryBean.serial(必须)
		 * 规格：productQueryBean.productSpec(必须)
		 * 查询菜场用户产品详细信息
		 */
		app.api.productDetailBySerial = app.Config.contextPath + "/market!productDetailBySerial.action";
		
		/***
		 * POST
		 * 编码：shoppingCartProductBean.productSerial(必须)
		 * 规格：shoppingCartProductBean.productSpec(必须)
		 * 单价：shoppingCartProductBean.productPrice(必须)
		 * 名称：shoppingCartProductBean.productName
		 * 小图片：shoppingCartProductBean.smallpic
		 * 数量：shoppingCartProductBean.number
		 * 加入商品到购物车
		 */
		//app.api.addProduct = app.Config.contextPath + "/market!addProduct.action";
		
		/***
		 * POST
		 * 编码：shoppingCartProductBean.productSerial(必须)
		 * 规格：shoppingCartProductBean.productSpec(必须)
		 * 单价：shoppingCartProductBean.productPrice(必须)
		 * 名称：shoppingCartProductBean.productName
		 * 小图片：shoppingCartProductBean.smallpic
		 * 数量：shoppingCartProductBean.number(必须)
		 * 修改商品到购物车
		 */
		//app.api.updateProduct = app.Config.contextPath + "/market!updateProduct.action";
		
		/***
		 * POST
		 * 编码：productSerial(必须)
		 * 规格：productSpec(必须)
		 * 删除购物车中的商品
		 */
		//app.api.deleteProduct = app.Config.contextPath + "/market!deleteProduct.action";
		
		/***
		 * 去购物车查看
		 */
		//app.api.queryShoppingCart = app.Config.contextPath + "/market!queryShoppingCart.action";
		
		/***
		 * POST
		 * 详细查看后端（MobileShoppingCartBean数据结构）
		 * 加载购物车的产品（用于验证）
		 */
		app.api.viewShoppingCart = app.Config.contextPath + "/market!viewShoppingCart.action";
		
		/***
		 * POST
		 * 详细查看后端（MobileShoppingCartBean/CustomerOrderBean数据结构）
		 * 增加菜摊用户订单
		 */
		app.api.generateMarketOrder = app.Config.contextPath + "/market!generateMarketOrder.action";
		
		/***
		 * POST
		 * 微信支付订单
		 */
		app.api.payOrderByWeixin = app.Config.contextPath + "/market!wechatPay.action";
		
		/***
		 * POST
		 * 订单状态：orderQueryBean.orderState(初始、汇总、送货、收货、审核、未支付)
		 * 订单当前页面数：orderQueryBean.currentPage(订单当前页数)
		 * 查询订单信息
		 */
		app.api.queryOrder = app.Config.contextPath + "/market!queryOrder.action";
		
		/***
		 * POST
		 * 订单ID：orderId
		 * 查询订单详细
		 */
		app.api.queryOrderDetail = app.Config.contextPath + "/market!queryOrderDetail.action";
		
		app.api.loginPage = app.Config.contextPath + "/fans!gotoLogin.action"; // 进入登录页面
		
		app.api.registPage = app.Config.contextPath + "/fans!gotoRegist.action"; // 进入注册页面
		
		app.api.registSuccessMessagePage = app.Config.contextPath + "/fans!gotoRegistSuccessMessage.action"; //注册成功后跳入成功信息提示页面
		
		app.api.marketIndexPage = app.Config.contextPath + "/market!pageJump.action?pageName=marketIndex"; // 进入菜场用户首页
		
		app.api.dishDetailsPage = app.Config.contextPath + "/market!pageJump.action?pageName=dishDetails"; // 菜品详细页面
		
		app.api.shopCarPage = app.Config.contextPath + "/market!pageJump.action?pageName=shopCar"; // 进入购物车页面
		
		app.api.orderDetailPage = app.Config.contextPath + "/market!pageJump.action?pageName=orderDetail"; // 进入订单详情页面
		
		app.api.orderListPage = app.Config.contextPath + "/market!pageJump.action?pageName=orderList"; // 进入订单列表
		
		//订单支付成功后跳转的页面
		app.api.paySuccessPage = app.Config.contextPath + "/market!pageJump.action?pageName=paySuccess";
		
		//订单支付失败后跳转的页面
		app.api.payFail = app.Config.contextPath + "/market!pageJump.action?pageName=payFail";
		
		//删除未支付的订单 (参数:orderId)
		app.api.deleteNoPayOrder = app.Config.contextPath + "/market!deleteNoPayOrder.action";
		
		//weixinRefundApplyBean
		app.api.refundApply = app.Config.contextPath + "/market!refundApply.action";
		//refundApply
		app.api.refundApplyPage = app.Config.contextPath + "/market!pageJump.action?pageName=refundApply";
	}
})();