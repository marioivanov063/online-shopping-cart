package org.bklvsc.shoppingcart.domain.cart.application.ports.out;

import java.util.Optional;
import java.util.UUID;

import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.CartDomainModel;
import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;

public interface CartRepository {
	CartDomainModel save(CartDomainModel cart);
	CartDomainModel getCart(UserId cartId);
	void remove(CartDomainModel cart);
}
