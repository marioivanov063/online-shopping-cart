package org.bklvsc.shoppingcart.domain.cart.application.ports.in;

import org.bklvsc.shoppingcart.domain.cart.application.dtos.commands.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.domain.cart.application.dtos.commands.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.UserDomainModel;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;

public interface UserCartService {
	public void addFoodToUsersCart(AddFoodToCartCommand addFoodToCartCommand);
	public void removeFoodFromUsersCart(RemoveFoodFromCartCommand removeFoodFromCartCommand);
}
