package pl.km.binance.api.domain.request;

import lombok.extern.slf4j.Slf4j;
import pl.km.binance.api.domain.request.secured.*;
import pl.km.binance.api.domain.security.ISecretKey;
import pl.km.binance.api.domain.time.IBinanceTime;

import java.util.LinkedHashMap;

/**
 * Account information request params
 */
@Slf4j
public class AccountRequest implements ISignedRequest {

    private ITimingSecurity timingSecurity;
    private ISecuredRequest securedRequest;

    public AccountRequest() {
        this(DefaultsParams.RECV_WINDOW_DEFAULT);
    }

    public AccountRequest(long recvWindow) {
        this.timingSecurity = new TimingSecurityRequest(recvWindow);
        this.securedRequest = new SecuredRequest();
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