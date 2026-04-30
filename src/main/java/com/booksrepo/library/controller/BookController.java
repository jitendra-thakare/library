package com.booksrepo.library.controller;

import com.booksrepo.library.model.Book;
import com.booksrepo.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookRepository repo;

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        return repo.save(book);
    }
}