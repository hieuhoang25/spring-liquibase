package com.hicode.springliquibase.controller;

import com.hicode.springliquibase.model.Author;
import com.hicode.springliquibase.model.Book;
import com.hicode.springliquibase.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("author")
public class AuthorController {
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return  ResponseEntity.ok(authorService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(authorService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Author author){
        return  ResponseEntity.ok(authorService.create(author));
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Author author){
        return  ResponseEntity.ok(authorService.update(author));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return  ResponseEntity.ok(authorService.findAll());
    }
}
