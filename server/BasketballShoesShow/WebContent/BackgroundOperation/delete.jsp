<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="DB" class="database.DBOperation" scope="page" />
<jsp:setProperty name="DB" property="*" />
<title>Insert title here</title>
</head>
<body>
	<%
		String table = request.getParameter("table");
		String serverId = request.getParameter("serverId");
		
		DB.delete(table, serverId);
		
		response.sendRedirect(table+".jsp");
	%>
</body>
</html>