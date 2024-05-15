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
        <h2>Card Header and Footer</h2>
        <div class="card">
            <div class="card-header">대갈이!!</div>
            <div class="card-body">Content</div>
            <div class="card-footer">MF1탄 강준기</div>
        </div>
    </div>

</body>
</html>