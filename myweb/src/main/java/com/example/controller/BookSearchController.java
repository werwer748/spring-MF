package com.example.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookSearchController {

    private final String apiKey = "8f78fbb9c13b04c14a25790c1fc5792f";

    @GetMapping("/search/books") // query 받기 title="책제목"
    // generic 비워서 쓰고싶으면 <?>
    public ResponseEntity<String> searchBooks(String title) {
        // kakao Book Search OpenAPI URL
        final String url = "https://dapi.kakao.com/v3/search/book?query=" + title;

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + apiKey);
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(response.getBody());
    }
}
