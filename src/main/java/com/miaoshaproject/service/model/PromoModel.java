package com.miaoshaproject.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;


/**
 * @ClassName PromoModel
 * @Description //TODO
 * @Author ccy
 * @Date 2020/4/3 14:16
 * @Version 1.0
 **/
public class PromoModel {
    private Integer id;
    //业务逻辑字段（非sql字段） 秒杀活动状态 1未开始 2进行中 3已结束
    private Integer status;
    //秒杀活动名称
    private String promoName;
    //秒杀活动开始时间
    private DateTime startDate;
    //秒杀活动结束时间
    private DateTime endDate;
    //秒杀商品
    private Integer itemId;
    //秒杀商品价格
    private BigDecimal promoItemPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }


    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
