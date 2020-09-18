<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
	<table border="1" class="table">
		<thead>
			<tr>
				<th><input type="checkbox" class="check-all"/>&nbsp전체선택</th>
				<th>사진</th>
				<th>상품</th>
				<th>금액</th>
				<th>수량</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="basket" items="${baskets}">
				<tr>
					<td><input type="checkbox" class="item"/></td>
					<td><img src="http://localhost:8080/img/${basket.imgUrl}"
						class="img" /></td>
					<td>${basket.name}</td>
					<td>${basket.price}</td>
					<td>
						<input id="${basket.id}" type="number" min="1" max="10"
						value="${basket.count}" name="count"/>
					</td>
					<td><button type="button" onclick="deleteBasket(${basket.id},this)">삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
function deleteBasket(id,obj){
	var tr = $(obj).parent().parent();
	let data = {
			id : id
	}
	
	$.ajax({
		type: "DELETE",
		url: "/basket",
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "text"
	}).done(function(resp){
		alert("성공");
		tr.remove();
		console.log(resp);
	}).fail(function(error){
		alert("실패");
		console.log(error);
	});
}


//전체선택 클릭시 input checkbox 체크하고 풀기
$( document ).ready( function() {
    $( '.check-all' ).click( function() {
      $( '.item' ).prop('checked', this.checked);
    } );
  } 
);

//장바구니 실시간으로 update
$(":input").bind('keyup mouseup', function () {
	if(this.name!="count"){
		//변경이 감지된 애가 name값을 count로 안들고 있으면 취소
		return;
	}
	let data = {
			id : this.id,
			count : this.value
	}
	console.log(data);

	$.ajax({
		type: "PUT",
		url: "/basket",
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "text"
	}).done(function(resp){
		console.log(resp);
	}).fail(function(error){
		console.log(error);
	});
});

</script>
<%@include file="../layout/footer.jsp"%>