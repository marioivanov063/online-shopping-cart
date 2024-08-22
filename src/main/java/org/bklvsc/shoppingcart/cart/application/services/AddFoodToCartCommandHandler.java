package org.bklvsc.shoppingcart.cart.application.services;

import java.util.UUID;

import org.bklvsc.shoppingcart.cart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.cart.application.mapper.CartMapper;
import org.bklvsc.shoppingcart.cart.application.dtos.CartDto;
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
import org.bklvsc.shoppingcart.cart.domain.value_objects.FoodItem;
import org.bklvsc.shoppingcart.commons.application.CommandHandler;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.mockito.ArgumentMatchers;
import org.springframework.stereotype.Service;

@Service
public class AddFoodToCartCommandHandler implements CommandHandler<AddFoodToCartCommand, CartDto>{
	private FoodRepository foodRepository;
	private CartRepository cartRepository;
	private CartMapper cartMapper;
		
	public AddFoodToCartCommandHandler(FoodRepository foodRepository, CartRepository cartRepository, CartMapper cartMapper) {
		this.cartRepository = cartRepository;
		this.foodRepository = foodRepository;
		this.cartMapper = cartMapper;
	}	
	@Override
	public CartDto handle(AddFoodToCartCommand command) {
		CartDomainBehaviour cart = cartRepository.getCart(command.userId());
		if(cart == null)//user does not have a cart
			cart = new CartDomainBehaviour(command.userId().toString());
		
		FoodName foodItem = cart.getFood(command.foodName());
		if(foodItem != null)
			cart.incrementQuantity(foodItem);
		else {
			FoodDomainModel food = foodRepository.getFood(command.foodName())
		        	.orElseThrow(FoodNotFoundException::new);
			cart.addFood(food.getFoodName(), food.getFoodPrice());
		}	 	
        cart = cartRepository.save(cart);
        return cartMapper.toCartDto(cart);
	}
}
