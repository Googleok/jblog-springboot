<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
	<c:import url='/WEB-INF/views/includes/header.jsp' />
		<form id="login-form" class="login-form" name="loginform" method="post" 
			action="${pageContext.servletContext.contextPath}/user/auth">
			<label>아이디</label> <input type="text" name="id"> <label>패스워드</label>
			<input type="text" name="password">

			<c:choose>
				<c:when test="${param.result eq 'fail'}">
					<p style="color: red; font-style: bold;">로그인이 실패 했습니다.</p>
				</c:when>

				<c:otherwise>
				</c:otherwise>
			</c:choose>

			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
