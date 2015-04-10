<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="GBK"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.*"%>

<% response.setContentType("text/html");
//   ͼƬ�ϴ�·��
   String uploadPath =request.getSession().getServletContext().getRealPath("/")+"upload/images/";
//   ͼƬ��ʱ�ϴ�·��
   String tempPath = request.getSession().getServletContext().getRealPath("/")+"upload/images/temp/";
//   ͼƬ�������·��
   String imagePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
//   �ļ��в����ھ��Զ�������
   if(!new File(uploadPath).isDirectory())
   new File(uploadPath).mkdirs();
   if(!new File(tempPath).isDirectory())
   new File(tempPath).mkdirs();
   try {
   DiskFileUpload fu = new DiskFileUpload();
//   ��������ļ��ߴ磬������4MB
   fu.setSizeMax(4194304);
//   ���û�������С��������4kb
   fu.setSizeThreshold(4096);
//   ������ʱĿ¼��
   fu.setRepositoryPath(tempPath);
//   �õ����е��ļ���
   List fileItems = fu.parseRequest(request);
   Iterator i = fileItems.iterator();
//   ���δ���ÿһ���ļ���
   while(i.hasNext()) {
   FileItem file = (FileItem)i.next();
//   ����ļ���������ļ������û��ϴ�ʱ�û��ľ���·����
   String sourcefileName = file.getName();
   if(sourcefileName!=null&&(sourcefileName.endsWith(".jpg")||sourcefileName.endsWith(".gif")||sourcefileName.endsWith(".png"))) {
//   ��������Լ�¼�û����ļ���Ϣ,�����ϴ�����ļ���
   String destinationfileName=null;
   Random rd = new Random();
   Calendar time = Calendar.getInstance();
   if(sourcefileName.endsWith(".jpg")){
   destinationfileName=String.valueOf(time.get(Calendar.YEAR))
   + String.valueOf(time.get(Calendar.MONTH))
   + String.valueOf(time.get(Calendar.DAY_OF_MONTH))
   + String.valueOf(time.get(Calendar.HOUR_OF_DAY))
   + String.valueOf(time.get(Calendar.MINUTE))
   + String.valueOf(time.get(Calendar.SECOND))
   + String.valueOf(rd.nextInt(100)) + ".jpg";
   }else if(sourcefileName.endsWith(".gif")){
   destinationfileName=String.valueOf(time.get(Calendar.YEAR))
   + String.valueOf(time.get(Calendar.MONTH))
   + String.valueOf(time.get(Calendar.DAY_OF_MONTH))
   + String.valueOf(time.get(Calendar.HOUR_OF_DAY))
   + String.valueOf(time.get(Calendar.MINUTE))
   + String.valueOf(time.get(Calendar.SECOND))
   + String.valueOf(rd.nextInt(100)) + ".gif";
   }else if(sourcefileName.endsWith(".png")){
	   destinationfileName=String.valueOf(time.get(Calendar.YEAR))
			   + String.valueOf(time.get(Calendar.MONTH))
			   + String.valueOf(time.get(Calendar.DAY_OF_MONTH))
			   + String.valueOf(time.get(Calendar.HOUR_OF_DAY))
			   + String.valueOf(time.get(Calendar.MINUTE))
			   + String.valueOf(time.get(Calendar.SECOND))
			   + String.valueOf(rd.nextInt(100)) + ".png";
			   }
   
   
   
   File f1=new File(uploadPath+ destinationfileName);
   file.write(f1);
   out.print(sourcefileName+"�ɹ��ϴ���") ;
   out.print("<img src="+imagePath+"upload/images/"+destinationfileName+">");
   }else{
   out.println("�ϴ��ļ�����ֻ���ϴ� *.jpg , *.gif, *.png");
   }
   }
//   ��ת���ϴ��ɹ���ʾҳ��
   }
   catch(Exception e) {
//   ������ת����ҳ��
   }
   out.flush();
   out.close();

%>