package com.meishubao.mybatisflex;

import com.meishubao.mybatisflex.domain.entity.Account;
import com.meishubao.mybatisflex.service.AccountService;
import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.meishubao.mybatisflex.domain.entity.table.AccountTableDef.Account;


@SpringBootTest
class SpringbootStudyMybatisFlexApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    void contextLoads() {
        QueryWrapper queryWrapper = QueryWrapper.create().select().from(Account)
                .where(Account.Age.eq(18));
        Account account = accountService.getOne(queryWrapper);
        System.out.println(account);
    }

}
