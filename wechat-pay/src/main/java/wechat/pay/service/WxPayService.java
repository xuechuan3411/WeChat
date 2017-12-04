package wechat.pay.service;

import wechat.pay.bean.notify.WxPayOrderNotifyResult;
import wechat.pay.bean.request.WxOrderComRequest;
import wechat.pay.bean.result.WxPayUnifiedOrderResult;
import wechat.pay.conf.WxPayConfig;
import wechat.pay.exception.WxPayException;

public interface WxPayService {
	
	/**
	   * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1)
	   * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
	   * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
	   *
	   * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
	   */
	  WxPayUnifiedOrderResult unifiedOrder(WxOrderComRequest request) throws WxPayException;
	  /**
	   * 获取配置
	   */
	  WxPayConfig getConfig();

	  /**
	   * 设置配置对象
	   */
	  void setConfig(WxPayConfig config);
	  
	  /**
	   * 解析支付结果通知
	   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
	   */
	  WxPayOrderNotifyResult parseOrderNotifyResult(String xmlData) throws WxPayException;
}
