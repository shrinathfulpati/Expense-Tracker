<%@page import="Model.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.TransactionDTOImplementation"%>
<%@page import="Model.TransactionDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body style="background:#F3F4F6" >
<% HttpSession sessions = request.getSession(false);
	if(sessions.getAttribute("userId")==null){ %>
  	<jsp:forward page="login.html"></jsp:forward>	
  	 <% }
	int id=(int) sessions.getAttribute("userId");
	%>
<jsp:include page="logout.jsp" />

<%	ArrayList<Transaction> transactionsList=(ArrayList<Transaction>) request.getAttribute("transactionsList");
	Transaction lst= new Transaction();
	if(!transactionsList.isEmpty()){ 
		lst=transactionsList.get(transactionsList.size()-1);
	}
%>
<div class="h-20 flex justify-start items-center px-8 gap-14">

	<a href="transactions">
	<button class="outline outline-black hover:bg-blue-400 hover:text-white font-bold py-2 px-4 rounded-3xl focus:outline-dashed focus:shadow-outline" type="button">
          All
    </button>
	</a>
	
	<a href="today">
	<button class="outline outline-black hover:bg-blue-400 hover:text-white font-bold py-2 px-4 rounded-3xl focus:outline-dashed focus:shadow-outline" type="button">
          Daily
    </button>
	</a>
	
	<a href="week">
	<button class="outline outline-black hover:bg-blue-400 hover:text-white font-bold py-2 px-4 rounded-3xl focus:outline-dashed focus:shadow-outline" type="button">
          Weekly
    </button>
	</a>
	
	<a href="month">
	<button class="outline outline-black hover:bg-blue-400 hover:text-white font-bold py-2 px-4 rounded-3xl focus:outline-dashed  focus:shadow-outline " type="button">
          Monthly
    </button>
	</a>
	
</div>
<table class="table" style="width:98%; margin:auto; background:	white; ">
  <thead>
    <tr style="background:#B0C4DE">
      <th scope="col">DATE</th>
      <th scope="col">CATEGORY</th>
      <th scope="col">INCOME</th>
      <th scope="col">EXPENSE</th>
    </tr>
  </thead>
  <tbody>
  	<%for(Transaction t:transactionsList) {%>
    <tr>
      <td><%= t.getDate() %></td>
      <td><%= t.getCategory() %></td>
      <td><%= t.getIncome() %></td>
      <td><%= t.getExpense() %></td>
    </tr>
    <%}
  	
  	%>
  </tbody>
  <tfoot style="background:#B0C4DE">
  	<tr >
  	 <th scope="row" colspan="2" style="color:green">TOTAL INCOME</th>
  	 <th scope="row" colspan="2" style="color:green"><%= lst.getTotalIncome() %></th>
  	</tr>
  	<tr>
  	 <th scope="row" colspan="2" style="color:red" >TOTAL EXPENSE</th>
  	 <th scope="row" colspan="2" style="color:red" ><%= lst.getTotalExpense() %></th>
  	</tr>
  	<tr>
  	 <th scope="row" colspan="2" >BALANCE</th>
  	 <th scope="row" colspan="2"><%= lst.getBalance() %></th>
  	</tr>
  </tfoot>	
</table>	
</body>
</html>