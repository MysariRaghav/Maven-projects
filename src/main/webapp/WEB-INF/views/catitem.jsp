<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="/manipulateCart" method="POST" modelAttribute="cartList">
<table>
<tr>
<th></th>
<th>name</th>
<th>price</th>
<th></th>
<th>quantity</th>
</tr>
<c:forEach items="${savedSessionCart}" var="citem">
<tr>
 <td><form:checkbox path="itemsList" name="selected" value="${citem}"/>

<td>${citem.name}</td>
<td>${citem.price}</td>
<th>*</th>
<td><input type="text"  name="taotalprice"/></td>
</tr>
</c:forEach> 
</table>
<br></br>
<input type="submit" name="save for latter" value="save for latter"/>
<input type="submit" name="delete from cart" value="delete from cart"/>

</form:form>
</body>
</html>