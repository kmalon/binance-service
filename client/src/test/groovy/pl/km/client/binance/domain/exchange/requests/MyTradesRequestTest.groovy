package pl.km.client.binance.domain.exchange.requests

import pl.km.client.binance.domain.request.DefaultsParams
import spock.lang.Specification

class MyTradesRequestTest extends Specification {
    def "New created instance has required params"() {
        given:
        def request = new MyTradesRequest(13, "ETHBTC")
        def requestBuilder = MyTradesRequest.builder(13, "ETHBTC").limit(10).build()
        expect:
        containsRequiredParams(request)
        containsRequiredParams(requestBuilder)
    }

    private static void containsRequiredParams(MyTradesRequest request) {
        assert request.getParams().containsKey(DefaultsParams.TIMESTAMP)
        assert request.getParams().containsKey(DefaultsParams.SYMBOL)

    }
}
