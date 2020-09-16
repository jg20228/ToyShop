<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../../layout/header.jsp"%>
<div class="container">

<br/>
<a href="/admin/productForm">상품 등록</a>
<br/>
<br/>
<table border="1" class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>사진</th>
				<th>설명</th>
				<th>가격</th>
				<th>등록일</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td><img src="http://localhost:8080/img/${product.imgUrl}"/></td>
					<td>${product.disc}</td>
					<td>${product.price}</td>
					<td><fmt:formatDate value="${product.createDate}" pattern="yyyy-MM-dd" /></td>
					<td><a href="/admin/productUpdateForm/${product.id}">수정</a></td>
					<td><i onclick="deleteById(${product.id},this);">삭제</i></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
function deleteById(id,obj){
	//해당 행 삭제
	var tr = $(obj).parent().parent();
	let data = {
			id : id
	}
	$.ajax({
		type: "DELETE",
		url: "/admin/product",
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "text"
	}).done(function(resp){
		console.log(resp);
		tr.remove();
		alert("성공");
	}).fail(function(error){
		console.log(error);
		alert("실패");
	});
}
</script>
<%@include file="../../layout/footer.jsp"%>

