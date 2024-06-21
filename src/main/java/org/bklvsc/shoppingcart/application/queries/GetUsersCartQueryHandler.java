package org.bklvsc.shoppingcart.application.queries;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.queries.GetUsersCart;
import org.bklvsc.shoppingcart.domain.port.in.queries.QueryHandler;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.FoodDto;
import org.bklvsc.shoppingcart.domain.port.out.read.CartReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.read.UserReadRepository;
import org.bklvsc.shoppingcart.domain.user.User;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

public class GetUsersCartQueryHandler implements QueryHandler<GetUsersCart, Map<FoodDto, Integer>>{
	private UserReadRepository userReadRepository;
	private CartReadRepository cartReadRepository;
	private FoodReadRepository foodReadRepository;

	public GetUsersCartQueryHandler(UserReadRepository userReadRepository, CartReadRepository cartReadRepository, FoodReadRepository foodReadRepository) {
		this.userReadRepository = userReadRepository;
		this.cartReadRepository = cartReadRepository;
		this.foodReadRepository = foodReadRepository;
	}


	@Override
	public Map<FoodDto, Integer> handle(GetUsersCart query) {
		UserId userId = UserId.from(query.userId());
		User user = userReadRepository.getUser(userId)
				.orElseThrow(UserNotFoundException::new);
		
		if(user.getCartId() == null)
			return Map.of();
		Cart cart = cartReadRepository.getCart(user.getCartId());
		
		List<Food> foods = cart.getFoods().keySet().stream()
			.map(name -> foodReadRepository.getFood(name).orElse(null))
			.collect(Collectors.toList());
		

	}

}
