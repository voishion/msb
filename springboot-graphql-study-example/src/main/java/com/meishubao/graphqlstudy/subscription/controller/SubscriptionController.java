package com.meishubao.graphqlstudy.subscription.controller;

import com.meishubao.graphqlstudy.subscription.publishers.StockTickerRxPublisher;
import com.meishubao.graphqlstudy.subscription.entity.StockPriceUpdate;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lilu
 */
@Component
class SubscriptionController implements GraphQLSubscriptionResolver {

    @Autowired
    private StockTickerRxPublisher stockTickerPublisher;

    Publisher<StockPriceUpdate> stockQuotes(List<String> stockCodes) {
        return stockTickerPublisher.getPublisher(stockCodes);
    }
}
