package com.miaoshaproject.controller.viewobject;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @ClassName ItemVO
 * @Description //TODO
 * @Author ccy
 * @Date 2020/3/31 17:23
 * @Version 1.0
 **/
public class ItemVO {
    //需要确认 ItemModel 的字段是否都可以对前端展示

    //id title price stock desc sales imgUrl
    private Integer id;
    private String title;
    //商业计算 - java.math.BigDecimal类来进行精确计算
    private BigDecimal price;
    private Integer stock;
    private String description;
    //销量是通过其他方式获取 非入参
    private Integer sales;
    private String imgUrl;

    //活动模块-扩展字段
    private Integer promoStatus;//活动状态 0无 1待开始 2进行中 3已结束
    private BigDecimal promoPrice;//活动价格
    private Integer promoId;//活动关联id
    private String startDate;//前端倒计时使用

    public Integer getPromoStatus() {
        return promoStatus;
    }

    public void setPromoStatus(Integer promoStatus) {
        this.promoStatus = promoStatus;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
