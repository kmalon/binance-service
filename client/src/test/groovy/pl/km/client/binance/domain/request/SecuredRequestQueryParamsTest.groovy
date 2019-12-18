package pl.km.client.binance.domain.request

import pl.km.client.binance.domain.security.BinanceSecretKey
import spock.lang.Specification

class SecuredRequestQueryParamsTest extends Specification {

    def securedRequestQueryParams = Spy(SecuredRequestQueryParams.class)


    def "Query param string should be properly prepared based on fields, values and separator"() {
        given:
        def queryParamsString = "field1=value1" + DefaultsParams.QUERY_PARAMS_SEPARATOR + "field2=value2"
        def queryParams = new LinkedHashMap<String, String>()
        queryParams.put("field1", "value1")
        queryParams.put("field2", "value2")
        when:
        securedRequestQueryParams.getParams() >> [queryParams]
        def queryParamsByte = securedRequestQueryParams.getRequestQueryParamsStringUrlAsBytes()
        then:
        assert new String(queryParamsByte).equals(queryParamsString)
    }

    def "Signature param is added"() {
        given:
        def queryParamsString = "field1=value1"
        def queryParams = new LinkedHashMap<String, String>()
        queryParams.put("field1", "value1")
        when:
        securedRequestQueryParams.getParams() >>> [queryParams]
        def queryParamsByte = securedRequestQueryParams.getRequestQueryParamsStringUrlAsBytes()
        securedRequestQueryParams.addSignature(new BinanceSecretKey("secret".toCharArray()))
        then:
        assert new String(queryParamsByte).equals(queryParamsString)
        assert securedRequestQueryParams.getParams().containsKey(DefaultsParams.SIGNATURE)
    }

}
