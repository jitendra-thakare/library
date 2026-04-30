package com.booksrepo.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksrepo.library.model.Genre;
import com.booksrepo.library.repository.GenreRepository;
import com.booksrepo.library.service.GenreService;

import lombok.RequiredArgsConstructor;
import payload.dto.GenreDTO;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

	@Autowired
    private final GenreRepository genreRepository;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {

        Genre genre = Genre.builder()
        			.code(genreDTO.getCode())
        			.name(genreDTO.getName())
        			.description(genreDTO.getDescription())
        			.displayOrder(genreDTO.getDisplayOrder())
        			.active(true)
        			.build();
        if(genreDTO.getParentGenreId() != 0) {
    		Genre parentGenre = genreRepository.findById(genreDTO.getParentGenreId()).get();
    		genre.setParentGenre(parentGenre);
        }
    	Genre savedGenre = genreRepository.save(genre);
    	GenreDTO dto = GenreDTO.builder()
    			.code(savedGenre.getCode())
    			.name(savedGenre.getName())
    			.description(savedGenre.getDescription())
    			.displayOrder(savedGenre.getDisplayOrder())
    			.active(true)
    			.build();
    	
        if(savedGenre.getParentGenre() != null) {
        	dto.setParentGenreId(savedGenre.getParentGenre().getId());
        	dto.setParentGenreName(savedGenre.getParentGenre().getName());
    		
        }
        
        //dto.setSubGenre(savedGenre.getSubGenres().stream().filter(subGenre -> subGenre.getActive().map()));
    	
        //dto.setBookCountLong();
    	return dto;
    }
}