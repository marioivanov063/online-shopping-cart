package org.bklvsc.shoppingcart.domain.port.in.queries;

public interface QueryHandler<T extends Query, R>{
	public R handle(T query);
}
