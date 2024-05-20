<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage = "login.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD EXPENSE</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<% HttpSession sessions = request.getSession(false);
	if(sessions==null){ %>
  	<jsp:forward page="login.html"></jsp:forward>	
  	 <% }
	int id=(int) sessions.getAttribute("userId");
	%>
<jsp:include page="logout.jsp" />
<div  class="bg-gray-100 w-full h-screen flex justify-center items-center">
<div class=" w-1/3 max-w-m  h-full flex items-center justify-center">
  <form class="w-full bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" action="AddExpenseLink">
  	<input type="hidden" name="userId" value="<%=id %>">
    <div class="w-full flex justify-center">
    <h1 class="mb-4 block text-gray-700 text-xl font-bold mb-2" >ADD EXPENSE</h1>
    </div>
  	
    <div class="mb-4">
      <label class="block text-gray-700 text-sm font-bold mb-2" for="username">
        Expense
      </label>
      <input  class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" name="expense" type="text" placeholder="5000" pattern="[0-9]*" required>
    </div>
     <div class="w-full  md:w-full  mb-6 md:mb-0">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-state">
        Category
      </label>
      <div class="relative">
        <select name="category" class="mb-4 block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-state">
          <option name="category" value="Travelling">Travelling</option>
          <option name="category" value="CableTV">CableTV</option>
          <option name="category" value="Cloths">Cloths</option>
          <option name="category" value="Education">Education</option>
          <option name="category" value="Electricity">Electricity</option>
          <option name="category" value="EMI">EMI</option>
          <option name="category" value="Entertainment">Entertainment</option>
          <option name="category" value="FastFood">FastFood</option>
          <option name="category" value="Festivals">Festivals</option>
          <option name="category" value="Fitness">Fitness</option>
          <option name="category" value="CardFee">CardFee</option>
        </select>
        <div class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
          <svg class="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
        </div>
      </div>
    </div>
    <div class="w-fulls md:w-full  mb-6 md:mb-0">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-state">
        Payment method
      </label>
      <div class="relative">
        <select name="paymentMethod"  class=" mb-4 block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-state">
          <option name="paymentMethod" value="bank">Bank</option>
          <option name="paymentMethod" value="card">Card</option>
          <option name="paymentMethod" value="cash">Cash</option>
          <option name="paymentMethod" value="other">Other</option>
        </select>
        <div class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
          <svg class="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
        </div>
      </div>
    </div>
    <div class="mb-4">
      <label class="block text-gray-700 text-sm font-bold mb-2" for="addNote">
        Add Note
      </label>
      <input  class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="addNote" type="text" name="addNote">
    </div>
    <div class="mb-4">
      <label class="block text-gray-700 text-sm font-bold mb-2" for="date">
        Date
      </label>
      <input required  class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="date" type="date" name="date">
    </div>
    <div class="mb-4">
      <label class="block text-gray-700 text-sm font-bold mb-2" for="time">
        Time
      </label>
      <input required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="time" type="time" name="time">
    </div>
    <div class="flex items-center justify-between">
      <input class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit" value="ADD">
      </input>
    </div>
  </form>
</div>
</div>
</body>
</html>