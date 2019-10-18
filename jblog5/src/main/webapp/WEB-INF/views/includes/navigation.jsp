<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="navigation">
	<h2>카테고리</h2>
	<ul>
		<c:forEach items='${list }' var='category'>
			<li><a href="${pageContext.request.contextPath}/${id}/${category.no}">${category.name}</a></li>
		</c:forEach>
	</ul>
</div>