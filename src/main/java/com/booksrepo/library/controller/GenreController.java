package com.booksrepo.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booksrepo.library.model.Genre;
import com.booksrepo.library.service.GenreService;

import lombok.RequiredArgsConstructor;
import payload.dto.GenreDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/genre")
public class GenreController {
	private final GenreService genreService;
	
	@PostMapping
	@RequestMapping("create")
	public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genre){
		GenreDTO newGenre = genreService.createGenre(genre);
		return ResponseEntity.ok(newGenre);
	}
}
