package com.meishubao.elasticsearch.controller;

import com.meishubao.elasticsearch.entity.Book;
import com.meishubao.elasticsearch.service.EsBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/es/book")
@RequiredArgsConstructor
public class EsBookController {

    private final EsBookService esBookService;

    @GetMapping("/list")
    public Iterable<Book> list() {
        return esBookService.findAll();
    }

    @GetMapping("/get/{id}")
    public Book find(@PathVariable String id) {
        return esBookService.findById(id).orElse(new Book());
    }

    @PostMapping("/save")
    public Book save(@RequestBody Book book) {
        return esBookService.save(book);
    }

}
