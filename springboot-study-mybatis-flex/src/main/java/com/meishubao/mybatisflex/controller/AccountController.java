package com.meishubao.mybatisflex.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.meishubao.mybatisflex.domain.entity.Account;
import com.meishubao.mybatisflex.service.AccountService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 账户表 控制器
 *
 * @author lilu
 * @since 2023-08-29
 */
@Slf4j
@ApiSort(1)
@Api(tags = "账户表接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    /**
     * 添加账户表
     *
     * @param account 账户表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @ApiOperationSupport(order = 1, author = "lilu")
    @ApiOperation("保存账户表")
    @PostMapping("save")
    public boolean save(@RequestBody @ApiParam("账户表") Account account) {
        return accountService.save(account);
    }

    /**
     * 根据主键删除账户表
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @ApiOperationSupport(order = 2, author = "lilu")
    @ApiOperation("根据主键账户表")
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable @ApiParam("账户表主键") Serializable id) {
        return accountService.removeById(id);
    }

    /**
     * 根据主键更新账户表
     *
     * @param account 账户表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @ApiOperationSupport(order = 3, author = "lilu")
    @ApiOperation("根据主键更新账户表")
    @PutMapping("update")
    public boolean update(@RequestBody @ApiParam("账户表主键") Account account) {
        return accountService.updateById(account);
    }

    /**
     * 查询所有账户表
     *
     * @return 所有数据
     */
    @ApiOperationSupport(order = 4, author = "lilu")
    @ApiOperation("查询所有账户表")
    @GetMapping("list")
    public List<Account> list() {
        return accountService.list();
    }

    /**
     * 根据账户表主键获取详细信息
     *
     * @param id 账户表主键
     * @return 账户表详情
     */
    @ApiOperationSupport(order = 5, author = "lilu")
    @ApiOperation("根据主键获取账户表")
    @GetMapping("getInfo/{id}")
    public Account getInfo(@PathVariable @ApiParam("账户表主键") Serializable id) {
        return accountService.getById(id);
    }

    /**
     * 分页查询账户表
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @ApiOperationSupport(order = 6, author = "lilu")
    @ApiOperation(value = "分页查询账户表", notes = "分页查询账户表接口")
    @GetMapping("page")
    public Page<Account> page(@ApiParam("分页信息") Page<Account> page) {
        return accountService.page(page);
    }

}
