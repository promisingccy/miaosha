package com.miaoshaproject.dataobject;

public class ItemDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.id
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.title
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.price
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.sales
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    private Integer sales;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.desc
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    private String desc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.img_url
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    private String imgUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.id
     *
     * @return the value of item.id
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.id
     *
     * @param id the value for item.id
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.title
     *
     * @return the value of item.title
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.title
     *
     * @param title the value for item.title
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.price
     *
     * @return the value of item.price
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.price
     *
     * @param price the value for item.price
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.sales
     *
     * @return the value of item.sales
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.sales
     *
     * @param sales the value for item.sales
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.desc
     *
     * @return the value of item.desc
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.desc
     *
     * @param desc the value for item.desc
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.img_url
     *
     * @return the value of item.img_url
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.img_url
     *
     * @param imgUrl the value for item.img_url
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}