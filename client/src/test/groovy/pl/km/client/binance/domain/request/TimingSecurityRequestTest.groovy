package pl.km.client.binance.domain.request

import spock.lang.Specification
import spock.lang.Unroll

class TimingSecurityRequestTest extends Specification {

    def timestamp = 1500100900

    @Unroll
    def "All fields are properly filled"() {
        given:
        def timingSecurityRequestWithTimestampOnly = new TimingSecurityRequest(timestamp)
        def timingSecurityRequest = new TimingSecurityRequest(timestamp, recvWindow as long)

        expect:
        timingSecurityRequestWithTimestampOnly.timestamp==timestamp
        timingSecurityRequestWithTimestampOnly.recvWindow == DefaultsParams.RECV_WINDOW_DEFAULT
        timingSecurityRequest.timestamp == timestamp
        timingSecurityRequest.recvWindow == recvWindowSet

        where:
        recvWindow                               |   recvWindowSet
        DefaultsParams.RECV_WINDOW_DEFAULT       |   DefaultsParams.RECV_WINDOW_DEFAULT
        DefaultsParams.RECV_WINDOW_MAX           |   DefaultsParams.RECV_WINDOW_MAX
        DefaultsParams.RECV_WINDOW_MAX+100       |   DefaultsParams.RECV_WINDOW_MAX
        3000                                     |   3000

    }

    def "Contains necessary query params"() {
        given:
        def timingSecurityRequest = new TimingSecurityRequest(timestamp)
        expect:
        timingSecurityRequest.getParams().containsKey(DefaultsParams.TIMESTAMP)
    }
}
