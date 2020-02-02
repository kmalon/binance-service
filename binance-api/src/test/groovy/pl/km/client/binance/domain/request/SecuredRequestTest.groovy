package pl.km.client.binance.domain.request

import pl.km.binance.api.domain.request.DefaultsParams
import pl.km.binance.api.domain.request.secured.SecuredRequest
import pl.km.binance.api.domain.security.BinanceSecretKey
import pl.km.client.binance.domain.request.config.TestBinanceTime
import pl.km.client.binance.domain.request.config.TestSignedRequest
import spock.lang.Specification

class SecuredRequestTest extends Specification {

    def binanceTime

    void setup() {
        binanceTime = new TestBinanceTime()
        binanceTime.setBinanceTime(1500100900)
    }

    def "Query param string should be properly prepared based on fields, values and separator"() {
        given:
        SecuredRequest securedRequest = new SecuredRequest()
        def queryParamsString = "field1=value1" + DefaultsParams.QUERY_PARAMS_SEPARATOR + "field2=value2"
        securedRequest.addQueryParam("field1", "value1")
        securedRequest.addQueryParam("field2", "value2")
        when:
        def queryParams = securedRequest.getUrlPathParams()
        then:
        assert queryParams.equals(queryParamsString)
    }

    def "Signature param is added"() {
        given:
        SecuredRequest securedRequest = new SecuredRequest()
        def queryParamsString = "field1=value1"
        def signedRequest = new TestSignedRequest(queryParamsString)
        securedRequest.addQueryParam("field1", "value1")
        when:
        def queryParams = securedRequest.getUrlPathParams()
        def params = securedRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), signedRequest)
        then:
        assert queryParams.equals(queryParamsString)
        assert params.containsKey(DefaultsParams.SIGNATURE)
    }

    def "Should always return the same number of query params"() {
        given:
        SecuredRequest securedRequest = new SecuredRequest()
        def queryParamsString = "field1=value1"
        def signedRequest = new TestSignedRequest(queryParamsString)
        securedRequest.addQueryParam("field1", "value1")
        when:
        def queryParams = securedRequest.getUrlPathParams()
        def params1 = securedRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), signedRequest)
        def params2 = securedRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), signedRequest)
        def params3 = securedRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), signedRequest)
        then:
        assert params1.size() == 2
        assert params2.size() == 2
        assert params3.size() == 2
        assert params1.containsKey(DefaultsParams.SIGNATURE)
        assert params2.containsKey(DefaultsParams.SIGNATURE)
        assert params3.containsKey(DefaultsParams.SIGNATURE)
    }

    def "Should return the same number of query params with different signature if secret changed"() {
        given:
        SecuredRequest securedRequest = new SecuredRequest()
        def queryParamsString = "field1=value1"
        def signedRequest = new TestSignedRequest(queryParamsString)
        securedRequest.addQueryParam("field1", "value1")
        when:
        def queryParams = securedRequest.getUrlPathParams()
        def params1 = securedRequest.getParamsWithSignature(new BinanceSecretKey("secret".toCharArray()), signedRequest)
        def params2 = securedRequest.getParamsWithSignature(new BinanceSecretKey("new secret".toCharArray()), signedRequest)
        then:
        assert params1.size() == 2
        assert params2.size() == 2
        assert params1.containsKey(DefaultsParams.SIGNATURE)
        assert params2.containsKey(DefaultsParams.SIGNATURE)
        assert !params1.get(DefaultsParams.SIGNATURE).equals(params2.get(DefaultsParams.SIGNATURE))
    }
}