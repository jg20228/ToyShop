<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>
<div class="container">
<h1>인덱스 페이지입니다.</h1>
${principal.username}
${principal.password}
${principal.user.id}
${principal.user.role}
<br/>
<c:if test="${principal.user.role eq 'ROLE_ADMIN'}">
참
</c:if>
<hr/>
${loginUser}
</div>
<%@include file="layout/footer.jsp"%>

