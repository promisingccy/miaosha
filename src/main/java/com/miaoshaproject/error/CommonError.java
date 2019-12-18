package com.miaoshaproject.error;

/**
 * @ClassName CommonError
 * @Description //TODO
 * @Author ccy
 * @Date 2019/12/18 19:35
 * @Version 1.0
 **/
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
