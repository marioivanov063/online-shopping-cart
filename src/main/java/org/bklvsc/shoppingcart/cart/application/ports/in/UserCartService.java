package org.bklvsc.shoppingcart.cart.application.ports.in;

import org.bklvsc.shoppingcart.cart.application.dtos.CartDto;
import org.bklvsc.shoppingcart.cart.application.dtos.add_to_cart.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.cart.application.dtos.remove_from_cart.RemoveFoodFromCartCommand;

public interface UserCartService {
	public CartDto addFoodToUsersCart(AddFoodToCartCommand addFoodToCartCommand);
	public CartDto removeFoodFromUsersCart(RemoveFoodFromCartCommand removeFoodFromCartCommand);
}
