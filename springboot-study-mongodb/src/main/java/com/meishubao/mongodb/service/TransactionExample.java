package com.meishubao.mongodb.service;

import com.meishubao.mongodb.entity.Status;
import com.meishubao.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 事务测试服务
 *
 * @author lilu
 */
@Service
public class TransactionExample {

    /** 设置集合名称 */
    private static final String COLLECTION_NAME = "users";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Exception.class)
    public Object transactionTest(){
        // 设置两个用户信息
        User user1 = new User()
                .setId("14")
                .setAge(29)
                .setSex("女")
                .setRemake("事务测试")
                .setSalary(2600)
                .setName("voishion")
                .setBirthday(new Date())
                .setStatus(new Status().setHeight(180).setWeight(150));
        // 插入数据
        User newUser1 = mongoTemplate.insert(user1, COLLECTION_NAME);
        // 抛出异常，观察数据是否进行回滚
        int error = 1/0;
        return newUser1;
    }

}
