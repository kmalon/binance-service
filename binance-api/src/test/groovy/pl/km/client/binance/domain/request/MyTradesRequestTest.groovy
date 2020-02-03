package pl.km.client.binance.domain.request

import pl.km.binance.api.domain.request.DefaultsParams
import pl.km.binance.api.domain.request.MyTradesRequest
import pl.km.binance.api.domain.security.BinanceSecretKey
import pl.km.client.binance.domain.request.config.TestBinanceTime
import spock.lang.Specification

class MyTradesRequestTest extends Specification {

    def binanceTime

    void setup() {
        binanceTime = new TestBinanceTime()
        binanceTime.setBinanceTime(1500100900)
    }

    def "Should construct MyTradesRequest with at least symbol param and needed params"() {
        given:
        def myTradesRequest = new MyTradesRequest("ETHBNC")
        when:
        def params = myTradesRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), binanceTime)
        then:
        assert params.containsKey(DefaultsParams.SYMBOL)
        assert params.containsKey(DefaultsParams.SIGNATURE)
    }

    def "Should construct MyTradesRequest with all params"() {
        given:
        def myTradesRequest = new MyTradesRequest("ETHBNC", 500, 500, 600, 3, 2)
        when:
        def params = myTradesRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), binanceTime)
        then:
        assert params.containsKey(DefaultsParams.SYMBOL)
        assert params.containsKey(DefaultsParams.RECV_WINDOW)
        assert params.containsKey(DefaultsParams.START_TIME)
        assert params.containsKey(DefaultsParams.END_TIME)
        assert params.containsKey(DefaultsParams.FROM_ID)
        assert params.containsKey(DefaultsParams.LIMIT)
        assert params.containsKey(DefaultsParams.SIGNATURE)
    }

    def "Should construct MyTradesRequest using builder with necessary params"() {
        given:
        def myTradesRequest = MyTradesRequest.builder("ETHBNC")
                .recvWindow(32)
                .startTime(500)
                .endTime(400)
                .build()

        when:
        def params = myTradesRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), binanceTime)
        then:
        assert params.containsKey(DefaultsParams.SYMBOL)
        assert params.containsKey(DefaultsParams.RECV_WINDOW)
        assert params.containsKey(DefaultsParams.START_TIME)
        assert params.containsKey(DefaultsParams.END_TIME)
        assert !params.containsKey(DefaultsParams.FROM_ID)
        assert !params.containsKey(DefaultsParams.LIMIT)
        assert params.containsKey(DefaultsParams.SIGNATURE)
    }


    def "Should return not changed params expect timestamp and signature after second invoke"() {
        given:
        def myTradesRequest = MyTradesRequest.builder("ETHBNC")
                .recvWindow(32)
                .startTime(500)
                .endTime(400)
                .fromId(4)
                .limit(32)
                .build()

        when:
        def params = myTradesRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), binanceTime)
        binanceTime.setBinanceTime(1500100904)
        def params2 = myTradesRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), binanceTime)
        then: "contain all setted params and params not changed after second invoke"
        assert params2.containsKey(DefaultsParams.SYMBOL)
        assert params2.containsKey(DefaultsParams.RECV_WINDOW)
        assert params2.containsKey(DefaultsParams.START_TIME)
        assert params2.containsKey(DefaultsParams.END_TIME)
        assert params2.containsKey(DefaultsParams.FROM_ID)
        assert params2.containsKey(DefaultsParams.LIMIT)
        assert params2.containsKey(DefaultsParams.SIGNATURE)
        assert params.get(DefaultsParams.SYMBOL).equals(params2.get(DefaultsParams.SYMBOL))
        assert params.get(DefaultsParams.RECV_WINDOW).equals(params2.get(DefaultsParams.RECV_WINDOW))
        assert params.get(DefaultsParams.START_TIME).equals(params2.get(DefaultsParams.START_TIME))
        assert params.get(DefaultsParams.END_TIME).equals(params2.get(DefaultsParams.END_TIME))
        assert params.get(DefaultsParams.FROM_ID).equals(params2.get(DefaultsParams.FROM_ID))
        assert params.get(DefaultsParams.LIMIT).equals(params2.get(DefaultsParams.LIMIT))
        and: "after second invoke timestamp and signature changed"
        assert !params.get(DefaultsParams.SIGNATURE).equals(params2.get(DefaultsParams.SIGNATURE))
        assert !params.get(DefaultsParams.TIMESTAMP).equals(params2.get(DefaultsParams.TIMESTAMP))
    }

    def "New created instance has required and added params"() {
        given:
        def request = new MyTradesRequest(13, "ETHBTC")
        def binanceSecretKey = new BinanceSecretKey("secret".toCharArray())
        when:
        def params = request.getParamsWithSignature(binanceSecretKey, binanceTime)
        then:
        assert params.size() == 4
        assert params.containsKey(DefaultsParams.TIMESTAMP)
        assert params.containsKey(DefaultsParams.SYMBOL)
        assert params.containsKey(DefaultsParams.RECV_WINDOW)
        assert params.containsKey(DefaultsParams.SIGNATURE)
    }

    def "New created instance by builder has all added adn required params"() {
        given:
        def requestBuilder = MyTradesRequest.builder("ETHBTC").limit(10).fromId(45).build()
        def binanceSecretKey = new BinanceSecretKey("secret".toCharArray())
        when:
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
        def binanceSecretKey = new BinanceSecretKey("secret".toCharArray())
        when:
        def paramsFirstInvoke = requestBuilder.getParamsWithSignature(binanceSecretKey, binanceTime)
        and:
        binanceTime.setBinanceTime(1500100901)
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