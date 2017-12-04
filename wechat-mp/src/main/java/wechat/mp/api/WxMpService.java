package wechat.mp.api;

import com.wechat.common.exception.WxErrorException;


public interface WxMpService {
	
	/**
	   * 获取access_token
	   */
	  String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

	  /**
	   * 获取access_token, 不强制刷新access_token
	   *
	   * @see #getAccessToken(boolean)
	   */
	  String getAccessToken() throws WxErrorException;
	  
	  /**
	   * <pre>
	   * 获取access_token，本方法线程安全
	   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
	   *
	   * 另：本service的所有方法都会在access_token过期是调用此方法
	   *
	   * 程序员在非必要情况下尽量不要主动调用此方法
	   *
	   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
	   * </pre>
	   *
	   * @param forceRefresh 强制刷新
	   */
	  String getAccessToken(boolean forceRefresh) throws WxErrorException;  
	  
	  /**
	   * 初始化http请求对象
	   */
	  void initHttp();
	  
	  /**
	   * 获取WxMpConfigStorage 对象
	   *
	   * @return WxMpConfigStorage
	   */
	  WxMpConfigStorage getWxMpConfigStorage();
	  
	  
	  /**
	   * 注入 {@link WxMpConfigStorage} 的实现
	   */
	  void setWxMpConfigStorage(WxMpConfigStorage wxConfigProvider);
	  
	  
}
