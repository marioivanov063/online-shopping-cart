package org.bklvsc.shoppingcart.domain.port.in.commands;


public interface CommandHandler<T extends Command, R>{
	public R handle(T command);
}
