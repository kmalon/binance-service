package pl.km.client.binance.domain.request.config;

import pl.km.binance.api.domain.request.secured.ISignedRequest;
import pl.km.binance.api.domain.security.ISecretKey;
import pl.km.binance.api.domain.time.IBinanceTime;

import java.util.LinkedHashMap;

public class TestSignedRequest implements ISignedRequest {

    private String urlPathParams;

    public TestSignedRequest(String urlPathParams) {
        this.urlPathParams = urlPathParams;
    }

    @Override
    public LinkedHashMap<String, String> getParamsWithSignature(ISecretKey secretKey, IBinanceTime binanceTime) {
        return null;
    }

    @Override
    public String getUrlPathParams() {
        return urlPathParams;
    }
}
