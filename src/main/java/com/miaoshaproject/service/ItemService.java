package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;

/**
 * @ClassName ItemService
 * @Description //TODO
 * @Author ccy
 * @Date 2020/2/26 15:59
 * @Version 1.0
 **/
public interface ItemService {
    //创建商品 返回商品对象
    ItemModel createItem(ItemModel itemModel) throws BusinessException;
    //商品列表 条件先忽略
    List<ItemModel> listItem();
    //商品详情 通过id返回商品对象
    ItemModel getItemById(Integer id);
    //库存扣减
    boolean decreaseStock(Integer itemId, Integer amount)throws BusinessException;
    //增加销量
    void increaseSales(Integer itemId, Integer amount)throws BusinessException;
}
