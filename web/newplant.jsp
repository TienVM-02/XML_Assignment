<%-- 
    Document   : newplant
    Created on : Apr 19, 2022, 1:53:40 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>CreatePlant</h1>
        <h1>${Success}</h1>
         <form>
            <input name="id" value="" placeholder="Enter id product..." /></br>
            <select name="idCategory">
                <option>Choose category</option>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
            </br>
            <label>name:   </label><input name="name" value=""/></br>
            <label>price:   </label><input name="price" value=""/></br>
            <label>description:   </label> </label><input name="description" value=""/></br>
            <label>date:   </label><input name="dateCreate" value=""/></br>
            <button name="btnAction" value="Create">Add</button>
             <a href="MainControler?btnAction=LoadProduct">Add New</a>
        </form>

    </body>
    </body>
</html>
