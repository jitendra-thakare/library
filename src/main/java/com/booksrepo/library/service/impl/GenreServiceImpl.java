package com.booksrepo.library.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	
	@Autowired
	private final GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.toEntity(genreDTO);
    	Genre savedGenre = genreRepository.save(genre);
    	GenreDTO returnDTO = genreMapper.toGenreDTO(savedGenre);
    	return returnDTO;
    }

	@Override
	public List<GenreDTO> getAllGenres() {
		return genreRepository.findAll().stream()
				.map(genre -> genreMapper.toGenreDTO(genre)).collect(Collectors.toList());
	}
}