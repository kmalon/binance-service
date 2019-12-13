package pl.km.client.binance.domain.request;

import java.util.LinkedHashMap;

/**
 * Getting requests params needed for invoke Binance API REST endpoints.
 */
public interface RequestParams {
    /**
     * @return request params ordered in input order
     */
    LinkedHashMap<String,String> getParams();
}
