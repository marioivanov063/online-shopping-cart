package org.bklvsc.shoppingcart.domain.port.out.write;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public interface CartWriteRepository {
	Cart saveCart(Cart cart);
}
