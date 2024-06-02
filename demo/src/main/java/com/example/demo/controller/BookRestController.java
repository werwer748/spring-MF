package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getAllList() {
        // return new ResponseEntity<>(bookService.getAllList(), HttpStatus.ok)
        return ResponseEntity.ok(bookService.getAllList());
    }

    @PostMapping("/books")
    public ResponseEntity<?> register(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.register(book));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.getById(id));
        } catch (Exception e) {
//            return new ResponseEntity<>("Book not found with id: " + id, HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with id: " + id);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        try {
            return ResponseEntity.ok(bookService.update(id, book));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Updated");
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            bookService.deleteById(id);
            return ResponseEntity.ok("Deleted book with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed: " + id);
        }
    }
}
