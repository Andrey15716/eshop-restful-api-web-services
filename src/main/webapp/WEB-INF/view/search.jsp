<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Search product</h2>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="${contextPath}/home">Online Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="${contextPath}/login/profile/1">Profile</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="col-md-8 offset-md-4">
        <form method="post">
            <div class="form-group">
                <label for="searchParam">Product:</label>
                <input type="text" class="form-control w-25" id="searchParam" name="searchParam"
                       placeholder="Введите наименование товара"
                       required>
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>
        <div class="container-fluid mb-4">
            <c:forEach items="${search_result}" var="searchItem">
            <div class="card w-25 m-1" type="searchItem">
                <div>
                    <a href="${contextPath}/product/${searchItem.getId()}&name=${searchItem.getName()}"></a>
                    <i class="fa-solid fa-cart-arrow-down fa-2x " style="color: black"></i>

                    <div class="card-body">
                        <img class="card-img" style="width:45%;height:100%"
                             src="images/${searchItem.getImageName()}" alt="Product image">
                        <div class="list-group list-group-flush">
                            <li class="card-title"><b>Name:</b> <a>${searchItem.getName()}</a></li>
                            <li class="card-title"><b>Description:</b> <a>${searchItem.getDescription()}</a></li>
                            <li class="card-title"><b>Price:</b> <a>${searchItem.getPrice()}</a></li>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>