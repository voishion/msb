package com.meishubao.mongodb.controller;

import com.meishubao.mongodb.service.TransactionExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务测试服务
 *
 * @author lilu
 */
@Api(tags = "事务测试服务")
@RestController
@RequestMapping("/transaction")
public class TransactionExampleController {

    @Autowired
    private TransactionExample transactionExample;

    @ApiOperation("事务测试")
    @PostMapping(value = "/transactionTest")
    public Object transactionTest(){
        return transactionExample.transactionTest();
    }

}
