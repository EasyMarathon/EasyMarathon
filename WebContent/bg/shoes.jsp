<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.EasyMarathon.bean.FreePicBean"%>
<%@ page import="com.EasyMarathon.bean.SNSUserInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.EasyMarathon.bean.GongzhonghaoInfo"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>易跑商城</title>
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
  <link rel="stylesheet" href="bg/test.css" type="text/css" />
  <!-- 调用微信接口 -->
  <!-- <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> -->
    <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
  <script type="text/javascript">
  <%-- wx.config({
	    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '<%=GongzhonghaoInfo.appID%>',
	    timestamp: , // 必填，生成签名的时间戳
	    nonceStr: '', // 必填，生成签名的随机串
	    signature: '',// 必填，签名，见附录1
	    jsApiList: ['closeWindow'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	}); --%>
  </script>
</head>
<body class="">
<%
	SNSUserInfo user = (SNSUserInfo)session.getAttribute("snsUserInfo");
%>
  <section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info nav-xs">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
          <i class="icon-list"></i>
        </a>
        <a class="navbar-brand text-lt">
          <span class="hidden-nav-xs m-l-sm">易跑商城</span>
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
                <img src="<%=user.getHeadImgUrl() %>" alt="...">
              </span>
              <%=user.getNickname() %> <b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInRight">            
              <li style="display:none">
                <span class="arrow top" ></span>
                <a href="#">设置</a>
              </li>
              <li style="display:none">
                <a href="#">
                  <span class="badge bg-danger pull-right">3</span>消息
                </a>
              </li>
              <li>
                <a href="#">我的积分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-fire" style="color:#fb3504;"></i>&nbsp;&nbsp;100</a>
              </li>
              <li class="divider"></li>
              <li>
                <a onclick="javascript:wx.hideOptionMenu();">注销</a>
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
                      <a href="clothes.jsp">
                        <span class="font-bold">服饰</span>
                      </a>
                    </li>
                    <li>
                      <a href="shoes.jsp">
                        <span class="font-bold">跑鞋</span>
                      </a>
                    </li>
                    <li>
                      <a href="fashion.jsp">
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
                      <a href="mainPage.jsp">
                        <span class="font-bold">用户拍摄</span>
                      </a>
                    </li>
                    <li>
                      <a href="genres.html">
                        <span class="font-bold">我的照片</span>
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
          <section class="hbox stretch">
            <section>
              <section class="vbox">
              	<!-- 导航头 -->
	              <header class="header bg-light lt">
	                      <ul class="nav nav-tabs nav-white">
	                        <li class="active" style="width:33.33%;text-align:center;"><a href="#activity" data-toggle="tab">默认排序</a></li>
	                        <li class="" style="width:33.33%;text-align:center"><a href="#events" data-toggle="tab">销量优先</a></li>
	                        <li class="" style="width:33.33%;text-align:center"><a href="#interaction" data-toggle="tab">综合排序</a></li>
	                      </ul>
	              </header>
	              
                 <div class="tab-content">
                        <div class="tab-pane active" id="activity">
                          <ul  class="list-group no-radius m-b-none m-t-n-xxs list-group-lg no-border">
                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes1" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/1.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes1" class="clear">
                                <strong >Salomon 萨洛蒙户外跑鞋城市马拉松竞赛鞋 S-LAB SONIC</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">上海</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;1898元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li> 
                            
                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes2" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/2.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes2" class="clear">
                                <strong >迪卡侬   轻盈专业竞速跑鞋  耐磨透气运动鞋KALENJI</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">深圳</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;399元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li> 
                            
                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes3" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/3.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes3" class="clear">
                                <strong >迪卡侬   跑步鞋男士   透气轻盈缓震    跑步机专用   室内健身运动鞋   KALENJI</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">江苏</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;299元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li> 
                                            
                          </ul>
                        </div>
                        <div class="tab-pane" id="events"><!-- 销量优先页面 -->
                        	<div class="tab-pane active" id="activity">
                          <ul  class="list-group no-radius m-b-none m-t-n-xxs list-group-lg no-border">
                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes2" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/2.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes2" class="clear">
                                <strong >迪卡侬   轻盈专业竞速跑鞋  耐磨透气运动鞋KALENJI</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">深圳</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;399元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li>
                            
                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes1" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/1.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes1" class="clear">
                                <strong >Salomon 萨洛蒙户外跑鞋城市马拉松竞赛鞋 S-LAB SONIC</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">上海</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;1898元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li> 

                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes3" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/3.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes3" class="clear">
                                <strong >迪卡侬   跑步鞋男士   透气轻盈缓震    跑步机专用   室内健身运动鞋   KALENJI</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">江苏</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;299元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li> 
                                            
                          </ul>
                        </div>
                        </div>
                        <div class="tab-pane" id="interaction"><!-- 上传时间优先页面 -->
                        	<div class="tab-pane active" id="activity">
                          <ul  class="list-group no-radius m-b-none m-t-n-xxs list-group-lg no-border">
                            
                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes3" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/3.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes3" class="clear">
                                <strong >迪卡侬   跑步鞋男士   透气轻盈缓震    跑步机专用   室内健身运动鞋   KALENJI</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">江苏</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;299元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li> 
                            
                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes1" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/1.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes1" class="clear">
                                <strong >Salomon 萨洛蒙户外跑鞋城市马拉松竞赛鞋 S-LAB SONIC</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">上海</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;1898元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li> 
                            
                            <li  class="list-group-item">
                            <a href="#" data-toggle="modal" data-target="#shoes2" class="thumb-lg pull-left m-r-sm">
                                <img src="images/shoes/2.jpg" >
                              </a>
                              <a href="#" data-toggle="modal" data-target="#shoes2" class="clear">
                                <strong >迪卡侬   轻盈专业竞速跑鞋  耐磨透气运动鞋KALENJI</strong>
                                <br><br>
                                <small>包邮</small>
                                <small class="pull-right">深圳</small>
                                <br><br><br>
                                <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;399元</i>
								<small class="pull-right">
								<i class="fa fa-heart  text-danger"></i>
                                                                                                销量：0
								</small>
                              </a>
                            </li> 
                            
                            
                                            
                          </ul>
                        </div>
                        </div>
                      </div>
              </section>
            </section>
            
            <!-- / side content -->
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
        </section>
      </section>
    </section>    
  </section>
  
  <!-- 模态框（Moda1） -->
<div class="modal fade" id="shoes1" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">大图查看</h4>
         </div>
         <div class="post-media">
			 <img src="images/shoes/1.jpg" class="img-full">
         </div>
         <div class="modal-body">
         <strong >Salomon 萨洛蒙户外跑鞋城市马拉松竞赛鞋 S-LAB SONIC</strong><br>
		 <small>包邮</small>
		 <small class="pull-right">上海</small>
		 <br><br>
		 <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;1898元</i>
		 <small class="pull-right">
		 <i class="fa fa-heart  text-danger"></i>
		  销量：0
		 </small>
         <div class="checkbox i-checks">
			<label>
       			<input type="checkbox" name="check" checked data-required="true"><i></i>我同意遵守 <a href="#" class="text-info" data-toggle="modal" data-target="#buyer">《易跑平台照片购买条款》</a>
     		</label>
 		 </div>
         </div>
         
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" >购买</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 模态框（Moda2） -->
<div class="modal fade" id="shoes2" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">大图查看</h4>
         </div>
         <div class="post-media">
			 <img src="images/shoes/2.jpg" class="img-full">
         </div>
         <div class="modal-body">
         <strong >迪卡侬   轻盈专业竞速跑鞋  耐磨透气运动鞋KALENJI</strong><br>
		 <small>包邮</small>
		 <small class="pull-right">深圳</small>
		 <br><br>
		 <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;399元</i>
		 <small class="pull-right">
		 <i class="fa fa-heart  text-danger"></i>
		  销量：0
		 </small>
         <div class="checkbox i-checks">
			<label>
       			<input type="checkbox" name="check" checked data-required="true"><i></i>我同意遵守 <a href="#" class="text-info" data-toggle="modal" data-target="#buyer">《易跑平台照片购买条款》</a>
     		</label>
 		 </div>
         </div>
         
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" >购买</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 模态框（Moda3） -->
<div class="modal fade" id="shoes3" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">大图查看</h4>
         </div>
         <div class="post-media">
			 <img src="images/shoes/3.jpg" class="img-full">
         </div>
         <div class="modal-body">
         <strong >迪卡侬   跑步鞋男士   透气轻盈缓震    跑步机专用   室内健身运动鞋   KALENJI</strong><br>
		 <small>包邮</small>
		 <small class="pull-right">江苏</small>
		 <br><br>
		 <i class="fa fa-rmb" style="color:#fb3504;">&nbsp;&nbsp;299元</i>
		 <small class="pull-right">
		 <i class="fa fa-heart  text-danger"></i>
		  销量：0
		 </small>
         <div class="checkbox i-checks">
			<label>
       			<input type="checkbox" name="check" checked data-required="true"><i></i>我同意遵守 <a href="#" class="text-info" data-toggle="modal" data-target="#buyer">《易跑平台照片购买条款》</a>
     		</label>
 		 </div>
         </div>
         
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" >购买</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

  <!-- 购买条款模态框（Modal） -->
	<div class="modal fade" id="buyer" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">易跑平台照片购买协议</h4>
				</div>
				<div class="modal-body">
					<span class="font-bold">1.服务说明</span><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选手可在参赛赛事前后2周时间内、服务提供前，通过本协议同意团队为您提供照片个性化服务。团队派摄影师进行为您进行活动照片拍摄，对数据进行处理，在网上针对参赛者公开，提供浏览服务。照片上除了浏览者本人外还可能拍到了其他人，但是图片信息，不会存在其他个人信息。如果被拍者不希望自己的照片出现在本平台上，可以向平台申请撤掉照片。赛事活动个性化照片服务只提供于本照片号码牌相应选手本人，不提供第三方。<br>
					<span class="font-bold">2.购买和退换</span><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;只接受线上预订和购买，电子版产品不接受除选手主体跑焦之外的其他退货理由。实物产品不接受除破损之外的其他退货理由。<br>
					<span class="font-bold">3.照片版权和使用范围</span><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在个人使用范围内，可用于一般的照片冲洗和家用打印机打印，可在自己的主页和微信微博中使用自己的照片，如果可以，请加上我们平台的二维码。在购买交易之后，照片的著作权仍属于我公司，照片如果用于商业目的，请通过邮件向我公司进行事先通报。<br>
					<span class="font-bold">4.个人隐私权说明</span><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台对用户注册的个人信息严格保密，不做其它使用。<br>
					<span class="font-bold">5.免责事项</span><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;微信使用者的ID和密码管理与本平台无关。如果ID和密码被第三者使用，个人信息可能被第三者浏览。<br>
					<div class="checkbox i-checks">
						<label> <input type="checkbox" name="check" checked
							data-required="true">
						</label>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
  
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