<%@page import="sun.security.pkcs11.Secmod.DbMode"%>
<%@page import="objects.Shoes"%>
<%@page import="objects.Color"%>
<%@page import="objects.Brand"%>
<%@page import="objects.Series"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="GBK"%>
<%@page import="java.util.*"%>
<%@page import="database.DBOperation"%>

<html>
    <head>
        <title>IBook���</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
            <jsp:useBean id="DB" class="database.DBOperation" scope="page"/>
        <jsp:setProperty name="DB" property="*"/>
        <%             request.setCharacterEncoding("gb2312");
             response.setCharacterEncoding("gb2312");
             try{
             File adidas = new File(application.getRealPath("/")+"/pics/adidas.png");
             String s = adidas.getAbsolutePath();
             System.out.print(s);
             FileInputStream in = new FileInputStream(adidas);
             int length = (int)adidas.length();
             byte[] bytes = new byte[length];
             in.read(bytes,0,length);
             Brand adidasBrand = new Brand("adidas",bytes);
             DB.insertBrand(adidasBrand);
             in.close();
             
             File anta = new File(application.getRealPath("/")+"pics/anta.png");
             length = (int)anta.length();
             in = new FileInputStream(anta);
             bytes = new byte[length];
             in.read(bytes,0,length);
             Brand antaBrand = new Brand("ANTA",bytes);
             DB.insertBrand(antaBrand);
             in.close();
             
             File nike = new File(application.getRealPath("/")+"pics/nike.png");
             length = (int)nike.length();
             in = new FileInputStream(nike);
             bytes = new byte[length];
             in.read(bytes,0,length);
             Brand nikeBrand = new Brand("Nike",bytes);
             DB.insertBrand(nikeBrand);
             in.close();
             
             File peak = new File(application.getRealPath("/")+"pics/peak.png");
             length = (int)peak.length();
             in = new FileInputStream(peak);
             bytes = new byte[length];
             in.read(bytes,0,length);
             Brand peakBrand = new Brand("PEAK",bytes);
             DB.insertBrand(peakBrand);
             in.close();
             
             File reebok = new File(application.getRealPath("/")+"pics/reebok.png");
             length = (int)reebok.length();
             in = new FileInputStream(reebok);
             bytes = new byte[length];
             in.read(bytes,0,length);
             Brand reebokBrand = new Brand("REEBOK",bytes);
             DB.insertBrand(reebokBrand);
             in.close();
             %>
             <%
             String seriesIndro = "1�������ܱ༭\n"
            		 +"  Nike Air Zoom ���Ϳ����漼���е�һ�֣��Ϳ˳�������漼��֮һ������֮�⣬����MAX AIR��AIR-SOLE��Nike Air Zoom���ص��ǳ��ᡢ������������㼼�����㷺�������Ϳ˵�����Ь���ܲ�Ь��ѵ��Ь�У���������������漼�������������Եý�Ӳ����ͬʱҲ�ṩ�˸��õĵ��ԣ��ܹ�Ϊ�������ṩ���ѵķ�Ӧ��nike air zoomרҵ�����ں�һ����������壬�Ը�ѹ�ķ�ʽ������͵ĺϳ����ڡ�Nike Air Zoom�Ƽ�֮���Գɹ��Ĺؼ�����Nike Air Zoomרҵ�����У�����Ĵ��������������ںϳ��𽺲��ϸ΢��϶�����������е����岻����ʧ��Nike Air Zoom������������������񶯺ͳ��ѹ����Ȼ��ܿ�Ļָ�ԭ�������ӣ���׼��������һ�γ����ͬʱ��������������Ь�ӵ��������ڶ�ĥ��\n"
            		 +"2��Ʒ��Ʊ༭\n"
            		 +"  nike air zoom�������������Ь�ڣ�һ���ǲ��ɼ��ģ��������Ь�����������漼���������Ь�����zoomair���������н�Ϊ�д����Ե�Ь����ZOOM KOBE,ZOOM HUARACHE 2K,ZOOM LEBRONϵ�еȡ�nike air zoom��һ���air���漼������ͬ��ӵ������Ƥ�����������������������ά�����Իظ��ٶ��뵯�Ծͱ���ͨ��air�����㡣"
            		 +"nike air zoom�Ĺ������һƬ��ƽ״�����棬Ȼ���м������������ά����ά������������һ��ֲ���ƽ�棬���ļ���ԭ���ǽ�Ϊ���ӵģ��������Ĵ����߸��и�ͬ���ܵĸо���nike air zoomһ����Ь�������ǿ������ģ��߱����������Ьһ������ڲ���Ь������zoom air���������д���Ŀ�ʽ����������Ϥ��zoom kobe�ȡ�\n"
            		 +"3��Ʒ�����༭\n"
            		 +"  1978�����һ��\n"
            		 +"  ���淢���߷�����³�ϣ�Frank Rudy���״�����������ó�����������ǿ����Ь�ı����ԡ�һ��֮�� Nike air zoom���������˶�Ьҵ�ĸ������������˶�Ь���ܾ�������³�ϳ��ѳ����������ʱ��Nikeȴ�������������Ƚ����뷨��\n"
            		 +"4����ԭ��༭\n"
            		 +"  ���ȣ�Nike air zoom�Ĺ������һƬ��ƽ״�����棬Ȼ���м������������ά����ά������������һ��֯���ƽ�棬�ÿ�������ѹ�ķ�ʽ�̶�������������ڱڣ���������еȷ������͵����ʣ������γ���״�����ƣ����Ի��������ά��ֱ�ɽ���״̬������γ�ƽ�⣬ʹ��ά������ô���ĺ��(8mm)������һ�����������"
            		 +"����Щ������ά��α����أ���һ�����ҿ�ʼ���뷨һ������Ϊ��������ά�Լ��ص����ǲ��ǣ��������֮ǰ�о���ֵĵط���"
            		 +"�ٸ����ӣ�����������һ�����߰�����ֱ����ʱ������Nike air zoom�е�������άһ�����ֽ�����״̬���������������м�ѹ��ʱ�����ô����?ֱ����������������˵���"
            		 +"�ۺ���˵����Nike air zoom����������棬�Ѿ�������ı��𻺳����ʣ����м���������¶˹̶���������ά֧�Ų��ϣ��ڱ���ʱ������ά�������������ӵ���������ֹ�������ּ�������������zoom air��������ô�̵ľ����У��ṩ�൱����ı��������������������ӵ�����ǿ��������άƽ��״̬ʱ�ĳ��ȣ���ѹ����ʹ��ԭ��������λ��ѹ���Ӷ��ص��������������˾��ȵĵ����뷴���ԡ�"
            		 +"�������ͨair�ı���ԭ����������ҪҲ����δ������λ������ӣ�����Ƥ���������������������ṩ�ظ��ĵ��ԣ���Nikeair zoom����ͬ��ӵ������Ƥ�����������������������ά�����Իظ��ٶ��뵯�Ծͱ���ͨair�����㡣"
            		 +"һ�Ա�֮������������ά����������ѹ֮����໥���������������뷴�������á�\n"
            		 +"  ����������ϣ�ȫ��ʽNike air zoom��Ϊ�ص�������λ���������ǰ��ʽ��Ҫ�����ṩ������������ά�����϶࣬�Ա����뵯���а�����Nike air zoom�����ʦ�Լ�Ҳ�����ȫ��ʽNike air zoom�ı�������������˾��ȡ�"
            		 +"�������㹭��Ҫ����֧�Ŷ��Ǳ�������Ƕ���������ʵ���ǿ��԰�Nike air zoom�ķ�Χ���㹭����Ĳ�����չ�����ҰѺ������(Nike air zoom)����߱�������������ʹ��ȫ��ʽNike air zoom��һ����Ƚ����롣";    
             File a = new File(application.getRealPath("/")+"pics/anta.png");
             int l = (int)a.length();
             FileInputStream i = new FileInputStream(a);
             byte[] b = new byte[l];
             i.read(b,0,l);
             Series antaSeries;
             String seriesName;
             String generationName;
             String colorName;
             Color color;
             String shoesName;
             Shoes shoes;
             for(int n=0;n<70;n++){
            	 seriesName = "air"+n;
                 antaSeries = new Series("ANTA",seriesName,b,seriesIndro);
                 DB.insertSeries(antaSeries);
             }
           	 for(int m = 0;m<70;m++){
        		 colorName = "color"+m;
        		 color = new Color("ANTA","air1",colorName,b);
        		 DB.insertColor(color);
        	 }
    		 for(int h = 0;h<100;h++){
    			 shoesName = "shoes"+h;
    			 shoes = new Shoes("ANTA", "air1","color1",shoesName,b,5000,"����","�߰�","����","��","��ĥ","ǰ��","��","����",seriesIndro);
    			 DB.insertShoes(shoes);
    		 }
             i.close();
             }catch(Exception e){
            	 e.printStackTrace();
             }
             %>
             

        
        <div class="container " style="background-color:rgb(139, 182, 238)">
            <br>
             
              <form class="form-signin" method="post" action="Loginralidate.jsp">
                <h2>��Ա��¼</h2>
                <input type="text" class="input-block-level" placeholder="�û���" name="username" id="username" onblur="checkName()"><label id="user-info" style="color:#ff0000"></label>
                <input type="password" class="input-block-level" placeholder="����" name="password" id="password" onblur="checkPwd()"><label id="password-info" style="color:#ff0000"></label>
                <button class="btn btn-large btn-primary sit " type="submit">��½</button>
                <input type="button" class="btn btn-large btn-primary" onclick="window.location.href='Register.jsp'" value="ע��">
              </form>
           </div>
<script>
                   
		function checkName(){
			var user = document.getElementById("username");
			var userInfo = document.getElementById("user-info");
			var reg = /^\w+$/;
			if(user.value.length==0){
				userInfo.innerHTML = "�û�������Ϊ��";
				return 0;
			}
			userInfo.innerHTML = "";
			return 1;
		}
		function checkPwd(){
			var n = document.getElementById("password");
			var passwordinfo = document.getElementById("password-info");
			if(n.value.length==0){
				passwordinfo.innerHTML = "���벻��Ϊ��!"
				return 0;
			}
			passwordinfo.innerHTML = "";
			return 1;
		}
	
               </script>
    </body>
</html>