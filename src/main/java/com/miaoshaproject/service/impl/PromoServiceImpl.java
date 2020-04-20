package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.PromoDOMapper;
import com.miaoshaproject.dataobject.PromoDO;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PromoServiceImpl
 * @Description //TODO
 * @Author ccy
 * @Date 2020/4/20 15:16
 * @Version 1.0
 **/
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        // 获取活动 promoDO （使用 promoDOMapper 操作获取）
        // 使用到了 promoDOMapper.selectByItemId 先创建这个sql查询方法
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);

        //将 promoDO 转化为 领域模型 promoModel
        PromoModel promoModel = convertFromDataObject(promoDO);
        if(promoDO == null){
            return null;
        }
        if(promoModel.getStartDate().isAfterNow()){
            promoModel.setStatus(1);//未开始
        }
        if(promoModel.getEndDate().isBeforeNow()){
            promoModel.setStatus(3);//已结束
        }
        return promoModel;
    }

    private PromoModel convertFromDataObject(PromoDO promoDO){
        if(promoDO == null){
            return null;
        }
        //复制属性
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        //单独处理特殊的类型
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
        return promoModel;
    }
}
