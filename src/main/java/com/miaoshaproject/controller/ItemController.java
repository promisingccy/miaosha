package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @ClassName ItemController
 * @Description //TODO
 * @Author ccy
 * @Date 2020/3/31 17:08
 * @Version 1.0
 **/
@Controller("item")
@RequestMapping("/item")
//解决跨域问题
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    //创建商品
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {HTTP_CONTENT_TYPE})
    @ResponseBody
    public CommonReturnType createItem(
        @RequestParam(name = "title")String title,
        @RequestParam(name = "description")String description,
        @RequestParam(name = "price") BigDecimal price,
        @RequestParam(name = "stock")Integer stock,
        @RequestParam(name = "imgUrl")String imgUrl
    ) throws BusinessException {
        //封装请求 创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);
//        return CommonReturnType.create(itemModel);
        //写入数据库 返回成功的对象
        ItemModel itemModelForReturn = itemService.createItem(itemModel);
        //将对象转换为 VO
        ItemVO itemVO = this.convertItemVOFromItemModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    private ItemVO convertItemVOFromItemModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        return itemVO;
    }
}
