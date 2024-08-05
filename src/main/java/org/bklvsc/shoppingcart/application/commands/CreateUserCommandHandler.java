package org.bklvsc.shoppingcart.application.commands;

import org.bklvsc.shoppingcart.domain.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateUserCommand;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserName;
import org.springframework.stereotype.Service;

@Service
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, String>{
	private UserRepository userRepository;
	
	public CreateUserCommandHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String handle(CreateUserCommand command) {
		UserDomainBehaviour user = 
				new UserDomainBehaviour(new UserName(command.name()));
		userRepository.save(user);
		return user.getModel().getUserId().toString();
	}

}
