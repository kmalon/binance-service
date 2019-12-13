package pl.km.client.binance.domain.request;

/**
 * Defaults values of requests
 */
public class DefaultsParams {
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
     * Default value of recvWindow param, specify the number of milliseconds after serverTime the request is valid for.
     */
    public static final long RECV_WINDOW_DEFAULT = 5000;
    /**
     * Maximum value of recvWindow established by Binance
     */
    public static final long RECV_WINDOW_MAX = 60_000;
}