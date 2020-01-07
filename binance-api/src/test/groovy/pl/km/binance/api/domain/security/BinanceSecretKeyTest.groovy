package pl.km.binance.api.domain.security

import org.bouncycastle.util.Strings
import spock.lang.Specification

class BinanceSecretKeyTest extends Specification {
    def "Check if created BinanceSecretKey is properly cleared"() {
        given:
        def secretKey = new BinanceSecretKey("secret".toCharArray())
        expect:
        secretKey.toString() != "secret"
        secretKey.getKey() == "secret".toCharArray()
        secretKey.toBytes() == Strings.toUTF8ByteArray("secret".toCharArray())
        secretKey.destroy()
        secretKey.getKey() != "secret".toCharArray()
        secretKey.toBytes() != Strings.toUTF8ByteArray("secret".toCharArray())
    }
}
