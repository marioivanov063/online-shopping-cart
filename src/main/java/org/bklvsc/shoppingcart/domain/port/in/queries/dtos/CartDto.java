package org.bklvsc.shoppingcart.domain.port.in.queries.dtos;

import java.util.List;

public record CartDto(
			String userId,
			String cartId,
			double cartTotal,
			List<FoodInCartDto> foods
		) {}
