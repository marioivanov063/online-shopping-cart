package org.bklvsc.shoppingcart.cart.application.services;

import org.bklvsc.shoppingcart.cart.application.dtos.CartDto;
import org.bklvsc.shoppingcart.cart.application.dtos.add_to_cart.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.cart.application.dtos.remove_from_cart.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.cart.application.ports.in.UserCartService;
import org.springframework.stereotype.Service;

@Service
class UserCartServiceImpl implements UserCartService{
	AddFoodToCartCommandHandler addFoodToCartCommandHandler;
	RemoveFoodFromCartCommandHandler removeFoodFromCartCommandHandler;

	public UserCartServiceImpl(AddFoodToCartCommandHandler addFoodToCartCommandHandler, RemoveFoodFromCartCommandHandler removeFoodFromCartCommandHandler) {
		this.addFoodToCartCommandHandler = addFoodToCartCommandHandler;
		this.removeFoodFromCartCommandHandler = removeFoodFromCartCommandHandler;
	}

	@Override
	public CartDto addFoodToUsersCart(AddFoodToCartCommand command) {
		return addFoodToCartCommandHandler.handle(command);
	}
	
	@Override
	public CartDto removeFoodFromUsersCart(RemoveFoodFromCartCommand command) {
		return removeFoodFromCartCommandHandler.handle(command);
	}
}
