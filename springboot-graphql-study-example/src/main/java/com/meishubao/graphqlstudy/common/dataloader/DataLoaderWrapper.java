package com.meishubao.graphqlstudy.common.dataloader;

import org.dataloader.MappedBatchLoaderWithContext;

/**
 * 为了防止N+1问题，社区为GraphQL提供了一个解决方案: DataLoader。其原理就是，在需要查询数据库的时候将查询进行延迟，等到拿到所有的查询需求之后再一次性查询出来。
 *
 * @author lilu
 */
public abstract class DataLoaderWrapper<K, V> extends DataLoaderAbstract implements MappedBatchLoaderWithContext<K, V> {
}
