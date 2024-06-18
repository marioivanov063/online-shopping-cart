package org.bklvsc.shoppingcart.domain.port.in.commands;

@FunctionalInterface
public interface CommandHandler<T extends Command, R>{
	public R handle(T command);
}
