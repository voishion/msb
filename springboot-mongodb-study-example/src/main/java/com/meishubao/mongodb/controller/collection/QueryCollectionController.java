package com.meishubao.mongodb.controller.collection;

import com.meishubao.mongodb.service.collection.QueryCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询集合
 *
 * @author lilu
 */
@Api(tags = "查询集合")
@RestController
@RequestMapping("/queryCollection")
public class QueryCollectionController {

    @Autowired
    private QueryCollectionService queryCollectionService;

    @ApiOperation("获取【集合名称】列表")
    @PostMapping(value = "/getCollectionNames")
    public Object getCollectionNames() {
        return queryCollectionService.getCollectionNames();
    }

    @ApiOperation("检测集合【是否存在】")
    @PostMapping(value = "/collectionExists")
    public boolean collectionExists(@RequestParam(defaultValue = "users1") String collectionName) {
        return queryCollectionService.collectionExists(collectionName);
    }

}
