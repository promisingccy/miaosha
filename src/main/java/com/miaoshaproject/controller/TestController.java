package com.miaoshaproject.controller;

import com.miaoshaproject.response.CommonReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestController
 * @Description //TODO
 * @Author ccy
 * @Date 2020/5/7 16:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    //redis操作对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //日志操作对象
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(){
        stringRedisTemplate.opsForValue().set("title", "redis test");
        String title = stringRedisTemplate.opsForValue().get("title");
        logger.debug("debug --- 测试redis操作："+title);
        logger.info("info --- 测试redis操作："+title);
        logger.error("error --- 测试redis操作："+title);
        logger.warn("warn --- 测试redis操作："+title);
        logger.trace("trace --- 测试redis操作："+title);
        return CommonReturnType.create(title);
    }
}
