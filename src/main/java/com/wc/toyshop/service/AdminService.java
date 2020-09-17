package com.wc.toyshop.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wc.toyshop.controller.dto.AddProductReqDto;
import com.wc.toyshop.controller.dto.UpdateProductReqDto;
import com.wc.toyshop.controller.respdto.ProductRespDto;
import com.wc.toyshop.model.Product;
import com.wc.toyshop.model.Stock;
import com.wc.toyshop.repository.AdminRepository;
import com.wc.toyshop.repository.ProductRepository;
import com.wc.toyshop.repository.StockRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StockRepository stockRepository;
	@Value("${file.path}")
	private String uploadFolder;

	@Transactional
	public void 상품등록(AddProductReqDto addProductReqDto) {
		UUID uuid = UUID.randomUUID();

		String imageFileName = uuid + "" + addProductReqDto.getFile().getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		try {
			Files.write(imageFilePath, addProductReqDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Product product = Product.builder()
				.name(addProductReqDto.getName())
				.imgUrl(imageFileName)
				.disc(addProductReqDto.getDisc())
				.price(addProductReqDto.getPrice())
				.build();
		productRepository.productSave(product);
		//mapper에서 id값을 받아왔기 때문에 여기부터 product에 id값이 있다. 이것으로 재고량을 넣자.
		Stock stock =Stock.builder()
				.productId(product.getId())
				.count(0)
				.build();
		stockRepository.save(stock);
	}

	public Product 상품수정가기(int id) {
		Product product = productRepository.findById(id);
		return product;
	}

	@Transactional
	public void 상품수정(UpdateProductReqDto updateProductReqDto) {
		UUID uuid = UUID.randomUUID();

		String imageFileName = uuid + "" + updateProductReqDto.getFile().getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		try {
			Files.write(imageFilePath, updateProductReqDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Product product = Product.builder()
				.id(updateProductReqDto.getId())
				.name(updateProductReqDto.getName())
				.imgUrl(imageFileName)
				.disc(updateProductReqDto.getDisc())
				.price(updateProductReqDto.getPrice())
				.build();
		productRepository.update(product);
	}

	@Transactional
	public void 상품삭제(int id) {
		productRepository.deleteById(id);
	}
}
