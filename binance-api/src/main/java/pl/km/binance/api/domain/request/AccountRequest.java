package pl.km.binance.api.domain.request;

import lombok.extern.slf4j.Slf4j;
import pl.km.binance.api.domain.request.secured.SecuredRequest;
import pl.km.binance.api.domain.request.secured.ISecuredRequestQueryParams;
import pl.km.binance.api.domain.request.secured.TimingSecurityRequest;
import pl.km.binance.api.domain.security.ISecretKey;
import pl.km.binance.api.domain.time.IBinanceTime;

import java.util.LinkedHashMap;

/**
 * Account information request params
 */
@Slf4j
public class AccountRequest implements ISecuredRequestQueryParams {

    private TimingSecurityRequest timingSecurityRequest;
    private SecuredRequest securedRequest;

    public AccountRequest() {
        this(DefaultsParams.RECV_WINDOW_DEFAULT);
    }

    public AccountRequest(long recvWindow) {
        this.timingSecurityRequest = new TimingSecurityRequest(recvWindow);
        this.securedRequest = new SecuredRequest();
    }

    @Override
    public LinkedHashMap<String, String> getParamsWithSignature(ISecretKey secretKey, IBinanceTime binanceTime) {
        securedRequest.addQueryParams(timingSecurityRequest.getTimeParamsForNow(binanceTime));
        securedRequest.addSignature(secretKey, this);
        return securedRequest.getParams();
    }

    @Override
    public String getUrlPathParams() {
        return securedRequest.getUrlPathParams();
    }
}