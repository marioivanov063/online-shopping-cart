package org.bklvsc.shoppingcart.cart.application.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.mockito.ArgumentMatchers;
import org.bklvsc.shoppingcart.cart.application.dtos.CartDto;
import org.bklvsc.shoppingcart.cart.application.dtos.FoodItemDto;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.cart.domain.value_objects.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
	public CartDto toCartDto(CartDomainBehaviour cartDomainBehaviour) {
		if(cartDomainBehaviour == null)
			return null;
		CartDomainModel cartDomainModel = cartDomainBehaviour.getModel();
		List<FoodItemDto> foods = toFoodItemDto(cartDomainModel.getFoodItems());
		CartDto cartDto = new CartDto(
				cartDomainModel.getUserId(), 
				cartDomainModel.getTotal().value(), 
				foods);
		return cartDto;
	}

	private List<FoodItemDto> toFoodItemDto(Map<FoodName, FoodItem> foods) {
		return foods.entrySet().stream()
			.map(food -> new FoodItemDto(
					food.getKey().value(), 
					food.getValue().price(),
					food.getValue().quantity()))
			.collect(Collectors.toList());
	}
	
	
}
