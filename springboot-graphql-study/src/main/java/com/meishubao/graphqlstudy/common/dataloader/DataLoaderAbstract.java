package com.meishubao.graphqlstudy.common.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.Executor;

/**
 * 为了防止N+1问题，社区为GraphQL提供了一个解决方案: DataLoader。其原理就是，在需要查询数据库的时候将查询进行延迟，等到拿到所有的查询需求之后再一次性查询出来。
 *
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
