package pl.km.client.binance.domain.exchange.requests;

import lombok.Builder;
import pl.km.client.binance.domain.exchange.general.Symbol;
import pl.km.client.binance.domain.request.BaseRequest;
import pl.km.client.binance.domain.request.DefaultsParams;
import pl.km.client.binance.domain.request.SecuredRequestQueryParams;
import pl.km.client.binance.domain.request.TimingSecurityRequest;

/**
 * Request for GET /myTrades endpoint
 */
public class MyTradesRequest extends BaseRequest implements SecuredRequestQueryParams {
    private final Symbol symbol;
    private long startTime;
    private long endTime;
    private long fromId;
    private int limit;
    private TimingSecurityRequest timingSecurityRequest;

    /**
     * All Args constructor and builder created based on its parameters
     *
     * @param symbolName
     * @param timestamp
     * @param recvWindow
     * @param startTime
     * @param endTime
     * @param fromId
     * @param limit
     */
    @Builder
    public MyTradesRequest(String symbolName, long timestamp, long recvWindow, long startTime, long endTime, long fromId, int limit) {
        this(timestamp, recvWindow, symbolName);
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromId = fromId;
        this.limit = limit;
        fillParams();
    }

    /**
     * Builder with required args
     *
     * @param symbolName
     * @param timestamp
     * @return
     */
    public static MyTradesRequestBuilder builder(long timestamp, String symbolName) {
        MyTradesRequestBuilder myTradesRequestBuilder = new MyTradesRequestBuilder();
        myTradesRequestBuilder.symbolName = symbolName;
        myTradesRequestBuilder.timestamp = timestamp;
        return myTradesRequestBuilder;
    }

    public MyTradesRequest(long timestamp, String symbol) {
        this(timestamp, DefaultsParams.RECV_WINDOW_DEFAULT, symbol);
    }

    public MyTradesRequest(long timestamp, long recvWindow, String symbol) {
        this.timingSecurityRequest = new TimingSecurityRequest(timestamp, recvWindow);
        this.symbol = new Symbol(symbol);
        fillParams();
    }

    @Override
    protected void fillParams() {
        super.addQueryParams(timingSecurityRequest.getParams());
        super.addQueryParam(DefaultsParams.SYMBOL, symbol.getSymbolName());
    }
}