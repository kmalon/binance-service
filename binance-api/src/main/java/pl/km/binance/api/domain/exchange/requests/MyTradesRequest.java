package pl.km.binance.api.domain.exchange.requests;

import lombok.Builder;
import pl.km.binance.api.domain.exchange.general.Symbol;
import pl.km.binance.api.domain.request.DefaultsParams;
import pl.km.binance.api.domain.request.secured.SecuredBaseRequest;
import pl.km.binance.api.domain.request.secured.SecuredRequestQueryParams;
import pl.km.binance.api.domain.request.secured.TimingSecurityRequest;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.domain.time.BinanceTime;

import java.util.LinkedHashMap;

/**
 * Request for GET /myTrades endpoint
 */
public class MyTradesRequest extends SecuredBaseRequest implements SecuredRequestQueryParams {
    private final Symbol symbol;
    private Long startTime;
    private Long endTime;
    private Long fromId;
    private Integer limit;
    private TimingSecurityRequest timingSecurityRequest;

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
    public MyTradesRequest(String symbolName, long recvWindow, long startTime, long endTime, long fromId, int limit) {
        this(recvWindow, symbolName);
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromId = fromId;
        this.limit = limit;
//        fillParams();
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

    public MyTradesRequest(long recvWindow, String symbol) {
        this.timingSecurityRequest = new TimingSecurityRequest(recvWindow);
        this.symbol = new Symbol(symbol);
//        fillParams();
    }

//    @Override
//    protected void fillParams() {
//    }

    @Override
    public LinkedHashMap<String, String> getParams(BinanceSecretKey binanceSecretKey, BinanceTime binanceTime) {
        super.addQueryParam(DefaultsParams.SYMBOL, symbol.getSymbolName());
        super.addQueryParams(timingSecurityRequest.getParams(binanceTime));
        addSignature(binanceSecretKey,this);
        return getQueryParams();
    }

    @Override
    public byte[] getRequestQueryParamsStringUrlAsBytes() {
        return super.getRequestQueryParamsStringUrlAsBytes();
    }
}