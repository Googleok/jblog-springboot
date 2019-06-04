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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/image-preview.js"></script>
<style type="text/css">
	.img_wrap {
		width: 300px;
		margin-top: 50px;
	}

	.img_wrap img{
		max-width: 100%;
	}
</style>
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/blog/includes/header.jsp' />	
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url='/WEB-INF/views/blog/includes/admin-menu.jsp'>
					<c:param name="menu" value="setting" />
				</c:import>
				<form
					action="${pageContext.request.contextPath}/${authUser.id}/admin/basic"
					method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${blogId}">
					<table class="admin-config">
						<tr>
							<td class="t">블로그 제목</td>
							<td><input type="text" size="40" name="title"></td>
						</tr>
						<tr>
							<td class="t">로고이미지</td>
							<td class="img_wrap"><img id="logo-img"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td><input type="file" name="logo-file" id="logo-file"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td class="s"><input type="submit" id="submit_button" value="기본설정 변경"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/includes/footer.jsp"/>
	</div>
</body>
</html>