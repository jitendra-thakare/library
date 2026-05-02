package com.booksrepo.library.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.booksrepo.library.exception.GenreException;
import com.booksrepo.library.mapper.BookMapper;
import com.booksrepo.library.model.Book;
import com.booksrepo.library.payload.dto.BookDTO;
import com.booksrepo.library.repository.BookRepository;
import com.booksrepo.library.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	private final BookRepository bookRepository;
	private final BookMapper bookMapper;
	
	@Override
	public BookDTO createBook(BookDTO bookDTO) throws GenreException {
		if(bookRepository.existsByIsbn(bookDTO.getIsbn())) {
			throw new RuntimeException("Book with the same ISBN already exists.");
		}
		Book book = bookMapper.toBookEntity(bookDTO);
		Book savedBook = bookRepository.save(book);
		return bookMapper.toBookDTO(savedBook);
	}

	@Override
	public List<BookDTO> createBooksBulk(List<BookDTO> bookDTOList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDTO getBookById(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deactivateBook(Long bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactivateBook(Long bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hardDeleteBook(Long bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getTotalActiveBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalAvailableBooks() {
		// TODO Auto-generated method stub
		return null;
	}

}
