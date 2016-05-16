<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.EasyMarathon.bean.SNSUserInfo"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="bg/css/default.css">
<link href="bg/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="bg/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="bg/PersonCenter/css/animate.css" type="text/css" />
<link rel="stylesheet" href="bg/PersonCenter/css/font-awesome.min.css" type="text/css" />
<link rel="stylesheet" href="bg/PersonCenter/css/simple-line-icons.css" type="text/css" />
<link rel="stylesheet" href="bg/PersonCenter/css/font.css" type="text/css" />
<link rel="stylesheet" href="bg/PersonCenter/css/app.css" type="text/css" /> 
<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
<title>摄影师上传</title>
</head>
<body>
	<% 
	Map<Integer, String> events=new HashMap<Integer, String>();
	Map<Integer, String> event=(Map<Integer, String>)request.getAttribute("event");
	%>

 	<form enctype="multipart/form-data" data-validate="parsley" action="MasterUploadPic" method="post">
		<div class="htmleaf-container" style="min-height: 300px">
			<div class="container kv-main">
 				<br>   
				<div class="row">
				<div class="col-sm-6">
				<section class="panel panel-default">
				<header class="panel-heading">
                        <span class="h4">摄影师上传照片</span>
                        <h5 class="h6"></h5>
                </header>
				<div class="panel-body">
				<div class="form-group">
					<label>摄影师姓名</label>
                    <input type="text" data-required="true" class="form-control" name="authorName"/>
				</div>
				<div class="form-group">
					<label>价格</label>
                    <input type="text" data-required="true" class="form-control" name="price"/>
				</div>
				
				<div class="form-group">
					<label for="name">赛事所在地</label> <select data-required="true" class="form-control"
						name="eventID">	
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
				<input id="file-1" class="file" type="file" name="picture"> <br>
				<button type="submit" class="btn btn-primary pull-left">&nbsp;&nbsp;提交&nbsp;&nbsp;</button>
				</div>
				</div>
				</section>
				</div>
			</div>
		</div>

	</form>

	<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js"></script> 
	<script src="bg/js/fileinput.js" type="text/javascript"></script>	
	<script src="bg/js/fileinput_locale_zh.js" type="text/javascript"></script>	
	<script src="bg/bootstrap-3.3.5-dist/js/bootstrap.min.js"	type="text/javascript"></script> 	
	<script>
	    $("#file-0").fileinput({
	        'allowedFileExtensions' : ['jpg','jpeg', 'png','gif'],
	        maxFilesNum: 10,
	    });
	    $("#file-1").fileinput({
	        uploadUrl: '#', // you must set a valid URL here else you will get an error
	        allowedFileExtensions : ['jpeg', 'jpg', 'png','gif'],
	        overwriteInitial: false,
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
	    /*
	    $('#file-4').on('fileselectnone', function() {
	        alert('Huh! You selected no files.');
	    });
	    $('#file-4').on('filebrowse', function() {
	        alert('File browse clicked for #file-4');
	    });
	    */
	    $(document).ready(function() {
	        $("#test-upload").fileinput({
	            'showPreview' : false,
	            'allowedFileExtensions' : ['jpg', 'png','gif'],
	            'elErrorContainer': '#errorBlock'
	        });
	        /*
	        $("#test-upload").on('fileloaded', function(event, file, previewId, index) {
	            alert('i = ' + index + ', id = ' + previewId + ', file = ' + file.name);
	        });
	        */
	    });
		</script>
<script src="bg/PersonCenter/js/jquery.min.js"></script>
<!-- parsley -->
<script src="bg/PersonCenter/js/parsley/parsley.min.js"></script>
<script src="bg/PersonCenter/js/parsley/parsley.extend.js"></script>
<script src="bg/PersonCenter/js/app.plugin.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/add-on/jplayer.playlist.min.js"></script>
<script type="text/javascript" src="bg/PersonCenter/js/jPlayer/demo.js"></script>
</body>
</html>