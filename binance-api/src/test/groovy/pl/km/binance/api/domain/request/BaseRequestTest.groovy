package pl.km.binance.api.domain.request

import spock.lang.Specification

class BaseRequestTest extends Specification {
    def "should order be the same after getting query params"() {
        given: "BaseReqest with query params"
        def baseRequest = new BaseRequest()
        baseRequest.addQueryParam("firstParam", "firstValue")
        baseRequest.addQueryParam("secondParam", "secondValue")
        baseRequest.addQueryParam("thirdParam", "thirdValue")
        when: "getting reqest params"
        def params = baseRequest.getParams()
        then: "returned params should has the same order"
        def orginal = baseRequest.getParams()
        assert params.size() == orginal.size()
        assert params.values().getAt(0).equals(orginal.values().getAt(0))
        assert params.values().getAt(1).equals(orginal.values().getAt(1))
        assert params.values().getAt(2).equals(orginal.values().getAt(2))
    }

    def "should not modify internal query params after remove"() {
        given: "BaseReqest with query params"
        def baseRequest = new BaseRequest()
        baseRequest.addQueryParam("firstParam", "firstValue")
        baseRequest.addQueryParam("secondParam", "secondValue")
        baseRequest.addQueryParam("thirdParam", "thirdValue")
        when: "getting reqest params and remove one element"
        def params = baseRequest.getParams()
        params.remove("secondParam")
        then: "orginal params should be not changed"
        assert params.size() == 2
        assert baseRequest.getParams().size() == 3
    }

    def "should not modify internal query params after edit value"() {
        given: "BaseReqest with query params"
        def baseRequest = new BaseRequest()
        baseRequest.addQueryParam("firstParam", "firstValue")
        baseRequest.addQueryParam("secondParam", "secondValue")
        baseRequest.addQueryParam("thirdParam", "thirdValue")
        when: "getting reqest params and edit one element"
        def params = baseRequest.getParams()
        params.put("secondParam", "newValue")
        then: "orginal params should be not changed"
        assert params.size() == 3
        assert baseRequest.getParams().size() == 3
        assert !(params.get("secondParam").equals(baseRequest.getParams().get("secondParam")))
    }
}