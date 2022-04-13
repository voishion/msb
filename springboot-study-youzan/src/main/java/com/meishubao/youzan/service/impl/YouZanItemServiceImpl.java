package com.meishubao.youzan.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.meishubao.youzan.service.YouZanItemService;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.gen.v1_0_0.api.YouzanItemDetailGet;
import com.youzan.cloud.open.sdk.gen.v1_0_0.model.YouzanItemDetailGetParams;
import com.youzan.cloud.open.sdk.gen.v1_0_0.model.YouzanItemDetailGetResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lilu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class YouZanItemServiceImpl implements YouZanItemService {

    private final DefaultYZClient youZanClient;

    @Override
    public YouzanItemDetailGetResult getItemDetail(Token token, Long itemId) {
        YouzanItemDetailGetResult result = null;
        try {
            YouzanItemDetailGet youzanItemDetailGet = new YouzanItemDetailGet();
            //创建参数对象,并设置参数
            YouzanItemDetailGetParams youzanItemDetailGetParams = new YouzanItemDetailGetParams();
            youzanItemDetailGetParams.setItemId(itemId);
            youzanItemDetailGet.setAPIParams(youzanItemDetailGetParams);

            result = youZanClient.invoke(youzanItemDetailGet, token, YouzanItemDetailGetResult.class);
            log.info("查询单商品明细, result=>{}", JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("查询单商品明细异常, error=>{}", Throwables.getStackTraceAsString(e));
        }
        return result;
    }

}
