package com.miaoshaproject.controller;

import com.miaoshaproject.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(){
        stringRedisTemplate.opsForValue().set("title", "redis test");
        String title = stringRedisTemplate.opsForValue().get("title");
        return CommonReturnType.create(title);
    }
}
