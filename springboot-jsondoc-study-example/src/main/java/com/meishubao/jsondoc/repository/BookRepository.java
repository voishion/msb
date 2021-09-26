package com.meishubao.jsondoc.repository;

import com.meishubao.jsondoc.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class BookRepository {
	
	void deleteByIdGreaterThan(Long id) {
		log.info("deleteByIdGreaterThan id:{}", id);
	}

	public Book findOne(Long id) {
		return Book.builder().id(id).title("《知识就是力量》").price(23.45D).build();
	}

	public List<Book> findAll() {
		return Stream.iterate(0, (x) -> x + 1).limit(10).map(x -> findOne(x.longValue())).collect(Collectors.toList());
	}

	public void save(Book book) {
		log.info("save book:{}", book);
	}

	public void delete(Book book) {
		log.info("delete book:{}", book);
	}
}
