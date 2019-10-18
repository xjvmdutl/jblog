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
<script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
function deleteTest(target) {
	var no = $(target).attr('category-no');
	console.log('no: ', no)

	$.ajax({
		url:"${pageContext.servletContext.contextPath}/api/admin/deletecategory?no="+no,
		contentType:"application/json; charset=utf-8",
		type:"delete",
		success:function(response){
			if(response.result =="fail"){
				console.error(response.message);
				return;
			}
			$('#list tr').remove('#cid-'+no)
			},
			error:function(xhr,error){
				console.err("error"+error);
			}
		});
	}
	$(function(){
		$("#btn-insert").click(function(){
			var CategoryVo = {
				"name" : $("#title1").val(),
				"explanation" : $("#explan1").val()
			};
			if(categoryvo=""){
				return;
			}
			//AJAX 통신
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/api/admin/insertcategory",
				contentType:"application/json; charset=utf-8",
				type:"post",
				dataType:"json",
				data:JSON.stringify(CategoryVo),
				success:function(response){
					if(response.result =="fail"){
						console.error(response.message);
						return
					}
					if(response.data==null){
						alert("통신실패");
						return;
					}
					var vo = response.data;
					console.log(vo);
					$("#list").append(
					"<tr>" +
					"<td>" + vo.no + "</td>"+
					"<td>" + vo.name + "</td>"+
					"<td>" + vo.count + "</td>"+
					"<td>" + vo.explanation + "</td>"+
					"<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg' onclick='deleteTest(this);' category-no='"+vo.no+"' id=category-'" + vo.no +"' ></td>" +
					"</tr>"
					);
				},
				error:function(xhr,error){
					console.err("error"+error);
				}
			});
			
		});
		
		
		
		$("#delete").click(function(event){			
			var no = $(event.target).attr('category-no')
			console.log('no: ', no)
			
			//AJAX 통신
			
			
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp"></c:import>
				<table class="admin-cat" id="list">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:forEach items="${list }" var="categoryvo">
						<tr id="cid-${categoryvo.no }">
							<td>${categoryvo.no }</td>
							<td>${categoryvo.name }</td>
							<td>${categoryvo.count }</td>
							<td>${categoryvo.explanation }</td>
							<td><img
								src="${pageContext.request.contextPath}/assets/images/delete.jpg"
								onclick="deleteTest(this);" category-no="${categoryvo.no }"
								id="category-${categoryvo.no }"></td>
						</tr>
					</c:forEach>

				</table>
				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="title1" type="text" name="title"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input id="explan1" type="text" name="explanation"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input id="btn-insert" type="submit" value="카테고리 추가"></td>
					</tr>
				</table>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>