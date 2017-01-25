	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
To see all the electronics products <br/>
<br/>

<a href="${pageContext.request.contextPath}/shoping-iteams?name=electronics" >Click Here</a><br></br>
To see all the book products <br/>
<a href="${pageContext.request.contextPath}/shoping-iteams?name=books" >Click Here</a>
<!-- if you want to pass two are more links to one controller then pass it with in href as
parameters as above and use on parameters at the @Requestparam side.as above.

one more like inputtype tags only can send the name as separate attribute
other tages we have to pass parameter with href as shown above using "?"   -->

</body>
</html>