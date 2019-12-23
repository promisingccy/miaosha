package com.miaoshaproject.error;

/**
 * @ClassName EmBusinessError
 * @Description //TODO
 * @Author ccy
 * @Date 2019/12/18 19:37
 * @Version 1.0
 **/
public enum EmBusinessError implements CommonError {
    //通用错误类型 10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),

    //20000 系列为用户信息相关错误
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户手机号或者密码不正确")
    ;

    private EmBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;//
    private String errMsg;//

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override //定制化错误信息
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
