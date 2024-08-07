package org.bklvsc.shoppingcart.cart.application.ports.in;

import org.bklvsc.shoppingcart.cart.application.dtos.add_to_cart.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.cart.application.dtos.remove_from_cart.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.catalog.domain.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.bklvsc.shoppingcart.user.domain.entities.UserDomainBehaviour;
import org.bklvsc.shoppingcart.user.domain.entities.UserDomainModel;

public interface UserCartService {
	public void addFoodToUsersCart(AddFoodToCartCommand addFoodToCartCommand);
	public void removeFoodFromUsersCart(RemoveFoodFromCartCommand removeFoodFromCartCommand);
}
