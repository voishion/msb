package com.meishubao.graphqlstudy.component;

import cn.hutool.core.collection.CollUtil;
import com.meishubao.graphqlstudy.common.dataloader.DataLoaderAbstract;
import com.meishubao.graphqlstudy.common.dataloader.DataLoaderWrapper;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.List;

/**
 * @author lilu
 */
@Component
public class GraphQLContextBuilder implements GraphQLServletContextBuilder {

    @Autowired
    private List<DataLoaderWrapper> dataLoaders;

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(buildDataLoaderRegistry(), null);
    }

    @Override
    public GraphQLContext build(HttpServletRequest request, HttpServletResponse response) {
        return DefaultGraphQLServletContext.createServletContext(buildDataLoaderRegistry(), null)
                .with(request)
                .with(response)
                .build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest request) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext(buildDataLoaderRegistry(), null)
                .with(session)
                .with(request)
                .build();
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
        if (CollUtil.isNotEmpty(dataLoaders)) {
            dataLoaders.forEach(loader -> {
                dataLoaderRegistry.register(loader.getKey().key(), DataLoader.newMappedDataLoader(loader));
            });
        }
        return dataLoaderRegistry;
    }

}
