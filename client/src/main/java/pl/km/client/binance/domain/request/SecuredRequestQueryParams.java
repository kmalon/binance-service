package pl.km.client.binance.domain.request;

import pl.km.client.binance.domain.security.BinanceSecretKey;
import pl.km.client.binance.domain.security.HmacSha256Singer;

import java.util.LinkedHashMap;

/**
 * Request params used for secured endpoints where signature is required
 */
public interface SecuredRequestQueryParams extends RequestQueryParams {

    /**
     * @return byte[] of params presented as: filed1=valueField1&field2=valueField2
     */
    default byte[] getRequestQueryParamsStringUrlAsBytes() {
        StringBuilder sb = new StringBuilder();
        LinkedHashMap<String, String> params = this.getParams();
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
    default void addSignature(BinanceSecretKey binanceSecretKey) {
        String signature = HmacSha256Singer.sing(binanceSecretKey, this);
        this.getParams().put(DefaultsParams.SIGNATURE, signature);
    }
}