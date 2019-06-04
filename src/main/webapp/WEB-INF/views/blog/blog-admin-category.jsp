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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/blog/includes/header.jsp' />	
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url='/WEB-INF/views/blog/includes/admin-menu.jsp'>
					<c:param name="menu" value="category" />
				</c:import>
				<!-- DB List 띄어주기 -->
				<div id="categoryList">
		      	</div>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<form id="categoryForm" name="categoryForm" method="post">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name" id="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description" id="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="button" onclick="category.category_add()" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
		      	</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/includes/footer.jsp"/>
	</div>
<script type="text/javascript">
var authUser = '${authUser.id}';
var context_path = '${pageContext.request.contextPath}';
// 초기 페이지 로딩시 불러오기
$(function () {
	category.category_get();
});
</script>
<script src="${pageContext.servletContext.contextPath }/assets/js/fn-category.js"></script>	
</body>
</html>