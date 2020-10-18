package com.soft1851.usercenter.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Yujie_Zhao
 * @ClassName CheckAuthorization
 * @Description 鉴权注解
 * @Date 2020/10/13  14:47
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
