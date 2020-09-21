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
				<th>이메일</th>
				<th>이름</th>
				<th>주소</th>
				<th>전화번호</th>
				<th>성별</th>
				<th>프로파일이미지</th>
				<th>권한</th>
				<th>provider</th>
				<th>providerId</th>
				<th>생성일자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.name}</td>
					<td>${user.address}</td>
					<td>${user.phone}</td>
					<td>${user.gender}</td>
					<td>${user.profileImage}</td>
					<td>${user.role}</td>
					<td>${user.provider}</td>
					<td>${user.providerId}</td>
					<td>${user.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@include file="../../layout/footer.jsp"%>

