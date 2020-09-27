<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
<h1>회원수정 페이지입니다.</h1>
<form id="frm">
	<input type="hidden" name="id" value="${user.id}"readonly/>

	<div>아이디 : <input type="text" name="username" placeholder="username" value="${user.username}"readonly/></div>
	<br/>
	<div>이메일 : <input type="email" name="email" placeholder="email"/></div>
	<br/>
	<div>이름 : <input type="text" name="name" placeholder="name" value="${user.name}"/></div>
	<br/>
	<div>비밀번호 수정 : <input id="password" type="password" name="password" placeholder="password"/></div>
	<br/>
	
	<div>전화번호 : <input type="text" name="phone" placeholder="phone" value="${user.phone}"/></div>
	<br/>
	
	<div>주소 : <input type="text" name="address" placeholder="address" value="${user.address}"/></div>
	<br/>
	
	<div>성별 : <input type="text" name="gender" placeholder="gender" value="${user.gender}"/></div>
	<br/>
	
	<div>로그인 유형 : <input type="text" value="${user.provider}"readonly/></div>
	<br/>
</form>
<button type="button" onclick="update()">회원수정</button>
</div>

<script>
function update(){

	let password = $(".password");
	console.log(password);
	
	let data = $("#frm").serialize();
	console.log(data);
	
	$.ajax({
		type: "PUT",
		url: "/user/update",
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