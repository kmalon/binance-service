package pl.km.binance.api.security;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import pl.km.binance.api.domain.request.secured.ISignedRequest;
import pl.km.binance.api.domain.security.ISecretKey;

/**
 * Singe request params provided security key with HmacSha256 algorithm
 */
@SuppressWarnings("UnstableApiUsage")
public class HmacSha256Signer /*implements ISecuritySinger*/ {
    /**
     * Signing request params with secret key used for initialize hmacsha256 algorithm.
     *
     * @param secretKey
     * @param signedRequest
     * @return signature of provided params
     */
    public static String sign(ISecretKey secretKey, ISignedRequest signedRequest) {
        HashFunction hmacSha256 = Hashing.hmacSha256(secretKey.toBytes());
        return hmacSha256.hashBytes(signedRequest.getUrlPathParams().getBytes()).toString();
    }
}