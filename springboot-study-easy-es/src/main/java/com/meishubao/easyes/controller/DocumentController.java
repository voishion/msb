package com.meishubao.easyes.controller;


import com.meishubao.easyes.domain.entity.Document;
import com.meishubao.easyes.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.dromara.easyes.core.core.EsWrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试使用Easy-ES
 * <p>
 * Copyright © 2021 xpc1024 All Rights Reserved
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/document")
public class DocumentController {

    private final DocumentMapper documentMapper;

    @GetMapping("/createIndex")
    public Boolean createIndex() {
        // 1.初始化-> 创建索引(相当于mysql中的表)
        return documentMapper.createIndex();
    }

    // 更新索引(不推荐使用,因为索引变动ES会重建索引,有其它更好的方式,可参考后面索引托管章节)
    @GetMapping("/updateIndex")
    public Boolean updateIndex() {
        // 1.初始化-> 创建索引(相当于mysql中的表)
        return documentMapper.updateIndex(EsWrappers.lambdaIndex(Document.class));
    }

    @GetMapping("/insert")
    public Integer insert() {
        // 2.初始化-> 新增数据
        Document document = new Document();
        document.setTitle("老汉");
        document.setContent("推*技术过硬");
        document.setDocNumber(3);
        document.setCreateTime(LocalDateTime.now());
        document.setUpdateTime(LocalDateTime.now().plusMinutes(5));
        return documentMapper.insert(document);
    }

    @PostMapping("/update")
    public Integer update(@RequestBody Document document) {
        return documentMapper.updateById(document);
    }

    @GetMapping("/search")
    public List<Document> search() {
        // 3.查询出所有标题为老汉的文档列表
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getTitle, "老汉");
        return documentMapper.selectList(wrapper);
    }

    @GetMapping("/testMultiFieldSelect")
    public List<Document> testMultiFieldSelect(String keyword) {
        // 药品 中文名叫葡萄糖酸钙口服溶液 英文名叫 Calcium Gluconate 汉语拼音为 putaotangsuangaikoufurongye
        // 用户可以通过模糊检索,例如输入 Calcium 或 葡萄糖 或 putaotang时对应药品均可以被检索到
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(Document::getMultiFieldEn, keyword)
                .or()
                .match("multi_field.zh", keyword)
                .or()
                .match("multi_field.pinyin", keyword);
        return documentMapper.selectList(wrapper);
    }

}
