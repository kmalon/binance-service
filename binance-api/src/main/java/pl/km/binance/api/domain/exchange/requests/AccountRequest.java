package pl.km.binance.api.domain.exchange.requests;

import lombok.extern.slf4j.Slf4j;
import pl.km.binance.api.domain.request.DefaultsParams;
import pl.km.binance.api.domain.request.secured.SecuredBaseRequest;
import pl.km.binance.api.domain.request.secured.SecuredRequestQueryParams;
import pl.km.binance.api.domain.request.secured.TimingSecurityRequest;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.domain.time.BinanceTime;

import java.util.LinkedHashMap;

/**
 * Account information request params
 */
@Slf4j
public class AccountRequest extends SecuredBaseRequest implements SecuredRequestQueryParams {

    private TimingSecurityRequest timingSecurityRequest;

    public AccountRequest() {
        this(DefaultsParams.RECV_WINDOW_DEFAULT);
    }

    public AccountRequest(long recvWindow) {
        super();
        this.timingSecurityRequest = new TimingSecurityRequest(recvWindow);
//        fillInitParams();
    }

//    @Override
//    protected void fillInitParams() {
//    }

    @Override
    public LinkedHashMap<String, String> getParams(BinanceSecretKey binanceSecretKey, BinanceTime binanceTime) {
        super.addQueryParams(timingSecurityRequest.getParams(binanceTime));
        super.addSignature(binanceSecretKey, this);
        return getQueryParams();
    }

    @Override
    public byte[] getRequestQueryParamsStringUrlAsBytes() {
        return super.getRequestQueryParamsStringUrlAsBytes();
    }
}