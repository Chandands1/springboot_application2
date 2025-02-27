package com.demo.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.application.entity.Product;

@RestController
public class ProductController {

	private static Map<String, Product> productRepo = new HashMap<>();
	static {
	Product honey = new Product();
	honey.setId("1");
	honey.setName("Honey");
	productRepo.put(honey.getId(), honey);
	Product almond = new Product();
	almond.setId("2");
	almond.setName("Almond");
	productRepo.put(almond.getId(), almond);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable ("id") String id){
		
		productRepo.remove(id);
		
		
		return new ResponseEntity<>("Product is deleted succesfully",HttpStatus.OK);
		
		
	}
	
	@PutMapping(value = "/products/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id,@RequestBody Product product){
		
		productRepo.remove(id, product);
		product.setId(id);
		productRepo.put(id,product);
		
		return new ResponseEntity<>("Product is updated succesfully",HttpStatus.OK);
		
	}
	
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		
		productRepo.put(product.getId(), product);
		
		return new ResponseEntity<>("Product Created successfully", HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/products")
	public ResponseEntity<Object> getProduct() {
	return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}
	
	
}
