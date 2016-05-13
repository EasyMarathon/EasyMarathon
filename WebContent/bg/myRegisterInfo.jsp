<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.EasyMarathon.bean.GongzhonghaoInfo"%>
<%@ page import="com.EasyMarathon.bean.UserBean"%>
<!DOCTYPE HTML>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>个人参赛信息</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="PersonCenter/js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/font-awesome-ie7.min.css">
  <link rel="stylesheet" href="PersonCenter/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/font.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/app.css" type="text/css" />  
    <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
</head>
<body class="">
<%
	UserBean user = (UserBean)session.getAttribute("user");
	String eventName = (String)session.getAttribute("eventName");
%>
    <section id="content">
    <div class="row m-n">
      <div class="col-sm-4 col-sm-offset-4">
        <div class="text-center m-b-lg"><br><br>
          <h2><%=eventName %></h2>
          <h4>我的参赛信息</h4>
        </div>
        <div class="list-group auto m-b-sm m-b-lg">
          <a class="list-group-item">
            <i class="fa fa-star" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;姓名：<%=user.getUserName() %>
          </a>
          <a class="list-group-item">
            <i class="fa fa-star" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;身份证号：<%=user.getIdentityCard() %>
          </a>
          <a class="list-group-item">
            <i class="fa fa-star" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;身高：<%=user.getHeight() %>
          </a>
          <a class="list-group-item">
            <i class="fa fa-star" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;体重：<%=user.getWeight() %>
          </a>
          <a class="list-group-item">
            <i class="fa fa-star" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;联系方式：<%=user.getCelphone() %>
          </a>
          <a class="list-group-item">
            <i class="fa fa-star" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;邮箱：<%=user.getEmail() %>
          </a>
          <a class="list-group-item">
            <i class="fa fa-star" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;血型：<%=user.getBloodType() %>
          </a>
          <a class="list-group-item">
            <i class="fa fa-star" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;紧急联系人：<%=user.getUrgencyContact() %>
          </a>
          <a href="tel://<%=user.getUrgencyPhone() %>" class="list-group-item" >
            <i class="fa fa-fw fa-phone icon-muted" style="color:#d0d0d0"></i>
            &nbsp;&nbsp;&nbsp;紧急联系人电话：
            <span class="badge bg-info lt"><%=user.getUrgencyPhone() %></span>
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
  <script src="PersonCenter/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="PersonCenter/js/bootstrap.js"></script>
  <!-- App -->
  <script src="PersonCenter/js/app.js"></script>  
  <script src="PersonCenter/js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="PersonCenter/js/app.plugin.js"></script>
  <script type="text/javascript" src="PersonCenter/js/jPlayer/jquery.jplayer.min.js"></script>
  <script type="text/javascript" src="PersonCenter/js/jPlayer/add-on/jplayer.playlist.min.js"></script>
  <script type="text/javascript" src="PersonCenter/js/jPlayer/demo.js"></script>

</body>
</html>