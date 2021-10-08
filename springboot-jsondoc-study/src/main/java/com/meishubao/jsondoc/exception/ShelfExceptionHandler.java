package com.meishubao.jsondoc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ShelfExceptionHandler {
	
	@ExceptionHandler(value = ItemNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void handleItemNotFoundException(ItemNotFoundException e) {
		
	}

}
