<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../../layout/header.jsp"%>
<div class="container">

<br/>
<br/>
<br/>
<table border="1" class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>유저네임</th>
				<th>아임포트 Id</th>
				<th>merchant Id</th>
				<th>applyNum</th>
				<th>totalPay</th>
				<th>구매일자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${orders}">
				<tr>
					<td><a href="/admin/orders/detail/${order.id}/${order.userId}">${order.id}</a></td>
					<td><a href="/admin/orders/search/${order.userId}">${order.username}</a></td>
					<td>${order.impId}</td>
					<td>${order.merchantId}</td>
					<td>${order.applyNum}</td>
					<td>${order.totalPay}</td>
					<td>${order.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
</script>
<%@include file="../../layout/footer.jsp"%>

