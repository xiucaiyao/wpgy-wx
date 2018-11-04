/**
 * 作者：xiucaiyao@126.com
 * 时间：2018-10-28
 * 描述：app 核心框架
 */
var app = app || {};
(function() {

	app.Config = {};
	app.Config.contextPath = "/wpgy-wx"; // 项目名
	app.Config.imgServerUrl = "/wpgy-wx"; // 资源路径
	//app.Config.projectPath = "http://101.132.161.50:8080"; // 项目路径
	app.Config.projectPath = "http://localhost:8080"; // 项目路径
	app.Config.isDebug = false;

	Date.prototype.format = function (format) {
	    var o = {
	        "M+": this.getMonth() + 1, //month
	        "d+": this.getDate(),    //day
	        "h+": this.getHours(),   //hour
	        "H+": this.getHours(),   //hour
	        "m+": this.getMinutes(), //minute
	        "s+": this.getSeconds(), //second
	        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
	        "S": this.getMilliseconds() //millisecond
	    };
	    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
	    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o) if (new RegExp("(" + k + ")").test(format))
	        format = format.replace(RegExp.$1,
	        RegExp.$1.length == 1 ? o[k] :
	        ("00" + o[k]).substr(("" + o[k]).length));
	    return format;
	};
	
	
	if(juicer){
		//防止和java语法冲突
		juicer.set({
			'tag::operationOpen': '{@',
			'tag::operationClose': '}',
			'tag::interpolateOpen': '^{',
			'tag::interpolateClose': '}',
			'tag::noneencodeOpen': '^^{',
			'tag::noneencodeClose': '}',
			'tag::commentOpen': '{#',
			'tag::commentClose': '}'
		});
		//图片路径
		juicer.register("imgServerUrl", function(parm) {
			return /http:\/\//g.test(parm) ? parm : app.Config.imgServerUrl + parm;
		});
		//对象转json
		juicer.register("toJSON", function(parm) {
			return JSON.stringify(parm);
		});
		//传入毫秒,输出时间字符串;处理json字符串中的时间;若毫秒不传入,则返回当前时间
		juicer.register("convertTime", function(parm,format) {
			if(!format)format="yyyy-MM-dd hh:mm:ss";
			return (parm&&new Date(parm.time).format(format))||new Date().format(format);
		});
		
		/**
		 * @desc	 模板渲染
		 * @param {Object} templateId
		 * @param {Object} renderData
		 */
		app.renderById = function(templateId, renderData) {
			if (!templateId) {
				return "";
			}
			return juicer($("#" + templateId).html()).render(renderData);
		};
	}
	
	
	
	//初始化App
	app.initApp = function(options) {

	};

	//重写ajax定义的方法，主要用于权限判断
	app.ajax = function(options) {
		app.showPreloader();
		var defaults = {
			type: "POST",
			dataType: "json",
			async: false,
			error: function(xhr, errorType, error) {
				console.info(xhr, errorType, error);
			},
			complete: function(xhr, status) {
				app.hidePreloader();
			},
		};
		$.extend(true, defaults, options);
		defaults.complete=function(xhr, status){
			app.hidePreloader();
			try {
				if(typeof options.complete=="function"){
					options.complete(xhr, status);
				}
			} catch (e) {
				if(console)console.error("complete回调方法出错");
			}
		};
		//解决hbuilder不支持post请求.json文件
		if (/\.json/g.test(options.url)) defaults.type = "GET";
		defaults.url = (/\http:\/\//g.test(options.url) ? defaults.url : app.Config.projectPath + defaults.url);
		$.ajax(defaults);
	};
	
	
	
	//附件上传
	app.ajaxFileUpload = function(dataOptions) {
		app.showPreloader("正在上传，请稍等...");
		if (!dataOptions || !dataOptions.files || dataOptions.files.length == 0) {
			return;
		}
		dataOptions.async = true;
		dataOptions.type = "POST";
		dataOptions.dataType = 'json';
		var formdata = new FormData();
		if (dataOptions.data) {
			for ( var key in dataOptions.data) {
				formdata.append(key, dataOptions.data[key]);
			}
		}
		if (dataOptions.files.length == 1) {
			if ($(dataOptions.files).attr("name")) {
				formdata.append($(dataOptions.files).attr("name"), $(dataOptions.files)[0].files[0]);
			}
		} else {
			for (var i = 0; i < dataOptions.files.length; i++) {
				formdata.append($(dataOptions.files[0]).attr("name"), $(dataOptions.files[i])[0].files[0]);
			}
		}
		var xmlRequest = new XMLHttpRequest();
		xmlRequest.open(dataOptions.type, dataOptions.url, true);
		xmlRequest.onload = function(e) {
			if (xmlRequest.readyState == 4) {
				app.hideIndicator();
				if (xmlRequest.status == 200) {
					var responseText = e.target.responseText;
					if (dataOptions.dataType == 'json') {
						dataOptions.success(JSON.parse(responseText), e.status);
					} else {
						dataOptions.success(responseText, e.status);
					}
				} else {
					if (dataOptions.error && typeof dataOptions.error == "function") {
						dataOptions.error(xmlRequest, e.status, e);
					}
				}
			}
		};
		xmlRequest.send(formdata);
	};

	app.parseUrlQuery = function(url) {
		var query = {}, i, params, param;
		if (url.indexOf('?') >= 0)
			url = url.split('?')[1];
		else
			return query;
		params = url.split('&');
		for (i = 0; i < params.length; i++) {
			param = params[i].split('=');
			query[param[0]] = param[1];
		}
		return query;
	};

	//TODO: 显示页面预加载提示
	app.showPreloader = function(message) {
		if (!message) {
			message = "正在加载...";
		}
		if ($('.preloader-indicator-overlay, .preloader-indicator-modal').length > 0) {
			$('.preloader-indicator-overlay, .preloader-indicator-modal').remove();
		}
		$.showIndicator();
	};

	//TODO: 隐藏页面加载提示
	app.hidePreloader = function() {
		$.hideIndicator();
	};

	app.initEvent = function() {
		$(document).on("click", "a.back", function() {
			window.history.go(-2);
		});
	};

	app.shareForword = {
		share_img : '/images/mobile/default_share.jpg',// 默认分享的图片
		share_img_width : "640",// 分享的的图片宽度
		share_img_height : "640",// 分享的的图片高度
		desc : null, // 分享内容的描述
		title : null, // 分享内容的标题

		changShare : function() {
			if(!wx){
				return;
			}
			//修改分享的图片，标题，内容
			this.setShareImg();
			this.setShareTitle();
			this.setShareContent();
			this.bindWeixin(this.onBindingShare);
		},
		
		disableShare : function(){
			//禁用分享
			if(!wx){
				return;
			}
			this.bindWeixin(function(){
				wx.hideMenuItems({
				    menuList: ['menuItem:share:appMessage', 'menuItem:share:timeline', 'menuItem:share:qq', 'menuItem:share:weiboApp', 'menuItem:share:facebook', 'menuItem:share:QZone'] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
				});
			});
		},

		setShareImg : function() {
			// 设置分享的图片
			var shareImg = "";
			if ($("input[name='shareImg']").length > 0) {
				if ($("input[name='shareImg']").length == 1) {
					shareImg = $("input[name='shareImg']").val();
				} else {
					$("input[name='shareImg']").each(function(index, element) {
						if (!shareImg) {
							shareImg = $(element).val();
						}
					});
				}
			}
			if (!shareImg) {
				var imgList = $("img");
				for (var i = 0; i < imgList.length; i++) {
					if ($(imgList[i]).attr("src") != "") {
						shareImg = $(imgList[i]).attr("src");
						break;
					}
				}
			}
			if (shareImg) {
				if (shareImg.indexOf("http://") != 0) {
					shareImg = app.FamiConfig.imgServerUrl + shareImg;
				}
				this.share_img = shareImg;
			} else {
				this.share_img = app.FamiConfig.imgServerUrl + this.share_img;
			}
		},

		setShareTitle : function() {
			// 设置分享的标题
			if ($("input[name='shareTitle']").length > 0) {
				this.title = $("input[name='shareTitle']").val();
			}
			if (!this.title) {
				this.title = $("title").text();
			}
			if (!this.title) {
				this.title = "欢迎进入上农" + WeixinConstants.APP_NAME + "电商平台";
			}
		},

		setShareContent : function() {
			// 设置分享的内容
			if ($("input[name='shareContent']").length > 0) {
				this.desc = $("input[name='shareContent']").val();
			}
			if (!this.desc) {
				this.desc = $("title").text();
			}
			if (!this.desc) {
				this.desc = "王胖果品，配送到家。";
			}
		},

		shareLink : function() {
			var shareUrl = window.location.href;
			return shareUrl;
		},

		bindWeixin : function(callback) {
			var weixinSdk = this;
			app.ajax({
				url : app.api.weixinsdk,
				data : {
					currPageUrl : window.location.href
				},
				success : function(result) {
					if (result.isSuccess === true) {
						result.data.callback = callback;
						weixinSdk.weixinConfig(result.data);
					}
					console.log(result);
				},
				error : function(e) {
					console.log(e);
				}
			});
		},
		
		weixinConfig: function(options){
			var weixinSdk = this;
			wx.config({
				debug : false,
				appId : options.appId, // 必填，公众号的唯一标识
				timestamp : options.timestamp, // 必填，生成签名的时间戳
				nonceStr : options.nonceStr, // 必填，生成签名的随机串
				signature : options.signature,// 必填，签名，见附录1
				jsApiList : [ 'checkJsApi', 'hideMenuItems', 'showMenuItems', 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo']
			// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function() {
				wx.checkJsApi({
					jsApiList : ['hideMenuItems', 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo' ],
					success : function(res) {
						if(options.callback)
							options.callback();
					}
				});
				// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			});
			wx.error(function(res) {
				aler(JSON.stringify(res));
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			});
		},

		onBindingShare : function() {
			//分享接口获取
			var weixinSdk = app.shareForword;
			var shareLink = weixinSdk.shareLink();
			// 分享到朋友圈
			wx.onMenuShareTimeline({
				title : weixinSdk.title, // 分享标题
				link : shareLink, // 分享链接
				imgUrl : weixinSdk.share_img, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					alert("onMenuShareTimeline success!");
				}
			});
			// 分享给朋友
			wx.onMenuShareAppMessage({
				title : weixinSdk.title, // 分享标题
				desc : weixinSdk.desc, // 分享描述
				link : shareLink, // 分享链接
				imgUrl : weixinSdk.share_img, // 分享图标
				type : 'link', // 分享类型,music、video或link，不填默认为link
				dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
				success : function() {
					// 用户确认分享后执行的回调函数
					alert("onMenuShareAppMessage success!");
				},
				cancel: function () { 
			        // 用户取消分享后执行的回调函数
					alert("onMenuShareAppMessage cancel!");
			    }
			});
			// 分享到QQ
			wx.onMenuShareQQ({
				title : weixinSdk.title, // 分享标题
				desc : weixinSdk.desc, // 分享描述
				link : weixinSdk.shareLink, // 分享链接
				imgUrl : weixinSdk.share_img, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					alert("onMenuShareQQ success!");
				}
			});
			// 分享到腾讯微博
			wx.onMenuShareWeibo({
				title : weixinSdk.title, // 分享标题
				desc : weixinSdk.desc, // 分享描述
				link : shareLink, // 分享链接
				imgUrl : weixinSdk.share_img, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					alert("onMenuShareWeibo success!");
				}
			});
		}
	};
})();



/**
 * 购物车类
 */
function ShopCar() {
	//构造对象时先持久化
	var shopCar = this.get() || {};
	this.price = shopCar.price || 0.00; //购物车内商品总价格
	this.number = shopCar.number || 0; //总数量
	this.sendDate = this.getSendDate();//送货日期
	if(this.sendDate){
		this.shoppingCartProductList = shopCar.shoppingCartProductList || [];
	} else {
		this.clear();
		this.shoppingCartProductList = [];
	}
	if (!shopCar.number) {
		this.save(this);
	}
}

/**
 * 往购物车中添加商品
 * @param {CarProduct} carProduct
 */
ShopCar.prototype.add = function(carProduct) {
	//判断购物车里是否有该商品,若有则数量+1,若无则push
	var _index = this.contain(carProduct);
 	var product=this.shoppingCartProductList[_index];
	if (product) {
		//TODO 更改这里可支持一次性往购物车添加多件同类型商品
		product.number++;
		product.priceSubtotal =parseFloat( (product.number * product.productPrice).toFixed(2));
	} else {
		this.shoppingCartProductList.push(carProduct);
	}
	this.update(product);
	this.save();
	return this;
};
ShopCar.prototype.remove = function(carProduct,isEmpt) {
	var _index = this.contain(carProduct);
 	var product=this.shoppingCartProductList[_index];
	if(!product){
		throw new Error("不存在的商品,无法移除!");
	}
	
	if(isEmpt){
		this.shoppingCartProductList.splice(_index,1);
	}
	//判断购物车里该商品数量,等于1则从购物车内remove掉
	else if(product.number>1){
		//TODO 更改这里可支持一次性往购物车减少多件同类型商品，注意判断也要改
		product.number--;
		product.priceSubtotal =parseFloat(( product.number * product.productPrice).toFixed(2));
	}else if(product.number==1){
		this.shoppingCartProductList.splice(_index,1);
	}
	this.update(product);
	this.save();
	return this;
};

/**
 * 直接改变购物车中某类商品的数量
 * @param {CarProduct} carProduct
 */
ShopCar.prototype.change=function(carProduct){
	var _index = this.contain(carProduct);
 	var product=this.shoppingCartProductList[_index];
	if(!product){
		throw new Error("不存在的商品,无法更改数量!");
	}
	product.number=parseInt(carProduct.number);
	product.priceSubtotal =parseFloat( (product.number * product.productPrice).toFixed(2));
	this.update(product);
	this.save();
	return this;
	
};

/**
 * 根据productSerial属性判断该商品是否存在于购物车,不存在返回false,存在则返回该商品在购物车列表中的索引
 * @param {CarProduct} carProduct
 */
ShopCar.prototype.contain = function(carProduct) {
	var CartProductList = this.shoppingCartProductList;
	for (var i=0,l=CartProductList.length;i<l;i++) {
		if (CartProductList[i].productSerial === carProduct.productSerial&&CartProductList[i].productSpec===carProduct.productSpec) {
			return i;
		}
	}
	return false;
};
/**
 * 持久化购物车对象
 * @param {CarProduct} shopCar
 */
ShopCar.prototype.save = function(shopCar) {
	localStorage.setItem("shopCar", encodeURIComponent(JSON.stringify(shopCar || this)));
};

ShopCar.prototype.clear=function(){
	localStorage.clear("shopCar");
};

/**
 * 获取最新的持久化购物车对象
 */
ShopCar.prototype.get = function() {
	var item = decodeURIComponent(localStorage.getItem("shopCar"));
	return item && item !== "null" ? JSON.parse(item) : null;;
};
/**
 * 更新当前购物车商品,将传入的购物车对象更新到内存，同时计算购物车价格数量等(非持久化)
 * @param {CarProduct} carProduct
 */
ShopCar.prototype.update = function(carProduct) {
	var price = 0.00,
		num = 0;;
	for (var i = 0, l = this.shoppingCartProductList.length; i < l; i++) {
		if (carProduct&&this.shoppingCartProductList[i].productSerial === carProduct.productSerial&&this.shoppingCartProductList[i].productSpec===carProduct.productSpec) {
			this.shoppingCartProductList[i] = carProduct;
		}
		price += this.shoppingCartProductList[i].priceSubtotal;
		num += this.shoppingCartProductList[i].number;
	}
	//计算总价格和总数量
	this.price = parseFloat( price.toFixed(2));
	this.number = num;
	return this;
};

/***
 * 将本地存储的购物车对象转换为可和后台对接的购物车对象
 */
ShopCar.prototype.TOJSON=function(_data){
	 var data=_data||{};
	 for(var i = 0; i < this.shoppingCartProductList.length; i++){
			data["mobileShoppingCartBean.mobileShoppingCartProductList["+i+"].number"] = this.shoppingCartProductList[i].number;
			data["mobileShoppingCartBean.mobileShoppingCartProductList["+i+"].productSerial"] = this.shoppingCartProductList[i].productSerial;
			data["mobileShoppingCartBean.mobileShoppingCartProductList["+i+"].productName"] = this.shoppingCartProductList[i].productName;
			data["mobileShoppingCartBean.mobileShoppingCartProductList["+i+"].productSpec"] = this.shoppingCartProductList[i].productSpec;
			data["mobileShoppingCartBean.mobileShoppingCartProductList["+i+"].productPrice"] = this.shoppingCartProductList[i].productPrice;
			data["mobileShoppingCartBean.mobileShoppingCartProductList["+i+"].smallpic"] = this.shoppingCartProductList[i].smallpic;
		}
	return data;
};


/***
 * 与数据库同步购物车内商品数据
 * @param {Array} latestProductList
 */
ShopCar.prototype.sync=function(latestProductList){
	var currentProductList=this.shoppingCartProductList;//当前本地存储中的商品
	for(var i=0,cl=currentProductList.length;i<cl;i++){
		//currentProductList[i]
		for(var j=0,ll=latestProductList.length;j<ll;j++){
			//latestProductList[j]
			if(currentProductList[i].productSerial==latestProductList[j].productSerial&&currentProductList[i].productSpec==latestProductList[j].productSpec){
				//赋值是否过期信息
				currentProductList[i].valid=latestProductList[j].valid;
			}
		}
	}
	this.shoppingCartProductList=currentProductList;
	return this;
};

/***
 * 获取送货日期
 * @param {Array} latestProductList
 */
ShopCar.prototype.getSendDate=function(){
	var sendDate = new Date();
	var hour = sendDate.getHours();
	//if(hour >= 15 && hour < 16){
	if(hour > -1){
		sendDate.setDate(sendDate.getDate()+1);
		return sendDate.format("yyyy-MM-dd"); 
	} else {
		return null;
	}
};

/**
 * 购物车内商品
 * @param {String} productSerial
 * @param {String} productName
 * @param {Number} productPrice
 * @param {String} productSpec
 * @param {String} brand
 * @param {String} smallpic
 * @param {String} productSaleType
 * @param {Number} number
 */
function CarProduct(productSerial, productName, productPrice, productSpec, brand, smallpic, productSaleType, number) {
	this.productSerial = productSerial+""; //id
	this.productName = productName; //名称
	this.productPrice = parseFloat(productPrice|| 0.00) ; //单价
	this.productSpec = productSpec; //规格
	this.brand = brand; //品牌
	this.smallpic = smallpic; //略缩图
	this.productSaleType = productSaleType; //产品类型
	this.number =parseInt(number||1) ; //商品数量
	this.priceSubtotal =parseFloat( (this.productPrice * this.number).toFixed(2)); //该商品总价格

}
