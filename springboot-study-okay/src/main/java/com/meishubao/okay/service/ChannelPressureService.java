package com.meishubao.okay.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 房间监听压力测试
 */
@Log4j2
@Component
public class ChannelPressureService {

    // 用户名密码认证(私密代理/独享代理)
    final static String ProxyUser = "t14068083966564";
    final static String ProxyPass = "kaoxfoar";

    // 代理IP、端口号
    final static String ProxyHost = "tps232.kdlapi.com";
    final static Integer ProxyPort = 15818;

    String LIVE_URL = "https://api.yinyuebao.com/api/play/callback/v1/help/createChannel";
    String DEFAULT_URL = "https://default.yinyuebao.com/api/play/callback/v1/help/createChannel";

    // LIVE, DEFAULT
    String profile = "LIVE";

    Vector<Integer> statusVector = new Vector<>();

    boolean status = true;
    boolean proxy = false;
    long startRoomId = 0;
    long size = 0;
    long sleep = 0;
    int concurrency = 10;

    public void count() {
        int total = statusVector.size();
        log.info("size:{}, concurrency:{}, sleep:{}, current:{}, status:{}, proxy:{}, start:{}", size, concurrency, sleep, total, status, proxy, startRoomId);
        Map<Integer, List<Integer>> map = statusVector.stream().collect(Collectors.groupingBy(Function.identity()));
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (CollUtil.isNotEmpty(entry.getValue())) {
                log.info("status:{}, size:{}", entry.getKey(), entry.getValue().size());
            }
        }
    }

    public void switchStatus(boolean status) {
        this.status = status;
    }

    public void start(Long startRoomId, Long size, Long sleep, Integer proxy, Integer concurrency) {
        this.startRoomId = startRoomId;
        this.size = size;
        this.sleep = sleep;
        if (Objects.isNull(proxy)) {
            proxy = 0;
        }
        if (proxy > 0) {
            this.proxy = true;
        }
        if (Objects.nonNull(concurrency)) {
            this.concurrency = concurrency;
        }

        CompletableFuture.runAsync(() -> {
            String url = url();
            List<Long> roomIds = new ArrayList<>();
            for (long i = 1; i <= size; i++) {
                roomIds.add(startRoomId + i);
            }
            List<List<Long>> partition = Lists.partition(roomIds, this.concurrency);
            for (List<Long> ids : partition) {
                if (!this.status) {
                    break;
                }
                if (this.proxy) {
                    batchSendProxy(ids, url);
                } else {
                    batchSend(ids, url);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep);
                } catch (InterruptedException e) {
                }
            }
        });
    }

    private void batchSend(List<Long> roomIds, String url) {
        roomIds.forEach(i -> CompletableFuture.runAsync(()-> {
            int status;
            try {
                status = HttpUtil.createPost(url).form("roomId", String.valueOf(i)).execute().getStatus();
            } catch (Exception e) {
                status = 400;
            }
            statusVector.add(status);
        }));
    }

    private void batchSendProxy(List<Long> roomIds, String url) {
        roomIds.forEach(i -> CompletableFuture.runAsync(()-> {
            // JDK 8u111版本后，目标页面为HTTPS协议，启用proxy用户密码鉴权
            System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
            // 设置请求验证信息
            Authenticator.setDefault(new ProxyAuthenticator(ProxyUser, ProxyPass));
            int status;
            try {
                status = HttpUtil.createPost(url).form("roomId", String.valueOf(i))
                        .setHttpProxy(ProxyHost, ProxyPort)
                        .timeout(7000)//设置超时，毫秒
                        .execute().getStatus();
            } catch (Exception e) {
                status = 400;
            }
            statusVector.add(status);
        }));
    }

    private String url() {
        return switch(profile){
            case "LIVE" -> LIVE_URL;
            default -> DEFAULT_URL;
        };
    }

    // 代理验证信息
    class ProxyAuthenticator extends Authenticator {
        private String user, password;

        public ProxyAuthenticator(String user, String password) {
            this.user     = user;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }

}