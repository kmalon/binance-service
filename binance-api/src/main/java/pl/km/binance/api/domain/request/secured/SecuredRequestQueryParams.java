package pl.km.binance.api.domain.request.secured;

import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.domain.time.BinanceTime;

import java.util.LinkedHashMap;

/**
 * Request params used for secured endpoints where signature is required
 */
public interface SecuredRequestQueryParams {

    /**
     * @return request query params ordered in input order
     */
    LinkedHashMap<String, String> getParams(BinanceSecretKey binanceSecretKey, BinanceTime binanceTime);

    /**
     * @return byte[] of params presented as: filed1=valueField1&field2=valueField2
     */
    byte[] getRequestQueryParamsStringUrlAsBytes();
}