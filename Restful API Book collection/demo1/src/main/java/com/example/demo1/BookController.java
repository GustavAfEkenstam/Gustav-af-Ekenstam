package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
public class BookController {

    @Autowired //an annotation for automatic dependency injection
    private BookRepository bookRepository;

    // Read operation
    @GetMapping("/books") //adds the GET endpoint
    public ResponseEntity<List<Book>> getAllBooks()
    {
        try {
            List<Book> bookList = new ArrayList<>();
            bookRepository.findAll().forEach(bookList::add);

            if (bookList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{title}") //adds the GET endpoint
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {

        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(bookList::add);

        for(Book book : bookList){

            if(book.getTitle().equals(title)){
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/books") //adds the POST endpoint
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        try {
            Book bookObj = bookRepository.save(newBook);
            return new ResponseEntity<>(bookObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
