package com.meishubao.openfeign.model.live;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Created by JiYin Zhao
 * Date: 2021/4/29
 * Time: 15:29
 **/
@Data
public class UmsLiveActivityVo implements Serializable {

    private Integer businessId;
    private String liveName;
    private String liveIntroduce;
    private String liveRoomId;
    private Integer liveStatus;
    private String liveActivityId;
    private String channelId;
    private Long openTime;
    private Long closeTime;
    private Long remindTime;
    private String appCoverUrl;
    private String goodsInfos;
    private Integer pushTerminal;
    private String appUserType;
    private Integer shelfStatus;
    private Integer fakeNum;
    private String liveUrl;
}
