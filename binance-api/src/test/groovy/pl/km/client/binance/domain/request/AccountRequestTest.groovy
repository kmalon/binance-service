package pl.km.client.binance.domain.request

import pl.km.binance.api.domain.request.AccountRequest
import pl.km.binance.api.domain.request.DefaultsParams
import pl.km.binance.api.domain.security.BinanceSecretKey
import pl.km.binance.api.domain.time.BinanceTime
import pl.km.client.binance.domain.request.SecuredRequestTest

class AccountRequestTest extends SecuredRequestTest {

    def "New created instance has required params"() {
        given:
        def request = new AccountRequest(13)
        def timestamp = 1500100900
        def binanceTime = Stub(BinanceTime.class)
        def binanceSecretKey = new BinanceSecretKey("secret".toCharArray())
        when:
        binanceTime.getBinanceTime() >> timestamp
        def params = request.getParamsWithSignatureAndForCurrentTime(binanceSecretKey, binanceTime)
        then:
//        timestamp, recvWindow, signature
        assert params.size() == 3
        assert params.containsKey(DefaultsParams.TIMESTAMP)
        assert params.containsKey(DefaultsParams.SIGNATURE)
        assert params.containsKey(DefaultsParams.RECV_WINDOW)
    }

    def "Getting two times params - different timestamp and signature - params size not changing"() {
        given:
        def request = new AccountRequest(13)
        def timestampOne = 1500100900
        def timestampTwo = 1500100901
        def binanceTime = Stub(BinanceTime.class)
        def binanceSecretKey = new BinanceSecretKey("secret".toCharArray())
        when:
        binanceTime.getBinanceTime() >>> [timestampOne, timestampTwo]
        def paramsFirstInvoke = request.getParamsWithSignatureAndForCurrentTime(binanceSecretKey, binanceTime)
        def paramsSecondInvoke = request.getParamsWithSignatureAndForCurrentTime(binanceSecretKey, binanceTime)
        then:
//        timestamp, recvWindow, signature
        assert paramsFirstInvoke.size() == 3
        assert paramsFirstInvoke.containsKey(DefaultsParams.TIMESTAMP)
        assert paramsFirstInvoke.containsKey(DefaultsParams.SIGNATURE)
        assert paramsFirstInvoke.containsKey(DefaultsParams.RECV_WINDOW)

        //        timestamp, recvWindow, signature
        assert paramsSecondInvoke.size() == 3
        assert paramsSecondInvoke.containsKey(DefaultsParams.TIMESTAMP)
        assert paramsSecondInvoke.containsKey(DefaultsParams.SIGNATURE)
        assert paramsSecondInvoke.containsKey(DefaultsParams.RECV_WINDOW)

        assert paramsFirstInvoke.get(DefaultsParams.TIMESTAMP) != paramsSecondInvoke.get(DefaultsParams.TIMESTAMP)
        assert paramsFirstInvoke.get(DefaultsParams.SIGNATURE) != paramsSecondInvoke.get(DefaultsParams.SIGNATURE)
        assert paramsFirstInvoke.get(DefaultsParams.RECV_WINDOW) == paramsSecondInvoke.get(DefaultsParams.RECV_WINDOW)
    }
}