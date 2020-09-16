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

import com.wc.toyshop.controller.dto.AddProductReqDto;
import com.wc.toyshop.controller.dto.UpdateProductReqDto;
import com.wc.toyshop.model.Product;
import com.wc.toyshop.repository.AdminRepository;
import com.wc.toyshop.repository.ProductRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ProductRepository productRepository;

	@Value("${file.path}")
	private String uploadFolder;

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
	}

	public List<Product> 모든상품() {
		return productRepository.findAll();
	}

	public Product 상품수정가기(int id) {
		Product product = productRepository.findById(id);
		return product;
	}

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

	public void 상품삭제(int id) {
		productRepository.deleteById(id);
	}
}
