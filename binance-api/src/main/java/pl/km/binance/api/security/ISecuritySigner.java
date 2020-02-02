package pl.km.binance.api.security;

import pl.km.binance.api.domain.request.secured.ISignedRequest;
import pl.km.binance.api.domain.security.ISecretKey;

public interface ISecuritySigner {
    static String sign(ISecretKey secretKey, ISignedRequest signedRequest) {
        return HmacSha256Signer.sign(secretKey, signedRequest);
    }
}