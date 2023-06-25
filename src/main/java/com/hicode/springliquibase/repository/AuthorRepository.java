package com.hicode.springliquibase.repository;

import com.hicode.springliquibase.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository  extends JpaRepository<Author, Integer> {
}
