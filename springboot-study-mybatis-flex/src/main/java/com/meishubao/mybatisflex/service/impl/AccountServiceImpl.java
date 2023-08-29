package com.meishubao.mybatisflex.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.meishubao.mybatisflex.domain.entity.Account;
import com.meishubao.mybatisflex.mapper.AccountMapper;
import com.meishubao.mybatisflex.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * 账户表 服务层实现。
 *
 * @author lilu
 * @since 2023-08-28
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
