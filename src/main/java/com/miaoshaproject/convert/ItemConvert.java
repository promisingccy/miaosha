package com.miaoshaproject.convert;


import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.dataobject.ItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName ItemConvert
 * @Description //TODO
 * @Author ccy
 * @Date 2020/5/8 15:29
 * @Version 1.0
 **/
@Mapper
public interface ItemConvert {
    ItemConvert INSTANCE = Mappers.getMapper(ItemConvert.class);
    ItemVO convert(ItemDO itemDO);


}
