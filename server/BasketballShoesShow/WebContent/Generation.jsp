<%@page import="objects.Generation"%>
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
	DB.setBrandName(brandName);
	DB.setSeriesName(seriesName);
	System.out.println("brandName:"+brandName+"\nseriesName:"+seriesName+"\nstartServerId:" + startServerId);
	
	DB.setStartServerId(startServerId);
	ArrayList<Generation> generationList = DB.getGenerations();
	System.out.println("generation number:" + generationList.size());
%>
<json:array name="generationList" var="generation" items="${DB.getGenerations()}">
	<json:object>
	    <json:property name="serverId" value="${generation.getServerId()}" />
		<json:property name="brandName" value="${generation.getBrandName()}" />
		<json:property name="seriesName" value="${generation.getSeriesName()}" />
		<json:property name="generationName" value="${generation.getName()}" />
		<json:property name="generationPic" value="${generation.getBitmapString()}" />
	</json:object>
</json:array>