package com.example.repository;

import com.example.entity.BookDTO;

import java.util.List;

public interface BookMapper {
    public List<BookDTO> bookList(String search);

    public void deleteBook(int num);
}
