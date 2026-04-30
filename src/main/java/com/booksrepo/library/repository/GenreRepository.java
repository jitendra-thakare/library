package com.booksrepo.library.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.booksrepo.library.model.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long>{
	

}
