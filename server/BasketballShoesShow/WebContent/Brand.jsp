<%@page import="objects.Brand"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="gb2312"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@page import="database.DBOperation"%>
<%@page import="java.util.*"%>

	<jsp:useBean id="DB" class="database.DBOperation" scope="page" />
	<jsp:setProperty name="DB" property="*" />

	<%
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
		int startServerId = Integer.parseInt(request.getParameter("startServerId"));
		System.out.println("startServerId:" + startServerId);
		DB.setStartServerId(startServerId);
		ArrayList<Brand> brandList = DB.getBrands();
		System.out.println("brand number:"+brandList.size());
	%>
	<json:array name="brandList" var="brand" items="${DB.getBrands()}">
		<json:object>
		    <json:property name="serverId" value="${brand.getServerId()}" />
			<json:property name="brandName" value="${brand.getName()}" />
			<json:property name="brandPic" value="${brand.getBitmapString()}" />
		</json:object>
	</json:array>

