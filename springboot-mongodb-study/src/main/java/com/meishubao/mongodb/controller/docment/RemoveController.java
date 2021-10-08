package com.meishubao.mongodb.controller.docment;

import com.meishubao.mongodb.service.docment.RemoveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文档删除
 *
 * @author lilu
 */
@Api(tags = "文档删除")
@RestController
@RequestMapping("/remove")
public class RemoveController {

    @Autowired
    private RemoveService removeService;

    @ApiOperation("删除集合中【符合条件】的【一个]或[多个】文档")
    @PostMapping(value = "/remove")
    public Object remove() {
        return removeService.remove();
    }

    @ApiOperation("删除【符合条件】的【单个文档】，并返回删除的文档。")
    @PostMapping(value = "/findAndRemove")
    public Object findAndRemove() {
        return removeService.findAndRemove();
    }

    @ApiOperation("删除【符合条件】的【全部文档】，并返回删除的文档。")
    @PostMapping(value = "/findAllAndRemove")
    public Object findAllAndRemove() {
        return removeService.findAllAndRemove();
    }

}
