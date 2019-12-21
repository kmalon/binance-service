package pl.km.binance.api.domain.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Contains query and request params names for Binance API REST
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BinanceApiParams {
    /**
     * API REST secret key for secured endpoints used for singing requests parameters passed into REST API via
     * the signature query param (case sensitive)
     */
    public static final String SIGNATURE = "signature";
}
