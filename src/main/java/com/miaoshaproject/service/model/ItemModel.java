package com.miaoshaproject.service.model;

import java.math.BigDecimal;

/**
 * @ClassName ItemModel
 * @Description //TODO
 * @Author ccy
 * @Date 2020/2/26 13:58
 * @Version 1.0
 **/
public class ItemModel {
    //id title price stock desc sales imgUrl
    private Integer id;
    private String title;
    //商业计算 - java.math.BigDecimal类来进行精确计算
    private BigDecimal price;
    private Integer stock;
    private String desc;
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
