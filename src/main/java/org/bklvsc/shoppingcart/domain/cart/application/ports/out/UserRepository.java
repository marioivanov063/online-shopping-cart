package org.bklvsc.shoppingcart.domain.cart.application.ports.out;


import java.util.Optional;
import java.util.UUID;

import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.UserDomainModel;

public interface UserRepository {
	public Optional<UserDomainModel> getUser(UUID userId);
}
