<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.EasyMarathon.bean.GongzhonghaoInfo" %>
<!DOCTYPE HTML>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>装备指南</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="bg/PersonCenter/js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/font-awesome-ie7.min.css">
  <link rel="stylesheet" href="bg/PersonCenter/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/font.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/app.css" type="text/css" />  
    <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
</head>
<body class="">
    <section id="content">
    <div class="row m-n">
      <div class="col-sm-4 col-sm-offset-4">
        <div class="text-center m-b-lg"><br>
          <h2>马拉松的那些正经装备，<br>你造吗？</h2><br>
          <label style="color:#ff6633">推送日期&nbsp;&nbsp;&nbsp;2016.05.19 08:10</label>
        </div>
        <div class="list-group auto m-b-sm m-b-lg">
          <a class="list-group-item">
          	<i class="fa fa-star icon-muted"></i>&nbsp;&nbsp;运动眼镜 Nike SHOW X2 E
          </a>
          <img src="<%=GongzhonghaoInfo.URL+"articlePic/glass.jpg" %>" style="width:100%" class="list-group-item">
          <a class="list-group-item">
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nike光学品质镜片可以为佩戴者提供高质量的UVA以及UVB射线保护，
          	而且这款眼镜很贴合面部以及脸的轮廓，不用担心运动起来上下摆动的问题。
          </a>
        
          <a class="list-group-item">
          	<i class="fa fa-star icon-muted"></i>&nbsp;&nbsp;压缩小腿套
          </a>
          <img src="<%=GongzhonghaoInfo.URL+"articlePic/leg.jpg" %>" style="width:100%" class="list-group-item">
          <a class="list-group-item">
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;小腿套可以帮助缓冲和吸收肌肉、静脉和关节的震颤情形，
          	缓解长时间跑步后所产生的肌肉酸痛感。 
          	轻量化材质和防撕裂面料结构能够帮助提升运动表现，
          	 是比赛中及鲜亮又实用的单品。
          </a>
          
          <a class="list-group-item">
          	<i class="fa fa-star icon-muted"></i>&nbsp;&nbsp;严肃魔术帽
          </a>
          <img src="<%=GongzhonghaoInfo.URL+"articlePic/maozi.jpg" %>" style="width:100%" class="list-group-item">
          <a class="list-group-item">
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以为这是魔术帽，你就输了，虽然说是严肃魔术帽，其实也没有那么严肃，
          	魔术帽名称的来源是因为这顶帽子可以当作毛巾和护腕同时使用，非常神奇。
          	是每次跑马拉松时必备的单品。
          </a>
          
        </div>
      </div>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder clearfix">
      <p>
        <small>易跑平台开发小组<br>&copy; 2016</small>
      </p>
    </div>
  </footer>
  <!-- / footer -->
  <script src="bg/PersonCenter/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="bg/PersonCenter/js/bootstrap.js"></script>
  <!-- App -->
  <script src="bg/PersonCenter/js/app.js"></script>  
  <script src="bg/PersonCenter/js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="PersonCenter/js/app.plugin.js"></script>
  <script type="text/javascript" src="bg/PersonCenter/js/jPlayer/jquery.jplayer.min.js"></script>
  <script type="text/javascript" src="bg/PersonCenter/js/jPlayer/add-on/jplayer.playlist.min.js"></script>
  <script type="text/javascript" src="bg/PersonCenter/js/jPlayer/demo.js"></script>

</body>
</html>