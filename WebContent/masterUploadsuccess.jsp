<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.EasyMarathon.bean.GongzhonghaoInfo" %>
<!DOCTYPE HTML>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>上传成功</title>
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
<%
	String name = (String)request.getAttribute("Name");
	String pictureURL = (String)request.getAttribute("PictureURL");
	int AthleteID = Integer.parseInt((String)request.getAttribute("AthleteID"));
%>
<body class="">
    <section id="content">
    <div class="row m-n">
      <div class="col-sm-4 col-sm-offset-4">
        <div class="text-center m-b-lg"><br><br>
          <h1>上传成功</h1>
          <label style="color:#ff6633">该照片已经识别完成，识别结果如下</label>
          <br>
        </div>
        <div class="list-group auto m-b-sm m-b-lg">
          <a class="list-group-item">
            <i class="fa fa-star icon-muted"></i>&nbsp;&nbsp;&nbsp;人脸识别匹配结果
          </a>
          <img src="<%=pictureURL %>" style="width:100%" class="list-group-item">

          <a class="list-group-item">
            <i class="fa fa-star icon-muted"></i>&nbsp;&nbsp;&nbsp;姓名：<%=name %>
          </a>
          <a class="list-group-item">
            <i class="fa fa-star icon-muted"></i>&nbsp;&nbsp;&nbsp;号码牌：<%=AthleteID %>
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