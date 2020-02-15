package pl.km.binance.api.domain.request;

public interface ISecretKey {
    byte[] getKey();

    void destroy();

}
