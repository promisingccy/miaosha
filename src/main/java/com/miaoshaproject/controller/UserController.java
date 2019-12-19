package com.miaoshaproject.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


/**
 * @ClassName UserController
 * @Description //TODO
 * @Author ccy
 * @Date 2019/12/18 16:45
 * @Version 1.0
 **/
@Controller("user")
@RequestMapping("/user")
//解决跨域问题
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class UserController extends BaseController {
    //通过bean注入 这种写法不用实例化对象了，直接作为类的属性使用
    @Autowired
    private UserService userService;

    //操作session
    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户注册接口
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {HTTP_CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType register(
            @RequestParam(name="name")String name,
            @RequestParam(name="password")String password,
            @RequestParam(name="gender")Byte gender,
            @RequestParam(name="age")Integer age,
            @RequestParam(name="telphone")String telphone,
            @RequestParam(name="otpCode")String otpCode
    ) throws BusinessException {
        //验证手机号对应的optCode
        String inSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(telphone);
        if(!StringUtils.equals(inSessionOtpCode, otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不正确");
        }
        //用户注册
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        //密码md5加密
        System.out.println(MD5Encoder.encode(password.getBytes()));
        userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes()));
        //执行注册
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    //获取短信验证码接口
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {HTTP_CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone")String telphone){
        //生成OTP验证码 otpCode
        Random random = new Random();
        int randomInt = random.nextInt(99999);//[0, 99999)
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

        //OTP关联手机号(redis/session)
        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        //发送给手机号
        System.out.printf("telphone=%s&otpCode=%s%n", telphone, otpCode);

        return CommonReturnType.create(null);
    }

    //获取单个用户信息
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException {
        //调用service层返回用户信息
        UserModel userModel = userService.getUserById(id);

        //用户信息不存在
        if(userModel == null){
//            userModel.setEncrptPassword("fdsaf");
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    //封装返回model信息
    private UserVO convertFromModel(UserModel userModel){
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

}
