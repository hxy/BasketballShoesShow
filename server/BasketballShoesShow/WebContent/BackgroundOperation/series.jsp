<%@page import="com.sun.corba.se.impl.interceptors.PICurrent"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="objects.Series"%>
<%@page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="DB" class="database.DBOperation" scope="page" />
<jsp:setProperty name="DB" property="*" />
<title>系列</title>
</head>
<body>


       <table align="center" border="0" cellspacing="2" width="810" class="table">
            
            <tr bgcolor="#00ccff">
                <td align="center" height="50"><P>品牌</P></td><td align="center" height="50"><p>系列</p></td><td align="center"><P>图片</P></td><td align="center"><P>介绍</P></td><td align="center"><P></P></td><td align="center"></td>
            </tr>
           <%
           ArrayList<Series> v = new ArrayList<Series>();
           String brandName = request.getParameter("brandName");
           DB.setBrandName(brandName);

                 v = DB.getAllSeries();
             
             
             for(int n=0;n<v.size();n++){
            	 Series series = v.get(n);
            	 
            	 try{
            		 File file = new File(application.getRealPath("/")+"images/"+brandName+"/"+series.getName()+".png");
//            		 if(!file.exists()){
  //          			 System.out.println(file.getAbsolutePath());
    //        			 
      //      			 System.out.println(file.createNewFile());
        //    		 }
                     FileOutputStream outPut = new FileOutputStream(file);
                     outPut.write(series.getBitmapBytes());
                     outPut.flush();
                     outPut.close(); 
            	 }catch(Exception e){
            		 e.printStackTrace();
            	 }
 
           %>

            <tr>
                <td align="center" height="80" class="detailed">
                    
                    <a href="color.jsp?brandName=<%=series.getBrandName()%>&seriesName=<%=series.getName()%>>" target="_blank" >
                        <%out.println(series.getBrandName());%>
                    </a>
                </td>
                <td align="center">
                <a href="color.jsp?brandName=<%=series.getBrandName()%>&seriesName=<%=series.getName()%>>" target="_blank" >
                    <%out.println(series.getName());%>
                    </a>
                </td>
                <td align="center">
                    <a href="color.jsp?brandName=<%=series.getBrandName()%>&seriesName=<%=series.getName()%>" target="_blank" ><img src="../images/<%=series.getBrandName()%>/<%=series.getName()%>.png" ></a>
                </td> 
                 <td align="center">
                    <a href="color.jsp?brandName=<%=series.getBrandName()%>&seriesName=<%=series.getName()%>>" target="_blank" >
                      <%out.println(series.getIndro());%>
                    </a>
                </td>
                <td align="center">
                    <% %>
                </td>
                <td align="center">
                <button onclick="window.location.href='delete.jsp?table=brand&serverId=<%=series.getServerId()%>'" class="btn btn-info">删除</button>
                </td> 
            </tr>
            <% 
             } %>
             
        </table>
</body>
</html>