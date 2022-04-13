package com.meishubao.youzan.service;

import com.youzan.cloud.open.sdk.core.client.auth.Token;

/**
 * @author lilu
 */
public interface YouZanOrderService {

    /**
     * 获取令牌，有效期7天
     *
     * @param shopId
     * @return
     */
    Token token(Long shopId);

}
