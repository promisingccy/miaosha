package com.miaoshaproject.response;

/**
 * @ClassName CommonReturnType
 * @Description //TODO
 * @Author ccy
 * @Date 2019/12/18 19:21
 * @Version 1.0
 **/
public class CommonReturnType {
    private Integer code;//业务返回码
    private String status;//返回状态 true fail
    private Object data;

    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, "success", 20000);
    }

    public static CommonReturnType create(Object result, String status, Integer code){
        CommonReturnType type = new CommonReturnType();
        type.setCode(code);
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
