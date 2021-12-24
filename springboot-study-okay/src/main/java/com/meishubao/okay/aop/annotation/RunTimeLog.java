package com.meishubao.okay.aop.annotation;

import java.lang.annotation.*;

/**
 * 执行时间日志
 * <coed><br><br>@RunTimeLog<br>
 *  public String getCode(String name) {<br>
 *  }<code/><br>
 *
 * @author lilu
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RunTimeLog {

    /**
     * 日志名称
     *
     * @return
     */
    String value() default "";

}
