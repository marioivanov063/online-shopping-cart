package org.bklvsc.shoppingcart.cart.application.services;

import java.util.UUID;

import org.bklvsc.shoppingcart.cart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.cart.application.dtos.add_to_cart.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.cart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.cart.application.ports.in.UserCartService;
import org.bklvsc.shoppingcart.cart.application.ports.out.CartRepository;
import org.bklvsc.shoppingcart.cart.application.ports.out.FoodRepository;
import org.bklvsc.shoppingcart.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.UserDomainModel;
import org.bklvsc.shoppingcart.commons.application.CommandHandler;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.bklvsc.shoppingcart.user.domain.entities.UserDomainBehaviour;
import org.mockito.ArgumentMatchers;
import org.springframework.stereotype.Service;

@Service
public class AddFoodToCartCommandHandler implements CommandHandler<AddFoodToCartCommand, CartDomainModel>{
	private FoodRepository foodRepository;
	private UserRepository userRepository;
	private CartRepository cartRepository;
		
	public AddFoodToCartCommandHandler(FoodRepository foodRepository, CartRepository cartRepository, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.foodRepository = foodRepository;
	}	
	@Override
	public CartDomainModel handle(AddFoodToCartCommand command) {
		UserDomainModel userExists = userRepository.getUser(command.userId())
				.orElseThrow(UserNotFoundException::new);
		CartDomainModel cart = cartRepository.getCart(command.userId());
		
		if(cart == null){ //user does not have a cart
			cart = new CartDomainModel(command.userId().toString());
		 	FoodDomainModel food = foodRepository.getFood(command.foodName())
        		.orElseThrow(FoodNotFoundException::new);
        	cart.addFood(food.getFoodName(), food.getFoodPrice());
		}
        else {
        	FoodName food = new FoodName(command.foodName());
        	if(cart.foodExists(food))
        		cart.incrementQuantity(food);
        	else {
        		FoodDomainModel foodModel = foodRepository.getFood(command.foodName())
            		.orElseThrow(FoodNotFoundException::new);
            	cart.addFood(foodModel.getFoodName(), foodModel.getFoodPrice());
        	}
        }
        cart = cartRepository.save(cart);
        return cart;
	}
}
