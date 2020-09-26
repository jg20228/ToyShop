<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">

	<form action="/auth/loginProc" method="post">
	<br/>
		<input type="text" name="username" placeholder="Username" required>
	<br/>
		<input type="password" name="password" placeholder="Password" required>
	<br/>
		<input type="submit" value="로그인">
	</form>
	<!-- 카카오 로그인 -->
	<br /> <a href="https://kauth.kakao.com/oauth/authorize?client_id=25ed0bbfe75444dcca1ac7386b6481cc&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"> 
	<img height="38px"
		src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_narrow.png"></img>
	</a> 
	<br/>
	
	<!-- 구글 로그인 이 주소는 마음대로 바꿔서 적을수 없음-->
	<a href="/oauth2/authorization/google">구글 로그인</a>
	
	<br/>
	
	<!-- 페이스북 로그인 이 주소는 마음대로 바꿔서 적을수 없음-->
	<a href="/oauth2/authorization/facebook">페이스북 로그인</a>
	
	<br/>
	
	<!-- 네이버 로그인 이 주소는 마음대로 바꿔서 적을수 없음-->
	<!-- yml에 적었던 https://nid.naver.com/oauth2.0/authorize 가 실행됨-->
	<a href="/oauth2/authorization/naver">네이버 로그인</a>
	
	<br/>
	
	<a href="/auth/join">회원가입 하러 가기</a>
	
</div>
<%@include file="../layout/footer.jsp"%>