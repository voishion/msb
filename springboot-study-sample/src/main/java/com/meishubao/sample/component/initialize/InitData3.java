package com.meishubao.sample.component.initialize;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lilu
 */
@Order(1)
@Component
public class InitData3 {

    static {
        System.out.println(1);
    }

    public InitData3() {
        System.out.println(2);
    }

    @PostConstruct
    public void init() {
        System.out.println(3);
    }

}
