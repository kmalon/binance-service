package pl.km.binance.api.domain.request.secured;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pl.km.binance.api.domain.request.DefaultsParams;
import pl.km.binance.api.domain.time.BinanceTime;

import java.util.LinkedHashMap;

/**
 * Contains needed parameters for signed endpoints with timing security
 */
@Slf4j
public class TimingSecurityRequest {
    private LinkedHashMap<String, String> params;
    /**
     * Server timestamp of when the request was created and sent.
     * ServerTime is mandatory account information request param
     */
    private long timestamp;
    /**
     * Specify the number of milliseconds after serverTime the request is valid for.
     * Not mandatory account information request param - default 5000ms.
     */
    private long recvWindow;


    public TimingSecurityRequest() {
        this(DefaultsParams.RECV_WINDOW_DEFAULT);
    }

    public TimingSecurityRequest(long recvWindow) {
        this.params = new LinkedHashMap<>();
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

    public LinkedHashMap<String, String> getParams(BinanceTime binanceTime) {
        this.timestamp = binanceTime.getBinanceTime();
        params.put(DefaultsParams.TIMESTAMP, Long.toString(timestamp));
        params.put(DefaultsParams.RECV_WINDOW, Long.toString(recvWindow));
        return params;
    }
}