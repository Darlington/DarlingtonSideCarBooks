<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
<label>Add Book</label>
<form action="addBook">
<label>Year: </label><input type="text" name="year"><br>
<label>Author: </label><input type="text" name="author"><br>
<label>Category: </label><input type="text" name="category"><br>
<label>Name: </label><input type="text" name="name"><br>
<label>No. Of Pages</label><input type="text" name="numberofpages"><br>
<input type="submit">
</form>
<br>

<label>Update Book</label>
<form action="updateBook">
<label>ID: </label>
<select name="id">
<c:forEach items="${sideCarBooks}" var="sidecb">
        <option value="${sidecb.id}">${sidecb.id}</option>
    </c:forEach>
</select>
<br>
<label>Year: </label><input type="text" name="year"><br>
<label>Author: </label><input type="text" name="author"><br>
<label>Category: </label><input type="text" name="category"><br>
<label>Name: </label><input type="text" name="name"><br>
<label>No. Of Pages</label><input type="text" name="numberofpages"><br>
<input type="submit">
</form>
<br>
------------------------------------------------------------------------

<table>
<thead>
<th>Year</th>
<th>Author</th>
<th>Category</th>
<th>Name</th>
<th>No. Of Pages</th>
<th>Delete</th>
</thead>
<tbody>
    <c:forEach items="${sideCarBooks}" var="sidecb">
        <tr>
            <td><c:out value="${sidecb.year}"/></td>
            <td><c:out value="${sidecb.author}"/></td>  
            <td><c:out value="${sidecb.category}"/></td>  
            <td><c:out value="${sidecb.name}"/></td>  
            <td><c:out value="${sidecb.numberofpages}"/></td> 
            <td><a href="deleteBook?id=${sidecb.id}">x</a></td>  
        </tr>
    </c:forEach>
</tbody>
</table>
</body>
</html>