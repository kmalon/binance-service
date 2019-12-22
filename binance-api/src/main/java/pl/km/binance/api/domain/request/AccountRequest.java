package pl.km.binance.api.domain.request;

import lombok.extern.slf4j.Slf4j;
import pl.km.binance.api.domain.request.secured.SecuredRequest;
import pl.km.binance.api.domain.request.secured.SecuredRequestQueryParams;
import pl.km.binance.api.domain.request.secured.TimingSecurityRequest;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.domain.time.BinanceTime;

import java.util.LinkedHashMap;

/**
 * Account information request params
 */
@Slf4j
public class AccountRequest implements SecuredRequestQueryParams {

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
    public LinkedHashMap<String, String> getParamsWithSignatureAndForCurrentTime(BinanceSecretKey binanceSecretKey, BinanceTime binanceTime) {
        securedRequest.addQueryParams(timingSecurityRequest.getTimeParamsForNow(binanceTime));
        securedRequest.addSignature(binanceSecretKey, this);
        return securedRequest.getQueryParams();
    }

    @Override
    public byte[] getRequestQueryParamsStringUrlBytes() {
        return securedRequest.getRequestQueryParamsStringUrlBytes();
    }
}