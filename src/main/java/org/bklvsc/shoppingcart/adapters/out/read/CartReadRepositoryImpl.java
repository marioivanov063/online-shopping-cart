package org.bklvsc.shoppingcart.adapters.out.read;

import java.util.Collection;

import org.bklvsc.shoppingcart.adapters.out.common.Carts;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.port.out.read.CartReadRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.springframework.stereotype.Repository;

@Repository
class CartReadRepositoryImpl implements CartReadRepository{
	@Override
	public Collection<Cart> getCarts() {
		return Carts.carts;
	}
	
	@Override
	public Cart getCart(CartId cartId) {
		return Carts.carts.stream()
				.filter(cart -> cart.getCartId()
				.equals(cartId))
				.findAny().orElse(null);
	}
	
	
}
