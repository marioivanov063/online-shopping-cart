package org.bklvsc.shoppingcart.adapters.out.read;

import java.util.Collection;

import org.bklvsc.shoppingcart.adapters.out.common.Carts;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.port.out.read.CartReadRepository;
import org.bklvsc.shoppingcart.domain.user.valueobjects.CartId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class CartReadRepositoryImpl implements CartReadRepository{
	@Autowired
	private JdbcTemplate template;
	
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
