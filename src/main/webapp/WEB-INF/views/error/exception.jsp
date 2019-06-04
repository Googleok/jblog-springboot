<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	background-color: fuchsia;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center" style="margin-top: 260px;">
		<h1 style="font-size: 100px;">Oooooooops!</h1>
		<h2>${uri }</h2>
		<p>
			죄송합니다. <br> 서비스 장애가 발생했습니다.<br> ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
		</p>
	</div>
	<pre style="color: white; font-weight: bold;">
		${exception }
	</pre>
</body>
</html>