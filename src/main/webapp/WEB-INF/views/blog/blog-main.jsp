<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/blog/includes/header.jsp' />	
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				<c:choose>
					<c:when test="${empty postOne}">
						<strong style="font-size: 15px; color: red;">해당 게시물이 존재하지 않습니다.</strong>
					</c:when>
					<c:otherwise>
						<h4>${postOne.title}</h4>
						<p>
							${fn:replace(postOne.contents, newline, "<br>") }	
						<p>
					</c:otherwise>
				</c:choose>
				</div>
				<ul class="blog-list">
				<c:choose>
					<c:when test="${fn:length(postList) == 0}">
						<strong style="font-size: 15px; color: red;">해당 카테고리가 존재하지 않거나</strong>
						<strong style="font-size: 15px; color: red;">해당 카테고리에 게시물이 존재하지 않습니다.</strong>
					</c:when>
					<c:otherwise>
						<c:forEach items="${postList}" var="vo" varStatus="status">
							<li><a href="${pageContext.request.contextPath}/${authUser.id}/${vo.categoryNo }/${vo.no}">${vo.title }</a> <span>${vo.regDate }</span>	</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/assets-upload${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList}" var="vo" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/${vo.no}">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/blog/includes/footer.jsp"/>
	</div>
</body>
</html>