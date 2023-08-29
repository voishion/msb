package com.meishubao.mybatisflex.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.meishubao.mybatisflex.domain.entity.Account;
import com.meishubao.mybatisflex.service.AccountService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.Serializable;
import java.util.List;

/**
 * 账户表 控制层。
 *
 * @author lilu
 * @since 2023-08-28
 */
@RestController
@Api(tags = "账户表接口")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 添加账户表。
     *
     * @param account 账户表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @ApiOperation("保存账户表")
    public boolean save(@RequestBody @ApiParam("账户表") Account account) {
        return accountService.save(account);
    }

    /**
     * 根据主键删除账户表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @ApiOperation("根据主键账户表")
    public boolean remove(@PathVariable @ApiParam("账户表主键") Serializable id) {
        return accountService.removeById(id);
    }

    /**
     * 根据主键更新账户表。
     *
     * @param account 账户表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @ApiOperation("根据主键更新账户表")
    public boolean update(@RequestBody @ApiParam("账户表主键") Account account) {
        return accountService.updateById(account);
    }

    /**
     * 查询所有账户表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @ApiOperation("查询所有账户表")
    public List<Account> list() {
        return accountService.list();
    }

    /**
     * 根据账户表主键获取详细信息。
     *
     * @param id 账户表主键
     * @return 账户表详情
     */
    @GetMapping("getInfo/{id}")
    @ApiOperation("根据主键获取账户表")
    public Account getInfo(@PathVariable @ApiParam("账户表主键") Serializable id) {
        return accountService.getById(id);
    }

    /**
     * 分页查询账户表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @ApiOperation("分页查询账户表")
    public Page<Account> page(@ApiParam("分页信息") Page<Account> page) {
        return accountService.page(page);
    }

}
