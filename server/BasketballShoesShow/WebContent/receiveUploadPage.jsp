<%@page import="objects.Shoes"%>
<%@page import="objects.Color"%>
<%@page import="objects.Series"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="objects.Brand"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="atg.taglib.json.util.JSONObject"%>
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
	
	JSONObject returnJsonObject = new JSONObject();
	returnJsonObject.put("resultString", "OK");
	
	StringBuffer json = new StringBuffer();
	String line = null;
	try {
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			json.append(line);
		}
		
		System.out.println(json.toString());
		JSONObject jsonObject = new JSONObject(json.toString());
		String brandName = jsonObject.getString("brandName");
		System.out.println(brandName);
		String seriesName = jsonObject.getString("seriesName");
		System.out.println(seriesName);
		String seriesIndro = jsonObject.optString("seriesIndro",null);
		System.out.println(seriesIndro);
		String colorName = jsonObject.getString("colorName");
		System.out.println(colorName);
		String shoesName = jsonObject.getString("shoesName");
		System.out.println(shoesName);
		int price = jsonObject.getInt("price");
		System.out.println(price);
		String season = jsonObject.getString("season");
		System.out.println(season);
		String upper = jsonObject.getString("upper");
		System.out.println(upper);
		String upperMaterial = jsonObject.getString("upperMaterial");
		System.out.println(upperMaterial);
		String lowMaterial = jsonObject.getString("lowMaterial");
		System.out.println(lowMaterial);
		String function = jsonObject.getString("function");
		System.out.println(function);
		String position = jsonObject.getString("position");
		System.out.println(position);
		String technology = jsonObject.getString("technology");
		System.out.println(technology);
		String indro = jsonObject.getString("indro");
		System.out.println(indro);
		String brandBitmapString = jsonObject.optString("brandBitmapBytes",null);
		//if(null != brandBitmapString){
			//System.out.println(brandBitmapString.charAt(0));
		//}else{
		
			System.out.println("brandBitmapString: "+brandBitmapString);
		//}
		
		String seriesBitmapString = jsonObject.optString("seriesBitmapBytes",null);
		//if(null != brandBitmapString){
			//System.out.println(seriesBitmapString.charAt(0));
		//}else{
			System.out.println("seriesBitmapString: "+seriesBitmapString);
		//}
		
		String colorBitmapString = jsonObject.optString("colorBitmapBytes",null);
		//if(null != brandBitmapString){
			//System.out.println(colorBitmapString.charAt(0));
		//}else{
			System.out.println("colorBitmapString: "+colorBitmapString);
		//}
		
		String shoesBitmapString = jsonObject.optString("shoesBitmapBytes",null);
		//if(null != brandBitmapString){
			//System.out.println(shoesBitmapString.charAt(0));
		//}else{
			System.out.println("shoesBitmapString: "+shoesBitmapString);
		//}
		
		
		if(null != brandBitmapString){
			byte[] bytes = Base64.decodeBase64(brandBitmapString);
			Brand brand = new Brand(brandName,bytes);
			DB.insertBrand(brand);
		}
		if(null != seriesBitmapString){
			byte[] bytes = Base64.decodeBase64(seriesBitmapString);
			Series series = new Series(brandName,seriesName,bytes,seriesIndro);
			DB.insertSeries(series);
		}
		if(null != colorBitmapString){
			byte[] bytes = Base64.decodeBase64(colorBitmapString);
			Color color = new Color(brandName,seriesName,colorName,bytes);
			DB.insertColor(color);
		}
		if(null != shoesBitmapString){
			byte[] bytes = Base64.decodeBase64(shoesBitmapString);
			Shoes shoes = new Shoes(brandName,seriesName,colorName,shoesName,bytes,price,season,upper,upperMaterial,lowMaterial,function,position,null,technology,indro);
			DB.insertShoes(shoes);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		returnJsonObject.remove("resultString");
		returnJsonObject.put("resultString", "ERROR");
	}
	
	out.print(returnJsonObject.toString());
	
	
	
	
%>

<!--<json:object>
	<json:property name="category" value="OK" />
</json:object>-->