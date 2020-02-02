package pl.km.binance.api.domain.request.secured;

import pl.km.binance.api.domain.security.ISecretKey;

import java.util.LinkedHashMap;

public interface ISecuredRequest {
    void addQueryParam(String paramName, String paramValue);

    void addQueryParams(LinkedHashMap<String, String> queryParams);

    LinkedHashMap<String, String> getParams();

    String getUrlPathParams();

    LinkedHashMap<String, String> getParamsWithSignature(ISecretKey secretKey, ISignedRequest requestParams);
}