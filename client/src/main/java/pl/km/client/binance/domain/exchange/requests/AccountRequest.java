package pl.km.client.binance.domain.exchange.requests;

import lombok.extern.slf4j.Slf4j;
import pl.km.client.binance.domain.request.BaseRequest;
import pl.km.client.binance.domain.request.DefaultsParams;
import pl.km.client.binance.domain.request.SecuredRequestQueryParams;
import pl.km.client.binance.domain.request.TimingSecurityRequest;

/**
 * Account information request params
 */
@Slf4j
public class AccountRequest extends BaseRequest implements SecuredRequestQueryParams {

    private TimingSecurityRequest timingSecurityRequest;

    public AccountRequest(long timestamp) {
        this(timestamp, DefaultsParams.RECV_WINDOW_DEFAULT);
    }

    public AccountRequest(long timestamp, long recvWindow) {
        this.timingSecurityRequest = new TimingSecurityRequest(timestamp, recvWindow);
        fillParams();
    }

    @Override
    protected void fillParams() {
        super.addQueryParams(timingSecurityRequest.getParams());
    }
}