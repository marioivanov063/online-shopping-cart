package org.bklvsc.shoppingcart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ShoppingCartController {
	static {
		System.out.println("hi");
	}
	
	@GetMapping("/")
	public String requestMapping() {
		return "Hi";
	}
}
