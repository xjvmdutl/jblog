<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js" type="text/javascript" ></script>
<script>
	$(function(){
		$("#blog-id").change(function(){
			$("#btn-checkemail").show();
			$("#img-checkemail").hide()();
		});
		
		$("#btn-checkemail").click(function(){
			var email=$("#blog-id").val();
			if(email==""){
				return;
			}
			//AJAX 통신
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/api/user/checkemail?email="+email,
				type:"get",
				dataType:"json",
				data:"",
				success:function(response){
					if(response.result =="fail"){
						console.error(response.message);
						return
					}
					if(response.data==true){
						alert("이미 존재하는 메일입니다");
						$("#blog-id").val("");
						$("#blog-id").focus();
						return;
					}
					$("#btn-checkemail").hide();
					$("#img-checkemail").show();
				},
				error:function(xhr,error){
					console.err("error"+error);
				}
				
			});
			
		});
	});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/menu.jsp"></c:import>
		<form:form 
		modelAttribute="userVo"
		class="join-form" 
		id="join-form" 
		method="post" 
		action="${pageContext.servletContext.contextPath}/user/join" >
			<label class="block-label" for="name">이름</label>
			<form:input id="name" path="name"  />
			<p style="font-weight:bold; color:red; text-align:left; padding:2px 0 0 0">
				<form:errors path="name" />
			</p>
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" id="blog-id" /> 
			<p style="font-weight:bold; color:red; text-align:left; padding:2px 0 0 0">
				<form:errors path="id" />
			</p>
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<form:password id="password" path="password" />

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
