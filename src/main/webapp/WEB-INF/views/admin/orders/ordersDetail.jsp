<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../../layout/header.jsp"%>
<div class="container">

	<br /> <br /> <br />
	<table border="1" class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>아임포트 Id</th>
				<th>merchant Id</th>
				<th>applyNum</th>
				<th>totalPay</th>
				<th>구매일자</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${dto.orders.id}</td>
				<td>${dto.orders.impId}</td>
				<td>${dto.orders.merchantId}</td>
				<td>${dto.orders.applyNum}</td>
				<td>${dto.orders.totalPay}</td>
				<td>${dto.orders.createDate}</td>
			</tr>
			<c:forEach var="detail" items="${dto.details}">
				<tr>
					<td colspan="2"><img
						src="http://localhost:8080/img/${detail.imgUrl}" class="img" /></td>
					<td>${detail.name}</td>
					<td>${detail.price}</td>
					<td>${detail.count}</td>
					<td>${detail.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	
</script>
<%@include file="../../layout/footer.jsp"%>

