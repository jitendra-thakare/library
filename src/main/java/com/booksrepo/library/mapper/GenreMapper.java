package com.booksrepo.library.mapper;

import java.util.List;
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

	    if (savedGenre == null) {
	        return null;
	    }

	    GenreDTO dto = GenreDTO.builder()
	            .id(savedGenre.getId())
	            .code(savedGenre.getCode())
	            .name(savedGenre.getName())
	            .description(savedGenre.getDescription())
	            .displayOrder(savedGenre.getDisplayOrder())
	            .active(savedGenre.getActive())
	            .createdAt(savedGenre.getCreatedAt())
	            .updatedAt(savedGenre.getUpdatedAt())
	            .build();

	    if (savedGenre.getParentGenre() != null) {
	        dto.setParentGenreId(savedGenre.getParentGenre().getId());
	        dto.setParentGenreName(savedGenre.getParentGenre().getName());
	    }

	    
	    if (savedGenre.getSubGenres() != null) {

	        List<GenreDTO> subGenreDtos = savedGenre.getSubGenres().stream()
	                .filter(Genre::getActive)
	                .map(subGenre -> GenreDTO.builder()
	                        .id(subGenre.getId())
	                        .code(subGenre.getCode())
	                        .name(subGenre.getName())
	                        .description(subGenre.getDescription())
	                        .displayOrder(subGenre.getDisplayOrder())
	                        .active(subGenre.getActive())
	                        .build())
	                .collect(Collectors.toList());

	        dto.setSubGenre(subGenreDtos);
	    }

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
	    			.active(genreDTO.getActive() != null ? genreDTO.getActive() : true)
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
	
	public List<GenreDTO> toGenreDTOList(List<Genre> genreList){
		return genreList.stream().map(genre-> toGenreDTO(genre)).collect(Collectors.toList());	
		}
}
