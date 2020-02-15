package pl.km.binance.api.domain.request;

import pl.km.binance.api.client.IBinanceTime;

import java.util.LinkedHashMap;

/**
 * Request used for secured endpoints where signature is required
 */
interface ISignedRequest {

    /**
     * @return request query params with signature for current time
     */
    LinkedHashMap<String, String> getParamsWithSignature(ISecretKey secretKey, IBinanceTime binanceTime);

    /**
     * @return String of path params presented as: filed1=valueField1&field2=valueField2
     */
    String getUrlPathParams();
}