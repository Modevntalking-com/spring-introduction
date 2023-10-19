package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController // RESTful API - GET/POST/PUT/DELETE
@RequestMapping("/books") // http://localhost:8080/books - our prefix
public class BookController {

    private BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/") // http://localhost:8080/books/ - endpoint address
    public Iterable<Book> getBooks() {
        return service.getBooks();
    }

    @GetMapping("/{id}") // http://localhost:8080/books/1 - endpoint address
    public Book findBookById(@PathVariable Long id) throws BookNotFoundException {
        return service.findBookById(id);
    }

    @PostMapping("/") // http://localhost:8080/books/ - endpoint address
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @PutMapping("/{id}") // http://localhost:8080/books/{id} - endpoint address
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) throws BookNotFoundException {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/{id}") // http://localhost:8080/books/{id} - endpoint address
    public void deleteBook(@PathVariable Long id) throws BookNotFoundException {
        service.deleteBook(id);
    }
}
