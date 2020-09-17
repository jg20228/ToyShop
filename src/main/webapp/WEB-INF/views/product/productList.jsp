<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">

<br/>
<a href="/admin/productForm">상품 등록</a>
<br/>
<br/>
<table border="1" class="table">
		<thead>
			<tr>
				<th>이름</th>
				<th>사진</th>
				<th>설명</th>
				<th>가격</th>
				<th>재고</th>
				<th>장바구니</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.name}</td>
					<td><img src="http://localhost:8080/img/${product.imgUrl}" class="img"/></td>
					<td>${product.disc}</td>
					<td>${product.price}</td>
					<td>${product.count}</td>
					<td><input type="number" min="1" max="10" value="1"/>클릭${product.id}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button type="button" onclick="check(${principal.user.id})">111</button>
</div>


<script>
function check(id){
	alert(id);
}
</script>
<%@include file="../layout/footer.jsp"%>

