<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>购物车</title>
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
  <section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info nav-xs">
        <a href="index.html" class="navbar-brand text-lt">
          <span class="hidden-nav-xs m-l-sm">Photo Club</span>
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
    
    <!-- /.aside -->
        <section id="content">
          <section class="hbox stretch">
            <section>
              <section class="vbox">
                <section class="scrollable padder-lg w-f-md" id="bjax-target">
                  <a href="#" class="pull-right text-muted m-t-lg" data-toggle="class:fa-spin" ><i class="icon-refresh i-lg  inline" id="refresh"></i></a>
                  <h2 class="font-thin m-b">购物车
                    <span class="bar1 a1 bg-primary lter"></span>
                    <span class="bar2 a2 bg-info lt"></span>
                    <span class="bar3 a3 bg-success"></span>
                    <span class="bar4 a4 bg-warning dk"></span>
                    <span class="bar5 a5 bg-danger dker"></span>
                  </h2>
                  <div class="row row-sm">
                    <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                      <div class="item">
                        <div class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">
                            <div class="center text-center m-t-n">
                            </div>
                            <div class="bottom padder m-b-sm">
                              <a href="#" class="pull-right" data-toggle="class">
                                <i class="fa fa-heart-o text"></i>
                                <i class="fa fa-heart text-active text-danger"></i>
                              </a>
                              <a href="#" data-toggle="class">
                              	<!-- 从购物车移除该照片 -->
                                <i class="fa fa-trash-o"></i>
                              </a>
                            </div>
                          </div>
                          <a href="#"><img src="PersonCenter/images/o7.jpg" alt="" class="r r-2x img-full"></a>
                        </div>
                        <div class="padder-v">
                          <a href="#" class="text-ellipsis">Morbi id neque quam liquam sollicitudin</a>
                          <a href="#" class="text-ellipsis text-xs text-muted">Allen JH</a>
                        </div>
                      </div>
                    </div>
                    
                    <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                      <div class="item">
                        <div class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">
                            <div class="center text-center m-t-n">
                            </div>
                            <div class="bottom padder m-b-sm">
                              <a href="#" class="pull-right" data-toggle="class">
                                <i class="fa fa-heart-o text"></i>
                                <i class="fa fa-heart text-active text-danger"></i>
                              </a>
                              <a href="#" data-toggle="class">
                                <!-- 从购物车移除该照片 -->
                                <i class="fa fa-trash-o"></i>
                              </a>
                            </div>
                          </div>
                          <a href="#"><img src="PersonCenter/images/o2.jpg" alt="" class="r r-2x img-full"></a>
                        </div>
                        <div class="padder-v">
                          <a href="#" class="text-ellipsis">Morbi id neque quam liquam sollicitudin</a>
                          <a href="#" class="text-ellipsis text-xs text-muted">Allen JH</a>
                        </div>
                      </div>
                    </div>
                    
                    <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                      <div class="item">
                        <div class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">
                            <div class="center text-center m-t-n">
                            </div>
                            <div class="bottom padder m-b-sm">
                              <a href="#" class="pull-right" data-toggle="class">
                                <i class="fa fa-heart-o text"></i>
                                <i class="fa fa-heart text-active text-danger"></i>
                              </a>
                              <a href="#" data-toggle="class">
                                <!-- 从购物车移除该照片 -->
                                <i class="fa fa-trash-o"></i>
                              </a>
                            </div>
                          </div>
                          <a href="#"><img src="PersonCenter/images/o11.jpg" alt="" class="r r-2x img-full"></a>
                        </div>
                        <div class="padder-v">
                          <a href="#" class="text-ellipsis">Tincidunt libero</a>
                          <a href="#" class="text-ellipsis text-xs text-muted">Amanda Conlan</a>
                        </div>
                      </div>
                    </div>

                    <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                      <div class="item">
                        <div class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">
                            <div class="center text-center m-t-n">
                            </div>
                            <div class="bottom padder m-b-sm">
                              <a href="#" class="pull-right" data-toggle="class">
                                <i class="fa fa-heart-o text"></i>
                                <i class="fa fa-heart text-active text-danger"></i>
                              </a>
                              <a href="#" data-toggle="class">
                                <!-- 从购物车移除该照片 -->
                                <i class="fa fa-trash-o"></i>
                              </a>
                            </div>
                          </div>
                          <a href="#"><img src="PersonCenter/images/o4.jpg" alt="" class="r r-2x img-full"></a>
                        </div>
                        <div class="padder-v">
                          <a href="#" class="text-ellipsis">Beijing Marathon</a>
                          <a href="#" class="text-ellipsis text-xs text-muted">Nisa Colen</a>
                        </div>
                      </div>
                    </div>
                    <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                      <div class="item">
                        <div class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">
                            <div class="center text-center m-t-n">
                            </div>
                            <div class="bottom padder m-b-sm">
                              <a href="#" class="pull-right" data-toggle="class">
                                <i class="fa fa-heart-o text"></i>
                                <i class="fa fa-heart text-active text-danger"></i>
                              </a>
                              <a href="#" data-toggle="class">
                                <!-- 从购物车移除该照片 -->
                                <i class="fa fa-trash-o"></i>
                              </a>
                            </div>
                          </div>                          
                          <a href="#"><img src="PersonCenter/images/o5.jpg" alt="" class="r r-2x img-full"></a>
                        </div>
                        <div class="padder-v">
                          <a href="#" class="text-ellipsis">Shanghai Marathon</a>
                          <a href="#" class="text-ellipsis text-xs text-muted">Dan Doorack</a>
                        </div>
                      </div>
                    </div>

                    <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                      <div class="item">
                        <div class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">
                            <div class="center text-center m-t-n">
                            </div>
                            <div class="bottom padder m-b-sm">
                              <a href="#" class="pull-right" data-toggle="class">
                                <i class="fa fa-heart-o text"></i>
                                <i class="fa fa-heart text-active text-danger"></i>
                              </a>
                              <a href="#" data-toggle="class">
                                <!-- 从购物车移除该照片 -->
                                <i class="fa fa-trash-o"></i>
                              </a>
                            </div>
                          </div>
                          <a href="#"><img src="PersonCenter/images/o6.jpg" alt="" class="r r-2x img-full"></a>
                        </div>
                        <div class="padder-v">
                          <a href="#" class="text-ellipsis">Vivamus vel tincidunt libero</a>
                          <a href="#" class="text-ellipsis text-xs text-muted">Ligula H</a>
                        </div>
                      </div>
                    </div>
                  </div>
                </section>
              </section>
            </section>
            
            <!-- / side content -->
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
        </section>
	
  	<footer>
  	<div class="btn-group btn-group-justified">
	<a href="findPicture.jsp" class="btn btn-primary">照片查看</a>
	<a href="#" class="btn btn-success">购物车</a>
	</div>
  	</footer>
  </section>
  
  
  
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