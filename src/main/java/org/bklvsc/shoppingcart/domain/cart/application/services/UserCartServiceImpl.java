package org.bklvsc.shoppingcart.domain.cart.application.services;

import org.bklvsc.shoppingcart.domain.cart.application.dtos.commands.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.domain.cart.application.dtos.commands.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.domain.cart.application.ports.in.UserCartService;
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
