package com.miaoshaproject.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName ItemModel
 * @Description //TODO
 * @Author ccy
 * @Date 2020/2/26 13:58
 * @Version 1.0
 **/
public class ItemModel {
    //id title price stock description sales imgUrl
    private Integer id;
    @NotBlank(message="商品名称不能为空")
    private String title;
    //商业计算 - java.math.BigDecimal类来进行精确计算
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "商品价格必须大于0")
    private BigDecimal price;
    @NotNull(message = "商品库存不能为空")
    private Integer stock;
    @NotNull(message = "商品描述信息不能为空")
    private String description;

    //销量是通过其他方式获取 非入参
    private Integer sales;

    @NotNull(message = "商品图片信息不能为空")
    private String imgUrl;

    //使用聚合模型 将活动信息聚合为商品模块的一个属性里
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
