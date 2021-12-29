package com.meishubao.okay.controller;

import com.meishubao.okay.service.ChannelPressureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilu
 */
@Log4j2
@RestController
@RequestMapping(value = "/channel/pressure")
@RequiredArgsConstructor
public class ChannelPressureController {

    private final ChannelPressureService channelPressureService;

    @GetMapping("/start")
    public String start(Long startRoomId, Long size, Long sleep, Integer proxy, Integer concurrency, String profile) {
        this.channelPressureService.switchStatus(true);
        this.channelPressureService.start(startRoomId, size, sleep, proxy, concurrency, profile);
        return "";
    }

    @GetMapping("/stop")
    public String stop() {
        this.channelPressureService.switchStatus(false);
        return "";
    }

    @GetMapping("/count")
    public String count() {
        this.channelPressureService.count();
        return "";
    }

}
