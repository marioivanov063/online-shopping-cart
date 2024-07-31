package org.bklvsc.shoppingcart.domain.port.out.write;

import java.util.Optional;

import org.bklvsc.shoppingcart.domain.cart.CartDomainBehaviour;
import org.bklvsc.shoppingcart.domain.cart.CartDomainModel;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.CartId;

public interface CartRepository {
	void save(CartDomainBehaviour cart);
	Optional<CartDomainBehaviour> getCart(CartId cartId);
}
