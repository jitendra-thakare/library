package com.booksrepo.library.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booksrepo.library.model.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long>{
	List<Genre> findByActiveTrueOrderByDisplayOrderAsc();
	List<Genre> findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
	List<Genre> findByParentGenreIdAndActiveTrueOrderByDisplayOrderAsc(Long parentGenreId);
	
	Long countByActiveTrue();
	
//	@Query("SELECT COUNT(1) FROM book b WHERE b.genreId=:genreId")
	//Long countBooksByGenre(@Param("genreId") Long genreId);
	
}
