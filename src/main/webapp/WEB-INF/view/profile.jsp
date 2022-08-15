<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
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
<h2>Profile</h2>

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
<div class="container-fluid">
    <div class=" row">
        <p align="left" style="font-size: 22px">Наши товары</p>
        <div class="card w-25 m-1">
            <div class="card-body">
                <ul class="list-group-list-group-flush">
                    <li class="list-group-item"><b>Имя:</b> <a>${loggedInUser.getName()}</a></li>
                    <li class="list-group-item"><b>Фамилия:</b> <a>${loggedInUser.getSurname()}</a></li>
                    <li class="list-group-item"><b>День рождения:</b> <a>${loggedInUser.getDateBorn()}</a></li>
                    <li class="list-group-item"><b>ID пользователя:</b> <a>${loggedInUser.getId()}</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="container-orders">
    <div class="container-orders-title">
        <p>История заказов</p>
    </div>
    <c:if test="${not empty userOrders}">
        <c:forEach items="${userOrders}" var="userOrder">
            <div class="order">
                <div class="order-info">
                    <p>Дата заказа:<b> ${userOrder.getDate()}</b></p>
                </div>
                <div class="row order-list">
                    <c:forEach items="${userOrder.getProductList()}" var="products">
                        <div class="card-body order-product">
                            <img class="card-img" style="width:30px;height:30px"
                                 src="${contextPath}/images/${products.getImageName()}" alt="Product image"/>
                            <ul class="list-group order-product-info">
                                <li class="list-group-item"><b>Name:</b> <a>${products.getName()}</a></li>
                                <li class="list-group-item"><b>Description:</b> <a>${products.getDescription()}</a></li>
                                <li class="list-group-item"><b>Price:</b> <a>${products.getPrice()}</a></li>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
        <div class="pages">
            <c:forEach items="${page_number}" var="page">
                <a href="${contextPath}/login/profile/${page}">${page}</a>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>