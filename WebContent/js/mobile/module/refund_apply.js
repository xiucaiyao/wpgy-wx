$(function() {
	"use strict";
	$(document).on("pageInit", "#tldl-refund-apply", function(e, id, page) {
		var orderId = $("input[name='orderId']").val();
		$("input[name='orderMoney']").keyup(function(e){
			console.info(e);
			if(!(/^(?:\d+|\d+\.\d{0,2})$/.test(this.value))){
				this.value = this.value.replace(/^(\d*\.\d{0,2}|\d+).*$/,'$1');
			} 
		})
		// ajax获取数据
		$("input[name='orderNo']").val(orderId);
//		app.ajax({
//			url: app.api.queryOrderDetail+"?orderId="+orderId,
//			success: function(resp) {
//				if (resp.success) {
//					$("input[name='orderNo']").val(resp.data.orderBean.orderId);
//					//$("input[name='orderMoney']").val(resp.data.orderBean.orderMoney);
//				}else{
//					$.toast(resp.message||resp.exceptionMessage);
//				}
//			}
//		});
		$(page).find("a.button-success").on("click", function(events){
			var reason = $("textarea").val();
			var refundMoney = $("input[name='orderMoney']").val();
			if(!reason){
				$.toast("请填写申请原因!");
				return;
			}
			if(refundMoney.length < 1){
				$.toast("请填写退款金额!");
				return;
			}
			app.ajax({
				url: app.api.refundApply,
				data:{
					"weixinRefundApplyBean.refundMoney":refundMoney,
					"weixinRefundApplyBean.refundReason":reason,
					"weixinRefundApplyBean.orderId": orderId
				},
				success: function(resp) {
					if (resp.success) {
						$.toast(resp.message);
						setTimeout(function(){
							window.location.href = app.api.orderListPage;
						}, 2000);
					}else{
						$.toast(resp.message||resp.exceptionMessage);
					}
				}
			});
		});
	});
	$.init();
});