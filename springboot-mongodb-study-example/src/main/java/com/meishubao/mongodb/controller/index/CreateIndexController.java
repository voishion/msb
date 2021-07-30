package com.meishubao.mongodb.controller.index;

import com.meishubao.mongodb.service.index.CreateIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建索引
 *
 * @author lilu
 */
@Api(tags = "创建索引")
@RestController
@RequestMapping("/createIndex")
public class CreateIndexController {

    @Autowired
    private CreateIndexService createIndexService;

    @ApiOperation("创建升序索引")
    @PostMapping(value = "/createAscendingIndex")
    public Object createAscendingIndex() {
        return createIndexService.createAscendingIndex();
    }

    @ApiOperation("创建降序索引")
    @PostMapping(value = "/createDescendingIndex")
    public Object createDescendingIndex() {
        return createIndexService.createDescendingIndex();
    }

    @ApiOperation("创建升序复合索引")
    @PostMapping(value = "/createCompositeIndex")
    public Object createCompositeIndex() {
        return createIndexService.createCompositeIndex();
    }

    @ApiOperation("创建文字索引")
    @PostMapping(value = "/createTextIndex")
    public Object createTextIndex() {
        return createIndexService.createTextIndex();
    }

    @ApiOperation("创建哈希索引")
    @PostMapping(value = "/createHashIndex")
    public Object createHashIndex() {
        return createIndexService.createHashIndex();
    }

    @ApiOperation("创建升序唯一索引")
    @PostMapping(value = "/createUniqueIndex")
    public Object createUniqueIndex() {
        return createIndexService.createUniqueIndex();
    }

    @ApiOperation("创建局部索引")
    @PostMapping(value = "/createPartialIndex")
    public Object createPartialIndex() {
        return createIndexService.createPartialIndex();
    }

}
