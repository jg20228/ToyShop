<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
	<h1>장바구니 페이지입니다.</h1>
	<table border="1" class="table">
		<thead>
			<tr>
				<th><input type="checkbox" class="check-all" />&nbsp전체선택</th>
				<th>사진</th>
				<th>상품</th>
				<th>금액</th>
				<th>수량</th>
				<th>결제금액</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="basket" items="${baskets}">
				<tr>
					<td><input type="checkbox" id="${basket.id}" name="item"
						class="item" /></td>
					<td><img src="http://localhost:8080/img/${basket.imgUrl}"
						class="img" /></td>
					<td>${basket.name}</td>
					<td id="price-${basket.id}">${basket.price}</td>
					<td><input id="${basket.id}" type="number" min="1" max="10"
						value="${basket.count}" name="count" /></td>
					<td class="sum${basket.id}">${basket.price * basket.count}</td>
					<td><button type="button"
							onclick="deleteBasket(${basket.id},this)">삭제</button></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6"></td>
				<td><button id="btn-delete">선택된 상품 삭제</button></td>
			</tr>
			<tr>
				<td colspan="6"></td>
				<td><button id="btn-pay">결제</button></td>
			</tr>
		</tbody>
	</table>

</div>
<script>
// 선택된 상품 삭제 버튼 클릭 시
$("#btn-delete").on("click", function () {
  var chkList = $('input[name="item"]:checked');
  let idList = [];
  // chcekd 된 product row 삭제
  // order row 삭제
  for (var i = chkList.length - 1; i > -1; i--) {
	  var temp = chkList.eq(i).attr('id');
    //console.log(chkList.eq(i).attr('id'));
    chkList.eq(i).closest("tr").remove();
    idList.push(temp);
  }
  console.log(idList);
  
		let data = {
				"idList" : idList
		}
		console.log("//////////");
		console.log(data);
		$.ajax({
			type: "DELETE",
			url: "/basket/list",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "text"
		}).done(function(resp){
			alert("성공");
			  for (var i = chkList.length - 1; i > -1; i--) {
				  var temp = chkList.eq(i).attr('id');
			    chkList.eq(i).closest("tr").remove();
			  }
			console.log(resp);
		}).fail(function(error){
			alert("실패");
			console.log(error);
		});
});

//선택된 상품 결제 버튼 클릭 시
$("#btn-pay").on("click", function () {
  var chkList = $('input[name="item"]:checked');
  var idList = [];
  // chcekd 된 product row 삭제
  // order row 삭제
  for (var i = chkList.length - 1; i > -1; i--) {
	  var temp = chkList.eq(i).attr('id');
    console.log(chkList.eq(i).attr('id'));
    idList.push(temp);
    //chkList.eq(i).closest("tr").remove();
  }
  console.log(idList);
  
		let data = {
				"idList" : idList
		}
		console.log("//////////");
		console.log(data);
		$.ajax({
			type: "POST",
			url: "/orders/sum",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			alert("성공");
			console.log(resp.data);
			//DB에 있는 결제금액을 가져와서 넣음
			IMP.request_pay({
			    pg : 'inicis', // version 1.1.0부터 지원.
			    pay_method : 'card',
			    merchant_uid : 'merchant_' + new Date().getTime(),
			    name : '주문명:결제테스트',
			    amount : resp.data, //이 부분
			    buyer_email : 'iamport@siot.do',
			    buyer_name : '구매자이름',
			    buyer_tel : '010-1234-5678',
			    buyer_addr : '서울특별시 강남구 삼성동',
			    buyer_postcode : '123-456',
			    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
			}, function(rsp) {
			    if ( rsp.success ) {
			        var msg = '결제가 완료되었습니다.';
			        msg += '고유ID : ' + rsp.imp_uid;
			        msg += '상점 거래ID : ' + rsp.merchant_uid;
			        msg += '결제 금액 : ' + rsp.paid_amount;
			        msg += '카드 승인번호 : ' + rsp.apply_num;
			        let ordersData = {
								impId : rsp.imp_uid,
								merchantId : rsp.merchant_uid,
								applyNum : rsp.apply_num,
								totalPay : rsp.paid_amount,
								idList : idList
					        }
			        console.log(ordersData);
			        $.ajax({
						type: "POST",
						url: "/orders",
						data: JSON.stringify(ordersData),
						contentType: "application/json; charset=utf-8",
						dataType: "json"
					}).done(function(resp){
						alert("성공");
						  for (var i = chkList.length - 1; i > -1; i--) {
							  var temp = chkList.eq(i).attr('id');
						    chkList.eq(i).closest("tr").remove();
						  }
					}).fail(function(error){
						alert("실패");
						console.log(error);
					});
			    } else {
			        var msg = '결제에 실패하였습니다.';
			        msg += '에러내용 : ' + rsp.error_msg;
			    }
			    alert(msg);
			});

			
		}).fail(function(error){
			alert("실패");
			console.log(error);
		});
});


//아임포트 초기화
function init() {
	var IMP = window.IMP; // 생략가능
	IMP.init('imp59848808');
}
init();

function tester(){

}

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
	$.ajax({
		type: "PUT",
		url: "/basket",
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "text"
	}).done(function(resp){
		console.log(resp);
		//id 값으로 해당 price의 값을 가져옴
		let price = $("#price-"+data.id).text();
		//수정이 완료되면 값도 같이 변함
		$(".sum"+data.id).text(price*data.count);
	}).fail(function(error){
		console.log(error);
	});
});

</script>
<%@include file="../layout/footer.jsp"%>