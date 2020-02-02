package pl.km.binance.api.domain.request.secured;

import pl.km.binance.api.domain.security.ISecretKey;
import pl.km.binance.api.domain.time.IBinanceTime;

import java.util.LinkedHashMap;

/**
 * Request used for secured endpoints where signature is required
 */
public interface ISignedRequest {

    /**
     * @return request query params with signature for current time
     */
    LinkedHashMap<String, String> getParamsWithSignature(ISecretKey secretKey, IBinanceTime binanceTime);

    /**
     * @return String of path params presented as: filed1=valueField1&field2=valueField2
     */
    String getUrlPathParams();
}