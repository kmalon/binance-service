package pl.km.client.binance.domain.request;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Defaults values of requests
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultsParams {
    /**
     * params separator e.g filed1=value1<SEPARATOR>field2=value2
     */
    public static final String QUERY_PARAMS_SEPARATOR = "&";
    /**
     * Name of server time (timestamp) param
     */
    public static final String TIMESTAMP = "timestamp";
    /**
     * Name of recvWindow param
     */
    public static final String RECV_WINDOW = "recvWindow";

    /**
     * Name of param with signature
     */
    public static final String SIGNATURE = "signature";

    /**
     * Name of symbol param
     */
    public static final String SYMBOL = "symbol";
    /**
     * Default value of recvWindow param, specify the number of milliseconds after serverTime the request is valid for.
     */
    public static final long RECV_WINDOW_DEFAULT = 5000;
    /**
     * Maximum value of recvWindow established by Binance
     */
    public static final long RECV_WINDOW_MAX = 60_000;
}