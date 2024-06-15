package org.bklvsc.shoppingcart.domain.commons;

import java.util.Objects;
import java.util.UUID;


public abstract class Identity{
	private final UUID id;

	protected Identity() {
		this.id = createId();
	}
	
	protected Identity(String id) {
		this.id = UUID.fromString(id);
	}
	
	public UUID createId() {
		return UUID.randomUUID();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identity other = (Identity) obj;
		return Objects.equals(id, other.id);
	}
}
