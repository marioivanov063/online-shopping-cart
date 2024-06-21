package org.bklvsc.shoppingcart.application.queries;

import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.queries.GetFoodQuery;
import org.bklvsc.shoppingcart.domain.port.in.queries.QueryHandler;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.FoodDto;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public class GetFoodQueryHandler implements QueryHandler<GetFoodQuery, FoodDto>{
	private FoodReadRepository foodReadRepository;
	
	public GetFoodQueryHandler(FoodReadRepository foodReadRepository) {
		this.foodReadRepository = foodReadRepository;
	}

	@Override
	public FoodDto handle(GetFoodQuery query) {
		FoodName foodName = new FoodName(query.foodName());
		return this.foodReadRepository.getFood(foodName)
			.map(food -> new FoodDto(food.getFoodName().name(), 
									 food.getFoodPrice().price(), 
									 food.getFoodQuantity().quantity()))
				.orElse(null);
	}

}
