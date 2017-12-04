package wechat.pay.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WxPayApiData {
	

	  /**
	   * 接口请求地址
	   */
	  private String url;

	  /**
	   * 请求数据
	   */
	  private String requestData;

	  /**
	   * 响应数据
	   */
	  private String responseData;

	  /**
	   * 接口请求异常信息
	   */
	  private String exceptionMsg;
	  
	  public WxPayApiData(String url, String requestData, String responseData, String exceptionMsg) {
		this.url=url;
		this.requestData=requestData;
		this.responseData=responseData;
		this.exceptionMsg=exceptionMsg;
	  }
	  
}

