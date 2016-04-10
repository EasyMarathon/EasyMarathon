<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>上传照片页面</title>
</head>
<body>

	<h1>上传照片页面</h1>
	<form action="uploadPic" name="uploadPic" method="post" >
		<input type="file" name="picture">
		<input type="text" name="eventID">
		<input type="submit" value="下一步">
	</form>

	<p>Copyright &copy; 2015 我帮你项目开发小组</p>

</body>
</html>