package pl.km.client.binance.domain.request

import pl.km.binance.api.domain.request.DefaultsParams
import pl.km.binance.api.domain.request.secured.TimingSecurityRequest
import pl.km.client.binance.domain.request.config.TestBinanceTime
import spock.lang.Specification
import spock.lang.Unroll

class TimingSecurityRequestTest extends Specification {

    def binanceTime
    def timestamp

    void setup() {
        binanceTime = new TestBinanceTime()
        timestamp = 1500100900
        binanceTime.setBinanceTime(timestamp)
    }


    @Unroll
    def "All fields are properly filled"() {
        given:
        def timingSecurityRequest = new TimingSecurityRequest(recvWindow as long)
        when:
        def params = timingSecurityRequest.getTimeParamsForNow(binanceTime)
        then:
        params.get(DefaultsParams.RECV_WINDOW) as Long == recvWindowSet
        where:
        recvWindow                           | recvWindowSet
        DefaultsParams.RECV_WINDOW_DEFAULT   | DefaultsParams.RECV_WINDOW_DEFAULT
        DefaultsParams.RECV_WINDOW_MAX       | DefaultsParams.RECV_WINDOW_MAX
        DefaultsParams.RECV_WINDOW_MAX + 100 | DefaultsParams.RECV_WINDOW_MAX
        3000                                 | 3000

    }

    def "Contains necessary query params"() {
        given:
        def timingSecurityRequest = new TimingSecurityRequest(DefaultsParams.RECV_WINDOW_DEFAULT)
        when:
        def params = timingSecurityRequest.getTimeParamsForNow(binanceTime)
        then:
        assert params.containsKey(DefaultsParams.TIMESTAMP)
        assert params.get(DefaultsParams.TIMESTAMP) == Long.toString(timestamp)
        assert params.get(DefaultsParams.RECV_WINDOW) as Long == DefaultsParams.RECV_WINDOW_DEFAULT
    }
}