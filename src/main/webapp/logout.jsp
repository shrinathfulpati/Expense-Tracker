<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>logout</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<nav class="bg-gray-800">
  <div class="mx-auto max-w-7xl px-2 sm:px-6">
    <div class="relative flex h-16 items-center justify-between">
    	<h1 Class="text-white text-xl font-bold">EXPENSE TRACKER</h1>
    	<div class=" flex h-16 items-center justify-between">
    	
    		<div class="flex space-x-4 mr-8 ">
            <a href="displayData" class="hover:no-underline text-gray-100 hover:bg-gray-600 hover:text-white rounded-md px-3 py-2 text-m font-medium ">HOME</a>
         	</div>
         	<div class="flex space-x-4">
            <a href="logoutLink" class=" hover:no-underline text-gray-100 hover:bg-gray-600 hover:text-white rounded-md px-3 py-2 text-m font-medium no-underline">LOGOUT <i class="fa-solid fa-right-from-bracket"></i></a>
        </div>
    	</div>
    	
    	
    </div>
  </div>
</nav>
</body>
</html>