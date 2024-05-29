<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cpath" value="/myweb" />

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container-fluid mt-3">
    <h1>Java Spring Full Stack Developer</h1>
    <p>Resize the browser window to see the effect.</p>
    <p>The first, second and third row will automatically stack on top of each other when the screen is less than 576px wide.</p>

    <div class="container-fluid">

        <div class="card">
            <div class="card-header">Java Spring Framework</div>
            <div class="card-body">
                <div class="row">
                    <div class="col-sm-2 mb-2">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Left</h4>
                                <p class="card-text">Some example text. Some example text.</p>
                                <a href="#" class="card-link">Card link</a>
                                <a href="#" class="card-link">Another link</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-7 mb-2">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">책 수정하기</h4>
                                <p class="card-text">선택한 <span class="badge badge-primary">${book.title}</span> 의 정보를 수정하기</p>
                                <form action="${cpath}/update" method="post">
                                    <input type="hidden" name="num" value="${book.num}" />
                                    <div class="form-group">
                                        <label for="title">제목:</label>
                                        <input type="text" class="form-control" value="${book.title}" id="title" name="title">
                                    </div>
                                    <div class="form-group">
                                        <label for="price">가격:</label>
                                        <input type="text" class="form-control" value="${book.price}" id="price" name="price">
                                    </div>
                                    <div class="form-group">
                                        <label for="author">저자:</label>
                                        <input type="text" class="form-control" value="${book.author}" id="author" name="author">
                                    </div>
                                    <div class="form-group">
                                        <label for="page">페이지수:</label>
                                        <input type="text" class="form-control" value="${book.page}" id="page" name="page">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-sm">수정</button>
                                    <button type="reset" class="btn btn-primary btn-sm">취소</button>
                                    <button type="button" class="btn btn-primary btn-sm" onclick="location.href='${cpath}/list'">목록</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Right</h4>
                                <p class="card-text">Some example text. Some example text.</p>
                                <a href="#" class="card-link">Card link</a>
                                <a href="#" class="card-link">Another link</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer text-center">Java Spring Full Stack Developer(강준기)</div>
        </div>



    </div>
</div>

</body>
</html>

