<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>BB-Bet Matches</title>
</head>
<body background="resources/light.gif">
<form method="POST" action='MatchController'>
	<center> 
	<h1>All the tickets from database: </h1>
	
	<table  width="60%" border=1  border="1" cellspacing="2" cellpadding="0">
      <tr>
        <td width="10%">
    		<hr align="left" />
    		<p><center><strong>Ticket Code</strong></center>
		 	<table  width="100%" border="1" cellspacing="2" cellpadding="0">
		 		<c:forEach items="${ticketCodes}" var="tck">
    				<tr>
    					<td>
    					<c:out value="${tck.code}" />
    					</td>
	    			</tr>
    			</c:forEach>		
			</table>
    	</td>
    	
    	<td width="6%">
    		<hr align="left" />
    		<p><center><strong>Match ID</strong></center>
		 	<table  border="1" width="100%" cellspacing="2" cellpadding="0">
		 		<c:forEach items="${allMatches}" var="all">
    				<tr>
    					<td>
    					<c:out value="${all.matchid}" />
    					</td>
	    			</tr>
    			</c:forEach>		
			</table>
    	</td>
    	
    	<td  width="6%">
    		<hr align="left" />
    		<p><center><strong>Share Type</strong></center>
		 	<table  width="100%" border="1" cellspacing="2" cellpadding="0">
		 		<c:forEach items="${allMatches}" var="all">
    				<tr>
    					<td >
    					<c:out value="${all.shareType}" />
    					</td>
	    			</tr>
    			</c:forEach>		
			</table>
    	</td>
    	
    	<td  width="9%">
    		<hr align="left" />
    		<p><center><strong>Share Value</strong></center>
		 	<table  width="100%" border="1" cellspacing="2" cellpadding="0">
		 		<c:forEach items="${allMatches}" var="all">
    				<tr>
    					<td >
    					<c:out value="${all.shareValue}" />
    					</td>
	    			</tr>
    			</c:forEach>		
			</table>
    	</td>
    

    </tr>
    </table>
    <p><a href="MatchController?action=listMatch">Back</a></p>
    </center>
    </form>
</body>
</html>
