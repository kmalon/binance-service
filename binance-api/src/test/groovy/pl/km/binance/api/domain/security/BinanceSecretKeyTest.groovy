package pl.km.binance.api.domain.securitySeck

import org.bouncycastle.util.Strings
import pl.km.binance.api.domain.request.BinanceSecretKey
import spock.lang.Specification

class BinanceSecretKeyTest extends Specification {
    def "Should properly clear BinanceSecretKey"() {
        given:
        def secretKey = new BinanceSecretKey("secret".toCharArray())
        expect: "toString method of Key must not return secret and secret should be returned after invoke proper method"
        secretKey.toString() != "secret"
        secretKey.getKey() == Strings.toUTF8ByteArray("secret".toCharArray())
        and: "After destroy key must be removed from memory"
        secretKey.destroy()
        secretKey.getKey() != Strings.toUTF8ByteArray("secret".toCharArray())
    }
}