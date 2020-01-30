package pl.km.binance.api.domain.security;

import lombok.Getter;
import org.bouncycastle.util.Strings;

import java.util.Arrays;

/**
 * Binance secret key required for singing requests
 */
public class BinanceSecretKey implements ISecretKey {
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
    @Override
    public byte[] toBytes() {
        return byteKey;
    }

    /**
     * Overwrite secret key and byte[] representation with '*'
     */
    @Override
    public void destroy() {
        Arrays.fill(key, '*');
        Arrays.fill(byteKey, (byte) '*');
    }
}