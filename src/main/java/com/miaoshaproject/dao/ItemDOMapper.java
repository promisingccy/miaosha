package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.ItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    int insert(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    int insertSelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    ItemDO selectByPrimaryKey(Integer id);

    List<ItemDO> listItem();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    int updateByPrimaryKeySelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Feb 26 15:49:25 CST 2020
     */
    int updateByPrimaryKey(ItemDO record);

    int increaseSales(@Param("itemId")Integer itemId, @Param("amount")Integer amount);
}