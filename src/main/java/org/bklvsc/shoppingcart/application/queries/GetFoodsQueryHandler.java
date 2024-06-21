package org.bklvsc.shoppingcart.application.queries;

import java.util.List;
import java.util.stream.Collectors;

import org.bklvsc.shoppingcart.domain.port.in.queries.GetFoodsQuery;
import org.bklvsc.shoppingcart.domain.port.in.queries.QueryHandler;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.FoodDto;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;

public class GetFoodsQueryHandler implements QueryHandler<GetFoodsQuery, List<FoodDto>>{
	private FoodReadRepository foodReadRepository;
	
	public GetFoodsQueryHandler(FoodReadRepository foodReadRepository) {
		this.foodReadRepository = foodReadRepository;
	}

	@Override
	public List<FoodDto> handle(GetFoodsQuery query) {
		return this.foodReadRepository.getFoods().stream()
			.map(food -> new FoodDto(food.getFoodName().name(), 
									 food.getFoodPrice().price(),
									 food.getFoodQuantity().quantity()))
			.collect(Collectors.toList());
				
	}

}
