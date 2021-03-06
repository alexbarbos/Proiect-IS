<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Users</title>
</head>
<body>
	<hr color="#FFA500" />
	<center> 
	<h1><font color="#3366cc"> Hi there ! Please 'Sing IN' to access the page !</font></h1>
    <table border=1 bgcolor="yellow">
        <thead>
            <tr>
                <th>User Id</th>
                <th>User Name</th>
                <th>Password</th>
                <th>Rol</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.userid}" /></td>
                    <td><c:out value="${user.userName}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.rol}" /></td>
                    <td><a href="UserController?action=edit&userId=<c:out value="${user.userid}"/>">Update</a></td>
                    <td><a href="UserController?action=delete&userId=<c:out value="${user.userid}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="UserController?action=insert">Sign UP</a></p>
    <p><a href="UserController?action=login">Login</a></p>
	<hr color="#FF00FF" />
	</center>
</body>
</html>
