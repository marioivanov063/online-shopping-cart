package org.bklvsc.shoppingcart.domain.port.out;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public interface CartRepository {
	public Cart getCart(CartId cartId);
	public Cart saveCart(Cart cart);
	public Collection<Cart> getCarts();
}
