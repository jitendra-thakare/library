package com.booksrepo.library.service;

import java.util.List;

import javax.print.attribute.standard.PageRanges;

import com.booksrepo.library.payload.dto.BookDTO;
import com.booksrepo.library.payload.request.BookSearchRequest;
import com.booksrepo.library.payload.response.PageResponse;

public interface BookService {
	BookDTO createBook(BookDTO bookDTO);
	List<BookDTO> createBooksBulk(List<BookDTO> bookDTOList);
	BookDTO getBookById(Long bookId);
	BookDTO updateBook(Long bookId, BookDTO bookDTO);
	void deactivateBook(Long bookId);
	void reactivateBook(Long bookId);
	void hardDeleteBook(Long bookId);
	
	//PageResponse<BookDTO> searchBooksWithFilters(BookSearchRequest searchRequest);
	Long getTotalActiveBooks();
	Long getTotalAvailableBooks();
	
}

