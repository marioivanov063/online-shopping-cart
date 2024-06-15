package org.bklvsc.shoppingcart.application.services;

import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.entities.User;
import org.bklvsc.shoppingcart.domain.port.out.CartRepository;
import org.bklvsc.shoppingcart.domain.port.out.UserRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.UserId;

public class UserServiceImpl implements UserService{
	private CartRepository cartRepository;
	private UserRepository userRepository;

	public UserServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Boolean addFoodToUsersCart(FoodName foodName, UserId userId) {
		User user = userRepository.getUser(userId)
				.orElseThrow(() -> new UserNotFoundException());
		Cart cart = null;
        if(user.getCartId() == null) {
        	cart = Cart.with(foodName);
        	user.assignCart(cart.getCartId());	
        	cartRepository.saveCart(cart);
        	return Boolean.TRUE;
        }
        
        cart = cartRepository.getCart(user.getCartId());
        return cart.addFood(foodName);
	}

	@Override
	public Boolean removeFoodFromUsersCart(FoodName foodName, UserId userId) {
		User user = userRepository.getUser(userId)
				.orElseThrow(() -> new UserNotFoundException());
		if(user.getCartId() == null)
        	return Boolean.FALSE;
        Cart cart = cartRepository.getCart(user.getCartId());
        
        Boolean remove = cart.removeFood(foodName);
        if(cart.getFoods().size() == 0)
        	user.assignCart(null);
        return remove;	
	}

}
