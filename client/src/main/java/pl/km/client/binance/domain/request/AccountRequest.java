package pl.km.client.binance.domain.request;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pl.km.client.binance.domain.security.BinanceSecretKey;
import pl.km.client.binance.domain.security.HmacSha256Singer;

import java.util.LinkedHashMap;

/**
 * Account information request params
 */
@Slf4j
@Getter
public class AccountRequest implements SecuredRequestParams {
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
    /**
     * Request params in order of putting in
     */
    private LinkedHashMap<String, String> params;

    public AccountRequest(long timestamp) {
        this(timestamp, DefaultsParams.RECV_WINDOW_DEFAULT);
    }

    public AccountRequest(long timestamp, long recvWindow) {
        this.timestamp = timestamp;
        if (isLargerThanMaxAllowedValue(recvWindow)) {
            log.warn("RecvWindow cannot be set larger than {} - recvWindow set to {}", DefaultsParams.RECV_WINDOW_MAX, DefaultsParams.RECV_WINDOW_MAX);
            this.recvWindow = DefaultsParams.RECV_WINDOW_MAX;
        } else {
            this.recvWindow = recvWindow;
        }
        this.params = new LinkedHashMap<>();
        fillParams();
    }

    /**
     * Checks if recvWindow param is not larger than allowed
     *
     * @param recvWindow to set
     * @return
     */
    private boolean isLargerThanMaxAllowedValue(long recvWindow) {
        return recvWindow > DefaultsParams.RECV_WINDOW_MAX;
    }

    /**
     * Fill request param map which will be used during invoking Binance API REST requests
     */
    private void fillParams() {
        params.put(DefaultsParams.TIMESTAMP, Long.toString(timestamp));
        params.put(DefaultsParams.RECV_WINDOW, Long.toString(recvWindow));
    }

    @Override
    public LinkedHashMap<String, String> getParams() {
        return params;
    }

    @Override
    public void addSignature(BinanceSecretKey binanceSecretKey) {
        String signature = HmacSha256Singer.sing(binanceSecretKey, this);
        params.put(DefaultsParams.SIGNATURE, signature);
    }
}