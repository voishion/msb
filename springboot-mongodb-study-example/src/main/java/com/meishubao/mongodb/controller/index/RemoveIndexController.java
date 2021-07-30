package com.meishubao.mongodb.controller.index;

import com.meishubao.mongodb.service.index.RemoveIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 删除索引
 *
 * @author lilu
 */
@Api(tags = "删除索引")
@RestController
@RequestMapping("/removeIndex")
public class RemoveIndexController {

    @Autowired
    private RemoveIndexService removeIndexService;

    /**
     * 设置集合名称
     */
    private static final String COLLECTION_NAME = "users";

    @ApiOperation("根据索引名称移除索引")
    @PostMapping(value = "/removeIndex")
    public boolean removeIndex(@RequestParam(defaultValue = "name_1") String indexName) {
        removeIndexService.removeIndex(indexName);
        return true;
    }

    @ApiOperation("移除全部索引")
    @PostMapping(value = "/removeIndexAll")
    public boolean removeIndexAll() {
        removeIndexService.removeIndexAll();
        return true;
    }

}
