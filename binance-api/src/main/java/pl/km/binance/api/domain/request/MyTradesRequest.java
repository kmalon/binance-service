package pl.km.binance.api.domain.request;

import lombok.Builder;
import pl.km.binance.api.domain.exchange.general.Symbol;
import pl.km.binance.api.domain.request.secured.SecuredRequest;
import pl.km.binance.api.domain.request.secured.SecuredRequestQueryParams;
import pl.km.binance.api.domain.request.secured.TimingSecurityRequest;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.domain.time.BinanceTime;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Request for GET /myTrades endpoint
 */
public class MyTradesRequest implements SecuredRequestQueryParams {
    private final Symbol symbol;
    private Long startTime;
    private Long endTime;
    private Long fromId;
    private Integer limit;
    private TimingSecurityRequest timingSecurityRequest;
    private SecuredRequest securedRequest;

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
        this(recvWindow, symbolName);
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromId = fromId;
        this.limit = limit;
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

    public MyTradesRequest(String symbol) {
        this(DefaultsParams.RECV_WINDOW_DEFAULT, symbol);
    }

    public MyTradesRequest(Long recvWindow, String symbol) {
        this.timingSecurityRequest = new TimingSecurityRequest(recvWindow);
        this.symbol = new Symbol(symbol);
        this.securedRequest = new SecuredRequest();
    }

    @Override
    public LinkedHashMap<String, String> getParamsWithSignatureAndForCurrentTime(BinanceSecretKey binanceSecretKey, BinanceTime binanceTime) {
        securedRequest.addQueryParam(DefaultsParams.SYMBOL, symbol.getSymbolName());
        securedRequest.addQueryParams(timingSecurityRequest.getTimeParamsForNow(binanceTime));
        if (Objects.nonNull(this.startTime)) {
            securedRequest.addQueryParam(DefaultsParams.START_TIME, this.startTime.toString());
        }
        if (Objects.nonNull(this.endTime)) {
            securedRequest.addQueryParam(DefaultsParams.END_TIME, this.endTime.toString());
        }
        if (Objects.nonNull(this.fromId)) {
            securedRequest.addQueryParam(DefaultsParams.FROM_ID, this.fromId.toString());
        }
        if (Objects.nonNull(this.limit)) {
            securedRequest.addQueryParam(DefaultsParams.LIMIT, this.limit.toString());
        }
        securedRequest.addSignature(binanceSecretKey, this);
        return securedRequest.getQueryParams();
    }

    @Override
    public byte[] getRequestQueryParamsStringUrlBytes() {
        return securedRequest.getRequestQueryParamsStringUrlBytes();
    }
}