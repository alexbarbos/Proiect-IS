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
<title>BB-BET Add match</title>
</head>
<body background="resources/light.gif">
	<center>
	<h1> Add/Update match HERE!</h1> 
    <form method="POST" action='MatchController' name="frmAddMatch">
        <br> 
        <input type="hidden" readonly="readonly" name="matchid"
            value="<c:out value="${match.matchid}" />" /> <br /> <br>
        Host Team : <input
            type="text" name="hostTeam"
            value="<c:out value="${match.hostTeam}" />" /> <br /> <br> 
        Away Team : <input
            type="text" name="awayTeam"
            value="<c:out value="${match.awayTeam}" />" /> <br />  <br>
        `1` : <input
            type="text" name="share1"
            value="<c:out value="${match.share1}" />" /> <br />  <br>
        `X` : <input
            type="text" name="shareX"
            value="<c:out value="${match.shareX}" />" /> <br />  <br>
        `2` : <input
            type="text" name="share2"
            value="<c:out value="${match.share2}" />" /> <br />  <br>
        `1X` : <input
            type="text" name="share1X"
            value="<c:out value="${match.share1X}" />" /> <br />  <br>
        `X2` : <input
            type="text" name="shareX2"
            value="<c:out value="${match.shareX2}" />" /> <br />  <br>
        `12` : <input
            type="text" name="share12"
            value="<c:out value="${match.share12}" />" /> <br />  <br>
        <input type="submit" value="Submit" />
        <h2>I don't want to make any changes !! <a href="indexMatch-admin.jsp"> Back </a> </h2>
    </form>
    </center>
</body>
</html>
