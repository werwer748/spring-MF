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
    <h2>Book Detail</h2>
    <div class="card">
        <div class="card-header">Book Information</div>
        <div class="card-body">
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <th>번호</th>
                    <td>${book.num}</td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>${book.title}</td>
                </tr>
                <tr>
                    <th>가격</th>
                    <td>${book.price}</td>
                </tr>
                <tr>
                    <th>저자</th>
                    <td>${book.author}</td>
                </tr>
                <tr>
                    <th>페이지 수</th>
                    <td>${book.page}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="card-footer text-right">
            <a href="edit?num=${book.num}" class="btn btn-secondary">Edit</a>
            <form action="delete" method="post" style="display:inline;" onsubmit="return confirm('정말로 삭제하시겠습니까?');">
                <input type="hidden" name="num" value="${book.num}">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
            <a href="list" class="btn btn-primary">Back to List</a>
        </div>
    </div>
</div>

</body>
</html>