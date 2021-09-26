package com.meishubao.jsondoc.controller;

import com.meishubao.jsondoc.documentation.DocumentationConstants;
import com.meishubao.jsondoc.exception.ItemNotFoundException;
import com.meishubao.jsondoc.model.User;
import com.meishubao.jsondoc.repository.UserRepository;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiError;
import org.jsondoc.core.annotation.ApiErrors;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "用户服务接口", name = "UserController")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@ApiMethod(id = DocumentationConstants.USER_FIND_ONE, description = "获取用户信息接口")
	@ApiErrors(apierrors = {
			@ApiError(code = "404", description = "未找到指定id用户")
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findOne(@PathVariable("id") Long id) throws ItemNotFoundException {
		User findOne = userRepository.findOne(id);
		if(findOne == null) {
			throw new ItemNotFoundException();
		} else {
			return findOne; 
		}
	}
	
	@RequestMapping
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
