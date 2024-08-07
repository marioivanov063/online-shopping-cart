package org.bklvsc.shoppingcart.commons.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot<T extends DomainEvent>{
	private final List<T> domainEvents = new ArrayList<>();
	
	protected void publish(T event) {
		this.domainEvents.add(event);
	}
	
	public List<T> getDomainEvents(){
		return Collections.unmodifiableList(domainEvents);
	}
}
