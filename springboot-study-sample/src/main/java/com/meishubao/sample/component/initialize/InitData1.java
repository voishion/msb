package com.meishubao.sample.component.initialize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lilu
 */
@Slf4j
@Order(1)
@Component
public class InitData1 {

    static {
        log.warn("{}|{}...", InitData1.class.getName(), 1);
    }

    public InitData1() {
        log.warn("{}|{}...", this.getClass().getName(), 2);
    }

    @PostConstruct
    public void init() {
        log.warn("{}|{}...", this.getClass().getName(), 3);
    }

}
