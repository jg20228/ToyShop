package com.wc.toyshop.controller.respdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Ajax로 요청했을때 응답결과를 알려줄려고 사용했음
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonRespDto<T> {
	private int statusCode; //1정상,  -1실패, 0 변경 안됨 원래는 enum 써야한다.
	private T data;
}
