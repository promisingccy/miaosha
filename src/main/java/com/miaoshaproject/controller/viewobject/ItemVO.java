package com.miaoshaproject.controller.viewobject;

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
    private String desc;
    //销量是通过其他方式获取 非入参
    private Integer sales;
    private String imgUrl;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
