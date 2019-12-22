package pl.km.binance.api.domain.request.secured;

import pl.km.binance.api.domain.request.DefaultsParams;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.domain.security.HmacSha256Singer;

import java.util.LinkedHashMap;

public class SecuredRequest {
    /**
     * Request params in order of putting in
     */
    private LinkedHashMap<String, String> queryParams;

    public SecuredRequest() {
        this.queryParams = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, String> getQueryParams() {
        return new LinkedHashMap<>(queryParams);
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

    /**
     * @return byte[] of params presented as: filed1=valueField1&field2=valueField2
     */
    public byte[] getRequestQueryParamsStringUrlBytes() {
        StringBuilder sb = new StringBuilder();
        LinkedHashMap<String, String> params = this.queryParams;
        int paramsSize = params.size() - 1;
        int[] paramIndex = new int[1];
        params.forEach((keyName, value) -> {
            sb.append(keyName).append("=").append(value);
            if (paramIndex[0] < paramsSize) {
                sb.append(DefaultsParams.QUERY_PARAMS_SEPARATOR);
            }
            paramIndex[0]++;
        });
        return sb.toString().getBytes();
    }

    /**
     * Signing request params and adding signature query param
     *
     * @param binanceSecretKey for signing purposes
     */
    public void addSignature(BinanceSecretKey binanceSecretKey, SecuredRequestQueryParams requestParams) {
        String signature = HmacSha256Singer.sing(binanceSecretKey, requestParams);
        this.queryParams.put(DefaultsParams.SIGNATURE, signature);
    }
}
