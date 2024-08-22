package org.bklvsc.shoppingcart.cart.application.services;
import java.util.UUID;

import org.bklvsc.shoppingcart.cart.application.dtos.CartDto;
import org.bklvsc.shoppingcart.cart.application.dtos.remove_from_cart.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.cart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.cart.application.mapper.CartMapper;
import org.bklvsc.shoppingcart.cart.application.ports.in.UserCartService;
import org.bklvsc.shoppingcart.cart.application.ports.out.CartRepository;
import org.bklvsc.shoppingcart.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.UserDomainModel;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.commons.application.CommandHandler;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.bklvsc.shoppingcart.user.domain.entities.UserDomainBehaviour;
import org.springframework.stereotype.Service;

@Service
public class RemoveFoodFromCartCommandHandler implements CommandHandler<RemoveFoodFromCartCommand, CartDto>{
	private CartMapper cartMapper;
	private CartRepository cartRepository;
	
	public RemoveFoodFromCartCommandHandler(CartRepository cartRepository, CartMapper cartMapper) {
		this.cartRepository = cartRepository;
		this.cartMapper = cartMapper;
	}

	@Override
	public CartDto handle(RemoveFoodFromCartCommand command) {
		CartDomainBehaviour cart = cartRepository.getCart(command.userId());
		if(cart == null)
		   	return null;
		cart.removeFood(new FoodName(command.foodName()));
		cart = cartRepository.save(cart);
		return cartMapper.toCartDto(cart);
	}
}
