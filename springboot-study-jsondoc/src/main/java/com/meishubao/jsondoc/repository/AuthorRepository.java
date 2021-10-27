package com.meishubao.jsondoc.repository;

import com.meishubao.jsondoc.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class AuthorRepository {

	@Autowired
	BookRepository bookRepository;

	void deleteByIdGreaterThan(Long id) {
		log.info("deleteByIdGreaterThan id:{}", id);
	}

	public Author findOne(Long id) {
		return Author.builder().id(id).name("Author" + id).books(bookRepository.findAll()).build();
	}

	public List<Author> findAll() {
		return Stream.iterate(0, (x) -> x + 1).limit(10).map(x -> findOne(x.longValue())).collect(Collectors.toList());
	}

	public void save(Author author) {
		log.info("save author:{}", author);
	}

	public void delete(Author author) {
		log.info("delete author:{}", author);
	}

}
