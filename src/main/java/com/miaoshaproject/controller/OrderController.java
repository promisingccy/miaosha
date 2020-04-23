package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName OrderController
 * @Description //TODO
 * @Author ccy
 * @Date 2020/4/2 16:49
 * @Version 1.0
 **/
@Controller("order")
@RequestMapping("/order")
//解决跨域问题
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class OrderController extends BaseController{
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create-order", method = {RequestMethod.POST}, consumes = {HTTP_CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "itemId")Integer itemId,
                                  @RequestParam(name = "amount")Integer amount,
                                  @RequestParam(name = "promoId", required = false)Integer promoId) throws BusinessException {
        //获取用户登录信息（从登录的session里拿用户信息）
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin == null || !isLogin.booleanValue()){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"还未登录，请登录");
        }
        //已登录 获取session里的用户对象 -> userId 下单
        UserModel userModel = (UserModel)httpServletRequest.getSession().getAttribute("LOGIN_USER");
        OrderModel orderModel = orderService.createOrder(userModel.getUserId(), itemId, promoId, amount);

        return CommonReturnType.create(orderModel);
    }
}
