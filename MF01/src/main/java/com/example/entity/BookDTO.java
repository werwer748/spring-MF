package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok API
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO { // 나중에는 Book으로 작성된 Entity를 작성하겠지만 지금은 구조파악을 위해서...

    private Long num;
    private String title;
    private int price;
    private String author;
    private int page;

}
