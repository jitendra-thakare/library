package com.booksrepo.library.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.booksrepo.library.model.Genre;
import com.booksrepo.library.repository.GenreRepository;

import lombok.RequiredArgsConstructor;
import com.booksrepo.library.payload.dto.GenreDTO;

@Component
@RequiredArgsConstructor
public class GenreMapper {
	private final GenreRepository genreRepository;
	
	public GenreDTO toGenreDTO(Genre savedGenre) {
		if(savedGenre == null) {
			return null;
		}
		GenreDTO dto = GenreDTO.builder()
				.id(savedGenre.getId())
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
	    
	    if(savedGenre.getSubGenres() != null) {
	    dto.setSubGenre(savedGenre.getSubGenres().stream()
	    		.filter(subGenre -> subGenre.getActive())
	    				.map(subGenre -> toGenreDTO(subGenre)).collect(Collectors.toList()));
	    }
		
	    //dto.setBookCountLong();
	    return dto;
	}
	
	public Genre toEntity(GenreDTO genreDTO) {
		if(genreDTO == null) {
			return null;
		}
		Genre genre = Genre.builder()
					.id(genreDTO.getId())
	    			.code(genreDTO.getCode())
	    			.name(genreDTO.getName())
	    			.description(genreDTO.getDescription())
	    			.displayOrder(genreDTO.getDisplayOrder())
	    			.active(true)
	    			.build();
        if(genreDTO.getParentGenreId() != 0) {
    		genreRepository.findById(genreDTO.getParentGenreId())
    		.ifPresent(genre::setParentGenre);;
        }
        return genre;
	}
	
	public Genre updateExistingGenre(GenreDTO genreDTO, Genre genre) {
		if(genreDTO == null || genre == null) {
			return null;
		}
		genre.setCode(genreDTO.getCode());
		genre.setName(genreDTO.getName());
		genre.setDescription(genreDTO.getDescription());
		genre.setDisplayOrder(genreDTO.getDisplayOrder()!=null?genreDTO.getDisplayOrder():0);
		if(genreDTO.getActive() != null) {
			genre.setActive(genreDTO.getActive());
		}
        if(genreDTO.getParentGenreId() != 0) {
    		genreRepository.findById(genreDTO.getParentGenreId())
    		.ifPresent(genre::setParentGenre);;
        }
        
        return genre;
	}
}
