package com.meishubao.mongodb.controller.aggregate;

import com.meishubao.mongodb.service.aggregate.AggregatePipelineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聚合管道操作符
 *
 * @author lilu
 */
@Api(tags = "聚合管道操作符")
@RestController
@RequestMapping("/aggregatePipeline")
public class AggregatePipelineController {

    @Autowired
    private AggregatePipelineService aggregatePipelineService;

    @ApiOperation("使用 $group 和 $match 聚合,先使用 $match 过滤文档，然后再使用 $group 进行分组")
    @PostMapping(value = "/aggregateGroupMatch")
    public Object aggregateGroupMatch() {
        return aggregatePipelineService.aggregateGroupMatch();
    }

    @ApiOperation("使用 $group 和 $sort 聚合,先使用 $group 进行分组，然后再使用 $sort 排序")
    @PostMapping(value = "/aggregateGroupSort")
    public Object aggregateGroupSort() {
        return aggregatePipelineService.aggregateGroupSkip();
    }

    @ApiOperation("使用 $group 和 $limit 聚合,先使用 $group 进行分组，然后再使用 $limit 限制一定数目文档")
    @PostMapping(value = "/aggregateGroupLimit")
    public Object aggregateGroupLimit() {
        return aggregatePipelineService.aggregateGroupLimit();
    }

    @ApiOperation("使用 $group 和 $skip 聚合,先使用 $group 进行分组，然后再使用 $skip 跳过一定数目文档")
    @PostMapping(value = "/aggregateGroupSkip")
    public Object aggregateGroupSkip() {
        return aggregatePipelineService.aggregateGroupSkip();
    }

    @ApiOperation("使用 $group 和 $project 聚合,先使用 $group 进行分组，然后再使用 $project 限制显示的字段")
    @PostMapping(value = "/aggregateGroupProject")
    public Object aggregateGroupProject() {
        return aggregatePipelineService.aggregateGroupProject();
    }

    @ApiOperation("使用 $group 和 $unwind 聚合,先使用 $project 进行分组，然后再使用 $unwind 拆分文档中的数组为一条新文档记录")
    @PostMapping(value = "/aggregateProjectUnwind")
    public Object aggregateProjectUnwind() {
        return aggregatePipelineService.aggregateProjectUnwind();
    }

}
