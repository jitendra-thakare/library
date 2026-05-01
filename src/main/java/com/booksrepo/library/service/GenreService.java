package com.booksrepo.library.service;


import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;

import com.booksrepo.library.exception.GenreException;

import com.booksrepo.library.payload.dto.GenreDTO;

public interface GenreService {
	GenreDTO createGenre(GenreDTO genreDTO);
	
	List<GenreDTO> getAllGenres();
	
	GenreDTO getGenreById(Long genreId) throws GenreException;
	
	GenreDTO updateGenreDTO(Long genreId, GenreDTO genreDTO) throws GenreException;

	void deactiveGenre(Long genreId)  throws GenreException;

	void reactiveGenre(Long genreId) throws GenreException;
	
	void hardDeleteGenre(Long genreId) throws GenreException;
	
	List<GenreDTO> getAllActiveGenresWithSubgenres();
	
	List<GenreDTO> getTopLevelGenres();
	
	//Page<GenreDTO> searchGenres(String searchText, Pageable pageable);
	
	long getTotalActiveGenres();
	
	long getBookCountByGenre(Long genreId);
	
	
}
