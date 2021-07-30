package com.meishubao.mongodb.controller.collection;

import com.meishubao.mongodb.service.collection.CreateCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建集合
 *
 * @author lilu
 */
@Api(tags = "创建集合")
@RestController
@RequestMapping("/createCollection")
public class CreateCollectionController {

    @Autowired
    private CreateCollectionService createCollectionService;

    @ApiOperation(value = "创建【集合】", notes = "创建一个大小没有限制的集合（默认集合创建方式）")
    @PostMapping(value = "/createCollection")
    public Object createCollection(@RequestParam(defaultValue = "users1") String collectionName) {
        return createCollectionService.createCollection(collectionName);
    }

    @ApiOperation(value = "创建【固定大小集合】", notes = "创建集合并设置 `capped=true` 创建 `固定大小集合`，可以配置参数 `size` 限制文档大小，可以配置参数 `max` 限制集合文档数量。")
    @PostMapping(value = "/createCollectionFixedSize")
    public Object createCollectionFixedSize() {
        return createCollectionService.createCollectionFixedSize();
    }

    @ApiOperation(value = "创建【验证文档数据】的集合", notes = "创建集合并在文档\"插入\"与\"更新\"时进行数据效验，如果符合创建集合设置的条件就进允许更新与插入，否则则按照设置的设置的策略进行处理。")
    @PostMapping(value = "/createCollectionValidation")
    public Object createCollectionValidation() {
        return createCollectionService.createCollectionValidation();
    }

}
