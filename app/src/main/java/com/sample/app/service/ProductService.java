package com.sample.app.service;

import java.util.List;

import com.sample.app.domain.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	Product getProduct(String productId);

}
