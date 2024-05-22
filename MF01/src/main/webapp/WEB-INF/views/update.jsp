<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- implementation 'javax.servlet:jstl:1.2' 추가되어 있어야 사용 가능 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 기본 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 날짜. 시간, 통화 등의 포맷팅에 도움 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- 스트링 조작에 도움 -->

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

    <div class="container">
        <h2>Web MVC Framework Basic</h2>
        <div class="card">
            <div class="card-header">Book Update</div>
            <div class="card-body">
                <form action="/MF01/update-post.do" method="post">

                    <input type="hidden" name="num" id="num" value="${book.num}" />

                    <div class="form-group">
                        <label for="title">제목:</label>
                        <input type="text" class="form-control" placeholder="Enter title" id="title" name="title" value="${book.title}">
                    </div>

                    <div class="form-group">
                        <label for="price">가격:</label>
                        <input type="text" class="form-control" placeholder="Enter price" id="price" name="price" value="${book.price}">
                    </div>

                    <div class="form-group">
                        <label for="author">저자:</label>
                        <input type="text" class="form-control" placeholder="Enter author" id="author" name="author" value="${book.author}">
                    </div>

                    <div class="form-group">
                        <label for="page">페이지수:</label>
                        <input type="text" class="form-control" placeholder="Enter page" id="page" name="page" value="${book.page}">
                    </div>

                    <button type="submit" class="btn btn-primary btn-sm">수정</button>
                    <button type="reset" class="btn btn-danger btn-sm">취소</button>
                    <button type="button" class="btn btn-info btn-sm" onclick="location.href='/MF01/list.do'">목록</button>

                </form>
            </div>
            <div class="card-footer">MF1탄 강준기</div>
        </div>
    </div>

</body>
</html>