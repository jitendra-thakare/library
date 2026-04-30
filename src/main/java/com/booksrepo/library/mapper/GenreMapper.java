package com.booksrepo.library.mapper;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.booksrepo.library.model.Genre;

import payload.dto.GenreDTO;

public class GenreMapper {
	public static GenreDTO toGenreDTO(Genre savedGenre) {
		if(savedGenre == null) {
			return null;
		}
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
	    
	    if(savedGenre.getSubGenres() != null) {
	    dto.setSubGenre(savedGenre.getSubGenres().stream()
	    		.filter(subGenre -> subGenre.getActive())
	    				.map(subGenre -> toGenreDTO(subGenre)).collect(Collectors.toList()));
	    }
		
	    //dto.setBookCountLong();
	    return dto;
	}
}
