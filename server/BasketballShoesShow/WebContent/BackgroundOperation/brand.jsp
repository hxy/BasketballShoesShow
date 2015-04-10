<%@page import="com.sun.corba.se.impl.interceptors.PICurrent"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="objects.Brand"%>
<%@page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="DB" class="database.DBOperation" scope="page" />
<jsp:setProperty name="DB" property="*" />
<title>品牌</title>
</head>
<body>


       <table align="center" border="0" cellspacing="2" width="810" class="table">
            
            <tr bgcolor="#00ccff">
                <td align="center" height="50"><P>品牌</P></td><td align="center" height="50"><p>图片</p></td><td align="center"><P></P></td><td align="center"><P></P></td><td align="center"><P></P></td><td align="center"></td>
            </tr>
           <%
           ArrayList<Brand> v = new ArrayList<Brand>();


                 v = DB.getAllBrands();
             
             
             for(int n=0;n<v.size();n++){
            	 Brand brand = v.get(n);
            	 
            	 try{
            		 File file = new File(application.getRealPath("/")+"images/"+brand.getName()+".png");
//            		 if(!file.exists()){
  //          			 System.out.println(file.getAbsolutePath());
    //        			 
      //      			 System.out.println(file.createNewFile());
        //    		 }
                     FileOutputStream outPut = new FileOutputStream(file);
                     outPut.write(brand.getBitmapBytes());
                     outPut.flush();
                     outPut.close(); 
            	 }catch(Exception e){
            		 e.printStackTrace();
            	 }
 
           %>

            <tr>
                <td align="center" height="80" class="detailed">
                    
                    <a href="series.jsp?brandName=<%=brand.getName()%>" target="_blank" >
                        <%out.println(brand.getName());%>
                    </a>
                </td>
                <td align="center">
                    <a href="series.jsp?brandName=<%=brand.getName()%>" target="_blank" ><img src="../images/<%=brand.getName()%>.png" ></a>
                </td>
                <td align="center">
                    <%%>
                </td> 
                 <td align="center">
                    <%%>
                </td>
                <td align="center">
                    <button onclick="window.location.href='edit.jsp?table=brand&serverId=<%=brand.getServerId()%>'" class="btn btn-info">编辑</button>
                </td>
                <td align="center">
                <button onclick="window.location.href='delete.jsp?table=brand&serverId=<%=brand.getServerId()%>'" class="btn btn-info">删除</button>
                </td> 
            </tr>
            <% 
             } %>
             
        </table>
</body>
</html>