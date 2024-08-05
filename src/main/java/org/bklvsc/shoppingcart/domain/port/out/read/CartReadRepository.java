package org.bklvsc.shoppingcart.domain.port.out.read;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.CartDomainModel;
import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;

public interface CartReadRepository {
	Optional<CartDomainModel> getCartById(CartId cartId);
}
