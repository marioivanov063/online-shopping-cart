package org.bklvsc.shoppingcart.cart.api;


import java.util.UUID;

import org.bklvsc.shoppingcart.cart.api.requests.FoodNameRequest;
import org.bklvsc.shoppingcart.cart.application.dtos.CartDto;
import org.bklvsc.shoppingcart.cart.application.dtos.add_to_cart.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.cart.application.dtos.remove_from_cart.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.cart.application.ports.in.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartController {
	private UserCartService userCartService;

	public CartController(UserCartService userCartService) {
		this.userCartService = userCartService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<CartDto> addFoodToCart(@RequestBody FoodNameRequest foodName, HttpSession session){
		CartDto cartDto = userCartService
				.addFoodToUsersCart(new AddFoodToCartCommand(foodName.value(), UUID.fromString(session.getId())));
		return ResponseEntity.ok(cartDto);
	}
	
	@GetMapping("/csrftoken")
	public CsrfToken getCsrfToken(CsrfToken csrfToken) {
		return csrfToken;
	}
	
	@PostMapping("/remove")
	public ResponseEntity<CartDto> removeFoodFromCart(@RequestBody FoodNameRequest foodName, HttpSession session){
		CartDto cartDto = userCartService.removeFoodFromUsersCart(new RemoveFoodFromCartCommand(foodName.value(), UUID.fromString(session.getId())));
		return ResponseEntity.ok(cartDto);
	}
}
