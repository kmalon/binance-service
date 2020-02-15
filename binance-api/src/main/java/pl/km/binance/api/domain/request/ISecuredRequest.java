package pl.km.binance.api.domain.request;

import java.util.LinkedHashMap;

interface ISecuredRequest {
    void addQueryParam(String paramName, String paramValue);

    void addQueryParams(LinkedHashMap<String, String> queryParams);

    LinkedHashMap<String, String> getParams();

    String getUrlPathParams();

    LinkedHashMap<String, String> getParamsWithSignature(ISecretKey secretKey, ISignedRequest requestParams);
}