package com.meishubao.sample.service;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

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
     * 保存值，默认永久保存
     *
     * @param key   键
     * @param value 值
     */
    void set(String key, Object value);

    /**
     * 保存值
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间，单位:秒
     */
    void set(String key, Object value, long time);

    /**
     * 保存值
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间
     * @param unit  过期时间单位
     */
    void set(String key, Object value, long time, TimeUnit unit);

    /**
     * 批量保存值
     *
     * @param map  多个键值对key-&gt;value
     * @param time 过期时间
     * @param unit 过期时间单位
     */
    void set(Map<String, Object> map, long time, TimeUnit unit);

    /**
     * 获取值
     *
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * 批量获取值
     *
     * @param keys 键列表
     * @return 值按照请求键的顺序返回，对于每个不包含字符串值或不存在的键，返回null
     */
    List<Object> mget(List<String> keys);

    /**
     * 删除指定的键。如果键不存在，则忽略它。
     *
     * @param key 键
     * @return 返回布尔类型，true-删除成功，false-键不存在
     */
    boolean del(String key);

    /**
     * 删除指定的键。如果键不存在，则忽略它。
     *
     * @param keys 键列表
     * @return 被移除的键的数量
     */
    Long del(List<String> keys);

    /**
     * 设置指定键的过期时间
     *
     * @param key  键
     * @param time 过期时间，单位:秒
     * @return 返回布尔类型，true-设置成功，false-未设置成功，例如键不存在，或由于提供的参数而跳过操作
     */
    boolean expire(String key, long time);

    /**
     * 设置指定键的过期时间
     *
     * @param key  键
     * @param time 过期时间
     * @param unit 过期时间单位
     * @return 返回布尔类型，true-设置成功，false-未设置成功，例如键不存在，或由于提供的参数而跳过操作
     */
    boolean expire(String key, long time, TimeUnit unit);

    /**
     * 获取指定键的过期时间
     *
     * @param key 键
     * @return 返回整数过期时间，单位:秒，-1:表示键没有设置过期时间，-2:表示键已经过期
     */
    Long ttl(String key);

    /**
     * 获取指定键的过期时间
     *
     * @param key  键
     * @param unit 过期时间单位
     * @return 返回整数过期时间，单位为指定的过期时间单位，-1:表示键没有设置过期时间，-2:表示键已经过期
     */
    Long ttl(String key, TimeUnit unit);

    /**
     * 检测指定键是否存在
     *
     * @param key 键
     * @return 返回布尔类型，true-存在，false-不存在
     */
    boolean exist(String key);

    /**
     * 将指定键的值递增指定的值
     *
     * @param key   键
     * @param delta 递增值
     * @return 返回递增后的值
     */
    Long incr(String key, long delta);

    /**
     * 将指定键的值递减指定的值
     *
     * @param key   键
     * @param delta 递减值
     * @return 返回递减后的值
     */
    Long decr(String key, long delta);

    /**
     * 从指定键的Hash结构中获取指定hashKey的值
     *
     * @param key     键
     * @param hashKey 哈希键
     * @return 返回与hashKey关联的值，或者当hashKey不存在于Hash结构中或键不存在时返回null
     */
    Object hget(String key, String hashKey);

    /**
     * 向指定键的Hash结构中放入指定hashKey的值
     *
     * @param key     键
     * @param hashKey 哈希键
     * @param value   值
     */
    void hset(String key, String hashKey, Object value);

    /**
     * 向指定键的Hash结构中放入指定hashKey的值，并设置指定键的过期时间，单位:秒
     *
     * @param key     键
     * @param hashKey 哈希键
     * @param value   值
     * @param time    过期时间
     * @return 返回布尔类型，true-过期时间设置成功，false-过期时间未设置成功，例如键不存在，或由于提供的参数而跳过操作
     */
    boolean hset(String key, String hashKey, Object value, long time);

    /**
     * 向指定键的Hash结构中放入指定hashKey的值，并设置指定键的过期时间并指定过期时间单位
     *
     * @param key     键
     * @param hashKey 哈希键
     * @param value   值
     * @param time    过期时间
     * @param unit    过期时间单位
     * @return 返回布尔类型，true-过期时间设置成功，false-过期时间未设置成功，例如键不存在，或由于提供的参数而跳过操作
     */
    boolean hset(String key, String hashKey, Object value, long time, TimeUnit unit);

    /**
     * 向指定键的Hash结构中批量放入hashKey和值，该操作会覆盖Hash结构中已存在的任何指定
     * 的hashKey和值，如果指定键不存在，则创建一个指定键的Hash结构并批量放入hashKey和值。
     *
     * @param key 键
     * @param map 批量放入hashKey和值
     */
    void hmset(String key, Map<String, Object> map);

    /**
     * 向指定键的Hash结构中批量放入hashKey和值，该操作会覆盖Hash结构中已存在的任何指定
     * 的hashKey和值，如果指定键不存在，则创建一个指定键的Hash结构并批量放入hashKey和值，
     * 并设置指定键的过期时间，单位:秒。
     *
     * @param key  键
     * @param map  批量放入hashKey和值
     * @param time 过期时间
     * @return 返回布尔类型，true-过期时间设置成功，false-过期时间未设置成功，例如键不存在，或由于提供的参数而跳过操作
     */
    boolean hmset(String key, Map<String, Object> map, long time);

    /**
     * 向指定键的Hash结构中批量放入hashKey和值，该操作会覆盖Hash结构中已存在的任何指定
     * 的hashKey和值，如果指定键不存在，则创建一个指定键的Hash结构并批量放入hashKey和值，
     * 并设置指定键的过期时间并指定过期时间单位。
     *
     * @param key  键
     * @param map  批量放入hashKey和值
     * @param time 过期时间
     * @param unit 过期时间单位
     * @return 返回布尔类型，true-过期时间设置成功，false-过期时间未设置成功，例如键不存在，或由于提供的参数而跳过操作
     */
    boolean hmset(String key, Map<String, Object> map, long time, TimeUnit unit);

    /**
     * 获取指定键的Hash结构中所有hashKey和值
     *
     * @param key 键
     * @return 返回Hash结构中所有hashKey和值，或者当键不存在时返回null
     */
    Map<Object, Object> hgetall(String key);

    /**
     * 获取指定键的Hash结构中hashKey的数量
     *
     * @param key 键
     * @return 返回Hash结构中hashKey的数量，如果键不存在，则返回0
     */
    Long hlen(String key);

    /**
     * 从指定键的Hash结构中删除给定的hashKeys，此Hash结构中不存在的指定hashKey将被忽略
     *
     * @param key      键
     * @param hashKeys 哈希键数组
     * @return 返回从Hash结构中删除的hashKey数，不包括指定但不存在的hashKey，如果指定键不存在，则将其视为空Hash，操作将返回0
     */
    Long hdel(String key, Object... hashKeys);

    /**
     * 检查指定键的Hash结构中hashKey是否存在
     *
     * @param key     键
     * @param hashKey 哈希键
     * @return 返回布尔类型，true-存在，false-不存在
     */
    boolean hexits(String key, String hashKey);

    /**
     * 以增量方式递增指定键的Hash结构中指定hashKey的数字值，如果指定键不存在，则创建
     * 一个指定键的Hash结构，如果hashKey不存在，则在执行操作之前将hashKey的值设置为0。
     *
     * @param key     键
     * @param hashKey 哈希键
     * @param delta   递增值
     * @return 返回递增操作后hashKey的值
     */
    Long hincrby(String key, String hashKey, Long delta);

    /**
     * 以减量方式递减指定键的Hash结构中指定hashKey的数字值，如果指定键不存在，则创建
     * 一个指定键的Hash结构，如果hashKey不存在，则在执行操作之前将hashKey的值设置为0。
     *
     * @param key     键
     * @param hashKey 哈希键
     * @param delta   递减值
     * @return 返回递减操作后hashKey的值
     */
    Long hdecrby(String key, String hashKey, Long delta);

    /**
     * 获取指定键的Set结构中的所有值
     *
     * @param key 键
     * @return 返回Set结构中的所有值
     */
    Set<Object> smembers(String key);

    /**
     * 向指定键的Set结构中批量添加值，如果已经是该Set结构中的值将被忽略。如果指定键不存在，
     * 则在添加值之前创建一个新Set结构。当指定键中存储的类型不是Set结构时将返回错误。
     *
     * @param key    键
     * @param values 值数组
     * @return 返回添加到Set结构中的值数量，不包括Set结构中已经存在的所有值
     */
    Long sadd(String key, Object... values);

    /**
     * 向指定键的Set结构中批量添加值，如果已经是该Set结构中的值将被忽略。如果指定键不存在，
     * 则在添加值之前创建一个新Set结构。当指定键中存储的类型不是Set结构时将返回错误。添加完
     * 成后设置指定键的过期时间，单位:秒。
     *
     * @param key    键
     * @param time   过期时间
     * @param values 值数组
     * @return 返回添加到Set结构中的值数量，不包括Set结构中已经存在的所有值
     */
    Long sadd(String key, long time, Object... values);

    /**
     * 向指定键的Set结构中批量添加值，如果已经是该Set结构中的值将被忽略。如果指定键不存在，
     * 则在添加值之前创建一个新Set结构。当指定键中存储的类型不是Set结构时将返回错误。添加完
     * 成后设置指定键的过期时间并指定过期时间单位。
     *
     * @param key    键
     * @param time   过期时间
     * @param unit   过期时间单位
     * @param values 值数组
     * @return 返回添加到Set结构中的值数量，不包括Set结构中已经存在的所有值
     */
    Long sadd(String key, long time, TimeUnit unit, Object... values);

    /**
     * 检查指定键的Set结构中是否存在指定的值
     *
     * @param key   键
     * @param value 值
     * @return 返回布尔类型，true-存在，false-不存在
     */
    boolean sismember(String key, Object value);

    /**
     * 获取指定键的Set结构中值的数量
     *
     * @param key 键
     * @return 返回指定键的Set结构中值的数量，如果指定键不存在，则返回0
     */
    Long scard(String key);

    /**
     * 从指定键的Set结构中删除指定的值。不属于该Set结构的指定值将被忽略。
     * 如果指定键不存在，则将其视为空集，返回0。当指定键中存储的类型不是
     * Set结构时将返回错误。
     *
     * @param key    键
     * @param values 值数组
     * @return 返回删除Set结构中值的数量，不包括Set结构中不存在的值
     */
    Long srem(String key, Object... values);

    /**
     * 返回存储在指定键列表的指定元素。偏移量start和end是从零开始的索引，0是列表的第一个元素（列表的头部），1是下一个元素，依此类推。
     * 这些偏移量也可以是负数，表示从列表末尾开始的偏移量。例如，-1是列表的最后一个元素，-2是倒数第二个元素，依此类推。
     *
     * @param key   键
     * @param start 开始偏移量
     * @param end   结束偏移量
     * @return 返回指定范围内的元素列表
     */
    List<Object> lrange(String key, long start, long end);

    /**
     * 返回存储在指定键的列表的长度。如果指定键不存在，则将其解释为空列表并返回0。当指定键存储的值不是列表时返回错误。
     *
     * @param key 键
     * @return 返回指定键列表的长度
     */
    Long llen(String key);

    /**
     * 返回存储在指定键的列表中索引index处的元素。索引是从零开始的，所以0表示第一个元素，1表示第二个元素，依此类推。负索引可用于指定从
     * 列表尾部开始的元素。这里，-1表示最后一个元素，-2表示倒数第二个，依此类推。当指定键的值不是列表时，返回错误。
     *
     * @param key   键
     * @param index 索引值
     * @return
     */
    Object lindex(String key, long index);

    /**
     * 将指定的值插入存储在指定键的列表的尾部。如果指定键不存在，则在执行推送操作之前将其创建为空列表。当指定键保存的值不是列表时，将返
     * 回错误。
     *
     * @param key   键
     * @param value 值
     * @return 返回推入操作后的列表长度
     */
    Long rpush(String key, Object value);

    /**
     * 将指定的值批量插入存储在指定键的列表的尾部。如果指定键不存在，则在执行推送操作之前将其创建为空列表。当指定键保存的值不是列表时，
     * 将返回错误。元素一个接一个地插入到列表的尾部，从最左边的元素到最右边的元素。因此，例如命令<code>RPUSH mylist a b c</code>将
     * 生成一个包含a作为第一个元素、b作为第二个元素和c作为第三个元素的列表。
     *
     * @param key    键
     * @param values 值数组
     * @return 返回推入操作后的列表长度
     */
    Long rpush(String key, Object... values);

    /**
     * 将指定的值插入存储在指定键的列表的头部。如果指定键不存在，则在执行推送操作之前将其创建为空列表。当指定键保存的值不是列表时，将返
     * 回错误。
     *
     * @param key
     * @param value
     * @return 返回推入操作后的列表长度
     */
    Long lpush(String key, Object value);

    /**
     * 将指定的值批量插入存储在指定键的列表的头部。如果指定键不存在，则在执行推送操作之前将其创建为空列表。当指定键保存的值不是列表时，
     * 将返回错误。元素一个接一个地插入到列表的头部，从最左边的元素到最右边的元素。因此，例如命令<code>LPUSH mylist a b c<code/>将
     * 生成一个包含c作为第一个元素、b作为第二个元素和a作为第三个元素的列表。
     *
     * @param key
     * @param values
     * @return 返回推入操作后的列表长度
     */
    Long lpush(String key, Object... values);

    /**
     * 删除并返回存储在指定键的列表的第一个元素。默认情况下，该命令从列表的开头弹出一个元素
     *
     * @param key 键
     * @return 返回第一个元素的值，指定键的列表不存在返回null
     */
    Object lpop(String key);

    /**
     * 删除并返回存储在指定键的列表的最后一个元素。默认情况下，该命令从列表末尾弹出一个元素
     *
     * @param key 键
     * @return 返回最后一个元素的值，指定键的列表不存在返回null
     */
    Object rpop(String key);

    /**
     * 从存储在指定键的列表中删除与指定值相等的元素，并且删除count次。count参数通过以下方式影响操作，
     * count > 0:删除元素等于从头到尾移动的元素。
     * count < 0:删除等于从尾部移动到头部的元素的元素。
     * count = 0:删除所有等于element的元素。
     * 例如，<code>LREM list -2 "hello"<code/>将删除存储在列表中的最后两次出现的"hello"。请注意，不存在的键被视为空列表，
     * 因此当键不存在时，该命令将始终返回0。
     *
     * @param key   键
     * @param count 次数
     * @param value 值
     * @return 返回移除元素的数量
     */
    Long lrem(String key, long count, Object value);

    /**
     * 添加元素到一个有序集合中，并更新它的分数
     *
     * @param key   键
     * @param value 元素
     * @param score 分数
     * @return 返回布尔类型，true-添加成功，false-添加失败
     */
    boolean zadd(String key, Object value, double score);

    /**
     * 批量添加元素到一个有序集合中
     *
     * @param key    键
     * @param tuples 元组集
     * @return 返回添加到有序集合中的元素数量
     */
    Long zadd(String key, Set<TypedTuple<Object>> tuples);

    /**
     * 从存储在指定键的有序集合中删除指定的元素。不存在的元素将被忽略。当指定键存在但存储类型不是有序集合时将返回错误
     *
     * @param key    键
     * @param values 元素数组
     * @return 返回从有序集合中删除的元素数量，不包括不存在的元素
     */
    Long zrem(String key, Object... values);

    /**
     * 将存储在指定键的有序集合中元素的分数递增。如果元素在有序集合中不存在，则将其添加为增量作为其分数（就像它之前的分数是0.0）。如果指定
     * 键不存在，则创建一个以指定元素为唯一元素的新的有序集合。当指定键存在但是存储类型不是有序集合合时返回错误。分数值应该是数值的字符串表
     * 示形式，并且接受双精度浮点数。可以提供一个负值来降低分数。
     *
     * @param key   键
     * @param value 元素
     * @param score 分数
     * @return 返回元素的新分数
     */
    Double zincrby(String key, Object value, double score);

    /**
     * 返回存储在指定键的有序集合中元素的等级，并从低到高点排序。等级（或索引）是基于0的，这意味着具有最低分数的成员具有等级0
     *
     * @param key   键
     * @param value 元素
     * @return 返回元素的等级，如果元素不存在于有序集合或指定键不存在，则返回null
     */
    Long zrank(String key, Object value);

    /**
     * 返回存储在指定键的有序集合中成员的等级，并从高到低点排序。等级（或索引）是基于0的，这意味着具有最高分分数的成员具有等级0
     *
     * @param key   键
     * @param value 元素
     * @return 返回元素的等级，如果元素不存在于有序集合或指定键不存在，则返回null
     */
    Long zrevrank(String key, Object value);

    /**
     * 获取索引区间内的有序集合元素，从0开始，从小到大，只有元素
     *
     * @param key   键
     * @param start 开始索引
     * @param end   结束索引
     * @return 返回指定范围内的元素列表
     */
    Set<Object> zrange(String key, long start, long end);

    /**
     * 获取索引区间内的有序集合元组，从0开始，从小到大，带上分数
     *
     * @param key   键
     * @param start 开始索引
     * @param end   结束索引
     * @return 返回指定范围内的元组列表
     */
    Set<TypedTuple<Object>> zrangewithscores(String key, long start, long end);

    /**
     * 获取分数范围内的有序集合元素，从小到大，只有列名
     *
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 返回指定范围内的元素列表
     */
    Set<Object> zrangebyscore(String key, double min, double max);

    /**
     * 获取分数范围内指定count数量的有序集合元素，并且从offset下标开始，从小到大，只有列名
     *
     * @param key    键
     * @param min    最小分数
     * @param max    最大分数
     * @param offset 开始下标
     * @param count  元素数量
     * @return 返回指定范围内的元素列表
     */
    Set<Object> zrangebyscore(String key, double min, double max, long offset, long count);

    /**
     * 获取分数范围内的有序集合元组，从小到大，集合带分数
     *
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 返回指定范围内的元组列表
     */
    Set<TypedTuple<Object>> zrangebyscorewithscores(String key, double min, double max);

    /**
     * 获取分数范围内指定count数量的有序集合元组，并且从offset下标开始，从小到大，集合带分数
     *
     * @param key    键
     * @param min    最小分数
     * @param max    最大分数
     * @param offset 开始下标
     * @param count  元素数量
     * @return 返回指定范围内的元组列表
     */
    Set<TypedTuple<Object>> zrangebyscorewithscores(String key, double min, double max, long offset, long count);

    /**
     * 获取索引区间内的有序集合元素，从0开始，从大到小，只有元素
     *
     * @param key   键
     * @param start 开始索引
     * @param end   结束索引
     * @return 返回指定范围内的元素列表
     */
    Set<Object> zrevrange(String key, long start, long end);

    /**
     * 获取索引区间内的有序集合元组，从0开始，从大到小，带上分数
     *
     * @param key   键
     * @param start 开始索引
     * @param end   结束索引
     * @return 返回指定范围内的元组列表
     */
    Set<TypedTuple<Object>> zrevrangewithscores(String key, long start, long end);

    /**
     * 获取分数范围内的有序集合元素，从大到小，只有列名
     *
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 返回指定范围内的元素列表
     */
    Set<Object> zrevrangebyscore(String key, double min, double max);

    /**
     * 获取分数范围内指定count数量的有序集合元素，并且从offset下标开始，从大到小，只有列名
     *
     * @param key    键
     * @param min    最小分数
     * @param max    最大分数
     * @param offset 开始下标
     * @param count  元素数量
     * @return 返回指定范围内的元素列表
     */
    Set<Object> zrevrangebyscore(String key, double min, double max, long offset, long count);

    /**
     * 获取分数范围内的有序集合元组，从大到小，集合带分数
     *
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 返回指定范围内的元组列表
     */
    Set<TypedTuple<Object>> zrevrangebyscorewithscores(String key, double min, double max);

    /**
     * 获取分数范围内指定count数量的有序集合元组，并且从offset下标开始，从大到小，集合带分数
     *
     * @param key    键
     * @param min    最小分数
     * @param max    最大分数
     * @param offset 开始下标
     * @param count  元素数量
     * @return 返回指定范围内的元组列表
     */
    Set<TypedTuple<Object>> zrevrangebyscorewithscores(String key, double min, double max, long offset, long count);

    /**
     * 获取分数范围内的有序集合元素个数，从小到大，只有列名
     *
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 返回指定范围内的元素个数
     */
    Long zcount(String key, double min, double max);

    /**
     * 获取指定键的有序集合元素个数
     *
     * @param key 键
     * @return 返回指定键的有序集合元素个数，如果指定键不存在，则返回0
     */
    Long zcard(String key);

    /**
     * 返回指定键有序集合中元素的分数。如果有序集合中不存在元素，或者指定键不存在，则返回null
     *
     * @param key   键
     * @param value 值
     * @return 返回元素的分数（双精度浮点数）
     */
    Double zscore(String key, Object value);

    /**
     * 删除存储在指定键的有序集合中的指定索引位置的元素，其中成员分数按从小到大
     *
     * @param key   键
     * @param start 开始索引
     * @param end   结束索引
     * @return 返回移除的元素数量
     */
    Long zremrangebyrank(String key, long start, long end);

    /**
     * 删除存储在指定键的有序集合中的指定分数范围的元素，其中成员分数按从小到大
     *
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 返回移除的元素数量
     */
    Long zremrangebyscore(String key, double min, double max);

    /**
     * firstKey和secondKey两个集合的并集保存在destKey集合中，列名相同的score相加
     *
     * @param firstKey  第一个集合键
     * @param secondKey 第二个集合键
     * @param destKey   目标集合键
     * @return 返回destKey有序集合元素数量
     */
    Long zunionstore(String firstKey, String secondKey, String destKey);

    /**
     * key和otherKeys多个集合的并集保存在destKey集合中，列名相同的score相加
     *
     * @param key       集合键
     * @param otherKeys 其他集合键列表
     * @param destKey   目标集合键
     * @return 返回destKey有序集合元素数量
     */
    Long zunionstore(String key, Collection<String> otherKeys, String destKey);

    /**
     * firstKey和secondKey两个集合的交集保存在destKey集合中
     *
     * @param firstKey  第一个集合键
     * @param secondKey 第二个集合键
     * @param destKey   目标集合键
     * @return 返回destKey有序集合元素数量
     */
    Long zinterstore(String firstKey, String secondKey, String destKey);

    /**
     * key和otherKeys多个集合的交集保存在destKey集合中
     *
     * @param key       集合键
     * @param otherKeys 其他集合键列表
     * @param destKey   目标集合键
     * @return 返回destKey有序集合元素数量
     */
    Long zinterstore(String key, Collection<String> otherKeys, String destKey);

}
