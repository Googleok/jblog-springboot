<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id="header">
			<c:choose>
				<c:when test="${empty blogVo }">
					<h1><a href="${pageContext.request.contextPath}/${authUser.id}" style="color: white;">Back Home!</a></h1>
				</c:when>
				<c:otherwise>	
					<h1><a href="${pageContext.request.contextPath}/${authUser.id}" style="color: white;">${blogVo.title}</a></h1>
				</c:otherwise>
			</c:choose>
			<ul>
				<c:choose>
					<c:when test='${empty authUser }'>
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					</c:when>
					<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">블로그 관리</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
