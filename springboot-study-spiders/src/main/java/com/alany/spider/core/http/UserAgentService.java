package com.alany.spider.core.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Slf4j
@Service
public class UserAgentService {

    public List<String> userAgentList = new ArrayList<>();

    @PostConstruct
    public void init() {
        List<String> list = new ArrayList<>();
        String defaultPath = this.getClass().getResource("/useragents.txt").getPath();
        File configFile = new File(defaultPath);
        try {
            list = FileUtils.readLines(configFile, "UTF-8");
            if (list != null && !list.isEmpty()) {
                //去重处理
                LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
                set.addAll(list);
                list.clear();
                list.addAll(set);
                userAgentList.addAll(list);
            }
        } catch (IOException e) {
            log.error("read user agent config file meet error:", e);
        }
        log.info("init userAgent config finished, userAgent list size=" + (list == null ? 0 : list.size()));
    }

    public List<String> getUserAgentList() {
        return userAgentList;
    }
}
