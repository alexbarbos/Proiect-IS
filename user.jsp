<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Registration</title>
</head>
<body>
	<center>
	<h1> Please register HERE !</h1> 
    <form method="POST" action='UserController' name="frmAddUser">
        <br> 
        User Name : <input
            type="text" name="userName"
            value="<c:out value="${user.userName}" />" /> <br /> <br> 
        Password : <input
            type="password" name="password"
            value="<c:out value="${user.password}" />" /> <br />  <br>
        <input type="submit" value="Submit" />
        <h2>Already registered!! <a href="index.jsp">Login Here</a> </h2>
    </form>
    </center>
</body>
</html>
