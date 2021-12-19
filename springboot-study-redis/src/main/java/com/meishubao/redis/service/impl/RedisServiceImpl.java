package com.meishubao.redis.service.impl;

import com.meishubao.redis.service.RedisService;
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
	public void set(String key, Object value, long time) {
		this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
	}

	@Override
	public void set(String key, Object value, long time, TimeUnit unit) {
		this.redisTemplate.opsForValue().set(key, value, time, unit);
	}

	@Override
	public void set(Map<String, Object> batchs, long time, TimeUnit unit) {
		this.redisTemplate.executePipelined(new SessionCallback<Object>() {
			@Override
			public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
				batchs.forEach((k, v) -> redisTemplate.opsForValue().set(k, v, time, unit));
				return null;
			}
		});
	}

	@Override
	public void set(String key, Object value) {
		this.redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public Object get(String key) {
		return this.redisTemplate.opsForValue().get(key);
	}

	@Override
	public List<Object> get(List<String> keys) {
		return this.redisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public Boolean del(String key) {
		return this.redisTemplate.delete(key);
	}

	@Override
	public Long del(Collection<String> keys) {
		return this.redisTemplate.delete(keys);
	}

	@Override
	public Boolean expire(String key, long time) {
		return this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
	}

	@Override
	public Boolean expire(String key, long time, TimeUnit unit) {
		return this.redisTemplate.expire(key, time, unit);
	}

	@Override
	public Long getExpire(String key) {
		return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	@Override
	public Long getExpire(String key, TimeUnit unit) {
		return this.redisTemplate.getExpire(key, unit);
	}

	@Override
	public Boolean hasKey(String key) {
		return this.redisTemplate.getExpire(key) != -2;
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
	public Object hGet(String key, String hashKey) {
		return this.redisTemplate.opsForHash().get(key, hashKey);
	}

	@Override
	public Boolean hSet(String key, String hashKey, Object value, long time) {
		this.redisTemplate.opsForHash().put(key, hashKey, value);
		return expire(key, time);
	}

	@Override
	public void hSet(String key, String hashKey, Object value) {
		this.redisTemplate.opsForHash().put(key, hashKey, value);
	}

	@Override
	public Map<Object, Object> hGetAll(String key) {
		return this.redisTemplate.opsForHash().entries(key);
	}

	@Override
	public Long hSize(String key) {
		return this.redisTemplate.opsForHash().size(key);
	}

	@Override
	public Boolean hSetAll(String key, Map<String, Object> map, long time) {
		this.redisTemplate.opsForHash().putAll(key, map);
		return expire(key, time);
	}

	@Override
	public void hSetAll(String key, Map<String, Object> map) {
		this.redisTemplate.opsForHash().putAll(key, map);
	}

	@Override
	public void hDel(String key, Object... hashKey) {
		this.redisTemplate.opsForHash().delete(key, hashKey);
	}

	@Override
	public Boolean hHasKey(String key, String hashKey) {
		return this.redisTemplate.opsForHash().hasKey(key, hashKey);
	}

	@Override
	public Long hIncr(String key, String hashKey, Long delta) {
		return this.redisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	@Override
	public Long hDecr(String key, String hashKey, Long delta) {
		return this.redisTemplate.opsForHash().increment(key, hashKey, -delta);
	}

	@Override
	public Set<Object> sMembers(String key) {
		return this.redisTemplate.opsForSet().members(key);
	}

	@Override
	public Long sAdd(String key, Object... values) {
		return this.redisTemplate.opsForSet().add(key, values);
	}

	@Override
	public Long sAdd(String key, long time, Object... values) {
		Long count = this.redisTemplate.opsForSet().add(key, values);
		expire(key, time);
		return count;
	}

	@Override
	public Boolean sIsMember(String key, Object value) {
		return this.redisTemplate.opsForSet().isMember(key, value);
	}

	@Override
	public Long sSize(String key) {
		return this.redisTemplate.opsForSet().size(key);
	}

	@Override
	public Long sRemove(String key, Object... values) {
		return this.redisTemplate.opsForSet().remove(key, values);
	}

	@Override
	public List<Object> lRange(String key, long start, long end) {
		return this.redisTemplate.opsForList().range(key, start, end);
	}

	@Override
	public Long lSize(String key) {
		return this.redisTemplate.opsForList().size(key);
	}

	@Override
	public Object lIndex(String key, long index) {
		return this.redisTemplate.opsForList().index(key, index);
	}

	@Override
	public Long lPush(String key, Object value) {
		return this.redisTemplate.opsForList().rightPush(key, value);
	}

	@Override
	public Long leftPush(String key, Object value) {
		return this.redisTemplate.opsForList().leftPush(key, value);
	}

	@Override
	public Long lPush(String key, Object value, long time) {
		Long index = this.redisTemplate.opsForList().rightPush(key, value);
		expire(key, time);
		return index;
	}

	@Override
	public Long leftPush(String key, Object value, long time) {
		Long index = this.redisTemplate.opsForList().leftPush(key, value);
		expire(key, time);
		return index;
	}

	@Override
	public Long lPushAll(String key, Object... values) {
		return this.redisTemplate.opsForList().rightPushAll(key, values);
	}

	@Override
	public Long leftPushAll(String key, Object... values) {
		return this.redisTemplate.opsForList().leftPushAll(key, values);
	}

	@Override
	public Long lPushAll(String key, Long time, Object... values) {
		Long count = this.redisTemplate.opsForList().rightPushAll(key, values);
		expire(key, time);
		return count;
	}

	@Override
	public Long leftPushAll(String key, Long time, Object... values) {
		Long count = this.redisTemplate.opsForList().leftPushAll(key, values);
		expire(key, time);
		return count;
	}

	@Override
	public Object leftPop(String key) {
		return this.redisTemplate.opsForList().leftPop(key);
	}

	@Override
	public Object rightPop(String key) {
		return this.redisTemplate.opsForList().rightPop(key);
	}

	@Override
	public Long lRemove(String key, long count, Object value) {
		return this.redisTemplate.opsForList().remove(key, count, value);
	}

	@Override
	public boolean zsAdd(String key, Object value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	@Override
	public Long zsAdd(String key, Set<TypedTuple<Object>> tuples) {
		return redisTemplate.opsForZSet().add(key, tuples);
	}

	@Override
	public Long zsRemove(String key, Object... values) {
		return redisTemplate.opsForZSet().remove(key, values);
	}

	@Override
	public Double zsIncrementScore(String key, Object value, double score) {
		return redisTemplate.opsForZSet().incrementScore(key, value, score);
	}

	@Override
	public Long zsRank(String key, Object value) {
		return redisTemplate.opsForZSet().rank(key, value);
	}

	@Override
	public Long zsReverseRank(String key, Object value) {
		return redisTemplate.opsForZSet().reverseRank(key, value);
	}

	@Override
	public Set<TypedTuple<Object>> zsRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}

	@Override
	public Set<Object> zsRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	@Override
	public Set<Object> zsRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max);
	}

	@Override
	public Set<TypedTuple<Object>> zsRangeByScoreWithScores(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Object> zsRangeByScore(String key, double min, double max, long offset, long count) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<TypedTuple<Object>> zsRangeByScoreWithScores(String key, double min, double max, long offset, long count) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Object> zsReverseRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	@Override
	public Set<TypedTuple<Object>> zsReverseRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
	}

	@Override
	public Set<Object> zsReverseRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	@Override
	public Set<TypedTuple<Object>> zsReverseRangeByScoreWithScores(String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Object> zsReverseRangeByScore(String key, double min, double max, long offset, long count) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<TypedTuple<Object>> zsReverseRangeByScoreWithScores(String key, double min, double max, long offset, long count) {
		return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public long zsCount(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	@Override
	public long zsSize(String key) {
		return redisTemplate.opsForZSet().size(key);
	}

	@Override
	public Double zsScore(String key, Object value) {
		return redisTemplate.opsForZSet().score(key, value);
	}

	@Override
	public Long zsRemoveRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	@Override
	public Long zsRemoveRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}

	@Override
	public Long zsUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
	}

	@Override
	public Long zsUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
	}

	@Override
	public Long zsIntersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
	}

	@Override
	public Long zsIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
	}

}
