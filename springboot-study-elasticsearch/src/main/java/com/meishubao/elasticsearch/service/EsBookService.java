package com.meishubao.elasticsearch.service;

import com.meishubao.elasticsearch.entity.Book;

import java.util.Optional;

/**
 * @author lilu
 */
public interface EsBookService {

    Iterable<Book> findAll();

    Optional<Book> findById(String id);

    Iterable<Book> saveAll(Iterable<Book> entities);

    Book save(Book book);
}
