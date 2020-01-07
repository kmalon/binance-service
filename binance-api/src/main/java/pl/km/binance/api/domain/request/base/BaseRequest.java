package pl.km.binance.api.domain.request.base;

import java.util.LinkedHashMap;

/**
 * Base request - contains all query parameters which will be sent to endpoints
 */
public class BaseRequest {
    /**
     * Request params in order of putting in
     */
    private LinkedHashMap<String, String> queryParams;

    public BaseRequest() {
        this.queryParams = new LinkedHashMap<>();
    }

    /**
     * Adding query param to the params list
     *
     * @param paramName
     * @param paramValue
     */
    public void addQueryParam(String paramName, String paramValue) {
        queryParams.put(paramName, paramValue);
    }

    /**
     * @param queryParams which have to be added to request query params
     */
    public void addQueryParams(LinkedHashMap<String, String> queryParams) {
        this.queryParams.putAll(queryParams);
    }

    public LinkedHashMap<String, String> getParams() {
        return new LinkedHashMap<>(queryParams);
    }
}