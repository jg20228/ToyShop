<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../../layout/header.jsp"%>
<div class="container">
<h1>상품 등록 페이지</h1>
<div class="box">
			<div class="bigbox">
				<br />

				<div class="text">
					<hr class="hr" />
					사진업로드
					<hr class="hr" />
				</div>
				<!-- 섬네일은 아래 주소 참고 -->
				<!-- https://medium.com/@asadise/create-thumbnail-for-an-image-in-spring-framework-49776c873ea1 -->
				<form action="/admin/product" method="post" enctype="multipart/form-data">
					<table class="table">
						<tr>
							<td><input id="input_img" type="file" name="file" placeholder="사진파일"></td>
						</tr>
						<tr>
							<td>
								<div class="img_wrap">
									<img id="img" />
								</div>
							</td>
						</tr>
						<tr>
							<td><input type="text" name="name" placeholder="상품 이름"></td>
						</tr>
						<tr>
							<td><input type="text" name="disc" placeholder="설명"></td>
						</tr>
						<tr>
							<td><input type="number" name="price" placeholder="가격"></td>
						</tr>
					</table>
					<input type="submit" value="업로드">
				</form>

			</div>
			<div class="smallbox">
				<div class=text>
					<a href="javascript:window.history.back()" class="under1">뒤로가기</a>
				</div>
			</div>
		</div>
</div>

<script>
		var sel_file;
		$(document).ready(function() {
			$('#input_img').on("change", handleImgFileSelect);
		});
		function handleImgFileSelect(e) {
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);
			filesArr.forEach(function(f) {
				if (!f.type.match("image.*")) {
					alert("확장자는 이미지 확장자만 가능합니다.");
					return;
				}
				sel_file = f;
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#img').attr('src', e.target.result);
				}
				reader.readAsDataURL(f);
			});
		}
	</script>

<%@include file="../../layout/footer.jsp"%>