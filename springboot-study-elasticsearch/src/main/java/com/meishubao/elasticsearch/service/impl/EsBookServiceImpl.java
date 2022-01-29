package com.meishubao.elasticsearch.service.impl;

import com.meishubao.elasticsearch.entity.Book;
import com.meishubao.elasticsearch.repository.EsBookRepository;
import com.meishubao.elasticsearch.service.EsBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

/**
 * @author lilu
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class EsBookServiceImpl implements EsBookService {

    private final EsBookRepository esBookRepository;

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public Iterable<Book> findAll() {
        return esBookRepository.findAll();
    }

    @Override
    public Iterable<Book> saveAll(Iterable<Book> entities) {
        return esBookRepository.saveAll(entities);
    }

}
