package pl.km.binance.api.domain.request;

import org.bouncycastle.util.Strings;

import java.util.Arrays;

/**
 * Binance secret key required for singing requests
 */
public class BinanceSecretKey implements ISecretKey {
    /**
     * Binance Api secretKey related to user
     */
    private final byte[] byteKey;

    public BinanceSecretKey(char[] key) {
        this.byteKey = Strings.toUTF8ByteArray(key);
    }

    /**
     * @return byte array of secret key
     */
    @Override
    public byte[] getKey() {
        return byteKey;
    }

    /**
     * Overwrite secret key and byte[] representation with '*'
     */
    @Override
    public void destroy() {
        Arrays.fill(byteKey, (byte) '*');
    }
}