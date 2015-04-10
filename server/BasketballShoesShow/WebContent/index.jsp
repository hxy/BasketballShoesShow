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
        <title>IBook书城</title>
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
             String seriesIndro = "1基本介绍编辑\n"
            		 +"  Nike Air Zoom 是耐克气垫技术中的一种，耐克成熟的气垫技术之一，除此之外，还有MAX AIR，AIR-SOLE。Nike Air Zoom的特点是超轻、超薄，且这项矮点技术被广泛运用于耐克的篮球鞋、跑步鞋和训练鞋中，相比另外两种气垫技术，这种气垫显得较硬，但同时也提供了更好的弹性，能够为穿着者提供更佳的反应。nike air zoom专业气垫内含一种特殊的气体，以高压的方式灌入坚韧的合成橡胶内。Nike Air Zoom科技之所以成功的关键在于Nike Air Zoom专业气垫中，特殊的大分子气体体积大于合成橡胶层的细微缝隙，所以气垫中的气体不会流失。Nike Air Zoom气体分子吸收外来的振动和冲击压力，然后很快的恢复原来的样子，并准备吸收下一次冲击。同时，它并不会随着鞋子的生命周期而磨损。\n"
            		 +"2产品设计编辑\n"
            		 +"  nike air zoom气垫设计在篮球鞋内，一般是不可见的，如果篮球鞋款有这种气垫技术，则会在鞋身标有zoomair字样，其中较为有代表性的鞋款有ZOOM KOBE,ZOOM HUARACHE 2K,ZOOM LEBRON系列等。nike air zoom比一般的air气垫技术除了同样拥有塑料皮的张力，还多了许多尼龙纤维，所以回复速度与弹性就比普通的air更优秀。"
            		 +"nike air zoom的构造就是一片扁平状的气垫，然后中间是许多尼龙纤维，纤维的上下两端有一个植物的平面，它的减震原理还是较为复杂的，切身体会的穿着者更有感同身受的感觉，nike air zoom一般在鞋子外面是看不见的，具备这种气垫的鞋一般会在内部的鞋身上有zoom air字样，其中代表的款式有我们所熟悉的zoom kobe等。\n"
            		 +"3产品发明编辑\n"
            		 +"  1978年灵光一现\n"
            		 +"  气垫发明者法兰克鲁迪（Frank Rudy）首次提出——运用充气气垫来加强慢跑鞋的避震性。一年之后， Nike air zoom便掀起了运动鞋业的革命。当其他运动鞋厂拒绝法兰克鲁迪超脱常规的想象力时，Nike却鼓励他完成这个先进的想法。\n"
            		 +"4减震原理编辑\n"
            		 +"  首先，Nike air zoom的构造就是一片扁平状的气垫，然后中间是许多尼龙纤维，纤维的上下两端有一个织物的平面，用可能是热压的方式固定在气垫的上下内壁，因气体具有等方向膨胀的性质，会有形成球状的趋势，所以会把尼龙纤维拉直成紧绷状态，因而形成平衡，使能维持在这么薄的厚度(8mm)，否则一定会鼓起来。"
            		 +"那这些尼龙纤维如何避震呢？你一定跟我开始的想法一样，以为是尼龙纤维自己回弹的是不是？这就是我之前感觉奇怪的地方。"
            		 +"举个例子，当我们拿着一条牙线把它拉直，这时候它跟Nike air zoom中的尼龙纤维一样呈现紧绷的状态，当把它用力往中间压的时候会怎么样呢?直接软掉，不会往两端弹。"
            		 +"综合来说就是Nike air zoom本身就是气垫，已具有气垫的避震缓冲性质，但中间加入了上下端固定的尼龙纤维支撑材料，在避震时可以纤维本身被拉长所增加的张力来阻止受力部分继续溃缩，所以zoom air才能在那么短的距离中，提供相当优异的避震能力，接着再以增加的张力强制拉回纤维平衡状态时的长度，挤压空气使得原本受力部位气压增加而回弹，所以有着令人惊讶的弹性与反馈性。"
            		 +"如果就普通air的避震原理来看，主要也是因未受力部位体积增加，塑料皮膨胀所产生的张力，来提供回复的弹性，而Nikeair zoom除了同样拥有塑料皮的张力，还多了许多尼龙纤维，所以回复速度与弹性就比普通air更优秀。"
            		 +"一言蔽之，利用尼龙纤维的张力与气压之间的相互调节来产生避震与反馈的作用。\n"
            		 +"  因此在理论上，全长式Nike air zoom因为重点受力部位以外区域比前后式的要大，能提供张力的尼龙纤维数量较多，对避震与弹性有帮助，Nike air zoom的设计师自己也提过，全长式Nike air zoom的避震表现总是令人惊讶。"
            		 +"但是在足弓需要更多支撑而非避震这个角度来看，其实还是可以把Nike air zoom的范围往足弓以外的部份扩展，并且把厚度增加(Nike air zoom)来提高避震能力，并非使用全长式Nike air zoom就一定会比较理想。";    
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
    			 shoes = new Shoes("ANTA", "air1","color1",shoesName,b,5000,"春季","高帮","尼龙","橡胶","耐磨","前锋","男","气垫",seriesIndro);
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
                <h2>会员登录</h2>
                <input type="text" class="input-block-level" placeholder="用户名" name="username" id="username" onblur="checkName()"><label id="user-info" style="color:#ff0000"></label>
                <input type="password" class="input-block-level" placeholder="密码" name="password" id="password" onblur="checkPwd()"><label id="password-info" style="color:#ff0000"></label>
                <button class="btn btn-large btn-primary sit " type="submit">登陆</button>
                <input type="button" class="btn btn-large btn-primary" onclick="window.location.href='Register.jsp'" value="注册">
              </form>
           </div>
<script>
                   
		function checkName(){
			var user = document.getElementById("username");
			var userInfo = document.getElementById("user-info");
			var reg = /^\w+$/;
			if(user.value.length==0){
				userInfo.innerHTML = "用户名不能为空";
				return 0;
			}
			userInfo.innerHTML = "";
			return 1;
		}
		function checkPwd(){
			var n = document.getElementById("password");
			var passwordinfo = document.getElementById("password-info");
			if(n.value.length==0){
				passwordinfo.innerHTML = "密码不能为空!"
				return 0;
			}
			passwordinfo.innerHTML = "";
			return 1;
		}
	
               </script>
    </body>
</html>