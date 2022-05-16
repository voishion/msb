package com.meishubao.sample.service;

import com.meishubao.sample.exception.CustomRetryException;

/**
 * <a href="https://mp.weixin.qq.com/s/_3N5HdNoKEXgIDkN_Mn2sw">使用 @Retryable 注解优雅实现重处理</a>
 *
 * @author lilu
 */
public interface TestRetryService {

    int test(int code) throws CustomRetryException;

}
