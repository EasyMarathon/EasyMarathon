<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.EasyMarathon.bean.SNSUserInfo"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>完善信息</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="PersonCenter/js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="PersonCenter/css/font-awesome.min.css" type="text/css" />
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
  
  SNSUserInfo user = (SNSUserInfo)session.getAttribute("snsUserInfo"); 
System.out.println(user.getOpenId());
%>
<section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info nav-xs">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
          <i class="icon-list"></i>
        </a>
        <a href="index.html" class="navbar-brand text-lt">
          <span class="hidden-nav-xs m-l-sm">Marathon Club</span>
        </a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">
          <i class="icon-settings"></i>
        </a>
      </div>      <ul class="nav navbar-nav hidden-xs">
        <li>
          <a href="#nav,.navbar-header" data-toggle="class:nav-xs,nav-xs" class="text-muted">
            <i class="fa fa-indent text"></i>
            <i class="fa fa-dedent text-active"></i>
          </a>
        </li>
      </ul>
      <form class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs" role="search">
        <div class="form-group">
          <div class="input-group">
            <span class="input-group-btn">
              <button type="submit" class="btn btn-sm bg-white btn-icon rounded"><i class="fa fa-search"></i></button>
            </span>
            <input type="text" class="form-control input-sm no-border rounded" placeholder="Search...">
          </div>
        </div>
      </form>
      <div class="navbar-right ">
        <ul class="nav navbar-nav m-n hidden-xs nav-user user">
          <li class="hidden-xs">
            <a href="#" class="dropdown-toggle lt" data-toggle="dropdown">
              <i class="icon-bell"></i>
              <span class="badge badge-sm up bg-danger count">2</span>
            </a>
            <section class="dropdown-menu aside-xl animated fadeInUp">
              <section class="panel bg-white">
                <div class="panel-heading b-light bg-light">
                  <strong>You have <span class="count">2</span> notifications</strong>
                </div>
                <div class="list-group list-group-alt">
                  <a href="#" class="media list-group-item">
                    <span class="pull-left thumb-sm">
                      <img src="PersonCenter/images/a0.png" alt="..." class="img-circle">
                    </span>
                    <span class="media-body block m-b-none">
                      Use awesome animate.css<br>
                      <small class="text-muted">10 minutes ago</small>
                    </span>
                  </a>
                  <a href="#" class="media list-group-item">
                    <span class="media-body block m-b-none">
                      1.0 initial released<br>
                      <small class="text-muted">1 hour ago</small>
                    </span>
                  </a>
                </div>
                <div class="panel-footer text-sm">
                  <a href="#" class="pull-right"><i class="fa fa-cog"></i></a>
                  <a href="#notes" data-toggle="class:show animated fadeInRight">See all the notifications</a>
                </div>
              </section>
            </section>
          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
              <span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm">
                <img src="PersonCenter/images/a0.png" alt="...">
              </span>
              John.Smith <b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInRight">            
              <li>
                <span class="arrow top"></span>
                <a href="#">设置</a>
              </li>
              <li>
                <a href="#">
                  <span class="badge bg-danger pull-right">3</span>消息
                </a>
              </li>
              <li>
                <a href="docs.html">帮助</a>
              </li>
              <li class="divider"></li>
              <li>
                <a href="modal.lockme.html" data-toggle="ajaxModal" >注销</a>
              </li>
            </ul>
          </li>
        </ul>
      </div>      
    </header>
    <section>
      <section class="hbox stretch">
        <!-- .aside -->
        <aside class="bg-black dk nav-xs aside hidden-print" id="nav">          
          <section class="vbox">
            <section class="w-f-md scrollable">
              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railOpacity="0.2">

                <!-- nav -->                 
                <nav class="nav-primary hidden-xs">
                  <ul class="nav bg clearfix">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">跑城装备</li>
                    <li>
                      <a href="">
                        <span class="font-bold">服饰</span>
                      </a>
                    </li>
                    <li>
                      <a href="">
                        <span class="font-bold">跑鞋</span>
                      </a>
                    </li>
                    <li>
                      <a href="">
                        <span class="font-bold">潮品</span>
                      </a>
                    </li>
                    <li>
                      <a href="events.html">
                        <b class="badge bg-primary pull-right">6</b>
                        <span class="font-bold">新品上市</span>
                      </a>
                    </li>
                    <li class="m-b hidden-nav-xs"></li>
                  </ul>
                  <ul class="nav bg clearfix">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                    	免费照片</li>
                    <li>
                      <a href="index.html">
                        <span class="font-bold">风景</span>
                      </a>
                    </li>
                    <li>
                      <a href="genres.html">
                        <span class="font-bold">人物</span>
                      </a>
                    </li>
                    <li>
                      <a href="listen.html">
                        <span class="font-bold">摄影师作品</span>
                      </a>
                    </li>
                    <li class="m-b hidden-nav-xs"></li>
                  </ul>
                  <ul class="nav" data-ride="collapse">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                      	跑城优惠
                    </li>
                    <li >
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="fa fa-angle-left text"></i>
                          <i class="fa fa-angle-down text-active"></i>
                        </span>
                        <span>美食</span>
                      </a>
                      <ul class="nav dk text-sm">
                        <li >
                          <a href="" class="auto">                                                        
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>中餐</span>
                          </a>
                        </li>
                        <li >
                          <a href="" class="auto"> 
                          	<i class="fa fa-angle-right text-xs"></i>                                                       
                            <span>西餐</span>
                          </a>
                        </li>
                        <li >
                          <a href="" class="auto">                                                        
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>甜点</span>
                          </a>
                        </li>
                        <li >
                          <a href="" class="auto">                                                        
                            <i class="fa fa-angle-right text-xs"></i>

                            <span>酒吧</span>
                          </a>
                        </li>
                      </ul>
                    </li>
                    <li >
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="fa fa-angle-left text"></i>
                          <i class="fa fa-angle-down text-active"></i>
                        </span>
                        <span>交通</span>
                      </a>
                      <ul class="nav dk text-sm">
                        <li >
                          <a href="" class="auto">                                                        
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>地铁</span>
                          </a>
                        </li>
                        <li >
                          <a href="" class="auto">                                                        
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>汽车</span>
                          </a>
                        </li>
                        <li >
                          <a href="" class="auto">                                                                                 
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>轮船</span>
                          </a>
                        </li>
                      </ul>
                    </li>
                    <li >
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="fa fa-angle-left text"></i>
                          <i class="fa fa-angle-down text-active"></i>
                        </span>
                        <span>住宿</span>
                      </a>
                      <ul class="nav dk text-sm">
                        <li >
                          <a href="" class="auto">                                                        
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>星级酒店</span>
                          </a>
                        </li>
                        <li >
                          <a href="" class="auto">                                                        
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>快捷酒店</span>
                          </a>
                        </li>
                        <li >
                          <a href="" class="auto">                                                        
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>民宿</span>
                          </a>
                        </li>                      
                      </ul>
                    </li>
                  </ul>     
                </nav>
                <!-- / nav -->
              </div>
            </section> 
          </section>
        </aside>
        <!-- /.aside -->
        <section id="content">
          <section class="vbox">
            <section class="scrollable padder">
              <div class="m-b-md">
                <h3 class="m-b-none"></h3>
              </div>
              <div class="row">
                <div class="col-sm-6">
                  <form data-validate="parsley" action="register">
                    <section class="panel panel-default">
                      <header class="panel-heading">
                        <span class="h4">完善信息</span>
                      </header>
                      <div class="panel-body">
                        <div class="form-group">
                          <label>姓名</label>
                          <input type="text"  data-required="true" name="user.userName"/>                        
                        </div>  
                        <div class="form-group">
                          <label>邮箱</label>
                        <input type="text"  data-type="email" data-required="true" name="user.email"/>                        
                        </div>
                        <div class="form-group">
                          <label>联系方式</label>
                          <input type="text"  data-type="phone" placeholder="(XXX) XXXX XXX" data-required="true" name="user.celphone"/>
                        </div>
                        </div>
                        			<div style="display:none">
						<input value="<%=user.getOpenId() %>" type="hidden" value="1" id="txt_width" name="user.wechatID"/>
						</div>
                        <div class="checkbox i-checks">
                          <label>
                            <input type="checkbox" name="check" checked data-required="true"><i></i>我同意遵守 <a href="#" class="text-info">《易跑平台服务条款》</a>
                          </label>
                        </div>
                      </div>
                      <footer class="panel-footer text-right bg-light lter">
                        <button type="submit" class="btn btn-success btn-s-xs">提交</button>
                      </footer>
                    </section>
                  </form>
                </div>
                </div>
                </section>
                </section>
			</section>
		</section>
	</section>
</section>

  <script src="PersonCenter/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="PersonCenter/js/bootstrap.js"></script>
  <!-- App -->
  <script src="PersonCenter/js/app.js"></script>  
  <script src="PersonCenter/js/slimscroll/jquery.slimscroll.min.js"></script>
  <!-- parsley -->
<script src="PersonCenter/js/parsley/parsley.min.js"></script>
<script src="PersonCenter/js/parsley/parsley.extend.js"></script>
  <script src="PersonCenter/js/app.plugin.js"></script>
  <script type="text/javascript" src="PersonCenter/js/jPlayer/jquery.jplayer.min.js"></script>
  <script type="text/javascript" src="PersonCenter/js/jPlayer/add-on/jplayer.playlist.min.js"></script>
  <script type="text/javascript" src="PersonCenter/js/jPlayer/demo.js"></script>

</body>
</html>