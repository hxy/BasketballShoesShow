<%@page contentType="text/html"%>
<%@page pageEncoding="utf8"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@page import="database.DBOperation"%>
<%@page import="java.util.*"%>

<jsp:useBean id="DB" class="database.DBOperation" scope="page" />
<jsp:setProperty name="DB" property="*" />

<% %>
<%
	request.setCharacterEncoding("utf8");
	response.setCharacterEncoding("utf8");

	String brandName = request.getParameter("brandName");
	String seriesName = request.getParameter("seriesName");
	String colorName = request.getParameter("colorName");
	String shoesName = request.getParameter("shoesName");
	System.out.println("brandName:"+brandName+"\nseriesName:"+seriesName+"\ncolorName:"+colorName+"\nshoesName:"+shoesName);
	
	DB.setBrandName(brandName);
	DB.setSeriesName(seriesName);
	DB.setColorName(colorName);
	DB.setshoesName(shoesName);
%>
<json:object>
	<json:property name="category" value="${DB.checkInfos()}" />
</json:object>