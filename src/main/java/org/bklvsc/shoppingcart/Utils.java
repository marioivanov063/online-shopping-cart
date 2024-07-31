package org.bklvsc.shoppingcart;

import java.nio.ByteBuffer;
import java.util.UUID;

public class Utils {
	public static byte[] uuidToBytes(UUID uuid) {
	    ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
	    byteBuffer.putLong(uuid.getMostSignificantBits());
	    byteBuffer.putLong(uuid.getLeastSignificantBits());
	    return byteBuffer.array();
	}
	
	public static UUID bytesToUuid(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long mostSigBits = byteBuffer.getLong();
        long leastSigBits = byteBuffer.getLong();
        return new UUID(mostSigBits, leastSigBits);
    }

}
