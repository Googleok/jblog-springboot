<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
   
   $(function(){ //dom이 다 로딩된 다음에 실행됨!!!!
      
      $("#id").change(function(){ //포커스를 다른곳으로 갈때 다시 검사
         $("#btn-checkemail").show();
         $("#img-checkemail").hide();
      });
      
      $('#btn-checkemail').click(function(){
         var id = $('#id').val();
         if(id == ''){
            return;
         }
         
         //ajax 통신
         $.ajax({
            url:"/jblog6/user/api/checkid?id="+id,
            type:"get",
            dataType:"json",
            data:"",
            success:function(response){
               if(response.result != "success"){
                  console.log(response);
            	   // console.error(response.message);
                  return;
               }
               if(response.data == true){
                  alert("이미 존재하는 아이디입니다.\n다른 아이디를 사용해 주세요.");
                  $("#id").focus();
                  $("#id").val("");
                  return;
               }
               
               $("#btn-checkemail").hide();
               $("#img-checkemail").show();
               
            },
            error:function(xhr,error){//내부에서 통신하고 있는 객체(에러내용 더 자세히 보라)
               console.error("error:"+error);               
            },
         
         });
         console.log(id);
      });
      
   });
   
</script>
</head>
<body>
	<div class="center-content">
	<c:import url='/WEB-INF/views/includes/header.jsp' />
		<form:form
			modelAttribute="userVo"
			class="join-form"
			id="join-form"
			name="joinForm"
			method="post"
			action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('name') }">
					<p style="font-weight:bold; color:red; text-align:left; padding:0">
					   <spring:message 
							code="${errors.getFieldError( 'name' ).codes[0] }" 				     
						    text="${errors.getFieldError( 'name' ).defaultMessage }" />
					</p> 
				</c:if>
			</spring:hasBindErrors>
			
			<label class="block-label" for="id">아이디</label>
			
			<form:input path="id" />
				<input type="button" id="btn-checkemail" value="id 중복체크">
				<img style="display:none" id="img-checkemail" src="${pageContext.servletContext.contextPath }/assets/images/check.png" />
				<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
					<form:errors path="id" />
				</p>
			
				<label class="block-label">패스워드</label>
				<form:password path="password" />
				<fieldset>
					<legend>약관동의</legend>
					<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
					<label class="l-float">서비스 약관에 동의합니다.</label>
				</fieldset>
	
				<input type="submit" value="가입하기">
			</form:form>
	</div>
</body>
</html>
