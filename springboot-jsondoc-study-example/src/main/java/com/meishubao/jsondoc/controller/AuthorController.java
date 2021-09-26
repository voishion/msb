package com.meishubao.jsondoc.controller;

import com.meishubao.jsondoc.documentation.DocumentationConstants;
import com.meishubao.jsondoc.model.Author;
import com.meishubao.jsondoc.repository.AuthorRepository;
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
@RequestMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(name = "AuthorController", description = "作者服务接口", group = DocumentationConstants.GROUP_LIBRARY)
@ApiAuthToken(roles = { "*" }, testtokens = "abc", scheme = "Bearer")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@ApiMethod(id = DocumentationConstants.AUTHOR_FIND_ONE, description = "获取作者信息接口")
	@ApiAuthToken
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ApiResponseObject Author findOne(@ApiPathParam(name = "id", description = "编号") @PathVariable Long id) {
		return authorRepository.findOne(id);
	}

	@ApiMethod(id = DocumentationConstants.AUTHOR_FIND_ALL, description = "获取所有作者接口")
	@RequestMapping(method = RequestMethod.GET)
	public @ApiResponseObject List<Author> findAll() {
		return authorRepository.findAll();
	}

	@ApiMethod(id = DocumentationConstants.AUTHOR_SAVE, description = "保存作者接口")
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ApiResponseObject ResponseEntity<Void> save(@ApiBodyObject @RequestBody Author author, UriComponentsBuilder uriComponentsBuilder) {
		authorRepository.save(author);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(uriComponentsBuilder.path("/authors/{id}").buildAndExpand(author.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiMethod(id = DocumentationConstants.AUTHOR_DELETE, description = "删除作者接口")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@ApiPathParam(name = "id", description = "编号") @PathVariable Long id) {
		Author author = authorRepository.findOne(id);
		authorRepository.delete(author);
	}

}
