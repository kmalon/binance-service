package pl.km.client.binance.domain.request

import pl.km.binance.api.domain.request.DefaultsParams
import pl.km.binance.api.domain.request.MyTradesRequest
import pl.km.binance.api.domain.security.BinanceSecretKey
import pl.km.binance.api.domain.time.IBinanceTime
import spock.lang.Specification

class MyTradesRequestTest extends Specification {
    def "New created instance has required and added params"() {
        given:
        def request = new MyTradesRequest(13, "ETHBTC")
        def timestamp = 1500100900
        def binanceTime = Stub(IBinanceTime.class)
        def binanceSecretKey = new BinanceSecretKey("secret".toCharArray())
        when:
        binanceTime.getBinanceTime() >> timestamp
        def params = request.getParamsWithSignature(binanceSecretKey, binanceTime)
        then:
        //symbol, recvWindow(default), timestamp, signature
        assert params.size() == 4
        assert params.containsKey(DefaultsParams.TIMESTAMP)
        assert params.containsKey(DefaultsParams.SYMBOL)
        assert params.containsKey(DefaultsParams.RECV_WINDOW)
        assert params.containsKey(DefaultsParams.SIGNATURE)
    }

    def "New created instance by builder has all added adn required params"() {
        given:
        def requestBuilder = MyTradesRequest.builder("ETHBTC").limit(10).fromId(45).build()
        def timestamp = 1500100900
        def binanceTime = Stub(IBinanceTime.class)
        def binanceSecretKey = new BinanceSecretKey("secret".toCharArray())
        when:
        binanceTime.getBinanceTime() >> timestamp
        def params = requestBuilder.getParamsWithSignature(binanceSecretKey, binanceTime)
        then:
        //symbol, recvWindow(default), timestamp, signature, limit, fromId
        assert params.size() == 6
        assert params.containsKey(DefaultsParams.TIMESTAMP)
        assert params.containsKey(DefaultsParams.SYMBOL)
        assert params.containsKey(DefaultsParams.RECV_WINDOW)
        assert params.containsKey(DefaultsParams.SIGNATURE)
    }

    def "Getting two times params has different time and signature and other params not changed"() {
        given:
        def requestBuilder = MyTradesRequest.builder("ETHBTC").limit(10).fromId(45).build()
        def timestampOne = 1500100900
        def timestampTwo = 1500100901
        def binanceTime = Stub(IBinanceTime.class)
        def binanceSecretKey = new BinanceSecretKey("secret".toCharArray())
        when:
        binanceTime.getBinanceTime() >>> [timestampOne, timestampTwo]
        def paramsFirstInvoke = requestBuilder.getParamsWithSignature(binanceSecretKey, binanceTime)
        def paramsSecondInvoke = requestBuilder.getParamsWithSignature(binanceSecretKey, binanceTime)
        then:
        //symbol, recvWindow(default), timestamp, signature, limit, fromId
        assert paramsFirstInvoke.size() == 6
        assert paramsFirstInvoke.containsKey(DefaultsParams.TIMESTAMP)
        assert paramsFirstInvoke.containsKey(DefaultsParams.SYMBOL)
        assert paramsFirstInvoke.containsKey(DefaultsParams.RECV_WINDOW)
        assert paramsFirstInvoke.containsKey(DefaultsParams.SIGNATURE)

        assert paramsSecondInvoke.size() == 6
        assert paramsSecondInvoke.containsKey(DefaultsParams.TIMESTAMP)
        assert paramsSecondInvoke.containsKey(DefaultsParams.SYMBOL)
        assert paramsSecondInvoke.containsKey(DefaultsParams.RECV_WINDOW)
        assert paramsSecondInvoke.containsKey(DefaultsParams.SIGNATURE)

        assert paramsFirstInvoke.get(DefaultsParams.TIMESTAMP) != paramsSecondInvoke.get(DefaultsParams.TIMESTAMP)
        assert paramsFirstInvoke.get(DefaultsParams.SIGNATURE) != paramsSecondInvoke.get(DefaultsParams.SIGNATURE)
    }
}