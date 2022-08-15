<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>${product.getName()}</h2>

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
                <li class="nav-item"><a class="nav-link" href="${contextPath}/search">Search</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid" type="product">
    <div class="row">
        <div class="block1">
            <p>Наименование</p>
            <a>${product.getName()}</a>
            <p>Описание</p>
            <a>${product.getDescription()}</a>
            <p>Цена</p>
            <a>${product.getPrice()}</a>
        </div>
        <div class="card w-25 m-1" type="product">
            <div class="card-body">
                <img class="card-img" style="width:150px;height:120px"
                     src="${contextPath}/images/${product.getImageName()}" alt="Product images">
                <a href="${contextPath}/cart/add?product_id=${product.getId()}&name=${product.getName()}"
                   class="btn btn-primary stretched-link">Добавить в корзину</a>
                <i class="fa-solid fa-cart-arrow-down fa-2x " style="color: black"></i>
            </div>
        </div>
    </div>
</div>
</body>
</html>