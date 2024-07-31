package org.bklvsc.shoppingcart.domain.commons;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

import org.mockito.ArgumentMatchers;


public abstract class Identity{
	private UUID id;
	
	public UUID getId() {
		return id;
	}
	
	protected Identity(String string) {
		if(string != null)
			this.id = UUID.fromString(string);
	}

	protected Identity() {
		this.id = UUID.randomUUID();
	}
	
	protected Identity(UUID uuid) {
		this.id = uuid;
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

	@Override
	public String toString() {
		return this.id.toString();
	}
	
	
}
