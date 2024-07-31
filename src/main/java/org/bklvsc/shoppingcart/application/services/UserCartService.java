package org.bklvsc.shoppingcart.application.services;

import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.UserDomainModel;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;

public interface UserCartService {
	public void addFoodToUsersCart(FoodName foodName, UserDomainBehaviour user);
	public void removeFoodFromUsersCart(FoodName foodName, UserDomainBehaviour user);
}
