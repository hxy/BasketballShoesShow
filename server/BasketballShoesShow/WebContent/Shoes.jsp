<%@page import="objects.Shoes"%>
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
	String colorName = request.getParameter("colorName");
	DB.setBrandName(brandName);
	DB.setSeriesName(seriesName);
	DB.setGenerationName(generationName);
	DB.setColorName(colorName);
	System.out.println("brandName:"+brandName+"\nseriesName:"+seriesName+"\ngenerationName:"+generationName+"\ncolorName:"+colorName+"\nstartposition:" + startServerId);
	DB.setStartServerId(startServerId);
	ArrayList<Shoes> shoesList = DB.getShoes();
	System.out.println("shoes number:" + shoesList.size());
%>
<json:array name="shoesList" var="shoes" items="${DB.getShoes()}">
	<json:object>
	    <json:property name="serverId" value="${color.getServerId()}" />
		<json:property name="brandName" value="${shoes.getBrand()}" />
		<json:property name="seriesName" value="${shoes.getSeries()}" />
		<json:property name="generationName" value="${shoes.getGeneration()}" />
		<json:property name="colorName" value="${shoes.getColor()}" />
		<json:property name="shoesName" value="${shoes.getName()}" />
		<json:property name="shoesPic" value="${shoes.getBitmapString()}" />
		<json:property name="shoesPrice" value="${shoes.getPrice()}" />
		<json:property name="shoesSeason" value="${shoes.getSeason()}" />
		<json:property name="shoesUpper" value="${shoes.getUpper()}" />
		<json:property name="shoesUpperMaterial" value="${shoes.getUpperMaterial()}" />
		<json:property name="shoesLowMaterial" value="${shoes.getLowMaterial()}" />
		<json:property name="shoesFunction" value="${shoes.getFunction()}" />
		<json:property name="shoesPosition" value="${shoes.getPosition()}" />
		<json:property name="shoesSex" value="${shoes.getSex()}" />
		<json:property name="shoesTechnology" value="${shoes.getTechnology()}" />
		<json:property name="shoesIndro" value="${shoes.getIndro()}" />
		
	</json:object>
</json:array>