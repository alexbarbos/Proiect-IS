<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Login</title>
</head>
<body background="resources/login.gif">
	<center>
	<h1> Please login HERE !</h1> 
    <form method="POST" action='LoginServlet' name="frmLogin">
        <br> 
        User Name : <input style="background-color:white" type="text" name="userName" 
        /> <br /> <br> 
        Password : <input style="background-color:white" type="password" name="password" 
        /> <br />  <br>
        <input style="background-color:white" type="submit" value="Login" />
    </form>
    </center>
</body>
</html>
