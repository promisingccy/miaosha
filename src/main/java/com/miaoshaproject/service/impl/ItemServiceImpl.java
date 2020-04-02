package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.ItemDOMapper;
import com.miaoshaproject.dao.ItemStockDOMapper;
import com.miaoshaproject.dataobject.ItemDO;
import com.miaoshaproject.dataobject.ItemStockDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ItemServiceImpl
 * @Description //TODO
 * @Author ccy
 * @Date 2020/2/26 16:04
 * @Version 1.0
 **/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private ItemDOMapper itemDOMapper;
    @Autowired
    private ItemStockDOMapper itemStockDOMapper;


    @Override
    @Transactional //开启事务
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        // ==== 校验入参
        // 1引入ValidatorImpl类注解
        // 2ItemModel 增加@NotBlank等注解
        // 3验证
        ValidationResult result = validator.validate(itemModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        // ==== 转换 itemmodel -> dataobject
        //1. 转换商品对象
        ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);
        //2. 商品写入数据库( DOMapper 操作DO )
        itemDOMapper.insertSelective(itemDO);//写入成功后 DO 会携带数据库id
        itemModel.setId(itemDO.getId());
        //3. 转换库存对象
        ItemStockDO itemStockDO = this.convertItemStockDOFromItemMode(itemModel);
        //4. 商品库存写入数据库( DOMapper 操作DO )
        itemStockDOMapper.insertSelective(itemStockDO);

        // ==== 通过id 查询返回创建完成的对象
        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        //获取商品列表
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        //相当于php的闭包函数 对list每一个元素进行相同处理
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            //获取商品库存
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            //将商品和库存结合返回
            return this.convertItemModelFromDataObject(itemDO, itemStockDO);
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    //通过 id 获取商品对象
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if(itemDO == null){
            return null;
        }
        //获取库存 - 新增语句步骤 ItemStockDOMapper.xml -> ItemStockDOMapper.java
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
        //将 DO 转换为 model 返回前端
        return this.convertItemModelFromDataObject(itemDO, itemStockDO);
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int affectedRow = itemStockDOMapper.decreaseStock(itemId, amount);
        if(affectedRow > 0){//更新成功
            return true;
        }
        return false;
    }

    //增加销量
    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        itemDOMapper.increaseSales(itemId, amount);
    }

    //============= 辅助方法
    //将 DataObject 转换为 model 返回前端
    private ItemModel convertItemModelFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO){
        ItemModel itemModel = new ItemModel();
        //赋值基础字段
        BeanUtils.copyProperties(itemDO, itemModel);
        //手动转换 price 的 BigDecimal 类型
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        //赋值库存字段
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }

    //将入参的 ItemModel 转换为 ItemDO
    private ItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        //复制对象属性
        ItemDO itemDO = new ItemDO();
        //  BeanUtils不会复制类型不一样的对象
        BeanUtils.copyProperties(itemModel, itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }

    //将库存对象 Model 转换为 DO
    private ItemStockDO convertItemStockDOFromItemMode(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());//获取商品id给库存表
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }



}
