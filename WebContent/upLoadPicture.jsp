<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.EasyMarathon.bean.SNSUserInfo"%>
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
	<link rel="stylesheet" type="text/css" href="css/default.css">
    <link href="css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet"> 
	<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
<title>我要上传</title>
</head>
<body>
	<% 
		// 获取由OAuthServlet中传入的参数
		SNSUserInfo user = (SNSUserInfo)request.getAttribute("snsUserInfo"); 
		if(null != user) {
	%>
	<!-- 
	以下是获取用户信息的方法，按需要你们用吧=。=
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenID</td><td><%=user.getOpenId()%></td></tr>
		<tr><td>昵称</td><td><%=user.getNickname()%></td></tr>
		<tr><td>性别</td><td><%=user.getSex()%></td></tr>
		<tr><td>国家</td><td><%=user.getCountry()%></td></tr>
		<tr><td>省份</td><td><%=user.getProvince()%></td></tr>
		<tr><td>城市</td><td><%=user.getCity()%></td></tr>
		<tr><td>头像</td><td><%=user.getHeadImgUrl()%></td></tr>
		<tr><td>特权</td><td><%=user.getPrivilegeList()%></td></tr>
	</table>
	 -->
	<%
		}
		else 
			out.print("用户不同意授权,未获取到用户信息！");
	%>

	<form enctype="multipart/form-data" action="uploadPic" method="post" >

	<div class="htmleaf-container" style="min-height:300px">
		<div class="container kv-main">
			<br>
       		<input id="file-0a" class="file" type="file" multiple data-min-file-count="1" name="picture.file">
           	<br>
           	
           	<div class="form-group">
				<label for="name">赛事所在地</label>
				<select class="form-control" name="eventID">
			         <option>1</option>
			         <option>2</option>
			         <option>3</option>
			         <option>4</option>
			         <option>5</option>
				</select>
           	</div>

			<button type="submit" class="btn btn-primary">提交</button>
        </div>
	</div>

	</form>
	 
	<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/fileinput.js" type="text/javascript"></script>
    <script src="js/fileinput_locale_zh.js" type="text/javascript"></script>
    <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script>
	    $("#file-0").fileinput({
	        'allowedFileExtensions' : ['jpg', 'png','gif'],
	    });
	    $("#file-1").fileinput({
	        uploadUrl: '#', // you must set a valid URL here else you will get an error
	        allowedFileExtensions : ['jpg', 'png','gif'],
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
</body>
</html>