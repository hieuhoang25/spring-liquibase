package com.hicode.springliquibase.service;



import com.hicode.springliquibase.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Integer id);

    Book create(Book author);

    Book update(Book author);

    void delete(Integer id);

    List<Book> findByAuthorName(String name);
}
