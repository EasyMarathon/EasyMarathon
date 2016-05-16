<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.EasyMarathon.bean.ConfirmData"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>信息审核</title>
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
  <link rel="stylesheet" href="bg/test.css" type="text/css" />
</head>
<body class="">
<%
	ArrayList<ConfirmData> dataList = (ArrayList<ConfirmData>) session.getAttribute("dataList");
	System.out.println("List Size = "+dataList.size());
%>
  <section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info nav-xs">
        <a class="navbar-brand text-lt">
          <span class="hidden-nav-xs m-l-sm">报名情况审核</span>
        </a>
      </div>     
    </header>
    <section>
      <section class="hbox stretch">
        <section id="content">
          <section class="hbox stretch">
            <section>
              <section class="vbox">  
                 <div class="tab-content">
                        <div class="tab-pane active" id="activity">
                          <ul  class="list-group no-radius m-b-none m-t-n-xxs list-group-lg no-border">
                            <%for(int i=0;i<dataList.size();i++){ %>
                            <li  class="list-group-item">
                            <form action="ConfirmInfoReject" method="post">
                            <a href="#" data-toggle="modal" data-target="#<%=i %>" class="thumb-lb pull-left m-r-sm">
                                <%	String Path = "bg/UserPicture/UserInfo/"+dataList.get(i).getIdentityPic();
                            		System.out.println("证件照存储路径="+Path);
                            	%>
                                <img src="<%=Path %>" >
                              </a>
                              <a class="clear">
                              
                                <strong >姓名：<%=dataList.get(i).getName() %></strong>
                                <small class="pull-right" style="color:#fb3504;">待审核</small>
                                <br><br>
                                <small>报名赛事：<%=dataList.get(i).getEventName() %></small><br>
                                <small>身份证号：<%=dataList.get(i).getIDcard() %></small><br>
                                <small>联系方式：<%=dataList.get(i).getPhone() %></small><br>
                                <small>紧急联系人：<%=dataList.get(i).getUrgencyName() %></small><br>
                                <small>紧急联系人号码：<%=dataList.get(i).getUrgencyPhone() %></small><br>
                                <div style="display:none">
                				<input type="text" name="eventID" value="<%=dataList.get(i).getEventID() %>">
								</div>
								<div style="display:none">
                				<input type="text" name="wechatID" value="<%=dataList.get(i).getWechatID() %>">
								</div>
								<small class="pull-right">
								<button type="submit" class="btn btn-danger btn-s-xs">拒绝审核</button>
                                <button type="button" data-toggle="modal" data-target="#R<%=i %>" class="btn btn-success btn-s-xs">通过审核</button>
								</small>
                              </a>
                              </form>
                            </li>
							<%} %>
                          </ul>
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
  
<!-- 模态框 -->
<%for(int i=0;i<dataList.size();i++){ %>
<div class="modal fade" id="<%=i %>" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">证件照</h4>
         </div>
         <div class="post-media">
			 <%	String Path = "bg/UserPicture/UserInfo/"+dataList.get(i).getIdentityPic();
         	 %>
             <img src="<%=Path %>" class="img-full">
         </div>
         
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<div class="modal fade" id="R<%=i %>" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <form class="form-horizontal" method="post" action="ConfirmInfo2">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">绑定号码牌</h4>
         </div>
         <div class="modal-body">
		 	<div class="form-group">
				<label class="col-sm-2 control-label">号码牌：</label><br>
				<div class="col-sm-10">
					<input type="text" class="form-control rounded" name="AthleteID">
				</div>
				<div style="display:none">
                	<input type="text" name="eventID" value="<%=dataList.get(i).getEventID() %>">
				</div>
				<div style="display:none">
                	<input type="text" name="wechatID" value="<%=dataList.get(i).getWechatID() %>">
				</div>
			</div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="submit" class="btn btn-primary" >提交</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</form>
</div>
<%} %>

<script src="bg/PersonCenter/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="bg/PersonCenter/js/bootstrap.js"></script>
<!-- App -->
<script src="bg/PersonCenter/js/app.js"></script>  
<script src="bg/PersonCenter/js/slimscroll/jquery.slimscroll.min.js"></script>
<script src="bg/PersonCenter/js/app.plugin.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/add-on/jplayer.playlist.min.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/demo.js"></script>
</body>
</html>