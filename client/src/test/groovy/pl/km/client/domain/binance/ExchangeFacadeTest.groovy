package pl.km.client.domain.binance

import pl.km.client.domain.binance.config.BinanceApiTest
import spock.lang.Specification

class ExchangeFacadeTest extends Specification {
    def "should cache ExchangeInfo and return this cached value"() {
        given: "BinanceApi client"
        BinanceApiTest binanceApiClient = new BinanceApiTest(false)
        def binanceFacade = new ExchangeFacade(binanceApiClient)
        when: "getting ExchangeInfo"
        def exchangeInfoInvokeCounter = binanceApiClient.getExchangeInfoInvokeCounter()
        def exchangeInfo = binanceFacade.getExchangeInfo()
        then: "should retrun cached value"
        assert exchangeInfoInvokeCounter == binanceApiClient.getExchangeInfoInvokeCounter()
    }

    def "should return new value after manual synchronization"() {
        given: "BinanceApi client"
        BinanceApiTest binanceApiClient = new BinanceApiTest(false)
        def binanceFacade = new ExchangeFacade(binanceApiClient)
        when: "synchronized ExchangeInfo"
        def exchangeInfoInvokeCounter = binanceApiClient.getExchangeInfoInvokeCounter()
        def exchangeInfo = binanceFacade.getExchangeInfo()
        binanceFacade.synchronizeExchangeInfo()
        then: "should retrun new value"
        assert exchangeInfoInvokeCounter + 1 == binanceApiClient.getExchangeInfoInvokeCounter()
        assert exchangeInfo != binanceFacade.getExchangeInfo()
    }

    def "should try gather new ExchangeInfo if during startup fails"() {
        given: "BinanceApi client"
        BinanceApiTest binanceApiClient = new BinanceApiTest(true)
        when: "fails during gathering exchange info at startup"
        def binanceFacade = new ExchangeFacade(binanceApiClient)
        then: "should invoke new rest request after first invoke"
        assert binanceApiClient.getExchangeInfoInvokeCounter() == 0
        binanceFacade.getExchangeInfo()
        assert binanceApiClient.getExchangeInfoInvokeCounter() == 1
        and: "after successful synchronization should return cache value"
        binanceFacade.getExchangeInfo()
        assert binanceApiClient.getExchangeInfoInvokeCounter() == 1
    }
}