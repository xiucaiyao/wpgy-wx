
$(function() {
	"use strict";

	$(document).on("pageInit", "#order-detail", function(e, id, page) {
		var _html="",
			parms = app.parseUrlQuery(location.href);
		app.ajax({
			url:app.api.queryOrderDetail+"?orderId="+parms.orderId,
			success:function(resule){
				//alert(JSON.stringify(resule));
				if(resule.success){
					if(resule.data.orderBean.orderState !="未支付" && resule.data.orderBean.orderState !="已过期"){
						$(".bar.bar-tab").hide();
					}
					if(resule.data.orderBean.orderState =="已过期"){
						$("#toPay").parent().remove();
						$("#tuidan").parent().removeClass("col-50").addClass("col-100");
					}
					_html=app.renderById("order_detail_tpl",resule.data);
				}
				
			}
			
		});
		$(page).find(".tabs").html(_html);
		
		//跳转到支付
		$("#toPay").on("click",function(){
			var _this=$(this);
			$.confirm("订单一旦支付不能再修改，请仔细核对后再支付！","确认",function(){
				location.href= app.Config.contextPath+"/market!wechatPay.action?orderNo="+$("#orderNo").attr("data-value");
				_this.off("click");
			});
		});
		
		//跳转到支付
		$("#tuidan").on("click",function(){
			var _this=$(this);
			$.confirm("您确认退单吗？","确认",function(){
				app.ajax({
					url:app.api.deleteNoPayOrder +"?orderId="+parms.orderId,
					success:function(resule){
						if(resule.success){
							$.toast("退单成功！");
							setTimeout(function(){
								window.location.href=app.api.orderListPage;
							},2000);
							
						}else if(result.message){
							$.toast(result.message);
						}
						
					}
					
				});
			});
		});
		
	});
	$.init();
});