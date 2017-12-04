package wechat.pay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("xml")
public class WxPayUnifiedOrderResult extends WxPayBaseResult {
	
	/**
	   * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	   */
	  @XStreamAlias("prepay_id")
	  private String prepayId;

	  /**
	   * 交易类型，取值为：JSAPI，NATIVE，APP等
	   */
	  @XStreamAlias("trade_type")
	  private String tradeType;

	  /**
	   * mweb_url 支付跳转链接
	   */
	  @XStreamAlias("mweb_url")
	  private String mwebUrl;

	  /**
	   * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
	   */
	  @XStreamAlias("code_url")
	  private String codeURL;
}
