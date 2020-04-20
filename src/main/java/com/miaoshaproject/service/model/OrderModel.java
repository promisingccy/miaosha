package com.miaoshaproject.service.model;

import java.math.BigDecimal;

/**
 * @ClassName OrderModel
 * @Description //TODO
 * @Author ccy
 * @Date 2020/4/2 11:26
 * @Version 1.0
 **/
//订单交易模型
public class OrderModel {
    private String id;//单号为字符型（例如 uuid 日期时间）
    private Integer userId;//用户id
    private Integer itemId;//商品id
    private BigDecimal itemPrice;//商品单价 promoId若非空,表示活动价格
    private Integer amount;//购买数量
    private BigDecimal orderPrice;//订单金额 promoId若非空,表示活动价格

    //扩展字段
    private Integer promoId;//promoId若非空,表示活动价格

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
