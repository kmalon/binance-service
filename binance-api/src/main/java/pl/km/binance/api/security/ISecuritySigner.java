package pl.km.binance.api.security;

import pl.km.binance.api.domain.request.secured.ISecuredRequestQueryParams;
import pl.km.binance.api.domain.security.ISecretKey;

public interface ISecuritySigner {
    static String sign(ISecretKey secretKey, ISecuredRequestQueryParams requestParams) {
        return HmacSha256Signer.sign(secretKey, requestParams);
    }
}