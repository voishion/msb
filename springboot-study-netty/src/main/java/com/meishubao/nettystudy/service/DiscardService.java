package com.meishubao.nettystudy.service;

import com.meishubao.nettystudy.socket.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author lilu
 */
@Slf4j
@Service
public class DiscardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscardService.class);

    public void discard (String message) {
        log.info("收到客户端消息:{}", message);
    }
    public void discard (Message message) {
        log.info("收到客户端消息:{}", message.toString());
    }

}
