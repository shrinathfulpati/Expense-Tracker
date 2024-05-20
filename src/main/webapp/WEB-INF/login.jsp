<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex justify-evenly items-center h-screen">
	<div class="mb-8">
	<h1 class="text-6xl font-bold text-blue-700"> EXPENSE TRACKER </h1>
	<p class="text-blue-600 mt-4">Track Income | Expense | Balance</p>
	</div>
    <div class="w-1/3">
    <form class="bg-white shadow-md rounded px-12 w-16 pt-10 pb-10 mb-4 w-full" action="loginLink" method="post">
    <div class="mb-6">
	<h1 class="block text-gray-700 text-3xl font-bold mb-2">SIGN IN</h1>
	</div>
    <div class="mb-4">
      <label class="block text-gray-700 text-m font-bold mb-2" for="username">
        Email / Mobile
      </label>
      <input name="mobile" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder="9561312332">
    </div>
    <div class="mb-6">
      <label class="block text-gray-700 text-m font-bold mb-2" for="password">
        Password
      </label>
      <input name="password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="password" type="text" placeholder="******************">
    </div>
    <div class="flex items-center justify-between">
      <input type="submit" value="Login" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-8 rounded focus:outline-none focus:shadow-outline" >
       
      <a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800" href="#">
        Forgot Password?
      </a>
    </div>
  </form>
  <p class="text-center text-blue-500 text-s">
    Don't have an Account? <a class="text-blue-900 " href="register.html">Register</a>
  </p>
    </div>
</body>
</html>