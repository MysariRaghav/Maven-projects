<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/login">

    <label>UserName:</label><input type="text" name="uname" placeholer="Username.." />
    <label>Password:</label><input type="password" name="pword" placeholer="*****" />
    <input type="submit" value="Login" />
</form>
<div>
    <p>If you are a new User, <a href="/signup">Sign up</a>
    <br/>
    ${message}

</div>    


</body>
</html>