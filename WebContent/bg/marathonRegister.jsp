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
<body onload="init();">
<%
	SNSUserInfo user = (SNSUserInfo)session.getAttribute("snsUserInfo"); 
%>
<section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info nav-xs">
        <a href="index.html" class="navbar-brand text-lt">
          <span class="hidden-nav-xs m-l-sm">完善信息</span>
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
                  <form data-validate="parsley" enctype="multipart/form-data" action="MarathonRegister" method="POST">
                    <section class="panel panel-default">
                      <header class="panel-heading">
                        <span class="h4">完善信息</span>
                      </header>
                      <div class="panel-body">
	                        <div class="form-group">
	                          <label>姓名</label>
	                          <input type="text"  class="form-control" data-required="true" name="user.userName"/>                        
	                        </div>  
	                        <div class="form-group">
	                          <label>邮箱</label>
	                        <input type="text"  class="form-control" data-type="email" data-required="true" name="user.email"/>                        
	                        </div>
	                        <div class="form-group">
	                          <label>联系方式</label>
	                          <input type="text"  class="form-control" data-type="phone" placeholder="请输入手机号" data-required="true" name="user.celphone"/>
	                        </div>
	                        <div class="form-group">
	                          <label>身份证号</label>
	                          <input type="text" class="form-control" data-required="true" name="user.identityCard"/>
	                        </div>
	                        <div class="form-group">
	                          <label>单寸照</label>
	                          <input id="file-0" class="file" type="file" name="user.identityPic">
	                        </div>
	                        <div class="form-group">
	                          <label>血型</label>
		                      <select id="bloodType" name="user.bloodType" class="form-control"> 
								<option value="A">A</option>
								<option value="B">B</option> 
								<option value="O">O</option>
								<option value="AB">AB</option>
							  </select>
	                        </div>
	                        <div class="form-group">
	                          <label>身高</label>
	                          <input type="text" class="form-control" data-required="true" name="user.height"/>
	                        </div>
	                        <div class="form-group">
	                          <label>体重</label>
	                          <input type="text" class="form-control" data-required="true" name="user.weight"/>
	                        </div>
	                        <div class="form-group">
	                          <label>所在地区</label>
		                      <select id="country" name="country" onchange="toProvince();" class="form-control m-b" data-required="true"> 
								<option value="-1">--国家---</option> 
							  </select> 
							  <select id="province" name="province" onchange="toCity();" class="form-control m-b" data-required="true"> 
							  	<option value="-1">--省份---</option> 
							  </select> 
							  <select id="city" name="city" class="form-control m-b" data-required="true"> 
							  	<option value="-1">--市/区---</option> 
							  </select>
		                   </div>
		                   <div class="form-group">
	                          <label>紧急联系人姓名</label>
	                          <input type="text" class="form-control" data-required="true" name="user.urgencyContact"/>
	                        </div>
	                        <div class="form-group">
	                          <label>紧急联系人电话</label>
	                          <input type="text" class="form-control" data-required="true" name="user.urgencyPhone"/>
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
                        	<button type="submit" class="btn btn-success btn-s-xs">提交</button>
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