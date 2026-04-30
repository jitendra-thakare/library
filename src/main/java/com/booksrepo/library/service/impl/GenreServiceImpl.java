package com.booksrepo.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksrepo.library.mapper.GenreMapper;
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

    	return GenreMapper.toGenreDTO(savedGenre);
    }
}