package com.meishubao.elasticsearch.service;

import com.meishubao.elasticsearch.entity.Book;

/**
 * @author lilu
 */
public interface EsBookService {

    Iterable<Book> findAll();

    Iterable<Book> saveAll(Iterable<Book> entities);

}
