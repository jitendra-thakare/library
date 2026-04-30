package com.booksrepo.library.service;


import java.util.List;

import payload.dto.GenreDTO;

public interface GenreService {
	GenreDTO createGenre(GenreDTO genreDTO);
	
	List<GenreDTO> getAllGenres();
}
