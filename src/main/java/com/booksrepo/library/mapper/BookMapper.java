package com.booksrepo.library.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.booksrepo.library.exception.GenreException;
import com.booksrepo.library.model.Book;
import com.booksrepo.library.model.Genre;
import com.booksrepo.library.payload.dto.BookDTO;
import com.booksrepo.library.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookMapper {

	private final GenreRepository genreRepository;
	
	public BookDTO toBookDTO(Book savedBook) {

	    if (savedBook == null) {
	        return null;
	    }

	    BookDTO bookDTO = BookDTO.builder()
	            .id(savedBook.getId())
	            .isbn(savedBook.getIsbn())
	            .title(savedBook.getTitle())
	            .author(savedBook.getAuthor())
	            .description(savedBook.getDescription())
	            .publisher(savedBook.getPublisher())
	            .publicationDate(savedBook.getPublicationDate())
	            .genreName(savedBook.getGenre() != null ? savedBook.getGenre().getName() : null)
	            .genreId(savedBook.getGenre() != null ? savedBook.getGenre().getId() : null)
	            .publicationDate(savedBook.getPublicationDate())
	            .language(savedBook.getLanguage())
	            .pages(savedBook.getPages())
	            .totalCopies(savedBook.getTotalCopies())
	            .availableCopies(savedBook.getAvailableCopies())
	            .price(savedBook.getPrice())
	            .coverImageUrl(savedBook.getCoverImageUrl())	            
	            .active(savedBook.getActive())
	            .createdAt(savedBook.getCreatedAt())
	            .updatedAt(savedBook.getUpdatedAt())
	            .build();

	    return bookDTO;
	}
	
	public Book toBookEntity(BookDTO bookDTO) throws GenreException {
	    if (bookDTO == null) {
	        return null;
	    }

	    Book book = Book.builder()
	            .id(bookDTO.getId())
	            .isbn(bookDTO.getIsbn())
	            .title(bookDTO.getTitle())
	            .author(bookDTO.getAuthor())
	            .description(bookDTO.getDescription())
	            .publisher(bookDTO.getPublisher())
	            .publicationDate(bookDTO.getPublicationDate())
	            .language(bookDTO.getLanguage())
	            .pages(bookDTO.getPages())
	            .totalCopies(bookDTO.getTotalCopies())
	            .availableCopies(bookDTO.getAvailableCopies())
	            .price(bookDTO.getPrice())
	            .coverImageUrl(bookDTO.getCoverImageUrl())	            
	            .active(bookDTO.getActive() != null ? bookDTO.getActive() : true)
	            .createdAt(bookDTO.getCreatedAt())
	            .updatedAt(bookDTO.getUpdatedAt())
	            .build();

	    if (bookDTO.getGenreId() != null) {
	        Genre genre = genreRepository.findById(bookDTO.getGenreId()).orElseThrow(
					()-> new GenreException("Genre with Id "+ bookDTO.getId() +" not found."));
	        book.setGenre(genre);
	    }

	    return book;
	}
	
	public Book updateExistingBook(BookDTO bookDTO, Book existingBook) throws GenreException {
	    if (bookDTO == null || existingBook == null) {
	        return existingBook;
	    }

	    existingBook.setIsbn(bookDTO.getIsbn());
	    existingBook.setTitle(bookDTO.getTitle());
	    existingBook.setAuthor(bookDTO.getAuthor());
	    existingBook.setDescription(bookDTO.getDescription());
	    existingBook.setPublisher(bookDTO.getPublisher());
	    existingBook.setPublicationDate(bookDTO.getPublicationDate());
	    existingBook.setLanguage(bookDTO.getLanguage());
	    existingBook.setPages(bookDTO.getPages());
	    existingBook.setTotalCopies(bookDTO.getTotalCopies());
	    existingBook.setAvailableCopies(bookDTO.getAvailableCopies());
	    existingBook.setPrice(bookDTO.getPrice());
	    existingBook.setCoverImageUrl(bookDTO.getCoverImageUrl());
	    existingBook.setActive(bookDTO.getActive() != null ? bookDTO.getActive() : existingBook.getActive());

	    if (bookDTO.getGenreId() != null) {
	        Genre genre = genreRepository.findById(bookDTO.getGenreId()).orElseThrow(
					()-> new GenreException("Genre with Id "+ bookDTO.getId() +" not found."));
	        existingBook.setGenre(genre);
	    }

	    return existingBook;
	}
	
	public List<BookDTO> toBookDTOList(List<Book> books) {
	    if (books == null) {
	        return null;
	    }
	    return books.stream()
	            .map(this::toBookDTO)
	            .toList();
	}
}
