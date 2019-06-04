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
	background-color: lime;
}

.img {
	position: relative;
	height: 100vh;
	background-size: cover;
}

.img-cover {
	position: absolute;
	height: 40%;
	width: 100%;
	z-index: 1;
}

.img .content {
	color: white;
	z-index: 2;
	text-align: center;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="img">
		<div class="content" style="height: 60%">
			<h1 style="font-size: 100px;">Oooooooops!</h1>
			<p>
				죄송합니다. <br> 서비스 장애가 발생했습니다.<br> 잠시후, 다시 이용해 주세요.
			</p>
		</div>
		<img class="img-cover" alt=""
			src="${pageContext.servletContext.contextPath }/assets/images/rainbow.png">
	</div>
</body>
</html>