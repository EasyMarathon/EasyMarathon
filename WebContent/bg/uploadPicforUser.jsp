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
<link rel="stylesheet" type="text/css" href="css/default.css">
<link href="bg/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="bg/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
<title>我要上传</title>
</head>
<body>
	<% 
	Map<Integer, String> events=new HashMap<Integer, String>();
	Map<Integer, String> event=(Map<Integer, String>)request.getAttribute("event");
		// 获取由OAuthServlet中传入的参数
		SNSUserInfo user = (SNSUserInfo)session.getAttribute("snsUserInfo"); 
		/* System.out.println(user.getCity());
		System.out.println(user.getOpenId()); */
		System.out.println("用户上传界面");
	%>
	<%-- <table width="100%" cellspacing="0" cellpadding="0">
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenID</td><td><%=user.getOpenId()%></td></tr>
		<tr><td>昵称</td><td><%=user.getNickname()%></td></tr>
		<tr><td>性别</td><td><%=user.getSex()%></td></tr>
		<tr><td>国家</td><td><%=user.getCountry()%></td></tr>
		<tr><td>省份</td><td><%=user.getProvince()%></td></tr>
		<tr><td>城市</td><td><%=user.getCity()%></td></tr>
		<tr><td>头像</td><td><%=user.getHeadImgUrl()%></td></tr>
		<tr><td>特权</td><td><%=user.getPrivilegeList()%></td></tr>
	</table>  --%>

 	<form enctype="multipart/form-data" action="UserUpload" method="post">
		<div class="htmleaf-container" style="min-height: 300px">
			<div class="container kv-main">
 				<br> <input id="file-1" class="file" type="file" name="picture"> <br>  

				<div class="form-group">
					<div style="display:none">
						<input name="wechatID" type="hidden" value="<%=user.getOpenId()%>" />
					</div>
					<label for="name">赛事所在地</label> <select class="form-control"
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

				<button type="submit" class="btn btn-primary pull-right">&nbsp;&nbsp;提交&nbsp;&nbsp;</button>
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
</body>
</html>