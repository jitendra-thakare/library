package com.booksrepo.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booksrepo.library.service.GenreService;

import lombok.RequiredArgsConstructor;

import com.booksrepo.library.exception.GenreException;
import com.booksrepo.library.payload.dto.GenreDTO;

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
	
	@GetMapping()
	public ResponseEntity<?> getAllGenres(){
		List<GenreDTO> genres = genreService.getAllGenres();
		return ResponseEntity.ok(genres);
	}
	@GetMapping("{genreId}")
	public ResponseEntity<?> getGenreById(@RequestParam("genreId")Long genreId) throws GenreException{
		GenreDTO genres = genreService.getGenreById(genreId);
		return ResponseEntity.ok(genres);
	}
	
	@PutMapping("{genreId}")
	public ResponseEntity<?> updateGenre(@RequestParam("genreId")Long genreId,@RequestBody GenreDTO genreDTO) throws GenreException{
		GenreDTO genres = genreService.updateGenreDTO(genreId, genreDTO);
		return ResponseEntity.ok(genres);
	}
}
