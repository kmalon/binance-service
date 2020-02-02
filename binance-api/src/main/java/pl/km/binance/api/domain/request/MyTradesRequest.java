package pl.km.binance.api.domain.request;

import lombok.Builder;
import pl.km.binance.api.domain.exchange.general.Symbol;
import pl.km.binance.api.domain.request.secured.*;
import pl.km.binance.api.domain.security.ISecretKey;
import pl.km.binance.api.domain.time.IBinanceTime;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Request for GET /myTrades endpoint
 */
public class MyTradesRequest implements ISignedRequest {
    private final Symbol symbol;
    private Long startTime;
    private Long endTime;
    private Long fromId;
    private Integer limit;
    private ITimingSecurity timingSecurity;
    private ISecuredRequest securedRequest;

    /**
     * All Args constructor and builder created based on its parameters
     *
     * @param symbolName
     * @param recvWindow
     * @param startTime
     * @param endTime
     * @param fromId
     * @param limit
     */
    @Builder
    public MyTradesRequest(String symbolName, Long recvWindow, Long startTime, Long endTime, Long fromId, Integer limit) {
        this.timingSecurity = new TimingSecurityRequest(recvWindow);
        this.symbol = new Symbol(symbolName);
        this.securedRequest = new SecuredRequest();
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromId = fromId;
        this.limit = limit;
        this.fillQueryParams();
    }

    public MyTradesRequest(String symbol) {
        this(DefaultsParams.RECV_WINDOW_DEFAULT, symbol);
    }

    public MyTradesRequest(Long recvWindow, String symbol) {
        this.timingSecurity = new TimingSecurityRequest(recvWindow);
        this.symbol = new Symbol(symbol);
        this.securedRequest = new SecuredRequest();
        this.fillQueryParams();
    }

    private void fillQueryParams() {
        this.securedRequest.addQueryParam(DefaultsParams.SYMBOL, this.symbol.getSymbolName());
        if (Objects.nonNull(this.startTime)) {
            this.securedRequest.addQueryParam(DefaultsParams.START_TIME, this.startTime.toString());
        }
        if (Objects.nonNull(this.endTime)) {
            this.securedRequest.addQueryParam(DefaultsParams.END_TIME, this.endTime.toString());
        }
        if (Objects.nonNull(this.fromId)) {
            this.securedRequest.addQueryParam(DefaultsParams.FROM_ID, this.fromId.toString());
        }
        if (Objects.nonNull(this.limit)) {
            this.securedRequest.addQueryParam(DefaultsParams.LIMIT, this.limit.toString());
        }
    }

    /**
     * @param secretKey
     * @param binanceTime
     * @return
     */
    @Override
    public LinkedHashMap<String, String> getParamsWithSignature(ISecretKey secretKey, IBinanceTime binanceTime) {
        securedRequest.addQueryParams(timingSecurity.getTimeParamsForNow(binanceTime));
        return securedRequest.getParamsWithSignature(secretKey, this);
    }

    @Override
    public String getUrlPathParams() {
        return securedRequest.getUrlPathParams();
    }

    public static class MyTradesRequestBuilder {
        String symbolName;
        Long recvWindow;
        Long startTime;
        Long endTime;
        Long fromId;
        Integer limit;

        public MyTradesRequest build() {
            var myTradesRequest = new MyTradesRequest(symbolName, recvWindow, startTime, endTime, fromId, limit);
            myTradesRequest.fillQueryParams();
            return myTradesRequest;
        }
    }

    /**
     * Builder with required args
     *
     * @param symbolName
     * @return
     */
    public static MyTradesRequestBuilder builder(String symbolName) {
        MyTradesRequestBuilder myTradesRequestBuilder = new MyTradesRequestBuilder();
        myTradesRequestBuilder.symbolName = symbolName;
        return myTradesRequestBuilder;
    }
}