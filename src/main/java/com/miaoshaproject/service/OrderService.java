package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

/**
 * @ClassName OrderService
 * @Description //TODO
 * @Author ccy
 * @Date 2020/4/2 14:12
 * @Version 1.0
 **/
public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
