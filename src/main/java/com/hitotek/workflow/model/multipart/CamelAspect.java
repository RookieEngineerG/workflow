package com.hitotek.workflow.model.multipart;

import com.hitotek.workflow.model.multipart.annotations.Camel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description
 * @date 2019-03-02
 */
@Aspect
@Component
@Slf4j
public class CamelAspect {
    @Autowired
    private MultiPartDataManager manager;


    /**
     *  数据返回之前直接转化为驼峰
     * @param joinPoint 切入点
     * @param camel 注解
     * @return 数据
     */
    @SuppressWarnings("unchecked")
    @Around(value = "@annotation(camel)")
    public Object convertWhenReturn(ProceedingJoinPoint joinPoint, Camel camel) {
        Object data = null;
        try {
             data = joinPoint.proceed();
             if (null != data) {
                 if (data instanceof MultipartData) {
                     ((MultipartData) data).translate2Camel();
                 }
                 if (data instanceof List) {
                    if (((List) data).size() > 0 && ((List) data).get(0) instanceof MultipartData) {
                        data = manager.convert((List<MultipartData>) data);
                    }
                 }
             }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return data;
    }

}
