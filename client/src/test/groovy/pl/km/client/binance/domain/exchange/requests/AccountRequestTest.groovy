package pl.km.client.binance.domain.exchange.requests

import pl.km.client.binance.domain.request.DefaultsParams
import spock.lang.Specification

class AccountRequestTest extends Specification {

    def "New created instance has required params"() {
        given:
        def request = new AccountRequest(13)
        expect:
        assert request.getParams().containsKey(DefaultsParams.TIMESTAMP)
    }
}
