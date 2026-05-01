package com.booksrepo.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booksrepo.library.exception.GenreException;
import com.booksrepo.library.mapper.GenreMapper;
import com.booksrepo.library.model.Genre;
import com.booksrepo.library.repository.GenreRepository;
import com.booksrepo.library.service.GenreService;

import lombok.RequiredArgsConstructor;
import com.booksrepo.library.payload.dto.GenreDTO;

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

	@Override
	public GenreDTO getGenreById(Long genreId) throws GenreException {
		Genre genre  = genreRepository.findById(genreId).orElseThrow(
				()-> new GenreException("Genre not found."));
		
		return genreMapper.toGenreDTO(genre);
	}

	@Override
	public GenreDTO updateGenreDTO(Long genreId, GenreDTO genreDTO) throws GenreException {
		Genre existingGenre =  genreRepository.findById(genreId).orElseThrow(
				()-> new GenreException("Genre not found."));

		Genre updatedGenre = genreMapper.updateExistingGenre(genreDTO,existingGenre);
		
		genreRepository.save(updatedGenre);
		return genreMapper.toGenreDTO(updatedGenre);
	}

	@Override
	@Transactional
	public void deactiveGenre(Long genreId)  throws GenreException {
	    Genre existingGenre = genreRepository.findById(genreId)
	            .orElseThrow(() -> new GenreException("Genre not found"));

	    existingGenre.setActive(false);
	    genreRepository.save(existingGenre);
		
	}
	
	@Override
	@Transactional
	public void reactiveGenre(Long genreId) throws GenreException {
		Genre existingGenre =  genreRepository.findById(genreId).orElseThrow(
				()-> new GenreException("Genre not found."));
		existingGenre.setActive(true);
	    genreRepository.save(existingGenre);
		
	}

	@Override
	public void hardDeleteGenre(Long genreId)  throws GenreException {
		Genre existingGenre =  genreRepository.findById(genreId).orElseThrow(
				()-> new GenreException("Genre not found."));
		genreRepository.delete(existingGenre);
		
	}

	@Override
	public List<GenreDTO> getAllActiveGenresWithSubgenres() {
		List<Genre> topLevelGenres = genreRepository.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
		return genreMapper.toGenreDTOList(topLevelGenres);
	}

	@Override
	public List<GenreDTO> getTopLevelGenres() {
		List<Genre> topLevelGenres = genreRepository.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
		return genreMapper.toGenreDTOList(topLevelGenres);
	}

	@Override
	public long getTotalActiveGenres() {
		return genreRepository.countByActiveTrue();
	}

	@Override
	public long getBookCountByGenre(Long genreId) {
		// TODO Auto-generated method stub
		return 0;
	}
}