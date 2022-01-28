package com.meishubao.redis.service;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis服务接口定义了大多数常用操作
 *
 * @author lilu
 */
public interface RedisService {
	/**
	 * 保存属性
	 */
	void set(String key, Object value, long time);

	/**
	 * 保存属性
	 */
	void set(String key, Object value, long time, TimeUnit unit);

	/**
	 * 保存属性[批量]
	 *
	 * @param batchs 多个数据key-&gt;value
	 */
	void set(Map<String, Object> batchs, long time, TimeUnit unit);

	/**
	 * 保存属性
	 */
	void set(String key, Object value);

	/**
	 * 获取属性
	 */
	Object get(String key);

	/**
	 * 获取属性[批量]
	 */
	List<Object> get(List<String> keys);

	/**
	 * 删除属性
	 */
	Boolean del(String key);

	/**
	 * 批量删除属性
	 */
	Long del(Collection<String> keys);

	/**
	 * 设置过期时间
	 */
	Boolean expire(String key, long time);

	/**
	 * 设置过期时间
	 */
	Boolean expire(String key, long time, TimeUnit unit);

	/**
	 * 获取过期时间
	 */
	Long getExpire(String key);

	/**
	 * 获取过期时间
	 */
	Long getExpire(String key, TimeUnit unit);

	/**
	 * 判断是否有该属性
	 */
	Boolean hasKey(String key);

	/**
	 * 按delta递增
	 */
	Long incr(String key, long delta);

	/**
	 * 按delta递减
	 */
	Long decr(String key, long delta);

	/**
	 * 获取Hash结构中的属性
	 */
	Object hGet(String key, String hashKey);

	/**
	 * 向Hash结构中放入一个属性
	 */
	Boolean hSet(String key, String hashKey, Object value, long time);

	/**
	 * 向Hash结构中放入一个属性
	 */
	void hSet(String key, String hashKey, Object value);

	/**
	 * 直接获取整个Hash结构
	 */
	Map<Object, Object> hGetAll(String key);

	/**
	 * 获取Hash结构的长度
	 */
	Long hSize(String key);

	/**
	 * 直接设置整个Hash结构
	 */
	Boolean hSetAll(String key, Map<String, Object> map, long time);

	/**
	 * 直接设置整个Hash结构
	 */
	void hSetAll(String key, Map<String, Object> map);

	/**
	 * 删除Hash结构中的属性
	 */
	void hDel(String key, Object... hashKey);

	/**
	 * 判断Hash结构中是否有该属性
	 */
	Boolean hHasKey(String key, String hashKey);

	/**
	 * Hash结构中属性递增
	 */
	Long hIncr(String key, String hashKey, Long delta);

	/**
	 * Hash结构中属性递减
	 */
	Long hDecr(String key, String hashKey, Long delta);

	/**
	 * 获取Set结构
	 */
	Set<Object> sMembers(String key);

	/**
	 * 向Set结构中添加属性
	 */
	Long sAdd(String key, Object... values);

	/**
	 * 向Set结构中添加属性
	 */
	Long sAdd(String key, long time, Object... values);

	/**
	 * 是否为Set中的属性
	 */
	Boolean sIsMember(String key, Object value);

	/**
	 * 获取Set结构的长度
	 */
	Long sSize(String key);

	/**
	 * 删除Set结构中的属性
	 */
	Long sRemove(String key, Object... values);

	/**
	 * 获取List结构中的属性
	 */
	List<Object> lRange(String key, long start, long end);

	/**
	 * 获取List结构的长度
	 */
	Long lSize(String key);

	/**
	 * 根据索引获取List中的属性
	 */
	Object lIndex(String key, long index);

	/**
	 * 向List结构中添加属性
	 */
	Long lPush(String key, Object value);

	/**
	 * 向List结构中添加属性
	 */
	Long leftPush(String key, Object value);

	/**
	 * 向List结构中添加属性
	 */
	Long lPush(String key, Object value, long time);

	/**
	 * 向List结构中添加属性
	 */
	Long leftPush(String key, Object value, long time);

	/**
	 * 向List结构中批量添加属性
	 */
	Long lPushAll(String key, Object... values);

	/**
	 * 向List结构中批量添加属性
	 */
	Long leftPushAll(String key, Object... values);

	/**
	 * 向List结构中批量添加属性
	 */
	Long lPushAll(String key, Long time, Object... values);

	/**
	 * 向List结构中批量添加属性
	 */
	Long leftPushAll(String key, Long time, Object... values);

	/**
	 * 从List结构中获取属性
	 */
	Object leftPop(String key);

	/**
	 * 从List结构中获取属性
	 */
	Object rightPop(String key);

	/**
	 * 从List结构中移除属性
	 */
	Long lRemove(String key, long count, Object value);

	/**
	 * 添加 ZSet 元素
	 */
	boolean zsAdd(String key, Object value, double score);

	/**
	 * 批量添加 ZSet
	 */
	Long zsAdd(String key, Set<TypedTuple<Object>> tuples);

	/**
	 * Zset 删除一个或多个元素
	 */
	Long zsRemove(String key, Object... values);

	/**
	 * 对指定的 zset 的 value 值 , socre 属性做增减操作
	 */
	Double zsIncrementScore(String key, Object value, double score);

	/**
	 * 获取 key 中指定 value 的排名(从0开始,从小到大排序)
	 */
	Long zsRank(String key, Object value);

	/**
	 * 获取 key 中指定 value 的排名(从0开始,从大到小排序)
	 */
	Long zsReverseRank(String key, Object value);

	/**
	 * 获取索引区间内的排序结果集合(从0开始,从小到大,带上分数)
	 */
	Set<TypedTuple<Object>> zsRangeWithScores(String key, long start, long end);

	/**
	 * 获取索引区间内的排序结果集合(从0开始,从小到大,只有列名)
	 */
	Set<Object> zsRange(String key, long start, long end);

	/**
	 * 获取分数范围内的 [min,max] 的排序结果集合 (从小到大,只有列名)
	 */
	Set<Object> zsRangeByScore(String key, double min, double max);
	/**
	 * 获取分数范围内的 [min,max] 的排序结果集合 (从小到大,集合带分数)
	 */
	Set<TypedTuple<Object>> zsRangeByScoreWithScores(String key, double min, double max);

	/**
	 * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从小到大,不带分数的集合)
	 */
	Set<Object> zsRangeByScore(String key, double min, double max, long offset, long count);

	/**
	 * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从小到大,带分数的集合)
	 */
	Set<TypedTuple<Object>> zsRangeByScoreWithScores(String key, double min, double max, long offset, long count);

	/**
	 * 获取索引区间内的排序结果集合(从0开始,从大到小,只有列名)
	 */
	Set<Object> zsReverseRange(String key, long start, long end);

	/**
	 * 获取索引区间内的排序结果集合(从0开始,从大到小,带上分数)
	 */
	Set<TypedTuple<Object>> zsReverseRangeWithScores(String key, long start, long end);

	/**
	 * 获取分数范围内的 [min,max] 的排序结果集合 (从大到小,集合不带分数)
	 */
	Set<Object> zsReverseRangeByScore(String key, double min, double max);

	/**
	 * 获取分数范围内的 [min,max] 的排序结果集合 (从大到小,集合带分数)
	 */
	Set<TypedTuple<Object>> zsReverseRangeByScoreWithScores(String key, double min, double max);

	/**
	 * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从大到小,不带分数的集合)
	 */
	Set<Object> zsReverseRangeByScore(String key, double min, double max, long offset, long count);

	/**
	 * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从大到小,带分数的集合)
	 */
	Set<TypedTuple<Object>> zsReverseRangeByScoreWithScores(String key, double min, double max, long offset, long count);

	/**
	 * 返回指定分数区间 [min,max] 的元素个数
	 */
	long zsCount(String key, double min, double max);

	/**
	 * 返回 zset 集合数量
	 */
	long zsSize(String key);

	/**
	 * 获取指定成员的 score 值
	 */
	Double zsScore(String key, Object value);

	/**
	 * 删除指定索引位置的成员,其中成员分数按( 从小到大 )
	 */
	Long zsRemoveRange(String key, long start, long end);

	/**
	 * 删除指定 分数范围 内的成员 [main,max],其中成员分数按( 从小到大 )
	 */
	Long zsRemoveRangeByScore(String key, double min, double max);

	/**
	 * key 和 other 两个集合的并集,保存在 destKey 集合中, 列名相同的 score 相加
	 */
	Long zsUnionAndStore(String key, String otherKey, String destKey);

	/**
	 * key 和 otherKeys 多个集合的并集,保存在 destKey 集合中, 列名相同的 score 相加
	 */
	Long zsUnionAndStore(String key, Collection<String> otherKeys, String destKey);

	/**
	 * key 和 otherKey 两个集合的交集,保存在 destKey 集合中
	 */
	Long zsIntersectAndStore(String key, String otherKey, String destKey);

	/**
	 * key 和 otherKeys 多个集合的交集,保存在 destKey 集合中
	 */
	Long zsIntersectAndStore(String key, Collection<String> otherKeys, String destKey);

	/**
	 * Executes the given {@link RedisScript}
	 *
	 * @param script The script to execute
	 * @param keys Any keys that need to be passed to the script
	 * @param args Any args that need to be passed to the script
	 * @return The return value of the script or null if {@link RedisScript#getResultType()} is null, likely indicating a
	 *         throw-away status reply (i.e. "OK")
	 */
	@Nullable
	<T> T execute(RedisScript<T> script, List<String> keys, Object... args);

	/**
	 * Executes the given {@link RedisScript}, using the provided {@link RedisSerializer}s to serialize the script
	 * arguments and result.
	 *
	 * @param script The script to execute
	 * @param argsSerializer The {@link RedisSerializer} to use for serializing args
	 * @param resultSerializer The {@link RedisSerializer} to use for serializing the script return value
	 * @param keys Any keys that need to be passed to the script
	 * @param args Any args that need to be passed to the script
	 * @return The return value of the script or null if {@link RedisScript#getResultType()} is null, likely indicating a
	 *         throw-away status reply (i.e. "OK")
	 */
	@Nullable
	<T> T execute(RedisScript<T> script, RedisSerializer<?> argsSerializer, RedisSerializer<T> resultSerializer,
				  List<String> keys, Object... args);

}
