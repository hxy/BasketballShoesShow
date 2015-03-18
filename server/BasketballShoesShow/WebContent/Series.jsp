<%@page import="objects.Series"%>
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
	System.out.println("brandName:"+brandName+"\nstartServerId:" + startServerId);
	DB.setStartServerId(startServerId);
	DB.setBrandName(brandName);
	
	ArrayList<Series> seriesList = DB.getSeries();
	System.out.println("series number:" + seriesList.size());
%>
<json:array name="seriesList" var="series" items="${DB.getSeries()}">
	<json:object>
	    <json:property name="serverId" value="${series.getServerId()}" />
		<json:property name="brandName" value="${series.getBrandName()}" />
		<json:property name="seriesName" value="${series.getName()}" />
		<json:property name="seriesPic" value="${series.getBitmapString()}" />
		<json:property name="seriesIndro" value="${series.getIndro()}" />
	</json:object>
</json:array>