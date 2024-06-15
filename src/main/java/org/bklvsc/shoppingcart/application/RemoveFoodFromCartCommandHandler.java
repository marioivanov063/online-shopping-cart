package org.bklvsc.shoppingcart.application;

import java.util.Optional;

import org.bklvsc.shoppingcart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.application.exceptions.NoCartException;
import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.application.services.UserService;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.entities.User;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.domain.port.out.CartRepository;
import org.bklvsc.shoppingcart.domain.port.out.FoodRepository;
import org.bklvsc.shoppingcart.domain.port.out.UserRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.UserId;

public class RemoveFoodFromCartCommandHandler implements CommandHandler<RemoveFoodFromCartCommand, Boolean>{
	private FoodRepository foodRepository;
	private UserService userService;
	
	public RemoveFoodFromCartCommandHandler(FoodRepository foodRepository, UserService userService) {
		super();
		this.foodRepository = foodRepository;
		this.userService = userService;
	}

	@Override
	public Boolean handle(RemoveFoodFromCartCommand command) {
		UserId userId = UserId.from(command.userId());
		FoodName foodName = new FoodName(command.foodName());
        Food food = foodRepository.getFood(foodName)
        		.orElseThrow(() -> new FoodNotFoundException());      
        return userService.removeFoodFromUsersCart(foodName, userId);
	}

}
