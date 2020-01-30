package pl.km.binance.api.domain.security;

public interface ISecretKey {
    byte[] toBytes();

    void destroy();

}
