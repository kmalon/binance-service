package pl.km.client.binance.domain.request;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;

/**
 * Contains needed parameters for signed endpoints with timing security
 */
@Slf4j
@Getter
public class TimingSecurityRequest implements RequestQueryParams {
    /**
     * Server timestamp of when the request was created and sent.
     * ServerTime is mandatory account information request param
     */
    private final long timestamp;
    /**
     * Specify the number of milliseconds after serverTime the request is valid for.
     * Not mandatory account information request param - default 5000ms.
     */
    private long recvWindow;


    public TimingSecurityRequest(long timestamp) {
        this(timestamp, DefaultsParams.RECV_WINDOW_DEFAULT);
    }

    public TimingSecurityRequest(long timestamp, long recvWindow) {
        this.timestamp = timestamp;
        if (isLargerThanMaxAllowedValue(recvWindow)) {
            log.warn("RecvWindow cannot be set larger than {} - recvWindow set to {}", DefaultsParams.RECV_WINDOW_MAX, DefaultsParams.RECV_WINDOW_MAX);
            this.recvWindow = DefaultsParams.RECV_WINDOW_MAX;
        } else {
            this.recvWindow = recvWindow;
        }
    }

    private boolean isLargerThanMaxAllowedValue(long recvWindow) {
        return recvWindow > DefaultsParams.RECV_WINDOW_MAX;
    }

    @Override
    public LinkedHashMap<String, String> getParams() {
        LinkedHashMap<String, String> queryParams = new LinkedHashMap<>();
        queryParams.put(DefaultsParams.TIMESTAMP, Long.toString(timestamp));
        queryParams.put(DefaultsParams.RECV_WINDOW, Long.toString(recvWindow));
        return queryParams;
    }
}