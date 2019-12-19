package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

/**
 * @ClassName UserService
 * @Description //TODO
 * @Author ccy
 * @Date 2019/12/18 16:48
 * @Version 1.0
 **/
public interface UserService {
    //id获取对象
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;
}
