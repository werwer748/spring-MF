package com.example.jwt.filter;

public interface JwtProperties {
    String SECRET = "cosin";
    int EXPIRATION_TIME = 60 * 10 * 1000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
