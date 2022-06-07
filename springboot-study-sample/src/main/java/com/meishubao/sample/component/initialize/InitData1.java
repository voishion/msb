package com.meishubao.sample.component.initialize;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lilu
 */
@Order(1)
@Component
public class InitData1 implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

}
