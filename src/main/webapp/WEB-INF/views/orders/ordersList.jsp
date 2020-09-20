<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
	<h1>결제내역 페이지입니다.</h1>
	<table border="1" class="table">
		<tbody>
			<c:forEach var="dto" items="${dtos}">
				<tr>
					<td colspan="5">${dto.orders.createDate}</td>
				</tr>
				<tr>
					<td colspan="5">${dto.orders.id}주문 상세보기</td>
				</tr>
				
				<c:forEach var="detail" items="${dto.details}">
				<tr><tr/>
				<tr>
					<td><img src="http://localhost:8080/img/${detail.imgUrl}"
						class="img" /></td>
					<td>${detail.name}</td>
					<td>${detail.price}</td>
					<td>${detail.count}</td>
					<td>${detail.status}</td>
				</tr>
				<tr>
					<td colspan="5" />
				<tr/>
				</c:forEach>
				
			</c:forEach>
		</tbody>
	</table>

</div>

<%@include file="../layout/footer.jsp"%>