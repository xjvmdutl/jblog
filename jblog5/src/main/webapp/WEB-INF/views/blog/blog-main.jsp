<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newline", '\n');
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				<c:if test="${not empty post }">
					<h4>${post.title}</h4>
					<p>
						${fn:replace(post.contents, newline, '<br/>')}
					<p>
				</c:if>
				</div>
				<c:if test="${ not empty postList}">
					<ul class="blog-list">
					<c:forEach items='${postList }' var='postvo'>
						<li><a href="${pageContext.request.contextPath}/${id}/${postvo.category_no}/${postvo.no}">${postvo.title} </a> <span>${postvo.written_date }</span></li>
					</c:forEach>
					</ul>
				</c:if>				
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }${vo.logo}">
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>