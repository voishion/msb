package com.meishubao.easyes.controller;


import com.meishubao.easyes.domain.entity.Article;
import com.meishubao.easyes.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.dromara.easyes.core.toolkit.FieldUtils;
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
@RequestMapping("/article")
public class ArticleController {

    private final ArticleMapper articleMapper;

    @GetMapping("/createIndex")
    public Boolean createIndex() {
        // 1.初始化-> 创建索引(相当于mysql中的表)
        return articleMapper.createIndex();
    }

    @GetMapping("/insert")
    public Integer insert() {
        // 2.初始化-> 新增数据
        Article article = new Article();
        article.setTitle("老汉");
        article.setTitleEn("Old man");
        article.setContent("推*技术过硬");
        article.setAuthor("李露");
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now().plusMinutes(5));
        return articleMapper.insert(article);
    }

    @PostMapping("/update")
    public Integer update(@RequestBody Article document) {
        return articleMapper.updateById(document);
    }

    @GetMapping("/search")
    public List<Article> search() {
        // 3.查询出所有标题为老汉的文档列表
        LambdaEsQueryWrapper<Article> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Article::getTitle, "老汉");
        return articleMapper.selectList(wrapper);
    }

    @GetMapping("/testMultiFieldSelect")
    public List<Article> testMultiFieldSelect(String keyword) {
        // 药品 中文名叫葡萄糖酸钙口服溶液 英文名叫 Calcium Gluconate 汉语拼音为 putaotangsuangaikoufurongye
        // 用户可以通过模糊检索,例如输入 Calcium 或 葡萄糖 或 putaotang时对应药品均可以被检索到
        LambdaEsQueryWrapper<Article> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(Article::getTitleEn, keyword)
                .or().match(FieldUtils.val(Article::getTitle) + ".zh", keyword)
                .or().match(FieldUtils.val(Article::getTitle) + ".pinyin", keyword)
                .or().match(Article::getContent, keyword)
                .or().match(Article::getAuthor, keyword)
        ;

        // 获取DSL语句
        String source = articleMapper.getSource(wrapper);
        System.out.println(source);

        return articleMapper.selectList(wrapper);
    }

}
