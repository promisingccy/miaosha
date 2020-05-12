package com.miaoshaproject.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName UserModel
 * @Description //TODO
 * @Author ccy
 * @Date 2019/12/18 17:08
 * @Version 1.0
 **/
@Setter
@Getter
public class UserModel {
    private Integer id;
    //不能为空不能为null 用于验证字符串
    @NotBlank(message = "用户名不能为空")
    private String name;
    //不能为null 用于验证数字
    @NotNull(message = "性别不能不填写")
    private Byte gender;
    @NotNull(message = "年龄不能不填写")
    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 150, message = "年龄必须小于150")
    private Integer age;
    @NotBlank(message = "手机号不能为空")
    private String telphone;
    private String registerMode;
    private String thirdPartyId;
    @NotBlank(message = "密码号不能为空")
    private String encrptPassword;
    private Integer userId;
    private String otpCode;
}
