package org.bklvsc.shoppingcart.domain.port.out.write;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.user.User;
import org.bklvsc.shoppingcart.domain.user.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;

public interface UserWriteRepository {
	public User saveUser(User user);
}
