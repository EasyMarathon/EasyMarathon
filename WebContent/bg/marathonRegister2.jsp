<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.*"%>
<%@ page import="com.EasyMarathon.bean.SNSUserInfo"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>赛事选择</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="bg/PersonCenter/js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/font.css" type="text/css" />
  <link rel="stylesheet" href="bg/PersonCenter/css/app.css" type="text/css" />  
  <link href="bg/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
  <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
<script src="bg/PersonCenter/js/jquery.min.js"></script>
<script type="text/javascript" src="bg/js/city_choose.js"></script>
</head>
<body>
<%
	Map<Integer, String> events=new HashMap<Integer, String>();
	Map<Integer, String> event=(Map<Integer, String>)request.getAttribute("event");
	SNSUserInfo user = (SNSUserInfo)session.getAttribute("snsUserInfo"); 
%>
<section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info nav-xs">
        <a href="index.html" class="navbar-brand text-lt">
          <span class="hidden-nav-xs m-l-sm">赛事选择</span>
        </a>
      </div>      
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
      
    </header>
    <section>
      <section class="hbox stretch">
        <!-- .aside -->
          <section class="vbox">
            <section class="scrollable padder">
              <div class="m-b-md">
                <h3 class="m-b-none"></h3>
              </div>
              <div class="row">
                <div class="col-sm-6">
                  <form data-validate="parsley" action="MarathonRegister3" method="POST">
                    <section class="panel panel-default">
                      <header class="panel-heading">
                        <span class="h4">赛事选择</span><br>
                        <label style="color:#ff6633;margin-top:5px">
                        	请从下列已开赛的赛事中选择一项您要参加的马拉松赛事，点击"报名"按钮后即完成报名
                        </label>
                      </header>
                      <div class="panel-body">
	                  	 <div class="form-group">
							<select class="form-control" name="eventID">	
							<%
								for (Map.Entry<Integer, String> entry : event.entrySet()) {  						  
									String eventName= entry.getValue();  		
									int eventID1= entry.getKey();
									String aa=Integer.toString(eventID1);
									/* System.out.println(eventID1); */						
				         	%> 
								<option value="<%=aa %>"><%=eventName %></option> 
							<%
								}
				         	%>
							</select> 
					       </div>
					       
		                   <div style="display:none"><!-- 微信号，主键 -->
								<input value="<%=user.getOpenId()%>" type="hidden" value="1" id="txt_width" name="user.wechatID"/>
							</div>
							<div class="form-group">
	                        <div class="checkbox i-checks">
	                          <label>
	                            <input type="checkbox" name="check" checked data-required="true"><i></i>我同意遵守 <a href="#" class="text-info">《易跑平台服务条款》</a>
	                          </label>
	                        </div>
                        	</div>
		              </div>
                        </section>
                        	<button type="submit" class="btn btn-success btn-s-xs">报名</button>
                        </form>
                        </div>
                        </div>	
                        </section>
                    </section>
                </section>
                <footer class="panel-footer text-right bg-light lter"></footer>
			</section>
		</section>

<script>
	    $("#file-0").fileinput({
	        'allowedFileExtensions' : ['jpg','jpeg', 'png','gif'],
	        maxFilesNum: 10,
	    });
	    $("#file-1").fileinput({
	        uploadUrl: '#', // you must set a valid URL here else you will get an error
	        allowedFileExtensions : ['jpeg', 'jpg', 'png','gif'],
	        overwriteInitial: false,
	        maxFileSize: 1000,
	        maxFilesNum: 10,
	        //allowedFileTypes: ['image', 'video', 'flash'],
	        slugCallback: function(filename) {
	            return filename.replace('(', '_').replace(']', '_');
	        }
		});
	    /*
	    $(".file").on('fileselect', function(event, n, l) {
	        alert('File Selected. Name: ' + l + ', Num: ' + n);
	    });
	    */
		$("#file-3").fileinput({
			showUpload: false,
			showCaption: false,
			browseClass: "btn btn-primary btn-lg",
			fileType: "any",
	        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
		});
		$("#file-4").fileinput({
			uploadExtraData: {kvId: '10'}
		});
	    $(".btn-warning").on('click', function() {
	        if ($('#file-4').attr('disabled')) {
	            $('#file-4').fileinput('enable');
	        } else {
	            $('#file-4').fileinput('disable');
	        }
	    });    
	    $(".btn-info").on('click', function() {
	        $('#file-4').fileinput('refresh', {previewClass:'bg-info'});
	    });
	    $(document).ready(function() {
	        $("#test-upload").fileinput({
	            'showPreview' : false,
	            'allowedFileExtensions' : ['jpg', 'png','gif'],
	            'elErrorContainer': '#errorBlock'
	        });
	    });
		</script>

<script src="bg/js/fileinput.js" type="text/javascript"></script>	
<script src="bg/js/fileinput_locale_zh.js" type="text/javascript"></script>	
<!-- Bootstrap -->
<script src="bg/bootstrap-3.3.5-dist/js/bootstrap.min.js"	type="text/javascript"></script>

<!-- App -->
<script src="bg/PersonCenter/js/app.js"></script>  
<script src="bg/PersonCenter/js/slimscroll/jquery.slimscroll.min.js"></script>
<!-- parsley -->
<script src="bg/PersonCenter/js/parsley/parsley.min.js"></script>
<script src="bg/PersonCenter/js/parsley/parsley.extend.js"></script>
<script src="bg/PersonCenter/js/app.plugin.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/add-on/jplayer.playlist.min.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/demo.js"></script>

</body>
</html>