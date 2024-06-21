package org.bklvsc.shoppingcart.application.services;

import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public interface UserService {
	public Boolean addFoodToUsersCart(FoodName name, UserId userId);
	public Boolean removeFoodFromUsersCart(FoodName name, UserId userId);
	public void removeFoodFromEveryCart(FoodName name);
}
