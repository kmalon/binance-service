package pl.km.binance.api.domain.request.secured;

import pl.km.binance.api.domain.request.DefaultsParams;
import pl.km.binance.api.domain.security.HmacSha256Singer;
import pl.km.binance.api.domain.security.ISecretKey;

import java.util.LinkedHashMap;

public class SecuredRequest {
    /**
     * Request params in order of putting in
     */
    private LinkedHashMap<String, String> queryParams;

    public SecuredRequest() {
        this.queryParams = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, String> getParams() {
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
     * @return String of params presented as: filed1=valueField1&field2=valueField2
     */
    public String getUrlPathParams() {
        var sb = new StringBuilder();
        var paramsSize = this.queryParams.size() - 1;
        var paramIndex = new int[1];
        this.queryParams.forEach((keyName, value) -> {
            composeAndAddParam(sb, keyName, value);
            addSeparatorIfNotLast(sb, paramsSize, paramIndex);
        });
        return sb.toString();
    }

    private void composeAndAddParam(StringBuilder sb, String keyName, String value) {
        sb.append(keyName).append("=").append(value);
    }

    private void addSeparatorIfNotLast(StringBuilder sb, int paramsSize, int[] paramIndex) {
        if (isNotLastElement(paramsSize, paramIndex[0])) {
            sb.append(DefaultsParams.QUERY_PARAMS_SEPARATOR);
        }
        paramIndex[0]++;
    }

    private boolean isNotLastElement(int paramsSize, int paramIndex) {
        return paramIndex < paramsSize;
    }

    /**
     * Signing request params and adding signature query param
     *
     * @param secretKey for signing purposes
     */
    public void addSignature(ISecretKey secretKey, ISecuredRequestQueryParams requestParams) {
        String signature = HmacSha256Singer.sing(secretKey, requestParams);
        this.queryParams.put(DefaultsParams.SIGNATURE, signature);
    }
}