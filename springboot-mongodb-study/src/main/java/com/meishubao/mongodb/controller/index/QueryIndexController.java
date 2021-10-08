package com.meishubao.mongodb.controller.index;

import com.meishubao.mongodb.service.index.QueryIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询索引操作
 *
 * @author lilu
 */
@Api(tags = "查询索引操作")
@RestController
@RequestMapping("/queryIndex")
public class QueryIndexController {

    @Autowired
    private QueryIndexService queryIndexService;

    @ApiOperation("获取当前【集合】对应的【所有索引】的【名称列表】")
    @PostMapping(value = "/getIndexAll")
    public Object getIndexAll() {
        return queryIndexService.getIndexAll();
    }

}
