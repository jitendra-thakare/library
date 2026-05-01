package com.booksrepo.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.booksrepo.library.payload.response.ApiResponse;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(GenreException.class)
	public ResponseEntity<ApiResponse> handleGenreException(GenreException e){
		return ResponseEntity.status((HttpStatus.BAD_REQUEST))
				.body(new ApiResponse(e.getMessage(),false));
	}
}
