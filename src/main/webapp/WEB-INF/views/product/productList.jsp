<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">

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
					<td><img src="http://localhost:8080/img/${product.imgUrl}"
						class="img" /></td>
					<td>${product.disc}</td>
					<td>${product.price}</td>
					<td>${product.count}</td>
					<td><c:choose>
							<c:when test="${empty principal}">
								<input id="count${product.id}" type="number" min="1" max="10"
									value="1" />
								<button onclick="alert('회원가입이 필요합니다.');">클릭</button>

							</c:when>
							<c:otherwise>
								<input id="count${product.id}" type="number" min="1" max="10"
									value="1" />
								<button onclick="check(${principal.user.id},${product.id});">클릭
								</button>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>


<script>
function check(userId,productId){
	//장바구니 누를때 몇개를 넣을지, 로그인된 유저의 세션 id를 가져옴
	let count = $("#count"+productId).val();

	let data = {
			userId : userId,
			productId : productId,
			count : count
	}
	$.ajax({
		type: "POST",
		url: "/basket",
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "text"
	}).done(function(resp){
		console.log(resp);
	}).fail(function(error){
		console.log(error);
	});
}
</script>
<%@include file="../layout/footer.jsp"%>

