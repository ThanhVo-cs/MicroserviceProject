package com.thanhvo.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhvo.productservice.dto.ProductReponse;
import com.thanhvo.productservice.dto.ProductRequest;
import com.thanhvo.productservice.model.Product;
import com.thanhvo.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	private final ProductRepository productRepsitory;
	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		productRepsitory.save(product);
		log.info("Product {} is saved", product.getId());
		
	}

	public List<ProductReponse> getAllProduct() {
		List<Product> products = productRepsitory.findAll();
		return products.stream().map(product -> mapToProductReponse(product)).collect(Collectors.toList());
	}
	
	private ProductReponse mapToProductReponse(Product product) {
		return ProductReponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
