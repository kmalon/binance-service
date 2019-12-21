package pl.km.binance.api.domain.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * API REST HEADERS not related directly passed into the Binance REST API invocations
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiHeaders {
    /**
     * API REST header used for pass Binance secret key for singing secured request
     */
    public static final String SECRET_KEY = "signature";
}
