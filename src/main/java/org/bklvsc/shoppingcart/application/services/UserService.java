package org.bklvsc.shoppingcart.application.services;

import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.UserId;

public interface UserService {
	public Boolean addFoodToUsersCart(FoodName name, UserId userId);
	public Boolean removeFoodFromUsersCart(FoodName name, UserId userId);
	public void removeFoodFromEveryCart(FoodName name);
}
