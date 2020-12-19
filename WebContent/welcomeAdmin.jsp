<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=UTF-8">
		<title>Scofield - Login</title>
	</head>
	<body>
	 	<%
           if (session.getAttribute("accountInfor") == null) {
        	   response.sendRedirect("login.jsp?error=1");
           }
       	%>
	
		<div style="background-color: yellow; width: 100px"><a href="ShowProductListServlet">Hàng hóa</a></div>
		<div><%=(String)session.getAttribute("accountInfor")%></div>
		<div style="text-color: blue; width: 100px"><a href="LogoutServlet">Logout</a></div>
	</body>
</html>