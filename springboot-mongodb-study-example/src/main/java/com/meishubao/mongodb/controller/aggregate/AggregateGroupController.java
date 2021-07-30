package com.meishubao.mongodb.controller.aggregate;

import com.meishubao.mongodb.service.aggregate.AggregateGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聚合表达式 $group
 *
 * @author lilu
 */
@Api(tags = "聚合表达式 $group")
@RestController
@RequestMapping("/aggregateGroup")
public class AggregateGroupController {

    @Autowired
    private AggregateGroupService aggregateGroupService;

    @ApiOperation("使用管道操作符 $group 结合 $count 方法进行聚合统计")
    @PostMapping(value = "/aggregationGroupCount")
    public Object aggregationGroupCount() {
        return aggregateGroupService.aggregationGroupCount();
    }

    @ApiOperation("使用管道操作符 $group 结合表达式操作符 $max 进行聚合统计")
    @PostMapping(value = "/aggregationGroupMax")
    public Object aggregationGroupMax() {
        return aggregateGroupService.aggregationGroupMax();
    }

    @ApiOperation("使用管道操作符 $group 结合表达式操作符 $min 进行聚合统计")
    @PostMapping(value = "/aggregationGroupMin")
    public Object aggregationGroupMin() {
        return aggregateGroupService.aggregationGroupMin();
    }

    @ApiOperation("使用管道操作符 $group 结合表达式操作符 $sum 进行聚合统计")
    @PostMapping(value = "/aggregationGroupSum")
    public Object aggregationGroupSum() {
        return aggregateGroupService.aggregationGroupSum();
    }

    @ApiOperation("使用管道操作符 $group 结合表达式操作符 $avg 进行聚合统计")
    @PostMapping(value = "/aggregationGroupAvg")
    public Object aggregationGroupAvg() {
        return aggregateGroupService.aggregationGroupAvg();
    }

    @ApiOperation("使用管道操作符 $group 结合表达式操作符 $first 获取每个组的包含某字段的文档的第一条数据")
    @PostMapping(value = "/aggregationGroupFirst")
    public Object aggregationGroupFirst() {
        return aggregateGroupService.aggregationGroupFirst();
    }

    @ApiOperation("使用管道操作符 $group 结合表达式操作符 $last 获取每个组的包含某字段的文档的最后一条数据")
    @PostMapping(value = "/aggregationGroupLast")
    public Object aggregationGroupLast() {
        return aggregateGroupService.aggregationGroupLast();
    }

    @ApiOperation("使用管道操作符 $group 结合表达式操作符 $push 获取某字段列表")
    @PostMapping(value = "/aggregationGroupPush")
    public Object aggregationGroupPush() {
        return aggregateGroupService.aggregationGroupPush();
    }

}
