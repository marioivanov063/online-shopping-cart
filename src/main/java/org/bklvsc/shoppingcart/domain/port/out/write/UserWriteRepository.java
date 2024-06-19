package org.bklvsc.shoppingcart.domain.port.out.write;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.entities.User;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.UserId;

public interface UserWriteRepository {
	public User saveUser(User user);
}
