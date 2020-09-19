package com.wc.toyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wc.toyshop.controller.dto.AddBasketReqDto;
import com.wc.toyshop.controller.dto.BasketListReqDto;
import com.wc.toyshop.controller.dto.UpdateBasketReqDto;
import com.wc.toyshop.controller.respdto.BasketRespDto;
import com.wc.toyshop.model.Basket;
import com.wc.toyshop.repository.BasketRepository;

@Service
public class BasketService {
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Transactional
	public void 장바구니목록삭제(BasketListReqDto dto) {
		for (String id : dto.getIdList()) {
			basketRepository.deleteById(Integer.parseInt(id));
		}
	}
	
	public void 장바구니삭제(int id) {
		basketRepository.deleteById(id);
	}
	
	public List<BasketRespDto> 장바구니조회(int userId) {
		return basketRepository.findyAllJoin(userId);
	}
	
	@Transactional
	public void 장바구니수정(UpdateBasketReqDto updateBasketReqDto) {
		Basket basketEntity = basketRepository.findById(updateBasketReqDto.getId());
		basketEntity.setCount(updateBasketReqDto.getCount());
		basketRepository.update(basketEntity);
	}
	
	@Transactional
	public void 장바구니추가(AddBasketReqDto addBasketReqDto) {
		//검사
		Basket basketEntity = basketRepository.findyByUserIdAndProductId(addBasketReqDto.getUserId(),addBasketReqDto.getProductId());
		System.out.println(basketEntity);
		
		//count는 10이 한계이다. front에서 잘막아도 들어올수있으므로 여기서 10초과시 10으로 지정해줌
		if(addBasketReqDto.getCount()>10) addBasketReqDto.setCount(10);
		//값이 없으면 save 있으면 count값을보고 update
		
		if(basketEntity==null) {
			System.out.println("값이 없어서 null 실행");
			basketEntity = Basket.builder()
					.userId(addBasketReqDto.getUserId())
					.productId(addBasketReqDto.getProductId())
					.count(addBasketReqDto.getCount())
					.build();
			basketRepository.save(basketEntity);
		}else if(basketEntity.getCount()<10){
			System.out.println("값이 있어서 else if 실행");
			int sum = basketEntity.getCount()+addBasketReqDto.getCount();
			if(sum>10) sum=10;
			basketEntity.setCount(sum);
			basketRepository.update(basketEntity);
		}
	}
}
