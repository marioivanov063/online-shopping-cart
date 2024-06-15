package org.bklvsc.shoppingcart.adapters.out;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.port.out.CartRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepositoryImpl implements CartRepository{
	static Set<Cart> carts = new HashSet<>();
	@Override
	public Cart getCart(CartId cartId) {
		return carts.stream()
				.filter(cart -> cart.getCartId()
				.equals(cartId))
				.findAny().orElse(null);
	}


	@Override
	public Cart saveCart(Cart cart) {
		this.carts.add(cart);
		return cart;
	}


	@Override
	public Collection<Cart> getCarts() {
		// TODO Auto-generated method stub
		return null;
	}
}
