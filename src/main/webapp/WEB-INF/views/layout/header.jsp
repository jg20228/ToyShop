<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/global.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>Toy</title>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="principal" />
	</sec:authorize>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/test/index">메인</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${empty principal}">
						<li class="nav-item"><a class="nav-link" href="/product">상품</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/join">회원가입</a></li>
					</c:when>
					<c:when test="${principal.user.role eq 'ROLE_ADMIN'}">
						<li class="nav-item"><a class="nav-link" href="/product">상품</a></li>
						<li class="nav-item"><a class="nav-link" href="/basket/list/">장바구니</a></li>
						<li class="nav-item"><a class="nav-link" href="#">결제내역</a></li>
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link" href="/admin/product">상품관리</a></li>
						<li class="nav-item"><a class="nav-link" href="#">유저관리</a></li>
						<li class="nav-item"><a class="nav-link" href="#">주문관리</a></li>
					</c:when>

					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/product">상품</a></li>
						<li class="nav-item"><a class="nav-link" href="/basket/list/">장바구니</a></li>
						<li class="nav-item"><a class="nav-link" href="#">결제내역</a></li>
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>