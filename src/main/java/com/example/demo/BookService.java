package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Iterable<Book> getBooks() {
        return repository.findAll();
    }

    public Book findBookById(Long id) throws BookNotFoundException {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Long id, Book book) throws BookNotFoundException {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }

        book.setId(id);
        return repository.save(book);
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }
        repository.deleteById(id);
    }
}
