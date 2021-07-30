package com.meishubao.mongodb.controller.docment;

import com.meishubao.mongodb.service.docment.QueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文档查询
 *
 * @author lilu
 */
@Api(tags = "文档查询")
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @ApiOperation("查询集合中的【全部】文档数据")
    @PostMapping(value = "/findAll")
    public Object findAll() {
        return queryService.findAll();
    }

    @ApiOperation("根据【文档ID】查询集合中文档数据")
    @PostMapping(value = "/findById")
    public Object findById() {
        return queryService.findById();
    }

    @ApiOperation("根据【条件】查询集合中【符合条件】的文档，只取【第一条】数据")
    @PostMapping(value = "/findOne")
    public Object findOne() {
        return queryService.findOne();
    }

    @ApiOperation("根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】")
    @PostMapping(value = "/findByCondition")
    public Object findByCondition() {
        return queryService.findByCondition();
    }

    @ApiOperation("根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】并【排序】")
    @PostMapping(value = "/findByConditionAndSort")
    public Object findByConditionAndSort() {
        return queryService.findByConditionAndSort();
    }

    @ApiOperation("根据【单个条件】查询集合中的文档数据，并【按指定字段进行排序】与【限制指定数目】")
    @PostMapping(value = "/findByConditionAndSortLimit")
    public Object findByConditionAndSortLimit() {
        return queryService.findByConditionAndSortLimit();
    }

    @ApiOperation("根据【单个条件】查询集合中的文档数据，并【按指定字段进行排序】与【并跳过指定数目】")
    @PostMapping(value = "/findByConditionAndSortSkip")
    public Object findByConditionAndSortSkip() {
        return queryService.findByConditionAndSortSkip();
    }

    @ApiOperation("查询【存在指定字段名称】的文档数据")
    @PostMapping(value = "/findByExistsField")
    public Object findByExistsField() {
        return queryService.findByExistsField();
    }

    @ApiOperation("根据【AND】关联多个查询条件，查询集合中的文档数据")
    @PostMapping(value = "/findByAndCondition")
    public Object findByAndCondition() {
        return queryService.findByAndCondition();
    }

    @ApiOperation("根据【OR】关联多个查询条件，查询集合中的文档数据")
    @PostMapping(value = "/findByOrCondition")
    public Object findByOrCondition() {
        return queryService.findByOrCondition();
    }

    @ApiOperation("根据【IN】关联多个查询条件，查询集合中的文档数据")
    @PostMapping(value = "/findByInCondition")
    public Object findByInCondition() {
        return queryService.findByInCondition();
    }

    @ApiOperation("根据【逻辑运算符】查询集合中的文档数据")
    @PostMapping(value = "/findByOperator")
    public Object findByOperator() {
        return queryService.findByOperator();
    }

    @ApiOperation("根据【正则表达式】查询集合中的文档数据")
    @PostMapping(value = "/findByRegex")
    public Object findByRegex() {
        return queryService.findByRegex();
    }

    @ApiOperation("统计集合中符合【查询条件】的文档【数量】")
    @PostMapping(value = "/countNumber")
    public Object countNumber() {
        return queryService.countNumber();
    }

}
