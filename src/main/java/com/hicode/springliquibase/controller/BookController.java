package com.hicode.springliquibase.controller;

import com.hicode.springliquibase.model.Book;
import com.hicode.springliquibase.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("book")
public class BookController {
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam("authorName") Optional<String> optName){
       if (!optName.isEmpty()){
           return ResponseEntity.ok(bookService.findByAuthorName(optName.get()));
       }
        return  ResponseEntity.ok(bookService.findAll());
    }

//    @GetMapping("/search")
//    public ResponseEntity<?> getByAuthorName(@RequestParam("authorName") String name){
//        return  ResponseEntity.ok(bookService.findByAuthorName(name));
//    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(bookService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book){
        return  ResponseEntity.ok(bookService.create(book));
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Book book){
        return  ResponseEntity.ok(bookService.update(book));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return  ResponseEntity.ok(bookService.findAll());
    }
}
