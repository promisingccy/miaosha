package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

/**
 * @ClassName PromoService
 * @Description //TODO
 * @Author ccy
 * @Date 2020/4/20 15:13
 * @Version 1.0
 **/
public interface PromoService {
    //根据itemId获取即将进行的或正在进行的活动
    PromoModel getPromoByItemId(Integer itemId);
}
