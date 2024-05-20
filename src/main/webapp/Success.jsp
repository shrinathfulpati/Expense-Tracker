<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% int count=(int) request.getAttribute("count");%>
<h1><%= count %> DATA ADDED SUCCESSFULLY</h1>
<h3><a href="ExpensiveManager.jsp">Home</a></h3>
</body>
</html>