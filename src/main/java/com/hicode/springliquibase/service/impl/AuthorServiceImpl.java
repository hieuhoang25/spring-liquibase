package com.hicode.springliquibase.service.impl;

import com.hicode.springliquibase.model.Author;
import com.hicode.springliquibase.repository.AuthorRepository;
import com.hicode.springliquibase.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id).orElseThrow(()-> new RuntimeException("Author not found"));
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void delete(Integer id) {
        authorRepository.delete(this.findById(id));
    }
}
