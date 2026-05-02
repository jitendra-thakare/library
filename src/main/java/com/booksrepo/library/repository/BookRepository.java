package com.booksrepo.library.repository;

import com.booksrepo.library.model.Book;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {
	Optional<Book> findByIsbn(String isbn);
	boolean existsByIsbn(String isbn);
	@Query("SELECT b FROM Book b WHERE " +
			"(LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
			"LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
			"LOWER(b.isbn) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
			"(:genreId IS NULL OR b.genre.id = :genreId) AND " +
			"(:availableOnly = FALSE OR :availableCopies >=0 ) AND b.active = TRUE")
	Page<Book> searchBookWithFilters(
			@Param("searchTerm") String searchTerm,
			@Param("genreId") Long genreIdLong ,
			@Param("availableOnly") Boolean availableOnly
			,Pageable pageable);
	@Query("SELECT COUNT(1) FROM Book b WHERE b.active = TRUE")
	Long countByActiveBooks();
	
	@Query("SELECT COUNT(1) FROM Book b WHERE b.active = TRUE AND b.availableCopies > 0")
	Long countAvailableBooks();
}