package pl.km.binance.api.domain.request;

import lombok.Builder;
import pl.km.binance.api.client.IBinanceTime;

import java.util.LinkedHashMap;
import java.util.Objects;

public class AllOrderList implements ISignedRequest {
    private Long fromId;
    private Long startTime;
    private Long endTime;
    private Integer limit;
    private ITimingSecurity timingSecurity;
    private ISecuredRequest securedRequest;

    public AllOrderList() {
        this(DefaultsParams.RECV_WINDOW_DEFAULT);
    }

    public AllOrderList(Long recvWindow) {
        this(null, null, null, null, recvWindow);
    }

    @Builder
    public AllOrderList(Long fromId, Long startTime, Long endTime, Integer limit, Long recvWindow) {
        this.fromId = fromId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.limit = limit;
        this.timingSecurity = new TimingSecurityRequest(recvWindow);
        this.securedRequest = new SecuredRequest();
        fillQueryParams();
    }

    private void fillQueryParams() {
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

    @Override
    public LinkedHashMap<String, String> getParamsWithSignature(ISecretKey secretKey, IBinanceTime binanceTime) {
        securedRequest.addQueryParams(timingSecurity.getTimeParamsForNow(binanceTime));
        return securedRequest.getParamsWithSignature(secretKey, this);
    }

    @Override
    public String getUrlPathParams() {
        return securedRequest.getUrlPathParams();
    }
}