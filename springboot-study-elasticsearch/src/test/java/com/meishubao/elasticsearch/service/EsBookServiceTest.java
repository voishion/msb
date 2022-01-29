package com.meishubao.elasticsearch.service;

import cn.hutool.core.convert.Convert;
import com.meishubao.elasticsearch.SpringbootStudyElasticsearchApplication;
import com.meishubao.elasticsearch.entity.Book;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootTest(classes = SpringbootStudyElasticsearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EsBookServiceTest {

    @Autowired
    private EsBookService esBookService;

    @Test
    void saveAll() {
        List<Book> books = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (int i = 0; i < 20; i++) {
             Book book = new Book();
             book.setId(Convert.toStr(i));
             book.setAuthor("赵四" + i);
             book.setTitle("《论街舞的重要性》" + (i + 1));
             book.setPrice(45.62D + i);
             book.setCreateTime(time);
             book.setUpdateTime(time);
             books.add(book);
             time = time.plusSeconds(1);
        }
        Iterable<Book> all1 = esBookService.saveAll(books);
        all1.forEach(b -> log.info("all1=>{}", b));

        Iterable<Book> all2 = esBookService.findAll();
        all2.forEach(b -> log.info("all2=>{}", b));
    }
}