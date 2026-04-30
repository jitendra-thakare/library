package com.booksrepo.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booksrepo.library.model.Genre;
import com.booksrepo.library.service.GenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/genre")
public class GenreController {
	private final GenreService genreService;
	
	@PostMapping
	public ResponseEntity<Genre> addGenre(@RequestBody Genre genre){
		Genre newGenre = genreService.createGenre(genre);
		return ResponseEntity.ok(newGenre);
	}
}
