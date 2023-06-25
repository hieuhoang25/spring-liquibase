package com.hicode.springliquibase.repository;

import com.hicode.springliquibase.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b JOIN Author a on b.authorId = a.id where a.name = ?1")
    public List<Book> findByAuthorName(String name);
}
