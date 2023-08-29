package com.meishubao.mybatisflex.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.meishubao.mybatisflex.domain.entity.Account;
import com.meishubao.mybatisflex.mapper.AccountMapper;
import com.meishubao.mybatisflex.service.AccountService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 账户表 服务接口实现
 *
 * @author lilu
 * @since 2023-08-29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
