package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
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

    //拿到session
    @Autowired
    private HttpServletRequest httpServletRequest;

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
