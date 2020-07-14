<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<style>
div{
text-align:center;
}
</style>
<body style="background-color:Gray;">
<div>
<a href="Deposit.jsp"><button><h2>Deposit</h2></button></a><br></br>
<a href="withdraw.jsp"><button><h2>Withdraw</h2></button></a><br></br>
<a href="Transfer.jsp"><button><h2>Transfer</h2></button></a><br></br>
<a href="AccountBalance.jsp"><button><h2>AccountBalance</h2></button></a><br></br>
<form action="CustomerServlet">
				<button type="submit" name="btn" value="transaction_detailes"><h2>Transaction Details</h2></button><br>
				</form><br>
				

<a href="ChangePassword.jsp"><button><h2>ChangePassword</h2></button></a><br></br>

</div>
</body>


</html>