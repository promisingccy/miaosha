package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @ClassName ValidatorImpl
 * @Description //TODO
 * @Author ccy
 * @Date 2019/12/24 10:56
 * @Version 1.0
 **/
@Component  //声明为一个spring bean
public class ValidatorImpl implements InitializingBean {

    //这个validator是真正实现了javax的一套验证工具
    private Validator validator;

    //实现校验方法并返回校验结果
    public ValidationResult validate(Object bean){
        //存储 验证错误信息 的对象result
        final ValidationResult result = new ValidationResult();
        //将参数对象进行验证 返回错误信息set
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        //判断是否出现错误
        if(constraintViolationSet.size() > 0){
            //有错误，标记
            result.setHasErrors(true);
            //遍历 constraintViolationSet，获取其中的错误信息
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                //将错误信息赋值给我们封装的 ValidationResult 类中
                result.getErrorMsgMap().put(propertyName, errMsg);
            });
        }
        return result;
    }


    //spring bean初始化完成之后，会回调这个afterPropertiesSet方法
    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂初始化方式进行实例化
        //并赋值给validator变量
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
