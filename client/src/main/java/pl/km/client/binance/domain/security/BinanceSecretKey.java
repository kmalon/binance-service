package pl.km.client.binance.domain.security;

import lombok.Getter;
import org.bouncycastle.util.Strings;

import java.util.Arrays;

/**
 * Binance secret key required for singing requests
 */
public class BinanceSecretKey {
    /**
     * Binance Api secretKey related to user
     */
    @Getter
    private final char[] key;
    /**
     * byte representation of key - needed for singing purposes
     */
    private final byte[] byteKey;

    public BinanceSecretKey(char[] key) {
        this.key = key;
        this.byteKey = Strings.toUTF8ByteArray(key);
    }

    /**
     * @return byte array of secret key
     */
    public byte[] toBytes() {
        return byteKey;
    }

    /**
     * Overwrite secret key and byte[] representation with '*'
     */
    public void destroy() {
        Arrays.fill(key, '*');
        Arrays.fill(byteKey, (byte) '*');
    }
}