package com.meishubao.youzan.service;

import com.meishubao.youzan.SpringbootStudyYouzanApplication;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStudyYouzanApplication.class)
class YouZanOrderServiceTest {

    @Autowired
    private YouZanOrderService youZanOrderService;

    @Test
    void token() {
        Token token = youZanOrderService.token(96222220L);
        log.info("token:{}", token);
    }
}