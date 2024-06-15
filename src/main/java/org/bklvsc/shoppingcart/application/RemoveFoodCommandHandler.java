package org.bklvsc.shoppingcart.application;

import java.util.Optional;

import org.bklvsc.shoppingcart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.application.services.CartService;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.RemoveFoodCommand;
import org.bklvsc.shoppingcart.domain.port.out.CartRepository;
import org.bklvsc.shoppingcart.domain.port.out.FoodRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public class RemoveFoodCommandHandler implements CommandHandler<RemoveFoodCommand, Boolean>{
	private FoodRepository foodRepository;
	private CartService cartService;
	
	private RemoveFoodCommandHandler(FoodRepository foodRepository, CartService cartService) {
		super();
		this.foodRepository = foodRepository;
		this.cartService = cartService;
	}

	@Override
	public Boolean handle(RemoveFoodCommand command) {
		FoodName foodName = new FoodName(command.foodName());
		Optional<Food> food = foodRepository.getFood(foodName);
		if(food.isEmpty())
	        return Boolean.FALSE;	
		
		cartService.removeFoodFromCarts(foodName);
		return Boolean.TRUE;
	}

}
