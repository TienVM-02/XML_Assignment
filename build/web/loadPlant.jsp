<%-- Document : loadPlant.jsp Created on : Apr 18, 2022, 12:15:29 PM Author : pc --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet"
      id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<script language="JavaScript" src="https://code.jquery.com/jquery-1.11.1.min.js"
type="text/javascript"></script>
<script language="JavaScript" src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"
type="text/javascript"></script>
<script language="JavaScript"
        src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.js"
type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
      href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
      href="http://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.css">

<link rel="stylesheet" type="text/css" href="./css/style.css">
<script src="./js/dataTable.js"></script>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant</title>
    </head>

    <body>
        <div class="header">
            <p class="title">Plant Management</p>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="formAction">
                        <form action="MainControler" method="POST">
                            <input class="txtSearch" name="searchValue" value="${filter}" placeholder="Search..." />
                            <select class="option" name="idCategory">
                                <c:forEach var="category" items="${categories}">
                                    <c:if test="${requestScope.idCategory eq category.id}">
                                        <option selected value="${category.id}">${category.id}</option>
                                    </c:if>
                                    <c:if test="${requestScope.idCategory != category.id}">
                                        <option value="${category.id}">${category.id}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <button class="btnSearch" name="btnAction" value="search">Search</button>
                            <button class="btnAdd" name="btnAdd" value="addnew">Add +</button>
                        </form>
                    </div>
                    <!--<a href="MainControler?btnAction=addNew">Add New</a>-->
                    <c:if test="${not empty plant}">


                        <table id="datatable" class="table table-striped table-bordered" cellspacing="0"
                               width="100%">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th style="width: 100px">Price $</th>
                                    <th>Description</th>
                                    <th>CateID</th>
                                    <th>Date Create</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="plants" items="${plant}" varStatus="counter">
                                    <tr>
                                <form action="MainControler" method="POST">
                                    <td>${counter.count}</td>
                                    <input type="hidden" name="id" value="${plants.id}" />
                                    <td>${plants.id}</td>
                                    <td><input name="name" value="${plants.name}" /></td>
                                    <td>$ <input style="width: 55px" name="price" value="${plants.price}" /></td>
                                    <td><input name="description" value="${plants.description}" /></td>
                                    <td><input name="idCate" value="${plants.cateID}" /></td>
                                    <td><input name="dateCreate" value="${plants.createDate}" /> </td>
                                    <!-- <td><a href="MainControler?btnAction=deleteProduct&id=${plants.id}">Delete</a></td> -->
                                    <!-- <td><button type="submit" name="btnAction" value="update">Update</button></td> -->
                                    <td>
                                        <p data-placement="top" data-toggle="tooltip" title="Edit">
                                            <button class="btn btn-primary btn-xs" data-title="Edit"
                                                    data-toggle="modal" data-target="#edit" type="submit" name="btnAction" value="update">
                                                <span class="glyphicon glyphicon-pencil"></span></button>
                                        </p>
                                    </td>
                                    <td>    
                                        <!--<a href="MainControler?btnAction=deletePlant&id=${plants.id}">Del</a>-->
                                        <p data-placement="top" data-toggle="tooltip" title="Delete">
                                            <button class="btn btn-danger btn-xs" data-title="Delete"
                                                    data-toggle="modal" data-target="#delete"
                                                    type="submit" name="btnAction" value="deletePlant"><span
                                                    class="glyphicon glyphicon-trash"></span></button>
                                        </p>
                                    </td>
                                </form>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty plant}">
                        <h3 style="color:red">Don't have plant</h3>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>