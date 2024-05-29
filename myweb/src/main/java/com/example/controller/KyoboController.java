package com.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@RestController
public class KyoboController {

    @GetMapping("/kysearch") // @RequestParam => 쿼리 스트링 받기 그런데 프론트에서 보내는 쿼리 키값과 컨트롤러 매개변수명이 일치하면 생략 가능
    public ResponseEntity<?> kysearch(@RequestParam("title") String title) { // 이 경우 생략 가능하다는거
        String url = "https://search.kyobobook.co.kr/search?keyword=" + title;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response); // 교보문고 검색 결과의 HTML 코드

        // 서버가 클라이언트에게 응답시 정보를 알려주는 행위(Header)
        HttpHeaders headers = new HttpHeaders();
        // 바이너리 데이터로 내려가서 한글 꺠질 수 있음
        headers.setContentType(MediaType.APPLICATION_JSON); // APPLICATION_JSON_UTF8 이걸 안써도 APPLICATION_JSON 쓰면 지원 된다고 함

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
