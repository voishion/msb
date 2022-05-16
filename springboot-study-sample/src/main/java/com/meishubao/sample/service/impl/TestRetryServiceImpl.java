package com.meishubao.sample.service.impl;

import com.meishubao.sample.exception.CustomRetryException;
import com.meishubao.sample.service.TestRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author lilu
 */
@Slf4j
@Service
public class TestRetryServiceImpl implements TestRetryService {

    @Override
    @Retryable(value = CustomRetryException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int test(int code) throws CustomRetryException {
        log.info("test被调用, code:{}, 时间:{}", code, LocalTime.now());
        if (code == 0) {
            throw new CustomRetryException("情况不对头");
        }
        log.info("test被调用, 情况对头了");
        return 200;
    }

    //@Recover
    //public int testRecover(CustomRetryException e, int code){
    //    log.info("兜底方法被执行, error:{}, code:{}, 时间:{}", e.getMessage(), code, LocalTime.now());;
    //    // 记日志到数据库 或者调用其余的方法
    //    return 400;
    //}

}
