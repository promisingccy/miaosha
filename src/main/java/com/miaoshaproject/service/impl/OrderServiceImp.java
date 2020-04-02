package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.OrderDOMapper;
import com.miaoshaproject.dao.SequenceDOMapper;
import com.miaoshaproject.dataobject.OrderDO;
import com.miaoshaproject.dataobject.SequenceDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName OrderServiceImp
 * @Description //TODO
 * @Author ccy
 * @Date 2020/4/2 14:15
 * @Version 1.0
 **/
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        //======== 校验下单状态
        //下单商品是否存在
        ItemModel itemModel = itemService.getItemById(itemId);
        if(itemModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        //用户是否合法
        UserModel userModel = userService.getUserById(userId);
        if(userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }
        //购买数量是否正确
        if(amount <= 0 || amount > 99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "数量信息不正确");
        }

        //======== 落单减库存
        boolean result = itemService.decreaseStock(itemId, amount);
        if(!result){//扣减失败
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }
        //======== 订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setItemId(itemId);
        orderModel.setUserId(userId);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setAmount(amount);
        orderModel.setOrderPrice(itemModel.getPrice().multiply(new BigDecimal(amount)));
        //生成交易流水号
        orderModel.setId(generateOrderNo());
        OrderDO orderDO = this.convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        //======== 增加商品销量
        itemService.increaseSales(itemId, amount);
        //======== 返回前端
        return orderModel;
    }

//    public static void main(String[] args){
//        LocalDateTime now = LocalDateTime.now();
//        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
//        System.out.println(nowDate);//20200402
//        //时间戳 Instant timestamp = Instant.now();
//    }

    //不受外部事务影响 序列号每次调用都会增加
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected String generateOrderNo(){
        // 16位
        StringBuilder stringBuilder = new StringBuilder();
        // == 年月日8位 方便数据归档
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);

        // == 自增序列6位  使用表的for update控制
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        int sequence = sequenceDO.getCurrentValue();//当前号码
        //更新增量
        sequenceDO.setCurrentValue(sequenceDO.getStep() + sequence);
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        //用0补全6位字符串
        String sequenceStr = String.valueOf(sequence);
        for(int i=0; i < 6 - sequenceStr.length(); i++){
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);

        // == 分库分表2位 00-99 (userId % 100) 固定用户的数据落在固定的表上
        // 分散数据查询落单压力
        stringBuilder.append("00");

        return stringBuilder.toString();
    }

    private OrderDO convertFromOrderModel(OrderModel orderModel){
        if(orderModel == null){
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }


}
