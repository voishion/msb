package com.meishubao.graphqlstudy.common.dataloader;

import org.dataloader.MappedBatchLoaderWithContext;

/**
 * @author lilu
 */
public abstract class DataLoaderWrapper<K, V> extends DataLoaderAbstract implements MappedBatchLoaderWithContext<K, V> {
}
