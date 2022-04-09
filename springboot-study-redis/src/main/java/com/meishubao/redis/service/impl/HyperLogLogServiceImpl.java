package com.meishubao.redis.service.impl;

import com.meishubao.redis.service.HyperLogLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RHyperLogLog;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HyperLogLog 是一种概率数据结构，它使用概率算法来统计集合的近似基数。而它算法的最本源则是伯努利过程。
 * <a href="https://mp.weixin.qq.com/s/GBXicIZEZFN-Ofs3gs2_DQ">相关文档</a>
 *
 * <p>在移动互联网的业务场景中数据量很大，我们需要保存这样的信息：一个 key 关联了一个数据集合，同时对这个数据集合做统计。
 *  <ul>
 *      <li>统计一个 APP 的日活、月活数；</li>
 *      <li>统计一个页面的每天被多少个不同账户访问量（Unique Visitor，UV）；</li>
 *      <li>统计用户每天搜索不同词条的个数；</li>
 *      <li>统计注册 IP 数。</li>
 *  </ul>
 *
 * @author lilu
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class HyperLogLogServiceImpl implements HyperLogLogService {

    private final RedissonClient redissonClient;

    @Override
    public <T> void add(String logName, T item) {
        RHyperLogLog<T> hyperLogLog = redissonClient.getHyperLogLog(logName);
        hyperLogLog.add(item);
    }

    @Override
    public <T> void addAll(String logName, List<T> items) {
        RHyperLogLog <T> hyperLogLog = redissonClient.getHyperLogLog(logName);
        hyperLogLog.addAll(items);
    }

    @Override
    public <T> void merge(String logName, String... otherLogNames) {
        RHyperLogLog <T> hyperLogLog = redissonClient.getHyperLogLog(logName);
        hyperLogLog.mergeWith(otherLogNames);
    }

    @Override
    public <T> long count(String logName) {
        RHyperLogLog <T> hyperLogLog = redissonClient.getHyperLogLog(logName);
        return hyperLogLog.count();
    }

}
