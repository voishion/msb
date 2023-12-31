package com.meishubao.graphqlstudy.user.resolvers;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.meishubao.graphqlstudy.user.entity.Bike;
import com.meishubao.graphqlstudy.user.entity.Car;
import com.meishubao.graphqlstudy.user.entity.User;
import graphql.com.google.common.collect.Lists;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lilu
 */
@Slf4j
@Component
public class UserResolver implements GraphQLResolver<User> {

    public Car car(User user) {
        log.info("car...");
        return Car.builder().id("京 H26A89").name(user.getNickname() + "的丰田凯美瑞").color("白色").build();
    }

    public Bike bike(User user, DataFetchingEnvironment environment) {
        log.info("bike...");
        Map<String, Object> arguments = MapUtil.isEmpty(environment.getArguments()) ? environment.getArguments() : environment.getVariables();
        log.info("params..");
        for (Map.Entry<String, Object> entry : arguments.entrySet()) {
            log.info("{}:{}", entry.getKey(), entry.getValue());
        }
        return Bike.builder().id("BX 110").name(user.getNickname() + "的闪电③").color("黑色").build();
    }

    public List<String> getHouseList(User user, String query, DataFetchingEnvironment environment) {
        List<String> houseList = Lists.newArrayList();
        for (int i = 1; i <= 5; i++) {
            houseList.add(StrUtil.format("{}'s House {}", user.getNickname(), i));
        }
        return houseList;
    }

}
