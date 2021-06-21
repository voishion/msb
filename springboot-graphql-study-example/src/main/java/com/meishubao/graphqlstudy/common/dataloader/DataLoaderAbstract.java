package com.meishubao.graphqlstudy.common.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.Executor;

/**
 * @author lilu
 */
public abstract class DataLoaderAbstract {

    /**
     * 数据加载器的键(唯一)
     *
     * @return
     */
    public abstract DataLoaderKey getKey();

    /** data loader threadpool task executor **/
    @Autowired
    @Qualifier("dataLoaderThreadPoolTaskExecutor")
    protected Executor dataLoaderThreadPoolTaskExecutor;

}
