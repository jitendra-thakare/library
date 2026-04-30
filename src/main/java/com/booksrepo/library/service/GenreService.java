package com.booksrepo.library.service;


import com.booksrepo.library.model.Genre;

import payload.dto.GenreDTO;

public interface GenreService {
	GenreDTO createGenre(GenreDTO genreDTO);
}
