package org.bklvsc.shoppingcart.cart.application.ports.out;

import java.util.Optional;
import java.util.UUID;

import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;

public interface CartRepository {
	CartDomainBehaviour save(CartDomainBehaviour cart);
	CartDomainBehaviour getCart(UUID userId);
}
