package com.meishubao.youzan.service;

import com.youzan.cloud.open.sdk.core.client.auth.Token;

/**
 * @author lilu
 */
public interface YouZanService {

    /**
     * <a href="https://doc.youzanyun.com/resource/develop-guide/41355/41512">获取令牌，有效期7天</a>
     *
     * @param shopId
     * @return
     */
    Token token(Long shopId);

}
