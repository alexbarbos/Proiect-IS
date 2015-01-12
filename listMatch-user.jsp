<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<script>
		document.getElementById('idBet').onkeydown = function(e) 
		{
		    var key = e.keyCode ? e.keyCode : e.which;
	    	if ( isNaN( String.fromCharCode(key) ) ) return false;
		}
		function isNumber(evt) {
		    evt = (evt) ? evt : window.event;
		    var charCode = (evt.which) ? evt.which : evt.keyCode;
		    if (charCode > 31 && charCode != 46 && (charCode < 48 || charCode > 57)) {
		        return false;
		    }
		    return true;
		}
	</script>
<title>BB-Bet Matches</title>
</head>
<body background="resources/orange.gif">
<form method="POST" action='LoginServlet'>
	<center> 
	<h2>Welcome user: <c:out value="${sessionScope.userName}"/> !!!</h2>
	<h1>Select matches</h1>
    <table border=1 bgcolor="magenta">
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
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${matches}" var="match">
                <tr>
                    <td><c:out value="${match.matchid}" /></td>
                    <td><c:out value="${match.hostTeam}" /></td>
                    <td><c:out value="${match.awayTeam}" /></td>
                    <td><a href="MatchController?action=add&matchId=<c:out value="${match.matchid}"/>&share1=<c:out value="${match.share1}"/>&shareX=0&share2=0&share1X=0&shareX2=0&share12=0"> <c:out value="${match.share1}"/> </a></td>
                    <td><a href="MatchController?action=add&matchId=<c:out value="${match.matchid}"/>&shareX=<c:out value="${match.shareX}"/>&share1=0&share2=0&share1X=0&shareX2=0&share12=0"> <c:out value="${match.shareX}"/> </a></td>
                    <td><a href="MatchController?action=add&matchId=<c:out value="${match.matchid}"/>&share2=<c:out value="${match.share2}"/>&share1=0&shareX=0&share1X=0&shareX2=0&share12=0"> <c:out value="${match.share2}"/> </a></td>
                    <td><a href="MatchController?action=add&matchId=<c:out value="${match.matchid}"/>&share1X=<c:out value="${match.share1X}"/>&share1=0&shareX=0&share2=0&shareX2=0&share12=0"> <c:out value="${match.share1X}"/> </a></td>
                    <td><a href="MatchController?action=add&matchId=<c:out value="${match.matchid}"/>&shareX2=<c:out value="${match.shareX2}"/>&share1=0&shareX=0&share2=0&share1X=0&share12=0"> <c:out value="${match.shareX2}"/> </a></td>
                    <td><a href="MatchController?action=add&matchId=<c:out value="${match.matchid}"/>&share12=<c:out value="${match.share12}"/>&share1=0&shareX=0&share2=0&share1X=0&shareX2=0"> <c:out value="${match.share12}"/> </a></td>                   
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</center>

	<center>
	<h1> Your selected matches </h1>
	<br>
	<table border=1 bgcolor="yellow">
        <thead>
            <tr>
                <th>Match Id</th>
                <th>Host Teams</th>
                <th>Away Teams</th>
                <th> `Type` </th>
                <th> `Share` </th>
                <th> X </th>
            </tr>
        </thead>
       
        <tbody>
        	<c:forEach items="${ticket}" var="tick">
                <tr>
                    <td><c:out value="${tick.matchid}" /></td>
                    <td><c:out value="${tick.hostTeam}" /></td>
                    <td><c:out value="${tick.awayTeam}" /></td>
                    <td><c:out value="${tick.shareType}" /></td>
                    <td><c:out value="${tick.shareValue}" /></td>
                    <td> <a href="MatchController?action=deleteMatch&matchId=<c:out value="${tick.matchid}" /> "> <img src="resources/delete.gif" width="30"> </a> </td>
                </tr>
            </c:forEach>
         	<tr>   
         		<td colspan=6 >
         			<font face="Arial" size="4" color="blue">Final Share =  <c:out value="${finalShare}" />
         			</font> 
         		</td>		
         	</tr>
         	<tr>
         		<td colspan=4 >
         			<font face="Arial" size="4" color="red">BET (VAT included) : <input onkeypress="return isNumber(event)" type="text" name="price" id="idBet" value="<c:out value="${bet}"/>"/> <br />
         			</font>
         		</td> 
         		<td colspan=1 >	
         			<font face="Arial" size="3" color="blue">BET =  <c:out value="${bet}" />
         			</font> 
         		</td>
         		<td colspan=1 >	
         			<input type="submit" name="Ok" value="Ok" onclick="this.form.action='MatchController'; method='POST' ">
         		</td>
         	</tr>
         	<tr>
         		<td colspan=6 >
         			<font face="Arial" size="4" color="red"><label>You win: <c:out value="${gainValue}" /> </label> <br />
         			</font>
         		</td> 
         	</tr>
        </tbody>
    </table>
	</center>
	<br>
	<center>
    	<input type="submit" name="Print" value="Print" onclick="this.form.action='MatchController'; method='POST' ">
    </center>
    	<br>
	<a href="LoginServlet"><font color="FFFFFF">Log Out </font></a>     
</form>
</body>
</html>
