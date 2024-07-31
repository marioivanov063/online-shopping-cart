package org.bklvsc.shoppingcart.application.commands;

import java.util.Optional;

import org.bklvsc.shoppingcart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.application.services.UserCartService;
import org.bklvsc.shoppingcart.domain.food.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.UserRepository;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.springframework.stereotype.Service;

@Service
public class RemoveFoodFromCartCommandHandler implements CommandHandler<RemoveFoodFromCartCommand, Void>{
	private FoodRepository foodRepository;
	private UserRepository userRepository;
	private UserCartService userCartService;
	
	public RemoveFoodFromCartCommandHandler(FoodRepository foodReadRepository,  UserCartService userCartService, UserRepository userRepository) {
		this.foodRepository = foodReadRepository;
		this.userRepository = userRepository;
		this.userCartService = userCartService;
	}

	@Override
	public Void handle(RemoveFoodFromCartCommand command) {
		UserId userId = new UserId(command.userId());
		FoodName foodName = new FoodName(command.foodName()); 
        UserDomainBehaviour user = userRepository.getUser(userId)
        		.orElseThrow(UserNotFoundException::new);
        userCartService.removeFoodFromUsersCart(foodName, user);
        return null;
	}

}
