package pl.km.binance.api.domain.request.base;

import java.util.LinkedHashMap;

/**
 * Getting requests query params needed for invoke Binance API REST endpoints.
 */
public interface IRequestQueryParams {
    /**
     * @return request query params ordered in input order
     */
    LinkedHashMap<String, String> getParams();
}