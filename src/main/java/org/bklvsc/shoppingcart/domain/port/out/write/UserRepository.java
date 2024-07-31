package org.bklvsc.shoppingcart.domain.port.out.write;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.UserDomainModel;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;

public interface UserRepository {
	public void save(UserDomainBehaviour user);
	public Optional<UserDomainBehaviour> getUser(UserId userId);
	public Collection<UserDomainBehaviour> getAllUsers();
}
