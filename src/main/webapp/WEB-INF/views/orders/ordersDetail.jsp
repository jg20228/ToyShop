<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
	<h1>결제내역 상세보기 페이지입니다.</h1>
	<table border="1" class="table">
		<tbody>
				<tr>
					<td colspan="3">주문일자 : ${dto.orders.createDate}</td>
					<td colspan="2">아임포트 아이디 : ${dto.orders.impId}</td>
				</tr>
				
				<tr>
					<td colspan="3">${dto.orders.merchantId}</td>
					<td colspan="2">승인번호 : ${dto.orders.applyNum}</td>
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
		</tbody>
	</table>

</div>

<%@include file="../layout/footer.jsp"%>