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
 <form:form action="${pageContext.request.contextPath}/mycart" method="post" modelAttribute="listOfItems">
<table>
     <caption>${name} items are here</caption>
     <thead>
         <tr>
            <th>select</th>
            <th>Name</th>
            <th>Description</th>
            <th>price</th>
            </tr>
            </thead>

      <tbody>
      
     
       <c:forEach items="${list}" var="cartitem" varStatus="counter">
          <tr>
         <%--  <jsp:useBean id="product" type="com.ecommerce.service.cart" class="com.ecommerce.service.cart"/>
          <jsp:setProperty property="pid" name="product" value="${cartitem.pid}"/> --%>
             <td><form:checkbox path="itemsList" name="getitem" value="${cartitem}"/>
             </td>
             <td>${cartitem.name}</td>
              <td>${cartitem.desc}</td>
             <td>${cartitem.price}</td> 
             
          </tr>   
       </c:forEach>      
     </tbody>
</table>
<input type="submit" value="addTo cart"/>
</form:form>
</body>
</html>