<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body><center>
<body style="background-color:Gray;">
	<c:forEach var="tran" items="${list }">
		<h1><c:out value="${tran.getAccno() }"></c:out>
		<c:out value="${tran.getTypeOfTran() }"></c:out>
		<c:out value="${tran.getBalance() }"></c:out></h1>
	</c:forEach>

</body>
</center>
</html>