package org.bklvsc.shoppingcart.adapters.out.write;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bklvsc.shoppingcart.adapters.out.common.Carts;
import org.bklvsc.shoppingcart.application.services.CartService;
import org.bklvsc.shoppingcart.application.services.UserService;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.port.out.write.CartWriteRepository;
import org.bklvsc.shoppingcart.domain.user.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class CartWriteRepositoryImpl implements CartWriteRepository{
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public Cart saveCart(Cart cart) {
		Carts.carts.remove(cart);
		Carts.carts.add(cart);
		return cart;
	}

}
