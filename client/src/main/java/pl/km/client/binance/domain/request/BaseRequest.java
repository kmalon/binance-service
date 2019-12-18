package pl.km.client.binance.domain.request;

import java.util.LinkedHashMap;

/**
 * Base request - contains all query parameters which will be sent to endpoints
 */
public abstract class BaseRequest implements RequestQueryParams {
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
    protected void addQueryParam(String paramName, String paramValue) {
        queryParams.put(paramName, paramValue);
    }

    /**
     * @param queryParams which have to be added to request query params
     */
    protected void addQueryParams(LinkedHashMap<String, String> queryParams) {
        this.queryParams.putAll(queryParams);
    }

    /**
     * Fill request param map which will be used during invoking Binance API REST requests
     */
    protected abstract void fillParams();

    @Override
    public LinkedHashMap<String, String> getParams() {
        return this.queryParams;
    }
}
