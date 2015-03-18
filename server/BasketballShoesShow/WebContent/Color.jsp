<%@page import="objects.Color"%>
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
	int startServerId = Integer.parseInt(request
			.getParameter("startServerId"));
	String brandName = request.getParameter("brandName");
	String seriesName = request.getParameter("seriesName");
	String generationName = request.getParameter("generationName");
	System.out.println("brandName:"+brandName+"\nseriesName:"+seriesName+"\ngenerationName:"+generationName+"\nstartposition:" + startServerId);
	DB.setStartServerId(startServerId);
	DB.setBrandName(brandName);
	DB.setSeriesName(seriesName);
	DB.setGenerationName(generationName);
	ArrayList<Color> colorList = DB.getColors();
	System.out.println("color number:" + colorList.size());
%>
<json:array name="colorList" var="color" items="${DB.getColors()}">
	<json:object>
	    <json:property name="serverId" value="${color.getServerId()}" />
		<json:property name="brandName" value="${color.getBrandName()}" />
		<json:property name="seriesName" value="${color.getSeriesName()}" />
		<json:property name="generationName" value="${color.getGeneration()}" />
		<json:property name="colorName" value="${color.getName()}" />
		<json:property name="colorPic" value="${color.getBitmapString()}" />
	</json:object>
</json:array>