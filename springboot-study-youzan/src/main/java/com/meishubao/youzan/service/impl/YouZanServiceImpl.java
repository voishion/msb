package com.meishubao.youzan.service.impl;

import com.google.common.base.Throwables;
import com.meishubao.youzan.config.YouZanConfig;
import com.meishubao.youzan.service.YouZanService;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lilu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class YouZanServiceImpl implements YouZanService {

    private final YouZanConfig youZanConfig;

    private final DefaultYZClient youZanClient;

    @Override
    public Token token(Long shopId) {
        Token token = null;
        try {
            YouZanConfig.YzConfig config = youZanConfig.getMap().get("config1");
            TokenParameter tokenParameter = TokenParameter.self()
                    .clientId(config.getClientId())
                    .clientSecret(config.getClientSecret())
                    .grantId(String.valueOf(shopId))
                    .refresh(true)
                    .build();
            OAuthToken oAuthToken = youZanClient.getOAuthToken(tokenParameter);
            token = new Token(oAuthToken.getAccessToken());
        } catch (Exception e) {
            log.info("获取令牌异常, error=>{}", Throwables.getStackTraceAsString(e));
        }
        return token;
    }

}
