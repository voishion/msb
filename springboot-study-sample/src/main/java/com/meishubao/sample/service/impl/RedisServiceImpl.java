package com.meishubao.sample.service.impl;

import com.meishubao.sample.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis服务接口定义了大多数常用操作实现
 *
 * @author lilu
 */
@Component
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, long time) {
        this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value, long time, TimeUnit unit) {
        this.redisTemplate.opsForValue().set(key, value, time, unit);
    }

    @Override
    public void set(Map<String, Object> map, long time, TimeUnit unit) {
        this.redisTemplate.executePipelined(new SessionCallback<>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                map.forEach((k, v) -> redisTemplate.opsForValue().set(k, v, time, unit));
                return null;
            }
        });
    }

    @Override
    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public List<Object> mget(List<String> keys) {
        return this.redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public boolean del(String key) {
        return Boolean.TRUE.equals(this.redisTemplate.delete(key));
    }

    @Override
    public Long del(List<String> keys) {
        return this.redisTemplate.delete(keys);
    }

    @Override
    public boolean expire(String key, long time) {
        return Boolean.TRUE.equals(this.redisTemplate.expire(key, time, TimeUnit.SECONDS));
    }

    @Override
    public boolean expire(String key, long time, TimeUnit unit) {
        return Boolean.TRUE.equals(this.redisTemplate.expire(key, time, unit));
    }

    @Override
    public Long ttl(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public Long ttl(String key, TimeUnit unit) {
        return this.redisTemplate.getExpire(key, unit);
    }

    @Override
    public boolean exist(String key) {
        return Boolean.TRUE.equals(!Long.valueOf(-2).equals(this.redisTemplate.getExpire(key)));
    }

    @Override
    public Long incr(String key, long delta) {
        return this.redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long decr(String key, long delta) {
        return this.redisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public Object hget(String key, String hashKey) {
        return this.redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void hset(String key, String hashKey, Object value) {
        this.redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public boolean hset(String key, String hashKey, Object value, long time) {
        this.redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }

    @Override
    public boolean hset(String key, String hashKey, Object value, long time, TimeUnit unit) {
        this.redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time, unit);
    }

    @Override
    public void hmset(String key, Map<String, Object> map) {
        this.redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map, long time) {
        this.redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map, long time, TimeUnit unit) {
        this.redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time, unit);
    }

    @Override
    public Map<Object, Object> hgetall(String key) {
        return this.redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Long hlen(String key) {
        return this.redisTemplate.opsForHash().size(key);
    }

    @Override
    public Long hdel(String key, Object... hashKeys) {
        return this.redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @Override
    public boolean hexits(String key, String hashKey) {
        return this.redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Long hincrby(String key, String hashKey, Long delta) {
        return this.redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Long hdecrby(String key, String hashKey, Long delta) {
        return this.redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    public Set<Object> smembers(String key) {
        return this.redisTemplate.opsForSet().members(key);
    }

    @Override
    public Long sadd(String key, Object... values) {
        return this.redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long sadd(String key, long time, Object... values) {
        Long count = this.redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Long sadd(String key, long time, TimeUnit unit, Object... values) {
        Long count = this.redisTemplate.opsForSet().add(key, values);
        expire(key, time, unit);
        return count;
    }

    @Override
    public boolean sismember(String key, Object value) {
        return Boolean.TRUE.equals(this.redisTemplate.opsForSet().isMember(key, value));
    }

    @Override
    public Long scard(String key) {
        return this.redisTemplate.opsForSet().size(key);
    }

    @Override
    public Long srem(String key, Object... values) {
        return this.redisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public List<Object> lrange(String key, long start, long end) {
        return this.redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long llen(String key) {
        return this.redisTemplate.opsForList().size(key);
    }

    @Override
    public Object lindex(String key, long index) {
        return this.redisTemplate.opsForList().index(key, index);
    }

    @Override
    public Long rpush(String key, Object value) {
        return this.redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long rpush(String key, Object... values) {
        return this.redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public Long lpush(String key, Object value) {
        return this.redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long lpush(String key, Object... values) {
        return this.redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Object lpop(String key) {
        return this.redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Object rpop(String key) {
        return this.redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Long lrem(String key, long count, Object value) {
        return this.redisTemplate.opsForList().remove(key, count, value);
    }

    @Override
    public boolean zadd(String key, Object value, double score) {
        return Boolean.TRUE.equals(redisTemplate.opsForZSet().add(key, value, score));
    }

    @Override
    public Long zadd(String key, Set<TypedTuple<Object>> tuples) {
        return redisTemplate.opsForZSet().add(key, tuples);
    }

    @Override
    public Long zrem(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    @Override
    public Double zincrby(String key, Object value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    @Override
    public Long zrank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    @Override
    public Long zrevrank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    @Override
    public Set<Object> zrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    @Override
    public Set<TypedTuple<Object>> zrangewithscores(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    @Override
    public Set<Object> zrangebyscore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    @Override
    public Set<Object> zrangebyscore(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<TypedTuple<Object>> zrangebyscorewithscores(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<TypedTuple<Object>> zrangebyscorewithscores(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Object> zrevrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    @Override
    public Set<TypedTuple<Object>> zrevrangewithscores(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    @Override
    public Set<Object> zrevrangebyscore(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    @Override
    public Set<TypedTuple<Object>> zrevrangebyscorewithscores(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Object> zrevrangebyscore(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<TypedTuple<Object>> zrevrangebyscorewithscores(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    @Override
    public Long zcard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    @Override
    public Double zscore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    @Override
    public Long zremrangebyrank(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    @Override
    public Long zremrangebyscore(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    @Override
    public Long zunionstore(String firstKey, String secondKey, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(firstKey, secondKey, destKey);
    }

    @Override
    public Long zunionstore(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    @Override
    public Long zinterstore(String firstKey, String secondKey, String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(firstKey, secondKey, destKey);
    }

    @Override
    public Long zinterstore(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

}
