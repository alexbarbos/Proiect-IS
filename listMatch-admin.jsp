<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BB-Bet Matches</title>
</head>
<body background="resources/sky.gif">
<form method="POST" action='LoginServlet'>
	<center> 
	<h2>Welcome admin: <c:out value="${sessionScope.userName}"/> !!!</h2>
	<h1>Select matches</h1>
    <table border=1>
        <thead>
            <tr>
                <th>Match Id</th>
                <th>Host Teams</th>
                <th>Away Teams</th>
                <th> `1` </th>
                <th> `X` </th>
                <th> `2` </th>
                <th> `1X` </th>
                <th> `X2` </th>
                <th> `12` </th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${matches}" var="match">
                <tr>
                    <td><c:out value="${match.matchid}" /></td>
                    <td><c:out value="${match.hostTeam}" /></td>
                    <td><c:out value="${match.awayTeam}" /></td>
                    <td><c:out value="${match.share1}" /></td>
                    <td><c:out value="${match.shareX}" /></td>
                    <td><c:out value="${match.share2}" /></td>
                    <td><c:out value="${match.share1X}" /></td>
                    <td><c:out value="${match.shareX2}" /></td>
                    <td><c:out value="${match.share12}" /></td>
                    <td><a href="MatchController?action=edit&matchId=<c:out value="${match.matchid}"/>">Update</a></td>
                    <td><a href="MatchController?action=delete&matchId=<c:out value="${match.matchid}"/>">Delete</a></td>              
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="MatchController?action=insert">Add match</a></p>
	</center>
	<br>
	<a href="LoginServlet">Log Out</a>
	<center>
		<input type="submit" name="delTickets" value="Delete Tickets" onclick="this.form.action='MatchController'; method='POST' ">
		<input type="submit" name="viewTickets" value="View Tickets" onclick="this.form.action='MatchController'; method='POST' ">
		<input type="submit" name="finalScore" value="Generate Final Score" onclick="this.form.action='MatchController'; method='POST' ">
	</center>
	</form>
</body>
</html>
