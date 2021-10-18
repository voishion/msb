package com.meishubao.openfeign.service;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.meishubao.openfeign.model.Response;
import com.meishubao.openfeign.model.live.UmsLiveActivityVo;
import com.meishubao.openfeign.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lilu
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsCenterClientTest {

    @Autowired
    private UmsCenterClient umsCenterClient;

    @Test
    public void umsCenterClientTest() {
        try {
            Response<List<UmsLiveActivityVo>> list1 = umsCenterClient.getUmsLiveActivitiesList(105, null, null, null, 1);
            log.info("list1:{}", JSONUtils.safeToJson(list1));

            Response<List<UmsLiveActivityVo>> list2 = umsCenterClient.getUmsLiveActivitiesListNew(Lists.newArrayList(105, 104, 103), null, null, null, 1);
            log.info("list2:{}", JSONUtils.safeToJson(list2));

            Response<String> liveUrl = umsCenterClient.getUmsLiveUrl(1, "1713404502348823", "597065693070172160", "SSS李科");
            log.info("liveUrl:{}", JSONUtils.safeToJson(liveUrl));
        } catch (Exception e) {
            log.error("getLiveLink error, error={}", Throwables.getStackTraceAsString(e));
        }
    }

}
