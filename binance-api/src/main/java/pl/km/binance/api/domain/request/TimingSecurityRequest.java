package pl.km.binance.api.domain.request;

import lombok.extern.slf4j.Slf4j;
import pl.km.binance.api.client.IBinanceTime;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Contains needed parameters for signed endpoints with timing security
 */
@Slf4j
class TimingSecurityRequest implements ITimingSecurity {
    /**
     * Specify the number of milliseconds after serverTime the request is valid for.
     * Not mandatory account information request param - default 5000ms.
     */
    private Long recvWindow;

     TimingSecurityRequest() {
        this(DefaultsParams.RECV_WINDOW_DEFAULT);
    }

     TimingSecurityRequest(Long recvWindow) {
        if (Objects.isNull(recvWindow)) {
            this.recvWindow = DefaultsParams.RECV_WINDOW_DEFAULT;
        } else if (isLargerThanMaxAllowedValueOrIsNegative(recvWindow)) {
            log.warn("RecvWindow cannot be set larger than {} or negative - recvWindow set to {}", DefaultsParams.RECV_WINDOW_MAX, DefaultsParams.RECV_WINDOW_MAX);
            this.recvWindow = DefaultsParams.RECV_WINDOW_MAX;
        } else {
            this.recvWindow = recvWindow;
        }
    }

    private boolean isLargerThanMaxAllowedValueOrIsNegative(long recvWindow) {
        return recvWindow > DefaultsParams.RECV_WINDOW_MAX || recvWindow < 0;
    }

    /**
     * @param binanceTime
     * @return time params needed for invoking secured request, with timestamp param generated while invoking
     * this method not when creating this object.
     */
    public LinkedHashMap<String, String> getTimeParamsForNow(IBinanceTime binanceTime) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put(DefaultsParams.TIMESTAMP, Long.toString(binanceTime.getBinanceTime()));
        params.put(DefaultsParams.RECV_WINDOW, Long.toString(recvWindow));
        return params;
    }
}