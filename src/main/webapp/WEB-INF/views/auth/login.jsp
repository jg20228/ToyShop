<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
<form id="frm">

	<div><input type="text" name="username" placeholder="username"/></div>
	<br/>
	<div><input type="password" name="password" placeholder="password"/></div>
	<br/>
</form>
<button type="button" onclick="login()">로그인</button>
	<a href="">
		<img height="38px" src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_narrow.png"></img>
	</a>
<a href="/auth/join">회원가입 하러 가기</a>
</div>
<script>
function login(){
	let data = $("#frm").serialize();
	console.log(data);
	
	$.ajax({
		type: "POST",
		url: "/auth/loginProc",
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "text"
	}).done(function(resp){
		console.log(resp);
		alert("성공");
		location.href="/test/index";
	}).fail(function(error){
		console.log(error);
		alert("실패");
	});
}
</script>
<%@include file="../layout/footer.jsp"%>