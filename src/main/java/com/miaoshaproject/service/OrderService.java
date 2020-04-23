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
    //1.通过url参数判断活动id，下单过程校验活动有效性（参数有则校验活动）
    //2.直接在下单接口内连表查询获取活动有效性（增加sql压力，不推荐）
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
