<%@page import="objects.Brand"%>
<%@page import="objects.Series"%>
<%@page import="objects.Color"%>
<%@page import="objects.Shoes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="DB" class="database.DBOperation" scope="page" />
<jsp:setProperty name="DB" property="*" />
<title>编辑</title>
</head>
<body>

     <table align="center" border="0" cellspacing="2" width="810" class="table">
            
            <tr bgcolor="#00ccff">
                <td align="center" height="50"><P>品牌</P></td><td align="center" height="50"><p>系列</p></td><td align="center" height="50"><p>系列介绍</p></td><td align="center"><P>颜色</P></td><td align="center"><P>名字</P></td><td align="center"><P>图片</P></td><td align="center"><P>价格</P></td><td align="center"><P>科技</P></td><td align="center"><P>季节</P></td><td align="center"><P>鞋帮</P></td><td align="center"><P>鞋面材料</P></td><td align="center"><P>鞋底材料</P></td><td align="center"><P>功能</P></td><td align="center"><P>位置</P></td><td align="center"><P>介绍</P></td>
            </tr>
           <%
           
           
			String brandName = null;
			String seriesName = null;
			String seriesIndro = null;
			String colorName = null;
			String shoesName = null;
			String picUrl = null;
			String shoes_price = null;
			String shoes_season = null;
			String shoes_upper = null;
			String shoes_upperMaterial = null;
			String shoes_lowMaterial = null;
			String shoes_function = null;
			String shoes_oisition = null;
			String shoes_sex = null;
			String shoes_technology = null;
			String shoes_indro = null;
           
           
           
           String table = request.getParameter("table");
           String serverId = request.getParameter("serverId");
           
   		if("brand".equals(table)){
   			Brand brand = (Brand)(DB.getOneCategory(table, serverId));
   			brandName = brand.getName();
   			picUrl = "../images/"+brandName+".png";
		}else if("series".equals(table)){
			Series series = (Series)DB.getOneCategory(table, serverId);
			brandName = series.getBrandName();
			seriesName = series.getName();
			seriesIndro = series.getIndro();
			picUrl = "../images/"+brandName+"/"+seriesName+".png";
			
		}else if ("color".equals(table)) {
			Color color = (Color)DB.getOneCategory(table, serverId);
			brandName = color.getBrandName();
			seriesName = color.getSeriesName();
			colorName = color.getName();
			picUrl = "../images/"+brandName+"/"+seriesName+"/"+colorName+".png";
		}else{
			Shoes shoes = (Shoes)DB.getOneCategory(table, serverId);
			brandName = shoes.getBrand();
			seriesName = shoes.getSeries();
			colorName = shoes.getColor();
			shoesName = shoes.getName();
			picUrl = "../images/"+brandName+"/"+seriesName+"/"+colorName+"/"+shoesName+".png";
			shoes_price = shoes.getPrice()+"";
			shoes_season = shoes.getSeason();
			shoes_upper = shoes.getUpper();
			shoes_upperMaterial = shoes.getUpperMaterial();
			shoes_lowMaterial = shoes.getLowMaterial();
			shoes_function = shoes.getFunction();
			shoes_oisition = shoes.getPosition();
			shoes_technology = shoes.getTechnology();
			shoes_indro = shoes.getIndro();
		}
          
   		   
           %>
<form action='doUpload.jsp?table' method='post' enctype='multipart/form-data'>
            <tr>
                <td align="center" height="80" class="detailed">
                    <input type="text" name="brandName" value=<%=brandName%>/>
                </td>
                <td align="center">
                
                   <input type="text" name="seriesName" value=<%=seriesName%>/>
                    
                </td>
                <td align="center">
                
                  <textarea name="seriesIndro"><%=seriesIndro%></textarea>
                </td>
                <td align="center">
                    <input type="text" name="colorName" value=<%=colorName%>/>
                </td> 
                 <td align="center">
                    
                      <input type="text" name="shoesName" value=<%=shoesName%>/>
                    
                </td>
                <td align="center">
                    <img src=<%=picUrl%>>
                    

                             选择要上传的图片<input type='file' name='upfile' size='50'>
                            
                            
                </td>
                <td align="center">
                    
                      <input type="text" name="shoesPrice" value=<%=shoes_price%>/>
                    
                </td>
                <td align="center">
                    
                      <input type="text" name="shoesTechnology" value=<%=shoes_technology%>/>
                    
                </td>
                <td align="center">
                    
                      <input type="text" name="shoesSeason" value=<%=shoes_season%>/>
                    
                </td>
                <td align="center">
                    
                      <input type="text" name="shoesUpper" value=<%=shoes_upper%>/>
                    
                </td>
                <td align="center">
                    
                      <input type="text" name="shoesUpperMaterial" value=<%=shoes_upperMaterial%>/>
                    
                </td>
                <td align="center">
                    
                      <input type="text" name="shoeslowMaterial" value=<%=shoes_lowMaterial%>/>
                    
                </td>
                <td align="center">
                    
                      <input type="text" name="shoesFunction" value=<%=shoes_function%>/>
                    
                </td>
                <td align="center">
                    
                      <input type="text" name="shoesPoisition" value=<%=shoes_oisition%>/>
                    
                </td>
                 <td align="center">
                    
                    <textarea name="shoesIndro"><%=shoes_indro%></textarea>
                </td>
                <td align="center">
                <input type='submit' value='提交'>
                </td> 
            </tr>
            </form>
        </table>

</body>
</html>