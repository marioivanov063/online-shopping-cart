package org.bklvsc.shoppingcart.cart.application.ports.out;


import java.util.Optional;
import java.util.UUID;

import org.bklvsc.shoppingcart.cart.domain.entities.UserDomainModel;

public interface UserRepository {
	public Optional<UserDomainModel> getUser(UUID userId);
}
