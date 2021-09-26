package com.meishubao.jsondoc.controller;

import com.meishubao.jsondoc.documentation.DocumentationConstants;
import com.meishubao.jsondoc.model.Book;
import com.meishubao.jsondoc.repository.BookRepository;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(name = "BookController", description = "图书服务接口", group = DocumentationConstants.GROUP_LIBRARY)
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@ApiMethod(id = DocumentationConstants.BOOK_FIND_ONE, summary = "Gets a book given the book ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ApiResponseObject Book findOne(@ApiPathParam(name = "id") @PathVariable Long id) {
		return bookRepository.findOne(id);
	}
	
	@ApiMethod(id = DocumentationConstants.BOOK_FIND_ALL)
	@RequestMapping(method = RequestMethod.GET)
	public @ApiResponseObject List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@ApiMethod(id = DocumentationConstants.BOOK_SAVE)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ApiResponseObject ResponseEntity<Void> save(@ApiBodyObject @RequestBody Book book, UriComponentsBuilder uriComponentsBuilder) {
		bookRepository.save(book);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(uriComponentsBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@ApiMethod(id = DocumentationConstants.BOOK_DELETE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@ApiPathParam(name = "id") @PathVariable Long id) {
		Book book = bookRepository.findOne(id);
		bookRepository.delete(book);
	}

}
