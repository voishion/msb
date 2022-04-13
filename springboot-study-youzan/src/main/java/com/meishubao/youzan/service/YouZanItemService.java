package com.meishubao.youzan.service;

import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.gen.v1_0_0.model.YouzanItemDetailGetResult;

/**
 * @author lilu
 */
public interface YouZanItemService {

    /**
     * <a href="https://doc.youzanyun.com/detail/API/0/2783">查询单商品明细接口-推荐使用</a>
     *
     * @param token
     * @param itemId
     * @return
     */
    YouzanItemDetailGetResult getItemDetail(Token token, Long itemId);

}
