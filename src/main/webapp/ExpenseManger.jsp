<%@page import="Model.Transaction"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage = "login.html" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Expense Tracker</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>    
    <% HttpSession sessions = request.getSession(false);
	if(sessions==null||sessions.getAttribute("userId")==null){ %>
  	<jsp:forward page="login.html"></jsp:forward>	
  	 <% }
	int id=(int) sessions.getAttribute("userId");
	%>
	<jsp:include page="logout.jsp" />
	<div  class="bg-gray-100  h-900 flex flex-column items-center justify-start" >
    <div class=" h-screen flex flex-col items-center justify-start gap-8 max-w-xs m-auto mt-1 ">
        <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 mt-12">
            <div class="flex items-center justify-between items-center">
                <a href="AddIncome.jsp">
                    <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mr-5" type="button">
                        ADD INCOME
                    </button>
                </a>
                <br>
                <a href="AddExpense.jsp">
                    <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">
                        ADD EXPENSE
                    </button>
                </a>
            </div>
            <br>
            <div class="w-full flex items-center justify-between">
                <a class="m-auto" href="today">
                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">
                        TRANSACTIONS
                    </button>
                </a>
            </div>
        </div>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
    <% %>
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" colspan="4" class="px-6 py-3">
                    <form action="displayData" method="post">
                    	<input class="p-2" type="date" name="startpoint" required/>
                    	<input class="p-2" type="date" name="endpoint" required/>
                    	<input class="p-2 ml-20" type="submit" value="submit"/>
                    </form>
                </th>
                
            </tr>
        </thead>
        <tbody>
        	<%Transaction t=(Transaction) request.getAttribute("Data");
        	 double income=0,expense=0,balance=0;
        		if(t!=null){
        			income=t.getTotalIncome();
        			expense=t.getTotalExpense();
        			balance=t.getBalance();
        		}
        	%>
        	<tr colspan="2" class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th  scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                   <%if(t!=null){%><%=t.getStartpoint()%><%} %> 
                </th>	
                <td scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    <%if(t!=null){%><%=t.getEndpoint() %><%} %>
                </td>
            </tr>
            <tr colspan="2" class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th  scope="row" class="px-6 py-4 font-medium text-green-500 whitespace-nowrap dark:text-white">
                    INCOME
                </th>	
                <td class="px-6 py-4 text-green-500">
                    <%if(t!=null){%><%=t.getTotalIncome() %><%} %>
                </td>
            </tr>
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th scope="row" class="px-6 py-4 font-medium text-red-500 whitespace-nowrap dark:text-white">
                    EXPENSE
                </th>
                <td class="px-6 py-4 text-red-500">
                    <%if(t!=null){%><%=t.getTotalExpense() %><%} %>
                </td>
            </tr>
            <tr class="bg-white dark:bg-gray-800 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    BALANCE
                </th>
                <td class="px-6 py-4">
                    <%if(t!=null){%><%=t.getBalance() %><%} %>
                </td>
            </tr>
        </tbody>
    </table>
</div>    
</div>
</div>
</body>
</html>

