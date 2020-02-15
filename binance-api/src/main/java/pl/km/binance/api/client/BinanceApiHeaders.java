package pl.km.binance.api.client;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Binance Api REST Headers used for creating requests
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class BinanceApiHeaders {
    /**
     * Rest API header for passing api-key for secured endpoints - case sensitive
     */
    static final String X_MBX_APIKEY = "X-MBX-APIKEY";
}
