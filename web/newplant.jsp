<%-- 
    Document   : newplant
    Created on : Apr 23, 2022, 11:49:31 PM
    Author     : vomin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Plant</title>
</head>

<body>
    <div class="header">
        <p class="title">Plant Management</p>
    </div>
    <form class="addForm" action="MainControler" method="POST">
        <h2>Add plant</h2>
        <h3 style="color: green">${Success}</h3>
        <label>Plant ID: </label>
        <input class="inputInfo" type="text" name="id" value="" placeholder="ID plant..." />
        <label>Category ID: </label>
        <select class="inputInfo" name="idCategory">
            <option>Choose category</option>
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        </br>
        <label>Name: </label>
        <input class="inputInfo" type="text" name="name" value="" placeholder="Plant name..." /></br>
        <label>Price: </label>
        <input class="inputInfo" type="text" name="price" value="" placeholder="Price..." /></br>
        <label>Description: </label>
        <input class="inputInfo" type="text" name="description" value="" placeholder="Description..." /></br>
        <button class="addBtn" name="btnAction" value="Create">Add</button>
        <a style="margin-top: 25px; font-size: 18px;" href="loadPlant.jsp">Back to plant list.</a>
    </form>

</body>

</html>
