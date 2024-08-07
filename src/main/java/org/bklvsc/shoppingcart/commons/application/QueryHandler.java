package org.bklvsc.shoppingcart.commons.application;

public interface QueryHandler<T extends Query, R>{
	public R handle(T query);
}
