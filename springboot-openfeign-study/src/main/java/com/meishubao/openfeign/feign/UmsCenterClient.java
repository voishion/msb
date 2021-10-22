package com.meishubao.openfeign.feign;

import com.meishubao.openfeign.config.FeignConfig;
import com.meishubao.openfeign.model.Response;
import com.meishubao.openfeign.model.live.UmsLiveActivityVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * UMS平台Feign接口
 *
 * @author admin
 */
@FeignClient(name = "UmsCenterClient", url = "http://ums-test-api.meixiu.mobi/api", configuration = FeignConfig.class)
public interface UmsCenterClient {

    /**
     * 获取直播任务（当前时间在开始/结束之间的）
     *
     * @param businessId 业务id
     * @param appUserType 用户类型012
     * @param userId 用户id
     * @param nickName 昵称
     * @param type 火山:1
     * @return
     */
    @PostMapping(value = "/play/v1/live/selectLiveInMiddleBeginAndEnd")
    Response<List<UmsLiveActivityVo>> getUmsLiveActivitiesList(@RequestParam(value = "businessId") Integer businessId,
                                                               @RequestParam(value = "appUserType", required = false) Integer appUserType,
                                                               @RequestParam(value = "userId", required = false) String userId,
                                                               @RequestParam(value = "nickName", required = false) String nickName,
                                                               @RequestParam(value = "type", required = false) Integer type);
    /**
     * 获取直播任务（当前时间在开始/结束之间的）
     *
     * @param businessIds 业务id
     * @param appUserType 用户类型012
     * @param userId 用户id
     * @param nickName 昵称
     * @param type 火山:1
     * @return
     */
    @PostMapping(value = "/play/v1/live/selectLiveInMiddleBeginAndEndNew")
    Response<List<UmsLiveActivityVo>> getUmsLiveActivitiesListNew(@RequestParam(value = "businessIds") List<Integer> businessIds,
                                                                  @RequestParam(value = "appUserType", required = false) Integer appUserType,
                                                                  @RequestParam(value = "userId", required = false) String userId,
                                                                  @RequestParam(value = "nickName", required = false) String nickName,
                                                                  @RequestParam(value = "type", required = false) Integer type);

    /**
     * 拼接直播地址链接
     *
     * @param type 火山:1
     * @param liveActivityId 直播任务id
     * @param userId 用户id
     * @param nickName 昵称
     * @return
     */
    @GetMapping(value = "/play/v1/live/splicingUrlLogin")
    Response<String> getUmsLiveUrl(@RequestParam(value = "type") Integer type,
                           @RequestParam(value = "liveActivityId") String liveActivityId,
                           @RequestParam(value = "userId") String userId,
                           @RequestParam(value = "nickName") String nickName);

}
