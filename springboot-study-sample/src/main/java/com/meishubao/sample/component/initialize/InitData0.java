package com.meishubao.sample.component.initialize;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lilu
 * 1：spring为bean提供了两种初始化bean的方式，实现InitializingBean接口，实现afterPropertiesSet方法，
 * 或者在配置文件中同过init-method指定，两种方式可以同时使用
 *
 * 2：实现InitializingBean接口是直接调用afterPropertiesSet方法，比通过反射调用init-method指定的方法
 * 效率相对来说要高点。但是init-method方式消除了对spring的依赖
 *
 * 3：如果调用afterPropertiesSet方法时出错，则不调用init-method指定的方法。
 */
@Order(1)
@Component
public class InitData0 implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }

}
