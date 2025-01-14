package com.sprint.ofd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ofd.entity.FoodCart;
import com.sprint.ofd.entity.Item;
import com.sprint.ofd.entity.dto.CartInputDto;
import com.sprint.ofd.service.ICartService;

@RestController
public class FoodCartController {
	
	@Autowired
	ICartService cartServ;
	
	
	
	//controller calling foodCartService to add Item to cart
	@GetMapping("/cart/additem/{itemId}/{cartId}")
	public ResponseEntity<FoodCart> addItemToCart(@Valid @PathVariable Integer itemId,@PathVariable Integer cartId) {
		FoodCart food = cartServ.addItemToCart(itemId,cartId);
		ResponseEntity<FoodCart> response = new ResponseEntity<>(food, HttpStatus.OK); 
		return response;
		
	}
	
	@PostMapping("/cart/newCart/additem/")
	public ResponseEntity<FoodCart> addItemToNewCart(@Valid @RequestBody Integer itemId) {
		FoodCart food = cartServ.addItemToNewCart(itemId);
		ResponseEntity<FoodCart> response = new ResponseEntity<>(food, HttpStatus.OK); 
		return response;
		
	}
	//controller calling foodCartService to increase quantity	
	@PutMapping("/cart/increaseQuantity/{cart}/{item}/{quant}/")
	public ResponseEntity<FoodCart> increaseQuantity(@Valid @PathVariable("cart") Integer cartId,@Valid @PathVariable("item") Integer itemId,@Valid @PathVariable("quant") Integer quantity) {
		FoodCart food = cartServ.increaseQuantity(cartId, itemId,quantity);
		ResponseEntity<FoodCart> response = new ResponseEntity<>(food, HttpStatus.OK); 
		return response;
	}
	//controller calling foodCartService to reduce quantity
	@PutMapping("/cart/reduceQuantity/{cart}/{item}/{quant}/")
	public ResponseEntity<FoodCart> reduceQuantity(@Valid @PathVariable("cart") Integer cartId,@Valid @PathVariable("item") Integer itemId,@Valid @PathVariable("quant") Integer quantity) {
		FoodCart food = cartServ.reduceQuantity(cartId, itemId,quantity);
		ResponseEntity<FoodCart> response = new ResponseEntity<>(food, HttpStatus.OK); 
		return response;
	}
	@GetMapping("/cart/view/all")
	public ResponseEntity<List<FoodCart>> viewAll() {
		List<FoodCart> food = cartServ.viewAll();
		ResponseEntity<List<FoodCart>> response = new ResponseEntity<>(food, HttpStatus.OK); 
		return response;
		
	}
	//controller calling foodCartService to delete Item from cart
	@DeleteMapping("/cart/delete/item/{cart}/{item}")
	public ResponseEntity<FoodCart> removeItem(@Valid @PathVariable("cart") Integer cart,@Valid @PathVariable("item") Integer item) {
		FoodCart food = cartServ.removeItem(cart, item);
		ResponseEntity<FoodCart> response = new ResponseEntity<>(food, HttpStatus.OK);
		return response;
	}
	
	//controller calling foodCartService to clear cart
	@DeleteMapping("/cart/delete/all/{cart}")
	public ResponseEntity<FoodCart> clearCart(@Valid @PathVariable Integer cart) {
		FoodCart food = cartServ.clearCart(cart);
		ResponseEntity<FoodCart> response = new ResponseEntity<>(food, HttpStatus.OK); 
		return response;
	}
	@DeleteMapping("/cart/deleteCart/{cart}")
	public ResponseEntity<String> removeCart(@PathVariable Integer cart) {
		String res = cartServ.removeCart(cart);
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK); 
		return response;
	}

}
