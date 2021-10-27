package com.meishubao.mongodb.controller.collection;

import com.meishubao.mongodb.service.collection.RemoveCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 删除集合
 *
 * @author lilu
 */
@Api(tags = "删除集合")
@RestController
@RequestMapping("/removeCollection")
public class RemoveCollectionController {

    @Autowired
    private RemoveCollectionService removeCollectionService;

    @ApiOperation("删除【集合】")
    @PostMapping(value = "/dropCollection")
    public Object dropCollection(@RequestParam(defaultValue = "users3") String collectionName) {
        return removeCollectionService.dropCollection(collectionName);
    }

}
