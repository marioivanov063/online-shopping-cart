package org.bklvsc.shoppingcart.domain.port.out.read;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;

public interface CartReadRepository {
	Cart getCart(CartId cartId);
	Collection<Cart> getCarts();
}
