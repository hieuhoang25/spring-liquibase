package com.hicode.springliquibase.service;

import com.hicode.springliquibase.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author findById(Integer id);

    Author create(Author author);

    Author update(Author author);

    void delete(Integer id);
}
