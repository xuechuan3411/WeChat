package wechat.pay.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import wechat.pay.bean.WxPayApiData;
import wechat.pay.bean.notify.WxPayOrderNotifyResult;
import wechat.pay.bean.notify.WxPayRefundNotifyResult;
import wechat.pay.bean.request.WxOrderComRequest;
import wechat.pay.bean.request.WxPayRefundRequest;
import wechat.pay.bean.result.WxPayBaseResult;
import wechat.pay.bean.result.WxPayRefundResult;
import wechat.pay.bean.result.WxPayUnifiedOrderResult;
import wechat.pay.conf.WxPayConfig;
import wechat.pay.exception.WxPayException;
import wechat.pay.service.WxPayService;




/**
 * <pre>
 *  微信支付接口请求抽象实现类
 * </pre>
 *
 */
public abstract class WxPayServiceAbstractImpl implements WxPayService {
	
	private static final String PAY_BASE_URL = "https://api.mch.weixin.qq.com";
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	protected static ThreadLocal<WxPayApiData> wxApiData = new ThreadLocal<>();
	
	protected WxPayConfig config;

	private String getPayBaseUrl() {
		    if (this.getConfig().useSandbox()) {
		      return PAY_BASE_URL + "/sandboxnew";
		    }

		    return PAY_BASE_URL;
	}	
	
	public WxPayConfig getConfig() {
	    return this.config;
	}

	public void setConfig(WxPayConfig config) {
	    this.config = config;
	}
	
	/**
	   * 发送post请求
	   *
	   * @param url        请求地址
	   * @param requestStr 请求信息
	   * @param useKey     是否使用证书
	   * @return 返回请求结果字符串
	   */
	protected abstract String post(String url, String requestStr, boolean useKey) throws WxPayException;
	
	@Override
	  public WxPayUnifiedOrderResult unifiedOrder(WxOrderComRequest request) throws WxPayException {
	    request.checkAndSign(this.getConfig());

	    String url = this.getPayBaseUrl() + "/pay/unifiedorder";
	    String responseContent = this.post(url, request.toXML(), false);
	    WxPayUnifiedOrderResult result = WxPayBaseResult.fromXML(responseContent, WxPayUnifiedOrderResult.class);
	    result.checkResult(this);
	    return result;
	  }
	
	@Override
	  public WxPayOrderNotifyResult parseOrderNotifyResult(String xmlData) throws WxPayException {
	    try {
	      log.debug("微信支付异步通知请求参数：{}", xmlData);
	      WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlData);
	      log.debug("微信支付异步通知请求解析后的对象：{}", result);
	      result.checkResult(this);
	      return result;
	    } catch (WxPayException e) {
	      log.error(e.getMessage(), e);
	      throw e;
	    } catch (Exception e) {
	      log.error(e.getMessage(), e);
	      throw new WxPayException("发生异常，" + e.getMessage(), e);
	    }
	  }
	
	@Override
	  public WxPayRefundResult refund(WxPayRefundRequest request) throws WxPayException {
	    request.checkAndSign(this.getConfig());

	    String url = this.getPayBaseUrl() + "/secapi/pay/refund";
	    String responseContent = this.post(url, request.toXML(), true);
	    WxPayRefundResult result = WxPayBaseResult.fromXML(responseContent, WxPayRefundResult.class);
	    result.checkResult(this);
	    return result;
	  }
	
	@Override
	  public WxPayRefundNotifyResult parseRefundNotifyResult(String xmlData) throws WxPayException {
	    try {
	      log.debug("微信支付退款异步通知参数：{}", xmlData);
	      WxPayRefundNotifyResult result = WxPayRefundNotifyResult.fromXML(xmlData, this.getConfig().getMchKey());
	      log.debug("微信支付退款异步通知解析后的对象：{}", result);
	      return result;
	    } catch (Exception e) {
	      log.error(e.getMessage(), e);
	      throw new WxPayException("发生异常，" + e.getMessage(), e);
	    }
	  }
}
