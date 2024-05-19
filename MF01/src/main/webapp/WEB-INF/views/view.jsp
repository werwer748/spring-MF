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
            <div class="card-header">Book View</div>
            <div class="card-body">
                <table class="table table-bordered">
                    <tr>
                        <td>번호</td>
                        <td>${book.num}</td>
                    </tr>
                    <tr>
                        <td>제목</td>
                        <td>${book.title}</td>
                    </tr>
                    <tr>
                        <td>가격</td>
                        <td>${book.price}</td>
                    </tr>
                    <tr>
                        <td>저자</td>
                        <td>${book.author}</td>
                    </tr>
                    <tr>
                        <td>페이지수</td>
                        <td>${book.page}</td>
                    </tr>
                </table>
                <button class="btn btn-sm btn-primary action">목록</button>
                <button class="btn btn-sm btn-success action">수정</button>
                <button class="btn btn-sm btn-warning action">삭제</button>
            </div>
            <div class="card-footer">MF1탄 강준기</div>
        </div>
    </div>
    <form id="myForm" method="post" action="">
        <input type="hidden" name="num" id="num" value="${book.num}">
    </form>
    <script>
        document.querySelector(".card-body").addEventListener("click", function (e) {
            if (e.target.classList.contains("action")) {
                let form = document.getElementById("myForm");
                let inputNum = document.getElementById("num");
                switch (e.target.classList[2]) {
                    case "btn-primary":
                        // location.href = "/MF01/list";
                        form.action = "/MF01/list";
                        if (inputNum) {
                            inputNum.remove();
                        }
                        form.method = "GET";
                        break;
                    case "btn-success":
                        // location.href = "/MF01/update-get?num=1";
                        form.action = "/MF01/update-get";
                        form.method = "POST";
                        break;
                    case "btn-warning":
                        // location.href = "/MF01/delete?num=1";
                        form.action = "/MF01/delete";
                        form.method = "POST";
                        break;
                }
                form.submit();
            }
        });
    </script>
</body>
</html>