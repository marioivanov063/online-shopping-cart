package org.bklvsc.shoppingcart.commons.application;

@FunctionalInterface
public interface CommandHandler<T extends Command, R>{
	public R handle(T command);
}
