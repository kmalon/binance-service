package pl.km.client.binance.domain.request;

import java.util.LinkedHashMap;

/**
 * Getting requests query params needed for invoke Binance API REST endpoints.
 */
public interface RequestQueryParams {
    /**
     * @return request query params ordered in input order
     */
    LinkedHashMap<String,String> getParams();
}