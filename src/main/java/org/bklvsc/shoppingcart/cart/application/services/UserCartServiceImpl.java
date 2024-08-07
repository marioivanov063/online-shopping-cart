package org.bklvsc.shoppingcart.cart.application.services;

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
	public void addFoodToUsersCart(AddFoodToCartCommand command) {
		addFoodToCartCommandHandler.handle(command);
	}
	
	@Override
	public void removeFoodFromUsersCart(RemoveFoodFromCartCommand command) {
		removeFoodFromCartCommandHandler.handle(command);
	}
}
