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
    <script>

        function goDel(num) {
            // alert(num)
            location.href = '/MF01/delete.do?num='+num;
        }

        function goRegister() {
            location.href = '/MF01/register-get';
        }
    </script>
</head>
<body>

    <div class="container">
        <h2>Web MVC Framework Basic</h2>
        <div class="card">
            <div class="card-header" style="justify-content: space-between;">
                Book List
<%--                <label>--%>
                    <select id="listOrder" name="listOrder" onchange="onChangeListOrder()">
                        <option value="num">번호</option>
                        <option value="title">제목</option>
                        <option value="price">가격</option>
                        <option value="author">저자</option>
                        <option value="page">페이지수</option>
                    </select>
<%--                </label>--%>
            </div>
            <div class="card-body">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>가격</th>
                            <th>저자</th>
                            <th>페이지수</th>
                            <th>삭제</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${list}">
                            <tr>
                                <!-- 선행학습 -->
<%--                                <td><a href="detail?num=${book.num}">${book.num}</a></td>--%>
                                <!-- 수업 -->
                                <td>${book.num}</td>
                                <td><a href="/MF01/view?num=${book.num}">${book.title}</a></td>
                                <td>${book.price}</td>
                                <td>${book.author}</td>
                                <td>${book.page}</td>
                                <td>
                                    <button
                                            class="btn btn-sm btn-warning"
                                            onclick="goDel(${book.num})"
                                    >
                                        삭제
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!-- 수업 -->
                <div>
                    <label>
                        <input type="text" placeholder="제목 또는 저자 검색" id="search" name="search" value="" />
                    </label>
                    <button class="btn btn-sm btn-primary" id="searchBtn" onclick="onClickSearchBtn()">검색</button>
                </div>
                <button
                        class="btn btn-sm btn-primary"
                        onclick="goRegister()"
                >
                    등록하기
                </button>
            </div>
            <div class="card-footer">인프런_마프 1탄_강준기</div>
        </div>
    </div>

    <script>
        function onClickSearchBtn() {
            const search = document.querySelector('#search').value;
            // console.log('확인', search);
            location.href = '/MF01/list?search=' + search;
        }

        // function onChangeListOrder() {
        //     const listOrder = document.querySelector('#listOrder').value;
        //     console.log('확인', listOrder);
        // }
    </script>
</body>
</html>