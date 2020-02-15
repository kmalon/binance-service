package pl.km.binance.api.domain.request;

interface ISecuritySigner {
    static String sign(ISecretKey secretKey, ISignedRequest signedRequest) {
        return HmacSha256Signer.sign(secretKey, signedRequest);
    }
}