package com.miaoshaproject.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    //日志操作对象
    private Logger logger = LoggerFactory.getLogger(getClass());

    //用户登录接口
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {HTTP_CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone")String telphone,
                                  @RequestParam(name = "password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(org.apache.commons.lang3.StringUtils.isEmpty(telphone) || org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //用户登录 校验登录合法性
        UserModel userModel = userService.validateLogin(telphone, this.EncodeByMd5(password));
//        System.out.println(ReflectionToStringBuilder.toString(userModel));
        //存储session
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(userModel);
    }

    //用户注册接口
    @PostMapping(value = "/register", consumes = {HTTP_CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType register(UserModel userModel) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        logger.debug(ReflectionToStringBuilder.toString(userModel));
        //验证手机号对应的optCode
        String inSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(userModel.getTelphone());
        if(!StringUtils.equals(inSessionOtpCode, userModel.getOtpCode())){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不正确");
        }
        //用户注册
        userModel.setName(userModel.getName());
        userModel.setGender(userModel.getGender());
        userModel.setAge(userModel.getAge());
        userModel.setTelphone(userModel.getTelphone());
        userModel.setRegisterMode("byphone");
        //密码md5加密
        userModel.setEncrptPassword(this.EncodeByMd5(userModel.getEncrptPassword()));
        //执行注册
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
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
