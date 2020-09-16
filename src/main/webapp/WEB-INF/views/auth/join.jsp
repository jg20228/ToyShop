<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
<h1>회원가입 페이지입니다.</h1>

<form id="frm">
	<div><input type="text" name="username" placeholder="username"/></div>
	<br/>
	<div><input type="email" name="email" placeholder="email"/></div>
	<br/>
	<div><input type="password" name="password" placeholder="password"/></div>
	<br/>
</form>
<button type="button" onclick="join()">회원가입</button>
</div>

<script>
function join(){
	let data = $("#frm").serialize();
	console.log(data);
	
	$.ajax({
		type: "POST",
		url: "/auth/join",
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "text"
	}).done(function(resp){
		console.log(resp);
		alert("성공");
		location.href="/auth/login";
	}).fail(function(error){
		console.log(error);
		alert("실패");
	});
}
</script>
<%@include file="../layout/footer.jsp"%>