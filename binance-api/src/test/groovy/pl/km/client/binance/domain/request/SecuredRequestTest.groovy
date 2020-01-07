package pl.km.client.binance.domain.request

import pl.km.binance.api.domain.request.DefaultsParams
import pl.km.binance.api.domain.request.secured.SecuredRequest
import pl.km.binance.api.domain.security.BinanceSecretKey
import pl.km.binance.api.domain.time.BinanceTime
import spock.lang.Specification

class SecuredRequestTest extends Specification {

    def "Query param string should be properly prepared based on fields, values and separator"() {
        given:
        SecuredRequest securedRequest = new SecuredRequest()
        def queryParamsString = "field1=value1" + DefaultsParams.QUERY_PARAMS_SEPARATOR + "field2=value2"
        securedRequest.addQueryParam("field1", "value1")
        securedRequest.addQueryParam("field2", "value2")
        when:
        def queryParamsByte = securedRequest.getRequestQueryParamsStringUrlBytes()
        then:
        assert new String(queryParamsByte).equals(queryParamsString)
    }

//    def "Signature param is added"() {
//        given:
//        SecuredRequest securedRequest = new SecuredRequest()
//        def queryParamsString = "field1=value1"
//        def timestamp = 1500100900
//        def binanceTime = Stub(BinanceTime.class)
//        securedRequest.addQueryParam("field1", "value1")
//        when:
//        binanceTime.getBinanceTime() >> timestamp
//        def queryParamsByte = securedRequest.getRequestQueryParamsStringUrlBytes()
////        def params = securedRequest.getParamsWithSignatureAndForCurrentTime(new BinanceSecretKey("secret".toCharArray()), binanceTime)
//        then:
//        assert new String(queryParamsByte).equals(queryParamsString)
////        assert params.containsKey(DefaultsParams.SIGNATURE)
//    }
}