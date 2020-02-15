package pl.km.binance.api.domain.request;

import pl.km.binance.api.domain.request.ISignedRequest;
import pl.km.binance.api.domain.request.ISecretKey;
import pl.km.binance.api.client.IBinanceTime;

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