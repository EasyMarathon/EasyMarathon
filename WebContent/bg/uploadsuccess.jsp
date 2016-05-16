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
<body class="">
    <section id="content">
    <div class="row m-n">
      <div class="col-sm-4 col-sm-offset-4">
        <div class="text-center m-b-lg"><br><br><br><br><br><br>
          <h1>上传成功</h1><br><br>
        </div>
        <div class="list-group auto m-b-sm m-b-lg">
          <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=<%=GongzhonghaoInfo.appID %>&redirect_uri=<%=GongzhonghaoInfo.URL %>oauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect" class="list-group-item">
            <i class="fa fa-chevron-right icon-muted" ></i>
            <i class="fa fa-cloud" style="color:#d0d0d0"></i>&nbsp;&nbsp;&nbsp;继续上传
          </a>
          <a href="tel://15700082120" class="list-group-item">
            <i class="fa fa-chevron-right icon-muted"></i>
            <span class="badge bg-info lt">10000</span>
            <i class="fa fa-fw fa-phone icon-muted"></i>&nbsp;&nbsp;联系我们
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