package pl.km.binance.api.domain.security;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import pl.km.binance.api.domain.request.secured.SecuredRequestQueryParams;

/**
 * Singe request params provided security key with HmacSha256 algorithm
 */
@SuppressWarnings("UnstableApiUsage")
public class HmacSha256Singer {
    /**
     * Signing request params with secret key used for initialize hmacsha256 algorithm.
     * After sign secret key is cleared from memory.
     *
     * @param binanceSecretKey
     * @param requestParams
     * @return signature of provided params
     */
    public static String sing(BinanceSecretKey binanceSecretKey, SecuredRequestQueryParams requestParams) {
        HashFunction hmacSha256 = Hashing.hmacSha256(binanceSecretKey.toBytes());
        String signature = hmacSha256.hashBytes(requestParams.getRequestQueryParamsStringUrlAsBytes()).toString();
        binanceSecretKey.destroy();
        return signature;
    }
}