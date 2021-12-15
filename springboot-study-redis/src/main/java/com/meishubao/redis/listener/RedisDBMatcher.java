package com.meishubao.redis.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lilu
 */
public class RedisDBMatcher {

    public static void main(String[] args) {
        String p = "__keyevent@12__:expired";
        Matcher matcher = Pattern.compile("__keyevent@(.+?)__:expired").matcher(p);
        if (matcher.find()) {
            String roomId = matcher.group(1);
            System.out.println(roomId);
        }
    }

}
