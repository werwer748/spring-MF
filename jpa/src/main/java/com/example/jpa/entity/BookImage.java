package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤책에 속한 이미지인가?
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private int type; // 1, 2, 3

    private String path; // 파일경로(이름)
}
