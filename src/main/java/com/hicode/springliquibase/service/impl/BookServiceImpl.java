package com.hicode.springliquibase.service.impl;

import com.hicode.springliquibase.model.Book;
import com.hicode.springliquibase.repository.BookRepository;
import com.hicode.springliquibase.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.delete(findById(id));
    }

    @Override
    public List<Book> findByAuthorName(String name) {
       return bookRepository.findByAuthorName(name);
    }
}
