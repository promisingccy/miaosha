package com.miaoshaproject;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
//1. 开启spring自动化配置
//@EnableAutoConfiguration
//5. 替换注解，托管给spring boot，并标注该类为主启动类，声明扫描范围
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
//3. 配置该类为一个rest控制器
@RestController
//6. 声明 mapper 扫描范围
@MapperScan("com.miaoshaproject.dao")
public class App 
{
    //7. @Autowired 完成自动装配（在使用@Autowired时，首先在容器中查询对应类型的bean
    // 如果查询结果刚好为一个，就将该bean装配给@Autowired指定的数据，
    // 如果查询的结果不止一个，那么@Autowired会根据名称来查找。）
    @Autowired
    private UserDOMapper userDOMapper;

    //4. 增加路由"/"对应到此方法
    @RequestMapping("/")
    public String home(){
        UserDO userDo = userDOMapper.selectByPrimaryKey(1);
        if (userDo != null) {
            return userDo.getName();
        }
        return "用户对象不存在";
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //2. 启动了一个web应用
        SpringApplication.run(App.class, args);
    }
}
