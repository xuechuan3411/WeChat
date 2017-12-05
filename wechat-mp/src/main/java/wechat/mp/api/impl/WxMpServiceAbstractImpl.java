package wechat.mp.api.impl;

import com.google.gson.JsonParser;

import wechat.common.exception.WxErrorException;
import wechat.common.util.http.RequestHttp;
import wechat.mp.api.WxMpConfigStorage;
import wechat.mp.api.WxMpService;

public abstract class WxMpServiceAbstractImpl<H, P> implements WxMpService, RequestHttp<H, P> {

  private static final JsonParser JSON_PARSER = new JsonParser();
  
  private WxMpConfigStorage wxMpConfigStorage;


  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;



  @Override
  public String getAccessToken() throws WxErrorException {
    return getAccessToken(false);
  }

  @Override
  public WxMpConfigStorage getWxMpConfigStorage() {
    return this.wxMpConfigStorage;
  }

  @Override
  public void setWxMpConfigStorage(WxMpConfigStorage wxConfigProvider) {
    this.wxMpConfigStorage = wxConfigProvider;
    this.initHttp();
  }
}
