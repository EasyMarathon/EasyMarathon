<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.EasyMarathon.bean.PicBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.io.File"%>
<%@ page import="com.EasyMarathon.bean.PicBean.Status"%>
<%@ page import="org.apache.struts2.ServletActionContext"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
<meta charset="utf-8" />
<title>照片查看</title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="PersonCenter/js/jPlayer/jplayer.flat.css"
	type="text/css" />
<link rel="stylesheet" href="PersonCenter/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="PersonCenter/css/animate.css"
	type="text/css" />
<link rel="stylesheet" href="PersonCenter/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="PersonCenter/css/font-awesome-ie7.min.css">
<link rel="stylesheet" href="PersonCenter/css/simple-line-icons.css"
	type="text/css" />
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
		<header
			class="bg-white-only header header-md navbar navbar-fixed-top-xs">
			<div class="navbar-header aside bg-info nav-xs">
				<a href="index.html" class="navbar-brand text-lt"> <span
					class="hidden-nav-xs m-l-sm">Photo Club</span>
				</a> <a class="btn btn-link visible-xs" data-toggle="dropdown"
					data-target=".user"> <i class="icon-settings"></i>
				</a>
			</div>
			<ul class="nav navbar-nav hidden-xs">
				<li><a href="#nav,.navbar-header"
					data-toggle="class:nav-xs,nav-xs" class="text-muted"> <i
						class="fa fa-indent text"></i> <i class="fa fa-dedent text-active"></i>
				</a></li>
			</ul>
			<form
				class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs"
				role="search">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-btn">
							<button type="submit"
								class="btn btn-sm bg-white btn-icon rounded">
								<i class="fa fa-search"></i>
							</button>
						</span> <input type="text"
							class="form-control input-sm no-border rounded"
							placeholder="Search...">
					</div>
				</div>
			</form>
			<div class="navbar-right ">
				<ul class="nav navbar-nav m-n hidden-xs nav-user user">
					<li class="hidden-xs"><a href="#" class="dropdown-toggle lt"
						data-toggle="dropdown"> <i class="icon-bell"></i> <span
							class="badge badge-sm up bg-danger count">2</span>
					</a>
						<section class="dropdown-menu aside-xl animated fadeInUp">
							<section class="panel bg-white">
								<div class="panel-heading b-light bg-light">
									<strong>You have <span class="count">2</span>
										notifications
									</strong>
								</div>
								<div class="list-group list-group-alt">
									<a href="#" class="media list-group-item"> <span
										class="pull-left thumb-sm"> <img
											src="PersonCenter/images/a0.png" alt="..." class="img-circle">
									</span> <span class="media-body block m-b-none"> Use awesome
											animate.css<br> <small class="text-muted">10
												minutes ago</small>
									</span>
									</a> <a href="#" class="media list-group-item"> <span
										class="media-body block m-b-none"> 1.0 initial released<br>
											<small class="text-muted">1 hour ago</small>
									</span>
									</a>
								</div>
								<div class="panel-footer text-sm">
									<a href="#" class="pull-right"><i class="fa fa-cog"></i></a> <a
										href="#notes" data-toggle="class:show animated fadeInRight">See
										all the notifications</a>
								</div>
							</section>
						</section></li>
					<li class="dropdown"><a href="#"
						class="dropdown-toggle bg clear" data-toggle="dropdown"> <span
							class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm">
								<img src="PersonCenter/images/a0.png" alt="...">
						</span> John.Smith <b class="caret"></b>
					</a>
						<ul class="dropdown-menu animated fadeInRight">
							<li><span class="arrow top"></span> <a href="#">设置</a></li>
							<li><a href="#"> <span
									class="badge bg-danger pull-right">3</span>消息
							</a></li>
							<li><a href="docs.html">帮助</a></li>
							<li class="divider"></li>
							<li><a href="modal.lockme.html" data-toggle="ajaxModal">注销</a>
							</li>
						</ul></li>
				</ul>
			</div>
		</header>

		<!-- /.aside -->
		<section id="content">
			<section class="hbox stretch">
				<section>
					<section class="vbox">
						<section class="scrollable padder-lg w-f-md" id="bjax-target">
							<a href="#" class="pull-right text-muted m-t-lg"
								data-toggle="class:fa-spin"><i
								class="icon-refresh i-lg  inline" id="refresh"></i></a>
							<h2 class="font-thin m-b">
								照片查看 <span class="bar1 a1 bg-primary lter"></span> <span
									class="bar2 a2 bg-info lt"></span> <span
									class="bar3 a3 bg-success"></span> <span
									class="bar4 a4 bg-warning dk"></span> <span
									class="bar5 a5 bg-danger dker"></span>
							</h2>
							<div class="row row-sm">
								<%
									ArrayList<PicBean> pictures = (ArrayList<PicBean>)session.getAttribute("pictures");									
									int eventID=(int)session.getAttribute("eventID");
									System.out.println(pictures.size());
									System.out.print(eventID);
									int start = 0;
									if (start >= 0) {
										while (start < pictures.size()) {
												PicBean picbean = (PicBean) pictures.get(start);
												String pID = picbean.getPicID();
												String author = picbean.getAuthor();
												int price = picbean.getPrice();
												
												String path="imageCamera" +"\\"+eventID;
											
											start++;
											if (PicBean.Status.hasBuy==picbean.getPicStatus()) {
												path=path+"\\"+"initial" + "\\"+pID+".jpg";
												System.out.println(path);
								%>
								<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
									<div class="item">
										<div class="pos-rlt">
											<div class="item-overlay opacity r r-2x bg-black">
												<div class="text-info padder m-t-sm text-sm">
													<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
														class="fa fa-star"></i> <i class="fa fa-star"></i> <i
														class="fa fa-star-o text-muted"></i>
												</div>
												<div class="center text-center m-t-n"></div>
												<div class="bottom padder m-b-sm">
													<a href="#" class="pull-right" data-toggle="class"> <i
														class="fa fa-heart-o text"></i> <i
														class="fa fa-heart text-active text-danger"></i>
													</a> <a href="#" data-toggle="modal" data-target="<%="i"+pID%>">
														<i class="icon-basket"></i> <i
														class="fa fa-check-circle text-active text-info"></i>
													</a>
												</div>
											</div>
											<a href="#"><img src="<%=path %>" alt=""
												class="r r-2x img-full"></a>
										</div>
										<div class="padder-v">
											<a href="#" class="text-ellipsis">Morbi id neque quam
												liquam sollicitudin</a> <a href="#"
												class="text-ellipsis text-xs text-muted"><%=author %></a>
										</div>
									</div>
								</div>
								
								
								
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="<%="i"+pID%>" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">照片查看</h4>
				</div>
				<div class="post-media">
					<img src="<%=path %>" class="img-full">
				</div>
				<div class="modal-body">
					设计者：<%=author %> ,价格：<%=price %><br>
					<div class="checkbox i-checks">
						<label> <input type="checkbox" name="check" checked
							data-required="true"><i></i>我同意遵守 <a href="#"
							class="text-info" data-toggle="modal" data-target="#buyer">《易跑平台照片购买条款》</a>
						</label>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">支付</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
								<%
									}else
									{
										path=path+"\\"+"watermark" + "\\"+pID+".jpg";
										System.out.println(path);
										%>

								<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
									<div class="item">
										<div class="pos-rlt">
											<div class="item-overlay opacity r r-2x bg-black">
												<div class="text-info padder m-t-sm text-sm">
													<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
														class="fa fa-star"></i> <i class="fa fa-star"></i> <i
														class="fa fa-star-o text-muted"></i>
												</div>
												<div class="center text-center m-t-n"></div>
												<div class="bottom padder m-b-sm">
													<a href="#" class="pull-right" data-toggle="class"> <i
														class="fa fa-heart-o text"></i> <i
														class="fa fa-heart text-active text-danger"></i>
													</a> <a href="#" data-toggle="modal" data-target="<%="w"+pID%>">
														<i class="icon-basket"></i> <i
														class="fa fa-check-circle text-active text-info"></i>
													</a>
												</div>
											</div>
											<a href="#"><img src="<%=path %>" alt=""
												class="r r-2x img-full"></a>
										</div>
										<div class="padder-v">
											<a href="#" class="text-ellipsis">Morbi id neque quam
												liquam sollicitudin</a> <a href="#"
												class="text-ellipsis text-xs text-muted"><%=author %></a>
										</div>
									</div>
								</div>

<!-- 模态框（Modal） -->
	<div class="modal fade" id="<%="w"+pID%>" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">照片查看</h4>
				</div>
				<div class="post-media">
					<img src="<%=path %>)" class="img-full">
				</div>
				<div class="modal-body">
					设计者：<%=author %> ,价格：<%=price %><br>
					<div class="checkbox i-checks">
						<label> <input type="checkbox" name="check" checked
							data-required="true"><i></i>我同意遵守 <a href="#"
							class="text-info" data-toggle="modal" data-target="#buyer">《易跑平台照片购买条款》</a>
						</label>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">支付</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
								<%
									}
										}
									}
								%>
							</div>
						</section>
					</section>
				</section>

				<!-- / side content -->
			</section>
			<a href="#" class="hide nav-off-screen-block"
				data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
		</section>

	</section>

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
	<script type="text/javascript"
		src="PersonCenter/js/jPlayer/jquery.jplayer.min.js"></script>
	<script type="text/javascript"
		src="PersonCenter/js/jPlayer/add-on/jplayer.playlist.min.js"></script>
	<script type="text/javascript" src="PersonCenter/js/jPlayer/demo.js"></script>

</body>
</html>